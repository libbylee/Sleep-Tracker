package ui;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    public MainMenu() {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("View Entries", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        JTextArea entriesArea = new JTextArea();
        entriesArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(entriesArea);

        add(label, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
