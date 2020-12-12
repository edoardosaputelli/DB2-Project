package it.polimi.db2.ejb;

import it.polimi.db2.entities.UserEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Stateless
public class UserManager {

    @PersistenceContext(unitName = "projectPersistenceUnit")
    private EntityManager em;

    public UserManager() {
    }

    public UserEntity checkCredentials(String username, String password) throws Exception {

        List<UserEntity> uList = null;

        try {

            uList = em.createNamedQuery("UserEntity.checkLogin", UserEntity.class).setParameter(1, username).setParameter(2, password)
                    .getResultList();
        } catch (PersistenceException e) {

            e.printStackTrace();

            throw new Exception("Could not verify credentials");
        }
        if (uList.isEmpty())
            return null;

        else if (uList.size() == 1){

            Date date = new Date();

            Timestamp today = new Timestamp(date.getTime());

            UserEntity user = uList.get(0);

            user.setDateLastLogin(today);

            return user;}
        throw new NonUniqueResultException("More than one user registered with same credentials");

    }

    //TBD, don't even know if really needed
    public UserEntity registerUser(String username, String password, String email) throws Exception {

        //if the user is already present we return null
        if (checkCredentials(username, password) != null) {
            return null;
        }

        UserEntity user = new UserEntity(username, password, email);

        try {
            em.persist(user);
            em.flush();

        } catch(PersistenceException e) {
            e.printStackTrace();

            throw new Exception("Wasn't able to register new user");
        }

        return user;
    }



}