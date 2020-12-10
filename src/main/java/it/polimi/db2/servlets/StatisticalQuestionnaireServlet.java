package it.polimi.db2.servlets;

import it.polimi.db2.ejb.QuestionnaireManager;
import it.polimi.db2.entities.MarketingQuestionEntity;
import it.polimi.db2.entities.StatisticalQuestionEntity;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "StatisticalQuestionnaireServlet", urlPatterns = {"/StatisticalQuestionnaireServlet"})
public class StatisticalQuestionnaireServlet extends HttpServlet {

    @EJB(name = "it.polimi.db2.ejb/QuestionnaireManager")
    private QuestionnaireManager questionnaireManager;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




        List<StatisticalQuestionEntity> statisticalQuestionEntityList = questionnaireManager.getStatisticalQuestionEntityList();

        request.setAttribute("statisticalQuestions", statisticalQuestionEntityList);
        request.getRequestDispatcher("statisticalQuestionnaire.jsp").forward(request,response);





    }
}
