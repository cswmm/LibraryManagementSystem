import java.util.ArrayList;
        import java.util.LinkedList;
        import java.util.Objects;

public class User extends Library {
    private String username;
    private String password;
    private String security1;
    private String security2;

    private LinkedList<String> securityAnswer;

    private ArrayList<Book> books;
    private ArrayList<Book> bookhistory;

    private ArrayList<Book> checkoutHistory;


    public User(){
    }
    public User(String username, String password, String security1, String security2) {
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

    public void changeAccountInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String requestInfo(String username, String password, String security1, String security2) {
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
    }
    public ArrayList<Book> search(String name, String genre){
        ArrayList<Book> searchlist = new ArrayList<>();
        if(genre == ""){ // Search with only name
            for(Book book : super.getBooks()){
                if(book.getName() == name){
                    searchlist.add(book);
                }
            }
        } else if(name == ""){ // Search with only category
            for(Book book : super.getBooks()){
                if(Objects.equals(book.getGenre(), genre)){
                    searchlist.add(book);
                }
            }
        } else {
            for(Book book : super.getBooks()){
                if(Objects.equals(book.getGenre(), genre) && Objects.equals(book.getName(), name)){
                    searchlist.add(book);
                }
            }
            if(searchlist.isEmpty()){
                System.out.print("There is no book fit your search"); // GUI message
            }
        }
        return searchlist;
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

    public boolean askSecurity(String username, String security1, String security2) {
        if(security1 != Library.users.get(username).getSecurity1() || security2 != Library.users.get(username).getSecurity2()){
            return false;
        } else {
            return true;
        }
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
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

    public void setPassword(String password){
        this.password = password;
    }
}
