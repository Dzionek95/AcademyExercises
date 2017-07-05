package game;

import game.enums.Sign;

//Methods are almost the same
@SuppressWarnings("Duplicates")
class WinnerChecker {

    private int winLength=0;
    private Board board;
    private int boardLength=0;


    WinnerChecker(int lengthX, int lengthY, Board board ){
        if(lengthX>lengthY)
            this.winLength=lengthY;
        else
            this.winLength=lengthX;
        this.boardLength=lengthY*lengthX;
        this.board=board;
    }

     boolean checkIfWin(){
        return diagonalCheck() && horiziontalCheck() && verticalCheck();
    }

    private boolean diagonalCheck() {
        int counterX = 0;
        int counterY = 0;
        for(int j=0; j<boardLength;j+=winLength) {
            for (int i = j; i < boardLength; i += winLength+1) {
                if (board.getBoard().get(i).equals(Sign.X)) {
                    ++counterX;
                    if (counterX == winLength) {
                        return false;
                    }
                } else if (board.getBoard().get(i).equals(Sign.O)) {
                    ++counterY;
                    if (counterY == winLength) {
                        return false;
                    }
                }
            }
            counterX=0;
            counterY=0;
        }

        for (int j = winLength - 1; j < boardLength; j += winLength) {
            for (int i = j; i < boardLength; i += winLength-1) {
                if (board.getBoard().get(i).equals(Sign.X)) {
                    ++counterX;
                    if (counterX == winLength) {
                        return false;
                    }
                } else if (board.getBoard().get(i).equals(Sign.O)) {
                    ++counterY;
                    if (counterY == winLength) {
                        return false;
                    }
                }
            }
            counterX=0;
            counterY=0;
        }
        return true;
    }

    private boolean horiziontalCheck() {
        int counterX = 0;
        int counterY = 0;
        for (int i = 0; i < boardLength; i += winLength) {
            for (int j = i; j < winLength + i; ++j) {
                if(board.getBoard().get(j).equals(Sign.X)){
                    ++counterX;
                    if(counterX==winLength){
                        return false;
                    }
                }else if(board.getBoard().get(j).equals(Sign.O)){
                    ++counterY;
                    if(counterY==winLength){
                        return false;
                    }
                }
            }
            counterX = 0;
            counterY = 0;
        }
        return true;

    }

    private boolean verticalCheck() {
        int counterX=0;
        int counterY=0;
        for(int i=0;i<winLength;++i) {
            for (int j = i; j < boardLength; j += winLength) {
                if(board.getBoard().get(j).equals(Sign.X)){
                    ++counterX;
                    if(counterX==winLength){
                        return false;
                    }
                }else if(board.getBoard().get(j).equals(Sign.O)){
                    ++counterY;
                    if(counterY==winLength){
                        return false;
                    }
                }
            }
            counterX=0;
            counterY=0;
        }
        return true;
    }

    public boolean checkIfDraw(int counter){
        return counter == boardLength;
    }
}
