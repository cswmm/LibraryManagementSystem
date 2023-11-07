import java.util.ArrayList;

public class Librarian extends User{
    String username;
    String password;

    public Librarian(String username, String password){
        super(username, password);
    }

    public boolean addBook(Book book){

    }

    public boolean removeBook(Book book){

    }

    public static void removeUser(User u){
        Library.users.remove(u);
    }

    public ArrayList<Book> checkUserCheckedOut(User user){
        return user.getBooks();
    }

    public ArrayList<Book> checkSuggestions(){

    }

}
