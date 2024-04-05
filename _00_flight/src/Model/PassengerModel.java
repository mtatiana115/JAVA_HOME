package Model;

import Configuration.ConfigDB;
import Entity.Passenger;
import Repository.CRUD;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerModel implements CRUD {
    @Override
    public Object insert(Object object) {

        //castear objeto
        Passenger objPassenger = (Passenger) object;

        //1. Abrir la conexión
        Connection objconnection = ConfigDB.openConnection();

        try {
            //2. sentencia sql
            String sql = "INSERT INTO passenger (name, lastname, idDocument ) VALUES (?,?,?);";

            //3. Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objconnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. Asignar los ?
            objPrepare.setString(1, objPassenger.getName());
            objPrepare.setString(2, objPassenger.getLastname());
            objPrepare.setString(3, objPassenger.getIdDocument());

            //5.Ejecutar el query
            objPrepare.execute();

            //6.Obtener resultado
            ResultSet objResulset = objPrepare.getGeneratedKeys();
            while (objResulset.next()){
                objPassenger.setId(objResulset.getInt(1));
            }

            //7.Cerrar el preparedStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Passenger insertion was succesfull.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding passenger" + e.getMessage());
        }

        //8. Cerrar la conexión
        ConfigDB.closeConnection();

        return objPassenger;
    }

    @Override
    public boolean update(Object object) {

        Passenger objPassenger = (Passenger) object;

        //1.Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //1.1 variable bandera para saber si se aactualizó
        boolean isUpdate = false;

        try {
            //2. Sentencia sql
            String sql = "UPDATE passenger SET name = ?, lastname = ?, idDocument = ? WHERE id = ?;";

            //3. Prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. dar valor a los query parameters
            objPrepare.setString(1,objPassenger.getName());
            objPrepare.setString(2,objPassenger.getLastname());
            objPrepare.setString(3, objPassenger.getIdDocument());
            objPrepare.setInt(4,objPassenger.getId());

            //5. Ejecutar el query
            int rowAffected = objPrepare.executeUpdate();
            if (rowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }
            //6. Cerrar el preparedStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Passenger insertion was succesfull.");

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
            String sql = "DELETE FROM passenger WHERE id = ?;";

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

        List<Object> listPassenger = new ArrayList<>();

        //1. abrir la conexión
        Connection objconnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM passenger;";

            //3. prepared statement
            PreparedStatement objPrepare = (PreparedStatement) objconnection.prepareStatement(sql);

            //4. que devuelva un resultado => executeQuery
            ResultSet objResulset = objPrepare.executeQuery();

            //5. Obtener los resultados
            while (objResulset.next()){

                //Crear la instancia de passenger
                Passenger passenger = new Passenger();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                passenger.setId(objResulset.getInt("id"));
                passenger.setName(objResulset.getString("name"));
                passenger.setLastname(objResulset.getString("lastname"));
                passenger.setIdDocument(objResulset.getString("idDocument"));

                //agregar a la lista la especialidad
                listPassenger.add(passenger);
            }
        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //cerrar la conexión
        ConfigDB.closeConnection();

        return listPassenger;
    }

    @Override
    public Object findById(int id) {

        Passenger objPassenger = null;

        //1. abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql= "SELECT * FROM passenger WHERE id = ?;";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos valor al ?
            objPrepare.setInt(1,id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente => obtener resultados
            while (objResult.next()){

                //Crear la instancia de passenger
                objPassenger = new Passenger();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objPassenger.setId(objResult.getInt("id"));
                objPassenger.setName(objResult.getString("name"));
                objPassenger.setLastname(objResult.getString("lastname"));
                objPassenger.setIdDocument(objResult.getString("idDocument"));
            }

        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }
        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return objPassenger;
    }


    public Object findByName(String name) {
        List<Object> listPassenger = new ArrayList<>();

        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2.Sentencia sql
            String sql = "SELECT * FROM passenger WHERE name LIKE ?;";

            //3. prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4.Damos valor a query parameter
            objPrepare.setString(1, "%" + name + "%");

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. verificar registro siguiente
            while (objResult.next()) {

                //Crear la instancia Passenger
                Passenger objPassenger = new Passenger();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objPassenger.setId(objResult.getInt("id"));
                objPassenger.setName(objResult.getString("name"));
                objPassenger.setLastname(objResult.getString("lastname"));
                objPassenger.setIdDocument(objResult.getString("idDocument"));

                //Agregar a la lista final

                listPassenger.add(objPassenger);
            }


        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return listPassenger;
    }
}
