import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.io.*;

public class User extends Library {
    private String username;
    private char[] password;
    private boolean isPremium = false; // User initially not Premium User

    private ArrayList<Book> books;

    public User(){
    }
    public User(String username, char[] password) {
        this.username = username;
        this.password = password;
        this.books = new ArrayList<>();
    }

    /*public void terminateAccount() {
        username = null;
        password = null;
        users.remove(this);
    }

    public void changeAccountInfo(String username, char[] password) {
        this.username = username;
        this.password = password;
    }

    // Click a button and enter username or password and answer 2 security questions
    // to retrieve login information
    public String forgetUsernamePassword(String username, String password, String security1, String security2){
        int count = 0;
        for(User u : users){
            if(u.getUsername() == username){
                count++;
                return "Username: "+ u.getUsername() + " Password: "+ u.getPassword().toString();
            } else if (u.getPassword().toString() == password) {
                count++;
                return "Username: "+ u.getUsername() + " Password: "+ u.getPassword().toString();
            }
        }
        if(count == 0){
            return "Information not match any account";
        }
        return "";
    }*/

    //method that handles exceptions when user checks out book
    public void bookCheckoutRequirement(Book book) throws BookCheckoutException {
        //if user has not upgraded premium, that means they can only check out 3 books
        if (!isPremium) {
            if (getBooks().size() < 3) {
                if (book.isCheckedOut()) {
                    throw new BookCheckoutException("Sorry. Book already checked out!");
                }
            } else {
                throw new BookCheckoutException("Sorry. You can't check out more than 3 books. Upgrade to premium to check out more books");
            }
        }
        else {
            //if user has upgraded to premium, that means they can check out 6 books
            if (getBooks().size() < 6) {
                if (book.isCheckedOut()) {
                    throw new BookCheckoutException("Sorry. Book already checked out!");
                }
            } else {
                throw new BookCheckoutException("Sorry. You can't check out more than 6 books");
            }
        }
    }

    // User search by type in book name and/or a list of genre
    /*public ArrayList<Book> search(String name, LinkedList<Book.genre> genreList){
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
    }*/
    public void checkout(Book book){
        books.add(book);
        book.setCheckedOut(true);
    }

    public void returnBook(Book book){
        book.setCheckedOut(false);
        books.remove(book);
    }
    /*public void requestionExtension(Book book){
    }
    public void requestSuggestedBooks(ArrayList<Book> Suggestion){

    }
    public ArrayList<Book> checkCheckedOutBook(){
        return this.books;
    }
    public ArrayList<Book> checkCheckedOutBookHistory(){
        return bookhistory;
    }*/

    /*public void buyPremium() {
        // Check if the user has paid first
        Library.givePremium(this);
        isPremium = true;
    }

    //Click a button to get all information of the account
    public String getInformation(){
        return "Username: " + getUsername() + "\nPassword:  " + getPassword();
    }*/

    //Get a list of check out books
    public ArrayList<Book> getBooks() {
        return books;
    }

    //Return user's username
    public String getUsername(){
        return this.username;
    }

    //Return user's password
    public char[] getPassword(){
        return this.password;
    }

    /*public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(char[] password){
        this.password = password;
    }*/

    public boolean hasPremium(){
        return isPremium;
    }

    //User don't set premium status, library sets it for them as seen in method in Library class
    protected void setPremium(Boolean isPremium){
        this.isPremium = isPremium;
    }

    @Override
    public String toString() {
        return "Username: " + username + "; Password: " + String.valueOf(password);
    }
}
