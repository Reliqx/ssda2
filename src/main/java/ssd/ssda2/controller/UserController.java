package ssd.ssda2.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ssd.ssda2.util.ValidatorUtil;
import ssd.ssda2.util.CookieUtil;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.*;
import javax.validation.ConstraintViolation;
import ssd.ssda2.business.User;
import ssd.ssda2.data.ConnectionPool;
import ssd.ssda2.data.LoginData;
import ssd.ssda2.data.UserData;

public class UserController {

    // a user comes to the default front page at "hello.do"
//    public static String main(HttpServletRequest request) {
//        String username = CookieUtil.getCookieValue(request.getCookies(), "username");
//        if (username.isEmpty()) {
//            //return input(request);
//        }
//        return "main"; // show "hello.jsp"
//    }

    // a user comes to the data input page at "input.do"
    public static String main(HttpServletRequest request) {
        return "main"; // show "input.jsp"
    }

    public static String login(HttpServletRequest request) {
        String view = "redirect:";
        User user = new User();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);

        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            String sql = "select * from users where username=? and password=?";
            PreparedStatement ps = null;
            ResultSet rs = null;

            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            while (rs.next()) {

                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }

            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                view = "main";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    //  a user clicks on "Forget Me" link to "forget.do"
    public static String forget(HttpServletResponse response) {
        Cookie cookie = new Cookie("username", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie); // remove the cookie
        return "redirect:."; // redirect to the default front page
    }

    // user clicks on "Continue" button in "input.jsp", 
    // the form submits the data to "next.do"
//    public static String next(HttpServletRequest request) {
//        Customer student = new Customer();
//        student.setFirstName(request.getParameter("firstName"));
//        student.setLastName(request.getParameter("lastName"));
//        student.setProgram(request.getParameter("program"));
//        student.setYear(request.getParameter("year"));
//        String coop = request.getParameter("coop");
//        student.setCoop((coop == null) ? "no" : "yes");
//        Set<ConstraintViolation<Customer>> errors
//                = ValidatorUtil.getValidator().validate(student);
//        if (errors.isEmpty()) {
//            HttpSession session = request.getSession();
//            session.setAttribute("student", student);
//            // no data saving yet, the user must look through and confirm
//            return "next"; // show "next.jsp"
//        } else {
//            request.setAttribute("errors", errors);
//            return input(request); // go back to showing "input.jsp"
//        }
    //}
//     a user clicks "Register" button in "next.jsp", the form submits to "submit.do"
//    public static String register(HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute("student") == null) {
//            return "expired"; // show "your session has expired" with "expired.jsp"
//        } else {
//            User user = (User) session.getAttribute("student");
//            StudentData.insert(student);
//            String userName = String.format("%s %s",
//                    student.getFirstName().trim(),
//                    student.getLastName().trim());
//            Cookie cookie = new Cookie("userName", userName);
//            cookie.setMaxAge(30 * 24 * 60 * 60);// one month in sec
//            response.addCookie(cookie);
//            return "redirect:thanks.do?id=" + student.getId();
//        }
//    }
//    // a user is redirected to "Thank you" page at "thanks.do"
//    public static String thanks(HttpServletRequest request) {
//        String strId = request.getParameter("id");
//        if (strId == null || strId.isEmpty()) {
//            return "notfound";
//        } else {
//            try {
//                int id = Integer.parseInt(strId);
//                Customer student = StudentData.get(id);
//                if (student == null) {
//                    return "notfound";
//                } else {
//                    request.setAttribute("student", student);
//                    return "thanks";
//                }
//            } catch (NumberFormatException e) {
//                return "notfound";
//            }
//        }
//    }
    // a user clicks on "List All" link to "listall.do", 
    // or a user is redirected to "listall.do" 
//    public static String listAll(HttpServletRequest request) {
//        List<Customer> list = StudentData.getAll();
//        request.setAttribute("visits", list);
//        if (request.isUserInRole("administrator")) {
//            return "editall";
//        } else {
//            return "readall";
//        }
//    }
    // a user clicks on "Clear All" link to "clearall.do"
    public static String clearAll() {
        UserData.delete();
        return "redirect:listall.do";
    }

