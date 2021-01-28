package model;

import persistence.InvadersReader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class InvadersList implements Saveable {
    public ArrayList<Invader> invaders;

    public  InvadersList() {
        invaders = new ArrayList<>();
    }

    //Effect: add Invader to the list
    //Modify: this
    public void addInvader(Invader invader) {
        invaders.add(invader);
    }

    //Effect: remove Invader with the given x y
    //Modify: this
    //
    public void removeInvader(Invader invader) {
        invaders.remove(invader);
    }

    //Effect: randomly invade
    public void invadersInvade() {
        for (Invader invader : invaders) {
            int direction = new Random().nextInt(32);
            invader.invade(direction);
        }
    }

    public int invadersNumber() {
        return invaders.size();
    }

    public  Invader getInvader(int index) {
        return  invaders.get(index);
    }

    @Override
    public void save(PrintWriter printWriter) {

        for (Invader invader: invaders) {
            printWriter.print(invader.xcoor);
            printWriter.print(InvadersReader.DELIMITER);
            printWriter.println(invader.ycoor);
        }
    }

}

