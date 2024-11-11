package ui;

import javax.swing.*;

import model.SleepEntry;
import model.SleepJournal;
import java.time.LocalDate;

import java.awt.*;

public class AddEntryPanel extends JPanel {
    private JTextField dateField;
    private JTextField hoursSleptField;
    private JTextField ratingField;
    private JTextArea notesArea;
    private JButton saveButton;
    private SleepJournal sleepJournal;
    private ImageIcon successNerdIcon;

    // MODIFIES: this
    // EFFECTS: Creates an add entry panel with buttons for sleepJournal functions
    public AddEntryPanel(SleepJournal journal) {
        this.sleepJournal = journal;
        setLayout(new BorderLayout());

        // Title label
        JLabel label = new JLabel("Add a new entry!", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 32));

        // Form setup
        JPanel entryForm = createEntryForm();

        // Save button setup
        saveButton = new JButton("Save Entry");
        saveButton.setPreferredSize(new Dimension(150, 50)); // Set button size
        saveButton.setFont(new Font("Arial", Font.BOLD, 20)); // Increase font size
        saveButton.addActionListener(e -> addEntry()); // Link button to action

        // Center the button in its own panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(saveButton);

        // Add components to main panel layout
        add(label, BorderLayout.NORTH);
        add(entryForm, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH); // Add button panel at the bottom
    }

    // MODIFIES: this
    // EFFECTS: creates a form with the label and input fields
    private JPanel createEntryForm() {
        JPanel entryForm = new JPanel(new GridLayout(5, 2, 10, 10));

        // Date Field
        entryForm.add(new JLabel("Enter Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        entryForm.add(dateField);

        // Hours Slept Field
        entryForm.add(new JLabel("Enter Hours Slept:"));
        hoursSleptField = new JTextField();
        entryForm.add(hoursSleptField);

        // Rating Field
        entryForm.add(new JLabel("Enter Sleep Rating (1-10):"));
        ratingField = new JTextField();
        entryForm.add(ratingField);

        // Notes Area
        entryForm.add(new JLabel("Notes:"));
        notesArea = new JTextArea(3, 20);
        entryForm.add(new JScrollPane(notesArea));

        return entryForm;
    }

    // MODIFIES: sleepJournal
    // EFFECTS: Creates and adds a new SleepEntry based on the form fields
    private void addEntry() {
        try {
            String date = dateField.getText();
            double hoursSlept = Double.parseDouble(hoursSleptField.getText());
            int sleepRating = Integer.parseInt(ratingField.getText());
            String notes = notesArea.getText();

            SleepEntry entry = new SleepEntry(LocalDate.parse(date), hoursSlept, sleepRating, notes);
            sleepJournal.addSleepEntryToSleepJournal(entry);

            ImageIcon successNerdIcon = new ImageIcon("nerd.png");

            JOptionPane.showMessageDialog(this, "Entry added successfully", "success!",
                    JOptionPane.INFORMATION_MESSAGE, successNerdIcon);

            // Clear fields after adding entry
            dateField.setText("");
            hoursSleptField.setText("");
            ratingField.setText("");
            notesArea.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check the format of your data.");
        }
    }

}
