package game;

import game.enums.Sign;
import game.exceptions.BadParamsException;
import game.streams.CommunicationStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.Callable;

class Game {

    private Settings settings = new Settings(System.in, System.out);

    private Board board = new Board(3 ,4);

    Callable<Sign> getFirstSign = new Callable<Sign>() {
        @Override
        public Sign call() throws IOException {
            writeOut("Pick who start, X or O ? \n");
            try {
                Scanner scanner = new Scanner(settings.getInput());
                String answer = scanner.nextLine();

                Optional<Sign> result = Sign.findSign(answer);
                if (!result.isPresent()) throw new BadParamsException();

                return result.get();
            } catch (BadParamsException e) {
                writeOut("Bad params !\n");
            }
            return call();
        }
    };

    Optional<Object> handleIOAndGetInput(Callable function) {
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

    void writeOut(String string) throws IOException {
        settings.getOutput().write(string.getBytes());
    }

    void printBoard() throws IOException {
        writeOut(this.board.toString());
    }

    void startGame() throws IOException {
        Sign choicedSign = (Sign) handleIOAndGetInput(getFirstSign).orElse(null);
        writeOut("You picked: " + choicedSign.toString()+"\n");

        printBoard();
    }

    Board getBoard() {
        return this.board;
    }

    void setInput(InputStream input) {
        this.settings.setInputStream(new CommunicationStream<>(input));
    }

    void setOutput(OutputStream output) {
        this.settings.setOutputStream(new CommunicationStream<>(output));
    }

}
