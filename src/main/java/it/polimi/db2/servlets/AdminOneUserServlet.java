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
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "AdminOneUserServlet")
public class AdminOneUserServlet extends HttpServlet {
    @EJB(name = "it.polimi.db2.ejb/AdminManager")
    private AdminManager adminManager;

    //this method takes from request a username and a date and returns the answers for that user of that questionnaire
    //the case where the date has no questionnaire should not be considered as this servlet is called after adminOverallServlet
    //SHOULD ONLY BE CALLED AFTER ADMINOVERALLSERVLET
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getAttribute("username");
        Date chosenDay = (Date) request.getAttribute("chosenDay");

        List<MarketingAnswerEntity> markAnswers = null;
        List<StatisticalAnswerEntity> statAnswers = null;
        List<MarketingQuestionEntity> mQuestions = null;
        List<StatisticalQuestionEntity> sQuestions = null;

        try {
            //i call ejb method to get the answers
            markAnswers = adminManager.retrieveMarketingAnswers(username, chosenDay);
            statAnswers= adminManager.retrieveStatisticalAnswers(username, chosenDay);

            //from those i get the corresponding questions
            mQuestions = markAnswers.stream().map(MarketingAnswerEntity::getmQuestion).collect(Collectors.toList());
            sQuestions = statAnswers.stream().map(x -> x.getQuestion()).collect(Collectors.toList());

        }catch (NothingThatDateException e) {
            //shouldn't actually happen, parsing should have been done before (SEE comment before this method)
        }catch (DatabaseFailException ex) {
            //should send to the gerenal error page
        }

        //now i pass the lists obtained so that they can be printed on the page
        request.setAttribute("markAnswers", markAnswers);
        request.setAttribute("statAnswers", statAnswers);
        request.setAttribute("mQuestions", mQuestions);
        request.setAttribute("sQuestions", sQuestions);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
