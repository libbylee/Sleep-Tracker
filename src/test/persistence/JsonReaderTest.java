package persistence;

import org.junit.jupiter.api.Test;

import model.SleepEntry;
import model.SleepJournal;
import java.io.IOException;
import java.util.List;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
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
            List<SleepEntry> sleepJournal = sj.getAllEntries();
            assertEquals(3, sleepJournal.size());

            SleepEntry firstEntry = sleepJournal.get(0);
            assertEquals(LocalDate.parse("2024-10-09"), firstEntry.getDate());
            assertEquals(2, firstEntry.getHoursSlept());
            assertEquals(5, firstEntry.getSleepRating());
            assertEquals("This was a terrible sleep", firstEntry.getSleepNote());

            SleepEntry secondEntry = sleepJournal.get(1);
            assertEquals(LocalDate.parse("2024-10-10"), secondEntry.getDate());
            assertEquals(10, secondEntry.getHoursSlept());
            assertEquals(10, secondEntry.getSleepRating());
            assertEquals("10/10 sleep for a 10/10 day", secondEntry.getSleepNote());

            SleepEntry thirdEntry = sleepJournal.get(2);
            assertEquals(LocalDate.parse("2024-10-11"), thirdEntry.getDate());
            assertEquals(7.5, thirdEntry.getHoursSlept());
            assertEquals(6, thirdEntry.getSleepRating());
            assertEquals("Not bad", thirdEntry.getSleepNote());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
