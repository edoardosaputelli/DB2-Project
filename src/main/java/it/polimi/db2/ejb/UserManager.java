package it.polimi.db2.ejb;

import it.polimi.db2.Exceptions.BannedUserException;
import it.polimi.db2.Exceptions.DatabaseFailException;
import it.polimi.db2.Exceptions.NothingThatDateException;
import it.polimi.db2.entities.ProductEntity;
import it.polimi.db2.entities.ReviewEntity;
import it.polimi.db2.entities.UserEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.*;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class UserManager {

    @PersistenceContext(unitName = "projectPersistenceUnit")
    private EntityManager em;

    public UserManager() {
    }

    public UserEntity checkCredentials(String username, String password) throws DatabaseFailException, BannedUserException {

        List<UserEntity> uList = null;

        try {

            uList = em.createNamedQuery("UserEntity.checkLogin", UserEntity.class).setParameter(1, username).setParameter(2, password)
                    .getResultList();
        } catch (PersistenceException e) {

            e.printStackTrace();

            throw new DatabaseFailException();
        }
        if (uList.isEmpty())
            return null;

        else if (uList.size() == 1){

            java.util.Date date = new java.util.Date();

            Timestamp today = new Timestamp(date.getTime());

            UserEntity user = uList.get(0);

            user.setDateLastLogin(today);

            if(user.getFlagStatus()!=00000000) throw new BannedUserException();

            return user;}
        throw new NonUniqueResultException("More than one user registered with same credentials");

    }

    //TBD, don't even know if really needed
    public UserEntity registerUser(String username, String password, String email) throws DatabaseFailException {

        try {
            //if the user is already present we return null
            if (checkCredentials(username, password) != null) {
                return null;
            }
        }catch (BannedUserException ex) {
            //not relevant here, ignore it
        }

        UserEntity user = new UserEntity(username, password, email);

        try {
            em.persist(user);
            em.flush();

        } catch(PersistenceException e) {
            e.printStackTrace();
            throw new DatabaseFailException();
        }

        return user;
    }

    public ProductEntity retrieveProductOfTheDay() throws DatabaseFailException, NothingThatDateException {
        ProductEntity product = null;
        Date today = Date.valueOf(LocalDate.now());
        if (em.createNamedQuery("ProductEntity.getProductOfGivenDay", ProductEntity.class).setParameter("givenDate", today).
                getResultList().isEmpty()) {
            throw new NothingThatDateException();
        }

        try{
            product = em.createNamedQuery("ProductEntity.getProductOfGivenDay", ProductEntity.class).
                    setParameter("givenDate", today).getResultList().get(0);
        }catch (PersistenceException ex) {
            ex.printStackTrace();
            throw new DatabaseFailException();
        }
        return product;
    }


    public List<ReviewEntity> retrieveReviewsForProduct(int idProduct) throws DatabaseFailException{

        List<ReviewEntity> reviews = new ArrayList<>();

        try {
            reviews = em.createNamedQuery("ReviewEntity.findReviewsProduct", ReviewEntity.class)
                    .setParameter(1, idProduct).getResultList();

        }catch(PersistenceException ex){
            ex.printStackTrace();
            throw new DatabaseFailException();
        }

        return reviews;
    }



}