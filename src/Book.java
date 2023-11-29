import java.util.Date;
import java.util.ArrayList;

public class Book {
    private String name;
    private String author;
    private String genre;
    private int year;
    private boolean checkedOut = false;

    public Book(){
    }

    public Book(String name, String author, String genre, int year){
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.year = year;
    }

    //checks if book is checked out or not when a user tries to check it out
    public boolean isCheckedOut() {
        return checkedOut;
    }

    //sets status of book to checked out or not
    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public String getName(){
        return this.name;
    }

    public String getAuthor(){
        return this.author;
    }
    public int getYear(){
        return this.year;
    }
    public String getGenre() {
        return this.genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Name: " + getName();
    }
}