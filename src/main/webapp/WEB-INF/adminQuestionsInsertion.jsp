<%--
  Created by IntelliJ IDEA.
  User: Simone Reale
  Date: 19/12/2020
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert Question</title>
</head>
<body>

<%

    Integer numOfMarkQuest = 0;
    String formInsertQuestions = "<form action=\"AdminAddQuestionsServlet\" method=\"post\">";

    if(request.getAttribute("numOfMarkQuest") != null) {

        numOfMarkQuest = (Integer) request.getAttribute("numOfMarkQuest");

    }


    for(int i = 0; i < numOfMarkQuest; i++){

        formInsertQuestions = formInsertQuestions +"<br>"
                + "<input type=\"text\"" + "name=" + "\"question" + i + "\"" + "required> <br>";

    }


    formInsertQuestions = formInsertQuestions + "<br>" + "<input type=\"submit\" value=\"Submit Questions\">" + "</form>";


%>








</body>
</html>
