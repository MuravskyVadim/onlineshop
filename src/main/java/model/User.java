package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import utils.Code;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class User {

    private Long id;
    private String email;
    private String password;
    private String role;
    private Code code;

    public User(Long id, String email, String password, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        code = new Code();
    }

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
        code = new Code();
    }
}
