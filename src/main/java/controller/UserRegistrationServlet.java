package controller;

import factory.UserServiceFactory;
import service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/register")
public class UserRegistrationServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getUserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");

        if (!email.isEmpty() && !password.isEmpty() && !repeatPassword.isEmpty()) {
            if (!isUserExist(email)) {
                if (password.equals(repeatPassword)) {
                    userService.addUser(email, password);
                    response.sendRedirect("/users");
                } else {
                    request.setAttribute("message", "Passwords not equals! Try again...");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message", "User " + email + " is already registered.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "All fields must be filled!!!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    private static boolean isUserExist(String email) {
        return userService.getAllUsers()
                .stream()
                .anyMatch(x -> x.getEmail().equals(email));
    }
}
