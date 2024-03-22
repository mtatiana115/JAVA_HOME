package Entity;

public class Book {
    //atributos
    private int id;
    private String title;
    private int publicationYear;
    private double price;
    private int authorId;

    //constructor
    public Book(int id, String title, int publicationYear, double price, int authorId) {
        this.id = id;
        this.title = title;
        this.publicationYear = publicationYear;
        this.price = price;
        this.authorId = authorId;
    }
    public Book() {
    }
    //Setters and getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    //toString

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                ", price=" + price +
                ", authorId=" + authorId +
                '}';
    }
}
