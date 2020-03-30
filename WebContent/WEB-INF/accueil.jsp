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

	<div class="formulaire d-flex justify-content-center">
		<h1>Liste des enchères</h1>
	</div>
	<form action="<%=request.getContextPath()%>/Accueil" method="post">
		<div class="row">
			<div class="col-6">
				<div class="col-8">
					<h4>Filtres : </h4><br>
					<input type="text" name="motCle" class="form-control" placeholder="Le nom de l'article contient">
				</div>
				<div class="row container-liste col-12">
					<div class="justify-content-start">
						<div class="col-6">
							<label>Catégorie </label>
						</div>
					</div>
					<div class="col-6">
						<select class="form-control" id="categorie" name="categorie">
						    <option value="toutes">Toutes</option>
						    <option value="Informatique">Informatique</option>
						    <option value="Ameublement">Ameublement</option>
						    <option value="V&ecirc;tement">Vêtement</option>
				        	<option value="Sport">Sport&amp;Loisirs</option>
				        </select>
					</div>
				</div>
			</div>
	        <div class="col align-self-end">
				<input class="bouton btn btn-dark" type="submit" value="Rechercher">
			</div>
			
		</div>
	</form>
	
<jsp:include page="/WEB-INF/includes/listeArticles.jsp"></jsp:include>
<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>
</body>
</html>