package controller;

import factory.UserServiceFactory;
import model.User;
import org.apache.log4j.Logger;
import service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/admin/user/delete")
public class DeleteUserServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DeleteUserServlet.class);
    private static final UserService userService = UserServiceFactory.getUserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            Optional<User> user = userService.getUserById(Long.parseLong(id));
            if (user.isPresent()) {
                userService.getAllUsers().remove(user.get());
                logger.info(user.get() + " removed from db");
            }
        }
        response.sendRedirect("/admin/users");
    }
}
