<%@ page import="it.polimi.db2.entities.StatisticalQuestionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="it.polimi.db2.entities.StatQuestionAlternativesEntity" %>
<%@ page import="java.util.HashMap" %><%--
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

<%
    String questionsTable;

    questionsTable = "<form action=\"StatisticalQuestionnaireServlet\" method=\"post\">";

    HashMap< StatisticalQuestionEntity, List<StatQuestionAlternativesEntity> > questionEntityList =
            (HashMap< StatisticalQuestionEntity, List<StatQuestionAlternativesEntity> > )
            request.getAttribute("statisticalQuestions");


    for(StatisticalQuestionEntity sqe : questionEntityList.keySet()){

        questionsTable = questionsTable +sqe.getQuestionText() +"<br>";

        for(StatQuestionAlternativesEntity sqa : questionEntityList.get(sqe)) {

            questionsTable = questionsTable + "<br>" + sqa.getAlternativeText() + ": "
                    + "<input type=\"radio\"" +" id = " +"\"" +sqa.getAlternativeText() +"\""
                    +"value=" +"\"" +sqa.getAlternativeText() +"\"" +"name=" +"\"" +sqe.getQuestionText() +"\" " + "> <br>";


        }

        questionsTable = questionsTable +"<br>";


    }


    questionsTable = questionsTable + "\n" + "<input type=\"submit\" value=\"Submit\">" + "</form>";

%>

<%=questionsTable%>


<form action="QuestionnaireServlet" method="get">

    <input type="submit" value="Go Back to Marketing Questionnaire">

</form>


<form action="CancelQuestionnaireServlet" method="post">

    <input type="submit" value="Cancel Questionnaire">

</form>



</body>
</html>
