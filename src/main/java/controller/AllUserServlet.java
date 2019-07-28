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
import java.util.List;

@WebServlet(value = "/users")
public class AllUserServlet extends HttpServlet {
    private UserService userService = UserServiceFactory.getUserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> allUsers = userService.getAllUsers();
        request.setAttribute("users", allUsers);
        request.getRequestDispatcher("users.jsp").forward(request, response);
    }
}
