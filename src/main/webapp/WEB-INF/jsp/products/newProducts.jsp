<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
    </head>
    <body>
        <h3>Enter the product details</h3>
        <form:form action="/storeProduct" modelAttribute="product" method="POST">
             <table>
                <tr>
                    <td><form:label path="name">Name</form:label></td>
                    <td><form:input path="name"/></td>
                    <form:errors path="name" />
                </tr>
                <tr>
                    <td><form:label path="description">Description</form:label></td>
                    <td><form:input path="description"/></td>
                    <form:errors path="description" />
                </tr>
                <tr>
                    <td><form:label path="price">Price</form:label></td>
                    <td><form:input path="price"/></td>
                    <form:errors path="price" />
                </tr>
                <tr>
                    <td><input type="submit" value="Store"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>