package it.polimi.db2.servlets;

import it.polimi.db2.Exceptions.DatabaseFailException;
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


    //this servlet call is associated to the cancel button at the end of the questionniare
    //it tells the manager to set the response of that user for today's questionnaire to cancelled
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        QuestionnaireEntity questionnaire = questionnaireManager.getMarketingQuestionEntityList().get(0).getQuestionnaire();
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");

        try {
            questionnaireManager.cancelQuestionnaire(questionnaire, user);

        }catch(DatabaseFailException ex) {
            request.getRequestDispatcher("WEB-INF/redirectDatabaseError.jsp").forward(request, response);
        }


        request.getRequestDispatcher("WEB-INF/cancelledQuestionnaire.jsp").forward(request, response);
        questionnaireManager.setSessionMapsNull(request);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
