<%--
  Created by IntelliJ IDEA.
  User: Simone Reale
  Date: 19/12/2020
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Admin Delete </title>
</head>
<body>

<h3> Insert a date to delete a product for that date </h3>

<form action="AdminDeleteServlet" method="post">
    <input type="date" name="chosenDate" required> <br>
    <input type="submit" value="Delete for this date"/>

    <%
        String errorParameter = request.getParameter("errorString");

        if(errorParameter != null){

            //it was inserted a not previous date
            if (errorParameter.equals("invalidDate") )
            {

    %>
            <br/> <br/> <font color="red"> You can only insert a previous date. </font> <br>
    <%
    }

    else if(errorParameter.equals("emptyDate")) {
    %>
            <br/> <br/> <font color="red"> The chosen date is not related to a product. </font> <br>
    <%
            }
        }
    %>

</form>

</body>
</html>
