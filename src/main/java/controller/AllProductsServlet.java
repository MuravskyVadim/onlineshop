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

@WebServlet(value = "/all_products")
public class AllProductsServlet extends HttpServlet {
    private ProductService productService = ProductServiceFactory.getProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> allProducts = productService.getAllProducts();
        request.setAttribute("all_products", allProducts);
        request.getRequestDispatcher("all_products.jsp").forward(request, response);
    }
}
