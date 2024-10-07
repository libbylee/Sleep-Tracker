package ui;

import model.SleepJournal;
import java.util.Scanner;

//SleepJournal App
public class SleepJournalApp {
    private SleepJournal sleepJournal;
    private Scanner input; 

public SleepJournalApp() {
    runSleepJournal();
}

// MODIFIES: this
// EFFECTS: processes user command
private void runSleepJournal(){
System.out.println("running");
}

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
