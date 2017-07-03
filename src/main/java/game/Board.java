package game;

import game.enums.Sign;

import java.util.HashMap;

class Board {

    private HashMap<Integer, Sign> board;
    private Integer dimensionX;
    private Integer dimensionY;

    Board(Integer dimensionX, Integer dimensionY) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        this.board = new HashMap<>();
    }

    Board putSign(Integer key, Sign sign) {
        this.board.put(key, sign);
        return this;
    }

    Sign getSign(Integer key) {
        return this.board.get(key);
    }

    Board setClearBoardGame() {
        for(int i=1; i<=dimensionX*dimensionY; i++)
            this.putSign(i, Sign.EMPTY);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Integer key : board.keySet()) {
            if (board.get(key).equals(Sign.EMPTY)) result.append(key);
            else result.append(board.get(key).toString());
            result.append(" ");
            if (key % dimensionX == 0) result.append("\n");
        }

        return result.toString();
    }
}
