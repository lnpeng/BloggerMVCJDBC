<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
    <title>Microblogs</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />" >
  </head>
  <body>
	<h1>Your Profile</h1>
	<c:out value="${blogger.username}" /><br/>
	<c:out value="${blogger.firstName}" /> <c:out value="${blogger.lastName}" /><br/>
	<c:out value="${blogger.email}" />
 </body>
</html>