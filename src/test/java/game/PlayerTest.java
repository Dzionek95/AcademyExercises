package game;

import game.enums.Sign;
import game.pojo.Player;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

@Test
public class PlayerTest {

    Player player;

    @BeforeTest
    public void setUp() {
        this.player = new Player();
    }

    @DataProvider(name = "players")
    public static Object[][] numbersForGame() {
        return new Object[][] {{"A", 1, Sign.O}, {"B", 0, Sign.X}};
    }

    @Test(dataProvider = "players")
    public void testSetAndGetPlayerWith(String expectedName, Integer expectedScore, Sign expectedSign) {
        Player testedPlayer = new Player().setName(expectedName).setScore(expectedScore).setSign(expectedSign);
        assertEquals(expectedName, testedPlayer.getName());
        assertEquals(expectedScore, testedPlayer.getScore());
        assertEquals(expectedSign, testedPlayer.getSign());
    }

}
