package it.polimi.db2.servlets;

import it.polimi.db2.Exceptions.BadLanguageException;
import it.polimi.db2.ejb.QuestionnaireManager;
import it.polimi.db2.ejb.UserManager;
import it.polimi.db2.entities.*;

import javax.ejb.EJB;
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "StatisticalQuestionnaireServlet", urlPatterns = {"/StatisticalQuestionnaireServlet"})
public class StatisticalQuestionnaireServlet extends HttpServlet {

    @EJB(name = "it.polimi.db2.ejb/QuestionnaireManager")
    private QuestionnaireManager questionnaireManager;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HashMap<Integer, String> mapStatAnsQuest = new HashMap<>();

        HashMap<StatisticalQuestionEntity, List<StatQuestionAlternativesEntity>> statQList = questionnaireManager.getStatisticalQuestionEntityList();

        for (StatisticalQuestionEntity sqe : statQList.keySet()) {

            mapStatAnsQuest.put(sqe.getIdStatisticalQuestion(), request.getParameter(sqe.getQuestionText()));

        }

        request.getSession().setAttribute("mapStatAnsQuest", mapStatAnsQuest);

        //TUTTO QUELLO CHE SEGUE VA MESSO IN UN RAMO IF CHE VIENE PERCORSO SOLO SE L'UTENTE SUBMITTA


        HashMap<Integer, String> mapMarketingAnsQuest = (HashMap<Integer, String>) request.getSession().getAttribute("mapMarketingAnsQuest");
        UserEntity currentUser =(UserEntity)request.getSession().getAttribute("user");

        //check, on the submitting of the questionnaire, if the user inserted forbidden words
        try {


            if (questionnaireManager.checkForOffensiveWords(mapMarketingAnsQuest)) {
                throw new BadLanguageException();
            }
            ;

            //metodo del questionnaire manager per fare persist


        } catch (BadLanguageException e) {
            //user is banned
            ;
            questionnaireManager.banUser(currentUser);

            //and redirected to an error page
            String path;
            response.setContentType("text/html");
            path = getServletContext().getContextPath() + "/index.jsp?errorString=bannedUser";
            response.sendRedirect(path);


            setSessionMapsNull(request);
        }


        //if everything went fine the answers are saved and so is a QuestionnaireResponse with flag 0
        try {

            //unreadable calls of unreadable methods
            List<MarketingAnswerEntity> mAnswers = formatMarketingAnswers (currentUser, questionnaireManager.getMarketingQuestionEntityList(),  mapMarketingAnsQuest);
            List<StatisticalAnswerEntity> sAnswers = formatStatisticalAnswers (currentUser, questionnaireManager.getStatisticalQuestionEntityList().keySet().stream().collect(Collectors.toList()), mapStatAnsQuest );

            questionnaireManager.persistQuestionnaireAnswers(mAnswers, sAnswers, currentUser);

            request.getRequestDispatcher("overallQuestSuccess.jsp").forward(request, response);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HashMap<StatisticalQuestionEntity, List<StatQuestionAlternativesEntity>> statisticalQuestionEntityList = questionnaireManager.getStatisticalQuestionEntityList();

        request.setAttribute("statisticalQuestions", statisticalQuestionEntityList);
        request.getRequestDispatcher("statisticalQuestionnaire.jsp").forward(request, response);


    }





    private List<MarketingAnswerEntity> formatMarketingAnswers(UserEntity user, List<MarketingQuestionEntity> mList, HashMap<Integer, String> mapMarketingAnsQuest) {
        ArrayList<MarketingAnswerEntity> mAnsList = new ArrayList<>();

        //for each identifier of a question it searches its text and builds the answer entity, than adds it to the list
        for (Integer i: mapMarketingAnsQuest.keySet()) {
            for (MarketingQuestionEntity question : mList) {
                if(i == question.getIdMarketingQuestion()) {
                    mAnsList.add(new MarketingAnswerEntity(question, user , mapMarketingAnsQuest.get(i)));
                }
            }
        }

        return mAnsList;
    }




    private List<StatisticalAnswerEntity> formatStatisticalAnswers(UserEntity user, List <StatisticalQuestionEntity> sList , HashMap<Integer, String> mapStatAnsQuest) {
        List<StatisticalAnswerEntity> sAnsList = new ArrayList<>();

        QuestionnaireEntity todaysQuestionnaire = questionnaireManager.getMarketingQuestionEntityList().get(0).getQuestionnaire();

        //for each identifier of a question it searches its text and builds the answer entity, than adds it to the list
        for (Integer i: mapStatAnsQuest.keySet()) {
            for(StatisticalQuestionEntity question : sList) {
                if (i == question.getIdStatisticalQuestion()) {
                    sAnsList.add(new StatisticalAnswerEntity(user, question, mapStatAnsQuest.get(i), todaysQuestionnaire));
                }
            }
        }

        return sAnsList;
    }



    private void setSessionMapsNull(HttpServletRequest request) {
        request.getSession().setAttribute("mapStatAnsQuest", null);
        request.getSession().setAttribute( "mapMarketingAnsQuest", null);

    }
}
