package ui;

import model.Buddy;
import model.Graveyard;

public class CurrState {
    Buddy currBuddy;
    Graveyard graveyard;

    public CurrState() {
        this.currBuddy = new Buddy("");
        this.graveyard = new Graveyard();
    }

    public void setCurrBuddy(Buddy currBuddy) {
        this.currBuddy = currBuddy;
    }

    public void setGraveyard(Graveyard graveyard) {
        this.graveyard = graveyard;
    }

    public Buddy getCurrBuddy() {
        return currBuddy;
    }

    public Graveyard getGraveyard() {
        return graveyard;
    }
}
