package it.polimi.db2.servlets;

import it.polimi.db2.Exceptions.DatabaseFailException;
import it.polimi.db2.Exceptions.NothingThatDateException;
import it.polimi.db2.ejb.AdminManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "AdminAddQuestionsServlet")
public class AdminAddQuestionsServlet extends HttpServlet {
    @EJB(name = "it.polimi.db2.ejb/AdminManager")
    private AdminManager adminManager;

    //THIS SERVLET SHOULD BE CALLED ONLY AFTER ADDPRODUCTSERVLET
    //this method takes from request a list of strings that will be transformed in questions for the product previously created
    //the date should stay the same so that association is unique and no other parsing is needed
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stringDate = (String) request.getAttribute("chosenDay");
        Date date = adminManager.fromStringToDate(stringDate);


        List<String> strinqQuestions = (List<String>) request.getAttribute("stringQuestions");

        try{
            adminManager.addMarketingQuestions(date, strinqQuestions);

        }catch(DatabaseFailException e) {
            //add redirect to generic error page
        }catch (NothingThatDateException ex) {
            //shouldn't be called because of order of calling of Servlets
        }

        //TBD redirect to a page that shows a success message and sends back to the Admin home



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
