package it.polimi.db2.servlets;

import it.polimi.db2.ejb.QuestionnaireManager;
import it.polimi.db2.entities.QuestionnaireEntity;
import it.polimi.db2.entities.UserEntity;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CancelQuestionnaireServlet", urlPatterns = {"/CancelQuestionnaireServlet"})
public class CancelQuestionnaireServlet extends HttpServlet {

    @EJB(name = "it.polimi.db2.ejb/QuestionnaireManager")
    private QuestionnaireManager questionnaireManager;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        QuestionnaireEntity questionnaire = questionnaireManager.getMarketingQuestionEntityList().get(0).getQuestionnaire();
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");

        questionnaireManager.cancelQuestionnaire(questionnaire, user);

        request.getRequestDispatcher("WEB-INF/cancelledQuestionnaire.jsp").forward(request, response);

        questionnaireManager.setSessionMapsNull(request);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
