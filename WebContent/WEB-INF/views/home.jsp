<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
    <title>Microblogs</title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/css/style.css" />" >
  </head>
  <body>
	<h1>Welcome to Microblogs</h1>
	
	<a href="<c:url value="/blogs" />">Microblogs</a>
	<a href="<c:url value="/blogger/register" />">Register</a>
  </body>
</html>