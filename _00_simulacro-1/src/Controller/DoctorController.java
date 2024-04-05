package Controller;

import Entity.Doctor;
import Entity.Specialty;
import Model.DoctorModel;
import Model.SpecialtyModel;

import javax.swing.*;
import java.util.List;

public class DoctorController {

    private DoctorModel objDoctorModel;

    //
    public DoctorController (){
        //Crear una instancia del model
        this.objDoctorModel = new DoctorModel();
    }




    //Método para listar todas las especialidades
    public String getAll(List<Object> listObject){
        String list = "Doctors' list \n";

        //Iteramos sobre la lista que devuelve el método find All
        for (Object obj: listObject){

            // Convertimos o castemos el objeto tipo Object a una especialidad
            Doctor objDoctor = (Doctor) obj;

            objDoctor = (Doctor) this.objDoctorModel.findDetailsDoctorById(objDoctor.getId());

            //Concatenamos la información
            list += objDoctor.toString() + "\n";
        }
        return list;
    }

    public void getAll(){
        //aplico sobre escritura, donde los métodos que se llaman igual son diferenciados por los parámetros que le paso
        String list = this.getAll(this.objDoctorModel.findAll());

        //Mostramos la lista
        JOptionPane.showMessageDialog(null, list);
    }

    //Método para insertar
    public void create(){
        Doctor objDoctor = new Doctor();
        String name = JOptionPane.showInputDialog(null,"Insert name: ");
        String lastName = JOptionPane.showInputDialog(null, "Insert a lastname: ");

        SpecialtyController objSpecialtyController = new SpecialtyController();
        SpecialtyModel objSpecialtyModel = new SpecialtyModel();

        int idSpecialty = Integer.parseInt(JOptionPane.showInputDialog(null, objSpecialtyController.getAll(objSpecialtyModel.findAll()) + "\n Type the id specialty"));


        objDoctor.setName(name);
        objDoctor.setLastName(lastName);
        objDoctor.setIdSpecialty(idSpecialty);

        objDoctor = (Doctor) this.objDoctorModel.insert(objDoctor);

        objDoctor = (Doctor) this.objDoctorModel.findDetailsDoctorById(objDoctor.getId());

        JOptionPane.showMessageDialog(null, objDoctor.toString());
    }

    //Método para actualizar
    public void update(){
        //Listamos
        String doctorList = this.getAll(objDoctorModel.findAll());

        //pedir el id
        int isUpdate = Integer.parseInt(JOptionPane.showInputDialog(doctorList + "\n Enter the ID of the doctor to edit"));

        //verificar el id
        Doctor objDoctor = (Doctor) this.objDoctorModel.findById(isUpdate);

        if (objDoctor == null){
            JOptionPane.showMessageDialog(null, "Doctor was not found");
        } else {
            String name = JOptionPane.showInputDialog(null, "Type a new name", objDoctor.getName());
            String lastName = JOptionPane.showInputDialog(null, "Type a new lastname", objDoctor.getLastName());

            SpecialtyController objSpecialtyController = new SpecialtyController();
            SpecialtyModel objSpecialtyModel = new SpecialtyModel();

            int idSpecialty = Integer.parseInt(JOptionPane.showInputDialog(null, objSpecialtyController.getAll(objSpecialtyModel.findAll()) + "\n Type the id specialty"));


            //actualizar el objeto
            objDoctor.setName(name);
            objDoctor.setLastName(lastName);
            objDoctor.setIdSpecialty(idSpecialty);

            //Guardar y actualizar en la DB con update
            this.objDoctorModel.update(objDoctor);
        }
    }

    public String detailsDoctorsAndSpecialties(){
        String list = "Doctors and specialty' list \n";

        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter the id of the specialty to find: "));

        //Iteramos sobre la lista que devuelve el método find All
        for (Object obj: objDoctorModel.findDetailsDoctorAll(id)){

            // Convertimos o castemos el objeto tipo Object a una especialidad
            Doctor objDoctor = (Doctor) obj;

            //Concatenamos la información
            list += objDoctor.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, list);
        return list;
    }

    public void filterByName (){
        String name = JOptionPane.showInputDialog("Type the name to search: ");

        List<Object> filteredDoctor = this.objDoctorModel.findByName(name);

        if (filteredDoctor.isEmpty()){
            JOptionPane.showMessageDialog(null, "No doctor found with the name " + name);
        } else {
            String listFilterDoctor = "Doctor's List \n";

            for (Object obj : filteredDoctor){

                //Castear
                Doctor objDoctor = (Doctor) obj;
                listFilterDoctor += objDoctor.toString() + "\n";
            }

            JOptionPane.showMessageDialog(null, listFilterDoctor);
        }
    }

    //Método para eliminar
    public void delete(){
        String listSpeciltyString = "Doctors' list \n";

        for (Object obj: this.objDoctorModel.findAll()){

            //Castear
            Doctor objDoctor = (Doctor) obj;
            listSpeciltyString += objDoctor.toString() + "\n";
        }

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listSpeciltyString + "Enter the id of the Doctor to delete"));

        //Implementar el método findById
        Doctor objDoctor = (Doctor) this.objDoctorModel.findById(idDelete);

        if (objDoctor == null){
            JOptionPane.showMessageDialog(null, "Doctor not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, " Are you sure to want to delete the doctor: \n" + objDoctor.toString());

            //eliminar despues de confirmación
            if (confirm == 0){
                this.objDoctorModel.delete(idDelete);
            }
        }
    }



    public void DoctorMenu (){
        String option1 = "";

        do {
            option1 = JOptionPane.showInputDialog("""
                DOCTOR'S MENU:
                1. Doctor's list             
                2. Add a new doctor
                3. Update a doctor
                4. Find a doctor by name
                5. Delete a doctor
                6. Doctors' list by specialty
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
                    this.filterByName();
                    break;

                case "5":
                    this.delete();
                    break;

                case "6":
                    this.detailsDoctorsAndSpecialties();
                    break;
            }
        }while (!option1.equals("7"));
    }
}
