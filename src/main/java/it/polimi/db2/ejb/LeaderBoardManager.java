package it.polimi.db2.ejb;

import it.polimi.db2.entities.UserEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
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

            leaderboard = em.createNamedQuery("UserEntity.getLeaderboard", UserEntity.class)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH")
                    .getResultList();

        }catch (PersistenceException ex){
            //TBD
            ex.printStackTrace();
        }


        return leaderboard;
    }
}
