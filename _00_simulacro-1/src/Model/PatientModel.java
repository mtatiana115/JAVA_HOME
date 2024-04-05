package Model;

import Configuration.ConfigDB;
import Entity.Patient;
import Repository.CRUD;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientModel implements CRUD {
    @Override
    public Object insert(Object object) {

        //Castear objeto
        Patient objPatient = (Patient) object;

        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "INSERT INTO patient (name, lastName, birthdate, idDocument) VALUES (?, ?, ?, ?);";

            //3. Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. Asignar los ?
            objPrepare.setString(1, objPatient.getName());
            objPrepare.setString(2, objPatient.getLastName());
            objPrepare.setDate(3, objPatient.getBirthdate());
            objPrepare.setString(4, objPatient.getIdDocument());

            //5. Ejecutar el query
            objPrepare.execute();

            //6. Obtener resultado para el id
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objPatient.setId(objResult.getInt(1));
            }

            //7. Cerrar el prepared statement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Patient insertion was successful");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding specialty" + e.getMessage());
        }
        //8. Cerrar la conexión
        ConfigDB.closeConnection();

        return objPatient;
    }

    @Override
    public boolean update(Object object) {

        Patient objPatient = (Patient) object;

        //1.Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //1.1 variable bandera para saber si se aactualizó
        boolean isUpdate = false;

        try {
            //2. Sentencia sql
            String sql = "UPDATE patient SET name = ?, lastName = ?, birthdate = ?, idDocument = ?, WHERE id = ?;";

            //3. Prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. dar valor a los query parameters
            objPrepare.setString(1,objPatient.getName());
            objPrepare.setString(2,objPatient.getLastName());
            objPrepare.setDate(3,objPatient.getBirthdate());
            objPrepare.setString(4,objPatient.getIdDocument());
            objPrepare.setInt(5, objPatient.getId());

            //5. Ejecutar el query
            int rowAffected = objPrepare.executeUpdate();
            if (rowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }
            //6. Cerrar el preparedStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Patient insertion was succesfull.");

        } catch (SQLException e) {
            System.out.println("Error in database " + e.getMessage());
        }

        //7. Cerrar la conexion
        ConfigDB.closeConnection();

        return isUpdate;
    }

    @Override
    public boolean delete(int id) {
        //Variable boolean bandera para saber el estado de la conexión
        boolean isDelete = false;

        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "DELETE FROM patient WHERE id = ?;";

            //3. Prepared statement
            PreparedStatement objPrepare = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. Dar valor a queryparameters
            objPrepare.setInt(1,id);

            //5. ejecutar query
            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null, "successful deletion");
            }

            //6. cerrar preparedStatement

        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //7. Cerrar conexión
        ConfigDB.closeConnection();

        return isDelete;
    }


    @Override
    public List<Object> findAll() {
        List<Object> listPatient = new ArrayList<>();

        //1.Abrir la conexión
        Connection objconnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM patient;";

            //3. prepared statement
            PreparedStatement objPrepare = (PreparedStatement) objconnection.prepareStatement(sql);

            //4. que devuelva un resultado => executeQuery
            ResultSet objResulset = objPrepare.executeQuery();

            //5. Obtener los resultados
            while (objResulset.next()){

                //Crear la instancia de patient
                Patient patient = new Patient();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                patient.setId(objResulset.getInt("id"));
                patient.setName(objResulset.getString("name"));
                patient.setLastName(objResulset.getString("lastName"));
                patient.setBirthdate(objResulset.getDate("birthdate"));
                patient.setIdDocument(objResulset.getString("idDocument"));

                //agregar a la lista la especialidad
                listPatient.add(patient);
            }
        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //cerrar la conexión
        ConfigDB.closeConnection();

        return listPatient;
    }

    @Override
    public Object findById(int id) {

        Patient objPatient = null;

        //1. abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql= "SELECT * FROM patient WHERE id = ?;";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos valor al ?
            objPrepare.setInt(1,id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente => obtener resultados
            while (objResult.next()){

                //Crear la instancia de patient
                objPatient = new Patient();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objPatient.setId(objResult.getInt("id"));
                objPatient.setName(objResult.getString("name"));
                objPatient.setLastName(objResult.getString("lastName"));
                objPatient.setBirthdate(objResult.getDate("birthdate"));
                objPatient.setIdDocument(objResult.getString("idDocument"));

            }

        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }
        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return objPatient;
    }

    public List<Object> findByName(String name) {

        List <Object> listPatient = new ArrayList<>();

        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2.Sentencia sql
            String sql = "SELECT * FROM patient WHERE name LIKE ?;";

            //3. prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4.Damos valor a query parameter
            objPrepare.setString(1, "%" + name + "%");

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. verificar registro siguiente
            while (objResult.next()){

                //Crear la instancia Patient
                Patient objPatient = new Patient();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objPatient.setId(objResult.getInt("id"));
                objPatient.setName(objResult.getString("name"));
                objPatient.setLastName(objResult.getString("lastName"));
                objPatient.setBirthdate(objResult.getDate("birthdate"));
                objPatient.setIdDocument(objResult.getString("idDocument"));

                //Agregar a la lista final

                listPatient.add(objPatient);
            }


        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return listPatient;
    }
}
