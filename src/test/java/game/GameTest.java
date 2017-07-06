package game;

import game.enums.Sign;
import game.pojo.Player;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

import java.io.*;

@Test
public class GameTest {

    private Player player;
    private Player player2;
    private Game game;

    @BeforeTest
    public void setUp() throws IOException {

        player = new Player();
        player2 = new Player();
    }


}
