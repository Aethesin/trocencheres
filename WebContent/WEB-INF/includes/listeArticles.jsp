<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:if test="${empty listeArticlesVendu}">
		<p>La liste n'existe pas</p>
	</c:if>

<c:forEach var="lArt" items="${listeArticlesVendu}">
	${lArt.nomArticleVendu }
</c:forEach>