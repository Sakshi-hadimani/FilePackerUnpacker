import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MarvellousLogin extends Template implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private int attempts = 0;
    private long lockoutTime = 0;

    public MarvellousLogin() {
        super();
        JLabel title = new JLabel("Login Portal");
        title.setFont(new Font("Century", Font.BOLD, 22));
        title.setForeground(Color.white);
        title.setBounds(280, 10, 200, 40);
        _header.add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.white);
        userLabel.setBounds(150, 50, 100, 25);
        _content.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(250, 50, 200, 25);
        _content.add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.white);
        passLabel.setBounds(150, 100, 100, 25);
        _content.add(passLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(250, 100, 200, 25);
        _content.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(300, 160, 100, 30);
        loginButton.addActionListener(this);
        _content.add(loginButton);

        // Buttons action
        exit.addActionListener(e -> System.exit(0));
        minimize.addActionListener(e -> setState(JFrame.ICONIFIED));

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (System.currentTimeMillis() < lockoutTime) {
            JOptionPane.showMessageDialog(this, "Too many attempts. Try again later.");
            return;
        }

        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());

        if (user.equals("admin") && pass.equals("admin123")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            this.dispose();
            new NextPage(user);
        } else {
            attempts++;
            if (attempts >= 3) {
                lockoutTime = System.currentTimeMillis() + (2 * 60 * 60 * 1000); // 2 hours lockout
                JOptionPane.showMessageDialog(this, "Too many attempts. Locked for 2 hours.");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials. Attempts left: " + (3 - attempts));
            }
        }
    }

    public static void main(String[] args) {
        new MarvellousLogin();
    }
}
