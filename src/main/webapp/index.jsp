<%--
  Created by IntelliJ IDEA.
  User: Simone Reale
  Date: 01/12/2020
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Data Bases 2 Page Login </title>
</head>
<body>

<h1> Welcome to the Marketing Application! </h1>

<%-- Login form for users --%>
<form action="CheckLoginServlet" method="post">

    <input type="text" placeholder="username" name="username" required> <br>
    <input type="password" placeholder="password" name="password" required> <br>
    <input type="submit" value="Login">

    <%
        //in case of any error, the jsp receives through the url a parameter "errorString"
        //it is able to recognize the different types of error which could occur
        String errorParameter = request.getParameter("errorString");

        if(errorParameter != null){

            //login with wrong credentials
            if (errorParameter.equals("invalidUser") )
            {

    %>
                <br/> <br/> <font color="red"> Error: the user doesn't exist. </font> <br>
    <%
            }

            //login attempt with already-logged in account
            else if (errorParameter.equals("alreadyLoggedIn") )
            {

    %>
                <br/> <br/> <font color="red"> Error: the user is already logged in. </font> <br>
    <%
    }

            //login attempt with banned account, caused by the insertion of banned words
            else if (errorParameter.equals("bannedUser") )
            {

    %>
                <br/> <br/> <font color="red"> Your account has been banned. </font> <br>
    <%
            }

            //registration attempt with already-used credentials
            else if (errorParameter.equals("alreadyRegistered") )
            {

    %>
                <br/> <br/> <font color="red"> Error: the user is already registered. </font> <br>
    <%
            }

        }
    %>

</form>


<%-- signUp.jsp is in WEB-INF: it can't be accessed.
The requested is then managed by the BrokerServlet --%>
<form action="BrokerServlet" method="get">
    <input type="hidden" name="redirectedPage" value="signUp" />
    <button type="submit" > No account? Sign up! </button>
</form>

<%-- button for the adminLogin page --%>
<form action="BrokerServlet" method="get">
    <input type="hidden" name="redirectedPage" value="adminLogin" />
    <button type="submit" > Click here to login as an admin </button>
</form>

</body>
</html>
