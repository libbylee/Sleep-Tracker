package persistence;

import org.junit.jupiter.api.Test;

import model.SleepEntry;
import model.SleepJournal;
import persistence.JsonReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            SleepJournal sj = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReadEmptySleepJournal() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySleepJournal.json");
        try {
            SleepJournal sj = reader.read();
            assertEquals(0, sj.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReadGeneralSleepJournal() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralSleepJournal.json");
        try {
            SleepJournal sj = reader.read();
            List<SleepEntry> sleepEntries = sj.getAllEntries();
            assertEquals(3, sleepEntries.size());

            SleepEntry firstEntry = sleepEntries.get(0);
            assertEquals("2024-10-09", firstEntry.getDate());
            assertEquals(2, firstEntry.getHoursSlept());
            assertEquals(5, firstEntry.getSleepRating());
            assertEquals("This was a terrible sleep", firstEntry.getSleepNote());

            SleepEntry secondEntry = sleepEntries.get(1);
            assertEquals("2024-10-10", secondEntry.getDate());
            assertEquals(10, secondEntry.getHoursSlept());
            assertEquals(10, secondEntry.getSleepRating());
            assertEquals("10/10 sleep for a 10/10 day", firstEntry.getSleepNote());

            SleepEntry thirdEntry = sleepEntries.get(2);
            assertEquals("2024-10-11", thirdEntry.getDate());
            assertEquals(7.5, thirdEntry.getHoursSlept());
            assertEquals(6, thirdEntry.getSleepRating());
            assertEquals("Not bad", firstEntry.getSleepNote());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
