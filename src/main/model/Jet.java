package model;


import persistence.GameReader;
import persistence.Saveable;
import ui.Game;

import java.awt.event.KeyEvent;
import java.io.PrintWriter;

public class Jet extends Airplane implements Saveable {

    public Jet(int xcoordinate, int ycoordinate) {
        this.xcoor = xcoordinate;
        this.ycoor = ycoordinate;
    }

    //Effect: shoot a missile
    @Override
    public void shoot() {
        Missile missile = new Missile(xcoor,ycoor,true);
        Game.missiles.add(missile);
    }

    // Controls the jet
    // modifies: this
    // effects: move jet or fire missile
    public void jetControl(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            moveLeft();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            moveRight();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            moveDown();
        } else if (keyCode == KeyEvent.VK_UP) {
            moveUp();
        } else if (keyCode == KeyEvent.VK_SPACE) {
            shoot();
        }
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(xcoor);
        printWriter.print(GameReader.DELIMITER);
        printWriter.print(ycoor);
        printWriter.print(GameReader.DELIMITER);
        printWriter.print(Game.level);
        printWriter.print(GameReader.DELIMITER);
        printWriter.print(Game.score);
        printWriter.print(GameReader.DELIMITER);
        printWriter.print(Game.hightestScore);
        printWriter.print(GameReader.DELIMITER);
        if (Game.running) {
            printWriter.print(1);
        } else {
            printWriter.print(0);
        }
        printWriter.print(GameReader.DELIMITER);
        printWriter.print(Game.mouseShootChance);

    }
}
