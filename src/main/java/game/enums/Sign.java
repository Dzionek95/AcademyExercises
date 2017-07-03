package game.enums;

import java.util.Optional;

public enum Sign {

    X('X'),
    O('O'),
    EMPTY(' ');

    private Character sign;

    Sign(Character sign) {
        this.sign = sign;
    }

    public String toString() {
        return this.sign.toString();
    }

    public static Optional<Sign> findSign(String character) {

        switch (Optional.ofNullable(character).map(String::toUpperCase).orElse("")) {
            case "X":
                return Optional.of(X);
            case "O":
                return Optional.of(O);
            default:
                return Optional.empty();
        }
    }
}
