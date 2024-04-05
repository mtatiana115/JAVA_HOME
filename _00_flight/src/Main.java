import Configuration.ConfigDB;
import Controller.BookingController;
import Controller.FlightController;
import Controller.PassengerController;
import Controller.PlaneController;

import javax.swing.*;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {;
        PlaneController objPlaneController = new PlaneController();
        PassengerController objPassengerController = new PassengerController();
        FlightController objFlightController = new FlightController();
        BookingController objBookingController = new BookingController();


        String option = "";
        do {

            option = JOptionPane.showInputDialog("""
                    PRINCIPAL MENU
                    1. Manage plane
                    2. Manage passenger
                    3. Manage flight
                    4. Manage booking
                    5. Exit
                                        
                    Choose an option: 
                    """);
            switch (option) {
                case "1":
                    objPlaneController.PlaneMenu();
                    break;

                case "2":
                    objPassengerController.PassengerMenu();
                    break;

                case "3":
                    objFlightController.FlightMenu();
                    break;

                case "4":
                objBookingController.BookingMenu();
                    break;
            }
        } while (!option.equals("5"));
    }
}
