package it.polimi.db2.ejb;

import it.polimi.db2.entities.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.sql.Date;
import java.sql.Timestamp;
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

    //TBD
    public boolean addProduct (String productName, Date chosenDay, byte[] image) throws Exception {
        ProductEntity productOfTheDAy = new ProductEntity(productName, chosenDay, image);

        if (em.createNamedQuery("AdminEntity.checkIfDayIsFree", AdminEntity.class).setParameter(1, chosenDay).getResultList().isEmpty()) {
            return false;
        }
        
        try {
            em.persist(productOfTheDAy);
            em.flush();

        }catch (PersistenceException e) {
            e.printStackTrace();
            System.out.println("The product was not added");
        }

        return true;
    }

    public void addMarketingQuestion(String product, MarketingQuestionEntity question) {

    }

    //TBD, maybe as argument we could put date? dunno
    public void deleteQuestionnaireData (ProductEntity product) {}

    //TBD
    public List<UserEntity> retrieveSubmitters () {
        List<UserEntity> uList = null;

        return uList;
    }

    //TBD
    public List<UserEntity> retrieveCancelers () {
        List<UserEntity> uList = null;

        return uList;
    }

    //TBD
    public List<MarketingAnswerEntity> retrieveMarketingAnswers (int userId) {
        List<MarketingAnswerEntity> mList = null;
        return mList;
    }

    //TBD
    public List<StatisticalAnswerEntity> retrieveStatisticalAnswers (int userId) {
        List<StatisticalAnswerEntity> sList = null;
        return sList;
    }







}
