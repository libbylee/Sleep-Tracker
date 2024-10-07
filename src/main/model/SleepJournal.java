package model;

import java.util.ArrayList;

//shows a list of all sleep entries
public class SleepJournal {
    private ArrayList<SleepEntry> sleepJournal; 

    //EFFECTS: makes an empty list, initializes the list 
    public SleepJournal(){
        sleepJournal = new ArrayList<>(); 
    }

    //REQUIRES: SleepEntry != null
    //MODIFIES: this
    //EFFECTS: adds a new sleep entry to the journal
    public void addSleepEntryToSleepJournal(SleepEntry entry){
        sleepJournal.add(entry);
    }

    //REQUIRES: index number is a valid index in the SleepEntry list
    //MODIFIES: this
    //EFFECTS: removes a sleep entry in the journal by index
    public void removeSleepEntryByIndex(int index){
        if (index >= 0 && index <= sleepJournal.size())
        sleepJournal.remove(index);
        else{
            System.out.println("Invalid");
        }
    }

    //REQUIRES: index number is a valid index in the SleepEntry list
    //EFFECTS: returns the sleep entry at the specified index
    public SleepEntry getEntry(int index) {
        return sleepJournal.get(index);
    }
    
    //EFFECTS: returns the number of sleep entries
    public int size() {
        return sleepJournal.size();
    }
}

