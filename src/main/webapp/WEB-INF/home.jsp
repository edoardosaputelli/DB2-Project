<%@ page import="it.polimi.db2.entities.UserEntity" %>
<%@ page import="it.polimi.db2.entities.ProductEntity" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="java.util.Base64" %>
<%@ page import="it.polimi.db2.entities.ReviewEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Simone Reale
  Date: 05/12/2020
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>

<h1>Home</h1>


<%
    //retrieving information about the current user (from the session)
    //and the product of the day (from the attributes)

    Object user = session.getAttribute("user");

    String username = ((UserEntity) user).getUserName();
    String productName = (String) request.getAttribute("productName");
    BufferedImage image = (BufferedImage) request.getAttribute("productImage");

   //code to render image in base64 string and show it on the html page
    if (image != null) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageIO.write( image, "jpg", baos );
    baos.flush();
    byte[] imageInByteArray = baos.toByteArray();
    baos.close();
    String b64 = Base64.getEncoder().encodeToString(imageInByteArray);

%>

<%
    //retrieving reviews about the product of the day
    List<ReviewEntity> reviews = (List<ReviewEntity>) request.getAttribute("listReviews");

    String stringTableReview = "No reviews for this product";

    //printing reviews
    if(reviews != null && (!reviews.isEmpty())) {

        stringTableReview = "<table style=\"width:50%\" border = \"5\">" +
                "  <tr>" +
                "    <th align = \"left\">Reviews</th>" +
                "  </tr>";


        for (ReviewEntity r : reviews) {

            stringTableReview = stringTableReview + "<tr><td>" + r.getReviewText() + "</td>"
                    + "</tr>";

        }

        stringTableReview = stringTableReview +"</table>";

    }

%>






<h3> Welcome: <%=username%>, let's start! </h3>


<h1> Product of the day: <%=productName%> </h1>


<div>
    <img src="data:image/jpg;base64, <%=b64%>" width="30%" height="30%" alt="Image of the product not found" />
</div>

<br>
<br>

<%=stringTableReview%>

<br>
<br>

<%-- Button to the marketing question page --%>
<form action="QuestionnaireServlet" method="get">
    <button type="submit"> Go to questionnaire </button>
</form>

<%-- Button to the product of the day's leaderboard page --%>
<form action="LeaderBoardServlet" method="get">
    <button type="submit" >See leaderboard</button>
</form>


<% } else { %>

<%
    //in case of errors, the jsp page receives the type of error through the url
    String errorParameter = request.getParameter("errorString");

    if(errorParameter != null){

        //no product of the day is retrieved for the current date
        if (errorParameter.equals("noProductOfTheDay") )
        {

%>
            <font color="red"> We are sorry, but there is no product for today :( </font> <br> <br>
<%
        }

    //if a user already completed the questionnaire, he can't submit it again
    } else {
%>

        <h4> You have already filled the questionnaire </h4>

<% } %>

<% } %>

<%-- Button to logout --%>
<form action="LogOutServlet" method="get">
    <button type="submit" >Logout</button>
</form>


</body>
</html>
