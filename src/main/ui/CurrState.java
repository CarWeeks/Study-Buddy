package ui;

import model.Buddy;
import model.Graveyard;

// represents current state of Buddy and graveyard
public class CurrState {

    Buddy currBuddy;
    Graveyard graveyard;
    Boolean idle;

    // EFFECTS: constructs a current Buddy and new graveyard
    public CurrState() {
        this.currBuddy = new Buddy("");
        this.graveyard = new Graveyard();
        this.idle = false;
    }

    // REQUIRES: currBuddy is not null
    // MODIFIES: this
    // EFFECTS: sets currBuddy
    public void setCurrBuddy(Buddy currBuddy) {
        this.currBuddy = currBuddy;
    }

    // REQUIRES: graveyard is not null
    // MODIFIES: this
    // EFFECTS: sets graveyard
    public void setGraveyard(Graveyard graveyard) {
        this.graveyard = graveyard;
    }

    // EFFECTS: returns currBuddy
    public Buddy getCurrBuddy() {
        return currBuddy;
    }

    // EFFECTS: returns graveyard
    public Graveyard getGraveyard() {
        return graveyard;
    }

    // EFFECTS: returns idle
    public Boolean getIdle() {
        return idle;
    }

    // REQUIRES: idle is not null
    // MODIFIES: this
    // EFFECTS: sets idle
    public void setIdle(Boolean idle) {
        this.idle = idle;
    }
}
