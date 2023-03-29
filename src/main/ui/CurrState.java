package ui;

import model.Buddy;
import model.Graveyard;

public class CurrState {
    Buddy currBuddy;
    Graveyard graveyard;
    Boolean reload;

    public CurrState() {
        this.currBuddy = new Buddy("");
        this.graveyard = new Graveyard();
        this.reload = false;
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

    public Boolean getReload() {
        return reload;
    }

    public void setReload(Boolean reload) {
        this.reload = reload;
    }
}
