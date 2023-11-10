import java.util.Date;

public class Book {
    private String name;
    private String author;
    private String genre;
    private int year;

    private Date dueDate = null; // book in library don't have due date

    public Book(){
    }

    public Book(String name,String author, String genre, int year, Date dueDate){
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.dueDate = dueDate;
    }
    public String getName(){
        return this.name;
    }
    public String getGenre(){
        return this.genre;
    }

    public String getAuthor(){
        return this.author;
    }
    public int getYear(){
        return this.year;
    }

    public Date getDueDate(){
        return this.dueDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}