package ssd.ssda2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class MainServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();
        String jspPath = "/WEB-INF/jsp/";
        String showNext; // where to forward/redirect

        switch (action) {

            // a user comes to the default front page at "hello.do"
            case "/main.do": {
                showNext = UserController.main(request);
                break;
            }
            
            case "/login.do":{
                showNext = UserController.login(request);
                break;
            }

            // a user comes to the first input form at "input.do"
//            case "/register.do": {
//                showNext = UserController.register(request);
//                break;
//            }
//
//            // a user clicks "Forget Me" link to "forget.do"
//            case "/forget.do": {
//                showNext = StudentController.forget(response);
//                break;
//            }

            // a user clicks "Continue" button in "input.jsp", 
            // the form submits the data to "next.do"
//            case "/next.do": {
//                showNext = StudentController.next(request);
//                break;
//            }

            // a user clicks "Register" button in "next.jsp", 
            // the form submits the data to "submit.do"
//            case "/submit.do": {
//                showNext = StudentController.submit(request, response);
//                break;
//            }

            // a user is redirected to "Thank you" page at "thanks.do"
//            case "/thanks.do": {
//                showNext = StudentController.thanks(request);
//                break;
//            }

            // a user clicks on "List All" link to "listall.do", 
            // or a user is redirected to "listall.do"
//            case "/listall.do": {
//                showNext = StudentController.listAll(request);
//                break;
//            }

//            // a user clicks on "Clear All" link to "clearall.do"
//            case "/clearall.do": {
//                showNext = StudentController.clearAll();
//                break;
//            }

            // a user click on "Edit" link (in the table) to "edit.do" 
//            case "/edit.do": {
//                showNext = StudentController.edit(request);
//                break;
//            }

            // a user clicks "Update" button in "edit.jsp", 
            // the form submits the data to "update.do"
//            case "/update.do": {
//                showNext = StudentController.update(request);
//                break;
//            }

            // a user cliks "Delete" link (in the table) to "delete.do"
//            case "/delete.do": {
//                showNext = StudentController.delete(request);
//                break;
//            }

            // a user clicks "Remove Record" button in "delete.jsp",
            // the form submits the data to "remove.do"
//            case "/remove.do": {
//                showNext = StudentController.remove(request);
//                break;
//            }
//
//            // a user clicks "Change password" link
//            case "/change_password.do": {
//                showNext = StudentController.changePassword(request);
//                break;
//            }
//
//            // a user clicks "Change Password" button in "passwords.jsp",
//            // the form submits data to "/update_password.do"
//            case "/update_password.do": {
//                showNext = StudentController.updatePassword(request);
//                break;
//            }

            //Listing all the administrators/assistants
//            case "/adminlistall.do": {
//                System.out.println("student servlet switch statement");
//                showNext = AdminController.listAll(request);
//                break;
//            }

//            //Sends to the input page for adding administrator
//            case "/admininput.do": {
//                showNext = AdminController.input(request);
//                break;
//            }
//            
//            //Not an actual page 
//            //Deletes the user and sends back to list all giving the illusion that it just "disappears"
//            case "/admindelete.do": {
//                showNext = AdminController.remove(request);
//                break;
//            }
            
            //Not an actual page
            //Adds the administrator and redirects to list all 
//            case "/adminsubmit.do": {
//                showNext = AdminController.submit(request);
//                break;
//            }

            default: {
                response.sendError(404);
                return;
            }
        }
        String greeting = getInitParameter("greeting");
        request.setAttribute("greeting", greeting);

        if (showNext.startsWith("redirect:")) {
            response.sendRedirect(response.encodeRedirectURL(showNext.substring(9)));
        } else {
            request.getRequestDispatcher(jspPath + showNext + ".jsp")
                    .forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Student data collection servlet";
    }// </editor-fold>

}
