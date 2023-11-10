import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Librarian extends User{

    public Librarian(String username, String password, String security1, String security2){
        super(username, password, security1, security2);
    }

    public void addBook(Book book){
        for(Book b : getBooks()){
            if(b != book){
                getBooks().add(book);
            } else {
                System.out.println("Book already in the library system"); // GUI message
            }
        }
    }

    public void removeBook(Book book){
        if(getBooks().contains(book)) {
            getBooks().remove(book);
            System.out.println("Book has been remove from the library system"); // GUI message
        } else {
            System.out.println("Book is not in the library system"); // GUI message
        }
    }

    // Librarian type in the username of the user they want to remove
    public void removeUser(String username){
        if(users.containsKey(username)){
            users.remove(username);
        } else {
            System.out.println("User not existed, please try again"); // GUI message
        }
    }

    public ArrayList<Book> checkUserCheckedOut(User user){
        return user.getBooks();
    }

    public ArrayList<Book> checkSuggestions(){
        return requestBook;
    }

    // Librarian type in book name and a list of genre they would like to add to that book
    public void addBookGenre(String name, LinkedList<Book.genre> genrelist){
        for(int i = 0; i < getBooks().size(); i++){
            if(Objects.equals(getBooks().get(i).getName(), name)){
                getBooks().get(i).getGenreList().addAll(genrelist);
            }
        }
    }

}
