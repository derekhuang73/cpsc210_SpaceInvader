package model;

import ui.Game;

public class Invader extends Airplane {

    //Effect: construct a Invader in given x y coordinate
    public Invader(int x, int y) {
        this.xcoor = x;
        this.ycoor = y;
    }


    //Effect: add a missile of type false to MissileList
    //Modify: Game.missiles;
    @Override
    public void shoot() {
        Missile missile = new Missile(xcoor,ycoor,false);
        Game.missiles.add(missile);
    }

    //Effect: take an int and decide move down or left or right
    //direction between 0 to 3 moves down, 4 move left, 5 move right, 6 shoot.
    //Modify: this
    public void invade(int direction) {
        if (direction <= 10) {
            moveDown();
        } else if (direction <= 20) {
            moveLeft();
        } else if (direction <= 30) {
            moveRight();
        } else if (direction == 31) {
            shoot();
        }
    }
}
