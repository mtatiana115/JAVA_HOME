package Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    //1.Crear la variable que obtiene el estado de la conexión
    static Connection connection = null;

    //2. Método para abrir la conexión entra java y db
    public static Connection openConnection(){
        try {
            //3. Traer una clase sin importar arriba para acceder al catch automático
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Variables credenciales de la DB
            String url ="jdbc:mysql://uwvrxljp8wrmj2ew:NCIvHSNqhYoi8QIxkkml@bkqqeknoxiq2im831g8s-mysql.services.clever-cloud.com:3306/bkqqeknoxiq2im831g8s";
            String user ="uwvrxljp8wrmj2ew";
            String password="NCIvHSNqhYoi8QIxkkml";

            //Establecer conexión => equivalente a hacer un fetch con await en js
            //instancia connection para que lo convierta al tipo de dato Connection
            //en los paréntesis el orden de los parámetros es url, user, password
            //buscar el error y selecciono add catch
            connection =(Connection) DriverManager.getConnection(url,user,password);
            System.out.println("All rigth!");

        } catch (ClassNotFoundException e) {
            System.out.println("Error in driver" + e);
        } catch (SQLException e) {
            System.out.println("Error in database" + e);
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
