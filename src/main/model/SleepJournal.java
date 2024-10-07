package model;

import java.util.ArrayList;

//shows a list of all sleep entries
public class SleepJournal {
    private ArrayList<SleepEntry> sleepEntries; 

    //EFFECTS: makes an empty list, initializes the list 
    public SleepJournal(){
        sleepEntries = new ArrayList<>(); 
    }

    //REQUIRES: SleepEntry != null
    //MODIFIES: this
    //EFFECTS: adds a new sleep entry to the journal
    public void addSleepEntryToSleepJournal(SleepEntry entry){
        sleepEntries.add(entry);
    }
    //REQUIRES: index number is a valid index in the SleepEntry list
    //MODIFIES: this
    //EFFECTS: removes a sleep entry in the journal by index
    public void removeSleepEntryByIndex(int index){
        index = 0;
    }
}

