package kad.production.pz_webapp.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kad.production.pz_webapp.model.Role;
import kad.production.pz_webapp.model.User;
import kad.production.pz_webapp.service.UserService;
import kad.production.pz_webapp.util.AuthorizedUtil;

import java.io.IOException;
import java.util.List;

@WebServlet("/users/all")
public class ReadAllUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(AuthorizedUtil.isAuthorized(req.getSession(), Role.ADMIN)) {
            List<User> users = UserService.findAll();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/user/users.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath());
        }
    }
}
