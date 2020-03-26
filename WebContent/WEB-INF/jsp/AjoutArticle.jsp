<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.javaee.trocencheres.messages.LecteurMessage"%>
<%@page import="fr.eni.javaee.trocencheres.bo.ArticleVendu"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AjoutArticle</title>
</head>
<body>

	<%
		ArticleVendu articleVendu = (ArticleVendu) request.getAttribute("articleVendu");
		if (articleVendu != null) {
	%>
	<p style="color: blue;">L'article a été ajouté avec succès :</p>
	<p><%=articleVendu%></p>
	<%
		}
	%>

	<%
		List<Integer> listeCodesErreur = (List<Integer>) request.getAttribute("listeCodesErreur");
		if (listeCodesErreur != null) {
	%>
	<p style="color: red;">Erreur, l'article n'a pas pu être ajouté :</p>
	<%
		for (int codeErreur : listeCodesErreur) {
	%>
	<p><%=LecteurMessage.getMessageErreur(codeErreur)%></p>
	<%
		}
		}
	%>


	<h2>Ajoutez votre article</h2>


	<form action="<%=request.getContextPath()%>/AjoutArticle" method="post">
		<div class="container-sm">

			<div class="ajout">
				<input type="hidden" class="form-control" id="noArticleVendu"
					name="noArticleVendu">
			</div>


			<div class="ajout">
				<label for="nom">Nom de l'article</label> <input type="text"
					class="form-control" id="nom" name="nom"
					placeholder="Mettez le nom de votre article">
			</div>

			<div class="ajout">
				<label for="description">Description</label> <input type="text"
					class="form-control" id="description" name="description"
					placeholder="Mettez la description de votre article">
			</div>

			<div class="ajout">
				<label for="dateDebutEncheres">Date de la mise aux enchères</label>
				<input type="datetime" class="form-control" id="dateDebutEncheres"
					name="dateDebutEncheres"
					placeholder="Mettez la date de début des enchères">
			</div>

			<div class="ajout">
				<label for="dateFinEncheres">Date de la fin des enchères</label> <input
					type="datetime" class="form-control" id="dateFinEncheres"
					name="dateFinEncheres"
					placeholder="Mettez la date de fin des enchères">
			</div>

			<div class="ajout">
				<label for="miseAPrix">Prix proposé</label> <input type="number"
					class="form-control" id="miseAPrix" name="piseAPrix"
					placeholder="Mettez votre prix proposé">
			</div>

			<div class="ajout">
				<label for="prixVente">Prix de vente</label> <input type="number"
					class="form-control" id="prixVente" name="prixVente"
					placeholder="Le prix de vente est initialement égal à votre prix proposé et augmentera avec les enchères">
			</div>

			<div class="ajout">
				<label for="vendeur">Vendeur</label> <input type="text"
					class="form-control" id="vendeur" name="noUtilisateur"
					placeholder="Mettez votre pseudo">
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

			<div id="boutons">
				<input type="reset" name="btAnnuler" value="Annuler" title="Annuler" />
				<input type="submit" name="btnValider" value="Valider"
					title="Valider" />
			</div>

		</div>
	</form>


	<h4>

		<a href="./acceuil.jsp">Retour à l'accueil</a>

	</h4>

</body>
</html>