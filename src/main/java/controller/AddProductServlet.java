package controller;

import model.Product;
import service.ProductService;
import service.impl.ProductServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/product")
public class AddProductServlet extends HttpServlet {
    private final ProductService productService = new ProductServiceImp();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Double price = Double.parseDouble(request.getParameter("price"));

        if (!name.isEmpty() && !description.isEmpty() && price != 0) {
            productService.addProduct(new Product(1L, name, description, price));
            request.setAttribute("message", "Item added!");
        } else {
            request.setAttribute("message", "Fields must not be empty!!!");
            request.getRequestDispatcher("product.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("product.jsp").forward(request, response);
    }
}
