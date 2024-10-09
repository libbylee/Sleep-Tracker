package model;

// import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SleepEntryTest {
    private SleepEntry sleepEntryTest; 
    private LocalDate date; 
    private double hoursSlept; 
    private int sleepRating; 
    private String sleepNote;

    @BeforeEach
    void runBefore() {
        date = LocalDate.of(2024,1,1);
        sleepRating = 0;
        hoursSlept = 0.0;
        sleepNote = "test";
        sleepEntryTest = new SleepEntry(date, hoursSlept, sleepRating, sleepNote);
    }

    @Test
    void testConstructor() {
        assertEquals((LocalDate.of(2024, 1, 1)), sleepEntryTest.getDate());
        assertEquals(0.0, sleepEntryTest.getHoursSlept());
        assertEquals(0, sleepEntryTest.getSleepRating());
        assertEquals("test", sleepEntryTest.getSleepNote());
    }

    @Test
    void testSetSleepNote() {
        assertEquals("test", sleepEntryTest.getSleepNote());
        sleepEntryTest.setSleepNote("This is a test.");
        assertEquals("This is a test.", sleepEntryTest.getSleepNote());
        sleepEntryTest.setSleepNote("This is a second test.");
        assertEquals("This is a second test.", sleepEntryTest.getSleepNote());
    }

    @Test
    void testSetEmptySleepNote() {
        sleepEntryTest.setSleepNote(sleepNote);
        assertEquals("test", sleepEntryTest.getSleepNote());
        sleepEntryTest.setSleepNote("");
        assertEquals("test", sleepEntryTest.getSleepNote());
    }

    @Test
    void testSetNullSleepNote() {
        sleepEntryTest.setSleepNote(sleepNote);
        assertEquals("test", sleepEntryTest.getSleepNote());
        sleepEntryTest.setSleepNote(null);
        assertEquals("test", sleepEntryTest.getSleepNote());
    }


    @Test
    void testSetSleepRating() {
        sleepEntryTest.setSleepRating(3);
        assertEquals(3, sleepEntryTest.getSleepRating());
        sleepEntryTest.setSleepRating(9);
        assertEquals(9, sleepEntryTest.getSleepRating());
    }

}
