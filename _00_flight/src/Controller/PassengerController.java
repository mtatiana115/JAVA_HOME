package Controller;

import Entity.Passenger;
import Model.PassengerModel;
import javax.swing.*;
import java.util.List;

public class PassengerController {

    private PassengerModel objPassengerModel;

    //
    public PassengerController (){
        //Crear una instancia del model
        this.objPassengerModel = new PassengerModel();
    }


    //Método para listar todas los pasajeros
    public String getAll(List<Object> listObject){
        String list = "Passengers' list \n";

        //Iteramos sobre la lista que devuelve el método find All
        for (Object obj: listObject){

            // Convertimos o castemos el objeto tipo Object a un pasajero
            Passenger objPassenger = (Passenger) obj;

            //Concatenamos la información
            list += objPassenger.toString() + "\n";
        }
        return list;
    }

    public void getAll(){
        //aplico sobre escritura, donde los métodos que se llaman igual son diferenciados por los parámetros que le paso
        String list = this.getAll(this.objPassengerModel.findAll());

        //Mostramos la lista
        JOptionPane.showMessageDialog(null, list);
    }

    //Método para insertar
    public void create(){
        Passenger objPassenger = new Passenger();
        String name = JOptionPane.showInputDialog("Insert the name: ");
        String lastname = JOptionPane.showInputDialog(null, "Insert the lastname: ");
        String idDocument = JOptionPane.showInputDialog(null, "Insert the id document: ");

        objPassenger.setName(name);
        objPassenger.setLastname(lastname);
        objPassenger.setIdDocument(idDocument);

        objPassenger = (Passenger) this.objPassengerModel.insert(objPassenger);

        JOptionPane.showMessageDialog(null, objPassenger.toString());
    }

    //Método para actualizar
    public void update(){
        //Listamos
        String passengerList = this.getAll(objPassengerModel.findAll());

        //pedir el id
        int isUpdate = Integer.parseInt(JOptionPane.showInputDialog(passengerList + "\n Enter the ID of the passenger to edit"));

        //verificar el id
        Passenger objPassenger = (Passenger) this.objPassengerModel.findById(isUpdate);

        if (objPassenger == null){
            JOptionPane.showMessageDialog(null, "Passenger was not found");
        } else {
            String name = JOptionPane.showInputDialog(null, "Type a new name", objPassenger.getName());
            String lastname = JOptionPane.showInputDialog(null, "Type a new lastname", objPassenger.getLastname());
            String idDocument = JOptionPane.showInputDialog(null, "Tyoe a new id document: ", objPassenger.getIdDocument());

            //actualizar el objeto
            objPassenger.setName(name);
            objPassenger.setLastname(lastname);
            objPassenger.setIdDocument(idDocument);

            //Guardar y actualizar en la DB con update
            this.objPassengerModel.update(objPassenger);
        }
    }

    public void filterByName (){
        String name = JOptionPane.showInputDialog("Type the name to search: ");

        List<Object> filteredPassenger = (List<Object>) this.objPassengerModel.findByName(name);

        if (filteredPassenger.isEmpty()){
            JOptionPane.showMessageDialog(null, "No passenger found with the name " + name);
        } else {
            String listFilterPassenger = "Passenger's List \n";

            for (Object obj : filteredPassenger){

                //Castear
                Passenger objPassenger = (Passenger) obj;
                listFilterPassenger += objPassenger.toString() + "\n";
            }

            JOptionPane.showMessageDialog(null, listFilterPassenger);
        }
    }

    //Método para eliminar
    public void delete(){
        String listSpeciltyString = "Passengers' list \n";

        for (Object obj: this.objPassengerModel.findAll()){

            //Castear
            Passenger objPassenger = (Passenger) obj;
            listSpeciltyString += objPassenger.toString() + "\n";
        }

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listSpeciltyString + "Enter the id of the Passenger to delete"));

        //Implementar el método findById
        Passenger objPassenger = (Passenger) this.objPassengerModel.findById(idDelete);

        if (objPassenger == null){
            JOptionPane.showMessageDialog(null, "Passenger not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, " Are you sure to want to delete the passenger: \n" + objPassenger.toString());

            //eliminar despues de confirmación
            if (confirm == 0){
                this.objPassengerModel.delete(idDelete);
            }
        }
    }



    public void PassengerMenu (){
        String option1 = "";

        do {
            option1 = JOptionPane.showInputDialog("""
                PASSENGER MENU:
                1. Passenger list
                2. Add a new passenger
                3. Update a passenger
                4. Find a passenger by name
                5. Delete a passenger
                6. Return
                
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
                    this.filterByName();
                    break;

                case "5":
                    this.delete();
                    break;
            }
        }while (!option1.equals("6"));
    }
}

