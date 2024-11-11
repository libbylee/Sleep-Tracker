package ui;

import model.SleepEntry;
import model.SleepJournal;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ViewEntriesPanel extends JPanel {
    private MainWindow mainWindow;
    private SleepJournal journal;
    private JTextArea entriesArea;
    private JsonReader jsonReader;
    private JButton loadJournalButton;
    private JComboBox<String> sortComboBox;

    // EFFECTS: Creates a panel to view all sleep entries from the journal
    public ViewEntriesPanel(MainWindow mainWindow, SleepJournal journal, JsonReader reader) {
        this.mainWindow = mainWindow;
        this.journal = journal;
        this.jsonReader = reader;

        setLayout(new BorderLayout());

        loadJournalButton = new JButton("Load Journal");
        loadJournalButton.addActionListener(e -> loadEntriesFromFile()); // Button action to load entries
        JPanel loadButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loadButtonPanel.add(loadJournalButton);
        add(loadButtonPanel, BorderLayout.SOUTH);

        
        JLabel label = new JLabel("View All Entries", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 32));

        String[] sortOptions = {"Sort by Highest Rating", "Sort by Most Hours Slept"};
        sortComboBox = new JComboBox<>(sortOptions);
        sortComboBox.addActionListener(e -> sortEntries());
        JPanel sortPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sortPanel.add(sortComboBox);
        add(sortPanel, BorderLayout.NORTH);

        entriesArea = new JTextArea(15, 40);
        entriesArea.setEditable(false);
        entriesArea.setFont(new Font("Arial", Font.PLAIN, 18));

        JScrollPane scrollPane = new JScrollPane(entriesArea);
        add(scrollPane, BorderLayout.CENTER);

        setupButtons();
    }

    // MODIFIES: this
    // EFFECTS: Sets up the load and save buttons
    private void setupButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton loadButton = new JButton("Load Existing Entries");
        loadButton.setFont(new Font("Arial", Font.BOLD, 16));
        loadButton.addActionListener(e -> loadEntriesFromFile());

        JButton goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(e -> goBackToMainMenu());

        buttonPanel.add(loadButton);
        buttonPanel.add(goBackButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: Loads the entries from the file using JsonReader and updates the text area
    private void loadEntriesFromFile() {
        try {
            journal = jsonReader.read();
            displayEntries();
        } catch (IOException e) {
            entriesArea.setText("Error loading entries from file.");
        }
    }

    // EFFECTS: Displays the entries in the text area
    private void displayEntries() {
        List<SleepEntry> entries = journal.getAllEntries();
        StringBuilder sb = new StringBuilder();

        if (entries.isEmpty()) {
            entriesArea.setText("No entries found in the journal.");
            return;
        }

        for (SleepEntry entry : entries) {
            sb.append("Date: ").append(entry.getDate()).append("\n");
            sb.append("Hours Slept: ").append(entry.getHoursSlept()).append("\n");
            sb.append("Sleep Rating: ").append(entry.getSleepRating()).append("\n");
            sb.append("Notes: ").append(entry.getSleepNote()).append("\n");
            sb.append("---------------------------------------------------------------------------\n");
        }

        entriesArea.setText(sb.toString());
    }

    // MODIFIES: this
    // EFFECTS: Sorts the entries based on the selected option (either by rating or hours slept)
    private void sortEntries(){
        List<SleepEntry> entries = journal.getAllEntries();

        String selectedOption = (String) sortComboBox.getSelectedItem();

        if ("Sort by Highest Rating".equals(selectedOption)) {
            entries.sort((entry1, entry2) -> Double.compare(entry2.getSleepRating(), entry1.getSleepRating()));
        } else if ("Sort by Most Hours Slept".equals(selectedOption)) {
            entries.sort((entry1, entry2) -> Double.compare(entry2.getHoursSlept(), entry1.getHoursSlept()));
        }

        journal.setEntries(entries);

        displayEntries(); 
    }

    // EFFECTS: goes back to main menu
    private void goBackToMainMenu() {
        mainWindow.switchToMainMenu();
    }

    // MODIFIES: this
    // EFFECTS: refreshes the display 
    public void refreshDisplay() {
        loadEntriesFromFile();
    }
}
