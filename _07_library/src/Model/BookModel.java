package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Author;
import Entity.Book;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookModel implements CRUD {
    @Override
    public Object insert(Object object) {
        //1. Abrir la conexión
        Connection objconnection = ConfigDB.openConnection();

        //2. Castear el objeto
        Book objBook =(Book) object;

        try {
            //Sentencia sql
            String sql = "INSERT INTO book (title,publicationYear,price,authorId) VALUES(?,?,?,?);";
            //Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objconnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //Asignar los ?
            objPrepare.setString(1, objBook.getTitle());
            objPrepare.setInt(2, objBook.getPublicationYear());
            objPrepare.setDouble(3, objBook.getPrice());
            objPrepare.setInt(4, objBook.getAuthorId());

            //Ejecutar el query
            objPrepare.execute();

            //Obtener resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objBook.setId(objResult.getInt(1));
            }
            //Cerramos el prepareStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null,  "Book insertion was succesfull.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding book" + e.getMessage());
        }

        //Cerrar la conexión
        ConfigDB.closeConnection();
        return objBook;
    }

    @Override
    public boolean update(Object object) {
        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();
        //2. Convertir el objeto porque object es un tipo de dato genérico, cuando se convierte podré accder a los métodos creados por mi
        Book objBook = (Book) object;
        //3. Variable bandera para saber si se actualizó, ya que el método devuelve boolean verdadero se actualizó, falso no se actualizó
        boolean isUpdated = false;

        //4.Cualquier cosa puede fallar creo try catch

        try {
            //5. Creamos la sentencia SQL
            String sql = "UPDATE book SET title = ?, publicationYear = ?, price = ?, authorId =? WHERE id= ?;";

            //6. Preparamos el statement
            PreparedStatement objPrepare = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //7. Dar valor a los ? parámetros de Query
            //ojo en set verificar el tipo de dato ex. setInt or setString
            objPrepare.setString(1, objBook.getTitle());
            objPrepare.setInt(2, objBook.getPublicationYear());
            objPrepare.setDouble(3,objBook.getPrice());
            objPrepare.setInt(4, objBook.getAuthorId());
            objPrepare.setInt(5, objBook.getId());

            //8. Ejecutamos el query
            //ExecuteQuery para obtener como quedó finalmente el obj y executeUpdate me da la cantidad de filas afectadas
            int rowAffected = objPrepare.executeUpdate();
            if (rowAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //Cerrar la conexión
        ConfigDB.closeConnection();

        return isUpdated;
    }

    @Override
    public boolean delete(int id) {
        // Variable booleana para medir el estado de la eliminación
        boolean isDeleted = false;

        // Abrir la conexión
        Connection connection = ConfigDB.openConnection();

        try {
            // Escribir la sentencia SQL
            String sql = "DELETE FROM book WHERE id = ?;";

            // Preparar el statement
            PreparedStatement objPrepare = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // Asignar el valor al ?
            objPrepare.setInt(1, id);

            // Ejecutar
            // La cantidad de filas afectadas por la sentencia ejecutada
            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The delete was successful");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // Cerrar la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listBook = new ArrayList<>();

        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM book;";

            //3. prepared statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);

            //4. que devuelva un resultado => executeQuery
            ResultSet objResultSet = objPrepare.executeQuery();

            //5. Obtener los resultado
            while (objResultSet.next()){

                //Crear la instancia del book
                Book book = new Book();

                //LLenar nuestro objeto con lo que devuelve la base de datos (ResultSet)
                book.setId(objResultSet.getInt("id"));
                book.setTitle(objResultSet.getString("title"));
                book.setPublicationYear(objResultSet.getInt("publicationYear"));
                book.setPrice(objResultSet.getDouble("price"));
                book.setAuthorId(objResultSet.getInt("authorId"));

                //Agregar a la lista del book
                listBook.add(book);

            }
        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //cerrar la conexión
        ConfigDB.closeConnection();

        return listBook;
    }

    @Override
    public Object findById(int id) {
        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();
        Book objBook = null;
        try {
            //2. Sentencia SQL
            String sql ="SELECT * FROM book WHERE id = ?;";
            //3. Preparar el statement
            PreparedStatement objPrepare = connection.prepareStatement(sql);
            //4. Damos valor al ?
            objPrepare.setInt(1,id);
            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6.Mientras haya un registro siguiente entonces
            while (objResult.next()){
                objBook = new Book();
                objBook.setId(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setPublicationYear(objResult.getInt("publicationYear"));
                objBook.setPrice(objResult.getDouble("price"));
                objBook.setAuthorId(objResult.getInt("authorId"));
            }

        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return objBook;
    }

    public List<Object> findByName (String title) {
        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();
        List<Object> listBook = new ArrayList<>();

        try {
            //2.sentencia SQL
            String sql = "SELECT * FROM book WHERE title LIKE ?";
            //String sql = "SELECT * FROM book WHERE name LIKE '%"+ title +"%'";
            //3. Preparar el statement
            PreparedStatement objPrepare = connection.prepareStatement(sql);
            //4. Damos valor al ?
            objPrepare.setString(1, "%" +title+ "%");
            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6. Mientras haya un registro siguiente entonces
            while (objResult.next()) {
                //creo la instancia de book
                Book objBook = new Book();

                objBook = new Book();
                objBook.setId(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setPublicationYear(objResult.getInt("publicationYear"));
                objBook.setPrice(objResult.getDouble("price"));
                objBook.setAuthorId(objResult.getInt("authorId"));

                //Finalmente Agregar a la lista el book
                listBook.add(objBook);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return listBook;
    }
}
