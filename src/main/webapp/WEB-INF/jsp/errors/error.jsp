<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
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