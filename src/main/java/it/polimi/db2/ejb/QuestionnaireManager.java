package it.polimi.db2.ejb;

import it.polimi.db2.entities.*;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class QuestionnaireManager {

    @PersistenceContext(unitName = "projectPersistenceUnit")
    private EntityManager em;

    private List<MarketingQuestionEntity> marketingQuestionEntityList;
    private List<StatisticalQuestionEntity> statisticalQuestionEntityList;

    public QuestionnaireManager () {}


    @PostConstruct
    public void init(){

        this.marketingQuestionEntityList = retrieveMarketingQuestions();
        this.statisticalQuestionEntityList = retrieveStatisticalQuestions();

    }


    public List<StatisticalQuestionEntity> getStatisticalQuestionEntityList() {
        return statisticalQuestionEntityList;
    }

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
        /*List<String> ansWords = Arrays.asList(answerText.split(" ").clone());
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

        }*/

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


    public List<MarketingQuestionEntity> getMarketingQuestionEntityList() {
        return marketingQuestionEntityList;
    }

    private List<MarketingQuestionEntity> retrieveMarketingQuestions(){


        Date today = Date.valueOf(LocalDate.now());

        try{
            //da aggiungere eccezione se ritorna più prodotti
            /*List<ProductEntity> listOfProduct = em.createNamedQuery("ProductEntity.getProductOfTheDay", ProductEntity.class)
                    .setParameter("today",today, TemporalType.DATE).getResultList();*/



            List<ProductEntity> listOfProduct = em.createQuery("SELECT r FROM ProductEntity r")
                    .getResultList();


            List<MarketingQuestionEntity> listMQ = listOfProduct.get(0).getQuestionnaire().getmList();


            if(listOfProduct.size() != 1){
                //eccezione da tirare
            }


            else{

                return listMQ;

            }

        }catch (PersistenceException ex){

            ex.printStackTrace();
        }


        return null;


    }

    private List<StatisticalQuestionEntity> retrieveStatisticalQuestions(){

        try{

            List<StatisticalQuestionEntity> listOfQuestions = em.createQuery("SELECT r FROM StatisticalQuestionEntity r")
                    .getResultList();

            return listOfQuestions;


        }catch (PersistenceException ex){

            ex.printStackTrace();
        }


        return null;

    }


    //support method for checkForOffensiveWords
    //probably won't be needed for all
    public List<String> convertToStringList (List<ForbiddenWordsEntity> fWList) {
        List<String> sList = null;
        sList = fWList.stream().map(obj -> obj.getForbiddenWord()).collect(Collectors.toList());

        return sList;
    }

}
