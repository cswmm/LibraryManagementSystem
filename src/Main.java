import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public static void main(String[] args) {
        Main app = new Main();
        app.setVisible(true);
    }

    public Main() {
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

        optionsButton = new JButton("Options");
        optionsButton.setBounds(475, 15, 100, 50);
        optionsButton.addActionListener(this);

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

        panel.add(applicationLabel);
        panel.add(nameLabel);
        panel.add(optionsButton);

        return panel;
    }

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

        panel.add(applicationLabel);
        panel.add(nameLabel);
        panel.add(optionsButton);

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

    private void showUserProfile(User user) {
        String name = user.getUsername();
        String userName = user.getUsername();
        String password = String.valueOf(user.getPassword());

        String profileMessage = "Name: " + name + "\n" + "Password: " + password;

        JOptionPane.showMessageDialog(this, profileMessage, "Profile", JOptionPane.INFORMATION_MESSAGE);
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
