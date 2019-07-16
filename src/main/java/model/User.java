package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class User {

     private Long id;
     private String email;
     private String password;
     private String role;
     private Cart cart;

     public User(Long id, String email, String password, String role) {
          this.id = id;
          this.email = email;
          this.password = password;
          this.role = role;
          this.cart = new Cart();
     }
}
