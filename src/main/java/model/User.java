package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import utils.Code;

@EqualsAndHashCode
@ToString
public class User {

     @Getter
     private Long id;

     @Setter
     @Getter
     private String email;

     @Setter
     @Getter
     private String password;

     @Setter
     @Getter
     private String role;

     @Getter
     private Basket basket;

     @Getter
     private Code code;

     public User(Long id, String email, String password, String role) {
          this.id = id;
          this.email = email;
          this.password = password;
          this.role = role;
          this.basket = new Basket();
          code = new Code();
     }
}
