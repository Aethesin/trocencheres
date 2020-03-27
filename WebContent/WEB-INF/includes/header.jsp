<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:if test="${sessionScope.utilisateur != null }">
	<h1 style="text-align: center;">Les objets sont nos amis</h1>
	<a href="<%=request.getContextPath()%>/AffichEnchere">Enchères</a>
	<a href ="<%=request.getContextPath()%>/AjoutArticle">Vendre un article</a>
	<a href="<%=request.getContextPath()%>/AffichProfil?pseudo=${sessionScope.utilisateur.pseudo}">Mon profil</a>
	<a href="<%=request.getContextPath()%>/Deconnexion">Déconnexion</a>
	
</c:if>
<c:if test="${sessionScope.utilisateur == null }">
	<h1 style="text-align: center;">Les objets sont nos amis</h1>
	<a href="<%=request.getContextPath()%>/Register">S'inscrire</a>
	<a href="<%=request.getContextPath()%>/Connexion">Se connecter</a>
</c:if>


