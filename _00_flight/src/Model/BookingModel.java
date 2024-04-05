package Model;
import Configuration.ConfigDB;
import Entity.Booking;
import Entity.Flight;
import Entity.Passenger;
import Repository.CRUD;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingModel implements CRUD {
    @Override
    public Object insert(Object object) {

        //castear objeto
        Booking objBooking = (Booking) object;

        // Validar si el avión tiene capacidad suficiente para new booking
        if (!isFlightCapacityAvailable(objBooking.getIdFlight())) {
            System.out.println("The flight capacity has been exceeded. Booking cannot be added.");
            return null;
        }

        // Validar si el asiento está disponible para la new booking
        if (!isSeatAvailable(objBooking.getIdFlight(), objBooking.getSeat())) {
            System.out.println("The seat is already reserved. Booking cannot be added.");
            return null;
        }

        //1. Abrir la conexión
        Connection objconnection = ConfigDB.openConnection();

        try {
            //2. sentencia sql
            String sql = "INSERT INTO booking (idPassenger, idFlight, bookingDate, seat) VALUES (?,?,?,?);";

            //3. Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objconnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. Asignar los ?
            objPrepare.setInt(1, objBooking.getIdPassenger());
            objPrepare.setInt(2, objBooking.getIdFlight());
            objPrepare.setDate(3, objBooking.getBookingDate());
            objPrepare.setString(4, objBooking.getSeat());


            //5.Ejecutar el query
            objPrepare.execute();

            //6.Obtener resultado
            ResultSet objResulset = objPrepare.getGeneratedKeys();
            while (objResulset.next()) {
                objBooking.setId(objResulset.getInt(1));
            }

            //7.Cerrar el preparedStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Booking insertion was succesfull.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding booking" + e.getMessage());
        }

        //8. Cerrar la conexión
        ConfigDB.closeConnection();

        return objBooking;
    }

    @Override
    public boolean update(Object object) {

        Booking objBooking = (Booking) object;

        //1.1 variable bandera para saber si se aactualizó
        boolean isUpdate = false;

        // Validar si el avión tiene capacidad suficiente para new booking
        if (!isFlightCapacityAvailable(objBooking.getIdFlight())) {
            System.out.println("The flight capacity has been exceeded. Booking cannot be added.");
            return false;
        }

        // Validar si el asiento está disponible para la new booking
        if (!isSeatAvailable(objBooking.getIdFlight(), objBooking.getSeat())) {
            System.out.println("The seat is already reserved. Booking cannot be added.");
            return false;
        }

        //1.Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();



        try {
            //2. Sentencia sql
            String sql = "UPDATE booking SET idPassenger = ?, idFlight = ?, bookingDate = ?, seat = ? WHERE id = ?;";

            //3. Prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. dar valor a los query parameters
            objPrepare.setInt(1, objBooking.getIdPassenger());
            objPrepare.setInt(2, objBooking.getIdFlight());
            objPrepare.setDate(3, objBooking.getBookingDate());
            objPrepare.setString(4, objBooking.getSeat());
            objPrepare.setInt(5, objBooking.getId());

            //5. Ejecutar el query
            int rowAffected = objPrepare.executeUpdate();
            if (rowAffected > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }
            //6. Cerrar el preparedStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Booking insertion was succesfull.");

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
            String sql = "DELETE FROM booking WHERE id = ?;";

            //3. Prepared statement
            PreparedStatement objPrepare = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. Dar valor a queryparameters
            objPrepare.setInt(1, id);

            //5. ejecutar query
            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0) {
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
        List<Object> listBooking = new ArrayList<>();

        //1. abrir la conexión
        Connection objconnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM booking;";

            //3. prepared statement
            PreparedStatement objPrepare = (PreparedStatement) objconnection.prepareStatement(sql);

            //4. que devuelva un resultado => executeQuery
            ResultSet objResulset = objPrepare.executeQuery();

            //5. Obtener los resultados
            while (objResulset.next()) {

                //Crear la instancia de booking
                Booking booking = new Booking();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                booking.setId(objResulset.getInt("id"));
                booking.setIdPassenger(objResulset.getInt("idPassenger"));
                booking.setIdFlight(objResulset.getInt("idFlight"));
                booking.setBookingDate(objResulset.getDate("bookingDate"));
                booking.setSeat(objResulset.getString("seat"));


                //agregar a la lista booking
                listBooking.add(booking);
            }
        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //cerrar la conexión
        ConfigDB.closeConnection();

        return listBooking;
    }

    @Override
    public Object findById(int id) {

        Booking objBooking = null;

        //1. abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM booking WHERE Id = ?;";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos valor al ?
            objPrepare.setInt(1, id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente => obtener resultados
            while (objResult.next()) {

                //Crear la instancia de booking
                objBooking = new Booking();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objBooking.setId(objResult.getInt("Id"));
                objBooking.setIdPassenger(objResult.getInt("idPassenger"));
                objBooking.setIdFlight(objResult.getInt("idFlight"));
                objBooking.setBookingDate(objResult.getDate("bookingDate"));
                objBooking.setSeat(objResult.getString("seat"));
            }

        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }
        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return objBooking;
    }

    public Object findByDetails(int id) {
        Booking objBooking = null;

        //1. abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM booking INNER JOIN passenger ON booking.idPassenger = passenger.id \n" +
                    "INNER JOIN flight ON booking.idFlight = flight.id\n" +
                    "INNER JOIN  plane ON flight.idPlane = plane.id\n" +
                    "WHERE booking.id = ?;";


            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos valor al ?
            objPrepare.setInt(1, id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente => obtener resultados
            while (objResult.next()) {


                //Crear la instancia de booking  y de la segunda tabla
                Passenger objPassenger = new Passenger();
                Flight objFlight = new Flight();
                objBooking = new Booking();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objBooking.setId(objResult.getInt("booking.id"));
                objBooking.setIdPassenger(objResult.getInt("idPassenger"));
                objBooking.setIdFlight(objResult.getInt("idFlight"));
                objBooking.setBookingDate(objResult.getDate("bookingDate"));
                objBooking.setSeat(objResult.getString("seat"));

                //lleno mi segundo objeto
                objFlight.setId(objResult.getInt("flight.id"));
                objFlight.setDestination(objResult.getString("destination"));
                objFlight.setDepartureDate(objResult.getDate("departureDate"));
                objFlight.setDepartureHour(objResult.getString("departureHour"));
                objFlight.setIdPlane(objResult.getInt("idPlane"));

                //lleno mi tercer objeto
                objPassenger.setId(objResult.getInt("passenger.id"));
                objPassenger.setName(objResult.getString("passenger.name"));
                objPassenger.setLastname(objResult.getString("passenger.lastname"));
                objPassenger.setIdDocument(objResult.getString("IdDocument"));


                //incluir el objeto 2 dentro del 1 (flight dentro de booking)
                objBooking.setFlight(objFlight);

                //incluir el objeto 3 dentro del 1 (passenger dentro de booking)
                objBooking.setPassenger(objPassenger);

                //añadir al controlador
                //objFlight.getSpecialty().getName();
            }


        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }
        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return objBooking;
    }

    public Object findByDate(Date date) {
        Booking objBooking = null;

        //1. abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM booking WHERE bookingDate = ?;";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos valor al ?
            objPrepare.setDate(1, date);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente => obtener resultados
            while (objResult.next()) {

                objBooking = new Booking();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objBooking.setId(objResult.getInt("booking.id"));
                objBooking.setIdPassenger(objResult.getInt("idPassenger"));
                objBooking.setIdFlight(objResult.getInt("idFlight"));
                objBooking.setBookingDate(objResult.getDate("bookingDate"));
                objBooking.setSeat(objResult.getString("seat"));
            }
        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }
        return objBooking;
    }

    //Método para conocer la capacidad actual del vuelo
    private int getCurrentCapacity(int id) {
        int currentCapacity = 0;

        //1. abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT COUNT(seat) FROM booking\n" +
                    " INNER JOIN flight ON booking.idFlight = flight.id WHERE booking.idFlight = ?;";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos valor al ?
            objPrepare.setInt(1, id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente => obtener resultados
            while (objResult.next()) {
                currentCapacity = objResult.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }
        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return currentCapacity;
    }

    //Método para conocer la capacidad máxima del vuelo
    private int getMaxCapacity(int id) {
        int maxCapacity = 0;

        //1. abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT capacity FROM plane WHERE id = ?";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos valor al ?
            objPrepare.setInt(1, id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente => obtener resultados
            while (objResult.next()) {
                maxCapacity = objResult.getInt("capacity");
            }
        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }
        //7.Cerrar la conexión
        ConfigDB.closeConnection();
        return maxCapacity;
    }

    //Método para conocer si un asiento está disponible
    private boolean isSeatAvailable(int idFlight, String seat) {

        boolean isAvailable = true;

        //1. abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT COUNT(*) FROM booking WHERE idFlight = ? AND seat = ?";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos valor al ?
            objPrepare.setInt(1, idFlight);
            objPrepare.setString(2, seat);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente => obtener resultados
            while (objResult.next()) {

                // Obtener el recuento de reservaciones para el asiento dado
                int count = objResult.getInt(1);

                // Verificar si el asiento está disponible
                isAvailable = (count == 0);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return isAvailable;
    }

    //Método para conocer si hay capacidad disponible en un vuelo
    private boolean isFlightCapacityAvailable(int idFlight) {

        //variables capacidad actual y maxima capacidad
        int currentCapacity = getCurrentCapacity(idFlight);
        int maxCapacity = getMaxCapacity(idFlight);

        return currentCapacity < maxCapacity;
    }
}

