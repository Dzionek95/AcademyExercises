package game;

import game.enums.Sign;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

@Test
public class BoardTest {

    Board board;

    @BeforeTest
    public void setUp() {
        this.board = new Board(2 ,2);
    }

    @DataProvider(name = "signs")
    public static Object[][] numbersForGame() {
        return new Object[][] {{1, Sign.X}, {2, Sign.O}};
    }

    @DataProvider(name = "boards")
    public static Object[][] boardsForGame() {
        return new Object[][] {
                {(new Board(2, 2).setClearBoardGame().putSign(1, Sign.X)), "X 2 \n3 4"},
                {new Board(3, 3).setClearBoardGame().putSign(1, Sign.O).putSign(2, Sign.X), "O X 3 \n4 5 6 \n7 8 9"}
        };
    }

    @Test(dataProvider = "signs")
    public void testPutSignWithGivenParamsAndExpectGivenValue(Integer key, Sign value) {
        this.board.putSign(key, value);
        assertEquals(value, this.board.getSign(key));
    }

    @Test
    public void testSetClearBoardAndExpectBoardWithEmptyFileds() {
        this.board.setClearBoardGame();
        assertEquals(this.board.getSign(1), Sign.EMPTY);
        assertEquals(this.board.getSign(4), Sign.EMPTY);
    }

    @Test(dataProvider = "signs")
    public void testGetBoardWithParamsAndExpectGivenResult(Integer key, Sign expectedSign) {
        assertEquals(new Board(2, 2).putSign(key, expectedSign).getSign(key), expectedSign);
    }

    @Test(dataProvider = "boards")
    public void testToStringBoardWithBoardAndExpectGivenString(Board givenBoard, String expectedBoard) {
        assertEquals(givenBoard.toString().trim(), expectedBoard);
    }

}
