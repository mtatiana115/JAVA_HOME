import controller.CustomerController;
import controller.ProductController;
import controller.PurchaseController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        CustomerController objCustomerController = new CustomerController();
        ProductController objProductController = new ProductController();
        PurchaseController objPurchaseController = new PurchaseController();

        String option = "";
        do {

            option = JOptionPane.showInputDialog("""
                    PRINCIPAL MENU
                    1. Manage products
                    2. Manage customers
                    3. Manage purchase
                    4. Exit
                                        
                    Choose an option: 
                    """);
            switch (option) {
                case "1":;
                objProductController.ProductMenu();
                    break;

                case "2":
                    objCustomerController.CustomerMenu();
                    break;

                case "3":
                    objPurchaseController.PurchaseMenu();
                    break;

            }
        } while (!option.equals("4"));
    }
}

