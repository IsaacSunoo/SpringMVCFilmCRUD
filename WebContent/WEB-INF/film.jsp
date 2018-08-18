<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${film.title}</title>
</head>
<body>

<c:choose>
    <c:when test="${! empty film}">
<table>
	<tr>
		<td><h2>ID</h2></td>
		<td><h2>Title</h2></td>
		<td><h2>Description</h2></td>
	</tr>
	<tr>
		<td>${film.id}</td>
		<td>${film.title}</td>
		<td>${film.description}</td>
	</tr>
</table>
<a href="updateFilm.html">Edit</a><br/>
<a href="deleteFilm.html">Delete</a>
   </c:when>
    <c:otherwise>
    No Film found
  </c:otherwise>
  </c:choose>

</body>
</html>