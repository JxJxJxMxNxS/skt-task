<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
                rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>Products</h1>
            <ul class="list-group">
              <c:forEach var="product" items="${products}">
                <li class="list-group-item"><c:out value="${product.name}"/></li>
              </c:forEach>
            </ul>
        </div>
        <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
        <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>