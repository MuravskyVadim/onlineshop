package controller;

import factory.ProductServiceFactory;
import factory.UserServiceFactory;
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
        userService.addUser("test@gmail.com", "123", "admin");
        productService.addProduct("Sushi", "food", 12.5);
    }
}
