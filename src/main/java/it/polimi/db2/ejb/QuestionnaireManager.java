package it.polimi.db2.ejb;

import it.polimi.db2.entities.ForbiddenWordsEntity;
import it.polimi.db2.entities.MarketingAnswerEntity;
import it.polimi.db2.entities.StatisticalAnswerEntity;
import it.polimi.db2.entities.UserEntity;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Stateful
public class QuestionnaireManager {
    @PersistenceContext(unitName = "projectPersistenceUnit", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public QuestionnaireManager () {}

    //TBD
    //to tell database that the user has started completing the questionnaire
    public void declareQuestionnaireCompletion (int userId) {}

    //TBD
    //CAUTION is it okay like this or the argument must be a list?
    //REMEMBER that if the user cancels no answer should be put in database!
    public void FillMarketingQuestion (int userId, MarketingAnswerEntity answer) {}

    //TBD
    public void FillStatisticalQuestion (int userId,StatisticalAnswerEntity answer) {}

    //TBD
    //to be used together with UserManager's banUser!
    //returns true if the text of an answer contains one of the forbidden words
    public boolean checkForOffensiveWords(int userId, String answerText) {
        List<String> ansWords = Arrays.asList(answerText.split(" ").clone());
        int numOfForbidden = 0;
        ForbiddenWordsEntity currentForbidden;

        try {
             numOfForbidden = em.createNamedQuery("ForbiddenWordsCount", ForbiddenWordsEntity.class).getFirstResult();

        }catch (PersistenceException e) {
            e.printStackTrace();
        }



        for(int i=0;i<numOfForbidden; i++){

            try {
                 currentForbidden = em.createNamedQuery("ForbiddenWordN", ForbiddenWordsEntity.class)
                 .setParameter(1, i-1).getSingleResult();

            }catch (PersistenceException e) {
                e.printStackTrace();
            }

            for (String s: ansWords) {
                s.toUpperCase().equals(currentForbidden.getForbiddenWord());
            }

        }

        /*
        List<ForbiddenWordsEntity> fWList = null;

        try {
            fWList = em.createNamedQuery("ForbiddenWordsRetrieval", ForbiddenWordsEntity.class)
                    .getResultList();

        }catch (PersistenceException e) {
            e.printStackTrace();
        }

        List<String> sList = convertToStringList(fWList);

        for (String s: sList) {
                if (answerText.contains(s)) {
                    return true;
                }
        }

         */

        return false;
    }

    //TBD
    //to correct the statement that questionnaire was going to be completed
    public void cancelQuestionnaireCompletion (int userId) {}


    //support method for checkForOffensiveWords
    //probably won't be needed for all
    public List<String> convertToStringList (List<ForbiddenWordsEntity> fWList) {
        List<String> sList = null;
        sList = fWList.stream().map(obj -> obj.getForbiddenWord()).collect(Collectors.toList());

        return sList;
    }

}
