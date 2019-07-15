package controller;

import dao.impl.ProductIdGenerator;
import dao.impl.UserIdGenerator;
import factory.ProductServiceFactory;
import factory.UserServiceFactory;
import model.Product;
import model.User;
import service.interfaces.ProductService;
import service.interfaces.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(value = "/", loadOnStartup = 1)
public class InitialServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getUserService();
    private static final ProductService productService = ProductServiceFactory.getInstance();

    @Override
    public void init() {

        User admin = new User(UserIdGenerator.getId(), "admin@gmail.com", "123", "admin");
        User user = new User(UserIdGenerator.getId(), "user@gmail.com", "123", "user");
        userService.addUser(admin);
        userService.addUser(user);

        Product product1 = new Product(
                ProductIdGenerator.getId(), "Phone", "iPhone X 128G", 699.0);
        Product product2 = new Product(
                ProductIdGenerator.getId(), "Watch", "wrist watch", 399.0);
        Product product3 = new Product(
                ProductIdGenerator.getId(), "MacBook", "laptop", 2699.0);
        Product product4 = new Product(
                ProductIdGenerator.getId(), "iPad", "tablet", 1100.0);
        productService.addProduct(product1);
        productService.addProduct(product2);
        productService.addProduct(product3);
        productService.addProduct(product4);
    }
}
