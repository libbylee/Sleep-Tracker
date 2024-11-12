package ui;

import model.SleepJournal;

import javax.swing.*;
import java.awt.*;

public class AverageStatsPanel extends JPanel {
    private SleepJournal journal;
    private Color green;
    private Color lightGreen;
    private MainWindow mainWindow;
    private JLabel averageHoursLabel;
    private JLabel averageRatingsLabel;

    // EFFECTS: constructs a panel with average hours, average ratings, a go back
    // and refresh button
    public AverageStatsPanel(MainWindow mainWindow, SleepJournal journal) {
        this.journal = journal;
        this.mainWindow = mainWindow;
        setLayout();

        JLabel titleLabel = setTitleLabel();
        JPanel statsPanel = new JPanel(new GridLayout(2, 1, 8, 8));
        statsPanel.setBackground(green);

        setHoursIcon(journal);
        setRatingsIcon(journal);
        JButton goBackButton = setBackButton();
        JButton updateButton = getUpdateButton();

        statsPanel.add(averageHoursLabel);
        statsPanel.add(averageRatingsLabel);

        addElements(titleLabel, statsPanel, goBackButton, updateButton);
    }

    // MODIFIES: this
    // EFFECTS: adds elements onto the border
    private void addElements(JLabel titleLabel, JPanel statsPanel, JButton goBackButton, JButton updateButton) {
        add(titleLabel, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.CENTER);
        add(goBackButton, BorderLayout.SOUTH);
        add(updateButton, BorderLayout.EAST);
    }

    // MODIFIES: this
    // EFFECTS: creates and sets the layout of the page
    private void setLayout() {
        setLayout(new GridLayout(3, 1, 10, 10));
        setBackground(green);
        green = new Color(223, 255, 229);
        lightGreen = new Color(238, 255, 240);

        setLayout(new BorderLayout(10, 10));
        setBackground(green);
    }

    // MODIFIES: this
    // EFFECTS: create the title label seen on top of the page
    private JLabel setTitleLabel() {
        JLabel titleLabel = new JLabel("Sleep Stats", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        return titleLabel;
    }

    // MODIFIES: this
    // EFFECTS: creates a button used for refreshing the stats
    private JButton getUpdateButton() {
        JButton updateButton = new JButton("Refresh");
        updateButton.setFont(new Font("Arial", Font.PLAIN, 16));
        updateButton.addActionListener(e -> updateStats());
        return updateButton;
    }

    // MODIFIES: this
    // EFFECTS: intializes back button
    private JButton setBackButton() {
        JButton goBackButton = new JButton("Go Back");
        goBackButton.setFont(new Font("Arial", Font.BOLD, 20));
        goBackButton.setPreferredSize(new Dimension(100, 50));
        goBackButton.addActionListener(e -> goBackToMainMenu());
        return goBackButton;
    }

    // MODIFIES: this
    // EFFECTS: intializes ratings tab
    private void setRatingsIcon(SleepJournal journal) {
        ImageIcon ratingsIcon = getRatingsIcon();
        averageRatingsLabel = new JLabel("Average Sleep Rating: " + journal.averageSleepRating(), ratingsIcon,
                JLabel.CENTER);
        averageRatingsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        averageRatingsLabel.setOpaque(true);
        averageRatingsLabel.setBackground(lightGreen);
        averageRatingsLabel.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));
    }

    // MODIFIES: this
    // EFFECTS: intializes hours tab
    private void setHoursIcon(SleepJournal journal) {
        ImageIcon hoursIcon = getHoursIcon();
        averageHoursLabel = new JLabel("Average Hours Slept: " + journal.averageHoursSlept(), hoursIcon, JLabel.CENTER);
        averageHoursLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        averageHoursLabel.setOpaque(true);
        averageHoursLabel.setBackground(lightGreen);
        averageHoursLabel.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));
    }

    // MODIFIES: this
    // EFFECTS: updates the average stats labels with current values from the
    // journal
    public void updateStats() {
        averageHoursLabel.setText("Average Hours Slept: " + journal.averageHoursSlept());
        averageRatingsLabel.setText("Average Sleep Rating: " + journal.averageSleepRating());
        revalidate();
        repaint();
    }

    // MODIFIES: ImageIcon
    // EFFECTS: resizes time icon to appropriate size
    private ImageIcon getHoursIcon() {
        ImageIcon saveTimeIcon = new ImageIcon("timeicon.png");
        Image timeIcon = saveTimeIcon.getImage();
        Image resizedTime = timeIcon.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        saveTimeIcon = new ImageIcon(resizedTime);
        return saveTimeIcon;
    }

    // MODIFIES: ImageIcon
    // EFFECTS: resizes rating icon to appropriate size
    private ImageIcon getRatingsIcon() {
        ImageIcon saveStarsIcon = new ImageIcon("starsclipart.png");
        Image starIcon = saveStarsIcon.getImage();
        Image resizedStars = starIcon.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        saveStarsIcon = new ImageIcon(resizedStars);
        return saveStarsIcon;
    }

    // EFFECTS: goes back to main menu
    private void goBackToMainMenu() {
        mainWindow.switchToMainMenu();
    }
}
