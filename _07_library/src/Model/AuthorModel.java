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

public class AuthorModel implements CRUD {

    @Override
    public Object insert(Object object) {
        //1. Abrir la conexión
        Connection objconnection = ConfigDB.openConnection();

        //2. Castear el objeto
        Author objAuthor =(Author) object;

        try {
            //Sentencia sql
            String sql = "INSERT INTO author(name,nationality) VALUES(?,?);";
            //Preparar el statement
            PreparedStatement objPrepare = (PreparedStatement) objconnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //Asignar los ?
            objPrepare.setString(1, objAuthor.getName());
            objPrepare.setString(2, objAuthor.getNationality());

            //Ejecutar el query
            objPrepare.execute();

            //Obtener resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objAuthor.setId(objResult.getInt(1));
            }
            //Cerramos el prepareStatement
            objPrepare.close();
            JOptionPane.showMessageDialog(null,  "Author insertion was succesfull.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding author" + e.getMessage());
        }

        //Cerrar la conexión
        ConfigDB.closeConnection();
        return objAuthor;
    }

    @Override
    public boolean update(Object object) {
        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();
        //2. Convertir el objeto porque object es un tipo de dato genérico, cuando se convierte podré accder a los métodos creados por mi
        Author objAuthor = (Author) object;
        //3. Variable bandera para saber si se actualizó, ya que el método devuelve boolean verdadero se actualizó, falso no se actualizó
        boolean isUpdated = false;

        //4.Cualquier cosa puede fallar creo try catch

        try {
            //5. Creamos la sentencia SQL
            String sql = "UPDATE author SET name = ?, nationality = ? WHERE id= ?;";

            //6. Preparamos el statement
            PreparedStatement objPrepare = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //7. Dar valor a los ? parámetros de Query
            //ojo en set verificar el tipo de dato ex. setInt or setString
            objPrepare.setString(1, objAuthor.getName());
            objPrepare.setString(2, objAuthor.getNationality());
            objPrepare.setInt(3, objAuthor.getId());

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
            String sql = "DELETE FROM author WHERE id = ?;";

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
        List<Object> listAuthors = new ArrayList<>();

        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        try {
            //2. Sentencia sql
            String sql = "SELECT * FROM author;";

            //3. prepared statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);

            //4. que devuelva un resultado => executeQuery
            ResultSet objResultSet = objPrepare.executeQuery();

            //5. Obtener los resultado
            while (objResultSet.next()){

                //Crear la instancia del author
                Author author = new Author();

                //LLenar nuestro objeto con lo que devuelve la base de datos (ResultSet)
                author.setId(objResultSet.getInt("id"));
                author.setName(objResultSet.getString("name"));
                author.setNationality(objResultSet.getString("nationality"));

                //Agregar a la lista del author
                listAuthors.add(author);

            }
        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //cerrar la conexión
        ConfigDB.closeConnection();

        return listAuthors;
    }

    @Override
    public Object findById(int id) {
        //1. Abrir la conexión
        Connection connection = ConfigDB.openConnection();
        Author objAuthor = null;
        try {
            //2. Sentencia SQL
            String sql ="SELECT * FROM author WHERE id = ?;";
            //3. Preparar el statement
            PreparedStatement objPrepare = connection.prepareStatement(sql);
            //4. Damos valor al ?
            objPrepare.setInt(1,id);
            //5. Ejecutamos el query
            ResultSet objResult = objPrepare.executeQuery();

            //6.Mientras haya un registro siguiente entonces
            while (objResult.next()){
                objAuthor = new Author();
                objAuthor.setId(objResult.getInt("id"));
                objAuthor.setName(objResult.getString("name"));
                objAuthor.setNationality(objResult.getString("nationality"));
            }

        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        //7. Cerrar la conexión
        ConfigDB.closeConnection();

        return objAuthor;
    }

    public List<Object> findBooksByIdAuthor(int authorId) {

        List<Object> listBooks = new ArrayList<>();

        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        try {
            //2. Sentencia sql
            String sql = "SELECT book.id, book.title, book.publicationYear, book.price, book.authorId, author.name AS author\n" +
                    "from author INNER JOIN book ON author.id = book.authorId\n" +
                    "WHERE author.id = ?;";

            //3. prepared statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // Asignar el valor al ?
            objPrepare.setInt(1, authorId);

            //4. que devuelva un resultado => executeQuery
            ResultSet objResultSet = objPrepare.executeQuery();

            //5. Obtener los resultado
            while (objResultSet.next()){

                //Crear la instancia
                Book book = new Book();

                //LLenar nuestro objeto con lo que devuelve la base de datos (ResultSet)
                book.setTitle(objResultSet.getString("title"));
                book.setPublicationYear(objResultSet.getInt("publicationYear"));
                book.setPrice(objResultSet.getDouble("price"));
                book.setAuthorId(objResultSet.getInt("authorId"));


                //Agregar a la lista del author
                listBooks.add(book);

            }
        } catch (SQLException e) {
            System.out.println("Error in database" + e.getMessage());
        }

        //cerrar la conexión
        ConfigDB.closeConnection();

        return listBooks;
    }
}

