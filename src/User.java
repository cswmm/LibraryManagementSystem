import java.util.ArrayList;

public class User {
    private String username;
    private String password;

    private ArrayList<Book> books;

    private ArrayList<Book> checkoutHistory;



    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void terminateAccount() {
        username = null;
        password = null;
        Library.users.remove(this);
    }

    public boolean changeAccountInfo(String username, String password) {
        this.username = username;
        this.password = password;
        return false;
    }
    public String requestLoginInfo(String username, String password) {
        return "";
    }
    //public ArrayList<Book> search(String name, String category){

    //}
    public void checkout(ArrayList<Book> cart){
        // We need to think of how we will implement this first
        // Do we want a cart? Do we check if the book is available before or after they add to cart?
        books.addAll(cart);
    }
    public void returnBook(ArrayList<Book> books){

    }
    public void requestionExtension(Book book){

    }
    public void requestSuggestedBooks(ArrayList<Book> Suggestion ){

    }
   // public ArrayList<Book> checkCheckedOutBook(){

    //}

    //public ArrayList<Book> checkCheckedOutBookHistory (){

    //}

    public void buyPremium() {
        // Check if the user has paid first
        Library.givePremium(this);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }
}
