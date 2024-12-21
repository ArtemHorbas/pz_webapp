package kad.production.pz_webapp.service;

import kad.production.pz_webapp.model.Role;
import kad.production.pz_webapp.model.User;
import kad.production.pz_webapp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static String READ_USERS = "SELECT * FROM uzytkownicy WHERE Rola != 'Administrator'";
    private static String READ_USER_BY_EMAIL = "SELECT * FROM uzytkownicy WHERE Email = ?";
    private static String INSERT_USERS_PROCEDURE = "CALL DodajUzytkownika(?, ?, ?, ?, ?)";
    private static String DELETE_USER_PROCEDURE = "CALL UsunKontoUzytkownika(?)";

    public static List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_USERS)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User readUser = new User();
                readUser.setId(resultSet.getInt(0));
                readUser.setFirstName(resultSet.getString(1));
                readUser.setSecondName(resultSet.getString(2));
                readUser.setEmail(resultSet.getString(3));
                readUser.setPassword(resultSet.getString(4));
                readUser.setRole(Role.getRoleByDisplayName(resultSet.getString(5)));
                users.add(readUser);
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }

        return users;
    }

    public static User findByEmail(String email) {
        User user = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_USER_BY_EMAIL)) {
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(0));
                user.setFirstName(resultSet.getString(1));
                user.setSecondName(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setRole(Role.getRoleByDisplayName(resultSet.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static String insert(User user) {
        String error = null;

        try (Connection connection = DBUtil.getConnection();
             CallableStatement statement = connection.prepareCall(INSERT_USERS_PROCEDURE)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSecondName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole().getDisplayName());
            statement.executeUpdate();
        } catch (SQLException e) {
            error = e.getMessage();
        }

        return error;
    }

    public static String delete(int id) {
        String error = null;

        try (Connection connection = DBUtil.getConnection();
             CallableStatement statement = connection.prepareCall(DELETE_USER_PROCEDURE)) {
            statement.setString(1, String.valueOf(id));
            statement.executeUpdate();
        } catch (SQLException e) {
            error = e.getMessage();
        }

        return error;
    }
}
