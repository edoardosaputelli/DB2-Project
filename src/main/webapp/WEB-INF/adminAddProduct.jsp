<%--
  Created by IntelliJ IDEA.
  User: Simone Reale
  Date: 19/12/2020
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product</title>
</head>
<body>

<h3> Insert a date to add a product for that date </h3>

<form action="AdminAddProductServlet" method="post" enctype="multipart/form-data">
    <input type="date" name="chosenDate" required> <br>
    <input type="text" placeholder="Product Name" name="productName" required > <br>
    <input type="file"  placeholder="Only jpg file" id="file" accept=".jpg" name="image" required> <br>
    <input type="submit" value="Add for this date"/>

    <%
        String errorParameter = request.getParameter("errorString");

        if(errorParameter != null){

            //it was inserted a date already passed
            if (errorParameter.equals("invalidDate") )
            {

    %>
            <br/> <br/> <font color="red"> You can only insert a current or posterior date. </font> <br>
    <%
            }
            else if(errorParameter.equals("alreadyOccupiedDate")) {
    %>
            <br/> <br/> <font color="red"> The chosen date is already occupied by another product. </font> <br>
    <%
            }
        }
    %>

</form>

</body>
</html>
