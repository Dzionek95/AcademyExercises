package game;


import java.io.*;
import java.net.Socket;


class Client {

    private IOHandler ioHandler;
    private Socket clientSocket;
    private DataOutputStream outToServer;
    private BufferedReader inFromServer;

    Client() throws IOException {
        ioHandler = new IOHandler();
        clientSocket = new Socket("localhost", 6789);
        outToServer = new DataOutputStream(clientSocket.getOutputStream());
        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    private void startGame() throws IOException {
        System.out.println("What's your name?");
        String nick = (String) ioHandler.handleIOAndGetInput(ioHandler.getPlayersName).orElse("brak");
        outToServer.writeBytes(nick + "\n");

        String arr[] = inFromServer.readLine().split(":");
        for (String line : arr)
            System.out.println(line);

        String message;
        String position;
        Character answear;

        while (true) {
            message = inFromServer.readLine();
            if (message.startsWith("Your")) {
                arr = inFromServer.readLine().split(":");
                for (String line : arr)
                    System.out.println(line);
                System.out.println(message);
                position = (String) ioHandler.handleIOAndGetInput(ioHandler.getPlayersName).orElse(-1);
                outToServer.writeBytes(position + "\n");
            } else if (message.startsWith("Wait")) {
                System.out.println(message);
            } else if (message.startsWith("Sorry wong")) {
                System.out.println(message);
                position = (String) ioHandler.handleIOAndGetInput(ioHandler.getPlayersName).orElse(-1);
                outToServer.writeBytes(position + "\n");
            } else if (message.startsWith("We have winner")) {
                System.out.println(message);
            } else if (message.startsWith("We have draw")) {
                System.out.println(message);
            } else if (message.startsWith("Do you")) {
                System.out.println(message);
                answear = (Character) ioHandler.handleIOAndGetInput(ioHandler.getQuitOption).orElse(" ");
                outToServer.writeBytes(answear + "\n");
                if (answear.equals('Y'))
                    break;
            } else if (message.startsWith("Player")) {
                System.out.println("Sorry, but other player left- that's all for now! \n");
                break;
            } else if (message.startsWith("End")) {
                message=inFromServer.readLine();
                System.out.println(message);
                System.out.println("That's all thanks for playing :) \n");
                break;
            }
        }
    }

    public static void main(String argv[]) throws Exception {
        Client client = new Client();
        client.startGame();

    }

}
