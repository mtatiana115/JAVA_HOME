package Controller;

import Entity.Booking;
import Model.BookingModel;
import javax.swing.*;
import java.sql.Date;
import java.util.List;

public class BookingController {

    private BookingModel objBookingModel;

    //
    public BookingController (){
        //Crear una instancia del model
        this.objBookingModel = new BookingModel();
    }




    //Método para listar todas las especialidades
    public String getAll(List<Object> listObject){
        String list = "Bookings' list \n";

        //Iteramos sobre la lista que devuelve el método find All
        for (Object obj: listObject){

            // Convertimos o castemos el objeto tipo Object a una especialidad
            Booking objBooking = (Booking) obj;

            objBooking = (Booking) this.objBookingModel.findByDetails(objBooking.getId());

            //Concatenamos la información
            list += objBooking.toString() + "\n";
        }
        return list;
    }

    public void getAll(){
        //aplico sobre escritura, donde los métodos que se llaman igual son diferenciados por los parámetros que le paso
        String list = this.getAll(this.objBookingModel.findAll());

        //Mostramos la lista
        JOptionPane.showMessageDialog(null, list);
    }

    //Método para insertar
    public void create(){
        Booking objBooking = new Booking();
        Date bookingDate = Date.valueOf(JOptionPane.showInputDialog(null,"Insert the booking date (yyyy-mm-dd): "));
        String seat = JOptionPane.showInputDialog(null, "Insert the booking seat (A-F / 1-25) : ");

        int idFlight = Integer.parseInt(JOptionPane.showInputDialog(null, "\n Insert the flight id: "));

        int idPassenger = Integer.parseInt(JOptionPane.showInputDialog(null, "\n Insert the passenger id: "));


        objBooking.setIdFlight(idFlight);
        objBooking.setIdPassenger(idPassenger);
        objBooking.setBookingDate(bookingDate);
        objBooking.setSeat(seat);

        objBooking = (Booking) this.objBookingModel.insert(objBooking);

        if (objBooking == null){
            JOptionPane.showMessageDialog(null,"it was not possible to add a new booking");
        }else {
            objBooking = (Booking) this.objBookingModel.findByDetails(objBooking.getId());
            JOptionPane.showMessageDialog(null, objBooking.toString());
        }

    }

    //Método para actualizar
    public void update(){
        //Listamos
        String bookingList = this.getAll(objBookingModel.findAll());

        //pedir el id
        int isUpdate = Integer.parseInt(JOptionPane.showInputDialog(bookingList + "\n Enter the ID of the booking to edit"));

        //verificar el id
        Booking objBooking = (Booking) this.objBookingModel.findById(isUpdate);

        if (objBooking == null){
            JOptionPane.showMessageDialog(null, "Booking was not found");
        } else {

            int idFlight = Integer.parseInt(JOptionPane.showInputDialog(null, "\n Insert the flight id: ", objBooking.getIdFlight()));
            int idPassenger = Integer.parseInt(JOptionPane.showInputDialog(null, "\n Insert the passenger's id: ", objBooking.getIdPassenger()));

            //Solicitar la demás información
            Date bookingDate = Date.valueOf(JOptionPane.showInputDialog(null, "Insert a new date (yyyy-mm-dd): ", objBooking.getBookingDate()));
            String seat = JOptionPane.showInputDialog(null, "Insert an booking seat", objBooking.getSeat());

            //actualizar el objeto
            objBooking.setIdFlight(idFlight);
            objBooking.setIdPassenger(idPassenger);
            objBooking.setBookingDate(bookingDate);
            objBooking.setSeat(seat);

            //Guardar y actualizar en la DB con update
            if (this.objBookingModel.update(objBooking)){
                JOptionPane.showMessageDialog(null,"successful updated");
            } else {
                JOptionPane.showMessageDialog(null, "it was not possible to update");
            }
        }
    }

    public void detailsBooking(){
        String list = "Booking details \n";

        BookingModel objBookingModel = new BookingModel();

        int id = Integer.parseInt(JOptionPane.showInputDialog(null, this.getAll(objBookingModel.findAll()) + "Enter the id of the booking to find: "));

        Booking objBooking = (Booking) objBookingModel.findByDetails(id);
        // Verificamos si la reserva no es nula antes de procesarla
        if (objBooking != null) {
            // Concatenamos la información de la reserva encontrada
            list += objBooking.toString() + "\n";
        } else {
            // Si no se encuentra la reserva, mostramos un mensaje
            list += "No booking found with ID: " + id + "\n";
        }

        // Mostramos los detalles de la reserva
        JOptionPane.showMessageDialog(null, list);
    }

    public void findBookingByDate(){
        String list = "Booking details \n";

        BookingModel objBookingModel = new BookingModel();

        Date bookingDate = Date.valueOf(JOptionPane.showInputDialog(null,"Insert the date of the booking to find (yy-mm-dd): "));

        Booking objBooking = (Booking) objBookingModel.findByDate(bookingDate);
        // Verificamos si la reserva no es nula antes de procesarla
        if (objBooking != null) {
            // Concatenamos la información de la reserva encontrada
            list += objBooking.toString() + "\n";
        } else {
            // Si no se encuentra la reserva, mostramos un mensaje
            list += "No booking found with date: " + bookingDate + "\n";
        }

        // Mostramos los detalles de la reserva
        JOptionPane.showMessageDialog(null, list);
    }


    //Método para eliminar
    public void delete(){
        String listSpeciltyString = "Bookings list \n";

        for (Object obj: this.objBookingModel.findAll()){

            //Castear
            Booking objBooking = (Booking) obj;
            listSpeciltyString += objBooking.toString() + "\n";
        }

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listSpeciltyString + "Enter the id of the Booking to delete"));

        //Implementar el método findById
        Booking objBooking = (Booking) this.objBookingModel.findById(idDelete);

        if (objBooking == null){
            JOptionPane.showMessageDialog(null, "Booking not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, " Are you sure to want to delete the booking: \n" + objBooking.toString());

            //eliminar despues de confirmación
            if (confirm == 0){
                this.objBookingModel.delete(idDelete);
            }
        }
    }



    public void BookingMenu (){
        String option1 = "";

        do {
            option1 = JOptionPane.showInputDialog("""
                BOOKING MENU:
                1. Booking's list             
                2. Add a new booking
                3. Update a booking
                4. Booking details
                5. Delete a booking
                6. Find booking by date
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
                    this.detailsBooking();
                    break;

                case "5":
                    this.delete();
                    break;

                case "6":
                    this.findBookingByDate();
                    break;
            }
        }while (!option1.equals("7"));
    }
}

