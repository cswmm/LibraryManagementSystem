import java.util.ArrayList;

public class Library {
    public static ArrayList<User> users;
    protected ArrayList<Librarian> librarians;
    protected ArrayList<Book> books;

    public static void givePremium(User u){
        PremiumUser payToWin = (PremiumUser) u;
        u = payToWin;
    }

    // method to add a user to the system
    // called after a user enters their information in signup page
    public static void addUser(String username, char[] password) throws PasswordException{
        //Throw exception if password is less than 8 characters
        if (password.length < 8) {
            throw new Minimum8CharactersRequired();
        }
        //Create flag for exception
        boolean flag = true;
        //Check if password has an upper case char
        for (int i = 0; i < password.length; i++) {
            if (Character.isUpperCase(password[i])) {
                flag = false;
            }
        }
        if (flag) {
            PasswordException UpperCaseCharacterMissing = new UpperCaseCharacterMissing();
            throw UpperCaseCharacterMissing;
        }
        flag = true;
        //check for lower case character
        for (int i = 0; i < password.length; i++) {
            if (Character.isLowerCase(password[i])) {
                flag = false;
            }
        }
        if (flag) {
            PasswordException LowerCaseCharacterMissing = new LowerCaseCharacterMissing();
            throw LowerCaseCharacterMissing;
        }
        flag = true;
        //check if there is a special character
        for (int i = 0; i < password.length; i++) {
            if (!Character.isDigit(password[i]) && !Character.isLetter(password[i])
                    && !Character.isWhitespace(password[i])) {
                flag = false;
            }
        }
        if (flag) {
            PasswordException SpecialCharacterMissing = new SpecialCharacterMissing();
            throw SpecialCharacterMissing;
        }
        flag = true;
        //check for a number character
        for (int i = 0; i < password.length; i++) {
            if (Character.isDigit(password[i])) {
                flag = false;
            }
        }
        if (flag) {
            PasswordException NumberCharacterMissing = new NumberCharacterMissing();
            throw NumberCharacterMissing;
        }
        User u = new User(username, password.toString());
        users.add(u);
    }
}
