package model;

import ui.Game;


public abstract class Airplane {
    protected int xcoor;              //The x-coordinate of the jet
    protected int ycoor;              //The y-coordinate ot the jet
    public static int movingSpeed = Game.level * 10; //The speed of the jet moving on the screen
    public static final int airplaneWidth = 100;
    public static final int airplaneHeight = 100;

    public abstract void shoot();

    //Effect: Set the level of AirPlanes
    //Modify: this
    public static void setLevel(int level) {
        Game.level = level;
        movingSpeed = level * 5;
    }

    //Effect: move the airplane left by its speed
    //Modify: this
    public void moveLeft() {
        if (xcoor >= movingSpeed) {
            xcoor = xcoor - movingSpeed * 2;
        }
    }

    //Effect: move the airplane Right by its speed
    //Modify: this
    public void moveRight() {
        if (xcoor <= Game.widthOfGameBoard - movingSpeed) {
            xcoor = xcoor + movingSpeed * 2;
        }
    }

    //Effect: move the airplane up by its speed
    //Modify: this
    public void moveUp() {
        if (ycoor >= movingSpeed) {
            ycoor = ycoor - movingSpeed;
        }
    }

    //Effect: move the airplane down by its speed
    //Modify: this
    public void moveDown() {
        if (ycoor <= Game.heightOfGameBoard - movingSpeed) {
            ycoor = ycoor + movingSpeed;
        }

    }

    public int getMovingSpeed() {
        return movingSpeed;
    }

    public int getXcoor() {
        return xcoor;
    }

    public int getYcoor() {
        return ycoor;
    }


}
