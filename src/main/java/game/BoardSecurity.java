package game;

import game.enums.Sign;

import java.io.IOException;
import java.util.HashMap;


class BoardSecurity {

    boolean checkIfPlaceIsFree(HashMap<Integer, Sign> board, Integer key) {
        return board.get(key) != null && board.get(key).equals(Sign.EMPTY);
    }

    boolean securityCheck(int positition, Board board) throws IOException {
        return checkIfPlaceIsFree(board.getBoard(), positition) && board.getSize() >= positition;
    }


}
