import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    private JPanel panel;
    private JPanel newPanel; // New panel for a fresh screen
    private boolean inUserLoginPanel = false;

    public static void main(String[] args) {
        Main app = new Main();
        app.setVisible(true);
    }

    public Main() {
        this.setTitle("1337h4x0r.library.sjsu.ca.gov");
        this.setSize(600, 400);
        panel = startScreenPanel();
        this.add(panel);
    }

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
        }

        this.remove(panel);
        panel = newPanel;
        this.add(panel);

        this.revalidate();
        this.repaint();
    }

    private JPanel startScreenPanel(){
        inUserLoginPanel = false;
        Rectangle rectangle = new Rectangle(0, 0, 600, 80);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        };

        panel.setBackground(new Color(160, 235, 242));
        panel.setLayout(null);

        JLabel applicationLabel = new JLabel("1337h4x0r Library");
        applicationLabel.setBounds(30,10,400,60);
        applicationLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        applicationLabel.setForeground(new Color(122, 183, 189));

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

    private JPanel createLoginPanel() {
        inUserLoginPanel = false;
        Rectangle rectangle = new Rectangle(0, 0, 600, 80);

        JPanel loginPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        };

        loginPanel.setBackground(new Color(163, 242, 160));
        loginPanel.setLayout(null);

        JLabel applicationLabel = new JLabel("1337h4x0r Library");
        applicationLabel.setBounds(30,10,400,60);
        applicationLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        applicationLabel.setForeground(new Color(130, 192, 128));

        JButton backButton = new JButton("Go Back");
        backButton.setBounds(475, 15, 100, 50);
        backButton.addActionListener(this);

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

        JPanel userLoginPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        };

        userLoginPanel.setBackground(new Color(242, 197, 160));
        userLoginPanel.setLayout(null);

        JLabel applicationLabel = new JLabel("1337h4x0r Library");
        applicationLabel.setBounds(30, 10, 400, 60);
        applicationLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        applicationLabel.setForeground(new Color(204, 167, 136));

        JButton backButton = new JButton("Go Back");
        backButton.setBounds(475, 15, 100, 50);
        backButton.addActionListener(this);

        JButton loginButton = new JButton("Log-In");
        loginButton.setBounds(220, 280, 90, 45);

        JLabel loginLabel = new JLabel("Log In");
        loginLabel.setBounds(218, 100, 100, 40);
        loginLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        loginLabel.setForeground(new Color(246, 243, 243));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(220, 145, 100, 30);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(215, 175, 200, 30);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(220, 205, 100, 30);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(215, 235, 200, 30);

        userLoginPanel.add(backButton);
        userLoginPanel.add(applicationLabel);
        userLoginPanel.add(usernameLabel);
        userLoginPanel.add(usernameField);
        userLoginPanel.add(passwordLabel);
        userLoginPanel.add(passwordField);
        userLoginPanel.add(loginLabel);
        userLoginPanel.add(loginButton);
        return userLoginPanel;
    }

    private JPanel createAdminLoginPanel(){
        inUserLoginPanel = true;
        Rectangle rectangle = new Rectangle(0, 0, 600, 80);

        JPanel adminLoginPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        };

        adminLoginPanel.setBackground(new Color(242, 197, 160));
        adminLoginPanel.setLayout(null);

        JLabel applicationLabel = new JLabel("1337h4x0r Library");
        applicationLabel.setBounds(30, 10, 400, 60);
        applicationLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        applicationLabel.setForeground(new Color(204, 167, 136));

        JButton backButton = new JButton("Go Back");
        backButton.setBounds(475, 15, 100, 50);
        backButton.addActionListener(this);

        JButton loginButton = new JButton("Log-In");
        loginButton.setBounds(220, 280, 90, 45);

        JLabel loginLabel = new JLabel("Log In [Admin]");
        loginLabel.setBounds(218, 100, 170, 40);
        loginLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        loginLabel.setForeground(new Color(246, 243, 243));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(220, 145, 100, 30);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(215, 175, 200, 30);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(220, 205, 100, 30);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(215, 235, 200, 30);

        adminLoginPanel.add(backButton);
        adminLoginPanel.add(applicationLabel);
        adminLoginPanel.add(usernameLabel);
        adminLoginPanel.add(usernameField);
        adminLoginPanel.add(passwordLabel);
        adminLoginPanel.add(passwordField);
        adminLoginPanel.add(loginLabel);
        adminLoginPanel.add(loginButton);
        return adminLoginPanel;
    }

    private JPanel createSignupPanel() {
        inUserLoginPanel = false;
        Rectangle rectangle = new Rectangle(0, 0, 600, 80);

        JPanel signupPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        };
        
        signupPanel.setBackground(new Color(163, 242, 160));
        signupPanel.setLayout(null);

        JLabel applicationLabel = new JLabel("1337h4x0r Library");
        applicationLabel.setBounds(30,10,400,60);
        applicationLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        applicationLabel.setForeground(new Color(130, 192, 128));

        JButton backButton = new JButton("Go Back");
        backButton.setBounds(475, 15, 100, 50);
        backButton.addActionListener(this);

        JButton signUpButton = new JButton("Sign-Up");
        signUpButton.setBounds(220, 280, 90, 45);

        JLabel signUpLabel = new JLabel("Sign Up");
        signUpLabel.setBounds(220, 100, 170, 40);
        signUpLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        signUpLabel.setForeground(new Color(246, 243, 243));

        JLabel usernameLabel = new JLabel("Enter Username:");
        usernameLabel.setBounds(220, 145, 150, 30);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(215, 175, 200, 30);

        JLabel passwordLabel = new JLabel("Enter Password:");
        passwordLabel.setBounds(220, 205, 100, 30);

        JPasswordField passwordField = new JPasswordField();
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

}
