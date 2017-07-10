package game;


import game.enums.Sign;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;


@Test
public class BoardSecurityTest {
    private Board board;
    private BoardSecurity boardSecurity;

    @BeforeTest
    public void setUp() {
        this.board = new Board(2, 2);
        boardSecurity = new BoardSecurity();
        board.setClearBoardGame();
    }

    @DataProvider(name = "Signs")
    public Object[][] positionsWithSings() {
        return new Object[][]
                {{1, Sign.X},
                        {2, Sign.O},
                        {3, Sign.X},
                        {4, Sign.O}};
    }

    @DataProvider(name = "OverridingSigns")
    public Object[][] doubleSignsToOnePosition() {
        return new Object[][]{{1}, {2}, {3}};
    }

    public void checkIfPlaceIsFree() {
        Assert.assertTrue(boardSecurity.checkIfPlaceIsFree(board.getBoard(), 0));
    }

    @Test(dataProvider = "Signs")
    public void checkIfPlaceIsTaken(int position, Sign sign) {
        board.putSign(position, sign);
        Assert.assertFalse(boardSecurity.checkIfPlaceIsFree(board.getBoard(), position));
    }

    public void checkIfGuardStopPuttingSign() throws IOException {

    }


}

