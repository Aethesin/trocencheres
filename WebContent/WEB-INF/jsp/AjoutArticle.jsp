<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AjoutArticle</title>
</head>
<body>

<jsp:include page="/WEB-INF/includes/header.jsp"></jsp:include>

<br>
	<div class="text-center formulaire">
		<h1>Ajoutez votre article</h1>
	</div>

<div class="container">
<div class="col-3"></div>
	<div class="col-9">
		<form action="<c:url value="/AjoutArticle"/>" method="post">
			<div class="ajout row h2-affichArt">
				<div class="col-2">
					<label for="nomArticleVendu">Article:<span
						class="requis"></span></label>
				</div>
				<div class="col-6">
					<input class="form-control" type="text" id="nomArticleVendu" required="required"
						name="nomArticleVendu" maxlength="30" placeholder="Nom de l'article"
						value="<c:out value="${nomArticleVendu}"/>" />
				</div>
			</div>
	
			<div class="ajout row h2-affichArt">
				<div class="col-2">
					<label for="description">Description<span
						class="requis"></span></label>
				</div>
				<div class="col-6">
					<textarea class="form-control" name="description" rows=5 cols=60 required="required"
						wrap="soft" placeholder="Description de votre article"></textarea>
				</div>
			</div>
	
			<div class="ajout row h2-affichArt">
				<div class="col-2">
					<label for="categorie">Catégorie<span class="requis"></span></label>
				</div>
				<div class="col-4">
					<select class="form-control" id="categorie" name="categorie">
						<option value="1">Informatique</option>
						<option value="2">Ameublement</option>
						<option value="3">Vêtement</option>
						<option value="4">Sport &amp; Loisirs</option>
					</select>
				</div>
			</div>
	
			<div class="ajout row h2-affichArt">
				<div class="col-2">
					<label for="dateDebutEncheres">Date de la mise aux enchères<span
						class="requis"></span></label>
				</div>
				<div class="col-4">
					<input class="form-control" type="date" id="dateDebutEncheresDate"
						name="dateDebutEncheresDate"/>
					<input class="form-control" type="time" id="dateDebutEncheresTime"
						name="dateDebutEncheresTime">
				</div>
			</div>
	
			<div class="ajout row h2-affichArt">
				<div class="col-2">
					<label for="dateFinEncheres">Date de la fin des enchères<span
						class="requis"></span></label>
				</div>
				<div class="col-4">
					<input class="form-control" type="date" id="dateFinEncheresDate"
						name="dateFinEncheresDate"/>
					<input class="form-control" type="time" id="dateFinEncheresTime"
						name="dateFinEncheresTime">
				</div>
			</div>
	
			<div class="ajout row h2-affichArt">
				<div class="col-2">
					<label for="miseAPrix">Mise à prix<span class="requis"></span></label>
				</div>
				<div class="col-4">
					<input class="form-control" type="number" id="miseAPrix" name="miseAPrix"
						placeholder="Prix proposé" value="<c:out value="${miseAPrix }"/>" />
				</div>
			</div>
	
			<label>Retrait</label>
			<div class="row h2-affichArt">
				<div class="col-2">
					<label for="rue">Rue</label>
				</div>
				<div class="col-4">
					 <input class="form-control" type="text" id="rue" name="rue"
							placeholder="Rue" value="<c:out value="${utilisateur.rue }"/>" />
				</div>
			</div>
			<div class="row">
				<div class="col-2">
					<label for="codePostal">Code postal</label>
				</div>
				<div class="col-4">
				  <input class="form-control" type="text"
					id="codePostal" name="codePostal" placeholder="Code postal"
					value="<c:out value="${utilisateur.codePostal }"/>" />
				</div>
			</div>
			<div class="row">
				<div class="col-2">
					<label for="ville">Ville</label>
				</div>
				<div class="col-4">
					 <input class="form-control" type="text" id="ville"
						name="ville" placeholder="Ville"
						value="<c:out value="${utilisateur.ville }"/>" />
				</div>
			</div>
	
			<div id="boutons" class="row h2-affichArt">
				<div class="col-2">
					<input type="submit" name="btnEnregistrer" value="Enregistrer"
						title="Enregistrer" />
				</div>
				<div class="col-10">
					<input type="reset" name="btAnnuler"
						value="Annuler" title="Annuler" />
				</div>
			</div>
	
		</form>
	</div>
</div>

<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>

</body>

</html>