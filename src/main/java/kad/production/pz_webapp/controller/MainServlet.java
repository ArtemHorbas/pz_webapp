package kad.production.pz_webapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kad.production.pz_webapp.model.Course;
import kad.production.pz_webapp.model.Role;
import kad.production.pz_webapp.model.User;
import kad.production.pz_webapp.service.CourseService;
import kad.production.pz_webapp.service.UserService;
import kad.production.pz_webapp.util.AuthorizedUtil;

import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!AuthorizedUtil.isAuthorized(req.getSession())) {
            System.out.println("NOT AUTHORIZED");
            resp.sendRedirect(req.getContextPath() + "/login");
        }

        if(AuthorizedUtil.isAuthorized(req.getSession(), Role.ADMIN)) {
            doAdminGet(req, resp);
        } else if(AuthorizedUtil.isAuthorized(req.getSession(), Role.TEACHER)) {
            doTeacherGet(req, resp);
        } else if(AuthorizedUtil.isAuthorized(req.getSession(), Role.STUDENT)) {
            doStudentGet(req, resp);
        }
    }

    private void doAdminGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = UserService.findAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
    private void doTeacherGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teacherEmail = (String) req.getSession().getAttribute("email");

        List<Course> courses = CourseService.findTeacherCourses(teacherEmail);
        req.setAttribute("courses", courses);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
    private void doStudentGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentEmail = (String) req.getSession().getAttribute("email");

        List<Course> courses = CourseService.findStudentCourses(studentEmail);
        req.setAttribute("courses", courses);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
