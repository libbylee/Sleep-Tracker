package ui;

import model.SleepEntry; 
import model.SleepJournal;

import java.util.Scanner;

//SleepJournal App, referencing the Teller app
public class SleepJournalApp {
    private SleepJournal sleepJournal;
    private Scanner input; 

public SleepJournalApp() {
    runSleepJournal();
}

// MODIFIES: this
// EFFECTS: processes user command, runs the app
private void runSleepJournal(){
    boolean running = true;
    String command = null;

    init();

    while (running) {
        displayMenu();
        command = input.next().toLowerCase();

        if (command.equals("q")){
            running = false;
        } else {
            processCommand(command);
        }
    }
    
    System.out.println("See you next time!");

    }

// EFFECTS: processes commands for user 

// EFFECTS: intializes menu with options for user 
private void displayMenu(){
    System.out.println("");
}

//MODIFIES: this 
//EFFECTS: gets user to enter sleep entry details, and adds it to the journal 
private void writeEntry(){

}

//EFFECTS: prints total sleep entries to screen
private void showAllEntries(){

}

// EFFECTS: prints total hours onto screen
private void viewTotalHours(){

}

// EFFECTS: prints total ratings onto screen
private void viewTotalRatings(){
}

}
