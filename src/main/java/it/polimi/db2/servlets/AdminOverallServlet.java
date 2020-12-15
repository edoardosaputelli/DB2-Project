package it.polimi.db2.servlets;

import it.polimi.db2.Exceptions.NothingThatDateException;
import it.polimi.db2.ejb.AdminManager;
import it.polimi.db2.entities.UserEntity;

import javax.ejb.EJB;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "AdminOverallServlet")
public class AdminOverallServlet extends HttpServlet {
    @EJB(name = "it.polimi.db2.ejb/AdminManager")
    private AdminManager adminManager;

    //this method gets a chosenDate parameter from session and sets as its attributes the users who completed/canceled
    // the questionnaire for that day
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //STRING NEEDS TO BE PARSED before arriving here!
        String dateString = request.getParameter("chosenDate");
        Date date = adminManager.fromStringToDate(dateString);
        List<UserEntity> onesWhoCancelledIt = null;
        List<UserEntity> onesWhoCompletedIt = null;

        try {
            onesWhoCompletedIt = adminManager.retrieveQuestionnaireResponders(date, false);
            onesWhoCancelledIt = adminManager.retrieveQuestionnaireResponders(date, true);
        } catch(NothingThatDateException ex) {

            //here it should redirect back to the page where you insert the date for which you wanna know about;

        }

        //the lists could be empty because there could be not a single user who did/canceled the questionnaire
        //IN THAT CASE SIMPLY PRINT A MESSAGE EXPLAINING
        request.setAttribute("onesWhoCompletedIt", onesWhoCompletedIt);
        request.setAttribute("onesWhoCancelledIt", onesWhoCancelledIt);

        //is there anything else i need to do here? i don't know actually

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }


}
