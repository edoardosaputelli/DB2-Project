package it.polimi.db2.servlets;

import it.polimi.db2.Exceptions.DatabaseFailException;
import it.polimi.db2.ejb.AdminManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet(name = "AdminAddProductServlet", urlPatterns = {"/AdminAddProductServlet"})
public class AdminAddProductServlet extends HttpServlet {
    @EJB(name = "it.polimi.db2.ejb/AdminManager")
    private AdminManager adminManager;

    //this method takes from request a name, a date and an img to add a new product to the application
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String stringDate = (String) request.getParameter("chosenDate");
        String productName = (String) request.getParameter("productName");
        byte [] img = null;
        boolean didIt = false;

        //DA RIVEDERE

        img = (byte []) request.getAttribute("image");
        Date date = adminManager.fromStringToDate(stringDate);

        //check if the date inserted is a day already passed
        if(date.before(Date.valueOf(LocalDate.now()))) {
            request.getRequestDispatcher("WEB-INF/adminAddProduct.jsp?invalidDate").forward(request, response);
        }


        try {
            didIt = adminManager.addProduct(productName, date, img);
        }catch (DatabaseFailException ex) {
            request.getRequestDispatcher("WEB-INF/redirectDatabaseError.jsp").forward(request, response);
        }

        if(didIt) {

            request.getSession().setAttribute("givenDate", date);
            //redirect to a page that asks for the questions to be associated with the product (with AdminAddQuestionsServlet)
            request.getRequestDispatcher("WEB-INF/adminAddQuestions.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("WEB-INF/adminAddProduct.jsp?alreadyOccupiedDate").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
