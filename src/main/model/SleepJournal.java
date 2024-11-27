package model;

import org.json.JSONArray;
import org.json.JSONObject;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;



import persistence.Writable;

public class SleepJournal implements Writable {
    private ArrayList<SleepEntry> sleepJournal;

    // EFFECTS: makes an empty list, initializes the list
    public SleepJournal() {
        sleepJournal = new ArrayList<>();
        EventLog.getInstance().logEvent(new Event("SleepJournal initialized."));
    }

    // REQUIRES: SleepEntry != null
    // MODIFIES: this
    // EFFECTS: adds a new sleep entry to the journal
    public void addSleepEntryToSleepJournal(SleepEntry entry) {
        sleepJournal.add(entry);
        EventLog.getInstance().logEvent(new Event("SleepEntry added: \n Date : " + entry.getDate()
                + "\n Rating : " + entry.getSleepRating()));
    }

    // REQUIRES: SleepEntry != null
    // MODIFIES: this
    // EFFECTS: setsleepJournal list to a new, sorted one
    public void setEntries(List<SleepEntry> entries) {
        this.sleepJournal = new ArrayList<>(entries);
        EventLog.getInstance().logEvent(new Event("SleepJournal entries updated."));
    }

    // REQUIRES: index number is a valid index in the SleepEntry list
    // MODIFIES: this
    // EFFECTS: removes a sleep entry in the journal by index (not 0 based)
    public void removeSleepEntryByIndex(int index) {
        if (index >= 1 && index <= sleepJournal.size()) {
            SleepEntry removed = sleepJournal.remove(index - 1);
            EventLog.getInstance().logEvent(new Event("Removed sleep entry: " + removed.toString()));
        }
    }

    // REQUIRES: index number is a valid index in the SleepEntry list
    // EFFECTS: returns the sleep entry at the specified position
    public SleepEntry getEntry(int index) {
        return sleepJournal.get(index - 1);

    }

    // EFFECTS: returns the number of sleep entries
    public int size() {
        return sleepJournal.size();
    }

    // EFFECTS: returns a new copy of all sleepentries in the journal
    public ArrayList<SleepEntry> getAllEntries() {
        EventLog.getInstance().logEvent(new Event("Accessed all SleepEntries."));
        return new ArrayList<>(sleepJournal);
    }

    // EFFECTS: Takes sleep entries and returns the total sleep hours in one Journal
    public String averageHoursSlept() {
        if (sleepJournal.isEmpty()) {
            return "0.0";
        }
        double totalHoursSlept = 0.0;
        for (SleepEntry sleepEntry : sleepJournal) {
            totalHoursSlept += sleepEntry.getHoursSlept();
        }
        double averageHoursSlept = totalHoursSlept / sleepJournal.size();
        DecimalFormat df = new DecimalFormat("#.0");
        EventLog.getInstance().logEvent(new Event("Calculated average hours slept: " + averageHoursSlept));
        return df.format(averageHoursSlept);
    }

    // EFFECTS: Takes sleep ratings and returns the total sleep ratings in one
    // journal
    public int averageSleepRating() {
        if (sleepJournal.isEmpty()) {
            return 0;
        }
        int totalSleepRatings = 0;
        for (SleepEntry sleepEntry : sleepJournal) {
            totalSleepRatings += sleepEntry.getSleepRating();
        }
        int averageRating = totalSleepRatings / sleepJournal.size();
        EventLog.getInstance().logEvent(new Event("Calculated average sleep rating: " + averageRating));
        return averageRating;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("sleepJournal", sleepJournalToJson());
        EventLog.getInstance().logEvent(new Event("SleepJournal serialized to JSON."));
        return json;
    }

    // EFFECTS: returns things in the sleepjournal as a JSON array
    private JSONArray sleepJournalToJson() {
        JSONArray jsonArray = new JSONArray();

        for (SleepEntry entry : sleepJournal) {
            jsonArray.put(entry.toJson());
        }

        return jsonArray;
    }

    // MODIFIES: this
    // EFFECTS: Sorts entries by sleep rating in descending order
    public void sortByRating() {
        sleepJournal.sort((entry1, entry2) -> Double.compare(entry2.getSleepRating(), entry1.getSleepRating()));
        EventLog.getInstance().logEvent(new Event("SleepJournal sorted by sleep rating."));
    }

    // MODIFIES: this
    // EFFECTS: Sorts entries by hours slept in descending order
    public void sortByHoursSlept() {
        sleepJournal.sort((entry1, entry2) -> Double.compare(entry2.getHoursSlept(), entry1.getHoursSlept()));
        EventLog.getInstance().logEvent(new Event("SleepJournal sorted by hours slept."));
    }

    // EFFECTS: prints all logged events to the console
    public void printEventLog() {
        EventLog log = EventLog.getInstance();
        for (Event event : log) {
            System.out.println(event.getDate() + " - " + event.getDescription());
        }
    }
}
