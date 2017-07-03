package game.streams;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.testng.Assert.assertEquals;

@Test
public class CommunicationStreamTest {

    @DataProvider(name = "streams")
    public static Object[][] provideStreams() {
        return new Object[][] {{System.in}, {null} };
    }

    @Test(dataProvider = "streams")
    public void testGetStreamWithGivenParamsAndExpectGivenStream(InputStream expectedStream) throws IOException {
        CommunicationStream<Object> givenCommunication = new CommunicationStream<>(expectedStream);

        assertEquals(expectedStream, givenCommunication.getStream());
    }

    @Test(dataProvider = "streams")
    public void testSetStreamWithGivenParamsAndExpectSettingGivenStream(InputStream expectedStream) throws IOException {
        CommunicationStream<Object> givenCommunication = new CommunicationStream<>();

        assertEquals(expectedStream, givenCommunication.setStream(expectedStream).getStream());
    }
}
