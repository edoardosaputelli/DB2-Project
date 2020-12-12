package it.polimi.db2.ejb;

import it.polimi.db2.entities.*;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class QuestionnaireManager {

    @PersistenceContext(unitName = "projectPersistenceUnit")
    private EntityManager em;

    private List<MarketingQuestionEntity> marketingQuestionEntityList;
    private HashMap< StatisticalQuestionEntity, List<StatQuestionAlternativesEntity> >  statisticalQuestionEntityList;

    public QuestionnaireManager () {}


    @PostConstruct
    public void init(){

        this.marketingQuestionEntityList = retrieveMarketingQuestions();
        this.statisticalQuestionEntityList = retrieveStatisticalQuestions();

    }


    public HashMap< StatisticalQuestionEntity, List<StatQuestionAlternativesEntity> >  getStatisticalQuestionEntityList() {
        return statisticalQuestionEntityList;
    }



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

    public List<MarketingQuestionEntity> getMarketingQuestionEntityList() {
        return marketingQuestionEntityList;
    }


    //la query sul prodotto non ha where sulla data!!!!!
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

    private HashMap< StatisticalQuestionEntity, List<StatQuestionAlternativesEntity> > retrieveStatisticalQuestions(){

        try{

            HashMap<StatisticalQuestionEntity, List<StatQuestionAlternativesEntity> > mapStatQuestAlternatives = new HashMap<>();

            List<StatisticalQuestionEntity> listOfQuestions = em.createQuery("SELECT r FROM StatisticalQuestionEntity r")
                    .getResultList();


            for(StatisticalQuestionEntity sqe : listOfQuestions){


              List<StatQuestionAlternativesEntity>  listOfAlt = em.createQuery("SELECT u FROM StatQuestionAlternativesEntity u WHERE u.statisticalQuestion.idStatisticalQuestion = ?1")
                        .setParameter(1, sqe.getIdStatisticalQuestion()).getResultList();


              mapStatQuestAlternatives.put(sqe, listOfAlt);


            }

            return mapStatQuestAlternatives;


        }catch (PersistenceException ex){

            ex.printStackTrace();
        }


        return null;

    }


    //bisogna scrivere un eccezione (banale) da tirare nel caso in cui trovi offensive words
    public void persistQuestionnaireAndCheck(int userId, HashMap<Integer, String> mapMarkAnsQuest, HashMap< StatisticalQuestionEntity, List<StatQuestionAlternativesEntity> > mapStatAnsQuest){



    }


    //support method for checkForOffensiveWords
    //probably won't be needed for all
    public List<String> convertToStringList (List<ForbiddenWordsEntity> fWList) {
        List<String> sList = null;
        sList = fWList.stream().map(obj -> obj.getForbiddenWord()).collect(Collectors.toList());

        return sList;
    }

}
