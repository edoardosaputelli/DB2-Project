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
    <title>Db2 Login</title>
</head>
<body>

<h1>Welcome to the Marketing Quiz!</h1>


<form action="CheckLoginServlet" method="post">

    <input type="text" placeholder="username" name="username" required> <br>
    <input type="password" placeholder="password" name="password" required> <br>
    <input type="submit" value="Login">

    <%
        String errorParameter = request.getParameter("errorString");

        if(errorParameter != null){

            //after login with wrong credentials
            if (errorParameter.equals("invalidUser") )
            {

    %>
                <br/> <br/> <font color="red">Error: the user doesn't exist.</font> <br>
    <%
            }

            //after a registration attempt with already-used credentials
            else if (errorParameter.equals("alreadyRegistered") )
            {

    %>
                <br/> <br/> <font color="red">Error: the user is already registered.</font> <br>
    <%
            }

            else if (errorParameter.equals("alreadyLoggedIn") )
            {

    %>
                <br/> <br/> <font color="red">Error: the user is already logged in.</font> <br>
    <%
    }

            //after the insertion of forbidden words
            else if (errorParameter.equals("bannedUser") )
            {

    %>
                <br/> <br/> <font color="red">Your account has been banned because you inserted forbidden words.</font> <br>
    <%
            }



        }
    %>

</form>


<a href="signUp.jsp">No account? Sign up!</a>


</body>
</html>
