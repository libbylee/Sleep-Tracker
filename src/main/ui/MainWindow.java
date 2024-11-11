package ui;

import javax.swing.*;

import model.SleepJournal;
import persistence.JsonWriter;
import persistence.JsonReader;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;

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
    private MainWindow mainWindow;

    public MainWindow() {
        journal = new SleepJournal();
        writer = new JsonWriter("data/sleepJournal.json");
        reader = new JsonReader("data/sleepJournal.json");

        frame = new JFrame("Sleep Tracking Journal");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();

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
                promptSaveData();
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

    private void promptSaveData() {
        int result = JOptionPane.showConfirmDialog(frame, "Save data to file?", "Save Data",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            addEntryPanel.saveSleepJournalToFile();
        }
        frame.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
