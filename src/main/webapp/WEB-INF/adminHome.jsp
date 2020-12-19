<%--
  Created by IntelliJ IDEA.
  User: Simone Reale
  Date: 17/12/2020
  Time: 18:01
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
        String stringParameter = request.getParameter("errorString");

        if(stringParameter != null){

            //no one filled or cancelled the questionnaire for the chosen date
            if (stringParameter.equals("noOneFilled") )
            {

    %>
            <br/> <br/> <font color="red"> No user filled or cancelled the questionnaire for the chosen date </font> <br>
    <%
    }
            else if(stringParameter.equals("noProductThatDay")) {
    %>
            <br/> <br/> <font color="red"> There is no valid product for that day </font> <br>
    <%
            }
            else if(stringParameter.equals("newProductHasBeenAdded")) {
    %>
            <br/> <br/> <font color="green"> The product has been added for the chosen date. </font> <br>
    <%
            }
        }
    %>

</form>



<form action="BrokerServlet" method="get">
    <input type="hidden" name="redirectedPage" value="adminAddProduct" />
    <button type="submit" > Click here to add a product for a date </button>
</form>


</body>
</html>
