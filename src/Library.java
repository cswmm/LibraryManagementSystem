import jdk.jfr.StackTrace;

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

    // Method to upgrade user privileges once they buy premium
    public static void givePremium(User u){
        // Type case given user to a PremiumUser
        PremiumUser payToWin = (PremiumUser) u;
        u = payToWin;
    }


    // method to add a book to system
    public void addBook(String name, String author, String genre, int year){
        // first check if the book is already in the system
        for (Book b: books){
            if(b.getName().equals(name) && b.getAuthor().equals(author) && b.getGenre().equals(genre) && b.getYear() == year){
                return;
            }
        }
        // else, add it to the database and arraylist
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(
                    "src/books.txt", true));
            // write new book at bottom of txt file
            bw.write(name + "," + author + "," + genre + "," + year + "\n");
            books.add(new Book(name, author, genre, year));
            bw.close();
        } catch (Exception e) {
            return;
        }
    }

    // method to remove a book from the system
    public void removeBook(Book book){
        // first checks if the book is in the system
        if (books.remove(book)){
            // if it is, it will already be removed from the arraylist
            // now, it must be removed from the database
            try {
                // to remove a book from the system, create a temp file and copy all lines but the book to remove
                File newFile = new File("src/books-temp.txt");
                File oldFile = new File("src/books.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(
                        "src/books-temp.txt", true));
                BufferedReader br = new BufferedReader(
                        new FileReader("src/books.txt"));
                String str;
                // check every line
                while ((str = br.readLine()) != null){
                    // separate fields into string array
                    String[] parts = str.split(",");
                    // if the database line is not the exact book to be removed, it will be written to the new file
                    if (parts[0] != book.getName() && parts[1] != book.getAuthor() && parts[2] != book.getGenre()
                    && Integer.parseInt(parts[3]) != book.getYear()){
                        bw.write(str + "\n");
                    }
                }
                br.close();
                bw.close();
                // delete the old file
                oldFile.delete();
                // rename the new file
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
        for (User u: users){
            if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                return;
            }
        }

        // file io- adding a user's username and password to the database (users txt file)
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(
                    "src/users.txt", true));
            // write username and password separated by a comma at the end of the text file
            bw.write(username + "," + String.valueOf(password) + "\n");
            // add to arraylist
            users.add(new User(username, password));
            bw.close();
        } catch (Exception e) {
            return;
        }
    }

    // method to remove a user from the system
    public void removeUser(User user){
        // first checks if the user is in the system
        if (users.remove(user)){
            // if it is, it will already be removed from the arraylist
            // now, it must be removed from the database
            try {
                // create a temp file and copy all lines but the removed user
                File newFile = new File("src/users-temp.txt");
                File oldFile = new File("src/users.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(
                        "src/users-temp.txt", true));
                BufferedReader br = new BufferedReader(
                        new FileReader("src/users.txt"));
                String str;
                // iterate through every line
                while ((str = br.readLine()) != null){
                    // separate each line into parts by a comma
                    String[] parts = str.split(",");
                    // if the database line is not the exact user to be removed, it will be written to the new file
                    if (!parts[0].equals(user.getUsername()) && !parts[1].equals(user.getPassword())){
                        bw.write(str + "\n");
                    }
                }
                br.close();
                bw.close();
                // delete old file
                oldFile.delete();
                // rename new file
                File dump = new File("src/users.txt");
                newFile.renameTo(dump);
            } catch (Exception e){
                e.printStackTrace();
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

    public boolean containsLibrarianUserName(String username) {
        if (librarians != null){
            for (int i = 0; i < librarians.size(); i++){
                if (librarians.get(i).getUsername().equals(username)){
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

    public boolean containsLibrarianPassword(char[] password) {
        if (librarians != null){
            for (int i = 0; i < librarians.size(); i++){
                if (Arrays.equals(librarians.get(i).getPassword(), password)){
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

    public ArrayList<User> getUsers(){
        return users;
    } // Return librarians list

    // method to read from database at start of program
    public void initializeUsers(){
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/users.txt"));
            String str;
            // adds all users in database to arraylist
            while ((str = br.readLine()) != null){
                // converts string into string array of each part separated by a comma as an element
                String[] parts = str.split(",");
                // create a new user and add to arraylist
                users.add(new User(parts[0], parts[1].toCharArray()));
            }
            br.close();
        } catch (Exception e){
            return;
        }
    }

    // method to read from database at start of program
    public void initializeLibrarian(){
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/librarian.txt"));
            String str;
            // adds all librarians in database to arraylist
            while ((str = br.readLine()) != null){
                // converts string into string array of each part separated by a comma as an element
                String[] parts = str.split(",");
                // create a new librarian and add to arraylist
                librarians.add(new Librarian(parts[0], parts[1].toCharArray()));
            }
            br.close();
        } catch (Exception e){
            return;
        }
    }

    // method to read from database at start of program
    public void initializeBooks(){
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/books.txt"));
            String str;
            // adds all books in database to arraylist
            while ((str = br.readLine()) != null){
                // converts string into string array of each part separated by a comma as an element
                String[] parts = str.split(",");
                // create a new book and add to arraylist
                books.add(new Book(parts[0], parts[1], parts[2], Integer.parseInt(parts[3])));
            }
            br.close();
        } catch (Exception e){
            return;
        }
    }
}
