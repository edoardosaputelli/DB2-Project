package it.polimi.db2.servlets;

import it.polimi.db2.Exceptions.DatabaseFailException;
import it.polimi.db2.Exceptions.NothingThatDateException;
import it.polimi.db2.ejb.AdminManager;
import it.polimi.db2.entities.MarketingAnswerEntity;
import it.polimi.db2.entities.MarketingQuestionEntity;
import it.polimi.db2.entities.StatisticalAnswerEntity;
import it.polimi.db2.entities.StatisticalQuestionEntity;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "AdminOneUserServlet", urlPatterns = {"/AdminOneUserServlet"})
public class AdminOneUserServlet extends HttpServlet {
    @EJB(name = "it.polimi.db2.ejb/AdminManager")
    private AdminManager adminManager;

    //this method takes from request a username and a date and returns the answers for that user of that questionnaire
    //the case where the date has no questionnaire should not be considered as this servlet is called after adminOverallServlet

    //SHOULD ONLY BE CALLED AFTER ADMINOVERALLSERVLET
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getParameter("username");
        String chosenDay = (String) request.getParameter("givenDate");

        Date date = adminManager.fromStringToDate(chosenDay);

        List<MarketingAnswerEntity> markAnswers = new ArrayList<>();
        List<StatisticalAnswerEntity> statAnswers = new ArrayList<>();


        try {
            //i call ejb method to get the answers
            markAnswers = adminManager.retrieveMarketingAnswers(username, date);
            statAnswers= adminManager.retrieveStatisticalAnswers(username, date);

        }catch (NothingThatDateException e) {
            //shouldn't actually happen, parsing should have been done before (SEE comment before this method)
        }catch (DatabaseFailException ex) {
            //redirecting to general error page
            request.getRequestDispatcher("WEB-INF/redirectDatabaseError.jsp").forward(request, response);
        }

        //there is no user with that name
        if(markAnswers == null || markAnswers.isEmpty()){

            request.getRequestDispatcher("WEB-INF/adminControlPanel.jsp?errorString=noExistingUser").forward(request, response);
        }

        else {

            //now i pass the lists obtained so that they can be printed on the page
            request.setAttribute("markAnswers", markAnswers);
            request.setAttribute("statAnswers", statAnswers);

            request.getRequestDispatcher("WEB-INF/adminOneUser.jsp").forward(request, response);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}