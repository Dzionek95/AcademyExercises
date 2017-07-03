package game.pojo;

import game.enums.Sign;

public class Player {

    String name;
    Integer score;
    Sign sign;

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
}