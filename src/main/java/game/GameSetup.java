package game;


import game.enums.Sign;
import game.pojo.Player;

import java.io.IOException;

/*class GameSetup {

    private IOHandler ioHandler=new IOHandler();
    Board board;
    WinnerChecker winnerChecker;
    Player player;
    Player player2;

    void getBasicInformationsAboutGame() throws IOException {

        ioHandler.writeOut("What's X size ? \n");
        int xDemension = (Integer) (ioHandler.handleIOAndGetInput(ioHandler.getNumber)).orElse(null);
        ioHandler.writeOut("What's Y size ? \n");
        int yDemension = (Integer) (ioHandler.handleIOAndGetInput(ioHandler.getNumber)).orElse(null);
        board = new Board(xDemension, yDemension);
        this.winnerChecker = new WinnerChecker(xDemension, yDemension, board);
    }

    void setupPlayers() throws IOException {
        Sign pickedSign = (Sign) ioHandler.handleIOAndGetInput(ioHandler.getFirstSign).orElse(null);
        ioHandler.writeOut("What's first player name? \n");
        String name = (String) ioHandler.handleIOAndGetInput(ioHandler.getPlayersName).orElse("NONE");
        player = new Player().setName(name);
        player.setSign(pickedSign);

        ioHandler.writeOut("What's second player name? \n");
        String name2 = (String) ioHandler.handleIOAndGetInput(ioHandler.getPlayersName).orElse("NONE");
        player2 = new Player().setName(name2);
        if (pickedSign.equals(Sign.O))
            player2.setSign(Sign.X);
        else
            player2.setSign(Sign.O);
    }

}*/
