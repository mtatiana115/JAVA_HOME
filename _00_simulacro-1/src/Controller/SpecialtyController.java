package Controller;

import Entity.Specialty;
import Model.SpecialtyModel;
import javax.swing.*;
import java.util.List;

public class SpecialtyController {

    private SpecialtyModel objSpecialtyModel;

    //
    public SpecialtyController (){
        //Crear una instancia del model
        this.objSpecialtyModel = new SpecialtyModel();
    }




    //Método para listar todas las especialidades
    public String getAll(List<Object> listObject){
        String list = "Specialties' list \n";

        //Iteramos sobre la lista que devuelve el método find All
        for (Object obj: listObject){

            // Convertimos o castemos el objeto tipo Object a una especialidad
            Specialty objSpecialty = (Specialty) obj;

            //Concatenamos la información
            list += objSpecialty.toString() + "\n";
        }
        return list;
    }

    public void getAll(){
        //aplico sobre escritura, donde los métodos que se llaman igual son diferenciados por los parámetros que le paso
        String list = this.getAll(this.objSpecialtyModel.findAll());

        //Mostramos la lista
        JOptionPane.showMessageDialog(null, list);
    }

    //Método para insertar
    public void create(){
        SpecialtyController objSpecialtyController = new SpecialtyController();
        Specialty objSpecialty = new Specialty();
        String name = JOptionPane.showInputDialog("Insert name: ");
        String description = JOptionPane.showInputDialog(null, "Insert a description: ");

        objSpecialty.setName(name);
        objSpecialty.setDescription(description);

        objSpecialty = (Specialty) this.objSpecialtyModel.insert(objSpecialty);

        JOptionPane.showMessageDialog(null, objSpecialty.toString());
    }

    //Método para actualizar
    public void update(){
        //Listamos
        String specialtyList = this.getAll(objSpecialtyModel.findAll());

        //pedir el id
        int isUpdate = Integer.parseInt(JOptionPane.showInputDialog(specialtyList + "\n Enter the ID of the specialty to edit"));

        //verificar el id
        Specialty objSpecialty = (Specialty) this.objSpecialtyModel.findById(isUpdate);

        if (objSpecialty == null){
            JOptionPane.showMessageDialog(null, "Specialty was not found");
        } else {
            String name = JOptionPane.showInputDialog(null, "Type a new name", objSpecialty.getName());
            String description = JOptionPane.showInputDialog(null, "Type a new description", objSpecialty.getDescription());


            //actualizar el objeto
            objSpecialty.setName(name);
            objSpecialty.setDescription(description);

            //Guardar y actualizar en la DB con update
            this.objSpecialtyModel.update(objSpecialty);
        }
    }

    public void filterByName (){
        String name = JOptionPane.showInputDialog("Type the name to search: ");

        List<Object> filteredSpecialty = this.objSpecialtyModel.findByName(name);

        if (filteredSpecialty.isEmpty()){
            JOptionPane.showMessageDialog(null, "No specialty found with the name " + name);
        } else {
            String listFilterSpecialty = "Specialty's List \n";

            for (Object obj : filteredSpecialty){

                //Castear
                Specialty objSpecialty = (Specialty) obj;
                listFilterSpecialty += objSpecialty.toString() + "\n";
            }

            JOptionPane.showMessageDialog(null, listFilterSpecialty);
        }
    }

    //Método para eliminar
    public void delete(){
        String listSpeciltyString = "Specialties' list \n";

        for (Object obj: this.objSpecialtyModel.findAll()){

            //Castear
            Specialty objSpecialty = (Specialty) obj;
            listSpeciltyString += objSpecialty.toString() + "\n";
        }

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listSpeciltyString + "Enter the id of the Specialty to delete"));

        //Implementar el método findById
        Specialty objSpecialty = (Specialty) this.objSpecialtyModel.findById(idDelete);

        if (objSpecialty == null){
            JOptionPane.showMessageDialog(null, "Specialty not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, " Are you sure to want to delete the specialty: \n" + objSpecialty.toString());

            //eliminar despues de confirmación
            if (confirm == 0){
                this.objSpecialtyModel.delete(idDelete);
            }
        }
    }



    public void SpecialtyMenu (){
        String option1 = "";

        do {
        option1 = JOptionPane.showInputDialog("""
                SPECIALTY'S MENU:
                1. Specialty's list
                2. Add a new specialty
                3. Update a specialty
                4. Find a specialty by name
                5. Delete a specialty
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
