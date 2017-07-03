package game;

import game.pojo.Player;
import game.streams.CommunicationStream;

import java.io.InputStream;
import java.io.OutputStream;

class Settings {

    private CommunicationStream<InputStream> inputStream;
    private CommunicationStream<OutputStream> outputStream;

    private Player firstPlayer;
    private Player secondPlayer;

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

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public Settings setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
        return this;
    }

    public Settings setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
        return this;
    }

    OutputStream getOutput() {
        return this.outputStream.getStream();
    }

    InputStream getInput() {
        return this.inputStream.getStream();
    }
}
