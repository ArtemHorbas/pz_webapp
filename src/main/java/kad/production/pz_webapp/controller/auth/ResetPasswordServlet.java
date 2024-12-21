package kad.production.pz_webapp.controller.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kad.production.pz_webapp.service.AuthService;
import kad.production.pz_webapp.util.AuthorizedUtil;

import java.io.IOException;

@WebServlet("/reset-password")
public class ResetPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(AuthorizedUtil.isAuthorized(req.getSession())) {
            req.getRequestDispatcher("/auth/reset_password.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(AuthorizedUtil.isAuthorized(req.getSession())) {
            int userId = Integer.parseInt(req.getParameter("email"));
            String newPassword = req.getParameter("password");

            String error = AuthService.resetPassword(userId, newPassword);

            if(error == null) {
                resp.sendRedirect(req.getContextPath() + "/profile");
            } else {
                req.setAttribute("error", error);
                req.getRequestDispatcher("/auth/reset_password.jsp").forward(req, resp);
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
