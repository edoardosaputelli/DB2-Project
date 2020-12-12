<%@ page import="it.polimi.db2.entities.UserEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="it.polimi.db2.entities.MarketingQuestionEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %><%--
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

<h1>Questionnaire</h1>

<%

    String questionsTable = "error";

    //the user goes to the marketing questionnaire page for the first time: the page is initialized
    if(request.getSession().getAttribute("mapMarketingAnsQuest") == null) {

        questionsTable = "<form action=\"QuestionnaireServlet\" method=\"post\">";

        //getting the list of marketing questions from the Questionnaire Servlet
        List<MarketingQuestionEntity> questionEntityList = (List<MarketingQuestionEntity>) request.getAttribute("marketingQuestions");

        int i = 0;

        for (MarketingQuestionEntity mq : questionEntityList) {
            questionsTable = questionsTable + "\n" + mq.getQuestionText() + ": "
                    + "<input type=\"text\"" + "name=" + "\"question" + i + "\"" + "required> <br>";

            i++;
        }

        questionsTable = questionsTable + "\n" + "<input type=\"submit\" value=\"Submit\">" + "</form>";
   }

    //the user comes back to this page: his previous answer are loaded
    else{

        questionsTable = "<form action=\"QuestionnaireServlet\" method=\"post\">";

        List<MarketingQuestionEntity> questionEntityList = (List<MarketingQuestionEntity>) request.getAttribute("marketingQuestions");

        HashMap<Integer, String> mapMarkAnsQuest = (HashMap<Integer, String>) request.getSession().getAttribute("mapMarketingAnsQuest");

        int i = 0;

        for (MarketingQuestionEntity mq : questionEntityList) {
            questionsTable = questionsTable + "\n" + mq.getQuestionText() + ": "
                    + "<input type=\"text\"" +"value= " +"\"" +mapMarkAnsQuest.get(mq.getIdMarketingQuestion()) +"\""
                    +" name=" + "\"question" + i + "\"" + "required> <br>";

            i++;
        }



        questionsTable = questionsTable + "\n" + "<input type=\"submit\" value=\"Submit\">" + "</form>";





    }





%>

<%=questionsTable%>




</body>
</html>
