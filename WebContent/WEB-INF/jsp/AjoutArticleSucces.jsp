<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="fr.eni.javaee.trocencheres.messages.LecteurMessage"%>
<%@page import="fr.eni.javaee.trocencheres.bo.ArticleVendu"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Succes</title>
</head>

<body>
	<c:if test="${articleVendu != null }">
		<p>L'article a été ajouté avec succès:</p>
		<p>
			Nom de l'article:
			<c:out value="${nomArticleVendu }"></c:out>
		</p>
		<p>
			Description:
			<c:out value="${description }"></c:out>
		</p>
		<p>
			Date de la mise aux enchères:
			<c:out value="${dateDebutEncheres }"></c:out>
		</p>
		<p>
			Date de la fin des enchères:
			<c:out value="${dateFinEncheres }"></c:out>
		</p>
		<p>
			Prix proposé:
			<c:out value="${miseAPrix }"></c:out>
		</p>
		<p>
			Prix de vente:
			<c:out value="${prixVente }"></c:out>
		</p>
		<p>
			Catégorie:
			<c:out value="${noCategorie }"></c:out>
		</p>

	</c:if>

	<h4>
		<a href="<%=request.getContextPath()%>/">Retour à l'accueil</a>
	</h4>
</body>
</html>