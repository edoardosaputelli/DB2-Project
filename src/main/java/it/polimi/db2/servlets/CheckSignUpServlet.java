package it.polimi.db2.servlets;

import it.polimi.db2.ejb.UserManager;
import it.polimi.db2.entities.UserEntity;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CheckSignUpServlet", urlPatterns = {"/CheckSignUpServlet"})
public class CheckSignUpServlet extends HttpServlet {

    @EJB(name = "it.polimi.db2.ejb/UserManager")
    private UserManager userManager;


    /*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        response.setContentType("text/plain");
        PrintWriter pw = response.getWriter();
        pw.println(username +"\n" +password +"\n" +email);
        pw.close();
    }*/

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // obtain and escape params
        String username = null;
        String password = null;
        String email = null;

        try {
            username = request.getParameter("username");
            password = request.getParameter("password");
            email = request.getParameter("email");

            if (username == null || password == null || email == null ||
                    username.isEmpty() || password.isEmpty() || email.isEmpty()) {
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
            user = userManager.registerUser(username, password, email);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not register user");
            return;
        }

        //If the user is registered, he is redirected to a successful page, from where he can go to the login page.
        //If the user is not registered, he was already in the database, so he is directly redirected to the login page with an error.

        String path;

        if(user==null) {

            //the user is already registered: the error is printed on the login page
            response.setContentType( "text/html" );
            path = getServletContext().getContextPath() + "/index.jsp?errorString=alreadyRegistered";
            response.sendRedirect(path);

        }
        else
        {
            //the user has been registered: he is redirected to the successful Sign Up page
            request.getSession().setAttribute("user", user);
            path = getServletContext().getContextPath() + "/successfulSignUp.jsp";
            response.sendRedirect(path);
        }

    }


}