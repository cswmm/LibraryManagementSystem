import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    private JPanel panel;
    private JPanel newPanel; // New panel for a fresh screen

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
        // Check which button was clicked
        if (e.getActionCommand().equals("Login")) {
            // Create a new panel or screen for the "Login" action
            newPanel = createLoginPanel(); // Implement createLoginPanel() method
        } else if (e.getActionCommand().equals("Sign Up")) {
            // Create a new panel or screen for the "Sign Up" action
            newPanel = createSignupPanel(); // Implement createSignupPanel() method
        } else if (e.getActionCommand().equals("Go Back")) {
            // Create a new panel or screen for the "Sign Up" action
            newPanel = startScreenPanel(); // Implement createSignupPanel() method
        }

        // Remove the current panel and add the new panel
        this.remove(panel);
        panel = newPanel;
        this.add(panel);

        // Repaint the JFrame to update the changes
        this.revalidate();
        this.repaint();
    }

    private JPanel startScreenPanel(){
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

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(475, 15, 100, 50);

        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(360, 15, 100, 50);

        signupButton.addActionListener(this);
        loginButton.addActionListener(this);

        panel.add(applicationLabel);
        panel.add(loginButton);
        panel.add(signupButton);

        return panel;
    }
    // Implement methods createLoginPanel() and createSignupPanel() to create the new screens
    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        JButton backButton = new JButton("Go Back");
        backButton.setBounds(475, 15, 100, 50);
        backButton.addActionListener(this);

        loginPanel.add(backButton);
        // Customize the "Login" screen here
        return loginPanel;
    }

    private JPanel createSignupPanel() {
        JPanel signupPanel = new JPanel();
        signupPanel.setLayout(null);
        JButton backButton = new JButton("Go Back");
        backButton.setBounds(475, 15, 100, 50);
        backButton.addActionListener(this);

        signupPanel.add(backButton);
        // Customize the "Login" screen here
        return signupPanel;
    }

}
