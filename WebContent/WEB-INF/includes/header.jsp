<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:if test="${sessionScope.utilisateur != null }">
	<h1 style="text-align: center;">Les objets sont nos amis</h1>
	<h3>${utilisateur.pseudo }</h3>
	<form action="<%=request.getContextPath()%>/Deconnexion" method="post">
		<button type="submit" value="Déconnexion">Déconnexion</button>
	</form>
</c:if>
<c:if test="${sessionScope.utilisateur == null }">
	<h1 style="text-align: center;">Les objets sont nos amis</h1>
	<a href="<%=request.getContextPath()%>/Register">S'inscrire</a>
	<a href="<%=request.getContextPath()%>/Connexion">Se connecter</a>
</c:if>
