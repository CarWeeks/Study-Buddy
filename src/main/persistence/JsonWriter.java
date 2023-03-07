package persistence;

import model.Buddy;
import model.Graveyard;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;

// citation: code in this class is modelled from the Workroom example from CPSC 210
// Represents a writer that writes JSON representation of  to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(Buddy b, Graveyard g) {
        JSONObject jsonB = b.buddyToJson();
        JSONArray jsonG = g.allBuddiesToJson();
        JSONObject json = new JSONObject();
        json.put("currBuddy", jsonB);
        json.put("graveyard", jsonG);
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
