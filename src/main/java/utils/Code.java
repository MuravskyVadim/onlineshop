package utils;

import lombok.Getter;

public class Code {

    @Getter
    private String code;

    public Code() {
        int number = 1000 + (int) (Math.random() * 9999);
        code = String.valueOf(number);
    }
}
