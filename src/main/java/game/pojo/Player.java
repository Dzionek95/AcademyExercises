package game.pojo;

import game.enums.Sign;

import java.net.Socket;

public class Player {

    private String name;
    private Integer score=0;
    private Sign sign;
    private Socket socket;


    public String getName() {
        return name;
    }

    public Player setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public Player setScore(Integer score) {
        this.score = score;
        return this;
    }

    public Sign getSign() {
        return sign;
    }

    public Player setSign(Sign sign) {
        this.sign = sign;
        return this;
    }


    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
