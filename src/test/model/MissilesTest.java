package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Game;

import static org.junit.jupiter.api.Assertions.*;

public class MissilesTest {
    Jet jet1;
    Invader invader1;
    Game game;

   @BeforeEach
    void runBefore() {
       game = new Game();
       jet1 = new Jet(0,0);
       game.setMyJet(jet1);
       jet1.setLevel(1);
       invader1 = new Invader(10, 10);
       invader1.setLevel(1);
   }

   @Test
    void testConstructor() {
       game.myJet.shoot();
       Missile missile = game.missiles.get(0);
       assertTrue(missile.getMissileType());
       assertEquals(0,missile.getMissileX());
       assertEquals(0,missile.getMissileY());
   }

}
