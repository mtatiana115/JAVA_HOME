import Controller.AuthorController;
import Controller.BookController;
import Database.ConfigDB;
import Model.AuthorModel;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AuthorController objAuthorController = new AuthorController();
        BookController objBookController = new BookController();
        String option = "";

        do {
            option = JOptionPane.showInputDialog("""
                    PRINCIPAL MENU
                    1. Administration for authors
                    2. Administration for books
                    3. consult books by an author
                    4. Exit
                                        
                    Choose an option:
                    """);

            switch (option) {
                case "1":
                    objAuthorController.authorMenu();

                    break;

                case "2":
                    objBookController.bookMenu();
                    break;

                case "3":
                    objAuthorController.filterByIdAuthor();
                    break;

            }
        }while (!option.equals("4")) ;
    }
}