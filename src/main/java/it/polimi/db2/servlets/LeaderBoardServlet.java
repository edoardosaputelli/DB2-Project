package it.polimi.db2.servlets;

import it.polimi.db2.Exceptions.DatabaseFailException;
import it.polimi.db2.ejb.LeaderBoardManager;
import it.polimi.db2.entities.UserEntity;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LeaderBoardServlet", urlPatterns = {"/LeaderBoardServlet"})
public class LeaderBoardServlet extends HttpServlet {

    @EJB(name = "it.polimi.db2.ejb/LeaderBoardManager")
    private LeaderBoardManager leaderBoardManager;


    //this servlet simply recovers the list of users for the leaderboard
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<UserEntity> leaderBoard = null;

        try {
            leaderBoard = leaderBoardManager.generateOrderedLeaderBoard();
        }catch (DatabaseFailException ex) {
            request.getRequestDispatcher("WEB-INF/redirectDatabaseError.jsp").forward(request, response);
        }

        request.setAttribute("leaderBoard", leaderBoard);

        request.getRequestDispatcher("WEB-INF/leaderboard.jsp").forward(request, response);

    }
}
