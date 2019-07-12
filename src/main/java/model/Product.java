package model;

import lombok.*;

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
