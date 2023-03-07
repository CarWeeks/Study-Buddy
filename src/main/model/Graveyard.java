package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// represents a list of your past Buddies
public class Graveyard {
    ArrayList<Buddy> graveyard;

    // EFFECTS: instantiates a graveyard as a list of type Buddy
    public Graveyard() {
        this.graveyard = new ArrayList<>();
    }

    // EFFECTS: returns the length of the list
    public int getLength() {
        return this.graveyard.size();
    }

    // REQUIRES: given Buddy must not be null
    // MODIFIES: this
    // EFFECTS: adds a given buddy to the end of the graveyard list
    public void addBuddy(Buddy currBuddy) {
        this.graveyard.add(currBuddy);
    }

    // REQUIRES: 0 <= i, and 0 <= i < list.size
    // EFFECTS: returns Buddy at given index of graveyard list
    public Buddy getBuddy(int i) {
        return this.graveyard.get(i);
    }

    // EFFECTS: returns Buddies in this graveyard as a JSON array
    public JSONArray allBuddiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Buddy b : graveyard) {
            JSONObject buddy = b.buddyToJson();
            jsonArray.put(buddy);
        }

        return jsonArray;
    }
}
