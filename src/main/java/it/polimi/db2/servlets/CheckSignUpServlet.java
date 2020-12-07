package it.polimi.db2.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CheckSignUpServlet", urlPatterns = {"/CheckSignUpServlet"})
public class CheckSignUpServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        response.setContentType("text/plain");
        PrintWriter pw = response.getWriter();
        pw.println(username +"\n" +password +"\n" +email);
        pw.close();





    }


}
