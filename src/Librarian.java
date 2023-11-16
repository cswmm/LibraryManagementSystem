import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;

public class Librarian extends User{

    public Librarian(){}

    public Librarian(String username, char[] password, String security1, String security2){
        super(username, password, security1, security2);
    }

    // Librarian enter book information to add book to library system
    public void addBook(String name, String author, String genre, int year, Date dueDate){
        int count = 0;
        for(Book book : ((Library)this).getBooks()){
            // Check using only name, author, and year
            if(Objects.equals(book.getName(), name) && Objects.equals(book.getAuthor(), author) && book.getYear() == year){
                System.out.println("Book already in library");
                count++;
            }
        }
        if(count == 0) {
            Book book = new Book(name, author, genre, year);
            ((Library)this).getBooks().add(book);
        }
    }

    // Librarian type in the name of the book they want to remove
    public void removeBook(String bookName){
        for(Book book : ((Library)this).getBooks()){
            if(Objects.equals(book.getName(), bookName)){
                ((Library)this).getBooks().remove(book);
                System.out.println("Book has been removed");  //GUI message
            } else {
                System.out.println("Book is not in library system, please try again");
                // GUI message
            }
        }
    }

    // Librarian type in the username of the user they want to remove
    public void removeUser(String username){
        int count = 0;
        for(User u : users) {
            if (u.getUsername().equals(username)) {
                count++;
                users.remove(username);
            }
        }
        if(count == 0) {
            System.out.println("User not existed, please try again"); // GUI message
        }
    }

    public ArrayList<Book> checkUserCheckedOut(String username){
        int count = 0;
       for(User u : users){
           if(u.getUsername() == username){
               count++;
               return u.getBooks();
           }
       }
       if(count == 0) {
           System.out.println("No account with that username");
       }
       return null;
    }

    public ArrayList<Book> checkSuggestions(){
        return requestBook;
    }

    // Librarian type in book name and a list of genre they would like to add to that book
    /*public void addBookGenre(String name, LinkedList<Book.genre> genreList){
        for(int i = 0; i < getBooks().size(); i++){
            if(Objects.equals(getBooks().get(i).getName(), name)){
                getBooks().get(i).getGenreList().addAll(genreList);
            }
        }
    }*/

    @Override
    public String toString() {
        return "Librarian username: " + this.getUsername() + "; Librarian password: " + String.valueOf(this.getPassword());
    }
}
