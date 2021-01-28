package model;

import persistence.MissilesReader;
import persistence.Saveable;

import java.io.PrintWriter;

public class Missile implements Saveable {
    public int missileX;    //x coord of missile
    public int missileY;    //y coord of missile
    public boolean missileType; // indicates the side the missile belongs
                                //true represent missile shot by player's jet
                                //false represent missile shot by space invader
    public static final int missileWidth  = 20;
    public static final int missileHeight = 20;

    public Missile(int x, int y, boolean type) {
        this.missileType = type;
        this.missileX = x;
        this.missileY = y;
    }

    public int getMissileX() {
        return missileX;
    }

    public int getMissileY() {
        return missileY;
    }

    public boolean getMissileType() {
        return missileType;
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(missileX);
        printWriter.print(MissilesReader.DELIMITER);
        printWriter.print(missileY);
        printWriter.print(MissilesReader.DELIMITER);
        printWriter.println(missileType);
    }
}
