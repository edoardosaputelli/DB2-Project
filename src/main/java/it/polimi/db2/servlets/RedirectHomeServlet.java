package it.polimi.db2.servlets;

import it.polimi.db2.Exceptions.DatabaseFailException;
import it.polimi.db2.Exceptions.NothingThatDateException;
import it.polimi.db2.ejb.UserManager;
import it.polimi.db2.entities.ProductEntity;
import it.polimi.db2.entities.ReviewEntity;
import it.polimi.db2.entities.UserEntity;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RedirectHomeServlet", urlPatterns = {"/RedirectHomeServlet"})
public class RedirectHomeServlet extends HttpServlet {

    @EJB(name = "it.polimi.db2.ejb/UserManager")
    private UserManager userManager;



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //getting the product
        ProductEntity product = null;
        List<ReviewEntity> reviews = null;

        try{

            product = userManager.retrieveProductOfTheDay();

            if(product != null) { reviews = userManager.retrieveReviewsForProduct(product.getIdProduct()); }

        }catch (DatabaseFailException ex) {
            request.getRequestDispatcher("WEB-INF/redirectDatabaseError.jsp").forward(request, response);
        }catch (NothingThatDateException ex) {
            response.setContentType( "text/html" );
            request.getRequestDispatcher("WEB-INF/home.jsp?errorString=noProductOfTheDay").forward(request, response);
        }

        //pass image and productName to the page
        BufferedImage image = CheckLoginServlet.createImageFromBytes(product.getProductImage());
        request.setAttribute("listReviews", reviews);
        request.setAttribute("productName", product.getProductName());
        request.setAttribute("productImage", image);
        request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);


    }





    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
