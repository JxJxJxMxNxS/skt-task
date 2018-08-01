<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-inverse">
          <div class="container-fluid">
            <div class="navbar-header">
              <a class="navbar-brand" href="/">Skytouch task</a>
            </div>
            <ul class="nav navbar-nav">
              <li><a href="/products">Product list</a></li>
              <li><a href="/view/product">Add product</a></li>
            </ul>
          </div>
        </nav>
        <div class="container">
            <h1>Error</h1>
            <ul>
                <c:forEach var="error" items="${errors}">
                    <li class="list-group-item">
                        <div class="alert alert-danger">
                          <strong><c:out value="${error}"/>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </body>
</html>