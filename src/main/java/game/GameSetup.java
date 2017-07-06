package game;


import game.enums.Sign;
import game.pojo.Player;

import java.io.IOException;

class GameSetup {

    private IOHandler ioHandler=new IOHandler();
    private Board board;
    private WinnerChecker winnerChecker;
    private Player player;
    private Player player2;

    GameSetup() throws IOException {
        getBasicInformationsAboutGame();
        setupPlayers();
    }

    void getBasicInformationsAboutGame() throws IOException {
        ioHandler.writeOut("What's X size ? \n");
        int xDemension = (Integer) (ioHandler.handleIOAndGetInput(ioHandler.getNumber)).orElse(null);
        ioHandler.writeOut("What's Y size ? \n");
        int yDemension = (Integer) (ioHandler.handleIOAndGetInput(ioHandler.getNumber)).orElse(null);
        setBoard(new Board(xDemension, yDemension));
        setWinnerChecker(new WinnerChecker(xDemension, yDemension, board));
    }

    void setupPlayers() throws IOException {
        Sign pickedSign = (Sign) ioHandler.handleIOAndGetInput(ioHandler.getFirstSign).orElse(null);
        ioHandler.writeOut("What's first player name? \n");
        String name = (String) ioHandler.handleIOAndGetInput(ioHandler.getPlayersName).orElse("NONE");
        setPlayer(new Player().setName(name));
        getPlayer().setSign(pickedSign);

        ioHandler.writeOut("What's second player name? \n");
        String name2 = (String) ioHandler.handleIOAndGetInput(ioHandler.getPlayersName).orElse("NONE");
        setPlayer2(new Player().setName(name2));
        if (pickedSign.equals(Sign.O))
            getPlayer2().setSign(Sign.X);
        else
            getPlayer2().setSign(Sign.O);
    }

    Board getBoard() {
        return board;
    }

    void setBoard(Board board) {
        this.board = board;
    }

    WinnerChecker getWinnerChecker() {
        return winnerChecker;
    }

    void setWinnerChecker(WinnerChecker winnerChecker) {
        this.winnerChecker = winnerChecker;
    }

    Player getPlayer() {
        return player;
    }

    void setPlayer(Player player) {
        this.player = player;
    }

    Player getPlayer2() {
        return player2;
    }

    void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    IOHandler getIoHandler() {
        return ioHandler;
    }


}
