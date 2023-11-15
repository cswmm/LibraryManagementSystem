import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Library {
    public ArrayList<User> users;
    protected ArrayList<Librarian> librarians;
    public ArrayList<Book> books;
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


    // method to add a book to system
    public void addBook(String name, String author, String genre, int year){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(
                    "src/books.txt", true));
            bw.write(name + "," + author + "," + genre + "," + year + "\n");
            books.add(new Book(name, author, genre, year));
            bw.close();
        } catch (Exception e) {
            return;
        }
    }

    public void removeBook(Book book){
        if (books.remove(book)){
            try {
                File newFile = new File("src/books-temp.txt");
                File oldFile = new File("src/books.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(
                        "src/books-temp.txt", true));
                BufferedReader br = new BufferedReader(
                        new FileReader("src/books.txt"));
                String str;
                while ((str = br.readLine()) != null){
                    String[] parts = str.split(",");
                    if (parts[0] != book.getName() && parts[1] != book.getAuthor() && parts[2] != book.getGenre()
                    && Integer.parseInt(parts[3]) != book.getYear()){
                        bw.write(str + "\n");
                    }
                }
                br.close();
                bw.close();
                oldFile.delete();
                File dump = new File("src/books.txt");
                newFile.renameTo(dump);
            } catch (Exception e){
                return;
            }
        }
    }
    // method to add a user to the system
    // called after a user enters their information in signup page
    public void addUser(String username, char[] password, String security1, String security2) throws PasswordException {
        // file io- adding a user's username and password to the database (users txt file)
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(
                    "src/users.txt", true));
            bw.write(username + "," + String.valueOf(password) + "\n");
            users.add(new User(username, password, security1, security2));
            bw.close();
        } catch (Exception e) {
            return;
        }
    }

    public void removeUser(User user){
        if (users.remove(user)){
            try {
                File newFile = new File("src/users-temp.txt");
                File oldFile = new File("src/users.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(
                        "src/users-temp.txt", true));
                BufferedReader br = new BufferedReader(
                        new FileReader("src/users.txt"));
                String str;
                while ((str = br.readLine()) != null){
                    String[] parts = str.split(",");
                    if (parts[0] != user.getUsername() && parts[1].toCharArray() != user.getPassword()){
                        bw.write(str + "\n");
                    }
                }
                br.close();
                bw.close();
                oldFile.delete();
                File dump = new File("src/users.txt");
                newFile.renameTo(dump);
            } catch (Exception e){
                return;
            }
        }
    }

    public User getUser(String username, char[] password){
        for (int i = 0; i < users.size(); i++){
            if (users.get(i).getUsername().equals(username) && Arrays.equals(users.get(i).getPassword(), password)){
                return users.get(i);
            }
        }
        return null;
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

    public void initializeUsers(){
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/users.txt"));
            String str;
            while ((str = br.readLine()) != null){
                String[] parts = str.split(",");
                users.add(new User(parts[0], parts[1].toCharArray(), null, null));
            }
            br.close();
        } catch (Exception e){
            return;
        }
    }

    public void initializeBooks(){
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/books.txt"));
            String str;
            while ((str = br.readLine()) != null){
                String[] parts = str.split(",");
                books.add(new Book(parts[0], parts[1], parts[2], Integer.parseInt(parts[3])));
            }
            br.close();
        } catch (Exception e){
            return;
        }
    }
}
