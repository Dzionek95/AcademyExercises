package game;

import game.enums.Sign;

import java.util.HashMap;


 class BoardSecurity {
     boolean checkIfPlaceIsFree(HashMap<Integer, Sign> board, Integer key) {
        return board.get(key).equals(Sign.EMPTY);
    }
}
