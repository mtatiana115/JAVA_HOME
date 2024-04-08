package controller;

import entity.Customer;
import entity.Product;
import entity.Purchase;
import model.CustomerModel;
import model.ProductModel;
import model.PurchaseModel;
import model.StoreModel;
import utils.Utils;
import javax.swing.*;
import java.util.List;

public class PurchaseController {

    private PurchaseModel objPurchaseModel;

    //
    public PurchaseController (){
        //Crear una instancia del model
        this.objPurchaseModel = new PurchaseModel();
    }

    public static PurchaseModel instanceModel(){
        return new PurchaseModel();
    }


    //Método para listar
    public String getAll(List<Object> listObject){
        String list = "Purchases list \n";

        //Iteramos sobre la lista que devuelve el método find All
        for (Object obj: listObject){

            // Convertimos o castemos el objeto tipo Object
            Purchase objPurchase = (Purchase) obj;

            //Concatenamos la información
            list += objPurchase.toString() + "\n";
        }
        return list;
    }

    public void getAll(){
        //aplico sobre escritura, donde los métodos que se llaman igual son diferenciados por los parámetros que le paso
        String list = getAll(instanceModel().findAll());

        //Mostramos la lista
        JOptionPane.showMessageDialog(null, list);
    }

    //Método para insertar
    public void create(){
        Purchase objPurchase = new Purchase();

        CustomerController objCustomerController = new CustomerController();
        CustomerModel objCustomerModel = new CustomerModel();

        ProductController objProductController = new ProductController();
        ProductModel objProductModel = new ProductModel();

        int idSCustomer = Integer.parseInt(JOptionPane.showInputDialog(null, objCustomerController.getAll(objCustomerModel.findAll()) + "\n Type the id customer"));
        int idSProduct = Integer.parseInt(JOptionPane.showInputDialog(null, objProductController.getAll(objProductModel.findAll()) + "\n Type the id product"));
        int amount = Integer.parseInt(JOptionPane.showInputDialog(null,"Insert a purchase amount: "));

        objPurchase.setAmount(amount);
        objPurchase.setIdCustomer(idSCustomer);
        objPurchase.setIdProduct(idSProduct);

        objPurchase = (Purchase) this.objPurchaseModel.insert(objPurchase);

        JOptionPane.showMessageDialog(null, objPurchase.toString());
    }


    //Método para actualizar
    public void update(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Purchase purchaseSelected = (Purchase)JOptionPane.showInputDialog(
                null,
                "Choese a purchase to update: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]

        ) ;
        purchaseSelected.setAmount(Integer.parseInt(JOptionPane.showInputDialog(null,"Type a new amount: ",purchaseSelected.getAmount() )));
        purchaseSelected.setIdCustomer(Integer.parseInt(JOptionPane.showInputDialog(null,"Type a new id store: ",purchaseSelected.getIdCustomer() )));
        purchaseSelected.setIdProduct(Integer.parseInt(JOptionPane.showInputDialog(null,"Type a new stock: ",purchaseSelected.getIdProduct() )));


        instanceModel().update(purchaseSelected);
    }


    public void filterByStore (){
        String store = JOptionPane.showInputDialog("Type the name to search: ");

        List<Object> filteredPurchase = this.objPurchaseModel.findByProduct(store);

        if (filteredPurchase.isEmpty()){
            JOptionPane.showMessageDialog(null, "No purchase found with the name " + store);
        } else {
            String listFilterPurchase = "Purchase's List \n";

            for (Object obj : filteredPurchase){

                //Castear
                Purchase objPurchase = (Purchase) obj;
                listFilterPurchase += objPurchase.toString() + "\n";
            }

            JOptionPane.showMessageDialog(null, listFilterPurchase);
        }
    }

    public void filterByProduct (){
        String product = JOptionPane.showInputDialog("Type the name of the store to search: ");

        List<Object> filteredPurchase = this.objPurchaseModel.findByProduct( product);

        if (filteredPurchase.isEmpty()){
            JOptionPane.showMessageDialog(null, "No store found with the name " + product);
        } else {
            String listFilterPurchase = "Purchase's List \n";

            for (Object obj : filteredPurchase){

                //Castear
                Purchase objPurchase = (Purchase) obj;
                listFilterPurchase += objPurchase.toString() + "\n";
            }

            JOptionPane.showMessageDialog(null, listFilterPurchase);
        }
    }

    //Método para eliminar
    public void delete(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Purchase purchaseSelected = (Purchase)JOptionPane.showInputDialog(
                null,
                "Choese a purchase to delete: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]

        ) ;

        instanceModel().delete(purchaseSelected);
    }


    public void PurchaseMenu (){
        String option1 = "";

        do {
            option1 = JOptionPane.showInputDialog("""
                    PRODUCTS MENU:
                1. Purchases list             
                2. Add a new purchase
                3. Update a purchase
                4. Find a purchase by store
                5. Delete a purchase
                6. Find a purchase by product
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
                    this.filterByStore();
                    break;

                case "5":
                    this.delete();
                    break;

                case "6":
                    this.filterByProduct();
                    break;
            }
        }while (!option1.equals("7"));
    }
}
