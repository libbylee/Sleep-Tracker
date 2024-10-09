package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SleepJournalTest {

    private SleepJournal sleepJournalTest;
    private SleepEntry entry1;
    private SleepEntry entry2;
    private SleepEntry entry3;

    @BeforeEach
    void runBefore() {
        sleepJournalTest = new SleepJournal();
        entry1 = new SleepEntry((LocalDate.of(2024, 1, 1)), 0.0, 0, "My sleep was terrible.");
        entry2 = new SleepEntry((LocalDate.of(2024, 2, 3)), 8.0, 1, "My sleep was amazing.");
        entry3 = new SleepEntry((LocalDate.of(2024, 3, 4)), 6.5, 5, "It was okay.");
    }

    @Test
    void testConstructor() {
        assertEquals(0, sleepJournalTest.size());
    }

    @Test
    void testAddingEntry() {
        sleepJournalTest.addSleepEntryToSleepJournal(entry1);
        assertEquals(1, sleepJournalTest.size());
    }

    @Test
    void testAddingMultipleEntries() {
        sleepJournalTest.addSleepEntryToSleepJournal(entry1);
        sleepJournalTest.addSleepEntryToSleepJournal(entry2);
        assertEquals(2, sleepJournalTest.size());
    }

    @Test
    void testremoveSleepEntryByIndex() {
        sleepJournalTest.addSleepEntryToSleepJournal(entry1);
        sleepJournalTest.addSleepEntryToSleepJournal(entry2);
        sleepJournalTest.addSleepEntryToSleepJournal(entry3);
        assertEquals(entry2, sleepJournalTest.getEntry(2));
        sleepJournalTest.removeSleepEntryByIndex(2);
        assertEquals(2, sleepJournalTest.size());
        assertEquals(entry3, sleepJournalTest.getEntry(2));
    }

    @Test
    void testGetAllEntries() {
        sleepJournalTest.addSleepEntryToSleepJournal(entry1);
        sleepJournalTest.addSleepEntryToSleepJournal(entry2);
        sleepJournalTest.addSleepEntryToSleepJournal(entry3);

        ArrayList<SleepEntry> expectedEntries = new ArrayList<>();

        expectedEntries.add(entry1);
        expectedEntries.add(entry2);
        expectedEntries.add(entry3);
        
        assertEquals(expectedEntries, sleepJournalTest.getAllEntries());
    }

    @Test
    void testAverageHoursSlept() {
        sleepJournalTest.addSleepEntryToSleepJournal(entry1);
        sleepJournalTest.addSleepEntryToSleepJournal(entry2);
        sleepJournalTest.addSleepEntryToSleepJournal(entry3);
        assertEquals("4.8", sleepJournalTest.averageHoursSlept());
    }

    @Test
    void testAverageSleepRating() {
        sleepJournalTest.addSleepEntryToSleepJournal(entry1);
        sleepJournalTest.addSleepEntryToSleepJournal(entry2);
        sleepJournalTest.addSleepEntryToSleepJournal(entry3);
        assertEquals(2, sleepJournalTest.averageSleepRating());
    }

}
