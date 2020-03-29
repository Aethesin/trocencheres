<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="fr.eni.javaee.trocencheres.messages.LecteurMessage"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Participation échouée</title>
</head>

<body>
	<c:if test="${listeCodesErreur != null }">
		<%
			List<Integer> listeCodesErreur = (List<Integer>) request.getAttribute("listeCodesErreur");
		%>
	</c:if>
	<p style="color: red;">Erreur, votre participation est échouée :</p>
	<c:forEach items="listeCodesErreur" var="codeErreur">
		<c:out value="${codeErreur }]"></c:out>
	</c:forEach>

	<h4>
		<a href="<%=request.getContextPath()%>/AffichEnchere">Afficher vente</a>
	</h4>

</body>
</html>