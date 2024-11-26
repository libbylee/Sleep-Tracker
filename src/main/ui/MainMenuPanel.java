package ui;

import javax.swing.*;
import model.SleepJournal;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    private MainWindow mainWindow;
    private SleepJournal journal;
    private JsonWriter writer;
    private JsonReader reader;
    private ViewEntriesPanel viewEntriesPanel;

    public MainMenuPanel(MainWindow mainWindow, SleepJournal journal, JsonWriter writer, JsonReader reader) {
        this.mainWindow = mainWindow;
        this.journal = journal;
        this.writer = writer;
        this.reader = reader;
        this.viewEntriesPanel = new ViewEntriesPanel(mainWindow, journal, reader);

        setLayout(new BorderLayout());

        JPanel titlePanel = setTitlePanel();

        add(titlePanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton viewAllEntriesButton = new JButton("View All Entries");
        JButton addEntryButton = new JButton("Add Entry");
        JButton viewAvgRatingButton = new JButton("Average Stats");
        JButton quitButton = new JButton("Quit");

        viewAllEntriesButton.addActionListener(e -> mainWindow.switchToViewEntries());
        addEntryButton.addActionListener(e -> mainWindow.switchToAddEntry());
        viewAvgRatingButton.addActionListener(e -> mainWindow.switchToAverageStatsPanel());

        quitButton.addActionListener(e -> {
            for (model.Event event : model.EventLog.getInstance()) {
                System.out.println(event.toString());
            }
            System.exit(0);
        });

        addButtonsToPanel(buttonPanel, viewAllEntriesButton, addEntryButton, viewAvgRatingButton,
                quitButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    // MODIFIES: buttonPanel
    // EFFECTS: initialize title panel
    private JPanel setTitlePanel() {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel titleLabel = new JLabel("Welcome to your sleep journal!", JLabel.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));

        ImageIcon titleIcon = getResizedBook();
        JLabel emojiLabel = new JLabel(titleIcon);

        titlePanel.add(titleLabel);
        titlePanel.add(emojiLabel);
        return titlePanel;
    }

    // MODIFIES: buttonPanel
    // EFFECTS: adds buttons to panel
    private void addButtonsToPanel(JPanel buttonPanel, JButton viewAllEntriesButton, JButton addEntryButton,
            JButton viewAvgRatingButton, JButton quitButton) {
        buttonPanel.add(viewAllEntriesButton);
        buttonPanel.add(addEntryButton);
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
}
