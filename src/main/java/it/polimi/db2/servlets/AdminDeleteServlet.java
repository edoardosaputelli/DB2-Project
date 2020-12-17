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

@WebServlet(name = "AdminDeleteServlet")
public class AdminDeleteServlet extends HttpServlet {
    @EJB(name = "it.polimi.db2.ejb/AdminManager")
    private AdminManager adminManager;

    //this method takes from request a date and deletes the associated product, questionnaire etc. (if present)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stringDate = (String) request.getAttribute("chosenDate");
        Date date = adminManager.fromStringToDate(stringDate);
        boolean wentFine = false;

        //check if the date inserted is a day already passed
        if(!date.before(Date.valueOf(LocalDate.now()))) {
            //there should be a redirect to the same page asking for another date
        }

        //actual deletion
        try {
            wentFine = adminManager.deleteQuestionnaireData(date);
        }catch (DatabaseFailException ex) {
            //add redirect to generic error page
        }

        if(wentFine) {
            //send to a page telling that the deletion went through
        }
        else {
            //there should be a redirect to the same page asking for another date
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
