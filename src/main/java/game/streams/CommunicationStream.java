package game.streams;

import java.io.IOException;
import java.io.OutputStream;

public class CommunicationStream<T> {

    T stream;

    public CommunicationStream() {}

    public CommunicationStream(T stream) {
        this.stream = stream;
    }

    public T getStream() {
        return stream;
    }

    public CommunicationStream<T> setStream(T stream) {
        this.stream = stream;
        return this;
    }


}
