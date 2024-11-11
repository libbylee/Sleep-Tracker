package ui;

import javax.swing.*;

import model.SleepEntry;
import model.SleepJournal;
import persistence.JsonWriter;

import java.time.LocalDate;
import java.awt.*;
import java.io.FileNotFoundException;

public class AddEntryPanel extends JPanel {
    private JTextField dateField;
    private JTextField hoursSleptField;
    private JTextField ratingField;
    private JTextArea notesArea;
    private JButton saveButton;
    private SleepJournal sleepJournal;
    private JsonWriter writer;
    private ViewEntriesPanel viewEntriesPanel;
    private MainWindow mainWindow;

    // MODIFIES: this
    // EFFECTS: Creates an add entry panel with buttons for sleepJournal functions
    public AddEntryPanel(MainWindow mainWindow, SleepJournal journal, JsonWriter writer,
            ViewEntriesPanel viewEntriesPanel) {
        this.mainWindow = mainWindow;
        this.sleepJournal = journal;
        this.writer = writer;
        this.viewEntriesPanel = viewEntriesPanel;

        setLayout(new BorderLayout());

        JPanel titlePanel = setTitlePanel();


        JPanel entryForm = createEntryForm();

        saveButton = new JButton("Save Entry");
        saveButton.setPreferredSize(new Dimension(500, 75));
        saveButton.setFont(new Font("Arial", Font.BOLD, 28));
        saveButton.addActionListener(e -> addEntry());

        JButton goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(e -> goBackToMainMenu());

        ImageIcon saveIcon = getResizedNerdImage();
        JLabel saveIconLabel = new JLabel(saveIcon);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(saveIconLabel);
        buttonPanel.add(saveButton);
        buttonPanel.add(goBackButton);

        add(titlePanel, BorderLayout.NORTH);
        add(entryForm, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel setTitlePanel() {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel titleLabel = new JLabel("Add a new entry!", JLabel.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));

        ImageIcon titleIcon = getResizedBlush();
        JLabel emojiLabel = new JLabel(titleIcon);

        titlePanel.add(titleLabel);
        titlePanel.add(emojiLabel);
        return titlePanel;
    }

    // MODIFIES: ImageIcon
    // EFFECTS: loads and resizes save entry success icon
    private ImageIcon getResizedBlush() {
        ImageIcon saveBlushIcon = new ImageIcon("blushingbluesmile.jpg");
        Image journalImage = saveBlushIcon.getImage();
        Image resizedBlushIcon = journalImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        saveBlushIcon = new ImageIcon(resizedBlushIcon);
        return saveBlushIcon;
    }

    // MODIFIES: this
    // EFFECTS: Sets the ViewEntriesPanel reference for refreshing the entries list
    public void setViewEntriesPanel(ViewEntriesPanel panel) {
        this.viewEntriesPanel = panel;
    }

    // EFFECTS: goes back to main menu
    private void goBackToMainMenu() {
        mainWindow.switchToMainMenu();
    }

    // MODIFIES: this
    // EFFECTS: creates a form with the label and input fields
    private JPanel createEntryForm() {
        JPanel entryForm = new JPanel(new GridLayout(5, 2, 10, 10));
        setDateLayout(entryForm);
        JLabel hourLabel = setHoursSleptLayout(entryForm);
        setRatingLayout(entryForm, hourLabel);
        setNotesLayout(entryForm);

        return entryForm;
    }

    //MODIFIES: this 
    //EFFECTS: Sets the layout for notes of entries 
    private void setNotesLayout(JPanel entryForm) {
        JLabel noteLabel = new JLabel("Enter notes: ");
        noteLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        entryForm.add(noteLabel);
        notesArea = new JTextArea(3, 20);
        entryForm.add(new JScrollPane(notesArea));
    }

    //MODIFIES: this 
    //EFFECTS: Sets the layout for sleep ratings of entries 
    private void setRatingLayout(JPanel entryForm, JLabel hourLabel) {
        JLabel ratingLabel = new JLabel("Enter Sleep Rating (1-10):");
        hourLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        entryForm.add(ratingLabel);
        ratingField = new JTextField();
        ratingField.setFont(new Font("Arial", Font.PLAIN, 16));
        entryForm.add(ratingField);
    }

    //MODIFIES: this 
    //EFFECTS: Sets the layout for number of hours slept in entires 
    private JLabel setHoursSleptLayout(JPanel entryForm) {
        JLabel hourLabel = new JLabel("Enter Hours Slept:");
        hourLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        entryForm.add(hourLabel);
        hoursSleptField = new JTextField();
        hoursSleptField.setFont(new Font("Arial", Font.PLAIN, 16));
        entryForm.add(hoursSleptField);
        return hourLabel;
    }

    //MODIFIES: this 
    //EFFECTS: Sets the layout for dates in entries 
    private void setDateLayout(JPanel entryForm) {
        JLabel dateLabel = new JLabel("Enter Date (YYYY-MM-DD):");
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        entryForm.add(dateLabel);
        dateField = new JTextField();
        dateField.setFont(new Font("Arial", Font.PLAIN, 16));
        entryForm.add(dateField);
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

            saveSleepJournalToFile();

            ImageIcon successNerdIcon = getNerdGIF();

            JLabel messageLabel = new JLabel("Entry added successfully!");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 42)); 

            JOptionPane.showMessageDialog(this, messageLabel, "success",
                    JOptionPane.INFORMATION_MESSAGE, successNerdIcon);

            dateField.setText("");
            hoursSleptField.setText("");
            ratingField.setText("");
            notesArea.setText("");

            if (viewEntriesPanel != null) {
                viewEntriesPanel.refreshDisplay();
            }

        } catch (Exception ex) {
            ImageIcon laughingIcon = getResizedLaughingIcon();
            JLabel messageLabelFail = new JLabel("Invalid input. Please check the format of your data.");
            messageLabelFail.setFont(new Font("Arial", Font.BOLD, 16)); 
            JOptionPane.showMessageDialog(this, messageLabelFail, "Retry", JOptionPane.INFORMATION_MESSAGE, laughingIcon);
        }
    }

    // MODIFIES: ImageIcon
    // EFFECTS: loads and resizes blue laughing emoji icon
    private ImageIcon getResizedLaughingIcon() {
        ImageIcon saveLaughingIcon = new ImageIcon("bluelaughingemoji.jpg");
        Image nerdimage = saveLaughingIcon.getImage();
        Image resizedLaughing = nerdimage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        saveLaughingIcon = new ImageIcon(resizedLaughing);
        return saveLaughingIcon;
    }


    // MODIFIES: ImageIcon
    // EFFECTS: loads nerd gif
    private ImageIcon getNerdGIF() {
        ImageIcon successNerdIcon = new ImageIcon("animatednerd.gif");
        return successNerdIcon;
    }

    // MODIFIES: ImageIcon
    // EFFECTS: loads and resizes save entry success icon
    private ImageIcon getResizedNerdImage() {
        ImageIcon saveNerdIcon = new ImageIcon("nerdemoji.jpg");
        Image nerdimage = saveNerdIcon.getImage();
        Image resizedNerdImage = nerdimage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        saveNerdIcon = new ImageIcon(resizedNerdImage);
        return saveNerdIcon;
    }

    // MODIFIES: this
    // EFFECTS: saves the sleep journal to the file using JsonWriter
    public void saveSleepJournalToFile() {

        try {
            writer.open();
            writer.write(sleepJournal);
            writer.close();

            if (viewEntriesPanel != null) {
                viewEntriesPanel.refreshDisplay();
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error saving data to file.");
        }
    }

}
