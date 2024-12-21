package kad.production.pz_webapp.service;

import kad.production.pz_webapp.util.DBUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {
    private static final String VERIFY_USER_PROCEDURE = "CALL SprawdzUzytkownika(?, ?)";
    private static final String RESET_PASSWORD_PROCEDURE = "CALL ZresetujHaslo(?, ?)";

    public static String verify(String email, String password) {
        String result = null;

        try (Connection connection = DBUtil.getConnection();
             CallableStatement statement = connection.prepareCall(VERIFY_USER_PROCEDURE)) {
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getString(0); //Message return in form of SQL table in column number zero
            }
        } catch (SQLException e) {
            result = e.getMessage();
        }

        return result;
    }

    public static String resetPassword(int id, String newPassword) {
         String error = null;

        try (Connection connection = DBUtil.getConnection();
             CallableStatement statement = connection.prepareCall(RESET_PASSWORD_PROCEDURE)) {
            statement.setInt(1, id);
            statement.setString(2, newPassword);

            statement.executeUpdate();
        } catch (SQLException e) {
            error = e.getMessage();
        }

         return error;
    }
}
