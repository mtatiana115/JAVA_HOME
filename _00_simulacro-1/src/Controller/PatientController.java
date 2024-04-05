package Controller;

import Entity.Patient;
import Model.PatientModel;
import javax.swing.*;
import java.sql.Date;
import java.util.List;

public class PatientController {

    private PatientModel objPatientModel;

    //
    public PatientController (){
        //Crear una instancia del model
        this.objPatientModel = new PatientModel();
    }




    //Método para listar todas las especialidades
    public String getAll(List<Object> listObject){
        String list = "Patients' list \n";

        //Iteramos sobre la lista que devuelve el método find All
        for (Object obj: listObject){

            // Convertimos o castemos el objeto tipo Object a una especialidad
            Patient objPatient = (Patient) obj;

            //Concatenamos la información
            list += objPatient.toString() + "\n";
        }
        return list;
    }

    public void getAll(){
        //aplico sobre escritura, donde los métodos que se llaman igual son diferenciados por los parámetros que le paso
        String list = this.getAll(this.objPatientModel.findAll());

        //Mostramos la lista
        JOptionPane.showMessageDialog(null, list);
    }

    //Método para insertar
    public void create(){
        PatientController objPatientController = new PatientController();
        Patient objPatient = new Patient();
        String name = JOptionPane.showInputDialog("Insert the name: ");
        String lastName = JOptionPane.showInputDialog(null, "Insert the lastname: ");
        Date birthdate = Date.valueOf(JOptionPane.showInputDialog(null,"Insert the birthdate (yyy-mm-dd): "));
        String idDocument = JOptionPane.showInputDialog(null, "Insert the id document: ");

        objPatient.setName(name);
        objPatient.setLastName(lastName);
        objPatient.setBirthdate(birthdate);
        objPatient.setIdDocument(idDocument);

        objPatient = (Patient) this.objPatientModel.insert(objPatient);

        JOptionPane.showMessageDialog(null, objPatient.toString());
    }

    //Método para actualizar
    public void update(){
        //Listamos
        String patientList = this.getAll(objPatientModel.findAll());

        //pedir el id
        int isUpdate = Integer.parseInt(JOptionPane.showInputDialog(patientList + "\n Enter the ID of the patient to edit"));

        //verificar el id
        Patient objPatient = (Patient) this.objPatientModel.findById(isUpdate);

        if (objPatient == null){
            JOptionPane.showMessageDialog(null, "Patient was not found");
        } else {
            String name = JOptionPane.showInputDialog(null, "Type a new name", objPatient.getName());
            String lastName = JOptionPane.showInputDialog(null, "Type a new lastname", objPatient.getLastName());
            Date birthdate = Date.valueOf(JOptionPane.showInputDialog(null, "Type a new birthdate (yyyy-mm-dd): ", objPatient.getBirthdate()));
            String idDocument = JOptionPane.showInputDialog(null, "Tyoe a new id document: ", objPatient.getIdDocument());

            //actualizar el objeto
            objPatient.setName(name);
            objPatient.setLastName(lastName);
            objPatient.setBirthdate(birthdate);
            objPatient.setIdDocument(idDocument);

            //Guardar y actualizar en la DB con update
            this.objPatientModel.update(objPatient);
        }
    }

    public void filterByName (){
        String name = JOptionPane.showInputDialog("Type the name to search: ");

        List<Object> filteredPatient = this.objPatientModel.findByName(name);

        if (filteredPatient.isEmpty()){
            JOptionPane.showMessageDialog(null, "No patient found with the name " + name);
        } else {
            String listFilterPatient = "Patient's List \n";

            for (Object obj : filteredPatient){

                //Castear
                Patient objPatient = (Patient) obj;
                listFilterPatient += objPatient.toString() + "\n";
            }

            JOptionPane.showMessageDialog(null, listFilterPatient);
        }
    }

    //Método para eliminar
    public void delete(){
        String listSpeciltyString = "Patients' list \n";

        for (Object obj: this.objPatientModel.findAll()){

            //Castear
            Patient objPatient = (Patient) obj;
            listSpeciltyString += objPatient.toString() + "\n";
        }

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listSpeciltyString + "Enter the id of the Patient to delete"));

        //Implementar el método findById
        Patient objPatient = (Patient) this.objPatientModel.findById(idDelete);

        if (objPatient == null){
            JOptionPane.showMessageDialog(null, "Patient not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, " Are you sure to want to delete the patient: \n" + objPatient.toString());

            //eliminar despues de confirmación
            if (confirm == 0){
                this.objPatientModel.delete(idDelete);
            }
        }
    }



    public void PatientMenu (){
        String option1 = "";

        do {
            option1 = JOptionPane.showInputDialog("""
                PATIENT'S MENU:
                1. Patient's list
                2. Add a new patient
                3. Update a patient
                4. Find a patient by name
                5. Delete a patient
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

