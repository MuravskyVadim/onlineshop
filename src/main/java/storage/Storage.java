package storage;

import dao.impl.ProductIdGenerator;
import dao.impl.UserIdGenerator;
import model.Product;
import model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Storage {

    public static List<User> userList = new ArrayList<>(
        Arrays.asList(new User(UserIdGenerator.getId(), "test@mail.ru", "1234")));

    public static List<Product> productList = new ArrayList<>(
            Arrays.asList(new Product(
                    ProductIdGenerator.getId(),
                    "Potato",
                    "food",
                    1.0)));
}
