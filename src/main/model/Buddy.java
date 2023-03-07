package model;

import org.json.JSONObject;

import java.lang.System;

// represents a Buddy with a name, birth time, death time and health, food, energy, and happiness bars
public class Buddy {
    public static final int TICKS_PER_SECOND = 10;
    public static final int MAX_BAR = 10000;
    private final long creationTime;
    private long deathTime;
    private int timeAlive;
    private final String name;    // represents a Buddy's name
    private boolean living; // represents whether a buddy is alive or not
    private int health;     // represents a Buddy's health [0, MAX_BAR]
    private int food;       // represents a Buddy's food [0, MAX_BAR]
    private int energy;     // represents a Buddy's energy [0, MAX_BAR]
    private int happiness;  // represents a Buddy's happiness [0, MAX_BAR]

    // REQUIRES: name is not an empty string
    // EFFECTS: instantiates a living buddy with a name and full stats
    public Buddy(String newName) {
        this.creationTime = System.currentTimeMillis();
        this.deathTime = 0;
        this.timeAlive = 0;
        this.living = true;
        this.health = MAX_BAR;
        this.food = MAX_BAR;
        this.energy = MAX_BAR;
        this.happiness = MAX_BAR;
        this.name = newName;
    }

    public Buddy(String name, Long creationTime, Long deathTime, int timeAlive, boolean living, int health, int food,
                 int energy, int happiness) {
        this.creationTime = creationTime;
        this.deathTime = deathTime;
        this.timeAlive = timeAlive;
        this.living = living;
        this.health = health;
        this.food = food;
        this.energy = energy;
        this.happiness = happiness;
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: updates the statistics of a Buddy
    public void updateStats() {
        if (this.getFood() > 0) {
            this.decreaseFood(1);
        } else {
            this.decreaseHealth(1);
        }

        if (this.getFood() < MAX_BAR / 2) {
            this.decreaseEnergy(1);
        } else {
            this.increaseEnergy(1);
        }

        if (this.getHealth() < MAX_BAR && this.getFood() < MAX_BAR / 2) {
            this.decreaseHappiness(1);
        } else {
            this.increaseHappiness(1);
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

    // EFFECTS: returns health
    public int getHealth() {
        return this.health;
    }

    // EFFECTS: returns food
    public int getFood() {
        return this.food;
    }

    // EFFECTS: returns energy
    public int getEnergy() {
        return this.energy;
    }

    // EFFECTS: returns happiness
    public int getHappiness() {
        return this.happiness;
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: increases health by given amount
    public void increaseHealth(int amount) {
        if (this.health + amount <= MAX_BAR) {
            this.health += amount;
        }
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: decreases health by given amount
    public void decreaseHealth(int amount) {
        if (this.health - amount >= 0) {
            this.health -= amount;
        }
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: increases food by given amount
    public void increaseFood(int amount) {
        if (this.food + amount <= MAX_BAR) {
            this.food += amount;
        }
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: decreases food by given amount
    public void decreaseFood(int amount) {
        if (this.food - amount >= 0) {
            this.food -= amount;
        }
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: increases energy by given amount
    public void increaseEnergy(int amount) {
        if (this.energy + amount <= MAX_BAR) {
            this.energy += amount;
        }
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: decreases energy by given amount
    public void decreaseEnergy(int amount) {
        if (this.energy - amount >= 0) {
            this.energy -= amount;
        }
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: increases happiness by given amount
    public void increaseHappiness(int amount) {
        if (this.happiness + amount <= MAX_BAR) {
            this.happiness += amount;
        }
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: decreases happiness by given amount
    public void decreaseHappiness(int amount) {
        if (this.happiness - amount >= 0) {
            this.happiness -= amount;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets living to false, creates death time and total time alive
    public void kill() {
        this.living = false;
        this.deathTime = System.currentTimeMillis();
        this.timeAlive = (int) ((this.deathTime - this.creationTime) / 1000);
    }

    // MODIFIES: this
    // EFFECTS: returns time of Buddy's creation
    public long getCreationTime() {
        return this.creationTime;
    }

    // MODIFIES: this
    // EFFECTS: returns time of Buddy's death
    public long getDeathTime() {
        return this.deathTime;
    }

    // MODIFIES: this
    // EFFECTS: returns total time alive
    public int getTimeAlive() {
        return this.timeAlive;
    }

    public JSONObject buddyToJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("health", this.health);
        json.put("food", this.food);
        json.put("energy", this.energy);
        json.put("happiness", this.happiness);
        json.put("birth", this.creationTime);
        json.put("death", this.deathTime);
        json.put("timeAlive", this.timeAlive);
        json.put("alive", this.living);
        return json;
    }
}


