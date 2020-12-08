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

</form>


<a href="signUp.jsp">No account? Sign up!</a>


</body>
</html>
