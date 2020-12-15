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

@WebServlet(name = "AdminCreateServlet")
public class AdminAddProductServlet extends HttpServlet {
    @EJB(name = "it.polimi.db2.ejb/AdminManager")
    private AdminManager adminManager;

    //this method takes from request a name, a date and an img to add a new product to the application
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stringDate = (String) request.getAttribute("chosenDay");
        String productName = (String) request.getAttribute("productName");
        byte [] img = null;
        //DA RIVEDERE
        img = (byte []) request.getAttribute("image");
        Date date = adminManager.fromStringToDate(stringDate);

        //check if the date inserted is a day already passed
        if(date.before(Date.valueOf(LocalDate.now()))) {
            //there should be a redirect to the same page asking for another date
        }

        try {
            adminManager.addProduct(productName, date, img);
        }catch (DatabaseFailException ex) {
            //add redirect to fail page
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
