package persistence;

import java.io.FileNotFoundException;

import model.SleepJournal;

//code is modified from JSONSerializerDemo

// Represents a writer that writes JSON representation of sleepjournal to file
public class JsonWriter {

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter() {
    };

    // MODIFIES: this
    // EFFECTS: opens writer, throws FileNotFoundException if destination file
    // cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of sleepjournal to file
    public void write(SleepJournal sleepjournal) {
    };

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile() {
    }
}
