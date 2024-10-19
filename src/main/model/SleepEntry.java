package model;

import java.time.LocalDate;
import org.json.JSONObject;

import persistence.Writable;

//Represents a single sleep journal entry having a date, the hours slept, quality, and notes about the sleep 
public class SleepEntry implements Writable {
    private LocalDate date; // the date of the sleep entry
    private double hoursSlept; // the number of hours slept
    private int sleepRating; // the quality of sleep rated on a scale from 1-10
    private String sleepNote; // Notes about the sleep

    // REQUIRES: sleepRating >=0, sleepNote has a non=zero length
    // MODIFIES: this
    // EFFECTS: sleepentry is given a date, numbers of hours slept, a rating, and a
    // note

    public SleepEntry(LocalDate date, double hoursSlept, int sleepRating, String sleepNote) {
        this.date = date;
        this.hoursSlept = hoursSlept;
        this.sleepRating = sleepRating;
        this.sleepNote = sleepNote;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", date.toString());
        json.put("hoursSlept", hoursSlept);
        json.put("sleepRating", sleepRating);
        json.put("sleepNote", sleepNote);
        return json;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getHoursSlept() {
        return hoursSlept;
    }

    public void setSleepRating(int sleepRating) {
        this.sleepRating = sleepRating;
    }

    public double getSleepRating() {
        return sleepRating;
    }

    // REQUIRES: sleepNote has a non-zero length
    public void setSleepNote(String sleepNote) {
        if (sleepNote != null && (!sleepNote.isEmpty())) {
            this.sleepNote = sleepNote;
        }
    }

    public String getSleepNote() {
        return sleepNote;
    }
}
