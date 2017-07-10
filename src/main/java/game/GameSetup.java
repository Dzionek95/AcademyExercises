package game;

import game.pojo.Player;
import java.io.IOException;

class GameSetup {

    private IOHandler ioHandler=new IOHandler();
    private Board board;
    private WinnerChecker winnerChecker;

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

    IOHandler getIoHandler() {
        return ioHandler;
    }


}
