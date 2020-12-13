<%@ page import="it.polimi.db2.entities.MarketingQuestionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: Simone Reale
  Date: 11/12/2020
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
</head>
<body>

<%
    String mapMark = request.getSession().getAttribute("mapMarketingAnsQuest").toString();
    String mapStat = request.getSession().getAttribute("mapStatAnsQuest").toString();

%>


<h1><%=mapMark%></h1>

<h2><%=mapStat%></h2>











</body>
</html>
