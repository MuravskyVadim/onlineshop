package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/code")
public class ConfirmCodeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String userCode = request.getParameter("code");
            String sendCode = (String) request.getSession().getAttribute("sendCode");
            if (userCode.equals(sendCode)) {
                request.setAttribute("message", "Code successfully confirmed.");
                request.getRequestDispatcher("/code.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Incorrect code!!!");
                request.getRequestDispatcher("/code.jsp").forward(request, response);
            }
        } catch (NullPointerException e) {
            request.setAttribute("message", "Wrong data!!!");
            request.getRequestDispatcher("/code.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/code.jsp");
    }
}
