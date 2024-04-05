import Controller.AppointmentController;
import Controller.DoctorController;
import Controller.PatientController;
import Controller.SpecialtyController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SpecialtyController objSpecialtyController = new SpecialtyController();
        PatientController objPatientController = new PatientController();
        DoctorController objDoctorController = new DoctorController();
        AppointmentController objAppointmentController = new AppointmentController();

        String option = "";
        do {

            option = JOptionPane.showInputDialog("""
                    PRINCIPAL MENU
                    1. Manage specialties
                    2. Manage doctors
                    3. Manage patients
                    4. Manage appointments
                    5. Exit
                                        
                    Choose an option: 
                    """);
            switch (option) {
                case "1":
                    objSpecialtyController.SpecialtyMenu();
                    break;

                case "2":
                    objDoctorController.DoctorMenu();
                    break;

                case "3":
                    objPatientController.PatientMenu();
                    break;

                case "4":
                    objAppointmentController.AppointmentMenu();
                    break;
            }
        } while (!option.equals("5"));
    }
}
