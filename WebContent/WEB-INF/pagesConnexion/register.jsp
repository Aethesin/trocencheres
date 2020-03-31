<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>S'inscrire</title>
</head>
<body>

<jsp:include page="/WEB-INF/includes/header.jsp"></jsp:include>

<c:if test="${!empty listeCodesErreurString}">
	<c:forEach var="l" items="${listeCodesErreurString}">
		<p>${l}</p>
	</c:forEach>
</c:if>

<div class="formulaire d-flex justify-content-center">
		<h4>Inscription</h4>
	</div>
<div class="row">
	<div class="col">
		<form action="<%=request.getContextPath()%>/Register" method="post">
		
			<div class="row">
				<div class="row col-6">
					<div class="col-9 d-flex justify-content-end">
						<label>Pseudo : </label>
					</div>
					<div class="col-3">
						<input class="form-control" type="text" name="pseudo" value="${utilisateur.pseudo}"><br>
					</div>
				</div>
				<div class="row col-3">
					<div class="col-3 d-flex justify-content-end">
						<label>Nom : </label>
					</div>
					<div class="col-6">
						<input class="form-control" type="text" name="nom" value="${utilisateur.nom}"><br>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="row col-6">
					<div class="col-9 d-flex justify-content-end">
						<label>Prénom : </label>
					</div>
					<div class="col-3">
						<input class="form-control" type="text" name="prenom" value="${utilisateur.prenom}"><br>
					</div>
				</div>
				<div class="row col-3">
					<div class="col-3 d-flex justify-content-end">
						<label>Email : </label>
					</div>
					<div class="col-6">
						<input class="form-control" type="text" name="email" value="${utilisateur.email}"><br>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="row col-6">
					<div class="col-9 d-flex justify-content-end">
						<label>Téléphone : </label>
					</div>
					<div class="col-3">
						<input class="form-control" type="text" name="telephone" value="${utilisateur.telephone}"><br>
					</div>
				</div>
				<div class="row col-3">
					<div class="col-3 d-flex justify-content-end">
						<label>Rue : </label>
					</div>
					<div class="col-6">
						<input class="form-control" type="text" name="rue" value="${utilisateur.rue}"><br>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="row col-6">
					<div class="col-9 d-flex justify-content-end">
						<label>Code postal : </label>
					</div>
					<div class="col-3">
						<input class="form-control" type="text" name="code_postal" value="${utilisateur.codePostal}"><br>
					</div>
				</div>
				<div class="row col-3">
					<div class="col-3 d-flex justify-content-end">
						<label>Ville : </label>
					</div>
					<div class="col-6">
						<input class="form-control" type="text" name="ville" value="${utilisateur.ville}"><br>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="row col-6">
					<div class="col-9 d-flex justify-content-end">
						<label>Mot de passe : </label>
					</div>
					<div class="col-3">
						<input class="form-control" type="password" name="motDePasse"><br>
					</div>
				</div>
				<div class="row col-3">
					<div class="col-3 d-flex justify-content-end">
						<label>Confirmation : </label>
					</div>
					<div class="col-6">
						<input class="form-control" type="password" name="motDePasseVerif"><br>
					</div>
				</div>
			</div>
			<div class="row d-flex justify-content-center">
				<div>
					<input class="btn btn-light" type="submit" value="Valider">
				</div>
				<div>
					<h4>
						<a class="btn btn-dark" href="<%=request.getContextPath()%>/">Retour à l'accueil</a>
					</h4>
				</div>
			</div>
		</form>
	</div>
</div>

<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>
</body>
</html>