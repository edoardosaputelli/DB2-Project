package it.polimi.db2.servlets;

import it.polimi.db2.Exceptions.DatabaseFailException;
import it.polimi.db2.Exceptions.NothingThatDateException;
import it.polimi.db2.ejb.AdminManager;
import it.polimi.db2.entities.ProductEntity;
import it.polimi.db2.entities.UserEntity;

import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "AdminOverallServlet", urlPatterns = "/AdminOverallServlet")
public class AdminOverallServlet extends HttpServlet {
    @EJB(name = "it.polimi.db2.ejb/AdminManager")
    private AdminManager adminManager;

    //this method gets a chosenDate parameter from session and sets as its attributes the users who completed/canceled
    //the questionnaire for that day
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //STRING NEEDS TO BE PARSED before arriving here!
        String dateString = request.getParameter("chosenDate");
        Date date = adminManager.fromStringToDate(dateString);
        ProductEntity productThatDay = null;
        List<UserEntity> onesWhoCancelledIt = new ArrayList<>();
        List<UserEntity> onesWhoCompletedIt = new ArrayList<>();

        try {

            productThatDay = adminManager.retrieveProductFromDay(date);

        } catch(NothingThatDateException ex) {

            //here it redirects back to the page where you insert the date for which you wanna know about
            request.getRequestDispatcher("WEB-INF/adminHome.jsp?errorString=noProductThatDay").forward(request, response);

        } catch(DatabaseFailException ex){

            //here it redirects to generic error page
            request.getRequestDispatcher("WEB-INF/redirectDatabaseError.jsp").forward(request, response);
        }

        try {

            onesWhoCompletedIt = adminManager.retrieveQuestionnaireResponders(date, false);
            onesWhoCancelledIt = adminManager.retrieveQuestionnaireResponders(date, true);

        } catch(DatabaseFailException ex){
            //here it redirects to generic error page
            request.getRequestDispatcher("WEB-INF/redirectDatabaseError.jsp").forward(request, response);
        }


        if(onesWhoCancelledIt.isEmpty() && onesWhoCompletedIt.isEmpty()){
            request.getRequestDispatcher("WEB-INF/adminHome.jsp?errorString=noFillingThatDay").forward(request, response);
        }

        //the lists could be empty because there could be not a single user who did/canceled the questionnaire
        //IN THAT CASE SIMPLY PRINT A MESSAGE EXPLAINING
        request.getSession().setAttribute("onesWhoCompletedIt", onesWhoCompletedIt);
        request.getSession().setAttribute("onesWhoCancelledIt", onesWhoCancelledIt);
        request.getSession().setAttribute("productThatDay", productThatDay);
        request.getSession().setAttribute("givenDate", date);

        request.getRequestDispatcher("WEB-INF/adminControlPanel.jsp").forward(request, response);

        //is there anything else i need to do here? i don't know actually

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }



}
