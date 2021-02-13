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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing credential value");
            return;
        }

        UserEntity user;
        try {
            // query db for user registration
            user = userManager.registerUser(username, password, email);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not register user");
            return;
        }


        //the user is already registered
        if(user==null) {

            //he is redirected to the index page with an error
            response.setContentType( "text/html" );
            request.getRequestDispatcher("index.jsp?errorString=alreadyRegistered").forward(request, response);

        }

        //the user has been registered
        else
        {
            //he is redirected to the login page
            request.getRequestDispatcher("index.jsp").forward(request, response);

        }

    }


}