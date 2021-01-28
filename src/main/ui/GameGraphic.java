package ui;

import model.Airplane;
import model.Invader;
import model.Jet;
import model.Missile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameGraphic extends JPanel {
    private Game game;
    private Image invaderImage;
    private Image jetImage;
    private Image jetMissileImage;
    private Image invaderMissileImage;
    private Font myFont;

    // Constructs a game panel
    // effects:  sets size and background colour of panel,
    //           updates this with the game to be displayed
    public GameGraphic(Game game) {
        setPreferredSize(new Dimension(Game.widthOfGameBoard, Game.heightOfGameBoard));
        setBackground(Color.BLACK);
        try {
            invaderImage = ImageIO.read(new File("./src/main/image/INVADER_IMAGE.png"));
            jetImage = ImageIO.read(new File("./src/main/image/JET_IMAGE.png"));
            jetMissileImage = ImageIO.read(new File("./src/main/image/JET_MISSILE.png"));
            invaderMissileImage = ImageIO.read(new File("./src/main/image/INVADER_MISSILE.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        myFont = new Font("Serif", Font.BOLD, 40);
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawScore(g);
        if (game.starting) {
            starting(g);
        } else if (game.running) {
            drawGame(g);
            fighting(g);
        } else if (!game.running) {
            gameOver(g);
        }
    }

    private void starting(Graphics g) {
        g.setColor(Color.YELLOW);
        g.setFont(myFont);
        g.drawString("SpaceInvader",  Game.widthOfGameBoard / 2, Game.heightOfGameBoard / 2);
        Font menueFont = new Font("Serif", Font.BOLD, 30);
        g.setFont(menueFont);
        g.setColor(Color.GRAY);
        g.drawString("please select your level, from 1 to 5",
                Game.widthOfGameBoard / 2, (Game.heightOfGameBoard / 2) + 30);
        g.drawString("press x to quit",
                Game.widthOfGameBoard / 2, (Game.heightOfGameBoard / 2) + 60);
    }

    private void fighting(Graphics g) {
        g.setColor(Color.GREEN);
        g.setFont(myFont);
        g.drawString("GO!!!",  Game.widthOfGameBoard / 2, Game.heightOfGameBoard / 2);
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.GREEN);
        g.setFont(myFont);
        g.drawString("Loser! Try Again!",  Game.widthOfGameBoard / 2, Game.heightOfGameBoard / 2);
        g.setColor(Color.YELLOW);
        g.drawString("press R restart",  Game.widthOfGameBoard / 2, (Game.heightOfGameBoard / 2) + 30);
        g.setColor(Color.RED);
        g.drawString("press X exit",  Game.widthOfGameBoard / 2, (Game.heightOfGameBoard / 2) + 60);
    }


    // Draws the game
    // modifies: g
    // effects:  draws the game onto g
    private void drawGame(Graphics g) {
        drawMyJet(g);
        drawInvaders(g);
        drawMissiles(g);
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.GREEN);
        g.setFont(myFont);
        g.drawString("Level: " + game.getLevel(), 30, 30);
        g.drawString("Mouse Shoot Chance: " + game.getMouseShootChance(), 30,60);
        g.drawString("SCORE: " + game.getScore(),30,90);
        g.drawString("Highest SCORE: " + game.getHighestScore(),30,120);
    }

    private void drawMissiles(Graphics g) {
        int height = Missile.missileHeight;
        int width = Missile.missileWidth;
        for (Missile missile : game.missiles) {
            if (missile.getMissileType()) {
                drawInCoordinate(g,jetMissileImage,missile.getMissileX(),missile.getMissileY(),width,height);
            } else {
                drawInCoordinate(g,invaderMissileImage,missile.getMissileX(),missile.getMissileY(),width,height);
            }
        }
    }

    private void drawInvaders(Graphics g) {
        int height = Airplane.airplaneHeight;
        int width  = Airplane.airplaneWidth;
        for (Invader invader : game.invadersList.invaders) {
            drawInCoordinate(g,invaderImage,invader.getXcoor(),invader.getYcoor(),width,height);
        }
    }

    private void drawInCoordinate(Graphics g, Image image, int x, int y, int width, int height) {
        g.drawImage(image, x - width / 2, y - height / 2, width, height, null);
    }

    private void drawMyJet(Graphics g) {
        Jet jet = game.myJet;
        drawInCoordinate(g,jetImage,jet.getXcoor(),jet.getYcoor(),Airplane.airplaneWidth,Airplane.airplaneHeight);
    }




}
