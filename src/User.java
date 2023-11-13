import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class User extends Library {
    private String username;
    private char[] password;
    private String security1;
    private String security2;

    private LinkedList<String> securityAnswer;

    private ArrayList<Book> books;
    private ArrayList<Book> bookhistory;

    private ArrayList<Book> checkoutHistory;

    private boolean isPremium;


    public User(){
    }
    public User(String username, char[] password, String security1, String security2) {
        this.username = username;
        this.password = password;
        this.security1 = security1;
        this.security2 = security2;
    }

    public void terminateAccount() {
        username = null;
        password = null;
        Library.users.remove(this);
    }

    public void changeAccountInfo(String username, char[] password) {
        this.username = username;
        this.password = password;
    }

    /*public String requestInfo(String username, String password, String security1, String security2) {
        // GUI show 4 textfield with text: "type in username or password and answer security questions"
        if(Library.users.containsKey(username)){
            if(askSecurity(username,security1,security2)) {
                return "Username: " + getUsername() + "\nPassword:  " + getPassword() +
                        "\nSecurity answer 1: " + getSecurity1() + "\nSecurity answer 2: " + getSecurity2();
            }
        } else {
            for(int i = 0; i < Library.users.size(); i++){
                if(Objects.equals(Library.users.get(String.valueOf(i)).getPassword(), password)){
                    if(askSecurity(username,security1,security2)) {
                        return "Username: " + getUsername() + "\nPassword:  " + getPassword() +
                                "\nSecurity answer 1: " + getSecurity1() + "\nSecurity answer 2: " + getSecurity2();
                    }
                }
            }

        }
        return "Cannot get user's information. username,password, or security questions' answers are wrong";
        // GUI message
    }*/

    // User search by type in book name and/or a list of genre
    public ArrayList<Book> search(String name, LinkedList<Book.genre> genreList){
        ArrayList<Book> searchList = new ArrayList<>();
        if(genreList.isEmpty()){ // Search with only name, assign null value to genre when user don't type in genre
            for(Book book : super.getBooks()){
                if(Objects.equals(book.getName(), name)){
                    searchList.add(book);
                }
            }
        } else if(Objects.equals(name, "")){ // Search with only genre
            for(Book book : super.getBooks()){
                for(Book.genre genre : genreList){
                    if(book.getGenreList().contains(genre)){
                        searchList.add(book);
                        break;
                    }
                }
            }
        } else { // Search by both book name and list of genre
            for(Book book : super.getBooks()){
                if(Objects.equals(book.getName(), name)){
                    for(Book.genre genre : genreList){
                        if(book.getGenreList().contains(genre)){
                            searchList.add(book);
                            break;
                        }
                    }
                }
            }
            if(searchList.isEmpty()){
                System.out.print("There is no book fit your search"); // GUI message
            }
        }
        return searchList;
    }
    public void checkout(ArrayList<Book> cart){
        // We need to think of how we will implement this first
        // Do we want a cart? Do we check if the book is available before or after they add to cart?
        books.addAll(cart);
        bookhistory.addAll(cart);
    }
    public void returnBook(ArrayList<Book> books){
        for (Book book : books) {
            this.books.remove(book);
        }
    }
    public void requestionExtension(Book book){
    }
    public void requestSuggestedBooks(ArrayList<Book> Suggestion){

    }
    public ArrayList<Book> checkCheckedOutBook(){
        return this.books;
    }
    public ArrayList<Book> checkCheckedOutBookHistory(){
        return bookhistory;
    }

    public void buyPremium() {
        // Check if the user has paid first
        Library.givePremium(this);
    }

    public ArrayList<Book> getBooks() { // I believe getBooks() and checkCheckedOutBook() are the same
        return books;
    }

    /*public boolean askSecurity(String username, String security1, String security2) {
        if(security1 != Library.users.get(username).getSecurity1() || security2 != Library.users.get(username).getSecurity2()){
            return false;
        } else {
            return true;
        }
    }*/

    public String getUsername(){
        return this.username;
    }

    public char[] getPassword(){
        return this.password;
    }

    public String getSecurity1(){
        return this.security1;
    }

    public String getSecurity2(){
        return this.security2;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(char[] password){
        this.password = password;
    }

    public boolean hasPremium(){
        return isPremium;
    }

    public void setPremium(Boolean isPremium){
        this.isPremium = isPremium;
    }
}
