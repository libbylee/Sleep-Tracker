package model;

import java.time.LocalDate;

//Represents a single sleep journal entry having a date, the hours slept, quality, and notes about the sleep 
public class SleepEntry {
    private LocalDate date; // the date of the sleep entry
    private double hoursSlept; //the number of hours slept
    private int sleepRating; //the quality of sleep rated on a scale from 1-10
    private String sleepNote; // Notes about the sleep

    //REQUIRES: sleepRating >=0, sleepNote has a non=zero length
    //MODIFIES: this 
    //EFFECTS: sleepentry is given a date, numbers of hours slept, a rating, and a note

    public SleepEntry(LocalDate date, double hoursSlept, int sleepRating, String sleepNote) {
        this.date = date; 
        this.hoursSlept = hoursSlept; 
        this.sleepRating = sleepRating; 
        this.sleepNote = sleepNote;
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

    public void setSleepNote(String sleepNote) {
        this.sleepNote = sleepNote; 
    }

    public String getSleepNote() {
        return sleepNote; 
    }
}
