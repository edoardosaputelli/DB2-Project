<%@ page import="it.polimi.db2.entities.StatisticalQuestionEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Simone Reale
  Date: 10/12/2020
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stat Questionnaire</title>
</head>
<body>

<% String questionsTable = "<form action=\"StatisticalQuestionnaireServlet\" method=\"post\">";

    List<StatisticalQuestionEntity> questionEntityList = (List<StatisticalQuestionEntity>)request
            .getAttribute("statisticalQuestions");

    int i = 0;

    for(StatisticalQuestionEntity mq : questionEntityList){
        questionsTable = questionsTable +"\n" + mq.getQuestionText() +": "
                +"<input type=\"radio\""  +"name=" +"\"statQuestion" +i +"\"" +"> <br>";

        i++;
    }

    questionsTable = questionsTable +"\n" +"<input type=\"submit\" value=\"Submit\">" +"</form>";

%>

<%=questionsTable%>




</body>
</html>
