<%@ page import="it.polimi.db2.entities.UserEntity" %>
<%@ page import="it.polimi.db2.entities.ProductEntity" %><%--
  Created by IntelliJ IDEA.
  User: Simone Reale
  Date: 05/12/2020
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>

<h1>Home</h1>


<% Object user = session.getAttribute("user");
   String username = ((UserEntity) user).getUserName();
%>

<h3>Welcome: <%=username%>, let's start!</h3>


<h1>Product of the day: tbd</h1>




<form action="QuestionnaireServlet" method="get">
    <button type="submit"> Go to questionnaire </button>
</form>



<form action="LeaderBoardServlet" method="get">
    <button type="submit" >See leaderboard</button>
</form>





</body>
</html>