    // a user clicks "Edit" link (in the table) to "edit.do" 
//    public static String edit(HttpServletRequest request) {
//
//        try {
//            int id = Integer.parseInt(request.getParameter("id"));
//            Customer student = StudentData.get(id);
//            if (student != null) {
//                request.setAttribute("student", student);
//                return "edit"; // show the student data in the form to edit
//            } else {
//                return "redirect:listall.do";
//            }
//        } catch (NumberFormatException e) {
//            return "notfound";
//        }
//    }
    // a user clicks "Update" button in "edit.jsp", 
    // the form submits the data to "update.do"
//    public static String update(HttpServletRequest request) {
//        Customer student = new Customer();
//        try {
//            student.setId(Integer.parseInt(request.getParameter("id")));
//            student.setFirstName(request.getParameter("firstName"));
//            student.setLastName(request.getParameter("lastName"));
//            student.setProgram(request.getParameter("program"));
//            student.setYear(request.getParameter("year"));
//            String coop = request.getParameter("coop");
//            student.setCoop((coop == null) ? "no" : "yes");
//            Set<ConstraintViolation<Customer>> errors
//                    = ValidatorUtil.getValidator().validate(student);
//            if (errors.isEmpty()) {
//                StudentData.update(student);
//                return "redirect:listall.do";
//            } else {
//
//                request.setAttribute("errors", errors);
//                request.setAttribute("student", student);
//                return "edit";
//            }
//        } catch (NumberFormatException e) {
//            return "notfound";
//        }
//    }
    // a user cliks "Delete" link (in the table) to "delete.do"
//    public static String delete(HttpServletRequest request) {
//        try {
//            int id = Integer.parseInt(request.getParameter("id"));
//            Customer student = StudentData.get(id);
//            if (student != null) {
//                request.setAttribute("student", student);
//                return "delete"; // ask "Do you really want to remove the data?"
//            } else {
//                return "redirect:listall.do";
//            }
//        } catch (NumberFormatException e) {
//            return "notfound";
//        }
//    }
    // a user clicks "Remove Record" button in "delete.jsp",
    // the form submits the data to "remove.do"
    public static String remove(HttpServletRequest request) {
        try {
//            UserData.delete(Integer.parseString(request.getParameter("id")));
        } catch (NumberFormatException e) {
            return "notfound";
        }
        return "redirect:listall.do";
    }

    // a user clicks "Change password" link
    public static String changePassword(HttpServletRequest request) {
        request.setAttribute("message", "Enter your new password twice");
        return "passwords";
    }

    // a user clicks "Change Password" button in "passwords.jsp",
    // the form submits data to "/update_password.do"
    public static String updatePassword(HttpServletRequest request) {
        String login = request.getRemoteUser();
        if (login == null) {
            return "expired";
        }
        String message;
        String current_password = request.getParameter("current_password");
        String new_password_1 = request.getParameter("new_password_1");
        String new_password_2 = request.getParameter("new_password_2");
        if (current_password == null || current_password.isEmpty()) {
            message = "Current password input is left empty.";
        } else if (LoginData.checkPassword(login, current_password)) {
            if (new_password_1 == null || new_password_1.isEmpty()
                    || new_password_2 == null || new_password_2.isEmpty()) {
                message = "New password input is left empty.";
            } else if (new_password_1.equals(new_password_2)) {
                LoginData.updatePassword(login, new_password_1);
                message = "Your password has been changed.";
            } else {
                message = "New password inputs do not match.";
            }
        } else {
            message = "Your current password input is wrong.";
        }
        request.setAttribute("message", message);

        return "passwords";
    }

}
