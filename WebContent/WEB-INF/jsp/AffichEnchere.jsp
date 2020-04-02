<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="fr.eni.javaee.trocencheres.bo.Utilisateur"%>

<%@page import="fr.eni.javaee.trocencheres.bo.Enchere"%>
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

<div>
	<div class="text-center">
		<h1 class="formulaire">Détail vente</h1>
	</div>
	<div class="row">
		<div class="col-3"></div>
		<div class="col-9">
			<h2>
				<c:out value="${nomArticle}"></c:out>
			</h2>
			<div class="row h2-affichArt">
				<h2 class="col-2">
					Description :
				</h2>
				<div>
					<h2><c:out value="${description}"></c:out></h2>
				</div>
			</div>
			<div class="row h2-affichArt">
				<h2 class="col-2">
					Catégorie :
				</h2>
				<h2><c:out value="${libelle}"></c:out></h2>
			</div>
			<div class="row h2-affichArt">
				<h2 class="col-2">
					Meilleur offre :		
				</h2>
				<h2><c:out value="${ prixVente}"></c:out> points par <c:out value="${ pseudoEnchereur}"></c:out></h2>
			</div>
			<div class="row h2-affichArt">
				<h2 class="col-2">
					Mise à prix :
		
				</h2>
				<h2><c:out value="${ miseAPrix}"></c:out> points</h2>
			</div>
			<div class="row h2-affichArt">
				<h2 class="col-2">
					Fin de l'enchère :		
				</h2>
				<h2><c:out value="${ dateFinEnchere}"></c:out></h2>
			</div>
			<div class="row h2-affichArt">
				<h2 class="col-2">
					Retrait :
				</h2>
				<h2>
					<c:out value="${ rue}"></c:out>
					<c:out value="${ codePostal}"></c:out>
					<p>
						<c:out value="${ ville}"></c:out>
					</p>
				</h2>
			</div>
			<div class="row h2-affichArt">
				<h2 class="col-2">
					Vendeur :
				</h2>
				<h2><c:out value="${ nomVendeur}"></c:out></h2>
			</div>
			
			<form action="<%=request.getContextPath()%>/AffichEnchere?noArticle=${articleVendu.noArticleVendu}" method="post">
				<div class="row h2-affichArt">
					<h2 class="col-2">
						<label for="proposition">Ma proposition : </label> 
					</h2>
					<div class="col-1">
						<input class="form-control col-8" type="number" id="proposition" name="proposition">
					</div>
			
					<div id="bouton text-center">
						<input class="btn btn-primary" type="submit" name="btnEncherir" value="Enchérir" />
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

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