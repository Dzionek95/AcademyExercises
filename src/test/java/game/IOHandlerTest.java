package game;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

@Test
public class IOHandlerTest {

    @Test
    public void shouldTakeUserInput() {
        IOHandler ioHandler=new IOHandler();
        Assert.assertEquals((ioHandler.handleIOAndGetInput(ioHandler.getNumber)).orElse(null), 5);
    }

}
