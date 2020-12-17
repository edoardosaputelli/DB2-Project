package it.polimi.db2.servlets;

import it.polimi.db2.Exceptions.AlreadyDoneException;
import it.polimi.db2.ejb.QuestionnaireManager;
import it.polimi.db2.entities.MarketingQuestionEntity;
import it.polimi.db2.entities.UserEntity;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "QuestionnaireServlet", urlPatterns = {"/QuestionnaireServlet"})
public class QuestionnaireServlet extends HttpServlet {

    @EJB(name = "it.polimi.db2.ejb/QuestionnaireManager")
    private QuestionnaireManager questionnaireManager;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HashMap<Integer, String> mapAnsQuest = new HashMap<>();

        List<MarketingQuestionEntity> mQuestionList = questionnaireManager.getMarketingQuestionEntityList();

        for(int i = 0; i < mQuestionList.size(); i++){

            mapAnsQuest.put(mQuestionList.get(i).getIdMarketingQuestion(), request.getParameter("question" +i));

        }

        //da inserire l'annullamento dell'attributo in caso di cancellazione o invio del questionario
        request.getSession().setAttribute("mapMarketingAnsQuest", mapAnsQuest);
        request.getRequestDispatcher("WEB-INF/successMarketingQuest.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //the QuestionnaireServlet gets the marketing questions from the Questionnaire Manager, in order to send them
        //to the marketing questionnaire page
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");

        //Checking that the user hasn't already done the questionnaire(EDOOOOOOOOOO!!!!!)

        try {

            questionnaireManager.checkIfAlreadyDone(user);

        } catch (AlreadyDoneException e) {

            //Eduardo gestisci 'sta cosa
            request.getRequestDispatcher("WEB-INF/redirectQuestionnaireAlreadyDone.jsp").forward(request,response);

        }


        List<MarketingQuestionEntity> mQuestionList = questionnaireManager.getMarketingQuestionEntityList();

        request.setAttribute("marketingQuestions", mQuestionList);
        request.getRequestDispatcher("WEB-INF/marketingQuestionnaire.jsp").forward(request,response);





    }






}
