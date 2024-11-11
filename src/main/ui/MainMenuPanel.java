package ui;

import javax.swing.*;
import model.SleepJournal;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    private JFrame window;
    private SleepJournal journal;
    private JsonWriter writer;
    private JsonReader reader;
    private ViewEntriesPanel viewEntriesPanel;

    public MainMenuPanel(JFrame window, SleepJournal journal, JsonWriter writer, JsonReader reader) {
        this.window = window;
        this.journal = journal;
        this.writer = writer;
        this.reader = reader;
        this.viewEntriesPanel = new ViewEntriesPanel(journal, reader);

        setLayout(new BorderLayout());

        JPanel titlePanel = setTitlePanel();

        add(titlePanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton viewAllEntriesButton = new JButton("View All Entries");
        JButton addEntryButton = new JButton("Add Entry");
        JButton viewAvgEntriesButton = new JButton("View Average Hours Slept");
        JButton viewAvgRatingButton = new JButton("View Average Sleep Rating");
        JButton quitButton = new JButton("Quit");

        viewAllEntriesButton.addActionListener(e -> showViewEntriesPanel());

        addEntryButton.addActionListener(e -> showAddEntryPanel());

        // Uncomment when those panels are implemented
        // viewAvgEntriesButton.addActionListener(e -> showAverageEntriesPanel());
        // viewAvgRatingButton.addActionListener(e -> showAverageScorePanel());
        quitButton.addActionListener(e -> System.exit(0));

        addButtonsToPanel(buttonPanel, viewAllEntriesButton, addEntryButton, viewAvgEntriesButton, viewAvgRatingButton,
                quitButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    private JPanel setTitlePanel() {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel titleLabel = new JLabel("Welcome to the Sleep Journal App", JLabel.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
        
        ImageIcon titleIcon = getResizedBook(); 
        JLabel emojiLabel = new JLabel(titleIcon);

        titlePanel.add(titleLabel);
        titlePanel.add(emojiLabel);
        return titlePanel;
    }

    private void addButtonsToPanel(JPanel buttonPanel, JButton viewAllEntriesButton, JButton addEntryButton,
            JButton viewAvgEntriesButton, JButton viewAvgRatingButton, JButton quitButton) {
        buttonPanel.add(viewAllEntriesButton);
        buttonPanel.add(addEntryButton);
        buttonPanel.add(viewAvgEntriesButton);
        buttonPanel.add(viewAvgRatingButton);
        buttonPanel.add(quitButton);
    }

        // MODIFIES: ImageIcon
    // EFFECTS: loads and resizes save entry success icon
    private ImageIcon getResizedBook() {
        ImageIcon saveJournalIcon = new ImageIcon("journalicon.png");
        Image journalImage = saveJournalIcon.getImage();
        Image resizedJournalIcon = journalImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        saveJournalIcon = new ImageIcon(resizedJournalIcon);
        return saveJournalIcon;
    }

    // EFFECTS: Switches to View Entries Panel
    private void showViewEntriesPanel() {
        if (viewEntriesPanel == null) {
            viewEntriesPanel = new ViewEntriesPanel(journal, reader);
        }
        window.getContentPane().removeAll();
        window.getContentPane().add(viewEntriesPanel, BorderLayout.CENTER);
        window.revalidate();
        window.repaint();
    }

    // EFFECTS: Switches to Add Entry Panel
    private void showAddEntryPanel() {
        if (window == null) {
            System.out.println("Error: JFrame window is null.");
            return;
        }
        AddEntryPanel addEntryPanel = new AddEntryPanel(window, journal, writer, viewEntriesPanel);
        window.getContentPane().removeAll();
        window.getContentPane().add(addEntryPanel, BorderLayout.CENTER);
        window.revalidate();
        window.repaint();
    }
}

// Uncomment and implement these methods when necessary
// // EFFECTS: Switches to Average Entries Panel (Average Hours Slept)
// private void showAverageEntriesPanel() {
// AverageEntriesPanel averageEntriesPanel = new
// AverageEntriesPanel(sleepJournal);
// window.getContentPane().removeAll();
// window.getContentPane().add(averageEntriesPanel, BorderLayout.CENTER);
// window.revalidate();
// window.repaint();
// }

// // EFFECTS: Switches to Average Score Panel (Average Sleep Rating)
// private void showAverageScorePanel() {
// AverageScorePanel averageScorePanel = new AverageScorePanel(sleepJournal);
// window.getContentPane().removeAll();
// window.getContentPane().add(averageScorePanel, BorderLayout.CENTER);
// window.revalidate();
// window.repaint();
// }
