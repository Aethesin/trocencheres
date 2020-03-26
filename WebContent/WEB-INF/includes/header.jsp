<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div style="background-color: #003b6f;">
	<c:if test="${!empty utilisateur}">
		<h1 style="text-align: left;">Les objets sont nos amis</h1>
		<h3 style="text-align: right;">${utilisateur.pseudo }</h5>
		<form action="<%=request.getContextPath()%>/Deconnexion" method="post">
			<a><button type="submit" value="Déconnexion">Déconnexion</button></a>
		</form>
	</c:if>
	<c:if test="${empty utilisateur}">
		<h1 style="text-align: left;">Les objets sont nos amis</h1>
		<a href="<%=request.getContextPath()%>/Connexion">Se connecter</a>
		<a href="<%=request.getContextPath()%>/Register">S'inscrire</a>
	</c:if>
</div>