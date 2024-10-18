package persistence;

import org.json.JSONObject;

//code is modified from JSONSerializerDemo

public interface Writable {
    // EFFECTS: returns this as a JSON object
    JSONObject toJson();

}
