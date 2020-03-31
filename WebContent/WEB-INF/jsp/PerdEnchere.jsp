<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Enchere perdu</title>
</head>
<body>

	<h2>
		<c:out value="${enchere.noUtilisateur }"></c:out>
		a remporté l'enchère
	</h2>
	<p>
		<c:out value="${enchere.nomEnchere }"></c:out>
	</p>
	<p>
		Description:
		<c:out value="${enchere.description }"></c:out>
	</p>
	<p>
		Meilleure offre:
		<c:out value="${enchere.montantEnchere }"></c:out>
		pts par
		<c:out value="${enchere.noUtilisateur} "></c:out>
	</p>
	<p>
		Mise à prix:
		<c:out value="${articleVendu.miseAPrix }"></c:out>
	</p>
	<p>
		Fin de l'enchère:
		<c:out value="${enchere.dateFinEnchere }"></c:out>
	</p>
	<p>
		Retrait:
		<c:out value="${enchere.rue }"></c:out>
		<c:out value="${enchere.codePostal }"></c:out>
		<c:out value="${enchere.ville }"></c:out>
	</p>
	<p>
		Vendeur:
		<c:out value="${articleVendu.noUtilisateur }"></c:out>
	</p>

	<a href="<c:url value="accueil"/>">Back</a>
</body>
</html>