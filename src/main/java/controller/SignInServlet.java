package controller;

import factory.UserServiceFactory;
import model.User;
import service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class SignInServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getUserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (!email.isEmpty() && !password.isEmpty()) {
            Optional<User> user = userService.getUserByEmail(email);
            if(user.isPresent()){
                request.getRequestDispatcher("/products").forward(request, response);
            } else {
                request.setAttribute("message",
                        "User " + email + " not exist. Please register.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "All fields must be filled!!!");
            request.getRequestDispatcher("/").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/").forward(request, response);
    }
}
