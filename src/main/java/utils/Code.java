package utils;

import lombok.Getter;
import lombok.Setter;
import model.User;

public class Code {

    @Getter
    private String code;

    @Setter
    @Getter
    private User user;

    public Code(User user) {
        int number = 1000 + (int) (Math.random() * 9999);
        code = String.valueOf(number);
        this.user = user;
    }
}
