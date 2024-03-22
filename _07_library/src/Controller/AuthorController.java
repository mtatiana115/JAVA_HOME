package Controller;
import Entity.Author;
import Entity.Book;
import Model.AuthorModel;
import javax.swing.*;
import java.util.List;

public class AuthorController {

    private AuthorModel objAuthorModel;

    public AuthorController(){
        objAuthorModel = new AuthorModel();
    }

    //Método para eliminar
    public void delete(){
        String listAuthorString = "AUTHORS' LIST \n";

        for (Object obj:this.objAuthorModel.findAll()) {
            //Castear
            Author objAuthor = (Author) obj;
            listAuthorString += objAuthor.toString() + "\n";
        }
        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAuthorString + "Enter the ID of the author to delete"));

        //Despues de crear el método delete
        Author objAuthor = (Author) this.objAuthorModel.findById(idDelete);

        if (objAuthor == null){
            JOptionPane.showMessageDialog(null, " Author not found.");
        }else {
            confirm = JOptionPane.showConfirmDialog(null," Are you sure to want to delete the author: \n" + objAuthor.toString());

            //Si el usuario escogió que si entonces eliminamos
            if (confirm == 0){
                this.objAuthorModel.delete(idDelete);
            }
        }
    }

    //Método para listar todos los autores
    public String getAll(){
        String list = "List Authors \n";
        for (Object obj: this.objAuthorModel.findAll()){
            Author objAuthor = (Author) obj;
            list += objAuthor.toString() + "\n";
        }
        return list;
    }

    public void getAllMessage(){
        JOptionPane.showMessageDialog(null, getAll());
    }

    //Método para insertar autor
    public void create(){
        Author objAuthor = new Author();
        String name = JOptionPane.showInputDialog("Insert name: ");
        String nationality = JOptionPane.showInputDialog("Insert nationality: ");

        objAuthor.setName(name);
        objAuthor.setNationality(nationality);

        objAuthor = (Author) this.objAuthorModel.insert(objAuthor);
        JOptionPane.showMessageDialog(null, objAuthor.toString());
    }

    public void update(){
        //Listamos
        String listAuthor = this.getAll();

        //Pedimos el id
        int isUpdate = Integer.parseInt(JOptionPane.showInputDialog(listAuthor + "\nEnter the ID of the author to edit"));

        //Verificar el id
        Author objAuthor = (Author) this.objAuthorModel.findById(isUpdate);

        if (objAuthor == null){
            JOptionPane.showMessageDialog(null, "Author not found.");
        } else {
            String name = JOptionPane.showInputDialog(null, "Enter new name", objAuthor.getName());
            String nationality = JOptionPane.showInputDialog(null, "Enter new nationality", objAuthor.getNationality());
            //ex. para int uso String.valueof => convierte todo un entero a string
            //int age = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter new age", String.valueOf(objAuthor.getAge())));

            //actualizar el objeto Author
            objAuthor.setName(name);
            objAuthor.setNationality(nationality);
            //objAuthor.setAge(age);

            //Guardar y actualizar en la DB con update
            this.objAuthorModel.update(objAuthor);
        }
    }

    public void filterByIdAuthor(){
        String listAuthors = objAuthorModel.findAll().toString();
        int authorId = Integer.parseInt(JOptionPane.showInputDialog(listAuthors + "\nEnter the ID of the author to filter books"));

        List<Object> books = objAuthorModel.findBooksByIdAuthor(authorId);

        if (books.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No books found for the author with ID: " + authorId);
        } else {
            String bookList = "BOOKS' LIST\n";
            for (Object obj : books) {
                Book book = (Book) obj;
                bookList += book.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null, bookList);
        }
    }


    public void authorMenu(){
        String option1 = "";
        do {
            option1 = JOptionPane.showInputDialog("""
                                AUTHORS' MENU
                                1. List Authors
                                2. Insert Author
                                3. Update Author
                                4. Delete Author
                                5. Exit
                                                                
                                Choose an option:
                                """);

            switch (option1) {
                case "1":
                    this.getAllMessage();
                    break;

                case "2":
                    this.create();
                    break;

                case "3":
                    this.update();
                    break;

                case "4":
                    this.delete();
                    break;
            }
        } while (!option1.equals("5"));
    }

}
