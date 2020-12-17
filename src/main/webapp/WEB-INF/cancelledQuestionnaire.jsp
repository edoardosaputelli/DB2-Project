<%--
  Created by IntelliJ IDEA.
  User: Simone Reale
  Date: 13/12/2020
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cancelled</title>
</head>
<body>

    <h1>You have cancelled the questionnaire.</h1>

    <%-- <form action="home.jsp">
        <button type="submit">Return to the Homepage</button>
    </form> --%>

    <form action="BrokerServlet" method="get">
        <input type="hidden" name="redirectedPage" value="home" />
        <button type="submit" > Return to the home page </button>
    </form>

</body>
</html>
