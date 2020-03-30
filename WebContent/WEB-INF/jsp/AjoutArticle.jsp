<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.javaee.trocencheres.bo.ArticleVendu"%>
<%@page import="fr.eni.javaee.trocencheres.bo.Categorie"%>
<%@page import="fr.eni.javaee.trocencheres.bo.Utilisateur"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AjoutArticle</title>
</head>
<body>

	<jsp:useBean id="utilisateur" scope="application"
		class="fr.eni.javaee.trocencheres.bo.Utilisateur"></jsp:useBean>

	<h2>Ajoutez votre article</h2>

	<form action="<%=request.getContextPath()%>/AjoutArticle" method="post">
	
		<div class="container-sm">


			<div class="ajout">
				<label for="nomArticleVendu">Nom de l'article</label> <input
					type="text" class="form-control" id="nomArticleVendu"
					name="nomArticleVendu" placeholder="Nom de votre article">
			</div>

			<div class="ajout">
				<label for="description">Description</label> <input type="textarea"
					class="form-control" id="description" name="description"
					placeholder="Description de votre article">
			</div>

			<div class="form-group">
				<label for="categorie">Catégorie</label> <select
					class="form-control" id="categorie" name="noCategorie">
					<option>Informatique</option>
					<option>Ameublement</option>
					<option>Vêtement</option>
					<option>Sport&amp;Loisirs</option>
				</select>
			</div>

			<div class="ajout">
				<label for="dateDebutEncheres">Date de la mise aux enchères</label>
				<input type="datetime" class="form-control" id="dateDebutEncheres"
					name="dateDebutEncheres" placeholder="Date de début des enchères">
			</div>

			<div class="ajout">
				<label for="dateFinEncheres">Date de la fin des enchères</label> <input
					type="datetime" class="form-control" id="dateFinEncheres"
					name="dateFinEncheres" placeholder="Date de fin des enchères">
			</div>

			<div class="ajout">
				<label for="miseAPrix">Prix proposé</label> <input type="number"
					class="form-control" id="miseAPrix" name="piseAPrix"
					placeholder="Prix proposé">
			</div>

			<div class="ajout">
				<label for="prixVente">Prix de vente</label> <input type="number"
					class="form-control" id="prixVente" name="prixVente"
					placeholder="Initialement égal au prix proposé">
			</div>

			<div id="adresse">
				<div class="ajout">
					<label for="rue">Rue</label> <input type="text"
						class="form-control" id="rue" name="rue" placeholder="Rue">
				</div>

				<div class="ajout">
					<label for="codePostal">Code postal</label> <input type="number"
						class="form-control" id="codePostal" name="codePostal"
						placeholder="Code postal">
				</div>

				<div class="ajout">
					<label for="ville">Ville</label> <input type="text"
						class="form-control" id="ville" name="ville" placeholder="Ville">
				</div>
			</div>

		<div id="boutons">
			<input type="submit" name="btnEnregistrer" value="Enregistrer" title="Enregistrer" /> 
			<input type="reset" name="btAnnuler" value="Annuler" title="Annuler" />
		</div>
</div>
	</form>

</body>

</html>