package ssd.ssda2.data;

import java.sql.*;
import java.util.*;
import ssd.ssda2.business.User;

public class UserData {

    public static void insert(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String update = "INSERT INTO users (username, password) VALUES (?, ?)";
        try {
            ps = connection.prepareStatement(update,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername().trim());
            ps.setString(2, user.getPassword().trim());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                user.setUsername(keys.getString(1));
            } else {
                throw new RuntimeException("Cannot get the generated key.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot insert the user data.", e);
        } finally {
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

//    public static ArrayList<Customer> getAll() {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        Statement st = null;
//        ResultSet rs = null;
//        ArrayList<Customer> students = new ArrayList<>();
//
//        String query = "SELECT * FROM student";
//        try {
//            st = connection.createStatement();
//            rs = st.executeQuery(query);
//            while (rs.next()) {
//                Customer student = new Customer();
//                student.setId(rs.getInt("id"));
//                student.setFirstName(rs.getString("first_name"));
//                student.setLastName(rs.getString("last_name"));
//                student.setProgram(rs.getString("program_name"));
//                student.setYear(Integer.toString(rs.getInt("program_year")));
//                student.setCoop(rs.getBoolean("program_coop") ? "yes" : "no");
//                students.add(student);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Cannot select the list of all students.", e);
//        } finally {
//            DatabaseUtil.closeResultSet(rs);
//            DatabaseUtil.closeStatement(st);
//            pool.freeConnection(connection);
//        }
//        return students;
//    }

    public static void delete() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement st = null;

        String update = "TRUNCATE TABLE users";
        try {
            st = connection.createStatement();
            st.executeUpdate(update);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot clear the users table.", e);
        } finally {
            DatabaseUtil.closeStatement(st);
            pool.freeConnection(connection);
        }
    }

    public static void delete(String username) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String update = "DELETE FROM users WHERE username = ?";
        try {
            ps = connection.prepareStatement(update);
            ps.setString(1, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot delete the user record.", e);
        } finally {
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static User get(String username) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null; // to be returned

        String query = "SELECT * FROM users WHERE username = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));

            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot get the user record.", e);
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

        return user;
    }

//    public static void update(Customer student) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//
//        String update
//                = "UPDATE student SET "
//                + "first_name = ?, last_name = ?, "
//                + "program_name = ?, program_year = ?, program_coop = ? "
//                + "WHERE id = ?";
//        try {
//            ps = connection.prepareStatement(update);
//            ps.setString(1, student.getFirstName());
//            ps.setString(2, student.getLastName());
//            ps.setString(3, student.getProgram());
//            ps.setInt(4, Integer.parseInt(student.getYear()));
//            ps.setBoolean(5, student.getCoop().equals("yes"));
//            ps.setInt(6, student.getId());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Cannot update the student record.", e);
//        } finally {
//            DatabaseUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
//        }
//    }

}
