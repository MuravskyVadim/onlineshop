package controller;

import factory.MailServiceFactory;
import model.User;
import service.interfaces.MailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/user/checkout")
public class CheckoutServlet extends HttpServlet {

    private static final MailService mailService = MailServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (Objects.nonNull(user)) {
            mailService.sendConfirmCode(user);
            session.setAttribute("sendCode", user.getCode().getCode());
        }
        response.sendRedirect("/code.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/checkout.jsp").forward(request, response);
    }
}
