package controller;

import factory.ProductServiceFactory;
import factory.UserServiceFactory;
import model.Product;
import model.User;
import service.interfaces.ProductService;
import service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "", loadOnStartup = 1)
public class InitialServlet extends HttpServlet {

    private static UserService userService = UserServiceFactory.getUserService();
    private static ProductService productService = ProductServiceFactory.getProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> allUsers = userService.getAllUsers();
        if (allUsers.size() == 0) {
            userService.addUser("test@mail.ru", "12345");
        }

        List<Product> allProducts = productService.getAllProducts();
        if (allProducts.size() == 0) {
            productService.addProduct("Sushi", "food", 12.5);
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
