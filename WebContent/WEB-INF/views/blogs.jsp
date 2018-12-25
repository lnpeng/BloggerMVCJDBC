<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
  <head>
    <title>Microblogs</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />" >
  </head>
  <body>
	<div class="blogForm">
	  <h1>What would make today great?</h1>
	  <form method="POST" name="blogForm">
	    <input type="hidden" name="latitude">
	    <input type="hidden" name="longitude">
	    <textarea name="message" cols="80" rows="5"></textarea><br/>
	    <input type="submit" value="Add" />
	    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	  </form>
	</div>
	<div class="listTitle">
	      <h1>Recent Microblogs</h1>
	      <ul class="blogList">
	        <c:forEach items="${blogList}" var="blog" >
	          <li id="blog_<c:out value="blog.id"/>">
	            <div class="blogMessage"><c:out value="${blog.message}" /></div>
	            <div>
	              <span class="blogTime"><c:out value="${blog.createTime}" /></span>
	            </div>
	          </li>
	        </c:forEach>
	      </ul>
	      <c:if test="${fn:length(blogList) gt 20}">
	        <hr />
	        <s:url value="/blogs?count=${nextCount}" var="more_url" />
	        <a href="${more_url}">Show more</a>
	      </c:if>
	 </div>
 </body>
</html>