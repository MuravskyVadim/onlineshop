package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Basket {

    private Long basketId;
    private List<Product> productList;
    private Long userId;

    public Basket(Long basketId, List<Product> productList, Long userId) {
        this.basketId = basketId;
        this.productList = productList;
        this.userId = userId;
    }

    public Basket(List<Product> productList, Long userId) {
        this.productList = productList;
        this.userId = userId;
    }
}
