package kad.production.pz_webapp.controller.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kad.production.pz_webapp.message.LoginMessage;
import kad.production.pz_webapp.model.User;
import kad.production.pz_webapp.service.AuthService;
import kad.production.pz_webapp.service.UserService;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        String result = AuthService.verify(email, password);

        if(!result.equals(LoginMessage.FIRST.getText()) && !result.equals(LoginMessage.SUBSEQUENT.getText())) {
            req.setAttribute("error", result);
            req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            User currentUser = UserService.findByEmail(email);

            session.setAttribute("email", email);
            session.setAttribute("role", currentUser.getRole().getDisplayName());

            if(result.equals(LoginMessage.FIRST.getText())) {
                resp.sendRedirect(req.getContextPath() + "/");
            } else {
                resp.sendRedirect(req.getContextPath() + "/reset-password");
            }
        }
    }
}
