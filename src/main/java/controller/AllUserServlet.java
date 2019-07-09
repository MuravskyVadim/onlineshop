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

    private static final UserService userService = UserServiceFactory.getUserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> allUsers = userService.getAllUsers();

        if(allUsers.size() == 0){
            userService.addUser("admin@example.com", "12345");
        }

        request.setAttribute("users", allUsers);
        request.getRequestDispatcher("users.jsp").forward(request, response);
    }
}
