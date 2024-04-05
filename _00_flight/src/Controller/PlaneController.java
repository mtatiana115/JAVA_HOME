package Controller;

import Entity.Plane;
import Model.PlaneModel;
import javax.swing.*;
import java.util.List;

public class PlaneController {

    private PlaneModel objPlaneModel;

    //
    public PlaneController (){
        //Crear una instancia del model
        this.objPlaneModel = new PlaneModel();
    }



    //Método para listar todos los aviones
    public String getAll(List<Object> listObject){
        String list = "Planes list \n";

        //Iteramos sobre la lista que devuelve el método find All
        for (Object obj: listObject){

            // Convertimos o castemos el objeto tipo Object a un avión
            Plane objPlane = (Plane) obj;

            //Concatenamos la información
            list += objPlane.toString() + "\n";
        }
        return list;
    }

    public void getAll(){
        //aplico sobre escritura, donde los métodos que se llaman igual son diferenciados por los parámetros que le paso
        String list = this.getAll(this.objPlaneModel.findAll());

        //Mostramos la lista
        JOptionPane.showMessageDialog(null, list);
    }

    //Método para insertar
    public void create(){
        PlaneController objPlaneController = new PlaneController();
        Plane objPlane = new Plane();
        String model = JOptionPane.showInputDialog("Insert model: ");
        int capacity = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert a capacity: "));

        objPlane.setModel(model);
        objPlane.setCapacity(capacity);

        objPlane = (Plane) this.objPlaneModel.insert(objPlane);

        JOptionPane.showMessageDialog(null, objPlane.toString());
    }

    //Método para actualizar
    public void update(){
        //Listamos
        String planeList = this.getAll(objPlaneModel.findAll());

        //pedir el id
        int isUpdate = Integer.parseInt(JOptionPane.showInputDialog(planeList + "\n Enter the ID of the plane to edit"));

        //verificar el id
        Plane objPlane = (Plane) this.objPlaneModel.findById(isUpdate);

        if (objPlane == null){
            JOptionPane.showMessageDialog(null, "Plane was not found");
        } else {
            String model = JOptionPane.showInputDialog(null, "Type a new model", objPlane.getModel());
            int capacity = Integer.parseInt(JOptionPane.showInputDialog(null, "Type a new capacity", objPlane.getCapacity()));


            //actualizar el objeto
            objPlane.setModel(model);
            objPlane.setCapacity(capacity);

            //Guardar y actualizar en la DB con update
            this.objPlaneModel.update(objPlane);
        }
    }

    public void filterByModel (){
        String model = JOptionPane.showInputDialog("Type the model to search: ");

        List<Object> filteredPlane = (List<Object>) this.objPlaneModel.findByModel(model);

        if (filteredPlane.isEmpty()){
            JOptionPane.showMessageDialog(null, "No plane found with the model " + model);
        } else {
            String listFilterPlane = "Plane's List \n";

            for (Object obj : filteredPlane){

                //Castear
                Plane objPlane = (Plane) obj;
                listFilterPlane += objPlane.toString() + "\n";
            }

            JOptionPane.showMessageDialog(null, listFilterPlane);
        }
    }

    //Método para eliminar
    public void delete(){
        String listSpeciltyString = "Specialties' list \n";

        for (Object obj: this.objPlaneModel.findAll()){

            //Castear
            Plane objPlane = (Plane) obj;
            listSpeciltyString += objPlane.toString() + "\n";
        }

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listSpeciltyString + "Enter the id of the Plane to delete"));

        //Implementar el método findById
        Plane objPlane = (Plane) this.objPlaneModel.findById(idDelete);

        if (objPlane == null){
            JOptionPane.showMessageDialog(null, "Plane not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, " Are you sure to want to delete the plane: \n" + objPlane.toString());

            //eliminar despues de confirmación
            if (confirm == 0){
                this.objPlaneModel.delete(idDelete);
            }
        }
    }



    public void PlaneMenu (){
        String option1 = "";

        do {
            option1 = JOptionPane.showInputDialog("""
                PLANE MENU:
                1. Plane list
                2. Add a new plane
                3. Update a plane
                4. Find a plane by model
                5. Delete a plane
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
                    this.filterByModel();
                    break;

                case "5":
                    this.delete();
                    break;
            }
        }while (!option1.equals("6"));
    }
}
