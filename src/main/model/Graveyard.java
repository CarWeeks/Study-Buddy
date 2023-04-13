package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// represents a list of your past Buddies
public class Graveyard {
    ArrayList<Buddy> graves;

    // EFFECTS: instantiates a graveyard as a list of type Buddy
    public Graveyard() {
        this.graves = new ArrayList<>();
    }

    // EFFECTS: returns the length of the list
    public int getLength() {
        return this.graves.size();
    }

    // REQUIRES: given Buddy must not be null
    // MODIFIES: this
    // EFFECTS: adds a given buddy to the end of the graveyard list
    public void addBuddy(Buddy currBuddy) {
        EventLog.getInstance().logEvent(new Event(currBuddy.getName() + " was added to the graveyard."));
        this.graves.add(currBuddy);
    }

    // REQUIRES: 0 <= i, and 0 <= i < list.size
    // EFFECTS: returns Buddy at given index of graveyard list
    public Buddy getBuddy(int i) {
        return this.graves.get(i);
    }

    // EFFECTS: returns graves in Graveyard
    public ArrayList<Buddy> getGraves() {
        return this.graves;
    }

    // EFFECTS: returns Buddies in this graveyard as a JSON array
    public JSONArray allBuddiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Buddy b : graves) {
            JSONObject buddy = b.buddyToJson();
            jsonArray.put(buddy);
        }

        return jsonArray;
    }

    // MODIFIES: this
    // EFFECTS: empties the graveyard
    public void clear() {
        EventLog.getInstance().logEvent(new Event("Graveyard cleared."));
        this.graves.clear();
    }
}
