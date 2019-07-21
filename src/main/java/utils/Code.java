package utils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Code {

    private String value;

    public Code() {
        value = String.valueOf(1000 + (int) (Math.random() * 9000));
    }
}
