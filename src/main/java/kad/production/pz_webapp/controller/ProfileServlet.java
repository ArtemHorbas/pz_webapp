package kad.production.pz_webapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kad.production.pz_webapp.model.User;
import kad.production.pz_webapp.service.UserService;
import kad.production.pz_webapp.util.AuthorizedUtil;

import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(AuthorizedUtil.isAuthorized(req.getSession())) {
            String email = (String) req.getSession().getAttribute("email");

            User profile = UserService.findByEmail(email);
            req.setAttribute("user", profile);
            req.getRequestDispatcher("/profile.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
