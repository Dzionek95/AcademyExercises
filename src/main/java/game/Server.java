package game;

import game.enums.Sign;
import game.pojo.Player;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings("Duplicates")
public class Server {

    private GameSetup gameSetup = new GameSetup();
    private Board board = gameSetup.getBoard();
    private WinnerChecker winnerChecker = gameSetup.getWinnerChecker();
    private IOHandler ioHandler = gameSetup.getIoHandler();
    private BoardSecurity boardSecurity = new BoardSecurity();
    private Player player;
    private Player player2;

    private DataOutputStream toPlayer;
    private BufferedReader fromPlayer;
    private DataOutputStream toPlayer2;
    private BufferedReader fromPlayer2;


    private Server() throws IOException {
    }

    public static void main(String argv[]) throws Exception {
        Server server = new Server();
        server.runServer();
    }

    private void runServer() throws IOException {
        Sign startingSign = (Sign) ioHandler.handleIOAndGetInput(ioHandler.getFirstSign).orElse(Sign.X);

        ServerSocket serverSocket = new ServerSocket(6789);
        ioHandler.writeOut("Waiting for players");

        Socket socket = serverSocket.accept();
        Socket socket2 = serverSocket.accept();
        player = new Player();
        player2 = new Player();

        startGameOnServerSide(socket,socket2, startingSign);
    }

    private void startGameOnServerSide(Socket socket, Socket socket2, Sign startingSign) throws IOException {
        toPlayer = new DataOutputStream(socket.getOutputStream());
        fromPlayer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String nick = fromPlayer.readLine();
        player.setName(nick);
        player.setSign(startingSign);
        toPlayer.writeBytes("\n");

        toPlayer2 = new DataOutputStream(socket2.getOutputStream());
        fromPlayer2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
        nick = fromPlayer2.readLine();
        player2.setName(nick);
        if (startingSign.toString().equals("X"))
            player2.setSign(Sign.O);
        else
            player2.setSign(Sign.X);
        toPlayer2.writeBytes(board.toString());

        int position;
        int movesCounter;
        for (int roundCounter = 0; roundCounter < 3; ++roundCounter) {
            board.setClearBoardGame();
            movesCounter=0;
            while (true) {
                firstMove();

                if (!winnerChecker.checkIfWin()) {
                    handleWinSituation(toPlayer, toPlayer2);
                    break;
                }

                movesCounter++;

                if(movesCounter==board.getSize()){
                    handleDrawSituation();
                    break;
                }

                secondMove();

                if (!winnerChecker.checkIfWin()) {
                    handleWinSituation(toPlayer2,toPlayer);
                    break;
                }
                movesCounter++;
            }

            if (roundCounter < 2) {
                if(checkIfSomeoneWantToEnd())
                    break;
            } else {
                handleEndOfGameSituation();
            }

        }
    }

    private void secondMove() throws IOException {
        int position;
        toPlayer2.writeBytes("Your turn where you want to put sign? \n");
        toPlayer.writeBytes("Wait for your turn \n");
        toPlayer2.writeBytes(board.toString());
        position = Integer.valueOf(fromPlayer2.readLine());
        if (boardSecurity.securityCheck(position, board))
            board.putSign(position, player2.getSign());
        else {
            toPlayer2.writeBytes("Sorry wong place take another one! \n");
            position = Integer.valueOf(fromPlayer2.readLine());
            while (!boardSecurity.securityCheck(position, board)) {
                toPlayer2.writeBytes("Sorry wong place take another one! \n");
                position = Integer.valueOf(fromPlayer2.readLine());
            }

            System.out.println(position);
            board.putSign(position, player2.getSign());
        }
    }

    private void firstMove() throws IOException {
        int position;
        toPlayer.writeBytes("Your turn where you want to put sign? \n");
        toPlayer2.writeBytes("Wait for your turn \n");
        toPlayer.writeBytes(board.toString());
        position = Integer.valueOf(fromPlayer.readLine());
        if (boardSecurity.securityCheck(position, board))
            board.putSign(position, player.getSign());
        else {
            toPlayer.writeBytes("Sorry wong place take another one! \n");
            position = Integer.valueOf(fromPlayer.readLine());
            while (!boardSecurity.securityCheck(position, board)) {
                toPlayer.writeBytes("Sorry wong place take another one! \n");
                position = Integer.valueOf(fromPlayer.readLine());
            }
            System.out.println(position);
            board.putSign(position, player.getSign());
        }
    }

    private boolean checkIfSomeoneWantToEnd() throws IOException {
        toPlayer.writeBytes("Do you want to end game?? Y/N \n");
        toPlayer2.writeBytes("Do you want to end game? Y/N \n");
        String aswearPlayer = fromPlayer.readLine();
        String aswearPlayer2 = fromPlayer2.readLine();
        if (aswearPlayer.equalsIgnoreCase("Y")) {
            toPlayer2.writeBytes("Player left \n");
            return true;
        } else if (aswearPlayer2.equalsIgnoreCase("Y")) {
            toPlayer.writeBytes("Player left \n");
            return true;
        }
        return false;
    }

    private void handleEndOfGameSituation() throws IOException {
        toPlayer.writeBytes("End of game thanks for playind \n");
        toPlayer.writeBytes("Overal score: " + player.getName() + " gained: " + player.getScore() + " and "+ player2.getName() + " gained: " + player2.getScore() + "\n");
        toPlayer2.writeBytes("End of game thanks for playind \n");
        toPlayer2.writeBytes("Overal score: " + player.getName() + " gained: " + player.getScore() + " and "+ player2.getName() + " gained: " + player2.getScore() + "\n");

    }

    private void handleWinSituation(DataOutputStream winnerPlayer, DataOutputStream looserPlayer) throws IOException {
        player.setScore(player.getScore() + 3);
        winnerPlayer.writeBytes("We have winner! Congratulations " + player.getName() + " You won!!! \n\n");
        looserPlayer.writeBytes("We have winner! Congratulations " + player.getName() + " You won!!! \n\n");
    }

    private void handleDrawSituation() throws IOException {
        toPlayer.writeBytes("We have draw you're getting 1 point \n");
        toPlayer2.writeBytes("We have draw you're getting 1 point \n");
        player.setScore(player.getScore()+1);
        player2.setScore(player2.getScore()+1);
    }


}





