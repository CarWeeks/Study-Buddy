package model;

import model.Exceptions.EmptyNameException;

// represents a single Buddy object
public class Buddy {
    public static final int TICKS_PER_SECOND = 10;
    public static final int MAX_BAR = 10000;
    private String name;    // represents a Buddy's name
    private boolean living; // represents whether a buddy is alive or not
    private int health;     // represents a Buddy's health [0, MAX_BAR]
    private int food;       // represents a Buddy's food [0, MAX_BAR]
    private int energy;     // represents a Buddy's energy [0, MAX_BAR]
    private int happiness;  // represents a Buddy's happiness [0, MAX_BAR]

    // EFFECTS: instantiates a living buddy with a name and full stats
    public Buddy(String newName) throws EmptyNameException {
        if (newName.isEmpty()) {
            throw new EmptyNameException();
        } else {
            this.name = newName;
            this.living = true;
            this.health = MAX_BAR;
            this.food = MAX_BAR;
            this.energy = MAX_BAR;
            this.happiness = MAX_BAR;
        }
    }

    public void updateStats() {
        if (this.getFood() > 0) {
            this.decreaseFood(1);
            this.increaseHappiness(1);
        }

        if (this.getFood() < MAX_BAR / 2) {
            this.decreaseEnergy(1);
        }

        if (this.getFood() == 0) {
            this.decreaseHealth(1);
        }

        if (this.getHealth() < MAX_BAR) {
            this.decreaseHappiness(1);
        }

        if (this.getHealth() == 0) {
            this.living = false;
        }
    }

    // EFFECTS: returns Buddy's name
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns Buddy's living status
    public boolean isLiving() {
        return this.living;
    }

    /*
     * EFFECTS: returns health
     */
    public int getHealth() {
        return this.health;
    }

    /*
     * EFFECTS: returns food
     */
    public int getFood() {
        return this.food;
    }

    /*
     * EFFECTS: returns energy
     */
    public int getEnergy() {
        return this.energy;
    }

    /*
     * EFFECTS: returns happiness
     */
    public int getHappiness() {
        return this.happiness;
    }

    /*
     * MODIFIES: this
     * EFFECTS: increases health by given amount
     */
    public void increaseHealth(int amount) {
        if (this.health + amount <= MAX_BAR) {
            this.health += amount;
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: decreases health by given amount
     */
    public void decreaseHealth(int amount) {
        if (this.health - amount >= 0) {
            this.health -= amount;
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: increases food by given amount
     */
    public void increaseFood(int amount) {
        if (this.food + amount <= MAX_BAR) {
            this.food += amount;
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: decreases food by given amount
     */
    public void decreaseFood(int amount) {
        if (this.food - amount >= 0) {
            this.food -= amount;
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: increases energy by given amount
     */
    public void increaseEnergy(int amount) {
        if (this.energy + amount <= MAX_BAR) {
            this.energy += amount;
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: decreases energy by given amount
     */
    public void decreaseEnergy(int amount) {
        if (this.energy - amount >= 0) {
            this.energy -= amount;
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: increases happiness by given amount
     */
    public void increaseHappiness(int amount) {
        if (this.happiness + amount <= MAX_BAR) {
            this.happiness += amount;
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: decreases happiness by given amount
     */
    public void decreaseHappiness(int amount) {
        if (this.happiness - amount >= 0) {
            this.happiness -= amount;
        }
    }

    public void kill() {
        this.living = false;
    }
}
