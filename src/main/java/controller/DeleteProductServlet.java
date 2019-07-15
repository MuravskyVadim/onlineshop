package controller;

import factory.ProductServiceFactory;
import model.Product;
import org.apache.log4j.Logger;
import service.interfaces.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/product/delete")
public class DeleteProductServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AllUsersServlet.class);
    private static final ProductService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            Optional<Product> product = productService.getProductById(Long.parseLong(id));
            if (product.isPresent()) {
                productService.getAllProducts().remove(product.get());
                logger.info(product.get() + " removed from db");
            }
        }
        response.sendRedirect("/products");
    }
}
