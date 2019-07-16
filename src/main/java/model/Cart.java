package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Product> cart;

    public Cart() {
        cart = new ArrayList<>();
    }

    public void addProduct(Product product) {
        cart.add(product);
    }

    public List<Product> getProducts() {
        return cart;
    }

    public int getSize() {
        return cart.size();
    }
}
