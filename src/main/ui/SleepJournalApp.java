package ui;

import model.SleepEntry;
import model.SleepJournal;

import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

//Represents SleepJournal App, made by referencing the Teller app
public class SleepJournalApp {
    private static final String JSON_STORE = "./data/SleepJournal.json";
    private SleepJournal sleepJournal;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public SleepJournalApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runSleepJournal();
    }

    // MODIFIES: this
    // EFFECTS: processes user command, runs the app
    private void runSleepJournal() {
        boolean running = true;
        String command = null;
        init();

        while (running) {
            displayMenu();
            command = input.next().toLowerCase();

            if (command.equals("q")) {
                running = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("See you next time!");
    }

    // MODIFIES: this
    // EFFECTS: Initializes the SleepJournal and Scanner
    private void init() {
        sleepJournal = new SleepJournal();
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            writeEntry();
        } else if (command.equals("2")) {
            deleteEntry();
        } else if (command.equals("3")) {
            viewAverageHours();
        } else if (command.equals("4")) {
            viewAverageRating();
        } else if (command.equals("5")) {
            showAllEntries();
        } else if (command.equals("6")) {
            saveSleepJournal();
        } else if (command.equals("7")) {
            loadSleepJournal();
        } else {
            System.out.println("Invalid selection, try again");
        }
    }

    // EFFECTS: intializes menu with options for user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Write new entry");
        System.out.println("\t2 -> Delete entry");
        System.out.println("\t3 -> View average hours slept");
        System.out.println("\t4 -> View average rating");
        System.out.println("\t5 -> View all sleep entries");
        System.out.println("\t6 -> Save sleep journal to file");
        System.out.println("\t7 -> Load sleep journal from file");
        System.out.println("\tq -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: gets user to enter sleep entry details, and adds it to the journal
    private void writeEntry() {
        System.out.println("Enter date in the form of (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(input.next());

        System.out.println("Enter the hours you've slept: ");
        double hoursSlept = input.nextDouble();

        System.out.println("What would you rate your your sleep from 1-10? ");
        int sleepRating = input.nextInt();
        input.nextLine();

        System.out.println("Enter your notes: ");

        String sleepNote = input.nextLine();

        SleepEntry entry = new SleepEntry(date, hoursSlept, sleepRating, sleepNote);
        sleepJournal.addSleepEntryToSleepJournal(entry);

        System.out.println("You have added a sleep entry!");

    }

    // EFFECTS: prints total hours onto screen
    private void viewAverageHours() {
        System.out.println("Average hours slept: " + sleepJournal.averageHoursSlept());
    }

    // EFFECTS: prints total ratings onto screen
    private void viewAverageRating() {
        System.out.println("Average rating: " + sleepJournal.averageSleepRating());
    }

    // EFFECTS: prints total sleep entries to screen
    private void showAllEntries() {
        System.out.println("All entries:");
        for (SleepEntry entry : sleepJournal.getAllEntries()) {
            System.out.println("\nDate:" + entry.getDate()
                    + "\nHours slept:" + entry.getHoursSlept()
                    + "\nSleep rating: " + entry.getSleepRating() + "\nNotes:" + entry.getSleepNote());
        }
    }

    // EFFECTS: deletes selected sleep entry
    private void deleteEntry() {
        {
            showAllEntries();
            System.out.println("Enter the sleep entry you'd like to delete: ");
            Integer index = input.nextInt();
            System.out.println("Are you sure you want to delete this entry? \n Choose y for yes and n for no. ");
            String answer = input.next().toLowerCase();
            if (answer.equals("y")) {
                sleepJournal.removeSleepEntryByIndex(index);
                System.out.println("Entry successfully deleted. ");
            } else if (answer.equals("n")) {
                System.out.println("Entry not deleted. ");
            } else {
                System.out.println("Invalid selection, try again");
            }
        }
    }

    // EFFECTS: saves the sleepjournal to a file
    private void saveSleepJournal() {
        try {
            jsonWriter.open();
            jsonWriter.write(sleepJournal);
            jsonWriter.close();
            System.out.println("Saved to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file:" + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads sleepjournal from file
    private void loadSleepJournal() {
        try {
            sleepJournal = jsonReader.read();
            System.out.println("Loaded from" + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file" + JSON_STORE);
        }
    }
}
