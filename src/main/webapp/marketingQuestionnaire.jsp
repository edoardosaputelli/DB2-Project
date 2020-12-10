<%@ page import="it.polimi.db2.entities.UserEntity" %><%--
  Created by IntelliJ IDEA.
  User: Simone Reale
  Date: 10/12/2020
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Questionnaire</title>
</head>
<body>


<% Object user = session.getAttribute("user");
    String username = ((UserEntity) user).toString();

%>

<h3><%=username%></h3>



</body>
</html>
