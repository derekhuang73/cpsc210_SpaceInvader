package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Game;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class AirplaneTest {
    Jet jet1;
    Invader invader1,invader2;
    Game game;

    @BeforeEach
    void runBefore(){
        jet1 = new Jet(0,0);
        game = new Game();
        game.setMyJet(jet1);
        jet1.setLevel(1);
        invader1 = new Invader(5,5);
        invader2 = new Invader(Game.widthOfGameBoard,Game.heightOfGameBoard);
    }

    @Test
    void testConstructor() {
        assertEquals(0,jet1.getXcoor());
        assertEquals(0,jet1.getYcoor());
        assertEquals(5,jet1.getMovingSpeed());
        assertEquals(5,invader1.getXcoor());
        assertEquals(5,invader1.getYcoor());
        assertEquals(5,invader1.getMovingSpeed());
    }

    @Test
    void testMoveDown() {
        jet1.moveDown();
        assertEquals(0,jet1.getXcoor());
        assertEquals(5,jet1.getYcoor());
        jet1.moveDown();
        jet1.moveDown();
        assertEquals(15,jet1.getYcoor());
        invader2.moveDown();
        assertEquals(Game.widthOfGameBoard,invader2.getXcoor());
        assertEquals(Game.heightOfGameBoard,invader2.getYcoor());
    }

    @Test
    void testMoveUp() {
        jet1.moveDown();
        assertEquals(5,jet1.getYcoor());
        jet1.moveUp();
        assertEquals(0,jet1.getYcoor());
    }

    @Test
    void testMoveRight() {
        jet1.moveRight();
        assertEquals(10,jet1.getXcoor());
        invader2.moveRight();
        assertEquals(Game.widthOfGameBoard,invader2.getXcoor());
        assertEquals(Game.heightOfGameBoard,invader2.getYcoor());
    }

    @Test
    void testMoveLeft(){
        jet1.moveRight();
        assertEquals(10,jet1.getXcoor());
        jet1.moveLeft();
        assertEquals(0,jet1.getXcoor());
    }

    @Test
    void testJetShoot() {
        jet1.shoot();
        assertEquals(1,game.missiles.size());
    }

    @Test
    void testInvaderShoot() {
        invader1.shoot();
        assertEquals(1,game.missiles.size());
        invader1.shoot();
        assertEquals(2,game.missiles.size());
    }

    @Test
    void testSetLevel() {
        Jet jet2 = new Jet(0,0);
        jet2.setLevel(1);
        assertEquals(5,jet2.getMovingSpeed());
        jet2.setLevel(2);
        assertEquals(10,jet2.getMovingSpeed());
    }

    @Test
    void testJetController() {
        Jet jet001 = new Jet(0,0);
        Jet jet002 = new Jet(0,0);
        jet001.moveUp();
        jet002.jetControl(KeyEvent.VK_KP_UP);
        assertEquals(jet001.getXcoor(),jet002.getXcoor());
        assertEquals(jet001.getYcoor(),jet002.getYcoor());
        jet001.moveDown();
        jet002.jetControl(KeyEvent.VK_DOWN);
        assertEquals(jet001.getXcoor(),jet002.getXcoor());
        assertEquals(jet001.getYcoor(),jet002.getYcoor());
        jet001.moveLeft();
        jet002.jetControl(KeyEvent.VK_LEFT);
        assertEquals(jet001.getXcoor(),jet002.getXcoor());
        assertEquals(jet001.getYcoor(),jet002.getYcoor());
        jet001.moveRight();
        jet002.jetControl(KeyEvent.VK_RIGHT);
        assertEquals(jet001.getXcoor(),jet002.getXcoor());
        assertEquals(jet001.getYcoor(),jet002.getYcoor());
        Game game2 = new Game();
        game2.setMyJet(jet002);
        jet002.jetControl(KeyEvent.VK_SPACE);
        assertEquals(1,game2.missiles.size());
        jet002.jetControl(KeyEvent.VK_S);
        assertEquals(1,game2.missiles.size());
    }

    @Test
    void testInvade() {
        Invader invader001 = new Invader(50,50);
        invader001.setLevel(1);
        invader001.invade(5);
        assertEquals(50 + invader001.getMovingSpeed(),invader001.getYcoor());
        invader001.invade(15);
        assertEquals(50 - invader001.getMovingSpeed() * 2,invader001.getXcoor());
        invader001.invade(25);
        assertEquals(50,invader001.getXcoor());
        Game game2 = new Game();
        game2.invadersList.invaders.add(invader001);
        invader001.invade(31);
        assertEquals(1,game2.missiles.size());
        invader001.invade(32);
        assertEquals(1,game2.missiles.size());
    }
}