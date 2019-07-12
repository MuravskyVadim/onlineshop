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
import java.util.List;
import java.util.Optional;

@WebServlet(value = "/users")
public class AllUsersServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AllUsersServlet.class);
    private static final UserService userService = UserServiceFactory.getUserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> allUsers = userService.getAllUsers();
        request.setAttribute("users", allUsers);
        request.getRequestDispatcher("users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String delete = request.getParameter("delete");

        if (delete != null) {
            Optional<User> user = userService.getUserById(Long.parseLong(delete));
            if(user.isPresent()) {
                userService.getAllUsers().remove(user.get());
                logger.info(user + " removed from db");
            }
        }
        response.sendRedirect("/users");
    }
}
