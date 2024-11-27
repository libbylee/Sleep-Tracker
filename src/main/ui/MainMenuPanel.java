package ui;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {


    public MainMenuPanel(MainWindow mainWindow) {

        setLayout(new BorderLayout());

        JPanel titlePanel = setTitlePanel();

        add(titlePanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton viewAllEntriesButton = new JButton("View All Entries");
        JButton addEntryButton = new JButton("Add Entry");
        JButton viewAvgRatingButton = new JButton("Average Stats");
        JButton quitButton = new JButton("Quit");

        addActionListenerToButtons(mainWindow, viewAllEntriesButton, addEntryButton, viewAvgRatingButton, quitButton);

        addButtonsToPanel(buttonPanel, viewAllEntriesButton, addEntryButton, viewAvgRatingButton,
                quitButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    // MODIFIES: buttonPanel
    // EFFECTS: adds response/ action listener to buttons
    private void addActionListenerToButtons(MainWindow mainWindow, JButton viewAllEntriesButton, JButton addEntryButton,
            JButton viewAvgRatingButton, JButton quitButton) {
        viewAllEntriesButton.addActionListener(e -> mainWindow.switchToViewEntries());
        addEntryButton.addActionListener(e -> mainWindow.switchToAddEntry());
        viewAvgRatingButton.addActionListener(e -> mainWindow.switchToAverageStatsPanel());

        quitButton.addActionListener(e -> {
            for (model.Event event : model.EventLog.getInstance()) {
                System.out.println(event.toString());
            }
            System.exit(0);
        });
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
