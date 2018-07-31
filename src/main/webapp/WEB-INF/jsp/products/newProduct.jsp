<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class = "container">
            <h3>Enter the product details</h3>
            <form:form action="/products" modelAttribute="product" method="POST">
                <div class = "form-group">
                    <form:label path="name">Name</form:label>
                    <form:input path="name" class="form-control"/>
                    <form:errors path="name" />
                </div>
                <div class = "form-group">
                    <form:label path="description">Description</form:label>
                    <form:input path="description" class="form-control"/>
                    <form:errors path="description" />
                </div>
                <div class = "form-group">
                    <form:label path="price">Price</form:label>
                    <form:input path="price" class="form-control"/>
                    <form:errors path="price" />
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