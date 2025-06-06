import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NextPage extends Template implements ActionListener {
    JLabel welcomeLabel;
    JButton packButton, unpackButton;

    public NextPage(String username) {
        super();

        welcomeLabel = new JLabel("Welcome: " + username);
        welcomeLabel.setBounds(270, 20, 200, 30);
        welcomeLabel.setFont(new Font("Century", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.cyan);
        _header.add(welcomeLabel);

        packButton = new JButton("Pack Files");
        packButton.setBounds(180, 100, 120, 40);
        packButton.addActionListener(this);
        _content.add(packButton);

        unpackButton = new JButton("Unpack Files");
        unpackButton.setBounds(380, 100, 120, 40);
        unpackButton.addActionListener(this);
        _content.add(unpackButton);

        exit.addActionListener(e -> System.exit(0));
        minimize.addActionListener(e -> setState(JFrame.ICONIFIED));

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == packButton) {
            this.dispose();
            new MarvellousPackerFrontend();
        } else if (ae.getSource() == unpackButton) {
            this.dispose();
            new MarvellousUnpackerFrontend();
        }
    }
}
