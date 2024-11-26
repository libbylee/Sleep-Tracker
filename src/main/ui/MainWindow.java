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
    AverageStatsPanel averageStatsPanel;

    // EFFECTS: loads a main menu which allows user to choose where they want to go
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

    // MODIFIES: this
    // EFFECTS: intializes the main menu
    private void initializeMenu() {
        tabbedPane = new JTabbedPane();

        mainMenu = new MainMenuPanel(this, journal, writer, reader);
        viewEntriesPanel = new ViewEntriesPanel(this, journal, reader);
        addEntryPanel = new AddEntryPanel(this, journal, writer, viewEntriesPanel);
        averageStatsPanel = new AverageStatsPanel(this, journal);

        tabbedPane.addTab("Main Menu", mainMenu);
        tabbedPane.addTab("Add Entry", addEntryPanel);
        tabbedPane.addTab("View Entries", viewEntriesPanel);
        tabbedPane.addTab("Stats", averageStatsPanel);

        frame.setLayout(new BorderLayout());

        frame.getContentPane().add(tabbedPane);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (model.Event event : model.EventLog.getInstance()) {
                    System.out.println(event.toString());
                }
                JDialog dialog = new JDialog(frame, "Confirmation", true);
                promptSaveData(dialog);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: switches back to main menu
    public void switchToMainMenu() {
        if (tabbedPane.getTabCount() > 0) {
            tabbedPane.setSelectedIndex(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: switches to add entry panel
    public void switchToAddEntry() {
        if (tabbedPane.getTabCount() > 1) {
            tabbedPane.setSelectedIndex(1);
        }
    }

    // MODIFIES: this
    // EFFECTS: switches to view entry panel
    public void switchToViewEntries() {
        if (tabbedPane.getTabCount() > 2) {
            tabbedPane.setSelectedIndex(2);
        }
    }

    // MODIFIES: this
    // EFFECTS: switches to average stats panel
    public void switchToAverageStatsPanel() {
        if (tabbedPane.getTabCount() > 3) {
            tabbedPane.setSelectedIndex(3);
        }
    }

    // MODIFIES: this
    // EFFECTS: returns frame
    public JFrame getFrame() {
        return frame;
    }

    // MODIFIES: this
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
