package model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class User {

     private Long id;
     private String email;
     private String password;
     private String role;
}
