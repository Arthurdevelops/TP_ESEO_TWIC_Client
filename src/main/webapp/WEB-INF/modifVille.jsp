<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Liste des villes</title>
</head>
<body>
	<h1>Liste des villes</h1>
	<table>
		<thead>
			<tr>
				<th>Nom commune</th>
				<th>Code postal</th>
				<th>Latitude</th>
				<th>Longitude</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="ville" items="${villes}">
				<tr>
					<td>${ville.nomCommune}</td>
					<td>${ville.codePostale}</td>
					<td>${ville.latitude}</td>
					<td>${ville.longitude}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="3">
					<nav>
						<ul>
							<li><a href="?page=1">1</a></li>
							<li><a href="?page=2">2</a></li>
							<li><a href="?page=3">3</a></li>
						</ul>
					</nav>
				</td>
			</tr>
		</tfoot>

	</table>

	<form action="villes" method="post">
		<input type="submit" name="page1" value="Page 1">
	</form>
</body>
</html>