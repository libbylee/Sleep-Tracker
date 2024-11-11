package ui;

import javax.swing.*;

import model.SleepEntry;
import model.SleepJournal;
import persistence.JsonReader;
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
    // private JFrame window;
    private MainWindow mainWindow;

    // MODIFIES: this
    // EFFECTS: Creates an add entry panel with buttons for sleepJournal functions
    public AddEntryPanel(MainWindow mainWindow, SleepJournal journal, JsonWriter writer,
            ViewEntriesPanel viewEntriesPanel) {
        //this.window = window;
        this.mainWindow = mainWindow;
        this.sleepJournal = journal;
        this.writer = writer;
        this.viewEntriesPanel = viewEntriesPanel;

        setLayout(new BorderLayout());

        JLabel label = new JLabel("Add a new entry!", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 32));

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

        add(label, BorderLayout.NORTH);
        add(entryForm, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
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
        entryForm.add(new JLabel("Enter Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        entryForm.add(dateField);

        entryForm.add(new JLabel("Enter Hours Slept:"));
        hoursSleptField = new JTextField();
        entryForm.add(hoursSleptField);

        entryForm.add(new JLabel("Enter Sleep Rating (1-10):"));
        ratingField = new JTextField();
        entryForm.add(ratingField);

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

            saveSleepJournalToFile();

            ImageIcon successNerdIcon = getNerdGIF();

            JLabel messageLabel = new JLabel("Entry added successfully!");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 42)); // Increase font size (adjust 16 to preferred size)

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
            JOptionPane.showMessageDialog(this, "Invalid input. Please check the format of your data.");
        }
    }

    // MODIFIES: ImageIcon
    // EFFECTS: loads and resizes save entry success icon
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
