package game;


import java.io.*;
import java.net.Socket;


public class Client {


    public static void main(String argv[]) throws Exception {
        IOHandler ioHandler = new IOHandler();
        Socket clientSocket = new Socket("localhost", 6789);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.println("What's your name?");
        String nick = (String) ioHandler.handleIOAndGetInput(ioHandler.getPlayersName).orElse("brak");
        outToServer.writeBytes(nick + "\n");

        String arr[] = inFromServer.readLine().split(":");
        for (String line : arr)
            System.out.println(line);
        String message;
        String position;
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
            } else if (message.startsWith("We have")) {
                System.out.println(message);
                break;
            }
        }
        System.out.println("thanks for playing ;]");
    }

}
