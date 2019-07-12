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

@WebServlet(value = "/product")
public class EditProductServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(EditProductServlet.class);
    private static ProductService productService = ProductServiceFactory.getProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String edit = request.getParameter("edit");

        if (edit != null) {
            Optional<Product> product = productService.getProductById(Long.parseLong(edit));
            if(product.isPresent()) {
                request.setAttribute("product", product.get());
            }
        }
        request.getRequestDispatcher("edit_product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String save = request.getParameter("save");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");

        if (save != null && !save.isEmpty()) {
            if (!name.isEmpty() && !description.isEmpty() && !price.isEmpty()) {
                Optional<Product> product = productService.getProductById(Long.parseLong(save));
                if (product.isPresent()){
                    product.get().setName(name);
                    product.get().setDescription(description);
                    Double priceDouble = Double.parseDouble(price);
                    product.get().setPrice(priceDouble > 0 ? priceDouble : 0);
                    logger.info(product.get() + " was edited");
                }
            } else {
                request.setAttribute("message", "All fields must be filled!!!");
                request.getRequestDispatcher("edit_product.jsp").forward(request, response);
            }
        }
        response.sendRedirect("/products");
    }
}
