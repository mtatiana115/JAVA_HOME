package Controller;

import Entity.Appointment;
import Entity.Specialty;
import Model.AppointmentModel;
import Model.DoctorModel;
import Model.PatientModel;
import Model.SpecialtyModel;

import javax.swing.*;
import java.sql.Date;
import java.util.List;

public class AppointmentController {

    private AppointmentModel objAppointmentModel;

    //
    public AppointmentController (){
        //Crear una instancia del model
        this.objAppointmentModel = new AppointmentModel();
    }




    //Método para listar todas las especialidades
    public String getAll(List<Object> listObject){
        String list = "Appointments' list \n";

        //Iteramos sobre la lista que devuelve el método find All
        for (Object obj: listObject){

            // Convertimos o castemos el objeto tipo Object a una especialidad
            Appointment objAppointment = (Appointment) obj;

            objAppointment = (Appointment) this.objAppointmentModel.findByDetails(objAppointment.getId());

            //Concatenamos la información
            list += objAppointment.toString() + "\n";
        }
        return list;
    }

    public void getAll(){
        //aplico sobre escritura, donde los métodos que se llaman igual son diferenciados por los parámetros que le paso
        String list = this.getAll(this.objAppointmentModel.findAll());

        //Mostramos la lista
        JOptionPane.showMessageDialog(null, list);
    }

    //Método para insertar
    public void create(){
        Appointment objAppointment = new Appointment();
        Date appointmentDate = Date.valueOf(JOptionPane.showInputDialog(null,"Insert the appointment date (yyy-mm-dd): "));
        String appointmentHour = JOptionPane.showInputDialog(null, "Insert the appointment hour (hh:mm:ss)");
        String reason = JOptionPane.showInputDialog(null, "Insert the appointment reason: ");

        int idDoctor = Integer.parseInt(JOptionPane.showInputDialog(null, "\n Insert the doctor id: "));

        int idPatient = Integer.parseInt(JOptionPane.showInputDialog(null, "\n Insert the patient id: "));


        objAppointment.setIdDoctor(idDoctor);
        objAppointment.setIdPatient(idPatient);
        objAppointment.setAppointmentDate(appointmentDate);
        objAppointment.setAppointmentHour(appointmentHour);
        objAppointment.setReason(reason);

        objAppointment = (Appointment) this.objAppointmentModel.insert(objAppointment);

        objAppointment = (Appointment) this.objAppointmentModel.findByDetails(objAppointment.getId());

        JOptionPane.showMessageDialog(null, objAppointment.toString());
    }

    //Método para actualizar
    public void update(){
        //Listamos
        String appointmentList = this.getAll(objAppointmentModel.findAll());

        //pedir el id
        int isUpdate = Integer.parseInt(JOptionPane.showInputDialog(appointmentList + "\n Enter the ID of the appointment to edit"));

        //verificar el id
        Appointment objAppointment = (Appointment) this.objAppointmentModel.findById(isUpdate);

        if (objAppointment == null){
            JOptionPane.showMessageDialog(null, "Appointment was not found");
        } else {

            int idDoctor = Integer.parseInt(JOptionPane.showInputDialog(null, "\n Insert the doctor's id: ", objAppointment.getIdDoctor()));
            int idPatient = Integer.parseInt(JOptionPane.showInputDialog(null, "\n Insert the patient's id: ", objAppointment.getIdPatient()));

            //Solicitar la demás información
            Date appointmentDate = Date.valueOf(JOptionPane.showInputDialog(null, "Insert a new date (yyyy-mm-dd): ", objAppointment.getAppointmentDate()));
            String appointmentHour = JOptionPane.showInputDialog(null, "Insert a new hour (hh:mm:ss): ", objAppointment.getAppointmentHour());
            String reason = JOptionPane.showInputDialog(null, "Insert an appointment reason", objAppointment.getReason());

            //actualizar el objeto
            objAppointment.setIdDoctor(idDoctor);
            objAppointment.setIdPatient(idPatient);
            objAppointment.setAppointmentDate(appointmentDate);
            objAppointment.setAppointmentHour(appointmentHour);
            objAppointment.setReason(reason);

            //Guardar y actualizar en la DB con update
            this.objAppointmentModel.update(objAppointment);
        }
    }

    public void detailsAppointment(){
        String list = "Appointment's details \n";

        AppointmentModel objAppointmentModel = new AppointmentModel();

        int id = Integer.parseInt(JOptionPane.showInputDialog(null, this.getAll(objAppointmentModel.findAll()) + "Enter the id of the appointment to find: "));

        Appointment objAppointment = (Appointment) objAppointmentModel.findByDetails(id);
        // Verificamos si la cita no es nula antes de procesarla
        if (objAppointment != null) {
            // Concatenamos la información de la cita encontrada
            list += objAppointment.toString() + "\n";
        } else {
            // Si no se encuentra la cita, mostramos un mensaje
            list += "No appointment found with ID: " + id + "\n";
        }

        // Mostramos los detalles de la cita
        JOptionPane.showMessageDialog(null, list);
    }

    public void findAppointmentByDate(){
        String list = "Appointment details \n";

        AppointmentModel objAppointmentModel = new AppointmentModel();

        Date appointmentDate = Date.valueOf(JOptionPane.showInputDialog(null,"Insert the date of the appointment to find (yy-mm-dd): "));

        Appointment objAppointment = (Appointment) objAppointmentModel.findByDate(appointmentDate);
        // Verificamos si la cita no es nula antes de procesarla
        if (objAppointment != null) {
            // Concatenamos la información de la cita encontrada
            list += objAppointment.toString() + "\n";
        } else {
            // Si no se encuentra la cita, mostramos un mensaje
            list += "No appointment found with date: " + appointmentDate + "\n";
        }

        // Mostramos los detalles de la cita
        JOptionPane.showMessageDialog(null, list);
    }


    //Método para eliminar
    public void delete(){
        String listSpeciltyString = "Appointments' list \n";

        for (Object obj: this.objAppointmentModel.findAll()){

            //Castear
            Appointment objAppointment = (Appointment) obj;
            listSpeciltyString += objAppointment.toString() + "\n";
        }

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listSpeciltyString + "Enter the id of the Appointment to delete"));

        //Implementar el método findById
        Appointment objAppointment = (Appointment) this.objAppointmentModel.findById(idDelete);

        if (objAppointment == null){
            JOptionPane.showMessageDialog(null, "Appointment not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, " Are you sure to want to delete the appointment: \n" + objAppointment.toString());

            //eliminar despues de confirmación
            if (confirm == 0){
                this.objAppointmentModel.delete(idDelete);
            }
        }
    }



    public void AppointmentMenu (){
        String option1 = "";

        do {
            option1 = JOptionPane.showInputDialog("""
                SPECIALTY'S MENU:
                1. Appointment's list             
                2. Add a new appointment
                3. Update a appointment
                4. Appointment details
                5. Delete a appointment
                6. Find appointment by date
                7. Return
                
                Choose an option:
                """);
            switch (option1){
                case "1":
                    this.getAll();
                    break;

                case "2":
                    this.create();
                    break;

                case "3":
                    this.update();
                    break;

                case "4":
                    this.detailsAppointment();
                    break;

                case "5":
                    this.delete();
                    break;

                case "6":
                    this.findAppointmentByDate();
                    break;
            }
        }while (!option1.equals("7"));
    }
}

