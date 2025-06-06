import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class MarvellousUnpackerFrontend extends Template implements ActionListener {
    JButton submitButton, previousButton, browseButton;
    JLabel label1, title;
    JTextField textFile;

    public MarvellousUnpackerFrontend() {
        super();

        title = new JLabel("Marvellous Unpacking Portal");
        title.setFont(new Font("Century", Font.BOLD, 22));
        title.setForeground(Color.white);
        title.setBounds(220, 10, 300, 30);
        _header.add(title);

        label1 = new JLabel("Select Packed File:");
        label1.setForeground(Color.white);
        label1.setBounds(50, 50, 150, 25);
        _content.add(label1);

        textFile = new JTextField();
        textFile.setBounds(200, 50, 300, 25);
        _content.add(textFile);

        browseButton = new JButton("Browse");
        browseButton.setBounds(510, 50, 90, 25);
        browseButton.addActionListener(this);
        _content.add(browseButton);

        submitButton = new JButton("Extract Here");
        submitButton.setBounds(200, 110, 120, 30);
        submitButton.addActionListener(this);
        _content.add(submitButton);

        previousButton = new JButton("Previous");
        previousButton.setBounds(340, 110, 120, 30);
        previousButton.addActionListener(this);
        _content.add(previousButton);

        exit.addActionListener(e -> System.exit(0));
        minimize.addActionListener(e -> setState(JFrame.ICONIFIED));

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == browseButton) {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int res = fc.showOpenDialog(this);
            if (res == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                textFile.setText(file.getAbsolutePath());
            }
        } else if (ae.getSource() == submitButton) {
            String packedFile = textFile.getText();
            if (packedFile.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a file.");
                return;
            }
            try {
                MarvellousUnpacker unpacker = new MarvellousUnpacker(packedFile);
                JOptionPane.showMessageDialog(this, "Unpacking Completed!");
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
