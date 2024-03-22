package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    static  Connection objconnection = null;

    //Método para abrir la conexion
    public static Connection openConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://bmykk160yrqtzsryqovb-mysql.services.clever-cloud.com:3306/bmykk160yrqtzsryqovb";
            //config usuario
            String user ="uzw30az6gtm9x4co";
            String password = "Q7s3ZkNOUEJ1DdcxZTFi";

            objconnection = (Connection) DriverManager.getConnection(url, user, password);

            System.out.println("All right!");

        }catch (ClassNotFoundException e) {
            System.out.println("Error in driver" + e);
        } catch (SQLException e) {
            System.out.println("Error in database" + e);
        }
        return objconnection;
    }

    //Método para cerrar la conexión
    public static void closeConnection(){
        try {
            if (objconnection != null){
               objconnection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }
    }
}
