package it.polimi.db2.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//this servlet manages the different redirections to the jsp pages
@WebServlet(name = "BrokerServlet", urlPatterns = {"/BrokerServlet"})
public class BrokerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestedPage = request.getParameter("redirectedPage");

        switch (requestedPage) {
            case "index":
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            case "signUp":
                request.getRequestDispatcher("WEB-INF/signUp.jsp").forward(request, response);
                break;
            case "home":
                request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
                break;
            case "adminLogin":
                request.getRequestDispatcher("WEB-INF/adminLogin.jsp").forward(request, response);
                break;
            case "adminAddProduct":
                request.getRequestDispatcher("WEB-INF/adminAddProduct.jsp").forward(request, response);
                break;
            case "adminDelete":
                request.getRequestDispatcher("WEB-INF/adminDelete.jsp").forward(request, response);
                break;
        }

    }

}