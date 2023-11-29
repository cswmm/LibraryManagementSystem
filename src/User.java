import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.io.*;

public class User extends Library {
    private String username;
    private char[] password;
    private boolean isPremium = false; // User initially not Premium User

    //stores books that user checked out
    private ArrayList<Book> books;

    public User(){
    }
    public User(String username, char[] password) {
        this.username = username;
        this.password = password;
        this.books = new ArrayList<>();
    }

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

    public void checkout(Book book){
        books.add(book);
        book.setCheckedOut(true);
    }

    public void returnBook(Book book){
        book.setCheckedOut(false);
        books.remove(book);
    }

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
