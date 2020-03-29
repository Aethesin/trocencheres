<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Participation réussie</title>
</head>
<body>
	<p>
		Votre enchère est enregistrée avec succès avec le prix proposé : 
		<c:out value="${prixVente }"></c:out> points.
	</p>

	<h4>
		<a href="<%=request.getContextPath()%>/">Retour à l'accueil</a>
	</h4>

</body>

</html>