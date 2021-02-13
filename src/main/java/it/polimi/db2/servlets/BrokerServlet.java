package it.polimi.db2.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//this servlet manages the different redirections to the jsp pages from other jsp pages
//this because they can't be accessed otherwise, because they are only found in the WEB-INF folder (except for index.jsp)
@WebServlet(name = "BrokerServlet", urlPatterns = {"/BrokerServlet"})
public class BrokerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //the desired page is stored in a parameter in the form where the BrokerServlet is called
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
            case "adminHome":
                request.getRequestDispatcher("WEB-INF/adminHome.jsp").forward(request, response);
                break;
            case "adminControlPanel":
                request.getRequestDispatcher("WEB-INF/adminControlPanel.jsp").forward(request, response);
                break;
        }

    }

}