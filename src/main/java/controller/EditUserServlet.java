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

@WebServlet(value = "/user")
public class EditUserServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(EditUserServlet.class);
    private static UserService userService = UserServiceFactory.getUserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String edit = request.getParameter("edit");

        if (edit != null) {
            Optional<User> user = userService.getUserById(Long.parseLong(edit));
            if(user.isPresent()) {
                request.setAttribute("user", user.get());
            }
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
        Optional<User> user = userService.getUserById(Long.parseLong(save));

        if (!save.isEmpty() && user.isPresent()) {
            if (!email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()) {
                if (!isUserExist(email) || user.get().getEmail().equals(email)) {
                    if (password.equals(confirmPassword)) {
                        user.get().setEmail(email);
                        user.get().setPassword(password);
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

    private static boolean isUserExist(String email) {
        return userService.getAllUsers()
                .stream()
                .anyMatch(x -> x.getEmail().equals(email));
    }
}
