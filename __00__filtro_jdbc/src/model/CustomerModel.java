package model;

import database.CRUD;
import database.ConfigDB;
import entity.Customer;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel implements CRUD {
    @Override
    public Object insert(Object object) {
        //castear objeto para convertir el objeto que llega a la clase que necesito en este caso customer
        Customer objCustomer = (Customer) object;

        //1. Abrir la conexión
        Connection objconnection = ConfigDB.openConnection();

        try {
            //2. sentencia sql
            String sql = "INSERT INTO customer (name,lastname,email) VALUES (?,?,?);";

            //3. Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objconnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. Asignar los ?
            objPrepare.setString(1, objCustomer.getName());
            objPrepare.setString(2, objCustomer.getLastname());
            objPrepare.setString(3, objCustomer.getEmail());

            //5.Ejecutar el query
            objPrepare.execute();

            //6.Obtener resultado
            ResultSet objResulset = objPrepare.getGeneratedKeys();
            while (objResulset.next()){
                objCustomer.setId(objResulset.getInt(1));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding customer" + e.getMessage());
        }

        //8. Cerrar la conexión
        ConfigDB.closeConnection();

        return objCustomer;
    }


    @Override
    public boolean update(Object object) {
        Customer objCustomer = (Customer) object;

        //1.Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //1.1 variable bandera para saber si se actualizó
        boolean isUpdate = false;

        try {
            //2. Sentencia sql
            String sql = "UPDATE customer SET name = ?, lastname = ?, email = ? WHERE id = ?;";

            //3. Prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. dar valor a los query parameters
            objPrepare.setString(1,objCustomer.getName());
            objPrepare.setString(2,objCustomer.getLastname());
            objPrepare.setString(3,objCustomer.getEmail());
            objPrepare.setInt(4, objCustomer.getId());

            //5. Ejecutar el query
            int rowAffected = objPrepare.executeUpdate();
            if (rowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }
            //6. Cerrar el preparedStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Customer insertion was succesfull.");

        } catch (SQLException e) {
            System.out.println("Error in database " + e.getMessage());
        }

        //7. Cerrar la conexion
        ConfigDB.closeConnection();

        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        //Variable boolean bandera para saber el estado de la conexión
        boolean isDelete = false;

        Customer objCustomer = (Customer) obj;

        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "DELETE FROM customer WHERE id = ?;";

            //3. Prepared statement
            PreparedStatement objPrepare = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. Dar valor a queryparameters
            objPrepare.setInt(1,objCustomer.getId());

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
        List<Object> listCustomer = new ArrayList<>();

        //1. abrir la conexión
        Connection objconnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM customer;";

            //3. prepared statement
            PreparedStatement objPrepare = objconnection.prepareStatement(sql);

            //4. que devuelva un resultado => executeQuery
            ResultSet objResulset = objPrepare.executeQuery();

            //5. Obtener los resultados
            while (objResulset.next()){

                //Crear la instancia de customer
                Customer customer = new Customer();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                customer.setId(objResulset.getInt("id"));
                customer.setName(objResulset.getString("name"));
                customer.setLastname(objResulset.getString("lastname"));
                customer.setEmail(objResulset.getString("email"));

                //agregar a la lista customer
                listCustomer.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //cerrar la conexión
        ConfigDB.closeConnection();

        return listCustomer;
    }

    @Override
    public Object findById(int id) {
        Customer objCustomer = null;

        //1. abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql= "SELECT * FROM customer WHERE id = ?;";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos valor al ?
            objPrepare.setInt(1,id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente => obtener resultados
            while (objResult.next()){

                //Crear la instancia de customer
                objCustomer = new Customer();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objCustomer.setId(objResult.getInt("id"));
                objCustomer.setName(objResult.getString("name"));
                objCustomer.setLastname(objResult.getString("lastname"));
                objCustomer.setEmail(objResult.getString("email"));

            }

        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }
        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return objCustomer;
    }

    public List<Object> findByName(String name) {
        List <Object> listCustomer = new ArrayList<>();

        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2.Sentencia sql
            String sql = "SELECT * FROM customer WHERE name LIKE ?;";

            //3. prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4.Damos valor a query parameter
            objPrepare.setString(1, "%" + name + "%");

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. verificar registro siguiente
            while (objResult.next()){

                //Crear la instancia Customer
                Customer objCustomer = new Customer();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objCustomer.setId(objResult.getInt("id"));
                objCustomer.setName(objResult.getString("name"));
                objCustomer.setLastname(objResult.getString("lastname"));
                objCustomer.setEmail(objResult.getString("email"));


                //Agregar a la lista final

                listCustomer.add(objCustomer);
            }


        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return listCustomer;
    }


}
