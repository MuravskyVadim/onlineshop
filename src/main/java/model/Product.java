package model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Product {

    private Long id;
    private String name;
    private String description;
    private Double price;
}
