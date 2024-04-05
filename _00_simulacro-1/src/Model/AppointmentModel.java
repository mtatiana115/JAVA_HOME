package Model;
import Configuration.ConfigDB;
import Entity.Appointment;
import Entity.Doctor;
import Entity.Patient;
import Repository.CRUD;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentModel implements CRUD {
    @Override
    public Object insert(Object object) {

        //castear objeto
        Appointment objAppointment = (Appointment) object;

        //1. Abrir la conexión
        Connection objconnection = ConfigDB.openConnection();

        try {
            //2. sentencia sql
            String sql = "INSERT INTO appointment (idPatient, idDoctor, appointmentDate, appointmentHour, reason) VALUES (?,?,?,?,?);";

            //3. Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objconnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. Asignar los ?
            objPrepare.setInt(1, objAppointment.getIdPatient());
            objPrepare.setInt(2, objAppointment.getIdDoctor());
            objPrepare.setDate(3, objAppointment.getAppointmentDate());
            objPrepare.setString(4, objAppointment.getAppointmentHour());
            objPrepare.setString(5, objAppointment.getReason());


            //5.Ejecutar el query
            objPrepare.execute();

            //6.Obtener resultado
            ResultSet objResulset = objPrepare.getGeneratedKeys();
            while (objResulset.next()) {
                objAppointment.setId(objResulset.getInt(1));
            }

            //7.Cerrar el preparedStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Appointment insertion was succesfull.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding appointment" + e.getMessage());
        }

        //8. Cerrar la conexión
        ConfigDB.closeConnection();

        return objAppointment;
    }

    @Override
    public boolean update(Object object) {

        Appointment objAppointment = (Appointment) object;

        //1.Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //1.1 variable bandera para saber si se aactualizó
        boolean isUpdate = false;

        try {
            //2. Sentencia sql
            String sql = "UPDATE appointment SET idPatient = ?, idDoctor = ?, appointmentDate = ?, appointmentHour = ?, reason = ? WHERE id = ?;";

            //3. Prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //4. dar valor a los query parameters
            objPrepare.setInt(1, objAppointment.getIdPatient());
            objPrepare.setInt(2, objAppointment.getIdDoctor());
            objPrepare.setDate(3, objAppointment.getAppointmentDate());
            objPrepare.setString(4, objAppointment.getAppointmentHour());
            objPrepare.setString(5, objAppointment.getReason());
            objPrepare.setInt(6, objAppointment.getId());

            //5. Ejecutar el query
            int rowAffected = objPrepare.executeUpdate();
            if (rowAffected > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }
            //6. Cerrar el preparedStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Appointment insertion was succesfull.");

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
            String sql = "DELETE FROM appointment WHERE id = ?;";

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
        List<Object> listAppointment = new ArrayList<>();

        //1. abrir la conexión
        Connection objconnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM appointment;";

            //3. prepared statement
            PreparedStatement objPrepare = (PreparedStatement) objconnection.prepareStatement(sql);

            //4. que devuelva un resultado => executeQuery
            ResultSet objResulset = objPrepare.executeQuery();

            //5. Obtener los resultados
            while (objResulset.next()) {

                //Crear la instancia de appointment
                Appointment appointment = new Appointment();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                appointment.setId(objResulset.getInt("id"));
                appointment.setIdPatient(objResulset.getInt("idPatient"));
                appointment.setIdDoctor(objResulset.getInt("idDoctor"));
                appointment.setAppointmentDate(objResulset.getDate("appointmentDate"));
                appointment.setAppointmentHour(objResulset.getString("appointmentHour"));
                appointment.setReason(objResulset.getString("reason"));


                //agregar a la lista la especialidad
                listAppointment.add(appointment);
            }
        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //cerrar la conexión
        ConfigDB.closeConnection();

        return listAppointment;
    }

    @Override
    public Object findById(int id) {

        Appointment objAppointment = null;

        //1. abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM appointment WHERE Id = ?;";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos valor al ?
            objPrepare.setInt(1, id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente => obtener resultados
            while (objResult.next()) {

                //Crear la instancia de appointment
                objAppointment = new Appointment();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objAppointment.setId(objResult.getInt("Id"));
                objAppointment.setIdPatient(objResult.getInt("idPatient"));
                objAppointment.setIdDoctor(objResult.getInt("idDoctor"));
                objAppointment.setAppointmentDate(objResult.getDate("appointmentDate"));
                objAppointment.setAppointmentHour(objResult.getString("appointmentHour"));
                objAppointment.setReason(objResult.getString("reason"));
            }

        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }
        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return objAppointment;
    }

    public Object findByDetails(int id) {
        Appointment objAppointment = null;

        //1. abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM appointment INNER JOIN patient ON appointment.idPatient = patient.id \n" +
                    "INNER JOIN doctor ON appointment.idDoctor = doctor.doctorId\n" +
                    "WHERE appointment.id = ?;";


            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos valor al ?
            objPrepare.setInt(1, id);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente => obtener resultados
            while (objResult.next()) {


                //Crear la instancia de appointment  y de la segunda tabla
                Patient objPatient = new Patient();
                Doctor objDoctor = new Doctor();
                objAppointment = new Appointment();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objAppointment.setId(objResult.getInt("appointment.id"));
                objAppointment.setIdPatient(objResult.getInt("idPatient"));
                objAppointment.setIdDoctor(objResult.getInt("idDoctor"));
                objAppointment.setAppointmentDate(objResult.getDate("appointmentDate"));
                objAppointment.setAppointmentHour(objResult.getString("appointmentHour"));
                objAppointment.setReason(objResult.getString("reason"));

                //lleno mi segundo objeto
                objDoctor.setId(objResult.getInt("doctorId"));
                objDoctor.setName(objResult.getString("doctorName"));
                objDoctor.setLastName(objResult.getString("doctorLastName"));
                objDoctor.setIdSpecialty(objResult.getInt("idSpecialty"));

                //lleno mi tercer objeto
                objPatient.setId(objResult.getInt("patient.id"));
                objPatient.setName(objResult.getString("patient.name"));
                objPatient.setLastName(objResult.getString("patient.lastName"));
                objPatient.setBirthdate(objResult.getDate("birthdate"));
                objPatient.setIdDocument(objResult.getString("IdDocument"));


                //incluir el objeto 2 dentro del 1 (doctor dentro de appointment)
                objAppointment.setDoctor(objDoctor);

                //incluir el objeto 3 dentro del 1 (patient dentro de appointment)
                objAppointment.setPatient(objPatient);
                ;
                //añadir al controlador
                //objDoctor.getSpecialty().getName();
            }


        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }
        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return objAppointment;
    }

    public Object findByDate(Date date) {
        Appointment objAppointment = null;

        //1. abrir conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM appointment WHERE appointmentDate = ?;";

            //3. Preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. Damos valor al ?
            objPrepare.setDate(1, date);

            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente => obtener resultados
            while (objResult.next()) {

                objAppointment = new Appointment();

                //Llenar el objeto con lo que devuelve la base de datos (ResulSet)
                objAppointment.setId(objResult.getInt("id"));
                objAppointment.setIdPatient(objResult.getInt("idPatient"));
                objAppointment.setIdDoctor(objResult.getInt("idDoctor"));
                objAppointment.setAppointmentDate(objResult.getDate("appointmentDate"));
                objAppointment.setAppointmentHour(objResult.getString("appointmentHour"));
                objAppointment.setReason(objResult.getString("reason"));
            }
        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }
        return objAppointment;
    }
}