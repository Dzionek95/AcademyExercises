package game;


import game.enums.Sign;
import game.pojo.Player;

import java.io.IOException;
import java.net.Socket;

class GameSetup {

    private IOHandler ioHandler=new IOHandler();
    private Board board;
    private WinnerChecker winnerChecker;
    private Player player;
    private Player player2;

    GameSetup() throws IOException {
        getBasicInformationsAboutGame();
    }


    void getBasicInformationsAboutGame() throws IOException {
        ioHandler.writeOut("What's X size ? \n");
        int xDemension = (Integer) (ioHandler.handleIOAndGetInput(ioHandler.getNumber)).orElse(null);
        ioHandler.writeOut("What's Y size ? \n");
        int yDemension = (Integer) (ioHandler.handleIOAndGetInput(ioHandler.getNumber)).orElse(null);
        setBoard(new Board(xDemension, yDemension));
        setWinnerChecker(new WinnerChecker(xDemension, yDemension, board));
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
