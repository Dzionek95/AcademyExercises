package game;


import game.enums.Sign;
import game.exceptions.BadParamsException;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.Callable;

 class IOHandler {

    private StreamSettings streamSettings = new StreamSettings(System.in, System.out);
    private Scanner scanner = new Scanner(streamSettings.getInput());

     void writeOut(String string) throws IOException {
        streamSettings.getOutput().write(string.getBytes());
    }


     Callable<Sign> getFirstSign = new Callable<Sign>() {
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
                writeOut("Bad params!\n");
            }
            return call();
        }
    };

     Callable<String> getPlayersName = () -> scanner.nextLine();

     Callable<Character> getQuitOption = new Callable<Character>() {

        @Override
        public Character call() throws IOException {
            scanner.nextLine();
            writeOut("Do you want to end game Y/N ? \n");
            try {
                Character answer = scanner.nextLine().toUpperCase().charAt(0);
                if (!(answer.equals('Y') || answer.equals('N'))) throw new BadParamsException();
                return answer;
            } catch (BadParamsException e) {
                writeOut("Bad params!\n");
            }
            return call();
        }
    };

     Callable<Integer> getNumber = new Callable<Integer>() {
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

}
