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
    private JsonWriter writer;
    private JButton loadJournalButton;

    // EFFECTS: Creates a panel to view all sleep entries from the journal
    public ViewEntriesPanel(MainWindow mainWindow, SleepJournal journal, JsonReader reader) {
        this.mainWindow = mainWindow;
        // this.window = window;
        this.journal = journal;
        this.jsonReader = reader;

        setLayout(new BorderLayout());

        loadJournalButton = new JButton("Load Journal");
        loadJournalButton.addActionListener(e -> loadEntriesFromFile());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(loadJournalButton);
        add(buttonPanel, BorderLayout.SOUTH);

        JLabel label = new JLabel("View All Entries", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 32));

        setupButtons();

        entriesArea = new JTextArea(15, 50);
        entriesArea.setEditable(false); // Make it non-editable
        entriesArea.setFont(new Font("Arial", Font.PLAIN, 18));

        loadEntriesFromFile();

        JScrollPane scrollPane = new JScrollPane(entriesArea);

        add(label, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Sets up the load and save buttons
    private void setupButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton loadButton = new JButton("Load Journal");
        loadButton.setFont(new Font("Arial", Font.BOLD, 16));
        // loadButton.addActionListener(e -> loadJournal());

        JButton saveButton = new JButton("Save Journal");
        saveButton.setFont(new Font("Arial", Font.BOLD, 16));
        // saveButton.addActionListener(e -> saveJournal());

        JButton goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(e -> goBackToMainMenu());

        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(goBackButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: Loads the entries from the file using JsonReader and updates the
    // text area
    private void loadEntriesFromFile() {
        try {
            journal = jsonReader.read();
            displayEntries();
        } catch (IOException e) {
            entriesArea.setText("Error loading entries from file.");
        }
    }

     //EFFECTS: goes back to main menu 
     private void goBackToMainMenu() {
        mainWindow.switchToMainMenu();
    }

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
            sb.append("------------------------------------------------------------\n");
        }

        entriesArea.setText(sb.toString());
    }

    public void refreshDisplay() {
        loadEntriesFromFile();
    }
}