package it.polimi.db2.servlets;

import it.polimi.db2.Exceptions.DatabaseFailException;
import it.polimi.db2.ejb.AdminManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet(name = "AdminAddProductServlet", urlPatterns = {"/AdminAddProductServlet"})
@MultipartConfig
public class AdminAddProductServlet extends HttpServlet {
    @EJB(name = "it.polimi.db2.ejb/AdminManager")
    private AdminManager adminManager;

    //this method takes from request a name, a date and an img to add a new product to the application
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String stringDate = (String) request.getParameter("chosenDate");
        String productName = (String) request.getParameter("productName");
        Part part = request.getPart("image");

        //didIt will become true if the product will be added successfully, false otherwise
        boolean didIt = false;

        byte [] img = generateFromImage(part);
        Date date = adminManager.fromStringToDate(stringDate);

        //check if the date inserted is a day already passed
        if(date.before(Date.valueOf(LocalDate.now()))) {

            request.getRequestDispatcher("WEB-INF/adminAddProduct.jsp?errorString=invalidDate").forward(request, response);

        } else {

            try {

                didIt = adminManager.addProduct(productName, date, img);

            } catch (DatabaseFailException ex) {
                request.getRequestDispatcher("WEB-INF/redirectDatabaseError.jsp").forward(request, response);
            }


            if (didIt) {

                request.getSession().setAttribute("givenDate", date);
                //redirect to a page that asks for the questions to be associated with the product (with AdminAddQuestionsServlet)
                request.getRequestDispatcher("WEB-INF/adminAddQuestions.jsp").forward(request, response);

            } else {
                request.getRequestDispatcher("WEB-INF/adminAddProduct.jsp?errorString=alreadyOccupiedDate").forward(request, response);
            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected byte[] generateFromImage(Part part) throws IOException {
        InputStream inps = null;


        inps = part.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[(int) part.getSize()];

        while ((nRead = inps.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        return buffer.toByteArray();
    }
}
