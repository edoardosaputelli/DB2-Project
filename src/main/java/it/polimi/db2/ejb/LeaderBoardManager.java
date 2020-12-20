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

    //edoooooo da gestire le eccezioni
    public List<UserEntity> generateOrderedLeaderBoard() throws DatabaseFailException {

        List<UserEntity> leaderboard = null;

        try {
            Date today = Date.valueOf(LocalDate.now());
            //i make the query so that flag is false and date is today
            leaderboard = em.createNamedQuery("UserEntity.getQuestionnaireTakers", UserEntity.class)
                    .setParameter("flag", false)
                    .setParameter("givenDate", today, TemporalType.DATE)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();

        }catch (PersistenceException ex){
            //TBD
            ex.printStackTrace();
            throw new DatabaseFailException();
        }


        return leaderboard;
    }
}
