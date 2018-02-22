package ssd.ssda2.data;

import java.sql.*;

import ssd.ssda2.login.SaltedPassword;

/**
 *
 * @author Alex
 */
public class LoginData {

    public static void updatePassword(String login, String password) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String update
                = "UPDATE users SET password = ? WHERE username = ?";

        try {
            ps = connection.prepareStatement(update);
            ps.setString(1, SaltedPassword.encode(password));
            ps.setString(2, login);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot update password", e);
        } finally {
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String resetPassword(String login) {

        String password = SaltedPassword.random(8);
        updatePassword(login, password);

        return password;
    }

    public static boolean checkPassword(String login, String password) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT password FROM users WHERE username = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                return SaltedPassword.match(password, rs.getString("password"));
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot check password", e);
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

}
