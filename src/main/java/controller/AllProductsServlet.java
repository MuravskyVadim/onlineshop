package controller;

import factory.ProductServiceFactory;
import model.Product;
import service.interfaces.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/products")
public class AllProductsServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> allProducts = productService.getAllProducts();
        request.setAttribute("products", allProducts);
        request.getRequestDispatcher("/products.jsp").forward(request, response);
    }
}
