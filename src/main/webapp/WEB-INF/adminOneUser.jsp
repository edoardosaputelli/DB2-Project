<%@ page import="it.polimi.db2.entities.MarketingAnswerEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="it.polimi.db2.entities.StatisticalAnswerEntity" %>
<%@ page import="it.polimi.db2.entities.StatisticalQuestionEntity" %><%--
  Created by IntelliJ IDEA.
  User: Simone Reale
  Date: 17/12/2020
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>One User</title>
</head>
<body>

<%
    String stringListQuestAnsMarketing = "";
    String stringListQuestAnsStatistical = "";

    List<MarketingAnswerEntity> markAnswers = (List<MarketingAnswerEntity>) request.getAttribute("markAnswers");
    List<StatisticalAnswerEntity> statAnswers = (List<StatisticalAnswerEntity>) request.getAttribute("statAnswers");

    int i = 0;

    for(MarketingAnswerEntity m : markAnswers){

        stringListQuestAnsMarketing = stringListQuestAnsMarketing +"<br>" +i +") " +m.getmQuestion().getQuestionText()
               +"<br>" +m.getAnswerText();

        i++;

    }


    i = 0;


    if( ! statAnswers.isEmpty() ){

    for(StatisticalAnswerEntity s : statAnswers){

        stringListQuestAnsStatistical = stringListQuestAnsStatistical +"<br>" +i +") " +s.getQuestion().getQuestionText()
                +"<br>" +s.getAnswerText();

        i++;

    }

    }else stringListQuestAnsStatistical = "The user did not answer statistical question";

%>


List Marketing:
<%= stringListQuestAnsMarketing %>
<br>
<br>
List Statistical:
<%= stringListQuestAnsStatistical %>


</body>
</html>
