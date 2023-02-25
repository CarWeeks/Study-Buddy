package model;

import java.util.ArrayList;
import java.util.List;

// represents a list of your past Buddies
public class Graveyard {
    ArrayList<Buddy> graveyard;

    public Graveyard() {
        this.graveyard = new ArrayList<>();
    }

    public int getLength() {
        return this.graveyard.size();
    }

    public void addBuddy(Buddy currBuddy) {
        this.graveyard.add(currBuddy);
    }

    public Buddy getBuddy(int i) {
        return this.graveyard.get(i);
    }
}
