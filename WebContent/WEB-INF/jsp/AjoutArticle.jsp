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

	<form action="<c:url value="/AjoutArticle"/>" method="post">


		<div class="ajout">
			<label for="nomArticleVendu">Nom de l'article<span
				class="requis"></span></label>
			<input type="text" id="nomArticleVendu" required="required"
				name="nomArticleVendu" maxlength="30" placeholder="Nom de l'article"
				value="<c:out value="${nomArticleVendu}"/>" />
		</div>

		<div class="ajout">
		<label for="description">Description<span
				class="requis"></span></label>
		<textarea name="description" rows=5 cols=60 required="required"
				wrap="soft" placeholder="Description de votre article"></textarea>
		</div>

		<div class="ajout">
			<label for="categorie">Catégorie<span class="requis"></span></label>
			<select id="categorie" name="categorie">
				<option value="1">Informatique</option>
				<option value="2">Ameublement</option>
				<option value="3">Vêtement</option>
				<option value="4">Sport &amp; Loisirs</option>
			</select>
		</div>

		<div class="ajout">
			<label for="dateDebutEncheres">Date de la mise aux enchères<span
				class="requis"></span></label>
			<input type="date" id="dateDebutEncheresDate"
				name="dateDebutEncheresDate"/>
			<input type="time" id="dateDebutEncheresTime"
				name="dateDebutEncheresTime">
		</div>

		<div class="ajout">
			<label for="dateFinEncheres">Date de la fin des enchères<span
				class="requis"></span></label>
			<input type="date" id="dateFinEncheresDate"
				name="dateFinEncheresDate"/>
			<input type="time" id="dateFinEncheresTime"
				name="dateFinEncheresTime">
		</div>

		<div class="ajout">
			<label for="miseAPrix">Mise à prix<span class="requis"></span></label>
			<input type="number" id="miseAPrix" name="miseAPrix"
				placeholder="Prix proposé" value="<c:out value="${miseAPrix }"/>" />
		</div>

		<div>
			<p>Retrait</p>
			<fieldSet>
				
				<div class="ajout">
					<label for="rue">Rue</label> <input type="text" id="rue" name="rue"
						placeholder="Rue" value="<c:out value="${utilisateur.rue }"/>" />
				</div>

				<div class="ajout">
					<label for="codePostal">Code postal</label> <input type="text"
						id="codePostal" name="codePostal" placeholder="Code postal"
						value="<c:out value="${utilisateur.codePostal }"/>" />
				</div>

				<div class="ajout">
					<label for="ville">Ville</label> <input type="text" id="ville"
						name="ville" placeholder="Ville"
						value="<c:out value="${utilisateur.ville }"/>" />
				</div>
			</fieldSet>
		</div>

		<div id="boutons">
			<input type="submit" name="btnEnregistrer" value="Enregistrer"
				title="Enregistrer" />
			<input type="reset" name="btAnnuler"
				value="Annuler" title="Annuler" />
		</div>

	</form>

<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>

</body>

</html>