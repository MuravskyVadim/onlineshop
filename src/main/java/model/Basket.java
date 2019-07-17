package model;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private List<Product> basket;

    public Basket() {
        basket = new ArrayList<>();
    }

    public void addProduct(Product product) {
        basket.add(product);
    }

    public List<Product> getProducts() {
        return basket;
    }

    public int getSize() {
        return basket.size();
    }
}
