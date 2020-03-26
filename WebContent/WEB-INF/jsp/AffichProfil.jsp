<%@page import="fr.eni.javaee.trocencheres.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Détails de l'utilisateur</title>
</head>
<body>

<jsp:include page="/WEB-INF/includes/headerDéconnecté.jsp"></jsp:include>

<h1>Détail du Profil</h1>

<jsp:useBean id="utilisateur" scope="request" class="fr.eni.javaee.trocencheres.bo.Utilisateur"></jsp:useBean>

<h2>Pseudo : <jsp:getProperty property="pseudo" name="utilisateur"/></h2>

<h2>Nom : <jsp:getProperty property="nom" name="utilisateur"/></h2>

<h2>Prénom : <jsp:getProperty property="prenom" name="utilisateur"/></h2>

<h2>Email  : <jsp:getProperty property="email" name="utilisateur"/></h2>

<h2>Téléphone : <jsp:getProperty property="telephone" name="utilisateur"/></h2>

<h2>Adresse : <jsp:getProperty property="rue" name="utilisateur"/></h2>

<h2>Code postal : <jsp:getProperty property="codePostal" name="utilisateur"/></h2>

<h2>Ville : <jsp:getProperty property="ville" name="utilisateur"/></h2>

<c:if test="${sessionScope.utilisateur != null}"><div>bouton edit</div></c:if>

<!-- possible uniquement si user = session en cours -->

<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>


</body>
</html>