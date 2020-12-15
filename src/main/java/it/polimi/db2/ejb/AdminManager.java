package it.polimi.db2.ejb;

import it.polimi.db2.entities.*;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AdminManager {

    @PersistenceContext(unitName = "projectPersistenceUnit")
    private EntityManager em;

    public AdminManager (){}

    //exact same method present for User, but the Admin will use a different Servlet and will need
    public AdminEntity checkCredentials (String username, String password) throws Exception{
        List<AdminEntity> aList = null;

        try {
            aList = em.createNamedQuery("AdminEntity.checkLogin", AdminEntity.class).setParameter(1, username
            ).setParameter(2, password)
                    .getResultList();
        } catch (PersistenceException e) {

            e.printStackTrace();

            throw new Exception("Could not verify credentials");

        }


        if (aList.isEmpty())
            return null;
        else if (aList.size() == 1)
            return aList.get(0);
        throw new NonUniqueResultException("More than one user registered with same credentials");

    }

    //method returns false if a product is already associated to that day, true if the operation went through
    public boolean addProduct (String productName, Date chosenDay, byte[] image) throws Exception {

        ProductEntity addedProduct = new ProductEntity(productName, chosenDay, image);

        if (!em.createNamedQuery("ProductEntity.getProductOfGivenDay", ProductEntity.class).setParameter("givenDate", chosenDay, TemporalType.DATE).getResultList().isEmpty()) {
            return false;
        }
        
        try {
            em.persist(addedProduct);
            em.flush();

        }catch (PersistenceException e) {
            e.printStackTrace();
            System.out.println("The product was not added");
        }

        return true;
    }

    //this method adds the questionnaire entity and the set of its marketing answers
    //TO BE CALLED AFTER THE PRODUCT HAS BEEN INSERTED
    public void addMarketingQuestions(Date chosenDay, List<String> questionTextList) throws Exception {

        // i retrieve the product for the chosen date
        List<ProductEntity> products = em.createNamedQuery("ProductEntity.getProductOfGivenDay", ProductEntity.class).
                setParameter("givenDate", chosenDay, TemporalType.DATE).getResultList();

        ProductEntity givenProduct;
        QuestionnaireEntity quest = new QuestionnaireEntity();
        MarketingQuestionEntity currentMarkQuestion;

        //now i check if the product is only one for the chosen date ad if yes i create the questionnaire
        if (products.isEmpty()) {
            //eccezione da tirare
        }
        else if (products.size()>1) {
            //altra eccezione da tirare
        }
        else {
            givenProduct = products.get(0);
            quest = new QuestionnaireEntity(givenProduct);

        }

        //now i persist the questionnaire, then generate the associated MarketingQuestions and persist them
        try{

            em.persist(quest);

            for (String s: questionTextList) {
                currentMarkQuestion = new MarketingQuestionEntity(quest, s);
                em.persist(currentMarkQuestion);
            }

            em.flush();

        }catch (PersistenceException ex) {
            ex.printStackTrace();
        }
    }

    //method returns true if the operation went through, false if there was no data to be deleted from that day
    public boolean deleteQuestionnaireData (Date chosenDay) throws Exception{
        ProductEntity toBeDeletedProduct;
        QuestionnaireEntity toBeDeletedQuestionnaire;

        if (!em.createNamedQuery("ProductEntity.getProductOfGivenDay", ProductEntity.class).setParameter("givenDate", chosenDay, TemporalType.DATE).getResultList().isEmpty()) {
            return false;
        }

        // i get the product from that day, then its questionnaire
        toBeDeletedProduct = em.createNamedQuery("ProductEntity.getProductOfGivenDay", ProductEntity.class).setParameter("givenDate", chosenDay, TemporalType.DATE).getResultList().get(0);
        toBeDeletedQuestionnaire = em.createNamedQuery("QuestionnaireEntity.questOfGivenDay", QuestionnaireEntity.class)
                .setParameter("givenDay", toBeDeletedProduct.getDate(), TemporalType.DATE).getSingleResult();

        //then i remove them from the persistance context, cascade options will remove marketing question/answers, statistical answers, questionnaire responses
        em.remove(toBeDeletedProduct);
        em.remove(toBeDeletedQuestionnaire);


        return true;
    }

    //this method retrieves the list of users who completed (flag = false) or canceled (flag = true) the questionnaire for chosen day
    public List<UserEntity> retrieveQuestionnaireResponders (Date chosenDay, boolean flagCancelled) {
        List<UserEntity> uList = null;
        try {
            uList = em.createNamedQuery("UserEntity.getQuestionnaireTakers", UserEntity.class)
                    .setParameter("flag", flagCancelled)
                    .setParameter("givenDate", chosenDay)
                    .getResultList();
        }catch (PersistenceException ex) {
            ex.printStackTrace();
        }

        return uList;
    }


    //returns the answers of a given user for a certain questionnaire, if there is no user or no questionnaire for that day returns a null list
    public List<MarketingAnswerEntity> retrieveMarketingAnswers (String userName, Date chosenDay) throws PersistenceException{

        UserEntity user;
        List<MarketingAnswerEntity> mList = null;
        QuestionnaireEntity questionnaire;

        //if the questionnaire is not present for that day i return null list, else i get it and continue
        if(em.createNamedQuery("QuestionnaireEntity.questOfGivenDay", QuestionnaireEntity.class)
                .setParameter("givenDay", chosenDay).getResultList().isEmpty() ) {return mList;}
        else {
            questionnaire = em.createNamedQuery("QuestionnaireEntity.questOfGivenDay", QuestionnaireEntity.class)
                    .setParameter("givenDay", chosenDay).getSingleResult();
        }



        //if there is a user i retrieve it and do everything needed, else i return null list
        if(!em.createNamedQuery("UserEntity.getUserWithName", UserEntity.class)
                .setParameter("name", userName).getResultList().isEmpty()){

            user = em.createNamedQuery("UserEntity.getUserWithName", UserEntity.class)
                    .setParameter("name", userName).getResultList().get(0);

            //now i get the answers of that user for that questionnaire
            mList = em.createNamedQuery("MarketingAnswerEntity.retrieverUserAnswers", MarketingAnswerEntity.class)
                    .setParameter(1, user)
                    .setParameter(2, questionnaire).getResultList();

        }

        return mList;
    }

    //returns the answers of a given user for a certain questionnaire, if there is no user or no questionnaire for that day returns a null list
    public List<StatisticalAnswerEntity> retrieveStatisticalAnswers (String userName, Date chosenDay) {
        UserEntity user;
        List<StatisticalAnswerEntity> sList = null;
        QuestionnaireEntity questionnaire;

        //if the questionnaire is not present for that day i return null list, else i get it and continue
        if(em.createNamedQuery("QuestionnaireEntity.questOfGivenDay", QuestionnaireEntity.class)
                .setParameter("givenDay", chosenDay).getResultList().isEmpty() ) {return sList;}
        else {
            questionnaire = em.createNamedQuery("QuestionnaireEntity.questOfGivenDay", QuestionnaireEntity.class)
                    .setParameter("givenDay", chosenDay).getSingleResult();
        }


        //if there is a user i retrieve it and do everything needed, else i return null list
        if(!em.createNamedQuery("UserEntity.getUserWithName", UserEntity.class)
                .setParameter("name", userName).getResultList().isEmpty()){

            user = em.createNamedQuery("UserEntity.getUserWithName", UserEntity.class)
                    .setParameter("name", userName).getResultList().get(0);

            //now i get the statistical answers of that user for that questionnaire
            sList = em.createNamedQuery("StatisticalAnswerEntity.retrieverUserAnswers", StatisticalAnswerEntity.class)
                    .setParameter(1, user)
                    .setParameter(2, questionnaire).getResultList();

        }
        return sList;
    }

}
