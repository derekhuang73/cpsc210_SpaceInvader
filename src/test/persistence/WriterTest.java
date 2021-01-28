package persistence;

import model.Invader;
import model.InvadersList;
import model.Jet;
import model.Missile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WriterTest {
    private static final String TEST_GAME_FILE = "./data/testGame.txt";
    private static final String TEST_MISSILES_FILE = "./data/testMissiles.txt";
    private static final String TEST_INVADERS_FILE = "./data/testInvaders.txt";
    private Writer testGameWriter;
    private Writer testMissilesWriter;
    private Writer testInvadersWriter;
    private Game game;
    private Jet myJet;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testGameWriter = new Writer(new File(TEST_GAME_FILE));
        testMissilesWriter = new Writer(new File(TEST_MISSILES_FILE));
        testInvadersWriter = new Writer(new File(TEST_INVADERS_FILE));
        game = new Game();
        myJet = new Jet(0,0);
        game.setMyJet(myJet);
        game.setLevel(1);
        game.myJet.shoot();
        game.myJet.moveDown();
        game.myJet.shoot();
        game.invadersList.addInvader(new Invader(40, 50));
        game.invadersList.addInvader(new Invader(60, 0));
    }

    @Test
    void testGameWriter() {
        //save myJet,score,level
        testGameWriter.write(game.myJet);
        testGameWriter.close();

        try {
            List<Integer> data = GameReader.readGame(new File(TEST_GAME_FILE));
            int xcoor = data.get(0);
            assertEquals(0, xcoor);
            int ycoor = data.get(1);
            assertEquals(5,ycoor);
            assertEquals(1,data.get(2));//level
            assertEquals(0,data.get(3));//score
            assertEquals(0,data.get(4));//highestScore
            assertEquals(1,data.get(5));//running
            assertEquals(0,data.get(6));
            assertEquals(7,data.size());//verify only 7 int have been add to the file
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testMissileWriter() {
        //save Missiles
        for (Missile missile : game.missiles) {
            testMissilesWriter.write(missile);
        }
        testMissilesWriter.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            List<Missile> missiles = MissilesReader.readMissiles(new File(TEST_MISSILES_FILE));
            Missile missile1 = missiles.get(0);
            Missile missile2 = missiles.get(1);
            assertEquals(0,missile1.getMissileX());
            assertEquals(0,missile1.getMissileY());
            assertTrue(missile1.missileType);
            assertEquals(5,missile2.getMissileY());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testInvadersWriter() {
        //save Invaders
        testInvadersWriter.write(game.invadersList);
        testInvadersWriter.close();

        try {
            InvadersList invaders = InvadersReader.readInvaders(new File(TEST_INVADERS_FILE));
            Invader invader1 = (Invader) invaders.getInvader(0);
            Invader invader2 = (Invader) invaders.getInvader(1);
            assertEquals(40,invader1.getXcoor());
            assertEquals(50,invader1.getYcoor());
            assertEquals(60,invader2.getXcoor());
            assertEquals(0,invader2.getYcoor());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}

