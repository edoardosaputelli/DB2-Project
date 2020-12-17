<%--
  Created by IntelliJ IDEA.
  User: Simone Reale
  Date: 05/12/2020
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>

<h1>Sign UP!</h1>


<form action="CheckSignUpServlet" method="post">

    <input type="text" placeholder="email" name="email" required>   <br>
    <input type="text" placeholder="username" name="username" required> <br>
    <input type="password" placeholder="password" name="password" required> <br>
    <input type="submit" value="Register">

</form>

<%-- <a href="../index.jsp">Already have an account?</a> --%>

<form action="BrokerServlet" method="get">
    <input type="hidden" name="redirectedPage" value="index" />
    <button type="submit" > Already have an account? </button>
</form>


</body>
</html>
