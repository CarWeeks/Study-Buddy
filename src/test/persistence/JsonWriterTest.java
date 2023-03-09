package persistence;

import model.Buddy;
import model.Graveyard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// citation: code in this class is modelled from the Workroom example from CPSC 210
public class JsonWriterTest {
    Buddy currB;
    Graveyard g;

    @BeforeEach
    public void setup() {
        currB = new Buddy("Chris");
        g = new Graveyard();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyGraveyard() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGraveyard.json");
            writer.open();
            writer.write(currB, g);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGraveyard.json");
            currB = reader.readBuddy();
            assertEquals("Chris", currB.getName());
            assertEquals(0, g.getLength());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterCurrBuddyThreeGraveyard() {
        try {
            Buddy testB1 = new Buddy("T1");
            Buddy testB2 = new Buddy("T2");
            Buddy testB3 = new Buddy("T3");
            g.addBuddy(testB1);
            g.addBuddy(testB2);
            g.addBuddy(testB3);
            JsonWriter writer = new JsonWriter("./data/testWriterCurrBuddyThreeGraveyard.json");
            writer.open();
            writer.write(currB, g);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterCurrBuddyThreeGraveyard.json");
            currB = reader.readBuddy();
            g = reader.readGraveyard();
            assertEquals("Chris", currB.getName());
            assertEquals(3, g.getLength());
            assertEquals("T1", g.getBuddy(0).getName());
            assertEquals("T2", g.getBuddy(1).getName());
            assertEquals("T3", g.getBuddy(2).getName());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
