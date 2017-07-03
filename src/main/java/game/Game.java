package game;

import game.enums.Sign;
import game.exceptions.BadParamsException;
import game.pojo.Player;
import game.streams.CommunicationStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.Callable;

@SuppressWarnings("Duplicates")
class Game {

    private Settings settings = new Settings(System.in, System.out);
    private Board board;
    private Player player;
    private Player player2;
    private Scanner scanner = new Scanner(settings.getInput());
    private BoardSecurity boardSecurity=new BoardSecurity();
    WinnerChecker winnerChecker;


    Game(){
        int xDemension=(Integer)(handleIOAndGetInput(getXDemension)).orElse(null);
        int yDemension=(Integer)(handleIOAndGetInput(getYDemension)).orElse(null);
        board = new Board(xDemension,yDemension);
        this.winnerChecker=new WinnerChecker(xDemension,yDemension,board);
    }

    private Callable<Sign> getFirstSign = new Callable<Sign>() {
        @Override
        public Sign call() throws IOException {
            writeOut("Pick who start, X or O ? \n");
            try {
                scanner.nextLine();
                String answer = scanner.nextLine();
                Optional<Sign> result = Sign.findSign(answer);
                if (!result.isPresent()) throw new BadParamsException();

                return result.get();
            } catch (BadParamsException e) {
                writeOut("Bad paramssss !\n");
            }
            return call();
        }
    };


    private Callable<String> getFirstPlayerName= () -> {
        writeOut("What's first player name? \n");
        return scanner.nextLine();
    };

   private Callable<String> getSecondPlayerName= () -> {
        writeOut("What's second player name? \n");
        return scanner.nextLine();
    };



   private Callable<Integer> getYDemension=new Callable<Integer>() {
        @Override
        public Integer call() throws Exception {
            try {
                writeOut("Enter Y demension size: \n");
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                writeOut("Bad params !\n");
                scanner.nextLine();
            }
            return call();
        }
    };

    private Callable<Integer> getXDemension=new Callable<Integer>() {
        @Override
        public Integer call() throws Exception {
            try {
                writeOut("Enter X demension size: \n");
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                writeOut("Bad params !\n");
                scanner.nextLine();
            }
            return call();
        }
    };

    private Callable<Integer> getPosition=new Callable<Integer>() {
        @Override
        public Integer call() throws Exception {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                writeOut("Bad params !\n");
                scanner.nextLine();
            }
            return call();
        }
    };


  private Optional<Object> handleIOAndGetInput(Callable function) {
        try {
            return Optional.ofNullable(function.call());
        } catch (IOException e) {
            System.err.println("Stream doesn't work");
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
            System.exit(1);
        }
        return Optional.empty();
    }

    Game setBoard() {
        this.board.setClearBoardGame();
        return this;
    }

    private void writeOut(String string) throws IOException {
        settings.getOutput().write(string.getBytes());
    }

    private void printBoard() throws IOException {
        writeOut(this.board.toString());
    }

    void startGame() throws IOException {
        setupGame();
        int i=0;
        int positition=0;
        while(winnerChecker.checkIfWin()){
            printBoard();
            writeOut("Put your sign Mr " + player.getName()+"\n");
            positition=(Integer)((handleIOAndGetInput(getPosition)).orElse(null));
            positition=securityCheck(positition);
            board.putSign(positition,player.getSign());

            printBoard();
            writeOut("Put your sign Mr " + player2.getName()+"\n");
            positition=(Integer)(handleIOAndGetInput(getPosition)).orElse(null);
            positition=securityCheck(positition);
            board.putSign(positition,player2.getSign());

            ++i;
        }


    }

    private void setupGame(){
        Sign pickedSign =  (Sign) handleIOAndGetInput(getFirstSign).orElse(null);
        String name =(String) handleIOAndGetInput(getFirstPlayerName).orElse("NONE");
        player=new Player().setName(name);
        player.setSign(pickedSign);

        String name2 =(String) handleIOAndGetInput(getSecondPlayerName).orElse("NONE");
        player2=new Player().setName(name2);
        if(pickedSign.equals(Sign.O))
            player2.setSign(Sign.X);
        else
            player2.setSign(Sign.O);
    }

    private int securityCheck(int positition) throws IOException {
        while(!boardSecurity.checkIfPlaceIsFree(board.getBoard(),positition)){
            writeOut("This place is already taken pick another one \n");
            positition=(Integer)((handleIOAndGetInput(getPosition)).orElse(null));
        }
        return positition;
    }

    void setInput(InputStream input) {
        this.settings.setInputStream(new CommunicationStream<>(input));
    }

    void setOutput(OutputStream output) {
        this.settings.setOutputStream(new CommunicationStream<>(output));
    }

}
