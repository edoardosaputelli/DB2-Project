package it.polimi.db2.servlets;

import it.polimi.db2.ejb.QuestionnaireManager;
import it.polimi.db2.entities.StatQuestionAlternativesEntity;
import it.polimi.db2.entities.StatisticalQuestionEntity;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "StatisticalQuestionnaireServlet", urlPatterns = {"/StatisticalQuestionnaireServlet"})
public class StatisticalQuestionnaireServlet extends HttpServlet {

    @EJB(name = "it.polimi.db2.ejb/QuestionnaireManager")
    private QuestionnaireManager questionnaireManager;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HashMap<Integer, String> mapStatAnsQuest = new HashMap<>();

        HashMap< StatisticalQuestionEntity, List<StatQuestionAlternativesEntity> >  statQList = questionnaireManager.getStatisticalQuestionEntityList();

        for(StatisticalQuestionEntity sqe : statQList.keySet()) {

            mapStatAnsQuest.put(sqe.getIdStatisticalQuestion(), request.getParameter(sqe.getQuestionText()));

        }

        request.getSession().setAttribute("mapStatAnsQuest", mapStatAnsQuest);

        //assolutamente va fatta un'eccezione più specifica se trova forbidden words

        try{

                //metodo del questionnaire manager per fare persist


        }catch(Exception ex){

            //deve fare tutta la robetta che si fa nel caso in cui ci siano forbidden words
            //come ad esempio fare redirect su di una pagina di errore e resettare gli attributi nella session

        }


        request.getRequestDispatcher("overallQuestSuccess.jsp").forward(request, response);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HashMap< StatisticalQuestionEntity, List<StatQuestionAlternativesEntity> >  statisticalQuestionEntityList = questionnaireManager.getStatisticalQuestionEntityList();

        request.setAttribute("statisticalQuestions", statisticalQuestionEntityList);
        request.getRequestDispatcher("statisticalQuestionnaire.jsp").forward(request,response);





    }

}
