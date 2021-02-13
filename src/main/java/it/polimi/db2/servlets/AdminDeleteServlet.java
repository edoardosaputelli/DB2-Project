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

@WebServlet(name = "AdminDeleteServlet", urlPatterns = {"/AdminDeleteServlet"})
public class AdminDeleteServlet extends HttpServlet {
    @EJB(name = "it.polimi.db2.ejb/AdminManager")
    private AdminManager adminManager;

    //this method takes from request a date and deletes the associated product, questionnaire etc. (if present)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String stringDate = (String) request.getParameter("chosenDate");
        Date date = adminManager.fromStringToDate(stringDate);
        boolean wentFine = false;

        //check if the date inserted is a day already passed
        if(!date.before(Date.valueOf(LocalDate.now()))) {
            //there is a redirect to the same page asking for another date
            request.getRequestDispatcher("WEB-INF/adminDelete.jsp?errorString=invalidDate").forward(request, response);

        } else {


            //actual deletion
            try {
                wentFine = adminManager.deleteQuestionnaireData(date);
            } catch (DatabaseFailException ex) {
                //redirect to generic error page
                request.getRequestDispatcher("WEB-INF/redirectDatabaseError.jsp").forward(request, response);
            }


            if (wentFine) {
                //send to a page telling that the deletion went through
                request.getRequestDispatcher("WEB-INF/adminHome.jsp?parameterString=newProductHasBeenDeleted").forward(request, response);
            } else {
                //redirect to the same page asking for another date
                request.getRequestDispatcher("WEB-INF/adminDelete.jsp?errorString=emptyDate").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
