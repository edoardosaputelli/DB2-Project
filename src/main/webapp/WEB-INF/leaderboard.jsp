<%@ page import="java.util.List" %>
<%@ page import="it.polimi.db2.entities.UserEntity" %><%--
  Created by IntelliJ IDEA.
  User: Simone Reale
  Date: 13/12/2020
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LeaderBoard</title>
</head>
<body>

<%
    //printing users from the leaderboard on the basis of the attribute received
    List<UserEntity> resultList = (List<UserEntity>) request.getAttribute("leaderBoard");

    String stringLeaderboard = "<table style=\"width:50%\" border = \"5\">" +
            "  <tr>" +
            "    <th align = \"left\">Username</th>" +
            "    <th align = \"left\">Points</th>" +
            "  </tr>";


    for(int j = resultList.size() - 1; j >= 0; j--){

        stringLeaderboard = stringLeaderboard + "<tr><td>" +resultList.get(j).getUserName() +"</td>"
                +"<td>" +resultList.get(j).getPoints() +"</td>"
                +"</tr>";

    }

%>

<h1>LEADERBOARD</h1> <br>


<%=stringLeaderboard%>


<form action="RedirectHomeServlet" method="post">
    <input type="submit" value="Return to the home page">
</form>


<form action="LogOutServlet" method="get">
    <button type="submit" >Logout</button>
</form>



</body>
</html>
