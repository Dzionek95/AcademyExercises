package game;


import game.enums.Sign;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Test
public class BoardSecurityTest {
    Board board;
    BoardSecurity boardSecurity;
    @BeforeTest
    public void setUp() {
        this.board = new Board(2 ,2);
        boardSecurity=new BoardSecurity();
        board.setClearBoardGame();
    }

   @DataProvider(name = "Signs")
   public Object[][] positionsWithSings(){
        return new Object[][]
                {{1, Sign.X},
                {2, Sign.O},
                {3, Sign.X},
                {4, Sign.O}};
    }

    @DataProvider(name = "OverridingSigns")
    public Object[][] doubleSignsToOnePosition(){
       return new Object[][] {{1}, {2}, {3}};
    }

    public void checkIfPlaceIsFree(){
       Assert.assertTrue(boardSecurity.checkIfPlaceIsFree(board.getBoard(),0));
    }

    @Test(dataProvider = "Signs")
    public void checkIfPlaceIsTaken(int position, Sign sign){
        board.putSign(position,sign);
        Assert.assertFalse(boardSecurity.checkIfPlaceIsFree(board.getBoard(),position));
    }

    public void checkIfGuardStopPuttingSign(int position) throws IOException {
        board.putSign(1,Sign.X);
        board.putSign(2,Sign.X);
        String input = "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        //Assert.assertEquals(sec, 3);
    }


}

