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
import java.util.List;

@WebServlet(value = "/products")
public class AllProductsServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AllProductsServlet.class);
    private static final ProductService productService =
            ProductServiceFactory.getProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> allProducts = productService.getAllProducts();
        request.setAttribute("products", allProducts);
        request.getRequestDispatcher("products.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String delete = request.getParameter("delete");

        if (delete != null) {
            Product product = productService.getProductById(Long.parseLong(delete));
            productService.getAllProducts().remove(product);
            logger.info(product + " removed from db");
        }
        response.sendRedirect("/products");
    }
}
