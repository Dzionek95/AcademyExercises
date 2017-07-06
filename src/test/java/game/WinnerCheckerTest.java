package game;

import game.enums.Sign;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class WinnerCheckerTest {

    private Board board;
    private Board board2;
    private WinnerChecker winnerChecker;
    private WinnerChecker winnerChecker2;

    @BeforeTest
    public void setUp() {
        board = new Board(3, 3);
        board.setClearBoardGame();
        board2 = new Board(3, 4);
        winnerChecker = new WinnerChecker(3, 3, board);
        winnerChecker2 = new WinnerChecker(3, 4, board2);
    }

    public void HorizontalTestReturnsFalseIfThereIsWinner() {
        board.putSign(0, Sign.X);
        board.putSign(1, Sign.X);
        board.putSign(2, Sign.X);
        Assert.assertEquals(winnerChecker.checkIfWin(), false);
        board.setClearBoardGame();
        board.putSign(3, Sign.O);
        board.putSign(4, Sign.O);
        board.putSign(5, Sign.O);
        Assert.assertEquals(winnerChecker.checkIfWin(), false);
        board.setClearBoardGame();
        board.putSign(6, Sign.X);
        board.putSign(7, Sign.X);
        board.putSign(8, Sign.X);
        Assert.assertEquals(winnerChecker.checkIfWin(), false);
        board.setClearBoardGame();
    }

    public void VerticalTestReturnsFalseIfThereIsWinner() {
        board.putSign(0, Sign.X);
        board.putSign(3, Sign.X);
        board.putSign(6, Sign.X);
        Assert.assertEquals(winnerChecker.checkIfWin(), false);
        board.setClearBoardGame();
        board.putSign(1, Sign.O);
        board.putSign(4, Sign.O);
        board.putSign(7, Sign.O);
        Assert.assertEquals(winnerChecker.checkIfWin(), false);
        board.setClearBoardGame();
        board.putSign(2, Sign.X);
        board.putSign(5, Sign.X);
        board.putSign(8, Sign.X);
        Assert.assertEquals(winnerChecker.checkIfWin(), false);
        board.setClearBoardGame();
    }

    public void DiagonalAndAntiDiagonalTestReturnsFalseIfThereIsWinner() {
        board.putSign(0, Sign.X);
        board.putSign(4, Sign.X);
        board.putSign(8, Sign.X);
        Assert.assertEquals(winnerChecker.checkIfWin(), false);
        board.setClearBoardGame();
        board.putSign(2, Sign.X);
        board.putSign(4, Sign.X);
        board.putSign(6, Sign.X);
        Assert.assertEquals(winnerChecker.checkIfWin(), false);
    }

    public void AdditionalDiagonalAndAntidiagonalTestReturnsFalseIfThereIsWinner() {
        board2.putSign(0, Sign.X);
        board2.putSign(4, Sign.X);
        board2.putSign(8, Sign.X);
        Assert.assertEquals(winnerChecker2.checkIfWin(), false);
        board.setClearBoardGame();
        board2.putSign(2, Sign.X);
        board2.putSign(4, Sign.X);
        board2.putSign(6, Sign.X);
        Assert.assertEquals(winnerChecker2.checkIfWin(), false);
        board2.putSign(3, Sign.X);
        board2.putSign(7, Sign.X);
        board2.putSign(11, Sign.X);
        Assert.assertEquals(winnerChecker2.checkIfWin(), false);
        board.setClearBoardGame();
        board2.putSign(5, Sign.X);
        board2.putSign(7, Sign.X);
        board2.putSign(9, Sign.X);
        Assert.assertEquals(winnerChecker2.checkIfWin(), false);
    }
       /*
   * 0,1,2
   * 3,4,5
   * 6,7,8
   * 9,10,11
   * */
}

