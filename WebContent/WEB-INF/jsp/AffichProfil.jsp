<%@page import="fr.eni.javaee.trocencheres.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Détails de l'utilisateur</title>
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/4-col-portfolio.css" rel="stylesheet">
<link href="css/custom.css" rel="stylesheet">
</head>
<body>

<jsp:include page="/WEB-INF/includes/header.jsp"></jsp:include>

	<jsp:useBean id="utilisateur" scope="request" class="fr.eni.javaee.trocencheres.bo.Utilisateur"></jsp:useBean>
	
<div class="d-flex justify-content-center formulaire">
	<h2>Pseudo : <jsp:getProperty property="pseudo" name="utilisateur"/></h2>
</div>

<div class="d-flex justify-content-center formulaire">
	<h2>Nom : <jsp:getProperty property="nom" name="utilisateur"/></h2>
</div>	

<div class="d-flex justify-content-center formulaire">
	<h2>Prénom : <jsp:getProperty property="prenom" name="utilisateur"/></h2>
</div>

<div class="d-flex justify-content-center formulaire">
	<h2>Email  : <jsp:getProperty property="email" name="utilisateur"/></h2>
</div>	

<div class="d-flex justify-content-center formulaire">
	<h2>Téléphone : <jsp:getProperty property="telephone" name="utilisateur"/></h2>
</div>	

<div class="d-flex justify-content-center formulaire">
	<h2>Adresse : <jsp:getProperty property="rue" name="utilisateur"/></h2>
</div>	

<div class="d-flex justify-content-center formulaire">
	<h2>Code postal : <jsp:getProperty property="codePostal" name="utilisateur"/></h2>
</div>	

<div class="d-flex justify-content-center formulaire">
	<h2>Ville : <jsp:getProperty property="ville" name="utilisateur"/></h2>
</div>	

<div class="d-flex justify-content-center formulaire">
	<c:set var="u" value="${sessionScope.utilisateur}"/>
	<c:if test="${(u != null) && (u.pseudo == utilisateur.pseudo)}"><div><a class="btn btn-light"href="<%=request.getContextPath()%>/ModifProfil">Modifier</a></div></c:if>
</div>	

<div class="d-flex justify-content-center formulaire">
	<h4>
	
		<a class="btn btn-dark" href="<%=request.getContextPath()%>/">Retour à l'accueil</a>
	
	</h4>
</div>	

<!-- possible uniquement si user = session en cours -->

<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>


</body>
</html>