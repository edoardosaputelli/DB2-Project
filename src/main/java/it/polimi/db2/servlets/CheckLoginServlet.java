package it.polimi.db2.servlets;

import it.polimi.db2.ejb.UserManager;
import it.polimi.db2.entities.UserEntity;

import javax.ejb.EJB;
import javax.persistence.NonUniqueResultException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CheckLoginServlet", urlPatterns = {"/CheckLoginServlet"})


public class CheckLoginServlet extends HttpServlet {

    @EJB(name = "it.polimi.db2.ejb/UserManager")
    private UserManager userManager;

    public CheckLoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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


        UserEntity user;
        try {
            // query db to authenticate for user
            user = userManager.checkCredentials(username, password);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not check credentials");
            return;
        }

        // If the user exists, add info to the session and go to home page, otherwise
        // show login page with error message

        String path;

        //the user doesn't exist
        if (user == null) {

            //the error is printed on the login page
            request.getSession().setAttribute("user", null);

            response.setContentType( "text/html" );
            path = getServletContext().getContextPath() + "/index.jsp?errorString=invalidUser";
            response.sendRedirect(path);

            /*response.setContentType( "text/html" );
            request.getRequestDispatcher("/index.jsp?errorString=invalidUser.jsp").forward(request, response);*/

        }

        //the user does exist
        else
        {
            //if the attribute "user" of the session is not null, the user is already logged in
            if(request.getSession().getAttribute("user") != null){

                response.setContentType( "text/html" );
                path = getServletContext().getContextPath() + "/index.jsp?errorString=alreadyLoggedIn";
                response.sendRedirect(path);

                /*response.setContentType( "text/html" );
                request.getRequestDispatcher("/index.jsp?errorString=alreadyLoggedIn.jsp").forward(request, response);*/
            }


            //the user has been logged in: he is redirected to the home page
            else {

                request.getSession().setAttribute("user", user);
                request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);

                /*path = getServletContext().getContextPath() + "/home.jsp";
                response.sendRedirect(path);*/

            }

        }

    }


}
