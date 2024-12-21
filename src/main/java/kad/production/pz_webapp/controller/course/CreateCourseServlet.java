package kad.production.pz_webapp.controller.course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kad.production.pz_webapp.model.Role;
import kad.production.pz_webapp.util.AuthorizedUtil;

import java.io.IOException;

@WebServlet("/courses/create")
public class CreateCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(AuthorizedUtil.isAuthorized(req.getSession(), Role.TEACHER)) {

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
