package it.polimi.db2.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminLogOutServlet", urlPatterns = {"/AdminLogOutServlet"})
public class AdminLogOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //just setting the admin of the session to null and redirecting to the login page
        request.getSession().setAttribute("admin", null);
        request.getRequestDispatcher("index.jsp").forward(request, response);



    }
}
