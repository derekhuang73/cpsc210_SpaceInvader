package persistence;

import model.Invader;
import model.InvadersList;
import model.Missile;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest {

    @Test
    void testParseInvaders() {
        try {
            InvadersList invadersList = InvadersReader.readInvaders(new File("./data/testInvadersReader.txt"));
            Invader invader1 = invadersList.getInvader(0);
            Invader invader2 = invadersList.getInvader(1);
            assertEquals(50, invader1.getXcoor());
            assertEquals(50, invader1.getYcoor());
            assertEquals(100, invader2.getXcoor());
            assertEquals(90, invader2.getYcoor());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }


    @Test
    void testconstructors() {
        //code coverage for constructors
        InvadersReader invadersReader = new InvadersReader();
        GameReader gameReader = new GameReader();
        MissilesReader missilesReader = new MissilesReader();
        assertNotNull(invadersReader);
        assertNotNull(gameReader);
        assertNotNull(missilesReader);
    }
    @Test
    void testParseMissiles() {
        try {
            ArrayList<Missile> missiles = MissilesReader.readMissiles(new File("./data/testMissilesReader.txt"));
            Missile missile1 = missiles.get(0);
            Missile missile2 = missiles.get(1);
            assertEquals(0,missile1.getMissileX());
            assertEquals(40,missile1.getMissileY());
            assertTrue(missile1.missileType);
            assertEquals(30,missile2.getMissileX());
            assertEquals(20,missile2.getMissileY());
            assertFalse(missile2.missileType);

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testParseGame(){
        try {
           List<Integer> integers = GameReader.readGame(new File("./data/testGameReader.txt"));
           assertEquals(40,integers.get(0));
           assertEquals(30,integers.get(1));
           assertEquals(2,integers.get(2));
           assertEquals(5,integers.get(3));
           assertEquals(99,integers.get(4));
           assertEquals(1,integers.get(5));
           assertEquals(3,integers.get(6));
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            GameReader.readGame(new File("./path/does/not/exist/testAccount.txt"));
            fail("should throw exception");
        } catch (IOException e) {
        }
    }
}
