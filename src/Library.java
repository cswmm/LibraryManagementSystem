import java.util.ArrayList;

public class Library {
    public static ArrayList<User> users;
    protected ArrayList<Librarian> librarians;
    protected ArrayList<Book> books;

    public static void givePremium(User u){
        PremiumUser payToWin = (PremiumUser) u;
        u = payToWin;
    }
}
