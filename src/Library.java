import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Library {
    public static ArrayList<User> users;
    protected ArrayList<Librarian> librarians;
    protected ArrayList<Book> books;
    protected ArrayList<Book> requestBook;

    public Library(){
        users = new ArrayList<>();
        librarians = new ArrayList<>();
        books = new ArrayList<>();
        requestBook = new ArrayList<>();
    }

    public static void givePremium(User u){
        PremiumUser payToWin = (PremiumUser) u;
        u = payToWin;
    }

    // method to add a user to the system
    // called after a user enters their information in signup page
    public void addUser(String username, char[] password, String security1, String security2) throws PasswordException {
        User u = new User(username, password, security1, security2);
        users.add(u);
    }

    public void passwordRequirement(char[] password) throws PasswordException {
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasSpecialChar = false;
        boolean hasNumber = false;

        if (password.length < 8) {
            throw new Minimum8CharactersRequired();
        }
        else {
            for (int i = 0; i < password.length; i++){
                char c = password[i];
                if (!Character.isDigit(c) && !Character.isLetter(c) && !Character.isWhitespace(c)){
                    hasSpecialChar = true;
                }
                if (Character.isDigit(c)){
                    hasNumber = true;
                }
                if (Character.isUpperCase(c)){
                    hasUpperCase = true;
                }
                if (Character.isLowerCase(c)){
                    hasLowerCase = true;
                }
            }
        }

        if (!hasSpecialChar) {
            throw new SpecialCharacterMissing();
        }
        if (!hasNumber) {
            throw new NumberCharacterMissing();
        }
        if (!hasUpperCase) {
            throw new UpperCaseCharacterMissing();
        }
        if (!hasLowerCase) {
            throw new LowerCaseCharacterMissing();
        }

    }

    public boolean containsUserName(String username) {
        if (users != null){
            for (int i = 0; i < users.size(); i++){
                if (users.get(i).getUsername().equals(username)){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean containsPassword(char[] password) {
        if (users != null){
            for (int i = 0; i < users.size(); i++){
                if (Arrays.equals(users.get(i).getPassword(), password)){
                    return true;
                }
            }
        }

        return false;
    }

    public ArrayList<Book> getBooks(){ // Get list of book in library system
        return books;
    }

    public ArrayList<Librarian> getLibrarians(){
        return librarians;
    } // Return librarians list

}
