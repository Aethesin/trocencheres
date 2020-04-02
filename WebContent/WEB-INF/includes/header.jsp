<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/4-col-portfolio.css" rel="stylesheet">
<link href="css/custom.css" rel="stylesheet">

<c:set var="user" value="${sessionScope.utilisateur }"></c:set>

<c:if test="${user != null }">
	<nav class="navbar navbar-expand-lg fixed-top navbar-light navigation">
		<div class="container">
			<a class="navbar-brand" href="./"><p class="h2">Les objets sont nos amis</p></a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarResponsive" aria-controls="navbarResponsive"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarResponsive">
					<ul class="navbar-nav ml-auto">
						<c:if test="${user.statut == 1 }">
							<li class="nav-item active">
								<a class="nav-link" href="<%=request.getContextPath()%>/GestionAdmin">Gestion Administrateur</a>
							</li>
						</c:if>
						<li class="nav-item active">
							<a class="nav-link" href="<%=request.getContextPath()%>/AffichEnchere">Enchères</a>
						</li>
						<li class="nav-item active">
							<a class="nav-link" href ="<%=request.getContextPath()%>/AjoutArticle">Vendre un article</a>
						</li>
						<li class="nav-item active">
							<a class="nav-link" href="<%=request.getContextPath()%>/AffichProfil?pseudo=${sessionScope.utilisateur.pseudo}">Mon profil</a>
						</li>
						<li class="nav-item active">
							<a class="nav-link" href="<%=request.getContextPath()%>/Deconnexion">Déconnexion</a>
						</li>
					</ul>
				</div>
		</div>
	</nav>
</c:if>
<c:if test="${user == null }">
<nav class="navbar navbar-expand-lg fixed-top navbar-light navigation">
	<div class="container">
		<a class="navbar-brand" href="./"><p class="h2">Les objets sont nos amis</p></a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active">
						<a class="nav-link" href="<%=request.getContextPath()%>/Register">S'inscrire</a>
					</li>
					<li>
						<a class="nav-link" href="<%=request.getContextPath()%>/Connexion">Se connecter</a>
					</li>
				</ul>
			</div>
	</div>
</nav>
</c:if>


