package ui;


public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.runGame();
        GameFrame gf = new GameFrame(game);
    }
}
