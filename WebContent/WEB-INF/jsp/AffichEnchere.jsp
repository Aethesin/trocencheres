<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="fr.eni.javaee.trocencheres.bo.Utilisateur"%>
<%@page import="fr.eni.javaee.trocencheres.bo.Encheres"%>
<%@page import="fr.eni.javaee.trocencheres.bo.ArticleVendu"%>
<%@page import="fr.eni.javaee.trocencheres.bo.Categorie"%>

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

	<h2><jsp:getProperty property="nomArticleVendu"
			name="articleVendu" /></h2>
	<h2>
		Description :
		<jsp:getProperty property="description" name="articleVendu" /></h2>
	<h2>
		Catégorie :
		<jsp:getProperty property="libelle" name="categorie" /></h2>
	<h2>
		Meilleur offre :
		<jsp:getProperty property="prixVente" name="articleVendu" /></h2>
	<h2>
		Mise à prix :
		<jsp:getProperty property="miseAPrix" name="articleVendu" /></h2>
	<h2>
		Fin de l'enchère :
		<jsp:getProperty property="dateFinEncheres" name="articleVendu" /></h2>
	<h2>
		Retrait :
		<jsp:getProperty property="rue"	name="utilisateur" />
		<jsp:getProperty property="codePostal" name="utilisateur" />
		<jsp:getProperty property="ville" name="utilisateur" /></h2>
	<h2>
		Vendeur :
		<jsp:getProperty property="pseudo" name="utilisateur" /></h2>
		
	<form action="<%=request.getContextPath()%>/AffichEnchere" method="post">
		<div>
			<label for="proosition">Ma proposition : </label> <input
				type="number" id="proposition" name="proposition">
		</div>

		<div id="bouton">
			<input type="submit" name="btnEncherir" value="Enchérir" />
		</div>
	</form>
	
</body>
</html>