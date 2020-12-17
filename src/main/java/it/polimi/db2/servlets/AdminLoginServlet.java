package it.polimi.db2.servlets;

import it.polimi.db2.ejb.AdminManager;
import it.polimi.db2.entities.AdminEntity;
import it.polimi.db2.entities.UserEntity;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {

    @EJB(name = "it.polimi.db2.ejb/AdminManager")
    private AdminManager adminManager;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // obtain and escape params
        String username = null;
        String password = null;
        try {
            username = request.getParameter("username");
            password = request.getParameter("password");

            if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
                throw new Exception("Missing or empty credential value");
            }

        } catch (Exception e) {
            // for debugging only e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing credential value");
            return;
        }


        AdminEntity admin;
        try {
            // query db to authenticate for user
            admin = adminManager.checkCredentials(username, password);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not check credentials");
            return;
        }

        // If the user exists, add info to the session and go to home page, otherwise
        // show login page with error message

        String path;

        if (admin == null) {

            //REWRITE PATHS

        }

        else
        {


            if(request.getSession().getAttribute("user") != null){

                //REWRITE PATHS

                response.setContentType( "text/html" );
                path = getServletContext().getContextPath() + "/index.jsp?errorString=alreadyLoggedIn";
                response.sendRedirect(path);

            }


            else {


                //REWRITE PATHS

                //the user has been logged in: he is redirected to the home page
                request.getSession().setAttribute("admin", admin);

                path = getServletContext().getContextPath() + "/home.jsp";
                response.sendRedirect(path);

            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
