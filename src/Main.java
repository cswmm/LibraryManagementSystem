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
        if (e.getActionCommand().equals("Login")) {
            newPanel = createLoginPanel();
        } else if (e.getActionCommand().equals("Sign Up")) {
            newPanel = createSignupPanel();
        } else if (e.getActionCommand().equals("Go Back")) {
            newPanel = startScreenPanel();
        }

        this.remove(panel);
        panel = newPanel;
        this.add(panel);

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

    private JPanel createLoginPanel() {
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

        loginPanel.add(backButton);
        loginPanel.add(applicationLabel);
        return loginPanel;
    }

    private JPanel createSignupPanel() {
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

        signupPanel.add(backButton);
        signupPanel.add(applicationLabel);
        return signupPanel;
    }

}
