package ui;

import model.SleepEntry; 
import model.SleepJournal;

import java.time.LocalDate;
import java.util.Scanner;

//SleepJournal App, made by referencing the Teller app
public class SleepJournalApp {
    private SleepJournal sleepJournal;
    private Scanner input; 

    public SleepJournalApp() {
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
    // EFFECTS: processes user command 
    private void processCommand(String command) {
        if (command.equals("1")) {
            writeEntry();
        } else if (command.equals("2")) {
            viewAverageHours();
        } else if (command.equals("3")) {
            viewAverageRating();
        } else if (command.equals("4")) {
            showAllEntries();
        } else {
            System.out.println("Invalid selection, try again");
        }
    }

    // MODIFIES: this
    // EFFECTS: Initializes the SleepJournal and Scanner
    private void init() {
        sleepJournal = new SleepJournal(); 
        input = new Scanner(System.in);
    }


// EFFECTS: intializes menu with options for user 
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Write new entry");
        // System.out.println("\t2 -> Delete entry");
        System.out.println("\t2 -> View average hours slept");
        System.out.println("\t3 -> View average rating");
        System.out.println("\t4 -> View all sleep entries");
        System.out.println("\tq -> Quit");
    }

    //MODIFIES: this 
    //EFFECTS: gets user to enter sleep entry details, and adds it to the journal 
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

    //EFFECTS: prints total sleep entries to screen
    private void showAllEntries() {
        System.out.println("All entries:");
        for (SleepEntry entry : sleepJournal.getAllEntries()) {
            System.out.println("\nDate:" + entry.getDate() 
                    + "\nHours slept:" + entry.getHoursSlept()
                    + "\nSleep rating: " + entry.getSleepRating() + "\nNotes:" + entry.getSleepNote());
        }

    }

    //EFFECTS: deletes selected sleep entry 
    // removeSleepEntryByIndex
    private void deleteEntry(){
    }

}