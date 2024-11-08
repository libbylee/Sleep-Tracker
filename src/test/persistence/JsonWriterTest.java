package persistence;

import org.junit.jupiter.api.Test;

import model.SleepJournal;
import model.SleepEntry;

import java.time.LocalDate;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {

        try {
            SleepJournal sj = new SleepJournal();
            JsonWriter writer = new JsonWriter(".data/noSuchFile.json");
            writer.open();
            fail("IOException was expected, but not thrown");
        } catch (IOException e) {
            System.out.println("IO Exception caught");
            // pass
        }
    }

    @Test
    void testWriterEmptySleepJournal() {
        try {
            SleepJournal sj = new SleepJournal();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptySleepJournal.json");
            writer.open();
            writer.write(sj);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptySleepJournal.json");
            sj = reader.read();
            assertEquals(0, sj.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralSleepJournal() {
        try {
            SleepJournal sj = new SleepJournal();
            SleepEntry entry1 = new SleepEntry(LocalDate.parse("2024-10-09"), 2, 5, "This was a terrible sleep");
            SleepEntry entry2 = new SleepEntry(LocalDate.parse("2024-10-10"), 10, 10, "10/10 sleep for a 10/10 day");
            SleepEntry entry3 = new SleepEntry(LocalDate.parse("2024-10-11"), 7.5, 6, "Not bad");
            sj.addSleepEntryToSleepJournal(entry1);
            sj.addSleepEntryToSleepJournal(entry2);
            sj.addSleepEntryToSleepJournal(entry3);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralSleepJouranl.json");
            writer.open();
            writer.write(sj);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralSleepJournal.json");
            sj = reader.read();
            List<SleepEntry> entries = sj.getAllEntries();
            assertEquals(3, entries.size());
            checkSleepEntry("2024-10-09", 2, 5, "This was a terrible sleep", entries.get(0));
            checkSleepEntry("2024-10-10", 10, 10, "10/10 sleep for a 10/10 day", entries.get(1));
            checkSleepEntry("2024-10-11", 7.5, 6, "Not bad", entries.get(2));

        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }

    }
}
