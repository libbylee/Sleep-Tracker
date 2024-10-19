package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import model.SleepEntry;

public class JsonTest {
    protected void checkSleepEntry(LocalDate date, double hoursSlept, int sleepRating, String sleepNote, SleepEntry entry) {
        assertEquals(date, entry.getDate());
        assertEquals(hoursSlept, entry.getHoursSlept());
        assertEquals(sleepRating, entry.getSleepRating());
        assertEquals(sleepNote, entry.getSleepNote());
    }
}

