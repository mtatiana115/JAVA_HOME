package Controller;

import Entity.Flight;
import Model.FlightModel;
import Model.PlaneModel;

import javax.swing.*;
import java.sql.Date;
import java.util.List;

public class FlightController {

    private FlightModel objFlightModel;

    //
    public FlightController (){
        //Crear una instancia del model
        this.objFlightModel = new FlightModel();
    }


    //Método para listar todos los vuelo
    public String getAll(List<Object> listObject){
        String list = "Flights list \n";

        //Iteramos sobre la lista que devuelve el método find All
        for (Object obj: listObject){

            // Convertimos o castemos el objeto tipo Object a un vuelo
            Flight objFlight = (Flight) obj;

            objFlight = (Flight) this.objFlightModel.findDetailsFlightById(objFlight.getIdPlane());

            //Concatenamos la información
            list += objFlight.toString() + "\n";
        }
        return list;
    }

    public void getAll(){
        //aplico sobre escritura, donde los métodos que se llaman igual son diferenciados por los parámetros que le paso
        String list = this.getAll(this.objFlightModel.findAll());

        //Mostramos la lista
        JOptionPane.showMessageDialog(null, list);
    }

    //Método para insertar
    public void create(){
        Flight objFlight = new Flight();
        String destination = JOptionPane.showInputDialog(null,"Insert destination: ");
        Date departureDate = Date.valueOf(JOptionPane.showInputDialog(null, "Insert a departure date (yyyy-mm-dd): "));
        String departureHour = JOptionPane.showInputDialog(null, "Insert a departure hour (hh:mm:ss): ");

        PlaneController objPlaneController = new PlaneController();
        PlaneModel objPlaneModel = new PlaneModel();

        int idPlane = Integer.parseInt(JOptionPane.showInputDialog(null, objPlaneController.getAll(objPlaneModel.findAll()) + "\n Type the id plane"));


        objFlight.setDestination(destination);
        objFlight.setDepartureDate(departureDate);
        objFlight.setDepartureHour(departureHour);
        objFlight.setIdPlane(idPlane);

        objFlight = (Flight) this.objFlightModel.insert(objFlight);

        objFlight = (Flight) this.objFlightModel.findDetailsFlightById(objFlight.getId());

        JOptionPane.showMessageDialog(null, objFlight.toString());
    }

    //Método para actualizar
    public void update(){
        //Listamos
        String flightList = this.getAll(objFlightModel.findAll());

        //pedir el id
        int isUpdate = Integer.parseInt(JOptionPane.showInputDialog(flightList + "\n Enter the ID of the flight to edit"));

        //verificar el id
        Flight objFlight = (Flight) this.objFlightModel.findById(isUpdate);

        if (objFlight == null){
            JOptionPane.showMessageDialog(null, "Flight was not found");
        } else {
            String destination = JOptionPane.showInputDialog(null, "Type a new destination", objFlight.getDestination());
            Date departureDate = Date.valueOf(JOptionPane.showInputDialog(null, "Type a new departure date", objFlight.getDepartureDate()));
            String departureHour = JOptionPane.showInputDialog(null, "Type a new departure hour", objFlight.getDepartureHour());

            PlaneController objPlaneController = new PlaneController();
            PlaneModel objPlaneModel = new PlaneModel();

            int idPlane = Integer.parseInt(JOptionPane.showInputDialog(null, objPlaneController.getAll(objPlaneModel.findAll()) + "\n Type the id plane"));


            //actualizar el objeto
            objFlight.setDestination(destination);
            objFlight.setDepartureDate(departureDate);
            objFlight.setDepartureHour(departureHour);
            objFlight.setIdPlane(idPlane);

            //Guardar y actualizar en la DB con update
            this.objFlightModel.update(objFlight);
            JOptionPane.showInputDialog(null,"successful updated");
        }
    }

    public String detailsFlightsAndSpecialties(){
        String list = "Flights and plane' list \n";

        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter the id of the plane to find: "));

        //Iteramos sobre la lista que devuelve el método find All
        for (Object obj: objFlightModel.findDetailsFlightAll(id)){

            // Convertimos o castemos el objeto tipo Object a un vuelo
            Flight objFlight = (Flight) obj;

            //Concatenamos la información
            list += objFlight.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, list);
        return list;
    }

    public void filterByDestination (){
        String destination = JOptionPane.showInputDialog("Type the destination to search: ");

        List<Object> filteredFlight = this.objFlightModel.findByDestination(destination);

        if (filteredFlight.isEmpty()){
            JOptionPane.showMessageDialog(null, "No flight found with the destination " + destination);
        } else {
            String listFilterFlight = "Flight's List \n";

            for (Object obj : filteredFlight){

                //Castear
                Flight objFlight = (Flight) obj;
                listFilterFlight += objFlight.toString() + "\n";
            }

            JOptionPane.showMessageDialog(null, listFilterFlight);
        }
    }

    //Método para eliminar
    public void delete(){
        String listSpeciltyString = "Flights' list \n";

        for (Object obj: this.objFlightModel.findAll()){

            //Castear
            Flight objFlight = (Flight) obj;
            listSpeciltyString += objFlight.toString() + "\n";
        }

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listSpeciltyString + "Enter the id of the Flight to delete"));

        //Implementar el método findById
        Flight objFlight = (Flight) this.objFlightModel.findById(idDelete);

        if (objFlight == null){
            JOptionPane.showMessageDialog(null, "Flight not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, " Are you sure to want to delete the flight: \n" + objFlight.toString());

            //eliminar despues de confirmación
            if (confirm == 0){
                this.objFlightModel.delete(idDelete);
            }
        }
    }



    public void FlightMenu (){
        String option1 = "";

        do {
            option1 = JOptionPane.showInputDialog("""
                FLIGHT MENU:
                1. Flight list             
                2. Add a new flight
                3. Update a flight
                4. Find a flight by destination
                5. Delete a flight
                6. Flights' list by plane
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
                    this.filterByDestination();
                    break;

                case "5":
                    this.delete();
                    break;

                case "6":
                    this.detailsFlightsAndSpecialties();
                    break;
            }
        }while (!option1.equals("7"));
    }
}
