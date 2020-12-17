<%@ page import="it.polimi.db2.entities.UserEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="it.polimi.db2.entities.ProductEntity" %><%--
  Created by IntelliJ IDEA.
  User: edoar
  Date: 17/12/2020
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Home</title>
</head>
<body>

    <h1> Welcome, admin </h1>

    <h3> Insert a date to see the users who filled the questionnaire for that date. </h3>

    <form action="AdminOverallServlet" method="post">
        <input type="date" name="chosenDate" required> <br>
        <input type="submit" value="Search for this date"/>

        <%
            String errorParameter = request.getParameter("errorString");

            if(errorParameter != null){

                //no one filled or cancelled the questionnaire for the chosen date
                if (errorParameter.equals("noOneFilled") )
                {

        %>
                <br/> <br/> <font color="red"> No user filled or cancelled the questionnaire for the chosen date </font> <br>
        <%
                }
                else if(errorParameter.equals("noProductThatDay")) {
        %>
                <br/> <br/> <font color="red"> There is no valid product for that day </font> <br>
        <%
                }
            }
        %>

    </form>

    <%
        String productEntity = "";
        String onesWhoCompletedIt = "";
        String onesWhoCancelledIt = "";

        List<UserEntity> listOnesWhoCompletedIt = (List<UserEntity>) request.getAttribute("onesWhoCompletedIt");
        List<UserEntity> listOnesWhoCancelledIt = (List<UserEntity>) request.getAttribute("onesWhoCancelledIt");
        ProductEntity productThatDay = (ProductEntity) request.getAttribute("productThatDay");

        for(UserEntity u : listOnesWhoCompletedIt){




        }


    %>

</body>
</html>
