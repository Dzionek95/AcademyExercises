package game;

import game.enums.Sign;


@SuppressWarnings("Duplicates")
public class WinnerChecker {

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
        return horiziontalCheck();
    }

//this logic is scary at first, but is very easy to be honest. It's all about repeating sequence every time when you have particular board size
// try to draw sample board(3x3 is the best for start) and go through logic of methods (start with vertical or horizontal logic)
// i was looking for pattern on this board:
// i, i+1, i+2
//i+3, i+4, i+5
//i+6, i+7,  i+8


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
            System.out.println(" ");
        }
        return true;
    }
}
