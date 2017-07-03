package game;

import game.enums.Sign;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

import java.io.*;

@Test
public class GameTest {

    Game game;

    @BeforeTest
    public void setUp() {
        this.game = new Game();
    }

    @DataProvider(name = "signs")
    public static Object[][] numbersForGame() {
        return new Object[][] {{"X", Sign.X}, {"O", Sign.O}, {"x", Sign.X}, {"o", Sign.O}/*, {"Z\nx\n", Sign.X} */};
    }

    @Test(dataProvider = "signs")
    public void testGetPlayersNumberAndExpectGivenSignsFromString(String givenInput, Sign expectedSign) throws Exception {
        InputStream stringInStream = new ByteArrayInputStream(String.valueOf(givenInput).getBytes());

        OutputStream mockOutput = mock(PrintStream.class);

        game.setInput(stringInStream);
        game.setOutput(mockOutput);
        Sign givenSign = game.getFirstSign.call();

        assertEquals(expectedSign, givenSign);
    }



}
