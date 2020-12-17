<%@ page import="it.polimi.db2.entities.MarketingQuestionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: Simone Reale
  Date: 11/12/2020
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
</head>
<body>

<h1>Thank you by the db2 team!</h1>


<%-- <form action="home.jsp">
    <button type="submit">Return to the Homepage</button>
</form> --%>

<form action="BrokerServlet" method="get">
    <input type="hidden" name="redirectedPage" value="home" />
    <button type="submit" > Return to the home page </button>
</form>



</body>
</html>
