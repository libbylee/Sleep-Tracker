package ui;

import javax.swing.*;

import model.SleepJournal;

import java.awt.*;

public class AddEntryPanel extends JPanel {
    private JTextField dateField;
    private JTextField hoursSleptField;
    private JTextField ratingField;
    private JTextArea notesArea;
    private JButton saveButton;
    private SleepJournal sleepJournal;


    public AddEntryPanel(SleepJournal journal) {
        this.sleepJournal = journal;
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Add New Entry", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        JTextField dateField = new JTextField("Enter date here MM-DD-YY");
        JTextField hoursSleptField = new JTextField("Enter hours slept");
        JTextField ratingField = new JTextField("Enter sleep rating");

        JButton saveButton = new JButton("Save Entry");

        JPanel entryForm = new JPanel(new GridLayout(3, 1));
        entryForm.add(dateField);
        entryForm.add(hoursSleptField);
        entryForm.add(ratingField);
        entryForm.add(saveButton);

        add(label, BorderLayout.NORTH);
        add(entryForm, BorderLayout.CENTER);
    }

    // MODIFIES: sleepJournal
    // EFFECTS: Creates and adds a new SleepEntry based on the form fields
    private void addEntry() {};

}
