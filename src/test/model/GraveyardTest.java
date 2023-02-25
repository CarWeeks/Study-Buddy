package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraveyardTest {
    private Graveyard testGraveyard;
    private Buddy testBuddy1;
    private Buddy testBuddy2;
    private Buddy testBuddy3;
    private Buddy testBuddy4;

    @BeforeEach
    public void setup() {
        testGraveyard = new Graveyard();
        testBuddy1 = new Buddy("Name1");
        testBuddy2 = new Buddy("Name2");
        testBuddy3 = new Buddy("Name3");
        testBuddy4 = new Buddy("Name4");

        testGraveyard.addBuddy(testBuddy1);
        testGraveyard.addBuddy(testBuddy2);
        testGraveyard.addBuddy(testBuddy3);
    }

    @Test
    public void testGetBuddy() {
        assertEquals(testBuddy2, testGraveyard.getBuddy(1));
    }

    @Test
    public void testGetLength() {
        assertEquals(3, testGraveyard.getLength());
    }

    @Test
    public void testAddBuddy() {
        assertEquals(3, testGraveyard.getLength());
        assertEquals(testBuddy1, testGraveyard.getBuddy(0));
        assertEquals(testBuddy2, testGraveyard.getBuddy(1));
        assertEquals(testBuddy3, testGraveyard.getBuddy(2));

        testGraveyard.addBuddy(testBuddy4);
        assertEquals(4, testGraveyard.getLength());
        assertEquals(testBuddy1, testGraveyard.getBuddy(0));
        assertEquals(testBuddy2, testGraveyard.getBuddy(1));
        assertEquals(testBuddy3, testGraveyard.getBuddy(2));
        assertEquals(testBuddy4, testGraveyard.getBuddy(3));
    }
}
