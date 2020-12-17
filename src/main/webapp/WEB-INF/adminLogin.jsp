<%--
  Created by IntelliJ IDEA.
  User: edoar
  Date: 17/12/2020
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Admin Login </title>
</head>
<body>

    <h3> Admin Login </h3>

    <form action="AdminLoginServlet" method="post">

        <input type="text" placeholder="username" name="username" required> <br>
        <input type="password" placeholder="password" name="password" required> <br>
        <input type="submit" value="Login">

    <%
        String errorParameter = request.getParameter("errorString");

        if(errorParameter != null){

            //after login with wrong credentials
            if (errorParameter.equals("invalidAdmin") )
            {

    %>
        <br/> <br/> <font color="red"> Invalid admin </font> <br>
    <%
            } else if (errorParameter.equals("alreadyLoggedIn")) {

    %>

        <br/> <br/> <font color="red"> Admin already logged in </font> <br>

    <%

            }
        }
    %>



    </form>

    <br/>

    <form action="BrokerServlet" method="get">
        <input type="hidden" name="redirectedPage" value="index" />
        <button type="submit" > Back to user login </button>
    </form>

</body>
</html>
