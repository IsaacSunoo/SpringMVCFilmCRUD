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
    <c:when test="${! empty films}">
	<div class="container-fluid">
		<table>
			<tr>
				<td><h2>ID</h2></td>
				<td><h2>Title</h2></td>
				<td><h2>Description</h2></td>
				<td><h2>Release Year</h2></td>
				<td><h2>Rental Duration</h2></td>
				<td><h2>Rental Rate</h2></td>
				<td><h2>Length</h2></td>
				<td><h2>Replacement Cost</h2></td>
				<td><h2>Category</h2></td>
			</tr>
		<c:forEach var="film" items="${films}">
			<tr>
				<td>${film.id}</td>
				<td>${film.title}</td>
				<td>${film.description}</td>
				<td>${film.releaseYear}</td>
				<td>${film.rentDur}</td>
				<td>${film.rentRate}</td>
				<td>${film.length}</td>
				<td>${film.repCost}</td>
				<td>${film.categories}</td>
			</tr>
	<table>
		<tr>
			<td><h5>Actors:</h5></td>
		</tr>
			<c:forEach var="actor" items="${film.actors}">
		<tr>
			<td>${actor.firstName} ${actor.lastName}</td>
		</tr>
			</c:forEach>
	</table>
			</c:forEach>
		</table>

   </div> 
   <a href="updateFilm.html">Edit</a><br/>
<a href="deleteFilm.html">Delete</a><br>
	</c:when>
    <c:when test="${! empty film}">
<div class="container-fluid">
<table>
	<tr>
		<td><h2>ID</h2></td>
		<td><h2>Title</h2></td>
		<td><h2>Description</h2></td>
		<td><h2>Release Year</h2></td>
		<td><h2>Rental Duration</h2></td>
		<td><h2>Rental Rate</h2></td>
		<td><h2>Length</h2></td>
		<td><h2>Replacement Cost</h2></td>
		<td><h2>Category</h2></td>
	</tr>
	<tr>
		<td>${film.id}</td>
		<td>${film.title}</td>
		<td>${film.description}</td>
		<td>${film.releaseYear}</td>
		<td>${film.rentDur}</td>
		<td>${film.rentRate}</td>
		<td>${film.length}</td>
		<td>${film.repCost}</td>
		<td>${film.categories}</td>
	</tr>
</table>
</div>

<div class="container">

<h2>Actors: </h2>
	<table>
		<tr>
			<td><h2>ID</h2></td>
			<td><h2>First Name</h2></td>
			<td><h2>Last Name</h2></td>
		</tr>
			<c:forEach var="actor" items="${film.actors}">
		<tr>
			<td>${actor.id}</td>
			<td>${actor.firstName}</td>
			<td>${actor.lastName}</td>
		</tr>
			</c:forEach>
	</table>
</div>
<a href="updateFilm.html">Edit</a><br/>
<a href="deleteFilm.html">Delete</a><br>
   </c:when>
   
    <c:otherwise>
    No Film found<br>
  </c:otherwise>
  </c:choose>
  
<a href="index.html">Home</a>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>