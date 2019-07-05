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
    private final UserService userService = UserServiceFactory.getUserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");

        if (!email.isEmpty() && !password.isEmpty() && !repeatPassword.isEmpty()) {
            if (password.equals(repeatPassword)) {
                userService.addUser(email, password);
                request.setAttribute("message", "You registered successful!");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Passwords not equals! Try again...");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "Fields must not be empty!!!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }

        System.out.println("Количество пользователей: " + userService.getAllUsers().size());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}
