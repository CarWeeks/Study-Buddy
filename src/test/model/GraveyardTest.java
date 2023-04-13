package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

    @Test
    public void testGetGraves() {
        assertEquals(3, testGraveyard.getLength());
        Buddy b1 = testGraveyard.getBuddy(0);
        Buddy b2 = testGraveyard.getBuddy(1);
        Buddy b3 = testGraveyard.getBuddy(2);
        ArrayList<Buddy> testBuddies = new ArrayList<>();
        testBuddies.add(b1);
        testBuddies.add(b2);
        testBuddies.add(b3);
        assertEquals(testBuddies, testGraveyard.getGraves());
    }

    @Test
    public void testClearGraveyard() {
        assertEquals(3, testGraveyard.getLength());
        testGraveyard.clear();
        assertEquals(0, testGraveyard.getLength());
    }
}
