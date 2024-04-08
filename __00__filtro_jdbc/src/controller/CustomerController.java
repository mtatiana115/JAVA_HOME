package controller;

import entity.Customer;
import model.CustomerModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class CustomerController {

    private CustomerModel objCustomerModel;

    //
    public CustomerController (){
        //Crear una instancia del model
        this.objCustomerModel = new CustomerModel();
    }




    //Método para listar todos los customers
    public static String getAll(List<Object> listObject){
        String list = "Customers list \n";

        //Iteramos sobre la lista que devuelve el método find All
        for (Object obj: listObject){

            // Convertimos o castemos el objeto tipo Object a una especialidad
            Customer objCustomer = (Customer) obj;

            //Concatenamos la información
            list += objCustomer.toString() + "\n";
        }

        return list;
    }

    public void getAll(){
        //aplico sobre escritura, donde los métodos que se llaman igual son diferenciados por los parámetros que le paso
        String list = this.getAll(this.instanceModel().findAll());

        //Mostramos la lista
        JOptionPane.showMessageDialog(null, list);
    }

    //Método para insertar
    public void create(){
        String name = JOptionPane.showInputDialog(null, "Insert a name: ");
        String lastname = JOptionPane.showInputDialog(null, "Insert a lastname: ");
        String email = JOptionPane.showInputDialog(null, "Insert an email: ");

        instanceModel().insert(new Customer(name, lastname, email));
        JOptionPane.showMessageDialog(null,"Customer has been added");

    }

    //Método para instanciar el modelo
    public static CustomerModel instanceModel(){
        return new CustomerModel();
    }

    //Método para actualizar
    public void update(){
        //crear un utilidad para cambiar de una nueva lista a array

        Object[] options = Utils.listToArray(instanceModel().findAll());

        Customer objSelected = (Customer) JOptionPane.showInputDialog(
                null,
                "Choose a customer to uptade: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        objSelected.setName(JOptionPane.showInputDialog(null, "Type a new name: ", objSelected.getName()));
        objSelected.setLastname(JOptionPane.showInputDialog(null, "Type a new lastname: ", objSelected.getLastname()));
        objSelected.setEmail(JOptionPane.showInputDialog(null,"Type a new email: ", objSelected.getEmail()));

        instanceModel().update(objSelected);

    }

    public void filterByName (){
        String name = JOptionPane.showInputDialog("Type the name to search: ");

        List<Object> filteredCustomer = this.objCustomerModel.findByName(name);

        if (filteredCustomer.isEmpty()){
            JOptionPane.showMessageDialog(null, "No customer found with the name " + name);
        } else {
            String listFilterCustomer = "Customer List \n";

            for (Object obj : filteredCustomer){

                //Castear
                Customer objCustomer = (Customer) obj;
                listFilterCustomer += objCustomer.toString() + "\n";
            }

            JOptionPane.showMessageDialog(null, listFilterCustomer);
        }
    }

    //Método para eliminar
    public static void delete(){
        //crear un utilidad para cambiar de una nueva lista a array

        Object[] options = Utils.listToArray(instanceModel().findAll());

        Customer objSelected = (Customer) JOptionPane.showInputDialog(
                null,
                "Choose a customer to delete: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().delete(objSelected.getId());
    }



    public void CustomerMenu (){
        String option1 = "";

        do {
            option1 = JOptionPane.showInputDialog("""
                CUSTOMER MENU:
                1. Customer list
                2. Add a new customer
                3. Update a customer
                4. Find a customer by name
                5. Delete a customer
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
