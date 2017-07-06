package game;

import game.enums.Sign;
import game.pojo.Player;

import java.io.IOException;

class Game {

    private GameSetup gameSetup = new GameSetup();
    private Board board = gameSetup.getBoard();
    private WinnerChecker winnerChecker = gameSetup.getWinnerChecker();
    private Player player = gameSetup.getPlayer();
    private Player player2 = gameSetup.getPlayer2();
    private IOHandler ioHandler = gameSetup.getIoHandler();

    private BoardSecurity boardSecurity = new BoardSecurity();

    Game() throws IOException {
    }


    void startGame() throws IOException {
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
