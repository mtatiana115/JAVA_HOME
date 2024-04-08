package model;

import database.ConfigDB;
import entity.Store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreModel {
    public List<Object> findAll() {
        List<Object> listStore = new ArrayList<>();

        //1. abrir la conexión
        Connection objconnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM store;";

            //3. prepared statement
            PreparedStatement objPrepare = objconnection.prepareStatement(sql);

            //4. que devuelva un resultado => executeQuery
            ResultSet objResulset = objPrepare.executeQuery();

            //5. Obtener los resultados
            while (objResulset.next()) {

                //Crear la instancia de store
                Store store = new Store();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                store.setId(objResulset.getInt("id"));
                store.setName(objResulset.getString("name"));
                store.setUbication(objResulset.getString("ubication"));

                //agregar a la lista store
                listStore.add(store);
            }
        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());

            //cerrar la conexión
            ConfigDB.closeConnection();
        }
        return listStore;
    }


}