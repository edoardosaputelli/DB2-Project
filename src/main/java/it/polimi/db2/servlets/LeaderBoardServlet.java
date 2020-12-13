package it.polimi.db2.servlets;

import it.polimi.db2.ejb.LeaderBoardManager;
import it.polimi.db2.ejb.UserManager;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<UserEntity> leaderBoard = leaderBoardManager.generateOrderedLeaderBoard();

        request.setAttribute("leaderBoard", leaderBoard);

        request.getRequestDispatcher("leaderboard.jsp").forward(request, response);





    }
}
