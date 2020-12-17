<%--
  Created by IntelliJ IDEA.
  User: Edoardo Saputelli
  Date: 15/12/2020
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Overall</title>
</head>
<body>

    <h3> Insert a date to see the users who filled the questionnaire for that date. </h3>

    <form action="AdminOverallServlet" method="post">
        <input type="date" name="chosenDate" required> <br>
        <input type="submit" value="Search for this date"/>
    </form>

</body>
</html>
