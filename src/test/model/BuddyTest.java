package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuddyTest {
    private Buddy testBuddy1;
    private Buddy testBuddy2;
    private long creationTime1;
    private long creationTime2;

    @BeforeEach
    public void setup() {
        testBuddy1 = new Buddy("testName1");
        creationTime1 = System.currentTimeMillis();
        assertEquals(creationTime1, testBuddy1.getCreationTime());
        testBuddy2 = new Buddy("testName2");
        creationTime2 = System.currentTimeMillis();
        assertEquals(creationTime2, testBuddy2.getCreationTime());
    }

    @Test
    public void testConstructor() {
        assertEquals("testName1", testBuddy1.getName());
        assertEquals(Buddy.MAX_BAR, testBuddy1.getHealth());
        assertEquals(Buddy.MAX_BAR, testBuddy1.getFood());
        assertEquals(Buddy.MAX_BAR, testBuddy1.getEnergy());
        assertEquals(Buddy.MAX_BAR, testBuddy1.getHappiness());
        assertTrue(testBuddy1.isLiving());
    }

    @Test
    public void testIncreaseDecreaseHealth() {
        assertEquals(Buddy.MAX_BAR, testBuddy1.getHealth());
        testBuddy1.increaseHealth(1);
        assertEquals(Buddy.MAX_BAR, testBuddy1.getHealth());
        testBuddy1.decreaseHealth(1);
        assertEquals(Buddy.MAX_BAR - 1, testBuddy1.getHealth());

        assertEquals(Buddy.MAX_BAR, testBuddy2.getHealth());
        testBuddy2.decreaseHealth(Buddy.MAX_BAR);
        assertEquals(0, testBuddy2.getHealth());
        testBuddy2.increaseHealth(5);
        assertEquals(5, testBuddy2.getHealth());
    }

    @Test
    public void testIncreaseDecreaseFood() {
        assertEquals(Buddy.MAX_BAR, testBuddy1.getFood());
        testBuddy1.increaseFood(1);
        assertEquals(Buddy.MAX_BAR, testBuddy1.getFood());
        testBuddy1.decreaseFood(1);
        assertEquals(Buddy.MAX_BAR - 1, testBuddy1.getFood());

        assertEquals(Buddy.MAX_BAR, testBuddy2.getFood());
        testBuddy2.decreaseFood(Buddy.MAX_BAR);
        assertEquals(0, testBuddy2.getFood());
        testBuddy2.increaseFood(5);
        assertEquals(5, testBuddy2.getFood());
    }

    @Test
    public void testIncreaseDecreaseEnergy() {
        assertEquals(Buddy.MAX_BAR, testBuddy1.getEnergy());
        testBuddy1.increaseEnergy(1);
        assertEquals(Buddy.MAX_BAR, testBuddy1.getEnergy());
        testBuddy1.decreaseEnergy(1);
        assertEquals(Buddy.MAX_BAR - 1, testBuddy1.getEnergy());

        assertEquals(Buddy.MAX_BAR, testBuddy2.getEnergy());
        testBuddy2.decreaseEnergy(Buddy.MAX_BAR);
        assertEquals(0, testBuddy2.getEnergy());
        testBuddy2.increaseEnergy(5);
        assertEquals(5, testBuddy2.getEnergy());
    }

    @Test
    public void testIncreaseDecreaseHappiness() {
        assertEquals(Buddy.MAX_BAR, testBuddy1.getHappiness());
        testBuddy1.increaseHappiness(1);
        assertEquals(Buddy.MAX_BAR, testBuddy1.getHappiness());
        testBuddy1.decreaseHappiness(1);
        assertEquals(Buddy.MAX_BAR - 1, testBuddy1.getHappiness());

        assertEquals(Buddy.MAX_BAR, testBuddy2.getHappiness());
        testBuddy2.decreaseHappiness(Buddy.MAX_BAR);
        assertEquals(0, testBuddy2.getHappiness());
        testBuddy2.increaseHappiness(5);
        assertEquals(5, testBuddy2.getHappiness());
    }

    @Test
    public void testKill() {
        assertTrue(testBuddy1.isLiving());
        testBuddy1.kill();
        Long deathTime = System.currentTimeMillis();
        assertFalse(testBuddy1.isLiving());
        assertEquals((int) ((deathTime - creationTime1) / 1000), testBuddy1.getTimeAlive());
    }
}