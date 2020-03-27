<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>S'inscrire</title>
</head>
<body>

<c:if test="${!empty listeCodesErreurString}">
	<c:forEach var="l" items="${listeCodesErreurString}">
		<p>${l}</p>
	</c:forEach>
</c:if>

	<form action="<%=request.getContextPath()%>/Register" method="post">
		<label>Pseudo : </label>
		<input type="text" name="pseudo" value="${utilisateur.pseudo}"><br>
		<label>Nom : </label>
		<input type="text" name="nom" value="${utilisateur.nom}"><br>
		<label>Prenom : </label>
		<input type="text" name="prenom" value="${utilisateur.prenom}"><br>
		<label>Email : </label>
		<input type="text" name="email" value="${utilisateur.email}"><br>
		<label>Telephone : </label>
		<input type="text" name="telephone" value="${utilisateur.telephone}"><br>
		<label>Rue : </label>
		<input type="text" name="rue" value="${utilisateur.rue}"><br>
		<label>Code postal : </label>
		<input type="text" name="code_postal" value="${utilisateur.codePostal}"><br>
		<label>Ville : </label>
		<input type="text" name="ville" value="${utilisateur.ville}"><br>
		<label>Mot de passe : </label>
		<input type="password" name="motDePasse"><br>
		<label>Vérifier mot de passe : </label>
		<input type="password" name="motDePasseVerif"><br>
		<input type="submit" value="Valider">
	</form>
	
<h4>

	<a href="<%=request.getContextPath()%>/">Retour à l'accueil</a>

</h4>
</body>
</html>