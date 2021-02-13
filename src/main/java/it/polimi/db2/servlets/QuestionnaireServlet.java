package it.polimi.db2.servlets;

import it.polimi.db2.Exceptions.AlreadyDoneException;
import it.polimi.db2.Exceptions.DatabaseFailException;
import it.polimi.db2.Exceptions.NothingThatDateException;
import it.polimi.db2.ejb.QuestionnaireManager;
import it.polimi.db2.entities.MarketingQuestionEntity;
import it.polimi.db2.entities.UserEntity;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "QuestionnaireServlet", urlPatterns = {"/QuestionnaireServlet"})
public class QuestionnaireServlet extends HttpServlet {

    @EJB(name = "it.polimi.db2.ejb/QuestionnaireManager")
    private QuestionnaireManager questionnaireManager;


    //method called after the user passes to the stat questions to save its marketing answers in the session
    //in case he wants to change them (the page will print them if the user accesses the page and they're present)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HashMap<Integer, String> mapAnsQuest = new HashMap<>();

        List<MarketingQuestionEntity> mQuestionList = questionnaireManager.getMarketingQuestionEntityList();

        //i generate a map between mark questions and their string answers
        for(int i = 0; i < mQuestionList.size(); i++){

            mapAnsQuest.put(mQuestionList.get(i).getIdMarketingQuestion(), request.getParameter("question" +i));

        }

        //we set the attribute to the session and continue
        request.getSession().setAttribute("mapMarketingAnsQuest", mapAnsQuest);
        request.getRequestDispatcher("WEB-INF/successMarketingQuest.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //the QuestionnaireServlet gets the marketing questions from the Questionnaire Manager, in order to send them
        //to the marketing questionnaire page
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");

        //Checking that the user hasn't already done the questionnaire
        try {

            questionnaireManager.checkIfAlreadyDone(user);

        } catch (AlreadyDoneException e) {
            request.getRequestDispatcher("WEB-INF/redirectQuestionnaireAlreadyDone.jsp").forward(request,response);
        } catch (DatabaseFailException ex){
            request.getRequestDispatcher("WEB-INF/redirectDatabaseError.jsp").forward(request, response);
        } catch (NothingThatDateException ex){
            request.getRequestDispatcher("WEB-INF/home.jsp?errorString=noProductOfTheDay").forward(request, response);
        }


        //getting the questions to print them and letting the user answer them
        List<MarketingQuestionEntity> mQuestionList = questionnaireManager.getMarketingQuestionEntityList();

        request.setAttribute("marketingQuestions", mQuestionList);
        request.getRequestDispatcher("WEB-INF/marketingQuestionnaire.jsp").forward(request,response);

    }

}
