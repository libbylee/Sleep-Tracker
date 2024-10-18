package persistence;

import java.io.IOException;

import model.SleepJournal;

//code is modified from JSONSerializerDemo

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source files
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads SleepJournal from file and returns it;
    // throw an IOException if an error occurs while reading data from file
    // IOException is an exception that occurs when reading a file/ data from a file
    public SleepJournal read() throws IOException {
        return null;
    }

    // EFFECTS: reads source file as a string and returns it
    private String readFile() {
        return null;
    }

    // EFFECTS: parses sleepjournal from JSON object and returns it
    private SleepJournal parseSleepJournal() {
        return null;
    }

    // MODIFIES: sleepjournal
    // EFFECTS: parses sleepentries from JSON object and adds them to sleepjournal
    private void addSleepEntries() {
    }
}
