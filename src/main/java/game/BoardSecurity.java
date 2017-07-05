package game;

import game.enums.Sign;

import java.io.IOException;
import java.util.HashMap;


 class BoardSecurity {

    private IOHandler ioHandler=new IOHandler();

     boolean checkIfPlaceIsFree(HashMap<Integer, Sign> board, Integer key) {
        return board.get(key).equals(Sign.EMPTY);
    }

      int securityCheck(int positition, Board board) throws IOException {
         while (!checkIfPlaceIsFree(board.getBoard(), positition)) {
             ioHandler.writeOut("This place is already taken pick another one \n");
             positition = (Integer) ((ioHandler.handleIOAndGetInput(ioHandler.getNumber)).orElse(null));
         }
         return positition;
     }
}
