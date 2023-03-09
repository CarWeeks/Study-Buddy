package persistence;

import model.Buddy;
import model.Graveyard;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// citation: code in this class is modelled from the Workroom example from CPSC 210
public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Buddy b = reader.readBuddy();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testEmptyGraveyard() {
        JsonReader reader = new JsonReader("./data/currBuddyNoGraveyard.json");
        try {
            Buddy b = reader.readBuddy();
            Graveyard g = reader.readGraveyard();
            assertEquals("Steven", b.getName());
            assertEquals(10000, b.getHealth());
            assertEquals(10000, b.getEnergy());
            assertEquals(10000, b.getHappiness());
            assertEquals(8811, b.getFood());
            assertTrue(b.isLiving());

            assertEquals(0, g.getLength());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testOneGraveyard() {
        JsonReader reader = new JsonReader("./data/currBuddyOneGraveyard.json");
        try {
            Buddy b = reader.readBuddy();
            Graveyard g = reader.readGraveyard();
            assertEquals("Steven", b.getName());
            assertEquals(10000, b.getHealth());
            assertEquals(10000, b.getEnergy());
            assertEquals(10000, b.getHappiness());
            assertEquals(8811, b.getFood());
            assertTrue(b.isLiving());

            assertEquals(1, g.getLength());
            Buddy graveBuddy = g.getBuddy(0);
            assertEquals("Perry", graveBuddy.getName());
            assertEquals(10000, graveBuddy.getHealth());
            assertEquals(10000, graveBuddy.getEnergy());
            assertEquals(10000, graveBuddy.getHappiness());
            assertEquals(9970, graveBuddy.getFood());
            assertFalse(graveBuddy.isLiving());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testThreeGraveyard() {
        JsonReader reader = new JsonReader("./data/currBuddyThreeGraveyard.json");
        try {
            Buddy b = reader.readBuddy();
            Graveyard g = reader.readGraveyard();
            assertEquals("Steven", b.getName());
            assertEquals(10000, b.getHealth());
            assertEquals(10000, b.getEnergy());
            assertEquals(10000, b.getHappiness());
            assertEquals(8811, b.getFood());
            assertTrue(b.isLiving());

            assertEquals(3, g.getLength());
            Buddy graveBuddy1 = g.getBuddy(0);
            assertEquals("Perry", graveBuddy1.getName());
            assertEquals(10000, graveBuddy1.getHealth());
            assertEquals(10000, graveBuddy1.getEnergy());
            assertEquals(10000, graveBuddy1.getHappiness());
            assertEquals(9970, graveBuddy1.getFood());
            assertFalse(graveBuddy1.isLiving());

            Buddy graveBuddy2 = g.getBuddy(1);
            assertEquals("Nick", graveBuddy2.getName());
            assertEquals(10000, graveBuddy2.getHealth());
            assertEquals(10000, graveBuddy2.getEnergy());
            assertEquals(10000, graveBuddy2.getHappiness());
            assertEquals(9974, graveBuddy2.getFood());
            assertFalse(graveBuddy2.isLiving());

            Buddy graveBuddy3 = g.getBuddy(2);
            assertEquals("Emu", graveBuddy3.getName());
            assertEquals(800, graveBuddy3.getHealth());
            assertEquals(10, graveBuddy3.getEnergy());
            assertEquals(10000, graveBuddy3.getHappiness());
            assertEquals(9973, graveBuddy3.getFood());
            assertFalse(graveBuddy3.isLiving());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
