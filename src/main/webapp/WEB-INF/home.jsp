<%@ page import="it.polimi.db2.entities.UserEntity" %>
<%@ page import="it.polimi.db2.entities.ProductEntity" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="java.util.Base64" %><%--
  Created by IntelliJ IDEA.
  User: Simone Reale
  Date: 05/12/2020
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>

<h1>Home</h1>


<% Object user = session.getAttribute("user");

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

<h3>Welcome: <%=username%>, let's start!</h3>


<h1>Product of the day: <%=productName%></h1>


<div>
    <img src="data:image/jpg;base64, <%=b64%>" width="30%" height="30%" alt="Image of the product not found" />
</div>

<br>
<br>
<form action="QuestionnaireServlet" method="get">
    <button type="submit"> Go to questionnaire </button>





</form>

<form action="LeaderBoardServlet" method="get">
    <button type="submit" >See leaderboard</button>
</form>



<% } else { %>
<%
    String errorParameter = request.getParameter("errorString");

    if(errorParameter != null){

        //after login with wrong credentials
        if (errorParameter.equals("noProductOfTheDay") )
        {

%>
<br/> <br/> <font color="red"> We are sorry, but there is no product for today :( </font> <br>
<%
        }
    } else {
%>
        <h4>I'm sorry you already completed the questtionnaire</h4>

<form action="LeaderBoardServlet" method="get">
    <button type="submit" >See leaderboard</button>
</form>
<% } %>

<% } %>




</body>
</html>
