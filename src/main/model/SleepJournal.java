package model;

import java.text.DecimalFormat;
import java.util.ArrayList;

//shows a list of all sleep entries
public class SleepJournal {
    private ArrayList<SleepEntry> sleepJournal; 

    //EFFECTS: makes an empty list, initializes the list 
    public SleepJournal() {
        sleepJournal = new ArrayList<>(); 
    }

    //REQUIRES: SleepEntry != null
    //MODIFIES: this
    //EFFECTS: adds a new sleep entry to the journal
    public void addSleepEntryToSleepJournal(SleepEntry entry) {
        sleepJournal.add(entry);
    }

    //REQUIRES: index number is a valid index in the SleepEntry list
    //MODIFIES: this
    //EFFECTS: removes a sleep entry in the journal by index (not 0 based)
    public void removeSleepEntryByIndex(int index) {
        if (index >= 1 && index <= sleepJournal.size()) {
            sleepJournal.remove(index - 1);
        }
    }

    //REQUIRES: index number is a valid index in the SleepEntry list
    //EFFECTS: returns the sleep entry at the specified position 
    public SleepEntry getEntry(int index) {
        return sleepJournal.get(index - 1);
    }

    //EFFECTS: returns the number of sleep entries
    public int size() {
        return sleepJournal.size();
    }

    //EFFECTS: returns a new copy of all sleepentries in the journal 
    public ArrayList<SleepEntry> getAllEntries() {
        return new ArrayList<>(sleepJournal); 
    }

    //EFFECTS: Takes sleep entries and returns the total sleep hours in one Journal
    public String averageHoursSlept() {
        if (sleepJournal.isEmpty()){
            return "0.0";
        }
        double totalHoursSlept = 0.0;
        for (SleepEntry sleepEntry : sleepJournal){
            totalHoursSlept += sleepEntry.getHoursSlept();
        }
        double averageHoursSlept = totalHoursSlept / sleepJournal.size();
        DecimalFormat df = new DecimalFormat("#.0");
        return df.format(averageHoursSlept);
    }

    //EFFECTS: Takes sleep ratings and returns the total sleep ratings in one journal
    public int averageSleepRating(){
        if (sleepJournal.isEmpty()){
            return 0;
        }
        int totalSleepRatings = 0;
        for(SleepEntry sleepEntry : sleepJournal){
            totalSleepRatings += sleepEntry.getSleepRating();
        }
        return totalSleepRatings / sleepJournal.size();
    }
}


