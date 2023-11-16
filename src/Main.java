import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends JFrame implements ActionListener {
    private JPanel panel;
    private JPanel newPanel; // New panel for a fresh screen
    private boolean inUserLoginPanel = false;
    private Library library = new Library();
    JTextField usernameField;
    JPasswordField passwordField;
    JTextField enterUsernameField;
    JPasswordField enterPasswordField;
    JTextField enterLibrarianUsernameField;
    JPasswordField enterLibrarianPasswordField;

    private JButton optionsButton;
    private String password;
    private String userName;

    JButton searchButton;
    JButton goButton;

    JTextField searchField;

    private boolean searchByGenre = false;

    ArrayList<Book>results = new ArrayList<>();

    JPopupMenu popupMenu2;


    public static void main(String[] args) {
        Main app = new Main();
        app.setVisible(true);
    }

    public Main() {
        library.initializeUsers();
        library.initializeBooks();
        System.out.println(library.users);
        System.out.println(library.books);
        Librarian admin = new Librarian("Mr. Librarian", "admin".toCharArray(), "", "");
        library.librarians.add(admin);
        this.setTitle("1337h4x0r.library.sjsu.ca.gov");
        this.setSize(600, 400);
        panel = startScreenPanel();
        this.add(panel);
    }

    //assigns actions to buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Log In")) {
            newPanel = createLoginPanel();
        } else if (e.getActionCommand().equals("Sign Up")) {
            newPanel = createSignupPanel();
        } else if (e.getActionCommand().equals("Go Back")) {
            if (inUserLoginPanel){
                newPanel = createLoginPanel();
            }
            else {
                newPanel = startScreenPanel();
            }
        } else if (e.getActionCommand().equals("User")) {
            newPanel = createUserLoginPanel();
        } else if (e.getActionCommand().equals("Admin")) {
            newPanel = createAdminLoginPanel();
        } else if (e.getActionCommand().equals("Sign-Up")){
            try {
                System.out.println("Signed Up Fields");
                //print statements so you remember user-name and password
                System.out.println("Username: " + usernameField.getText());
                System.out.println(passwordField.getPassword());
                library.passwordRequirement(passwordField.getPassword());
                //creates and adds user to library
                library.addUser(usernameField.getText(), passwordField.getPassword(), "", "");
                password = String.valueOf(passwordField.getPassword());
                userName = usernameField.getText();
                JOptionPane.showMessageDialog(null, "Sign In Successful!");

            } catch (PasswordException ex) {
                JOptionPane.showMessageDialog(null, "Password error: " + ex.getMessage());
            }
        } else if (e.getActionCommand().equals("Log-In")) {
            //print statements to check if you entered correct user-name and password
            System.out.println("Entered Fields");
            System.out.println("Username: " + enterUsernameField.getText());
            System.out.println(enterPasswordField.getPassword());
            //creates new unique user panel
            if (library.containsUserName(enterUsernameField.getText()) && library.containsPassword(enterPasswordField.getPassword())){
                User u = library.getUser(userName, password.toCharArray());
                if (u.hasPremium()){
                    newPanel = createPremiumUserPagePanel();
                } else {
                    newPanel = createUserPagePanel();
                }
            }
            else {
                System.out.println("Invalid");
            }

        } else if (e.getActionCommand().equals("Log-In [A]")) {
            if (enterLibrarianUsernameField.getText().equals("Mr. Librarian") && Arrays.equals(enterLibrarianPasswordField.getPassword(), "admin".toCharArray())){
                newPanel = createLibrarianPanel();
            }
        }
        else if (e.getActionCommand().equals("Options")) {
            //creates drop menu when options menu is pressed in user panel
            User u = library.getUser(userName, password.toCharArray());

            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem menuItem1 = new JMenuItem("Account Info");
            JMenuItem menuItem2 = new JMenuItem("Buy Premium");
            JMenuItem menuItem3 = new JMenuItem("Log Out");

            menuItem1.addActionListener(this);
            menuItem2.addActionListener(this);
            menuItem3.addActionListener(this);

            popupMenu.add(menuItem1);

            if (!u.hasPremium()){
                popupMenu.add(menuItem2);
            }
            popupMenu.add(menuItem3);

            popupMenu.show(optionsButton, 0, optionsButton.getHeight());

        } else if (e.getActionCommand().equals("Log Out")){
            newPanel = startScreenPanel();
        } else if (e.getActionCommand().equals("Account Info")){
            String enterPassword = JOptionPane.showInputDialog("Enter password to see account information");
            if (enterPassword.equals(password)){
                //displays username and password, add library card number
                showUserProfile(library.getUser(userName, password.toCharArray()));
            }
        } else if (e.getActionCommand().equals("Buy Premium")) {
            String upgradeToPremium = JOptionPane.showInputDialog("Upgrade to premium for $5 a month. Enter your password");
            if (upgradeToPremium.equals(password)){
                library.getUser(userName, password.toCharArray()).setPremium(true);
                JOptionPane.showMessageDialog(null, "Welcome to the premium club. Sign in again to access your premium account");
            }
        } else if (e.getActionCommand().equals("Search")) {
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem menuItem1 = new JMenuItem("Name");
            JMenuItem menuItem2 = new JMenuItem("Author");
            JMenuItem menuItem3 = new JMenuItem("Genre");
            JMenuItem menuItem4 = new JMenuItem("Year");

            menuItem1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    searchButton.setText("Search by Name");
                }
            });

            menuItem2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    searchButton.setText("Search by Author");
                }
            });

            menuItem3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    searchByGenre = true;
                    searchButton.setText("Search by Genre");
                }
            });

            menuItem4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    searchButton.setText("Search by Year");
                }
            });

            popupMenu.add(menuItem1);
            popupMenu.add(menuItem2);
            popupMenu.add(menuItem3);
            popupMenu.add(menuItem4);

            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Show the drop-down menu when the search button is clicked
                    popupMenu.show(searchButton, 0, searchButton.getHeight());
                };
            });
        } else if (e.getActionCommand().equals("Go")){
            System.out.println("Detected");
            results.clear();

            if (searchButton.getText().equals("Search by Genre")){
                for (int i = 0; i < library.books.size(); i++){
                    if (library.books.get(i).getGenre().equals(searchField.getText())){
                        System.out.println("Added to result list");
                        results.add(library.books.get(i));
                    }
                }

                popupMenu2 = new JPopupMenu();

                for (int i = 0; i < results.size(); i++){
                    JMenuItem menuItemOne = new JMenuItem(results.get(i).toString());
                    menuItemOne.setPreferredSize(new java.awt.Dimension(300, 20));
                    int finalI = i;
                    menuItemOne.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //add check out button to J Option pane that adds to users check out list
                            showBookInfo(results.get(finalI), library.getUser(userName, password.toCharArray()));
                        }
                    });
                    popupMenu2.add(menuItemOne);
                }
                System.out.println(results.size());

                popupMenu2.show(searchField, 0, searchField.getHeight());

            }
            if (searchButton.getText().equals("Search by Name")){
                for (int i = 0; i < library.books.size(); i++){
                    if (library.books.get(i).getName().equals(searchField.getText())){
                        System.out.println("Added to result list");
                        results.add(library.books.get(i));
                    }
                }

                popupMenu2 = new JPopupMenu();

                for (int i = 0; i < results.size(); i++){
                    JMenuItem menuItemOne = new JMenuItem(results.get(i).toString());
                    menuItemOne.setPreferredSize(new java.awt.Dimension(300, 20));
                    int finalI = i;
                    menuItemOne.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //add check out button to J Option pane that adds to users check out list
                            showBookInfo(results.get(finalI), library.getUser(userName, password.toCharArray()));
                        }
                    });
                    popupMenu2.add(menuItemOne);
                }
                System.out.println(results.size());

                popupMenu2.show(searchField, 0, searchField.getHeight());

            }
            if (searchButton.getText().equals("Search by Author")){
                for (int i = 0; i < library.books.size(); i++){
                    if (library.books.get(i).getAuthor().equals(searchField.getText())){
                        System.out.println("Added to result list");
                        results.add(library.books.get(i));
                    }
                }

                popupMenu2 = new JPopupMenu();

                for (int i = 0; i < results.size(); i++){
                    JMenuItem menuItemOne = new JMenuItem(results.get(i).toString());
                    menuItemOne.setPreferredSize(new java.awt.Dimension(300, 20));
                    int finalI = i;
                    menuItemOne.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //add check out button to J Option pane that adds to users check out list
                            showBookInfo(results.get(finalI), library.getUser(userName, password.toCharArray()));
                        }
                    });
                    popupMenu2.add(menuItemOne);
                }
                System.out.println(results.size());

                popupMenu2.show(searchField, 0, searchField.getHeight());

            }
            if (searchButton.getText().equals("Search by Year")){
                for (int i = 0; i < library.books.size(); i++){
                    if (String.valueOf(library.books.get(i).getYear()).equals(searchField.getText())){
                        System.out.println("Added to result list");
                        results.add(library.books.get(i));
                    }
                }

                popupMenu2 = new JPopupMenu();

                for (int i = 0; i < results.size(); i++){
                    JMenuItem menuItemOne = new JMenuItem(results.get(i).toString());
                    menuItemOne.setPreferredSize(new java.awt.Dimension(300, 20));
                    int finalI = i;
                    menuItemOne.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //add check out button to J Option pane that adds to users check out list
                            showBookInfo(results.get(finalI), library.getUser(userName, password.toCharArray()));
                        }
                    });
                    popupMenu2.add(menuItemOne);
                }
                System.out.println(results.size());

                popupMenu2.show(searchField, 0, searchField.getHeight());

            }

        } else if(e.getActionCommand().equals("Add Book")){ // Librarian-Add Book
            newPanel = createAddBookPanel();
        } else if(e.getActionCommand().equals("Remove Book")){ // Librarian-Remove Book
            newPanel = createRemoveBookPanel();
        } else if(e.getActionCommand().equals("Add User")){// Librarian Add User
            newPanel = createAddUserPanel();
        } else if(e.getActionCommand().equals("Remove User")){// Remove User
            newPanel = createRemoveUserPanel();
        } else if(e.getActionCommand().equals("Show User List")){// Show User List
            newPanel = createShowUserListPanel();
        }

        this.remove(panel);
        panel = newPanel;
        this.add(panel);

        this.revalidate();
        this.repaint();
    }

    private JPanel createLibrarianPanel() {
        inUserLoginPanel = false;
        Rectangle rectangle = new Rectangle(0, 0, 600, 80);

        JPanel panel = createUpperBorderDisplay(rectangle, new Color(197, 160, 242));
        panel.setLayout(null);

        JLabel applicationLabel = createApplicationLabel(new Color(169, 138, 208));

        JLabel nameLabel = new JLabel("Librarian ");
        nameLabel.setBounds(250, 100, 400, 60);
        nameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        nameLabel.setForeground(Color.white);

        optionsButton = new JButton("Log Out"); // Change from Options to Log out
        optionsButton.setBounds(475, 15, 100, 50);
        optionsButton.addActionListener(this);

        // Add buttons, each button is a function
        JButton addBookButton = new JButton("Add Book");
        addBookButton.setBounds(200,170,200,50);
        addBookButton.addActionListener(this);

        JButton removeBookButton = new JButton("Remove Book");
        removeBookButton.setBounds(200,240,200,50);
        removeBookButton.addActionListener(this);

        JButton removeUserButton = new JButton("Add User");
        removeUserButton.setBounds(200,310,200,50);
        removeUserButton.addActionListener(this);

        JButton checkRequestButton = new JButton("Remove User");
        checkRequestButton.setBounds(200,380,200,50);
        checkRequestButton.addActionListener(this);

        JButton checkUserCheckOutButton = new JButton("Show User List");
        checkUserCheckOutButton.setBounds(200,450,200,50);
        checkUserCheckOutButton.addActionListener(this);

       /* JButton addNewAdminButton = new JButton("Add Admin");
        addNewAdminButton.setBounds(200,520,200,50);
        addNewAdminButton.add(this); */

        panel.add(addBookButton);
        panel.add(removeBookButton);
        panel.add(removeUserButton);
        panel.add(checkRequestButton);
        panel.add(checkUserCheckOutButton);
       // panel.add(addNewAdminButton);
        //End of add buttons


        panel.add(applicationLabel);
        panel.add(nameLabel);
        panel.add(optionsButton);

        return panel;
    }

    private JPanel startScreenPanel(){
        inUserLoginPanel = false;
        Rectangle rectangle = new Rectangle(0, 0, 600, 80);

        JPanel panel = createUpperBorderDisplay(rectangle, new Color(160, 235, 242));
        panel.setLayout(null);

        JLabel applicationLabel = createApplicationLabel(new Color(122, 183, 189));

        JButton loginButton = new JButton("Log In");
        loginButton.setBounds(360, 15, 100, 50);

        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(475, 15, 100, 50);

        signupButton.addActionListener(this);
        loginButton.addActionListener(this);

        panel.add(applicationLabel);
        panel.add(loginButton);
        panel.add(signupButton);

        return panel;
    }

    //user page when you log in
    private JPanel createUserPagePanel() {
        inUserLoginPanel = false;
        Rectangle rectangle = new Rectangle(0, 0, 600, 80);

        JPanel panel = createUpperBorderDisplay(rectangle, new Color(197, 160, 242));
        panel.setLayout(null);

        JLabel applicationLabel = createApplicationLabel(new Color(169, 138, 208));

        JLabel nameLabel = new JLabel("Welcome " + usernameField.getText() + "!");
        nameLabel.setBounds(250, 100, 400, 60);
        nameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        nameLabel.setForeground(Color.white);

        optionsButton = new JButton("Options");
        optionsButton.setBounds(475, 15, 100, 50);
        optionsButton.addActionListener(this);

        searchField = new JTextField(20);
        searchField.setBounds(215, 200, 265, 60);
        searchButton = new JButton("Search");
        searchButton.setBounds(100, 200, 120, 60);
        searchButton.addActionListener(this);
        goButton = new JButton("Go");
        goButton.setBounds(475, 200, 60, 60);
        goButton.addActionListener(this);


        panel.add(applicationLabel);
        panel.add(nameLabel);
        panel.add(optionsButton);
        panel.add(searchField);
        panel.add(searchButton);
        panel.add(goButton);

        return panel;


    }

    //premium user page when you log in
    private JPanel createPremiumUserPagePanel() {
        inUserLoginPanel = false;
        Rectangle rectangle = new Rectangle(0, 0, 600, 80);

        JPanel panel = createUpperBorderDisplay(rectangle, new Color(242, 206, 160));
        panel.setLayout(null);

        JLabel applicationLabel = createApplicationLabel(new Color(206, 166, 105));

        JLabel nameLabel = new JLabel("Premium User " + userName + "!");
        nameLabel.setBounds(250,100,400,60);
        nameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        nameLabel.setForeground(Color.white);

        optionsButton = new JButton("Options");
        optionsButton.setBounds(475, 15, 100, 50);
        optionsButton.addActionListener(this);

        searchField = new JTextField(20);
        searchField.setBounds(215, 200, 265, 60);
        searchButton = new JButton("Search");
        searchButton.setBounds(100, 200, 120, 60);
        searchButton.addActionListener(this);
        goButton = new JButton("Go");
        goButton.setBounds(475, 200, 60, 60);
        goButton.addActionListener(this);


        panel.add(applicationLabel);
        panel.add(nameLabel);
        panel.add(optionsButton);
        panel.add(searchField);
        panel.add(searchButton);
        panel.add(goButton);

        return panel;
    }


    private JPanel createLoginPanel() {
        inUserLoginPanel = false;
        Rectangle rectangle = new Rectangle(0, 0, 600, 80);

        JPanel loginPanel = createUpperBorderDisplay(rectangle, new Color(163, 242, 160));
        loginPanel.setLayout(null);

        JLabel applicationLabel = createApplicationLabel(new Color(130, 192, 128));
        JButton backButton = createBackButton();

        JButton adminstratorButton = new JButton("Admin");
        adminstratorButton.setBounds(310,150,160,120);
        adminstratorButton.addActionListener(this);

        JButton userButton = new JButton("User");
        userButton.setBounds(135,150,160,120);
        userButton.addActionListener(this);

        loginPanel.add(backButton);
        loginPanel.add(applicationLabel);
        loginPanel.add(adminstratorButton);
        loginPanel.add(userButton);
        return loginPanel;
    }

    private JPanel createUserLoginPanel(){
        inUserLoginPanel = true;
        Rectangle rectangle = new Rectangle(0, 0, 600, 80);

        JPanel userLoginPanel = createUpperBorderDisplay(rectangle, new Color(242, 197, 160));
        userLoginPanel.setLayout(null);

        JLabel applicationLabel = createApplicationLabel(new Color(204, 167, 136));

        JButton backButton = createBackButton();

        JButton loginButton = new JButton("Log-In");
        loginButton.setBounds(220, 280, 90, 45);
        loginButton.addActionListener(this);

        JLabel loginLabel = new JLabel("Log In");
        loginLabel.setBounds(218, 100, 100, 40);
        loginLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        loginLabel.setForeground(new Color(246, 243, 243));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(220, 145, 100, 30);

        enterUsernameField = new JTextField();
        enterUsernameField.setBounds(215, 175, 200, 30);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(220, 205, 100, 30);

        enterPasswordField = new JPasswordField();
        enterPasswordField.setBounds(215, 235, 200, 30);

        userLoginPanel.add(backButton);
        userLoginPanel.add(applicationLabel);
        userLoginPanel.add(usernameLabel);
        userLoginPanel.add(enterUsernameField);
        userLoginPanel.add(passwordLabel);
        userLoginPanel.add(enterPasswordField);
        userLoginPanel.add(loginLabel);
        userLoginPanel.add(loginButton);
        return userLoginPanel;
    }

    private JPanel createAdminLoginPanel(){
        inUserLoginPanel = true;
        Rectangle rectangle = new Rectangle(0, 0, 600, 80);

        JPanel adminLoginPanel = createUpperBorderDisplay(rectangle, new Color(242, 197, 160));
        adminLoginPanel.setLayout(null);

        JLabel applicationLabel = createApplicationLabel(new Color(204, 167, 136));

        JButton backButton = createBackButton();

        JButton loginButton = new JButton("Log-In [A]");
        loginButton.setBounds(220, 280, 90, 45);
        loginButton.addActionListener(this);

        JLabel loginLabel = new JLabel("Log In [Admin]");
        loginLabel.setBounds(218, 100, 170, 40);
        loginLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        loginLabel.setForeground(new Color(246, 243, 243));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(220, 145, 100, 30);

        enterLibrarianUsernameField = new JTextField();
        enterLibrarianUsernameField.setBounds(215, 175, 200, 30);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(220, 205, 100, 30);

        enterLibrarianPasswordField = new JPasswordField();
        enterLibrarianPasswordField.setBounds(215, 235, 200, 30);

        adminLoginPanel.add(backButton);
        adminLoginPanel.add(applicationLabel);
        adminLoginPanel.add(usernameLabel);
        adminLoginPanel.add(enterLibrarianUsernameField);
        adminLoginPanel.add(passwordLabel);
        adminLoginPanel.add(enterLibrarianPasswordField);
        adminLoginPanel.add(loginLabel);
        adminLoginPanel.add(loginButton);
        return adminLoginPanel;
    }

    private JPanel createSignupPanel() {
        inUserLoginPanel = false;

        Rectangle rectangle = new Rectangle(0, 0, 600, 80);

        JPanel signupPanel = createUpperBorderDisplay(rectangle, new Color(163, 242, 160));
        signupPanel.setLayout(null);

        JLabel applicationLabel = createApplicationLabel(new Color(130, 192, 128));
        JButton backButton = createBackButton();

        JButton signUpButton = new JButton("Sign-Up");
        signUpButton.setBounds(220, 280, 90, 45);
        signUpButton.addActionListener(this);

        JLabel signUpLabel = new JLabel("Sign Up");
        signUpLabel.setBounds(220, 100, 170, 40);
        signUpLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        signUpLabel.setForeground(new Color(246, 243, 243));

        JLabel usernameLabel = new JLabel("Enter Username:");
        usernameLabel.setBounds(220, 145, 150, 30);

        usernameField = new JTextField();
        usernameField.setBounds(215, 175, 200, 30);

        JLabel passwordLabel = new JLabel("Enter Password:");
        passwordLabel.setBounds(220, 205, 100, 30);

        passwordField = new JPasswordField();
        passwordField.setBounds(215, 235, 200, 30);

        signupPanel.add(backButton);
        signupPanel.add(applicationLabel);
        signupPanel.add(signUpLabel);
        signupPanel.add(usernameLabel);
        signupPanel.add(usernameField);
        signupPanel.add(passwordLabel);
        signupPanel.add(passwordField);
        signupPanel.add(signUpButton);
        return signupPanel;
    }
    private JPanel createAddBookPanel(){
        inUserLoginPanel = false;
        Rectangle rectangle = new Rectangle(0, 0, 600, 80);

        JPanel panel = createUpperBorderDisplay(rectangle, new Color(197, 160, 242));
        panel.setLayout(null);

        JLabel applicationLabel = createApplicationLabel(new Color(169, 138, 208));

        JLabel nameLabel = new JLabel("Add Book");
        nameLabel.setBounds(250, 100, 400, 60);
        nameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        nameLabel.setForeground(Color.white);

        optionsButton = new JButton("Go Back"); // Go Back button
        optionsButton.setBounds(475, 15, 100, 50);
        optionsButton.addActionListener(this);

        JLabel bookNameLabel = new JLabel("Enter Book Name:");
        bookNameLabel.setBounds(210, 145, 150, 30);

        JTextField bookNameField = new JTextField();
        bookNameField.setBounds(205, 175, 200, 30);

        JLabel bookAuthorLabel = new JLabel("Enter Author:");
        bookAuthorLabel.setBounds(210, 205, 150, 30);

        JTextField bookAuthorField = new JTextField();
        bookAuthorField.setBounds(205, 235, 200, 30);

        JLabel bookGenreLabel = new JLabel("Enter Genre:");
        bookGenreLabel.setBounds(210,265,150,30);

        JTextField bookGenreField = new JTextField();
        bookGenreField.setBounds(205,295,200,30);

        JLabel bookYearLabel = new JLabel("Enter Year:");
        bookYearLabel.setBounds(210,325,150,30);

        JTextField bookYearField = new JTextField();
        bookYearField.setBounds(205,355,200,30);

        JButton addBookButton = new JButton("Add Book");
        addBookButton.setBounds(220, 400, 90, 45);
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = 0;
                String bookName = bookNameField.getText();
                String bookAuthor = bookAuthorField.getText();
                String bookGenre = bookGenreField.getText();
                int bookYear = Integer.parseInt(bookYearField.getText());
                for(Book book : library.getBooks()) {
                    if(bookName == book.getName() && bookAuthor == book.getAuthor() && bookGenre == book.getGenre() && bookYear == book.getYear()) {
                        count++;
                    }
                }
                if(count == 0){
                    library.addBook(bookName, bookAuthor, bookGenre, bookYear);
                    JOptionPane.showMessageDialog(null,"Success add book to library system");
                } else {
                    JOptionPane.showMessageDialog(null,"Book already in library system");
                }
            }
        });


        panel.add(applicationLabel);
        panel.add(nameLabel);
        panel.add(optionsButton);
        panel.add(bookNameLabel);
        panel.add(bookAuthorLabel);
        panel.add(bookGenreLabel);
        panel.add(bookYearLabel);
        panel.add(bookNameField);
        panel.add(bookAuthorField);
        panel.add(bookGenreField);
        panel.add(bookYearField);
        panel.add(addBookButton);

        return panel;
    }

    private JPanel createRemoveBookPanel(){
        inUserLoginPanel = false;
        Rectangle rectangle = new Rectangle(0, 0, 600, 80);

        JPanel panel = createUpperBorderDisplay(rectangle, new Color(197, 160, 242));
        panel.setLayout(null);

        JLabel applicationLabel = createApplicationLabel(new Color(169, 138, 208));

        JLabel nameLabel = new JLabel("Remove Book");
        nameLabel.setBounds(250, 100, 400, 60);
        nameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        nameLabel.setForeground(Color.white);

        optionsButton = new JButton("Go Back"); // Go Back button
        optionsButton.setBounds(475, 15, 100, 50);
        optionsButton.addActionListener(this);
        JLabel bookNameLabel = new JLabel("Enter Book Name:");
        bookNameLabel.setBounds(210, 145, 150, 30);

        JTextField bookNameField = new JTextField();
        bookNameField.setBounds(205, 175, 200, 30);

        JLabel bookAuthorLabel = new JLabel("Enter Author:");
        bookAuthorLabel.setBounds(210, 205, 150, 30);

        JTextField bookAuthorField = new JTextField();
        bookAuthorField.setBounds(205, 235, 200, 30);

        JLabel bookGenreLabel = new JLabel("Enter Genre:");
        bookGenreLabel.setBounds(210,265,150,30);

        JTextField bookGenreField = new JTextField();
        bookGenreField.setBounds(205,295,200,30);

        JLabel bookYearLabel = new JLabel("Enter Year:");
        bookYearLabel.setBounds(210,325,150,30);

        JTextField bookYearField = new JTextField();
        bookYearField.setBounds(205,355,200,30);

        JButton addBookButton = new JButton("Remove Book");
        addBookButton.setBounds(220, 400, 90, 45);
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = 0;
                String bookName = bookNameField.getText();
                String bookAuthor = bookAuthorField.getText();
                String bookGenre = bookGenreField.getText();
                int bookYear = Integer.parseInt(bookYearField.getText());
                for(Book book : library.getBooks()) {
                    if(bookName == book.getName() && bookAuthor == book.getAuthor() && bookGenre == book.getGenre() && bookYear == book.getYear()) {
                        count++;
                        library.removeBook(new Book(bookName,bookAuthor,bookGenre,bookYear));
                        JOptionPane.showMessageDialog(null,"Success remove book from library system");
                    }
                }
                if(count == 0){
                    JOptionPane.showMessageDialog(null,"Book not in library system");
                }

            }
        });

        panel.add(applicationLabel);
        panel.add(nameLabel);
        panel.add(optionsButton);
        panel.add(bookNameLabel);
        panel.add(bookAuthorLabel);
        panel.add(bookGenreLabel);
        panel.add(bookYearLabel);
        panel.add(bookNameField);
        panel.add(bookAuthorField);
        panel.add(bookGenreField);
        panel.add(bookYearField);
        panel.add(addBookButton);

        return panel;
    }

    private JPanel createAddUserPanel(){
        inUserLoginPanel = false;
        Rectangle rectangle = new Rectangle(0, 0, 600, 80);

        JPanel panel = createUpperBorderDisplay(rectangle, new Color(197, 160, 242));
        panel.setLayout(null);

        JLabel applicationLabel = createApplicationLabel(new Color(169, 138, 208));

        JLabel nameLabel = new JLabel("Add User");
        nameLabel.setBounds(250, 100, 400, 60);
        nameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        nameLabel.setForeground(Color.white);

        optionsButton = new JButton("Go Back"); // Go Back button
        optionsButton.setBounds(475, 15, 100, 50);
        optionsButton.addActionListener(this);

        JLabel usernameLLabel = new JLabel("Enter Username:");
        usernameLLabel.setBounds(210, 145, 150, 30);

        JTextField usernameLField = new JTextField();
        usernameLField.setBounds(205, 175, 200, 30);

        JLabel passwordLLabel = new JLabel("Enter Password:");
        passwordLLabel.setBounds(210, 205, 150, 30);

        JPasswordField passwordLField = new JPasswordField();
        passwordLField.setBounds(205, 235, 200, 30);

        JLabel security1LLabel = new JLabel("Enter security 1: What is your mother's maiden name?");
        security1LLabel.setBounds(210,265,300,30);

        JTextField security1LField = new JTextField();
        security1LField.setBounds(205,295,200,30);

        JLabel security2LLabel = new JLabel("Enter security 2: What is the name of your first pet?");
        security2LLabel.setBounds(210,325,300,30);

        JTextField security2LField = new JTextField();
        security2LField.setBounds(205,355,200,30);

        JButton addUserButton = new JButton("Add User");
        addUserButton.setBounds(220, 400, 90, 45);
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(library.containsUserName(usernameLField.getText())){
                        JOptionPane.showMessageDialog(null, "Username existed, please choose another username");
                    }
                    library.passwordRequirement(passwordLField.getPassword());
                    //creates and adds user to library
                    library.addUser(usernameLField.getText(), password.toCharArray(),security1LField.getText(), security2LField.getText());
                    JOptionPane.showMessageDialog(null, "User account added");

                } catch (PasswordException ex) {
                    JOptionPane.showMessageDialog(null, "Password error: " + ex.getMessage());
                }
            }
        });


        panel.add(applicationLabel);
        panel.add(nameLabel);
        panel.add(optionsButton);
        panel.add(usernameLLabel);
        panel.add(usernameLField);
        panel.add(passwordLLabel);
        panel.add(passwordLField);
        panel.add(security1LLabel);
        panel.add(security1LField);
        panel.add(security2LLabel);
        panel.add(security2LField);
        panel.add(addUserButton);

        return panel;
    }

    private JPanel createRemoveUserPanel(){
        inUserLoginPanel = false;
        Rectangle rectangle = new Rectangle(0, 0, 600, 80);

        JPanel panel = createUpperBorderDisplay(rectangle, new Color(197, 160, 242));
        panel.setLayout(null);

        JLabel applicationLabel = createApplicationLabel(new Color(169, 138, 208));

        JLabel nameLabel = new JLabel("Remove User");
        nameLabel.setBounds(250, 100, 400, 60);
        nameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        nameLabel.setForeground(Color.white);

        optionsButton = new JButton("Go Back"); // Go Back button
        optionsButton.setBounds(475, 15, 100, 50);
        optionsButton.addActionListener(this);

        JLabel usernameLLabel = new JLabel("Enter Username:");
        usernameLLabel.setBounds(210, 145, 150, 30);

        JTextField usernameLField = new JTextField();
        usernameLField.setBounds(205, 175, 200, 30);

        JButton removeUserButton = new JButton("Remove User");
        removeUserButton.setBounds(220, 400, 90, 45);
        removeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = 0;
               for(int i = 0; i < library.users.size(); i++){
                   if(library.users.get(i).getUsername() == usernameLField.getText()){
                       count++;
                       User user = library.users.get(i);
                       library.users.remove(user);
                   }
               }
               if(count == 0 ){
                   JOptionPane.showMessageDialog(null, "Username not exist, please enter anther username");
               } else {
                   JOptionPane.showMessageDialog(null, "User account removed");
               }
            }
        });

        panel.add(applicationLabel);
        panel.add(nameLabel);
        panel.add(optionsButton);
        panel.add(usernameLLabel);
        panel.add(usernameLField);
        panel.add(removeUserButton);

        return panel;
    }

    private JPanel createShowUserListPanel(){
        inUserLoginPanel = false;
        Rectangle rectangle = new Rectangle(0, 0, 600, 80);

        JPanel panel = createUpperBorderDisplay(rectangle, new Color(197, 160, 242));
        panel.setLayout(null);

        JLabel applicationLabel = createApplicationLabel(new Color(169, 138, 208));

        JLabel nameLabel = new JLabel("Show User List");
        nameLabel.setBounds(250, 100, 400, 60);
        nameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        nameLabel.setForeground(Color.white);

        optionsButton = new JButton("Go Back"); // Go Back button
        optionsButton.setBounds(475, 15, 100, 50);
        optionsButton.addActionListener(this);

        panel.add(applicationLabel);
        panel.add(nameLabel);
        panel.add(optionsButton);

        return panel;
    }

    private void showUserProfile(User user) {
        String name = user.getUsername();
        String userName = user.getUsername();
        String password = String.valueOf(user.getPassword());

        String profileMessage = "Name: " + name + "\n" + "Password: " + password;

        JOptionPane.showMessageDialog(this, profileMessage, "Profile", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showBookInfo(Book book, User user) {
        String name = book.getName();
        String author = book.getAuthor();
        String genre = book.getGenre();
        String year = String.valueOf(book.getYear());

        String bookMessage = " Title: " + name + "\n" + "Author: " + author + "\n" + "Genre: " + genre + "\n" + "Year: " + year;

        // Create an array of options (buttons)
        Object[] options = {"Back", "Check Out"};

        // Display the JOptionPane with a custom option panel
        int choice = JOptionPane.showOptionDialog(
                this,
                bookMessage,
                "Book",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        // Handle the user's choice
        if (choice == 1) {
            // User clicked "Check Out"
            // Add your checkout logic here
            user.getBooks().add(book);
            System.out.println(user.getBooks());
            JOptionPane.showMessageDialog(this, "Book Checked Out!");
        }
    }

    public JPanel createUpperBorderDisplay(Rectangle rectangle, Color c){
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        };
        panel.setBackground(c);
        return panel;
    }

    private JLabel createApplicationLabel(Color c) {
        JLabel label = new JLabel("1337h4x0r Library");
        label.setBounds(30, 10, 400, 60);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        label.setForeground(c);
        return label;
    }

    private JButton createBackButton() {
        JButton button = new JButton("Go Back");
        button.setBounds(475, 15, 100, 50);
        button.addActionListener(this);
        return button;
    }

}
