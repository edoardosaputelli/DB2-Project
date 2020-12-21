<%@ page import="it.polimi.db2.entities.UserEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="it.polimi.db2.entities.ProductEntity" %><%--
  Created by IntelliJ IDEA.
  User: edoar
  Date: 17/12/2020
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Home</title>
</head>
<body>

<%
        String stringProductEntity;
        String onesWhoCompletedIt;
        String onesWhoCancelledIt;

        List<UserEntity> listOnesWhoCompletedIt = (List<UserEntity>) request.getSession().getAttribute("onesWhoCompletedIt");
        List<UserEntity> listOnesWhoCancelledIt = (List<UserEntity>) request.getSession().getAttribute("onesWhoCancelledIt");
        ProductEntity productThatDay = (ProductEntity) request.getSession().getAttribute("productThatDay");



        String tableHeader = "<table style=\"width:50%\" border = \"5\">" +
                "  <tr>" +
                "    <th align = \"left\">Username</th>" +
                "    <th align = \"left\">Points</th>" +
                "    <th align = \"left\">Date of last login</th>" +
                "  </tr>";


        onesWhoCompletedIt = tableHeader;
        onesWhoCancelledIt = tableHeader;

        stringProductEntity = productThatDay.getProductName();


      if(listOnesWhoCompletedIt != null || listOnesWhoCompletedIt.size() != 0) {

          for (UserEntity u : listOnesWhoCompletedIt) {

              onesWhoCompletedIt = onesWhoCompletedIt + "<tr><td>" + u.getUserName() + "</td>"
                      + "<td>" + u.getPoints() + "</td>"
                      + "<td>" + u.getDateLastLogin().toString() + "</td>"
                      + "</tr>";
          }

          onesWhoCompletedIt = onesWhoCompletedIt + "</table>";

      }

      else{

          onesWhoCompletedIt = "The list of the people who submitted the questionnaire is empty";

      }

      if(listOnesWhoCancelledIt != null || listOnesWhoCancelledIt.size() != 0) {

            for (UserEntity u : listOnesWhoCancelledIt) {

                onesWhoCancelledIt = onesWhoCancelledIt + "<tr><td>" + u.getUserName() + "</td>"
                        + "<td>" + u.getPoints() + "</td>"
                        + "<td>" + u.getDateLastLogin().toString() + "</td>"
                        + "</tr>";
            }

            onesWhoCancelledIt = onesWhoCancelledIt + "</table>";

        }

      else{

          onesWhoCancelledIt = "The list of the people who cancelled the questionnaire is empty";
      }

%>

Product:
<%= stringProductEntity %>
<br>
<br>
List of people who submitted the questionnaire
<%= onesWhoCompletedIt %>
<br>
<br>
List of people who cancelled the questionnaire
<%= onesWhoCancelledIt %>
<br>
<br>
Look for personal answers
<br>
<form action="AdminOneUserServlet" method="post">

    <input type="text" name="username">
    <input type="date" name="givenDate" hidden value="<%= request.getSession().getAttribute("givenDate")%>">
    <button type="submit">Search for this user</button>

    <%
        String errorParameter = request.getParameter("errorString");

        if(errorParameter != null){

            if (errorParameter.equals("noExistingUser") )
            {

    %>
            <br/> <br/> <font color="red">Error: the user doesn't exist or has not aswered the questionnaire.</font> <br>
    <%
            }
        }
    %>


</form>


<br>
<br>

<form action="BrokerServlet" method="get">
    <input type="hidden" name="redirectedPage" value="adminHome" />
    <button type="submit" > Back to Admin Home Page </button>
</form>


</body>
</html>
