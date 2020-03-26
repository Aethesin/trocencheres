<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page d'accueil</title>
</head>
<body>
<jsp:include page="/WEB-INF/includes/header.jsp"></jsp:include>

	<form action="<%=request.getContextPath()%>/Accueil" method="post">
		<input type="text" name="motCle" class="form-control" placeholder="Le nom de l'article contient">
		<label>Catégorie</label>
		<select class="form-control" id="categorie" name="categorie">
		    <option value="toutes">Toutes</option>
		    <option value="Informatique">Informatique</option>
		    <option value="Ameublement">Ameublement</option>
		    <option value="V&ecirc;tement">Vêtement</option>
        	<option value="Sport">Sport&amp;Loisirs</option>
        </select>
		<input type="submit" value="Rechercher">
	</form>
	
<jsp:include page="/WEB-INF/includes/listeArticles.jsp"></jsp:include>
<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>
</body>
</html>