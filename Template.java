import java.awt.*;
import javax.swing.*;

public class Template extends JFrame {
    protected JPanel _header;
    protected JPanel _content;
    protected JPanel _footer;
    protected JButton exit, minimize;

    public Template() {
        // Setup frame basics
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        _header = new JPanel();
        _header.setLayout(null);
        _header.setBackground(new Color(10, 10, 70));
        _header.setBounds(0, 0, 700, 80);

        _content = new JPanel();
        _content.setLayout(null);
        _content.setBackground(new Color(15, 15, 120));
        _content.setBounds(0, 80, 700, 270);

        _footer = new JPanel();
        _footer.setLayout(null);
        _footer.setBackground(new Color(10, 10, 50));
        _footer.setBounds(0, 350, 700, 30);

        exit = new JButton("Exit");
        exit.setBounds(600, 5, 80, 25);

        minimize = new JButton("Minimize");
        minimize.setBounds(510, 5, 80, 25);

        _header.add(exit);
        _header.add(minimize);

        this.add(_header);
        this.add(_content);
        this.add(_footer);

        this.setSize(700, 420);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
}
