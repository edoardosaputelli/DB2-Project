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

    List<UserEntity> resultList = (List<UserEntity>) request.getAttribute("leaderBoard");

    String stringLeaderboard = "<table style=\"width:50%\" border = \"5\">" +
            "  <tr>" +
            "    <th align = \"left\">Username</th>" +
            "    <th align = \"left\">Points</th>" +
            "    <th align = \"left\">Date of last login</th>" +
            "  </tr>";


    for(int j = resultList.size() - 1; j >= 0; j--){

        stringLeaderboard = stringLeaderboard + "<tr><td>" +resultList.get(j).getUserName() +"</td>"
                +"<td>" +resultList.get(j).getPoints() +"</td>"
                +"<td>" +resultList.get(j).getDateLastLogin().toString() +"</td>"
                +"</tr>";

    }

%>

<h1>LEADERBOARD</h1> <br>


<%=stringLeaderboard%>


<%-- <form action="WEB-INF/home.jsp">
    <button type="submit"> Return to the Homepage</button>
</form><br><br> --%>


<form action="BrokerServlet" method="get">
    <input type="hidden" name="redirectedPage" value="home" />
    <button type="submit" > Return to the home page </button>
</form>



</body>
</html>
