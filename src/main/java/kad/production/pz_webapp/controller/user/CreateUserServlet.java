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

@WebServlet("/users/create")
public class CreateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(AuthorizedUtil.isAuthorized(req.getSession(), Role.ADMIN)) {
            req.getRequestDispatcher("/user/user_form.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(AuthorizedUtil.isAuthorized(req.getSession(), Role.ADMIN)) {
            String firstName = req.getParameter("firstName");
            String secondName = req.getParameter("secondName");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String role = req.getParameter("role");

            User user = new User( firstName, secondName, email, password,
                    Role.getRoleByDisplayName(role));

            String error = UserService.insert(user);

            if(error == null) {
                resp.sendRedirect(req.getContextPath() + "/users");
            } else {
                req.setAttribute("error", error);
                req.getRequestDispatcher("/user/user_form.jsp").forward(req, resp);
            }
        } else {
            resp.sendRedirect(req.getContextPath());
        }
    }
}
