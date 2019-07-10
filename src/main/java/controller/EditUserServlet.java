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

@WebServlet(value = "/user")
public class EditUserServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(EditUserServlet.class);
    private static UserService userService = UserServiceFactory.getUserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String edit = request.getParameter("edit");

        if (edit != null) {
            User user = userService.getUserById(Long.parseLong(edit));
            request.setAttribute("user", user);
        }
        request.getRequestDispatcher("user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String save = request.getParameter("save");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        User user = userService.getUserById(Long.parseLong(save));

        if (!save.isEmpty()) {
            if (!email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()) {
                if (!isUserExist(email) || user.getEmail().equals(email)) {
                    if (password.equals(confirmPassword)) {
                        user.setEmail(email);
                        user.setPassword(password);
                        logger.info(user + " was edited");
                    } else {
                        request.setAttribute("message", "Passwords not equals! Try again.");
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("message", "User " + email +
                            " is already registered.");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message", "All fields must be filled!!!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        }
        response.sendRedirect("/users");
    }

    private boolean isUserExist(String email) {
        return userService.getAllUsers()
                .stream()
                .anyMatch(x -> x.getEmail().equals(email));
    }
}
