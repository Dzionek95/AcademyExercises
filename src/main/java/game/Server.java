package game;

import game.enums.Sign;
import game.pojo.Player;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

@SuppressWarnings("Duplicates")
public class Server {

    private GameSetup gameSetup = new GameSetup();
    private Board board = gameSetup.getBoard();
    private WinnerChecker winnerChecker = gameSetup.getWinnerChecker();
    private IOHandler ioHandler = gameSetup.getIoHandler();
    private BoardSecurity boardSecurity = new BoardSecurity();
    private Player player;
    private Player player2;

    public Server() throws IOException {
    }

    public static void main(String argv[]) throws Exception {

        Server server = new Server();
        server.runServer();
    }

    private void runServer() throws IOException {
        Sign startingSign= (Sign)ioHandler.handleIOAndGetInput(ioHandler.getFirstSign).orElse(Sign.X);

        ServerSocket serverSocket = new ServerSocket(6789);
        System.out.println("Waiting for players");

        Socket socket = serverSocket.accept();
        Socket socket2 = serverSocket.accept();
        player = new Player();
        player2 = new Player();

        DataOutputStream toPlayer = new DataOutputStream(socket.getOutputStream());
        BufferedReader fromPlayer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String nick = fromPlayer.readLine();
        player.setName(nick);
        player.setSign(startingSign);
        toPlayer.writeBytes("\n");

        DataOutputStream toPlayer2 = new DataOutputStream(socket2.getOutputStream());
        BufferedReader fromPlayer2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
        nick = fromPlayer2.readLine();
        player2.setName(nick);
        if(startingSign.toString().equals("X"))
            player2.setSign(Sign.O);
        else
            player2.setSign(Sign.X);
        toPlayer2.writeBytes(board.toString());

        int position;
        while (winnerChecker.checkIfWin()) {

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

            if (!winnerChecker.checkIfWin()) {
                handleWinSituation(toPlayer, toPlayer2);
                break;
            }

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

        handleWinSituation(toPlayer2, toPlayer);

    }

    private void handleWinSituation(DataOutputStream winnerPlayer, DataOutputStream looserPlayer) throws IOException {
        player.setScore(player.getScore() + 3);
        winnerPlayer.writeBytes("We have winner! Congratulations " + player.getName() + " You won!!! \n\n");
        looserPlayer.writeBytes("We have winner! Congratulations " + player.getName() + " You won!!! \n\n");
    }


}





