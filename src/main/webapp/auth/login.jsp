<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>SuperEdu | Login</title>
</head>
<body>
<div style="text-align: center">
    <h1><%= "Login to your account" %></h1>
    <br/>
    <br/>
    <jsp:useBean id="now" class="java.util.Date" />
    <h3><fmt:formatDate value="${now}" type="both" dateStyle="long" timeStyle="long" /></h3>
</div>
</body>
</html>