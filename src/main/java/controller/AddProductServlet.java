package controller;

import factory.ProductServiceFactory;
import service.interfaces.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(value = "/add_product")
public class AddProductServlet extends HttpServlet {
    private final ProductService productService = ProductServiceFactory.getProductServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Double price = Double.parseDouble(request.getParameter("price"));

        if (Objects.nonNull(name) && price != 0) {
            productService.addProduct(name, description, price);
            response.sendRedirect("/all_products");
        } else {
            request.setAttribute("message", "Fields must not be empty!!!");
            request.getRequestDispatcher("add_product.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("add_product.jsp").forward(request, response);
    }
}
