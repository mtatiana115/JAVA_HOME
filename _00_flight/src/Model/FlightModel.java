package Model;

import Configuration.ConfigDB;
import Entity.Flight;
import Entity.Plane;
import Repository.CRUD;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightModel implements CRUD {
    @Override
    public Object insert(Object object) {

        //castear objeto
        Flight objFlight = (Flight) object;

        //1. Abrir la conexión
        Connection objconnection = ConfigDB.openConnection();

        try {
            //2. sentencia sql
            String sql = "INSERT INTO flight (destination, departureDate, departureHour, idPlane) VALUES (?,?,?,?);";

            //3. Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objconnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. Asignar los ?
            objPrepare.setString(1, objFlight.getDestination());
            objPrepare.setDate(2, objFlight.getDepartureDate());
            objPrepare.setString(3, objFlight.getDepartureHour());
            objPrepare.setInt(4, objFlight.getIdPlane());

            //5.Ejecutar el query
            objPrepare.execute();

            //6.Obtener resultado
            ResultSet objResulset = objPrepare.getGeneratedKeys();
            while (objResulset.next()){
                objFlight.setId(objResulset.getInt(1));
            }

            //7.Cerrar el preparedStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Flight insertion was succesfull.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding flight" + e.getMessage());
        }

        //8. Cerrar la conexión
        ConfigDB.closeConnection();

        return objFlight;
    }

    @Override
    public boolean update(Object object) {

        Flight objFlight = (Flight) object;

        //1.Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //1.1 variable bandera para saber si se aactualizó
        boolean isUpdate = false;

        try {
            //2. Sentencia sql
            String sql = "UPDATE flight SET destination = ?, departureDate = ?, departureHour = ?, idPlane = ? WHERE id = ?;";

            //3. Prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. dar valor a los query parameters
            objPrepare.setString(1,objFlight.getDestination());
            objPrepare.setDate(2,objFlight.getDepartureDate());
            objPrepare.setString(3, objFlight.getDepartureHour());
            objPrepare.setInt(4, objFlight.getIdPlane());
            objPrepare.setInt(5, objFlight.getId());

            //5. Ejecutar el query
            int rowAffected = objPrepare.executeUpdate();
            if (rowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }
            //6. Cerrar el preparedStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Flight insertion was succesfull.");

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
            String sql = "DELETE FROM flight WHERE id = ?;";

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
        List<Object> listFlight = new ArrayList<>();

        //1. abrir la conexión
        Connection objconnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM flight;";

            //3. prepared statement
            PreparedStatement objPrepare = (PreparedStatement) objconnection.prepareStatement(sql);

            //4. que devuelva un resultado => executeQuery
            ResultSet objResulset = objPrepare.executeQuery();

            //5. Obtener los resultados
            while (objResulset.next()){

                //Crear la instancia de flight
                Flight flight = new Flight();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                flight.setId(objResulset.getInt("id"));
                flight.setDestination(objResulset.getString("destination"));
                flight.setDepartureDate(objResulset.getDate("departureDate"));
                flight.setDepartureHour(objResulset.getString("departureHour"));
                flight.setIdPlane(objResulset.getInt("idPlane"));

                //agregar a la lista plane
                listFlight.add(flight);
            }
        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //cerrar la conexión
        ConfigDB.closeConnection();

        return listFlight;

    }

    @Override
    public Object findById(int id) {
        Flight objFlight = null;

        //1. abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql= "SELECT * FROM flight WHERE id = ?;";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos valor al ?
            objPrepare.setInt(1,id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente => obtener resultados
            while (objResult.next()){

                //Crear la instancia de flight
                objFlight = new Flight();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objFlight.setId(objResult.getInt("id"));
                objFlight.setDestination(objResult.getString("destination"));
                objFlight.setDepartureDate(objResult.getDate("departureDate"));
                objFlight.setDepartureHour(objResult.getString("departureHour"));
                objFlight.setIdPlane(objResult.getInt("idPlane"));
            }

        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }
        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return objFlight;
    }

    public List<Object> findByDestination(String destination) {

        List <Object> listFlight = new ArrayList<>();

        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2.Sentencia sql
            String sql = "SELECT * FROM flight WHERE destination LIKE ?;";

            //3. prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4.Damos valor a query parameter
            objPrepare.setString(1, "%" + destination + "%");

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. verificar registro siguiente
            while (objResult.next()){

                //Crear la instancia Flight
                Flight objFlight = new Flight();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objFlight.setId(objResult.getInt("id"));
                objFlight.setDestination(objResult.getString("destination"));
                objFlight.setDepartureDate(objResult.getDate("departureDate"));
                objFlight.setDepartureHour(objResult.getString("departureHour"));
                objFlight.setIdPlane(objResult.getInt("idPlane"));

                //Agregar a la lista final

                listFlight.add(objFlight);
            }


        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return listFlight;

    }

    public List<Object> findDetailsFlightAll(int id) {
        List<Object> listFlightsByPlane = new ArrayList<>();

        //1. abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM flight INNER JOIN plane ON flight.idPlane = plane.id WHERE flight.idPlane = ?;";


            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos valor al ?
            objPrepare.setInt(1, id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente => obtener resultados
            while (objResult.next()) {

                Flight objFlight = null;

                //Crear la instancia de flight  y de la segunda tabla
                objFlight = new Flight();
                Plane objPlane = new Plane();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objFlight.setId(objResult.getInt("id"));
                objFlight.setDestination(objResult.getString("destination"));
                objFlight.setDepartureDate(objResult.getDate("departureDate"));
                objFlight.setDepartureHour(objResult.getString("departureHour"));
                objFlight.setIdPlane(objResult.getInt("idPlane"));

                //lleno mi segundo objeto
                objPlane.setId(objResult.getInt("id"));
                objPlane.setModel(objResult.getString("model"));
                objPlane.setCapacity(objResult.getInt("capacity"));

                //incluir el objeto 2 dentro del 1 (plane dentro de flight)
                objFlight.setPlane(objPlane);

                listFlightsByPlane.add(objFlight);
                //añadir al controlador
                //objFlight.getPlane().getName();
            }


        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }
        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return listFlightsByPlane;
    }

    public Object findDetailsFlightById(int id) {
        Flight objFlight = new Flight();

        //1. abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM flight INNER JOIN plane ON flight.idPlane = plane.id WHERE flight.id = ?;";


            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos valor al ?
            objPrepare.setInt(1, id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente => obtener resultados
            while (objResult.next()) {

                //Crear la instancia de flight  y de la segunda tabla
                Plane objPlane = new Plane();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objFlight.setId(objResult.getInt("id"));
                objFlight.setDestination(objResult.getString("destination"));
                objFlight.setDepartureDate(objResult.getDate("departureDate"));
                objFlight.setDepartureHour(objResult.getString("departureHour"));
                objFlight.setIdPlane(objResult.getInt("idPlane"));

                //lleno mi segundo objeto
                objPlane.setId(objResult.getInt("id"));
                objPlane.setModel(objResult.getString("model"));
                objPlane.setCapacity(objResult.getInt("capacity"));

                //incluir el objeto 2 dentro del 1 (plane dentro de flight)
                objFlight.setPlane(objPlane);

                //añadir al controlador
                //objFlight.getPlane().getName();
            }


        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }
        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return objFlight;
    }
}
