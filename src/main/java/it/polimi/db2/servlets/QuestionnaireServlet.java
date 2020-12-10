package it.polimi.db2.servlets;

import it.polimi.db2.ejb.QuestionnaireManager;
import it.polimi.db2.ejb.UserManager;
import it.polimi.db2.entities.MarketingQuestionEntity;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
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

        request.getSession().setAttribute("mapAnswerQuestions", mapAnsQuest);
        request.getRequestDispatcher("successMarketingQuest.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<MarketingQuestionEntity> mQuestionList = questionnaireManager.getMarketingQuestionEntityList();

        request.setAttribute("questions", mQuestionList);
        request.getRequestDispatcher("marketingQuestionnaire.jsp").forward(request,response);





    }






}
