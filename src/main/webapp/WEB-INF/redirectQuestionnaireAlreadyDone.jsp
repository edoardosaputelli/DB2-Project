<%@ page import="it.polimi.db2.entities.UserEntity" %><%--
  Created by IntelliJ IDEA.
  User: Simone Reale
  Date: 13/12/2020
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Fail</title>
</head>
<body>

    <h1> You have already filled the questionnaire. </h1>

    <%
        Object user = session.getAttribute("user");
        String username = ((UserEntity) user).getUserName();
        String password = ((UserEntity) user).getUserPassword();
        request.getSession().setAttribute("user", null);
    %>


    <form action="CheckLoginServlet" method="post">
        <input type="hidden" name="username" value = "<%=username%>" > <br>
        <input type="hidden" name="password" value = "<%=password%>"> <br>
        <input type="submit" value="Return to the home page">
    </form>

</body>
</html>
