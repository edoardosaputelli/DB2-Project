package it.polimi.db2.ejb;

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
    public List<UserEntity> generateOrderedLeaderBoard() {

        List<UserEntity> leaderboard = null;

        try {
            Date today = Date.valueOf(LocalDate.now());
            leaderboard = em.createNamedQuery("UserEntity.getLeaderboard", UserEntity.class)
                    .setParameter("today", today, TemporalType.DATE)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();

        }catch (PersistenceException ex){
            //TBD
            ex.printStackTrace();
        }


        return leaderboard;
    }
}
