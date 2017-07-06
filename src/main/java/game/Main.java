package game;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {

        try {
            Game game = new Game();
            game.startGame();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
