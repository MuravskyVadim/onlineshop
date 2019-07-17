package controller;

import factory.ProductServiceFactory;
import factory.UserServiceFactory;
import model.Product;
import model.User;
import org.apache.log4j.Logger;
import service.interfaces.ProductService;
import service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/user/product/by")
public class BuyProductServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(BuyProductServlet.class);
    private final ProductService productService = ProductServiceFactory.getInstance();
    private final UserService userService = UserServiceFactory.getUserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String productId = request.getParameter("productId");
        if (productId != null && userId != null) {
            Optional<Product> product = productService.getProductById(Long.parseLong(productId));
            Optional<User> user = userService.getUserById(Long.parseLong(userId));
            if (product.isPresent() && user.isPresent()) {
                user.get().getBasket().addProduct(product.get());
                request.setAttribute("message", product.get().getName() + " added to basket");
                request.getRequestDispatcher("/user/products").forward(request, response);
                logger.info(product.get() + " added to basket");
            } else {
                request.setAttribute("message", "Such product or user not exist.");
                request.getRequestDispatcher("/user/products").forward(request, response);
            }
        } else {
            request.setAttribute("message", "Incorrect data.");
            request.getRequestDispatcher("/user/products").forward(request, response);
        }
    }
}
