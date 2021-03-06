package it.polimi.db2.servlets;

import it.polimi.db2.Exceptions.DatabaseFailException;
import it.polimi.db2.Exceptions.NothingThatDateException;
import it.polimi.db2.ejb.AdminManager;

import javax.ejb.EJB;
import javax.persistence.NonUniqueResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminAddQuestionsServlet", urlPatterns = {"/AdminAddQuestionsServlet"})
public class AdminAddQuestionsServlet extends HttpServlet {
    @EJB(name = "it.polimi.db2.ejb/AdminManager")
    private AdminManager adminManager;

    //This servlet is called only after AdminAddProductServlet

    //This method takes from request a list of strings that will be transformed in questions for the product previously created.
    //The date should stay the same so that association is unique and no other parsing is needed.
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean didIt = false;

        Date date  = (Date) request.getSession().getAttribute("givenDate");

        List<String> strinqQuestions = new ArrayList<>();


        int i = 0;

        while(request.getParameter("question" +i) != null){

            strinqQuestions.add(request.getParameter("question" +i));
            i++;
        }



        try{

            didIt = adminManager.addMarketingQuestions(date, strinqQuestions);

        } catch(DatabaseFailException ex) {
            //add redirect to generic error page
            request.getRequestDispatcher("WEB-INF/redirectDatabaseError.jsp").forward(request, response);
        } catch (NothingThatDateException ex) {
            //shouldn't be called because of order of calling of Servlets
        }

        //the questions have been added for the chosen product: the admin is now redirected to
        //the adminHome, with a successful message
        if( didIt ){

            request.getRequestDispatcher("WEB-INF/adminHome.jsp?parameterString=newProductHasBeenAdded").forward(request, response);

        } else {
            //it shouldn't happen
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int numOfMarkQuest = 0;


        try {//da gestire l'errore di parse
            numOfMarkQuest  = Integer.parseInt(request.getParameter("numOfMarkQuest"));
        }catch (Exception ex){}



        String formInsertQuestions = "";

        formInsertQuestions = "<form action=\"AdminAddQuestionsServlet\" method=\"post\">";

        for(int i = 0; i < numOfMarkQuest; i++){

            formInsertQuestions = formInsertQuestions +"<br>"
                    + "<input type=\"text\"" + "name=" + "\"question" + i + "\"" + "required> <br>";

        }

        formInsertQuestions = formInsertQuestions + "<br>" + "<input type=\"submit\" value=\"Submit Questions\">" + "</form>";

        request.setAttribute("formInsertQuestions", formInsertQuestions);
        request.getRequestDispatcher("WEB-INF/adminQuestionsInsertion.jsp").forward(request, response);


    }

}
