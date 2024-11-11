package ui;

import model.SleepEntry;
import model.SleepJournal;
import persistence.JsonReader;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ViewEntriesPanel extends JPanel {
    private SleepJournal sleepJournal;
    private JTextArea entriesArea;
    private JsonReader jsonReader;

    // EFFECTS: Creates a panel to view all sleep entries from the journal
    public ViewEntriesPanel(SleepJournal journal, JsonReader reader) {
        this.sleepJournal = journal;
        this.jsonReader = reader;

        setLayout(new BorderLayout());

        JLabel label = new JLabel("View All Entries", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 32));

        entriesArea = new JTextArea(15, 50);
        entriesArea.setEditable(false); // Make it non-editable
        entriesArea.setFont(new Font("Arial", Font.PLAIN, 18));

        loadEntriesFromFile();

        JScrollPane scrollPane = new JScrollPane(entriesArea);

        add(label, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Loads the entries from the file using JsonReader and updates the text area
    private void loadEntriesFromFile() {
        try {
            sleepJournal = jsonReader.read();
            displayEntries();
        } catch (IOException e) {
            entriesArea.setText("Error loading entries from file.");
        }
    }

    private void displayEntries() {
        List<SleepEntry> entries = sleepJournal.getAllEntries();
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