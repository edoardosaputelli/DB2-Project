<%--
  Created by IntelliJ IDEA.
  User: Edoardo Saputelli
  Date: 15/12/2020
  Time: 09:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Persistence exception - Database error </title>
</head>
<body>

    <h1> You encountered an error with the database: </h1>

    <h2> <br/> Try again later. </h2>

    <form action="BrokerServlet" method="get">
        <input type="hidden" name="redirectedPage" value="index" />
        <button type="submit" > Go to the login page </button>
    </form>

</body>
</html>
