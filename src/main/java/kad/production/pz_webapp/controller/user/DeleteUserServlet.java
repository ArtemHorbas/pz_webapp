package kad.production.pz_webapp.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kad.production.pz_webapp.model.Role;
import kad.production.pz_webapp.service.UserService;
import kad.production.pz_webapp.util.AuthorizedUtil;

import java.io.IOException;

@WebServlet("/users/delete")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(AuthorizedUtil.isAuthorized(req.getSession(), Role.ADMIN)) {
            int idDel = Integer.parseInt(req.getParameter("id_del"));
            String error = UserService.delete(idDel);

            if(error != null) {
                req.setAttribute("error", error);
            }

            req.getRequestDispatcher("/user/users.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath());
        }
    }
}
