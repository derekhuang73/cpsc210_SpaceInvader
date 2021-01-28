package ui;


import model.*;
import persistence.GameReader;
import persistence.InvadersReader;
import persistence.MissilesReader;
import persistence.Writer;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;


public class Game {
    private static final String INVADERS_FILE = "./data/invaders.txt";
    private static final String MISSILES_FILE = "./data/missiles.txt";
    private static final String GAME_FILE = "./data/game.txt";
    public Jet myJet;
    public static int score;
    public InvadersList invadersList;
    public static ArrayList<Missile> missiles;
    public static int level;
    public static Boolean running;
    public static final int widthOfGameBoard = 1300;
    public static final int heightOfGameBoard = 1000;
    public static int hightestScore;
    public static boolean starting;
    public static int mouseShootChance;



    public Game() {
        myJet = new Jet(widthOfGameBoard / 2, heightOfGameBoard);
        missiles = new ArrayList<>();
        invadersList = new InvadersList();
        score = 0;
        hightestScore = 0;
        level = 0;
        running = true;
        mouseShootChance = 0;
    }

    public void runGame() {
        loadGame();
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void updateGame() {
        generateInvaders();
        updateMissileList();
        updateInvaderList();
        updateCollision();
        checkGameOver();
        levelUp();
    }

    //EFFECT: randomly move every invader in the list
    // with higher possibility of moving down
    public void updateInvaderList() {
        invadersList.invadersInvade();
    }

    //EFFECT: set running to false if myJet get shot or Invader pass the bottom line or myJet get hit by Invader
    public void checkGameOver() {
        int jetX = myJet.getXcoor();
        int jetY = myJet.getYcoor();
        for (Missile missile : missiles) {
            if (!missile.missileType
                    && missile.getMissileX() >= jetX - Airplane.airplaneWidth / 2 - Missile.missileWidth / 2
                    && missile.getMissileX() <= jetX + Airplane.airplaneWidth / 2 + Missile.missileWidth / 2
                    && missile.getMissileY() <= jetY  + Airplane.airplaneHeight / 2 + Missile.missileHeight / 2
                    && missile.getMissileY() >= jetY - Airplane.airplaneHeight / 2 - Missile.missileHeight / 2
            ) {
                running = false;
            } else if (isInvadeSuccess(myJet.getXcoor(),myJet.getYcoor())) {
                running = false;
            }
        }
        if (!running) {
            if (score > hightestScore) {
                hightestScore = score;
            }
        }
    }

    //EFFECT: return true if any invader pass or touch the bottom line
    //or any invader crush with myJet
    public boolean isInvadeSuccess(int x, int y) {
        boolean isSuccess = false;
        for (Invader invader : invadersList.invaders) {
            if (invader.getYcoor() >= Game.heightOfGameBoard) {
                isSuccess = true;
            } else if (invader.getYcoor() == y && invader.getXcoor() == x) {
                isSuccess = true;
            }
        }
        return isSuccess;
    }

    //EFFECT: creat invaders
    private void generateInvaders() {
        if (invadersList.invadersNumber() == 0) {
            randomCreatInvaders(level);
        }
    }

    //Effect: creat given number of invaders with random x coordinate
    //Modify: invaderList
    public void randomCreatInvaders(int numberOfInvader) {
        for (int i = 0; i < numberOfInvader; i++) {
            Random random = new Random();
            int xcoor = random.nextInt(Game.widthOfGameBoard / 10) * 10;
            Invader invader = new Invader(xcoor,0);
            invadersList.addInvader(invader);
        }
    }

    //EFFECT move missiles
    public void updateMissileList() {
        int missileSpeed = level * 5;
        ArrayList<Missile> missilesToRemove = new ArrayList<>();
        for (Missile missile : missiles) {
            if (missile.missileType) {
                missile.missileY = missile.missileY - missileSpeed;
            } else {
                missile.missileY = missile.missileY + missileSpeed;
            }
            if (missile.getMissileY() < 10 || missile.getMissileY() > heightOfGameBoard - 10) {
                missilesToRemove.add(missile);
            }
        }
        for (Missile missile : missilesToRemove) {
            missiles.remove(missile);
        }
    }

    //Effect: check if a invader collide with a missile
    private boolean isCollision(Invader invader, Missile missile) {
        int missX = missile.getMissileX();
        int missY = missile.getMissileY();
        int invadX = invader.getXcoor();
        int invadY = invader.getYcoor();
        return missX <= invadX + Airplane.airplaneWidth / 2 + Missile.missileWidth / 2
               && missX >= invadX - Airplane.airplaneWidth / 2 - Missile.missileWidth / 2
                && invadY + Airplane.airplaneHeight / 2 + Missile.missileHeight / 2 >= missY
                && invadY - Airplane.airplaneHeight / 2 - Missile.missileHeight / 2 <= missY
                && missile.missileType;
    }

    //Effect clear collided missiles and invaders, add score if collided
    private void updateCollision() {
        ArrayList<Invader> invadersToRemove = new ArrayList<>();
        ArrayList<Missile> missilesToRemove = new ArrayList<>();
        for (Invader invader : invadersList.invaders) {
            for (Missile missile : missiles) {
                if (isCollision(invader,missile)) {
                    invadersToRemove.add(invader);
                    missilesToRemove.add(missile);
                    score++;
                }
            }
        }

        for (Missile missile : missilesToRemove) {
            missiles.remove(missile);
        }
        for (Invader invader : invadersToRemove) {
            invadersList.removeInvader(invader);
        }
    }


    // effect: a key handler
    public void keyControl(int keyCode) {
        if (keyCode == KeyEvent.VK_R && !running) {
            clearGame();
            starting = true;
        } else if (keyCode <= KeyEvent.VK_5 && keyCode > KeyEvent.VK_0 && starting) {
            setLevel(keyCode - KeyEvent.VK_0);
            score = level * 10 - 10;
            starting = false;
            running = true;
        } else if (keyCode == KeyEvent.VK_X) {
            saveGame();
            System.exit(0);
        } else {
            myJet.jetControl(keyCode);
        }
    }

    //Effect: initialize game
    private void clearGame() {
        myJet = new Jet(widthOfGameBoard / 2,heightOfGameBoard);
        missiles = new ArrayList<>();
        invadersList = new InvadersList();
        score = 0;
        mouseShootChance = 0;
    }

    public int getLevel() {
        return level;
    }

    //effect : level up
    public void levelUp() {
        if (score > level * 10) {
            level++;
            mouseShootChance++;
        }
    }

    //Effect shoot a missile by mouse click if mouseShootChance > 0
    public void shootByMouse(int x, int y) {
        if (mouseShootChance > 0) {
            missiles.add(new Missile(x,y,true));
            mouseShootChance--;
        }
    }






//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MODIFIES: this
    // EFFECTS: loads Invaders from INVADERS_FILE, if that file exists;
    // otherwise initializes invadersList with default values
    private void loadInvaders() {
        try {
            invadersList = InvadersReader.readInvaders(new File(INVADERS_FILE));
        } catch (IOException e) {
            invadersList = new InvadersList();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Missiles from MISSILES_FILE, if that file exists;
    // otherwise initializes missilesList with default values
    private void loadMissiles() {
        try {
            missiles = MissilesReader.readMissiles(new File(MISSILES_FILE));
        } catch (IOException e) {
            missiles = new ArrayList<>();
        }
    }

    public void setLevel(int level) {
        Game.level = level;
        Airplane.setLevel(level);
    }


    // MODIFIES: this
    // EFFECTS: loads myjet and score and level from GAME_FILE, if that file exists;
    // otherwise initializes Jet with default values
    private void loadJet() {
        try {
            List<Integer> data = GameReader.readGame(new File(GAME_FILE));
            setMyJet(new Jet(data.get(0),data.get(1)));
            setLevel(data.get(2));
            score = data.get(3);
            hightestScore = data.get(4);
            int run = data.get(5);
            if (run == 1) {
                running = true;
            } else {
                running = false;
            }
            mouseShootChance = data.get(6);
        } catch (IOException e) {
            myJet = new Jet(widthOfGameBoard / 2,heightOfGameBoard);
            running = false;
            starting = true;
        }
    }

    // EFFECTS: saves state of missiles to MISSILES_FILE
    private void saveMissiles() {
        try {
            Writer writer = new Writer(new File(MISSILES_FILE));
            for (Missile missile : missiles) {
                writer.write(missile);
            }
            writer.close();
            System.out.println("Missiles saved to file " + MISSILES_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save missiles to " + MISSILES_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }


    // EFFECTS: saves state of Invaders to INVADERS_FILE
    private void saveInvaders() {
        try {
            Writer writer = new Writer(new File(INVADERS_FILE));
            writer.write(invadersList);
            writer.close();
            System.out.println("Invaders saved to file " + INVADERS_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save invaders to " + INVADERS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // EFFECTS: saves state of score level and myjet to GAME_FILE
    private void saveGameState() {
        try {
            Writer writer = new Writer(new File(GAME_FILE));
            writer.write(myJet);
            writer.close();
            System.out.println("Game information saved to file " + GAME_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save Game information to " + GAME_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    private void saveGame() {
        saveMissiles();
        saveInvaders();
        saveGameState();
    }

    private void loadGame() {
        loadMissiles();
        loadInvaders();
        loadJet();
    }


    public void setMyJet(Jet jet) {
        myJet = jet;
    }

    public int getScore() {
        return score;
    }

    public int getHighestScore() {
        return hightestScore;
    }

    public int getMouseShootChance() {
        return mouseShootChance;
    }



//////////////////////////////////////////////////////////////////////////////
}
