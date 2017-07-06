package game;

import game.streams.CommunicationStream;

import java.io.InputStream;
import java.io.OutputStream;

class StreamSettings {

    private CommunicationStream<InputStream> inputStream;
    private CommunicationStream<OutputStream> outputStream;


    StreamSettings(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = new CommunicationStream<>(inputStream);
        this.outputStream = new CommunicationStream<>(outputStream);
    }


    public CommunicationStream<InputStream> getInputStream() {
        return inputStream;
    }

    StreamSettings setInputStream(CommunicationStream<InputStream> inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public CommunicationStream<OutputStream> getOutputStream() {
        return outputStream;
    }

    public StreamSettings setOutputStream(CommunicationStream<OutputStream> outputStream) {
        this.outputStream = outputStream;
        return this;
    }


    OutputStream getOutput() {
        return this.outputStream.getStream();
    }

    InputStream getInput() {
        return this.inputStream.getStream();
    }
}
