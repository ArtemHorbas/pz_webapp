package kad.production.pz_webapp.service;

import kad.production.pz_webapp.model.Course;
import kad.production.pz_webapp.model.Role;
import kad.production.pz_webapp.model.User;
import kad.production.pz_webapp.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseService {
    private static String READ_ALL_COURSES = "SELECT * FROM courses";

    public static List<Course> findAll() {
        List<Course> courses = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ALL_COURSES)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Course readCourse = new Course();
                readCourse.setId(resultSet.getInt(0));
                readCourse.setName(resultSet.getString(1));
                readCourse.setDescription(resultSet.getString(2));
                readCourse.setStartTime(resultSet.getDate(3).toLocalDate());
                readCourse.setEndTime(resultSet.getDate(4).toLocalDate());
                //readCourse.setTeacher();
                courses.add(readCourse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public static List<Course> findTeacherCourses(String teacherEmail) {}

    public static List<Course> findStudentCourses(String studentEmail) {}
}
