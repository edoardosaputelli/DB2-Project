package it.polimi.db2.servlets;

import it.polimi.db2.Exceptions.BannedUserException;
import it.polimi.db2.Exceptions.DatabaseFailException;
import it.polimi.db2.Exceptions.NothingThatDateException;
import it.polimi.db2.ejb.UserManager;
import it.polimi.db2.entities.ProductEntity;
import it.polimi.db2.entities.ReviewEntity;
import it.polimi.db2.entities.UserEntity;

import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.persistence.NonUniqueResultException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CheckLoginServlet", urlPatterns = {"/CheckLoginServlet"})

//this servlet is called in the form of users' login
public class CheckLoginServlet extends HttpServlet {

    @EJB(name = "it.polimi.db2.ejb/UserManager")
    private UserManager userManager;

    public CheckLoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = null;
        String password = null;

        try {
            username = request.getParameter("username");
            password = request.getParameter("password");

            if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
                throw new Exception("Missing or empty credential value");
            }

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing credential value");
            return;
        }


        UserEntity user = null;

        try {

            // query db for user authentication
            user = userManager.checkCredentials(username, password);

        } catch (DatabaseFailException e) {

            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not check credentials");
            return;

        } catch (BannedUserException ex) {
            response.setContentType( "text/html" );
            request.getRequestDispatcher("index.jsp?errorString=bannedUser").forward(request, response);
        }

        // If the user exists, add info to the session and go to home page, otherwise
        // show login page with error message

        String path;

        //the user doesn't exist
        if (user == null) {

            //the error is printed on the login page
            request.getSession().setAttribute("user", null);

            response.setContentType( "text/html" );
            request.getRequestDispatcher("index.jsp?errorString=invalidUser").forward(request, response);

        }

        //the user does exist
        else
        {
            //if the attribute "user" of the session is not null, the user is already logged in
            if(request.getSession().getAttribute("user") != null){

                response.setContentType( "text/html" );
                request.getRequestDispatcher("index.jsp?errorString=alreadyLoggedIn").forward(request, response);

            }


            //the user has been logged in: he is redirected to the home page
            else {
                //assigning the user to the session
                request.getSession().setAttribute("user", user);

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
                BufferedImage image = createImageFromBytes(product.getProductImage());
                request.setAttribute("listReviews", reviews);
                request.setAttribute("productName", product.getProductName());
                request.setAttribute("productImage", image);
                request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);


            }

        }

    }

    //method to generate the BufferedImage from the attribute of the product which is of type byte[]
    static protected BufferedImage createImageFromBytes (byte [] img) {
        BufferedImage image = null;
        ByteArrayInputStream inps = new ByteArrayInputStream(img);

        try {
            image = ImageIO.read(inps);
        }catch(IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return image;
    }


}
