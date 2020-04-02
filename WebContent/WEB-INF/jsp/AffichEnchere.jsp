<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="fr.eni.javaee.trocencheres.bo.Utilisateur"%>
<%@page import="fr.eni.javaee.trocencheres.bo.ArticleVendu"%>
<%@page import="fr.eni.javaee.trocencheres.bo.Categorie"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Détail vente</title>
</head>
<body>
	<jsp:include page="/WEB-INF/includes/header.jsp"></jsp:include>

	<jsp:useBean id="articleVendu" scope="request"
		class="fr.eni.javaee.trocencheres.bo.ArticleVendu"></jsp:useBean>
	<jsp:useBean id="categorie" scope="request"
		class="fr.eni.javaee.trocencheres.bo.Categorie"></jsp:useBean>
	<jsp:useBean id="utilisateur" scope="request"
		class="fr.eni.javaee.trocencheres.bo.Utilisateur"></jsp:useBean>

	<h1>Détail vente</h1>
	<form action="<%=request.getContextPath()%>/AffichEnchere" method="get">
		<h2>
			<c:out value="${ nomArticle}"></c:out>
		</h2>
		<h2>
			Description :
			<c:out value="${ description}"></c:out>
		</h2>
		<h2>
			Catégorie :
			<c:out value="${ categorie}"></c:out>
		</h2>
		<h2>
			Meilleur offre :
			<c:out value="${ prixVente}"> pts </c:out>

		</h2>
		<h2>
			Mise à prix :
			<c:out value="${ miseAPrix}"> points</c:out>

		</h2>
		<h2>
			Fin de l'enchère :
			<c:out value="${ dateFinEnchere}"></c:out>

		</h2>
		<h2>
			Retrait :
			<c:out value="${ rue}"></c:out>
			<c:out value="${ codePostal}"></c:out>
			<p>
				<c:out value="${ ville}"></c:out>
			</p>
		</h2>
		<h2>
			Vendeur :
			<c:out value="${ nomVendeur}"></c:out>
		</h2>

		<h2>
			<label for="prop osition">Ma proposition : </label> <input
				type="number" id="proposition" name="proposition">
		</h2>

		<div id="bouton">
			<input type="submit" name="btnEncherir" value="Enchérir" />
		</div>
	</form>

	<c:if test="${listeCodesErreur != null }">
		<p style="color: red;">Erreur, votre participation est échouée :</p>
		<c:forEach items="listeCodesErreur" var="codeErreur">
			<c:out value="${codeErreur }"></c:out>
		</c:forEach>
	</c:if>
	<c:if test="${(!empty enchere) && (listeCodesErreur == null)}">
		<p>
			Votre enchère est enregistrée avec succès avec le prix proposé :
			<c:out value="${prixVente }"></c:out>
			points.
		</p>
	</c:if>
</body>
</html>