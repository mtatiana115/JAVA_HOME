package Model;

import Configuration.ConfigDB;
import Entity.Doctor;
import Entity.Specialty;
import Repository.CRUD;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorModel implements CRUD {
    @Override
    public Object insert(Object object) {

        //castear objeto
        Doctor objDoctor = (Doctor) object;

        //1. Abrir la conexión
        Connection objconnection = ConfigDB.openConnection();

        try {
            //2. sentencia sql
            String sql = "INSERT INTO doctor (doctorName, doctorLastName, idSpecialty) VALUES (?,?,?);";

            //3. Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objconnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. Asignar los ?
            objPrepare.setString(1, objDoctor.getName());
            objPrepare.setString(2, objDoctor.getLastName());
            objPrepare.setInt(3, objDoctor.getIdSpecialty());

            //5.Ejecutar el query
            objPrepare.execute();

            //6.Obtener resultado
            ResultSet objResulset = objPrepare.getGeneratedKeys();
            while (objResulset.next()){
                objDoctor.setId(objResulset.getInt(1));
            }

            //7.Cerrar el preparedStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Doctor insertion was succesfull.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding doctor" + e.getMessage());
        }

        //8. Cerrar la conexión
        ConfigDB.closeConnection();

        return objDoctor;
    }

    @Override
    public boolean update(Object object) {

        Doctor objDoctor = (Doctor) object;

        //1.Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //1.1 variable bandera para saber si se aactualizó
        boolean isUpdate = false;

        try {
            //2. Sentencia sql
            String sql = "UPDATE doctor SET doctorName = ?, doctorLastName = ?, idSpecialty = ? WHERE doctorId = ?;";

            //3. Prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. dar valor a los query parameters
            objPrepare.setString(1,objDoctor.getName());
            objPrepare.setString(2,objDoctor.getLastName());
            objPrepare.setInt(3, objDoctor.getIdSpecialty());
            objPrepare.setInt(4, objDoctor.getId());

            //5. Ejecutar el query
            int rowAffected = objPrepare.executeUpdate();
            if (rowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }
            //6. Cerrar el preparedStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Doctor insertion was succesfull.");

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
            String sql = "DELETE FROM doctor WHERE doctorId = ?;";

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
        List<Object> listDoctor = new ArrayList<>();

        //1. abrir la conexión
        Connection objconnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM doctor;";

            //3. prepared statement
            PreparedStatement objPrepare = (PreparedStatement) objconnection.prepareStatement(sql);

            //4. que devuelva un resultado => executeQuery
            ResultSet objResulset = objPrepare.executeQuery();

            //5. Obtener los resultados
            while (objResulset.next()){

                //Crear la instancia de doctor
                Doctor doctor = new Doctor();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                doctor.setId(objResulset.getInt("doctorId"));
                doctor.setName(objResulset.getString("doctorName"));
                doctor.setLastName(objResulset.getString("doctorLastName"));
                doctor.setIdSpecialty(objResulset.getInt("idSpecialty"));

                //agregar a la lista la especialidad
                listDoctor.add(doctor);
            }
        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //cerrar la conexión
        ConfigDB.closeConnection();

        return listDoctor;

    }

    @Override
    public Object findById(int id) {
        Doctor objDoctor = null;

        //1. abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql= "SELECT * FROM doctor WHERE doctorId = ?;";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos valor al ?
            objPrepare.setInt(1,id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente => obtener resultados
            while (objResult.next()){

                //Crear la instancia de doctor
                objDoctor = new Doctor();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objDoctor.setId(objResult.getInt("doctorId"));
                objDoctor.setName(objResult.getString("doctorName"));
                objDoctor.setLastName(objResult.getString("doctorLastName"));
                objDoctor.setIdSpecialty(objResult.getInt("idSpecialty"));
            }

        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }
        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return objDoctor;
    }

    public List<Object> findByName(String name) {

        List <Object> listDoctor = new ArrayList<>();

        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2.Sentencia sql
            String sql = "SELECT * FROM doctor WHERE doctorName LIKE ?;";

            //3. prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4.Damos valor a query parameter
            objPrepare.setString(1, "%" + name + "%");

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. verificar registro siguiente
            while (objResult.next()){

                //Crear la instancia Doctor
                Doctor objDoctor = new Doctor();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objDoctor.setId(objResult.getInt("doctorId"));
                objDoctor.setName(objResult.getString("doctorName"));
                objDoctor.setLastName(objResult.getString("doctorLastName"));
                objDoctor.setIdSpecialty(objResult.getInt("idSpecialty"));

                //Agregar a la lista final

                listDoctor.add(objDoctor);
            }


        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return listDoctor;

    }

    public List<Object> findDetailsDoctorAll(int id) {
        List<Object> listDoctorsBySpecialty = new ArrayList<>();

        //1. abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM doctor INNER JOIN specialty ON doctor.idSpecialty = specialty.id WHERE doctor.idSpecialty = ?;";


            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos valor al ?
            objPrepare.setInt(1, id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente => obtener resultados
            while (objResult.next()) {

                Doctor objDoctor = null;

                //Crear la instancia de doctor  y de la segunda tabla
                objDoctor = new Doctor();
                Specialty objSpecialty = new Specialty();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objDoctor.setId(objResult.getInt("doctorId"));
                objDoctor.setName(objResult.getString("doctorName"));
                objDoctor.setLastName(objResult.getString("doctorLastName"));
                objDoctor.setIdSpecialty(objResult.getInt("idSpecialty"));

                //lleno mi segundo objeto
                objSpecialty.setId(objResult.getInt("idSpecialty"));
                objSpecialty.setName(objResult.getString("name"));
                objSpecialty.setDescription(objResult.getString("description"));

                //incluir el objeto 2 dentro del 1 (especialidad dentro de doctor)
                objDoctor.setSpecialty(objSpecialty);

                listDoctorsBySpecialty.add(objDoctor);
                //añadir al controlador
                //objDoctor.getSpecialty().getName();
            }


        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }
        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return listDoctorsBySpecialty;
    }

    public Object findDetailsDoctorById(int id) {
        Doctor objDoctor = new Doctor();

        //1. abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM doctor INNER JOIN specialty ON doctor.idSpecialty = specialty.id WHERE doctor.doctorId = ?;";


            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos valor al ?
            objPrepare.setInt(1, id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente => obtener resultados
            while (objResult.next()) {

                //Crear la instancia de doctor  y de la segunda tabla
                Specialty objSpecialty = new Specialty();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objDoctor.setId(objResult.getInt("doctorId"));
                objDoctor.setName(objResult.getString("doctorName"));
                objDoctor.setLastName(objResult.getString("doctorLastName"));
                objDoctor.setIdSpecialty(objResult.getInt("idSpecialty"));

                //lleno mi segundo objeto
                objSpecialty.setId(objResult.getInt("idSpecialty"));
                objSpecialty.setName(objResult.getString("name"));
                objSpecialty.setDescription(objResult.getString("description"));

                //incluir el objeto 2 dentro del 1 (especialidad dentro de doctor)
                objDoctor.setSpecialty(objSpecialty);

                //añadir al controlador
                //objDoctor.getSpecialty().getName();
            }


        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }
        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return objDoctor;
    }
}
