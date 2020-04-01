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

	<h2>Ajoutez votre article</h2>

	<form action="<c:url value="/AjoutArticle"/>" method="post">


		<div class="ajout">
			<label for="nomArticleVendu">Nom de l'article<span
				class="requis"></span></label> <input type="text" id="nomArticleVendu"
				name="nomArticleVendu" maxlength="30" placeholder="Nom de l'article"
				value="<c:out value="${nomAticle}"/>" />
		</div>

		<div class="ajout">
		<label for="description">Description<span
				class="requis"></span></label>
			<textarea name="description" rows=5 cols=60 required="required"
				wrap="soft" placeholder="Description de votre article"></textarea>
		</div>

		<div class="ajout">
			<label for="categorie">Catégorie<span class="requis"></span></label>
			<select id="categorie" name="noCategorie">
				<option>Informatique</option>
				<option>Ameublement</option>
				<option>Vêtement</option>
				<option>Sport&amp;Loisirs</option>
			</select>
		</div>

		<div class="ajout">
			<label for="dateDebutEncheres">Date de la mise aux enchères<span
				class="requis"></span></label> <input type="datetime-local" id="dateDebutEncheres"
				name="dateDebutEncheres" placeholder="Date de début des enchères"
				value="<c:out value="${dateDebutEncheres }"/>" />
		</div>

		<div class="ajout">
			<label for="dateFinEncheres">Date de la fin des enchères<span
				class="requis"></span></label> <input type="datetime-local" id="dateFinEncheres"
				name="dateFinEncheres" placeholder="Date de fin des enchères"
				value="<c:out value="${dateFinEncheres }"/>" />
		</div>

		<div class="ajout">
			<label for="miseAPrix">Mise à prix<span class="requis"></span></label>
			<input type="number" id="miseAPrix" name="piseAPrix"
				placeholder="Prix proposé" value="<c:out value="${miseAPrix }"/>" />
		</div>

		<div>
			<p>Retrait</p>
			<fieldSet>
				
				<div class="ajout">
					<label for="rue">Rue</label> <input type="text" id="rue" name="rue"
						placeholder="Rue" value="<c:out value="${rue }"/>" />
				</div>

				<div class="ajout">
					<label for="codePostal">Code postal</label> <input type="text"
						id="codePostal" name="codePostal" placeholder="Code postal"
						value="<c:out value="${codePostal }"/>" />
				</div>

				<div class="ajout">
					<label for="ville">Ville</label> <input type="text" id="ville"
						name="ville" placeholder="Ville"
						value="<c:out value="${ville }"/>" />
				</div>
			</fieldSet>
		</div>

		<div id="boutons">
			<input type="submit" name="btnEnregistrer" value="Enregistrer"
				title="Enregistrer" /> <input type="reset" name="btAnnuler"
				value="Annuler" title="Annuler" />
		</div>

	</form>

</body>

</html>