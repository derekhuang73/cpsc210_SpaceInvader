package ui;

import javax.swing.*;
import java.awt.event.*;

public class GameFrame extends JFrame {
    private Game game;
    private int timerconstant = 150;
    private GameGraphic gg;

    public GameFrame(Game game) {
        super("Space Invaders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(game.widthOfGameBoard,game.heightOfGameBoard);
        setAlwaysOnTop(true);
        setResizable(false);
        setVisible(true);
        this.game = game;
        gg = new GameGraphic(game);
        add(gg);
        addKeyListener(new KeyHandler());
        addMouseListener(new MouseHandler());
        pack();
        addTimer();
    }

    public void addTimer() {
        Timer t = new Timer(timerconstant, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.running) {
                    game.updateGame();
                    gg.repaint();
                } else {
                    gg.repaint();
                }
            }
        });
        t.start();
    }

    /*
     * A key handler to respond to key events
     */
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            game.keyControl(keyCode);
        }
    }

    /*
    A mouse handler to respond to mouse events
     */
    private class MouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            game.shootByMouse(x,y);
        }
    }
}
