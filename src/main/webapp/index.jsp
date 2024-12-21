<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <title>SuperRepo | Main</title>
</head>
<body>
<div style="text-align: center">
  <h1><%= "Hello World with Hibernate!!!" %></h1>
  <br/>
  <h2><a href="patients">Show All Patients</a></h2>
  <br/>
  <jsp:useBean id="now" class="java.util.Date" />
  <h3><fmt:formatDate value="${now}" type="both" dateStyle="long" timeStyle="long" /></h3>
</div>
</body>
</html>