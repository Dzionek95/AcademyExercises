package game.enums;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.assertEquals;

@Test
public class SignTest {

    @DataProvider(name = "signs")
    public static Object[][] streams() {
        return new Object[][] {{"X", Optional.of(Sign.X)},
                                {"O", Optional.of(Sign.O)},
                                {null, Optional.empty()},
                                {"Z", Optional.empty()}};
    }

    @Test(dataProvider = "signs")
    public void testFindSingWithGivenParamsAndExpectGivenResult(String givenString, Optional<Sign> expectedSign) {
        assertEquals(expectedSign, Sign.findSign(givenString));
    }

}
