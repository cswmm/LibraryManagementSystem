import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;

public class Librarian extends User {

    public Librarian(){}

    public Librarian(String username, char[] password){
        super(username, password);
    }

    // method that allows librarian to access library system to add book
    public void addBook(Library library, String name, String author, String genre, int year){
        library.addBook(name, author, genre, year);
    }

    // method that allows librarian to access library system to remove book
    public void removeBook(Library library, Book book){
        library.removeBook(book);
        for (int i = 0; i < library.getUsers().size(); i++) {
            library.getUsers().get(i).getBooks().remove(book);
        }
    }

    // method that allows librarian to access library system to remove user
    public void removeUser(Library library, User user){
        library.removeUser(user);
    }

    //toString method. Used in console for verifying librarian is already in system
    @Override
    public String toString() {
        return "Librarian username: " + this.getUsername() + "; Librarian password: " + String.valueOf(this.getPassword());
    }
}
