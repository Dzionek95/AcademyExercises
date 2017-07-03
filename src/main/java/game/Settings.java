package game;

import game.pojo.Player;
import game.streams.CommunicationStream;

import java.io.InputStream;
import java.io.OutputStream;

class Settings {

    private CommunicationStream<InputStream> inputStream;
    private CommunicationStream<OutputStream> outputStream;


    Settings(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = new CommunicationStream<>(inputStream);
        this.outputStream = new CommunicationStream<>(outputStream);
    }


    public CommunicationStream<InputStream> getInputStream() {
        return inputStream;
    }

    Settings setInputStream(CommunicationStream<InputStream> inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public CommunicationStream<OutputStream> getOutputStream() {
        return outputStream;
    }

    public Settings setOutputStream(CommunicationStream<OutputStream> outputStream) {
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
