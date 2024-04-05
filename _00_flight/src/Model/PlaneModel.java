package Model;

import Configuration.ConfigDB;
import Entity.Plane;
import Repository.CRUD;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaneModel implements CRUD {
    @Override
    public Object insert(Object object) {

        //castear objeto
        Plane objPlane = (Plane) object;

        //1. Abrir la conexión
        Connection objconnection = ConfigDB.openConnection();

        try {
            //2. sentencia sql
            String sql = "INSERT INTO plane (model, capacity) VALUES (?,?);";

            //3. Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objconnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. Asignar los ?
            objPrepare.setString(1, objPlane.getModel());
            objPrepare.setInt(2, objPlane.getCapacity());

            //5.Ejecutar el query
            objPrepare.execute();

            //6.Obtener resultado
            ResultSet objResulset = objPrepare.getGeneratedKeys();
            while (objResulset.next()){
                objPlane.setId(objResulset.getInt(1));
            }

            //7.Cerrar el preparedStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Plane insertion was succesfull.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding plane" + e.getMessage());
        }

        //8. Cerrar la conexión
        ConfigDB.closeConnection();

        return objPlane;
    }

    @Override
    public boolean update(Object object) {

        Plane objPlane = (Plane) object;

        //1.Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //1.1 variable bandera para saber si se aactualizó
        boolean isUpdate = false;

        try {
            //2. Sentencia sql
            String sql = "UPDATE plane SET model = ?, capacity = ? WHERE id = ?;";

            //3. Prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. dar valor a los query parameters
            objPrepare.setString(1,objPlane.getModel());
            objPrepare.setInt(2,objPlane.getCapacity());
            objPrepare.setInt(3, objPlane.getId());

            //5. Ejecutar el query
            int rowAffected = objPrepare.executeUpdate();
            if (rowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }
            //6. Cerrar el preparedStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Plane insertion was succesfull.");

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
            String sql = "DELETE FROM plane WHERE id = ?;";

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

        List<Object> listPlane = new ArrayList<>();

        //1. abrir la conexión
        Connection objconnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM plane;";

            //3. prepared statement
            PreparedStatement objPrepare = (PreparedStatement) objconnection.prepareStatement(sql);

            //4. que devuelva un resultado => executeQuery
            ResultSet objResulset = objPrepare.executeQuery();

            //5. Obtener los resultados
            while (objResulset.next()){

                //Crear la instancia de plane
                Plane plane = new Plane();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                plane.setId(objResulset.getInt("id"));
                plane.setModel(objResulset.getString("model"));
                plane.setCapacity(objResulset.getInt("capacity"));

                //agregar a la lista la especialidad
                listPlane.add(plane);
            }
        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //cerrar la conexión
        ConfigDB.closeConnection();

        return listPlane;
    }

    @Override
    public Object findById(int id) {

        Plane objPlane = null;

        //1. abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql= "SELECT * FROM plane WHERE id = ?;";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos valor al ?
            objPrepare.setInt(1,id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente => obtener resultados
            while (objResult.next()){

                //Crear la instancia de plane
                objPlane = new Plane();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objPlane.setId(objResult.getInt("id"));
                objPlane.setModel(objResult.getString("model"));
                objPlane.setCapacity(objResult.getInt("capacity"));
            }

        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }
        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return objPlane;
    }


    public Object findByModel(String model) {
        List<Object> listPlane = new ArrayList<>();

        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2.Sentencia sql
            String sql = "SELECT * FROM plane WHERE model LIKE ?;";

            //3. prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4.Damos valor a query parameter
            objPrepare.setString(1, "%" + model + "%");

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. verificar registro siguiente
            while (objResult.next()) {

                //Crear la instancia Plane
                Plane objPlane = new Plane();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objPlane.setId(objResult.getInt("id"));
                objPlane.setModel(objResult.getString("model"));
                objPlane.setCapacity(objResult.getInt("capacity"));

                //Agregar a la lista final

                listPlane.add(objPlane);
            }


        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return listPlane;
    }
}
