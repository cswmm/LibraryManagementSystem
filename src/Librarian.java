import java.util.ArrayList;

public class Librarian extends User{
    // All of these get from Java Swing window
    String username;
    String password;
    String security1;
    String security2;

    public Librarian(String username, String password, String security1, String security2){
        super(username, password, security1, security2);
    }

    public void addBook(Book book){
        for(Book b : getBooks()){
            if(b != book){
                getBooks().add(book);
            } else {
                System.out.println("Book already in the library system"); // GUI message
            }
        }
    }

    public void removeBook(Book book){
        if(getBooks().contains(book)) {
            getBooks().remove(book);
            System.out.println("Book has been remove from the library system"); // GUI message
        } else {
            System.out.println("Book is not in the library system"); // GUI message
        }
    }

    public static void removeUser(String u){
        Library.users.remove(u);
    }

    public ArrayList<Book> checkUserCheckedOut(User user){
        return user.getBooks();
    }

    public ArrayList<Book> checkSuggestions(){
        return requestBook;
    }

}
