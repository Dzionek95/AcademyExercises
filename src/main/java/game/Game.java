package game;

import game.enums.Sign;
import game.pojo.Player;
import java.io.IOException;

class Game {


    private Board board;
    private BoardSecurity boardSecurity = new BoardSecurity();
    private IOHandler ioHandler=new IOHandler();
    private WinnerChecker winnerChecker;
    private Player player;
    private Player player2;

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

    void startGame() throws IOException {
        getBasicInformationsAboutGame();
        setupPlayers();
        int roundCounter = 0;

        for (int i = 0; i < 3; ++i) {
            int signsCounter = 0;
            ioHandler.writeOut("Let's get ready to round number: " + ++roundCounter + " !!!\n");
            board.setClearBoardGame();
            while (winnerChecker.checkIfWin()) {

                ioHandler.writeOut(board.toString());
                handlePlayersChoice(player);
                signsCounter++;

                if (!winnerChecker.checkIfWin()) {
                    handleWinSituation(player);
                    break;
                }
                if (winnerChecker.checkIfDraw(signsCounter)) {
                    handleDrawSituation();
                    break;
                }

                ioHandler.writeOut(board.toString());
                handlePlayersChoice(player2);
                signsCounter++;

                if (!winnerChecker.checkIfWin()) {
                    handleWinSituation(player2);
                    break;
                }
                if (winnerChecker.checkIfDraw(signsCounter)) {
                    handleDrawSituation();
                    break;
                }
            }
            if (roundCounter < 3) {

                Character answer = (Character) (ioHandler.handleIOAndGetInput(ioHandler.getQuitOption).orElse(null));
                if (answer.equals('Y')) {
                    handleEndOfGameMesssage();
                    break;
                }
            } else
                handleEndOfGameMesssage();
        }
    }

    private void handlePlayersChoice(Player player) throws IOException {
        int positition;
        ioHandler.writeOut("Put your sign Mr " + player.getName() + "\n");
        positition = (Integer) ((ioHandler.handleIOAndGetInput(ioHandler.getNumber)).orElse(null));
        positition = boardSecurity.securityCheck(positition, board);
        board.putSign(positition, player.getSign());
    }

    private void handleWinSituation(Player player) throws IOException {
        ioHandler.writeOut("Congratulations " + player.getName() + " You won!!! \n\n");
        player.setScore(player.getScore() + 3);
        getOveralScore();
    }

    private void handleDrawSituation() throws IOException {
        ioHandler.writeOut("We have Draw! You both get 1 point \n\n");
        player.setScore(player.getScore() + 1);
        player2.setScore(player2.getScore() + 1);
        getOveralScore();
    }

    private void handleEndOfGameMesssage() throws IOException {
        Player winner;
        Player looser;
        if (player.getScore() > player2.getScore()) {
            winner = player;
            looser = player2;
        } else {
            winner = player;
            looser = player2;
        }

        ioHandler.writeOut("Thanks for playing you both were great, but " + winner.getName() +
                " won " + " with " + winner.getScore() + " points "
                + " You " + looser.getName() + " collected " + looser.getScore());
    }

    private void getOveralScore() throws IOException {
        ioHandler.writeOut("Overall scores: \n");
        ioHandler.writeOut(player.getName() + " has " + player.getScore() + " points\n");
        ioHandler.writeOut(player2.getName() + " has " + player2.getScore() + " points\n");
    }

}
