package model;

import database.CRUD;
import database.ConfigDB;
import entity.Product;
import entity.Store;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductModel implements CRUD {
    @Override
    public Object insert(Object object) {
        //castear objeto para convertir el objeto que llega a la clase que necesito en este caso product
        Product objProduct = (Product) object;

        //1. Abrir la conexión
        Connection objconnection = ConfigDB.openConnection();

        try {
            //2. sentencia sql
            String sql = "INSERT INTO product (name,price,idStore,stock) VALUES (?,?,?,?);";

            //3. Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objconnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. Asignar los ?
            objPrepare.setString(1, objProduct.getName());
            objPrepare.setDouble(2, objProduct.getPrice());
            objPrepare.setInt(3, objProduct.getIdStore());
            objPrepare.setInt(4,objProduct.getStock());

            //5.Ejecutar el query
            objPrepare.execute();

            //6.Obtener resultado
            ResultSet objResulset = objPrepare.getGeneratedKeys();

            while (objResulset.next()){

                objProduct.setId(objResulset.getInt(1));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding product" + e.getMessage());
        }

        //8. Cerrar la conexión
        ConfigDB.closeConnection();

        return objProduct;
    }


    @Override
    public boolean update(Object object) {
        Product objProduct = (Product) object;

        //1.Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //1.1 variable bandera para saber si se actualizó
        boolean isUpdate = false;

        try {
            //2. Sentencia sql
            String sql = "UPDATE product SET name = ?, price = ?, idStore = ?, stock = ? WHERE id = ?;";

            //3. Prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. dar valor a los query parameters
            objPrepare.setString(1,objProduct.getName());
            objPrepare.setDouble(2,objProduct.getPrice());
            objPrepare.setInt(3,objProduct.getIdStore());
            objPrepare.setInt(4,objProduct.getStock());
            objPrepare.setInt(5, objProduct.getId());

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

        Product objProduct = (Product) obj;

        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "DELETE FROM product WHERE id = ?;";

            //3. Prepared statement
            PreparedStatement objPrepare = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. Dar valor a queryparameters
            objPrepare.setInt(1,objProduct.getId());

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
        List<Object> listProduct = new ArrayList<>();

        //1. abrir la conexión
        Connection objconnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM product\n" +
                    "INNER JOIN store ON store.id = product.idStore;";

            //3. prepared statement
            PreparedStatement objPrepare = objconnection.prepareStatement(sql);

            //4. que devuelva un resultado => executeQuery
            ResultSet objResulset = objPrepare.executeQuery();

            //5. Obtener los resultados
            while (objResulset.next()){

                //Crear la instancia de product
                Product product = new Product();
                Store store = new Store();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                product.setId(objResulset.getInt("product.id"));
                product.setName(objResulset.getString("product.name"));
                product.setPrice(objResulset.getDouble("product.price"));
                product.setIdStore(objResulset.getInt("product.idStore"));
                product.setStock(objResulset.getInt("product.stock"));

                store.setName(objResulset.getString("store.name"));
                store.setUbication(objResulset.getString("store.ubication"));

                product.setStore(store);
                //agregar a la lista
                listProduct.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //cerrar la conexión
        ConfigDB.closeConnection();

        return listProduct;
    }

    @Override
    public Object findById(int id) {
        Product objProduct = null;

        //1. abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql= "SELECT * FROM product WHERE id = ?;";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos valor al ?
            objPrepare.setInt(1,id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente => obtener resultados
            while (objResult.next()){

                //Crear la instancia de product
                objProduct = new Product();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objProduct.setId(objResult.getInt("id"));
                objProduct.setName(objResult.getString("name"));
                objProduct.setPrice(objResult.getDouble("price"));
                objProduct.setIdStore(objResult.getInt("idStore"));
                objProduct.setIdStore(objResult.getInt("stock"));

            }

        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }
        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return objProduct;
    }

    public List<Object> findByStore(String store) {
        List <Object> listProduct = new ArrayList<>();

        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2.Sentencia sql
            String sql ="SELECT * FROM product \n" +
                    "INNER JOIN store ON store.id = product.idStore \n" +
                    "WHERE store.name LIKE ?;";

            //3. prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4.Damos valor a query parameter
            objPrepare.setString(1, "%" + store + "%");

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. verificar registro siguiente
            while (objResult.next()){

                //Crear la instancia Product
                Product objProduct = new Product();
                Store objStore = new Store();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objProduct.setId(objResult.getInt("product.id"));
                objProduct.setName(objResult.getString("product.name"));
                objProduct.setPrice(objResult.getDouble("product.price"));
                objProduct.setIdStore(objResult.getInt("product.idStore"));
                objProduct.setStock(objResult.getInt("product.stock"));

                objStore.setName(objResult.getString("store.name"));
                objStore.setUbication(objResult.getString("store.ubication"));

                objProduct.setStore(objStore);
                //agregar a la lista
                listProduct.add(objProduct);


                //Agregar a la lista final

                listProduct.add(objProduct);
            }


        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return listProduct;
    }

    public List<Object> findByName(String name) {
        List <Object> listProduct = new ArrayList<>();

        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2.Sentencia sql
            String sql = "SELECT * FROM product WHERE name LIKE ?;";

            //3. prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4.Damos valor a query parameter
            objPrepare.setString(1, "%" + name + "%");

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. verificar registro siguiente
            while (objResult.next()){

                //Crear la instancia Product
                Product objProduct = new Product();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objProduct.setId(objResult.getInt("product.id"));
                objProduct.setName(objResult.getString("product.name"));
                objProduct.setPrice(objResult.getDouble("product.price"));
                objProduct.setIdStore(objResult.getInt("product.idStore"));
                objProduct.setStock(objResult.getInt("product.stock"));


                //agregar a la lista
                listProduct.add(objProduct);
            }

        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return listProduct;
    }


}
