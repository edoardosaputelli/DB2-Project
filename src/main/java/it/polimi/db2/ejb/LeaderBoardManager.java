package it.polimi.db2.ejb;

import it.polimi.db2.Exceptions.DatabaseFailException;
import it.polimi.db2.entities.UserEntity;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class LeaderBoardManager {
    @PersistenceContext(unitName = "projectPersistenceUnit")
    private EntityManager em;

    public LeaderBoardManager () {}

    //this method simply gets the list of users who completed today's questionnaire to be printed in the leaderboard
    public List<UserEntity> generateOrderedLeaderBoard() throws DatabaseFailException {

        List<UserEntity> leaderboard = null;

        try {

            Date today = Date.valueOf(LocalDate.now());

            //making the query so that flagCancelled is false and date is today
            leaderboard = em.createNamedQuery("UserEntity.getQuestionnaireTakers", UserEntity.class)
                    .setParameter("flag", false)
                    .setParameter("givenDate", today, TemporalType.DATE)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();

        }catch (PersistenceException ex){
            ex.printStackTrace();
            throw new DatabaseFailException();
        }

        return leaderboard;

    }
}
