package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    //1.Crear la variable statica de tipo Connection que obtiene el estado de la conexión
    public static Connection connection = null;

    //2. Método estatico para abrir la conexión entre java y db
    public static Connection openConnection(){
        try {
            //3. llamar al driver, agregar la excepcion en catch
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Variables credenciales de la DB
            String url ="jdbc:mysql://u4uia1hznzc2oumh:kT6tLWFYWHBIaOIhtTVf@b2svc1fq51fqs5e7nwzi-mysql.services.clever-cloud.com:3306/b2svc1fq51fqs5e7nwzi";
            String user ="u4uia1hznzc2oumh";
            String password="kT6tLWFYWHBIaOIhtTVf";

            //Establecer conexión mediante el drver manager
            //habilitar catch manejo de errores de sql exception
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("All rigth!");

        } catch (ClassNotFoundException e) {
            System.out.println("Error in driver" + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }
        return connection;
    }

    //método para cerrar la conexión
    public static void closeConnection(){
        try {
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }
    }
}
