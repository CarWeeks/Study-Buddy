package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Buddy;
import model.Graveyard;
import org.json.*;

// Represents a reader that reads a current Buddy and graveyard from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads current Buddy from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Buddy readBuddy() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBuddy(jsonObject, "currBuddy");
    }

    // EFFECTS: reads graveyard from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Graveyard readGraveyard() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGraveyard(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Buddy from JSON object and returns it
    private Buddy parseBuddy(JSONObject jsonObject, String key) {
        JSONObject currentB;
        if (key == "currBuddy") {
            currentB = jsonObject.getJSONObject("currBuddy");
        } else {
            currentB = jsonObject;
        }

        String name = currentB.getString("name");
        int timeAlive = currentB.getInt("timeAlive");
        Long creationTime = currentB.getLong("birth");
        Long deathTime = currentB.getLong("death");
        int health = currentB.getInt("health");
        int food = currentB.getInt("food");
        int energy = currentB.getInt("energy");
        int happiness = currentB.getInt("happiness");
        boolean living = currentB.getBoolean("alive");

        Buddy b = new Buddy(name, creationTime, deathTime, timeAlive, living, health, food, energy, happiness);
        return b;
    }

    // EFFECTS: parses graveyard from JSON object and returns it
    private Graveyard parseGraveyard(JSONObject jsonObject) {
        JSONArray graveyard = jsonObject.getJSONArray("graveyard");
        Graveyard g = new Graveyard();
        for (Object b : graveyard) {
            Buddy buddy = parseBuddy((JSONObject) b, "dead");
            g.addBuddy(buddy);
        }
        return g;
    }
}

