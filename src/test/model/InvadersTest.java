package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvadersTest {
    InvadersList invaders;
    Invader invader1,invader2;
    @BeforeEach
    void runBefore() {
        invaders = new InvadersList();
        invader1 = new Invader(10,10);
        invader2 = new Invader( 40,90);
    }

    @Test
    void testConstructor() {
        assertEquals(0,invaders.invadersNumber());
    }

    @Test
    void testAddInvader() {
        invaders.addInvader(invader1);
        assertEquals(1,invaders.invadersNumber());
        invaders.addInvader(invader2);
        Invader invader = (Invader) invaders.getInvader(1);
        assertEquals(invader2,invader);
        invader = (Invader) invaders.getInvader(0);
        assertEquals(invader1,invader);
    }

    @Test
    void testRemoveInvader() {
        invaders.addInvader(invader1);
        invaders.addInvader(invader2);
        assertEquals(2,invaders.invadersNumber());
        invaders.removeInvader(new Invader(0,0));
        assertEquals(2,invaders.invadersNumber());
        invaders.removeInvader(invader1);
        assertEquals(1,invaders.invadersNumber());
        Invader invader = (Invader) invaders.getInvader(0);
        assertEquals(40,invader.getXcoor());
    }

}
