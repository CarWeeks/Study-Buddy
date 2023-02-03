package model;

    // represents a single Buddy object
public class Buddy {
    private int health;     // represents a Buddy's health [0, 10]
    private int food;       // represents a Buddy's food [0, 10]
    private int energy;     // represents a Buddy's energy [0, 10]
    private int happiness;  // represents a Buddy's happiness [0, 10]

    public Buddy() {
        this.health = 10;
        this.food = 10;
        this.energy = 10;
        this.happiness = 10;
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
     * REQUIRES: health + amount <= 10
     * MODIFIES: this
     * EFFECTS: increases health by given amount
     */
    public void increaseHealth(int amount) {
        this.health += amount;
    }

    /*
     * REQUIRES: health - amount >= 0
     * MODIFIES: this
     * EFFECTS: decreases health by given amount
     */
    public void decreasesHealth(int amount) {
        this.health -= amount;
    }

    /*
     * REQUIRES: food + amount <= 10
     * MODIFIES: this
     * EFFECTS: increases food by given amount
     */
    public void increaseFood(int amount) {
        this.food += amount;
    }

    /*
     * REQUIRES: food - amount >= 0
     * MODIFIES: this
     * EFFECTS: decreases food by given amount
     */
    public void decreaseFood(int amount) {
        this.food -= amount;
    }

    /*
     * REQUIRES: energy + amount <= 10
     * MODIFIES: this
     * EFFECTS: increases energy by given amount
     */
    public void increaseEnergy(int amount) {
        this.energy += amount;
    }

    /*
     * REQUIRES: energy - amount >= 0
     * MODIFIES: this
     * EFFECTS: decreases energy by given amount
     */
    public void decreaseEnergy(int amount) {
        this.energy -= amount;
    }

    /*
     * REQUIRES: happiness + amount <= 10
     * MODIFIES: this
     * EFFECTS: increases happiness by given amount
     */
    public void increaseHappiness(int amount) {
        this.happiness += amount;
    }

    /*
     * REQUIRES: happiness - amount >= 0
     * MODIFIES: this
     * EFFECTS: decreases happiness by given amount
     */
    public void decreaseHappiness(int amount) {
        this.happiness -= amount;
    }
}

