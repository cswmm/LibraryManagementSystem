import java.util.Date;
import java.util.ArrayList;

public class Book {

    public enum genre{
        COMEDY, SCIENCE_FICTION, ROMANCE, FANTASY, ACTION, DYSTOPIA, HISTORY
    }

    private ArrayList<genre> genreList = new ArrayList<>();
    private String name;
    private String author;
    private String genre;
    private int year;

    private Date dueDate = null; // book in library don't have due date

    public Book(){
    }

    public Book(String name, String author, String genre, int year/* Date dueDate*/){
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.year = year;
        //this.dueDate = dueDate;
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

    public Date getDueDate(){
        return this.dueDate;
    }

    //public LinkedList<genre> getGenreList(){ return genreList; }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    /*public void setGenreList(LinkedList<genre> genreList) {
        this.genreList = genreList;
    }*/

    public void setYear(int year) {
        this.year = year;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Name: " + getName();
    }
}