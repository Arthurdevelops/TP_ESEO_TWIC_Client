<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>Calcul distance entre 2 villes</title>
</head>
<body>
	<h1>Calcul de distance entre 2 villes</h1>
	<form action="villes" method="post">
		<label for="villeDepart">Ville de départ:</label> <select
			name="villeDepart" id="villeDepart">
			<c:forEach var="ville" items="${villes}">
				<option value="${ville.nomCommune}">${ville.nomCommune}</option>
			</c:forEach>
		</select> <br> <label for="villeArrivee">Ville d'arrivée:</label> <select
			name="villeArrivee" id="villeArrivee">
			<c:forEach var="ville" items="${villes}">
				<option value="${ville.nomCommune}">${ville.nomCommune}</option>
			</c:forEach>
		</select> <input type="submit" name="calculer" value="Calculer"> <br>
		<input type="submit" name="page2" value="Page 2">
	</form>

	<%
	String distance = request.getAttribute("distance").toString();
	String villeArrivee = (String) request.getAttribute("villeArrivee");
	String villeDepart = (String) request.getAttribute("villeDepart");
	
	if (distance != null) {
	%>
	<p>
		La distance entre <%=villeDepart%> et <%=villeArrivee%> est de
		<%=distance%>
		km.
	</p>
	<%
	}
	%>


</body>
</html>
