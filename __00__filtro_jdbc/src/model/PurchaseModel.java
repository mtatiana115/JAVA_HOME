package model;

import database.CRUD;
import database.ConfigDB;
import entity.Customer;
import entity.Purchase;
import entity.Product;
import entity.Store;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseModel implements CRUD {
    @Override
    public Object insert(Object object) {
        //castear objeto para convertir el objeto que llega a la clase que necesito en este caso purchase
        Purchase objPurchase = (Purchase) object;

        //1. Abrir la conexión
        Connection objconnection = ConfigDB.openConnection();

        try {
            //2. sentencia sql
            String sql = "INSERT INTO purchase (amount,idCustomer,idProduct) VALUES (?,?,?);";

            //3. Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objconnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. Asignar los ?
            objPrepare.setInt(1, objPurchase.getAmount());
            objPrepare.setDouble(2, objPurchase.getIdCustomer());
            objPrepare.setInt(3, objPurchase.getIdProduct());

            //5.Ejecutar el query
            objPrepare.execute();

            //6.Obtener resultado
            ResultSet objResulset = objPrepare.getGeneratedKeys();

            while (objResulset.next()){

                objPurchase.setId(objResulset.getInt(1));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding purchase" + e.getMessage());
        }

        //8. Cerrar la conexión
        ConfigDB.closeConnection();

        return objPurchase;
    }


    @Override
    public boolean update(Object object) {
        Purchase objPurchase = (Purchase) object;

        //1.Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //1.1 variable bandera para saber si se actualizó
        boolean isUpdate = false;

        try {
            //2. Sentencia sql
            String sql = "UPDATE purchase SET purchaseDate = ?, amount = ?, idCustomer = ?, idProduct = ? WHERE id = ?;";

            //3. Prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. dar valor a los query parameters
            objPrepare.setString(1,objPurchase.getPurchaseDate());
            objPrepare.setInt(2,objPurchase.getAmount());
            objPrepare.setInt(3,objPurchase.getIdCustomer());
            objPrepare.setInt(4,objPurchase.getIdProduct());
            objPrepare.setInt(5, objPurchase.getId());

            //5. Ejecutar el query
            int rowAffected = objPrepare.executeUpdate();
            if (rowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }

        } catch (SQLException e) {
            System.out.println("Error in database " + e.getMessage());
        }

        // Cerrar la conexion
        ConfigDB.closeConnection();

        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        //Variable boolean bandera para saber el estado de la conexión
        boolean isDelete = false;

        Purchase objPurchase = (Purchase) obj;

        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "DELETE FROM purchase WHERE id = ?;";

            //3. Prepared statement
            PreparedStatement objPrepare = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. Dar valor a queryparameters
            objPrepare.setInt(1,objPurchase.getId());

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
        List<Object> listPurchase = new ArrayList<>();

        //1. abrir la conexión
        Connection objconnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM purchase\n" +
                    "INNER JOIN product ON product.id = purchase.idProduct\n" +
                    "INNER JOIN customer ON customer.id = purchase.idCustomer;";

            //3. prepared statement
            PreparedStatement objPrepare = objconnection.prepareStatement(sql);

            //4. que devuelva un resultado => executeQuery
            ResultSet objResulset = objPrepare.executeQuery();

            //5. Obtener los resultados
            while (objResulset.next()){

                //Crear la instancia de purchase
                Purchase purchase = new Purchase();
                Product product = new Product();
                Customer customer = new Customer();
                Store objStore = new Store();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                purchase.setId(objResulset.getInt("purchase.id"));
                purchase.setPurchaseDate(objResulset.getString("purchase.purchaseDate"));
                purchase.setAmount(objResulset.getInt("purchase.amount"));
                purchase.setIdCustomer(objResulset.getInt("purchase.idCustomer"));
                purchase.setIdProduct(objResulset.getInt("purchase.idProduct"));

                product.setId(objResulset.getInt("product.id"));
                product.setName(objResulset.getString("product.name"));
                product.setPrice(objResulset.getDouble("product.price"));
                product.setIdStore(objResulset.getInt("product.idStore"));
                product.setStock(objResulset.getInt("product.stock"));
                product.setStore(objStore);

                customer.setId(objResulset.getInt("customer.id"));
                customer.setName(objResulset.getString("customer.name"));
                customer.setLastname(objResulset.getString("customer.lastname"));
                customer.setEmail(objResulset.getString("customer.email"));

                purchase.setCustomer(customer);
                purchase.setProduct(product);
                //agregar a la lista
                listPurchase.add(purchase);
            }
        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //cerrar la conexión
        ConfigDB.closeConnection();

        return listPurchase;
    }

    @Override
    public Object findById(int id) {
        Purchase purchase = null;

        //1. abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql= "SELECT * FROM purchase WHERE id = ?;";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos valor al ?
            objPrepare.setInt(1,id);

            //5. Ejecutamos el query
            ResultSet objResultset = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente => obtener resultados
            while (objResultset.next()){

                //Crear la instancia de purchase
                purchase = new Purchase();

                purchase.setId(objResultset.getInt("purchase.id"));
                purchase.setPurchaseDate(objResultset.getString("purchase.purchaseDate"));
                purchase.setAmount(objResultset.getInt("purchase.amount"));
                purchase.setIdCustomer(objResultset.getInt("purchase.idCustomer"));
                purchase.setIdProduct(objResultset.getInt("purchase.idProduct"));

            }

        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }
        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return purchase;
    }

    public List<Object> findByProduct(String product) {
        List <Object> listPurchase = new ArrayList<>();

        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2.Sentencia sql
            String sql ="SELECT * FROM purchase \n" +
                    "INNER JOIN product ON product.id = purchase.idProduct \n" +
                    "WHERE product.name LIKE ?;";

            //3. prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4.Damos valor a query parameter
            objPrepare.setString(1, "%" + product + "%");

            //5. Ejecutamos el query
            ResultSet objResultset = objPrepare.executeQuery();

            //6. verificar registro siguiente
            while (objResultset.next()){

                //Crear la instancia Purchase
                Purchase purchase = new Purchase();

                Product objProduct = new Product();
                Store objStore = new Store();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                purchase.setId(objResultset.getInt("purchase.id"));
                purchase.setPurchaseDate(objResultset.getString("purchase.purchaseDate"));
                purchase.setAmount(objResultset.getInt("purchase.amount"));
                purchase.setIdCustomer(objResultset.getInt("purchase.idCustomer"));
                purchase.setIdProduct(objResultset.getInt("purchase.idProduct"));

                objProduct.setId(objResultset.getInt("product.id"));
                objProduct.setName(objResultset.getString("product.name"));
                objProduct.setPrice(objResultset.getDouble("product.price"));
                objProduct.setIdStore(objResultset.getInt("product.idStore"));
                objProduct.setStock(objResultset.getInt("product.stock"));
                objProduct.setStore(objStore);

                purchase.setProduct(objProduct);
                //agregar a la lista
                listPurchase.add(purchase);

            }


        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return listPurchase;
    }

    public List<Object> findByAmount(String amount) {
        List <Object> listPurchase = new ArrayList<>();

        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2.Sentencia sql
            String sql = "SELECT * FROM purchase WHERE amount LIKE ?;";

            //3. prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4.Damos valor a query parameter
            objPrepare.setString(1, "%" + amount + "%");

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. verificar registro siguiente
            while (objResult.next()){

                //Crear la instancia Purchase
                Purchase objPurchase = new Purchase();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objPurchase.setId(objResult.getInt("purchase.id"));
                objPurchase.setAmount(objResult.getInt("purchase.amount"));
                objPurchase.setIdCustomer(objResult.getInt("purchase.idCustomer"));
                objPurchase.setIdProduct(objResult.getInt("purchase.idProduct"));


                //agregar a la lista
                listPurchase.add(objPurchase);
            }

        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return listPurchase;
    }

    //public Object generateteBill(Object object){

        //castear objeto para convertir el objeto que llega a la clase que necesito en este caso purchase
        //Purchase objPurchase = (Purchase) object;

        //1. Abrir la conexión
        //Connection objconnection = ConfigDB.openConnection();

        //try {
            //2. sentencia sql
            //String sql = "SELECT * FROM purchase\n" +
                    //"INNER JOIN product ON product.id = purchase.idProduct\n" +
                    //"INNER JOIN customer ON customer.id = purchase.idCustomer;";

            //3. prepared statement
            //PreparedStatement objPrepare = objconnection.prepareStatement(sql);

            //4. que devuelva un resultado => executeQuery
            //ResultSet objResulset = objPrepare.executeQuery();

            //5. Obtener los resultados
            //while (objResulset.next()){

                //Crear la instancia de purchase
                //Purchase purchase = new Purchase();
                //Product product = new Product();
                //Customer customer = new Customer();
                //Store objStore = new Store();


}
