import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class MarvellousPackerFrontend extends Template implements ActionListener {
    JButton submitButton, previousButton, browseButton;
    JLabel label1, label2, title;
    JTextField textDir, textDest;

    public MarvellousPackerFrontend() {
        super();

        title = new JLabel("Marvellous Packing Portal");
        title.setFont(new Font("Century", Font.BOLD, 22));
        title.setForeground(Color.white);
        title.setBounds(220, 10, 300, 30);
        _header.add(title);

        label1 = new JLabel("Directory to Pack:");
        label1.setForeground(Color.white);
        label1.setBounds(50, 50, 150, 25);
        _content.add(label1);

        textDir = new JTextField();
        textDir.setBounds(200, 50, 300, 25);
        _content.add(textDir);

        browseButton = new JButton("Browse");
        browseButton.setBounds(510, 50, 90, 25);
        browseButton.addActionListener(this);
        _content.add(browseButton);

        label2 = new JLabel("Destination File Name:");
        label2.setForeground(Color.white);
        label2.setBounds(50, 100, 150, 25);
        _content.add(label2);

        textDest = new JTextField();
        textDest.setBounds(200, 100, 300, 25);
        _content.add(textDest);

        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 160, 100, 30);
        submitButton.addActionListener(this);
        _content.add(submitButton);

        previousButton = new JButton("Previous");
        previousButton.setBounds(320, 160, 100, 30);
        previousButton.addActionListener(this);
        _content.add(previousButton);

        exit.addActionListener(e -> System.exit(0));
        minimize.addActionListener(e -> setState(JFrame.ICONIFIED));

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == browseButton) {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int res = fc.showOpenDialog(this);
            if (res == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                textDir.setText(file.getAbsolutePath());
            }
        } else if (ae.getSource() == submitButton) {
            String sourceDir = textDir.getText();
            String destFile = textDest.getText();

            if (sourceDir.isEmpty() || destFile.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill both fields.");
                return;
            }

            try {
                MarvellousPacker packer = new MarvellousPacker(sourceDir, destFile);
                JOptionPane.showMessageDialog(this, "Packing Completed!");
                this.dispose();
                new NextPage("MarvellousAdmin");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        } else if (ae.getSource() == previousButton) {
            this.dispose();
            new NextPage("MarvellousAdmin");
        }
    }
}
