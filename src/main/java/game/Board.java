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
        board=new HashMap<>();
        setClearBoardGame();
    }

    Board putSign(Integer key, Sign sign) {
        this.board.put(key, sign);
        return this;
    }

    Sign getSign(Integer key) {
        return this.board.get(key);
    }

    void setClearBoardGame() {
        for(int i=0; i<dimensionX*dimensionY; i++)
            this.putSign(i, Sign.EMPTY);
    }

     HashMap<Integer, Sign> getBoard() {
        return this.board;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\n");
        for (Integer key : board.keySet()) {
            if (key % dimensionX == 0 && key!=0) result.append("\n");
            if (board.get(key).equals(Sign.EMPTY)) result.append(key);
            else result.append(board.get(key).toString());
            result.append(" ");

        }
        result.append("\n");
        return result.toString();
    }

}
