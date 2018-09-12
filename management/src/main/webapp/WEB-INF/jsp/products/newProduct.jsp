<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
         <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
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
        <div class = "container">
            <c:if test="${not empty errors}" >
                <div class="alert alert-primary" role="alert">
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
            </c:if>

            <h3>Enter the product details</h3>
            <form:form action="/products" modelAttribute="product" method="POST">
                <div class = "form-group">
                    <form:label path="name">Name</form:label>
                    <form:input path="name" class="form-control"/>
                    <form:errors cssClass="alert-danger" path="name" />
                </div>
                <div class = "form-group">
                    <form:label path="description">Description</form:label>
                    <form:input path="description" class="form-control"/>
                    <form:errors cssClass="alert-danger" path="description" />
                </div>
                <div class = "form-group">
                    <form:label path="price">Price</form:label>
                    <form:input path="price" class="form-control"/>
                    <form:errors cssClass="alert-danger" path="price" />
                </div>
                <div class = "form-group">
                    <input class="btn btn-success" type="submit" value="Store"/>
                </div>
            </form:form>
        </div>
        <script src="/webjars/jquery/1.9.1/jquery.min.js"></script>
        <script src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>