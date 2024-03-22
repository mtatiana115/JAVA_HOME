package Controller;
import Entity.Book;
import Model.BookModel;
import javax.swing.*;
import java.util.List;

public class BookController {

    private BookModel objBookModel;

    public BookController(){
        objBookModel = new BookModel();
    }

    //Método para listar todos los libros
    public String getAll(){
        String list = "Books' list \n";
        for (Object obj: this.objBookModel.findAll()){
            Book objBook = (Book) obj;
            list += objBook.toString() + "\n";
        }
        return list;
    }

    public void getAllMessage(){
        JOptionPane.showMessageDialog(null, getAll());
    }

    //Método para insertar
    public void create(){
        BookController objBookController = new BookController();
        Book objBook = new Book();
        String title = JOptionPane.showInputDialog("Insert title: ");
        int publicationYear = Integer.parseInt(JOptionPane.showInputDialog("Insert publication year: "));
        double price = Double.parseDouble(JOptionPane.showInputDialog("Insert price: "));
        JOptionPane.showMessageDialog(null, "look at the list of books and identify their id number" + objBookController.getAll());
        int authorId = Integer.parseInt(JOptionPane.showInputDialog("Insert author id: "));


        objBook.setTitle(title);
        objBook.setPublicationYear(publicationYear);
        objBook.setPrice(price);
        objBook.setAuthorId(authorId);

        objBook = (Book) this.objBookModel.insert(objBook);
        JOptionPane.showMessageDialog(null, objBook.toString());
    }

    public void delete(){
        String listBookString = "BOOKS' LIST \n";

        for (Object obj:this.objBookModel.findAll()) {
            //Castear
            Book objBook = (Book) obj;
            listBookString += objBook.toString() + "\n";
        }
        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listBookString + "Enter the ID of the book to delete"));

        //Despues de crear el método delete
        Book objBook = (Book) this.objBookModel.findById(idDelete);

        if (objBook == null){
            JOptionPane.showMessageDialog(null, " Book not found.");
        }else {
            confirm = JOptionPane.showConfirmDialog(null," Are you sure to want to delete the book: \n" + objBook.toString());

            //Si el usuario escogió que si entonces eliminamos
            if (confirm == 0){
                this.objBookModel.delete(idDelete);
            }
        }
    }

    public void filterByName() {
        String title = JOptionPane.showInputDialog("Enter the title to search:");

        List<Object> filteredBooks = this.objBookModel.findByName(title);

        if (filteredBooks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No books found with the title: " + title);
        } else {
            String listBookFilter = "BOOKS' LIST \n";
            for (Object obj : filteredBooks) {
                // Castear
                Book objBook = (Book) obj;
                listBookFilter += objBook.toString() + "\n";

            }

            JOptionPane.showMessageDialog(null, listBookFilter);

        }
    }

    public void filterById() {
        JOptionPane.showMessageDialog(null,"Find the id of the book that you want to delete" + this.getAll());
        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID to search:"));

        Object book = objBookModel.findById(id);

        if (book == null) {
            JOptionPane.showMessageDialog(null, "No book found with the ID: " + id);
        } else {
            Book foundBook = (Book) book;
            JOptionPane.showMessageDialog(null, "Book found:\n" + foundBook.toString());
        }
    }



    public void update(){
        //Listamos
        String listBook = this.getAll();

        //Pedimos el id
        int isUpdate = Integer.parseInt(JOptionPane.showInputDialog(listBook + "\nEnter the ID of the book to edit"));

        //Verificar el id
        Book objBook = (Book) this.objBookModel.findById(isUpdate);

        if (objBook == null){
            JOptionPane.showMessageDialog(null, "Book not found.");
        } else {
            String title = JOptionPane.showInputDialog(null, "Enter new title: ", objBook.getTitle());
            int publicationYear = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter new publication year: ", objBook.getPublicationYear()));
            double price = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter new price: ", objBook.getPrice()));
            int authorId = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter new author Id", objBook.getAuthorId()));

            //actualizar el objeto Book

            objBook.setTitle(title);
            objBook.setPublicationYear(publicationYear);
            objBook.setPrice(price);
            objBook.setAuthorId(authorId);
            //objAuth.setAge(age);

            //Guardar y actualizar en la DB con update
            this.objBookModel.update(objBook);
            JOptionPane.showMessageDialog(null,"Update "+ listBook);
        }
    }

    public void bookMenu(){
        String option2 = "";

        do {
            option2 = JOptionPane.showInputDialog("""
                    BOOKS' MENU
                    1. List books
                    2. Insert book
                    3. Update book
                    4. Delete book
                    5. Filter book
                    6. Return
                                                    
                    Choose an option:
                    """);
            switch (option2) {
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

                case "5":
                    String option3 = "";

                    do {
                        option3 = JOptionPane.showInputDialog("""
                                 BOOK FILTER MENU
                                1. Filter by ID
                                2. Filter by title
                                3. Filter by author
                                4. Return
                                                                            
                                Choose an option:
                                """);

                        switch (option3) {
                            case "1":
                                this.filterById();
                                break;

                            case "2":
                                this.filterByName();
                                break;

                            case "3":
                                break;
                        }
                    } while (!option3.equals("4"));

                    break;
            }
        }while (!option2.equals("6")) ;
    }
}


