package ui;

import javax.swing.*;

import model.SleepJournal;
import persistence.JsonWriter;
import persistence.JsonReader;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Font;

public class MainWindow {
    JFrame frame;
    JPanel panel;
    SleepJournal journal;
    JsonWriter writer;
    JsonReader reader;
    ViewEntriesPanel viewEntriesPanel;
    AddEntryPanel addEntryPanel;
    MainMenuPanel mainMenu;
    JFrame window;
    JTabbedPane tabbedPane;

    public MainWindow() {
        journal = new SleepJournal();
        writer = new JsonWriter("data/SleepJournal.json");
        reader = new JsonReader("data/SleepJournal.json");

        frame = new JFrame("Sleep Tracking Journal");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();

        loadExistingEntries();

        initializeMenu();

        frame.setVisible(true);
    }

    private void initializeMenu() {
        tabbedPane = new JTabbedPane();

        mainMenu = new MainMenuPanel(this, journal, writer, reader);
        viewEntriesPanel = new ViewEntriesPanel(this, journal, reader);
        addEntryPanel = new AddEntryPanel(this, journal, writer, viewEntriesPanel);

        tabbedPane.addTab("Main Menu", mainMenu);
        tabbedPane.addTab("Add Entry", addEntryPanel);
        tabbedPane.addTab("View Entries", viewEntriesPanel);

        frame.setLayout(new BorderLayout());

        // frame.add(tabbedPane, BorderLayout.CENTER);
        frame.getContentPane().add(tabbedPane);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JDialog dialog = new JDialog(frame, "Confirmation", true);
                promptSaveData(dialog);
            }
        });
    }

    public void switchToMainMenu() {
        if (tabbedPane.getTabCount() > 0) {
            tabbedPane.setSelectedIndex(0);
        }
    }

    public void switchToAddEntry() {
        if (tabbedPane.getTabCount() > 1) {
            tabbedPane.setSelectedIndex(1);
        }
    }

    public void switchToViewEntries() {
        if (tabbedPane.getTabCount() > 2) {
            tabbedPane.setSelectedIndex(2);
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    // // EFFECTS: shows prompting to ask to save data
    // private void promptSaveData() {
    // int result = JOptionPane.showConfirmDialog(frame, "Save data to file?", "Save
    // Data",
    // JOptionPane.YES_NO_OPTION);
    // if (result == JOptionPane.YES_OPTION) {
    // addEntryPanel.saveSleepJournalToFile();
    // }
    // frame.dispose();
    // }

    // EFFECTS: shows prompting to ask to save data
    private void promptSaveData(JDialog dialog) {
        ImageIcon wonder = new ImageIcon("thinkinganimated.gif");

        JPanel messagePanel = new JPanel(new BorderLayout());
        JLabel messageLabel = new JLabel("Are you sure you want to exit?", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel imageLabel = new JLabel(wonder);
        messagePanel.add(messageLabel, BorderLayout.CENTER);
        messagePanel.add(imageLabel, BorderLayout.WEST);

        int result = JOptionPane.showConfirmDialog(dialog, messagePanel, "Exit?",
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            addEntryPanel.saveSleepJournalToFile();
            frame.dispose();
        } else {
            dialog.dispose();
        }
    }

    // MODIFIES: this
    // EFFECTS: Loads entries from file
    private void loadExistingEntries() {
        try {
            journal = reader.read();
        } catch (IOException e) {
            journal = new SleepJournal();
            JOptionPane.showMessageDialog(frame, "No existing entries found. Starting a new journal.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
