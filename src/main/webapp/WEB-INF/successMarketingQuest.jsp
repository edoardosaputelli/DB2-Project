<%--
  Created by IntelliJ IDEA.
  User: Simone Reale
  Date: 10/12/2020
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
</head>
<body>

<h1>Thank you!</h1>


<%-- Button to pass to the second part of the questionnaire --%>
<form action="StatisticalQuestionnaireServlet" method="get">
    <button type="submit" >Go to Statistical Questionnaire</button>
</form>


</body>
</html>
