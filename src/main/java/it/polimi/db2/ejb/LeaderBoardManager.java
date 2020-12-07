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

    //TBD
    public List<UserEntity> generateOrderedLeaderBoard() {
        List<UserEntity> uList = null;


        return null;
    }
}
