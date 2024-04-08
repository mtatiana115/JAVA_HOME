package controller;

import entity.Product;
import entity.Store;
import model.ProductModel;
import model.StoreModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class ProductController {

    private ProductModel objProductModel;

    //
    public ProductController (){
        //Crear una instancia del model
        this.objProductModel = new ProductModel();
    }

    public static ProductModel instanceModel(){
        return new ProductModel();
    }


    //Método para listar todos los productos
    public String getAll(List<Object> listObject){
        String list = "Products list \n";

        //Iteramos sobre la lista que devuelve el método find All
        for (Object obj: listObject){

            // Convertimos o castemos el objeto tipo Object a una producto
            Product objProduct = (Product) obj;

            //Concatenamos la información
            list += objProduct.toString() + "\n";
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
        Product objProduct = new Product();
        String name = JOptionPane.showInputDialog(null,"Insert a product name: ");
        Double price = Double.parseDouble(JOptionPane.showInputDialog(null, "Insert a price: "));
        int stock = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert a stock"));

        StoreController objStoreController = new StoreController();
        StoreModel objStoreModel = new StoreModel();

        int idStore = Integer.parseInt(JOptionPane.showInputDialog(null, objStoreController.getAll(objStoreModel.findAll()) + "\n Type the id store"));


        objProduct.setName(name);
        objProduct.setPrice(price);
        objProduct.setStock(stock);
        objProduct.setIdStore(idStore);

        objProduct = (Product) this.objProductModel.insert(objProduct);

        JOptionPane.showMessageDialog(null, objProduct.toString());
    }


    //Método para actualizar
    public void update(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Product productSelected = (Product)JOptionPane.showInputDialog(
                null,
                "Choese a product to update: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]

        ) ;
        productSelected.setName(JOptionPane.showInputDialog(null, "Type a new name: ",productSelected.getName()));
        productSelected.setPrice(Double.parseDouble(JOptionPane.showInputDialog(null,"Type a new price: ",productSelected.getPrice() )));
        productSelected.setIdStore(Integer.parseInt(JOptionPane.showInputDialog(null,"Type a new id store: ",productSelected.getIdStore() )));
        productSelected.setStock(Integer.parseInt(JOptionPane.showInputDialog(null,"Type a new stock: ",productSelected.getStock() )));


        instanceModel().update(productSelected);
    }


    public void filterByName (){
        String name = JOptionPane.showInputDialog("Type the name to search: ");

        List<Object> filteredProduct = this.objProductModel.findByName(name);

        if (filteredProduct.isEmpty()){
            JOptionPane.showMessageDialog(null, "No product found with the name " + name);
        } else {
            String listFilterProduct = "Product's List \n";

            for (Object obj : filteredProduct){

                //Castear
                Product objProduct = (Product) obj;
                listFilterProduct += objProduct.toString() + "\n";
            }

            JOptionPane.showMessageDialog(null, listFilterProduct);
        }
    }

    public void filterByStore (){
        String store = JOptionPane.showInputDialog("Type the name of the store to search: ");

        List<Object> filteredProduct = this.objProductModel.findByStore( store);

        if (filteredProduct.isEmpty()){
            JOptionPane.showMessageDialog(null, "No store found with the name " + store);
        } else {
            String listFilterProduct = "Product's List \n";

            for (Object obj : filteredProduct){

                //Castear
                Product objProduct = (Product) obj;
                listFilterProduct += objProduct.toString() + "\n";
            }

            JOptionPane.showMessageDialog(null, listFilterProduct);
        }
    }

    //Método para eliminar
    public void delete(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Product productSelected = (Product)JOptionPane.showInputDialog(
                null,
                "Choese a product to delete: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]

        ) ;

        instanceModel().delete(productSelected);
    }


    public void ProductMenu (){
        String option1 = "";

        do {
            option1 = JOptionPane.showInputDialog("""
                    PRODUCTS MENU:
                1. Products list             
                2. Add a new product
                3. Update a product
                4. Find a product by name
                5. Delete a product
                6. Find a product by name of the store
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
                    this.filterByStore();
                    break;
            }
        }while (!option1.equals("7"));
    }
}
