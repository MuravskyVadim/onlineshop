package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import utils.Code;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Order {

    private Long id;
    private User user;
    private Code code;
    private String firstName;
    private String lastName;
    private String city;
    private String street;
    private String houseNumber;
    private String phoneNumber;

    public Order(Long id, User user, Code code, String firstName, String lastName, String city,
                 String street, String houseNumber, String phoneNumber) {
        this.id = id;
        this.user = user;
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.phoneNumber = phoneNumber;
    }

    public Order(User user, Code code, String firstName, String lastName, String city,
                 String street, String houseNumber, String phoneNumber) {
        this.user = user;
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.phoneNumber = phoneNumber;
    }
}
