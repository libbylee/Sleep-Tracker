package persistence;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.time.LocalDate;
import model.SleepEntry;
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
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSleepJournal(jsonObject);
    }

    // EFFECTS: reads source file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();

    }

    // EFFECTS: parses sleepjournal from JSON object and returns it
    private SleepJournal parseSleepJournal(JSONObject jsonObject) {
        SleepJournal sj = new SleepJournal();
        addSleepEntries(sj, jsonObject);
        return sj;
    }

    // MODIFIES: sleepjournal
    // EFFECTS: parses sleepentries from JSON object and adds them to sleepjournal
    private void addSleepEntries(SleepJournal sj, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("sleepJournal");
        for (Object json : jsonArray) {
            JSONObject nextSleepEntry = (JSONObject) json;
            addSleepEntry(sj, nextSleepEntry);
        }
    }

    // MODIFIES: sleepjournal
    // EFFECTS: parses a single sleepentry from JSON object and adds it to
    // sleepjournal
    private void addSleepEntry(SleepJournal sj, JSONObject jsonObject) {
        LocalDate date = LocalDate.parse(jsonObject.getString("date"));
        double hoursSlept = jsonObject.getDouble("hoursSlept");
        int sleepRating = jsonObject.getInt("sleepRating");
        String sleepNote = jsonObject.getString("sleepNote");

        SleepEntry entry = new SleepEntry(date, hoursSlept, sleepRating, sleepNote);
        sj.addSleepEntryToSleepJournal(entry);
    }
}
