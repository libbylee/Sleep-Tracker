package ui;

import javax.swing.*;

import model.SleepJournal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import model.SleepJournal;

public class MainWindow {
    JFrame frame;
    JPanel panel;
    SleepJournal journal;

    public MainWindow() {
        journal = new SleepJournal();

        frame = new JFrame("Sleep Tracking Journal");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        // frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        initializeMenu();

        frame.setVisible(true);
    }

    private void initializeMenu() {
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel mainMenu = new MainMenu();
        JPanel addEntryPanel = new AddEntryPanel(journal);
        JPanel viewEntriesPanel = new ViewEntriesPanel();

        tabbedPane.addTab("Main Menu", mainMenu);
        tabbedPane.addTab("Add Entry", addEntryPanel);
        tabbedPane.addTab("View Entries", viewEntriesPanel);

        frame.setLayout(new BorderLayout());

        frame.add(tabbedPane, BorderLayout.CENTER);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                promptSaveData(); // Ask user if they want to save data before exiting
            }
        });
    }

    private void promptSaveData() {
        int result = JOptionPane.showConfirmDialog(frame, "Save data to file?", "Save Data",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            // Save data logic here
        }
        frame.dispose(); // Close the application
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
