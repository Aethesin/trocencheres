<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="fr.eni.javaee.trocencheres.bo.Utilisateur"%>

<%@page import="fr.eni.javaee.trocencheres.bo.Enchere"%>
<%@page import="fr.eni.javaee.trocencheres.bo.ArticleVendu"%>
<%@page import="fr.eni.javaee.trocencheres.bo.Categorie"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Détail vente</title>
</head>
<body>
	<jsp:include page="/WEB-INF/includes/header.jsp"></jsp:include>

	<jsp:useBean id="articleVendu" scope="request"
		class="fr.eni.javaee.trocencheres.bo.ArticleVendu"></jsp:useBean>
	<jsp:useBean id="categorie" scope="request"
		class="fr.eni.javaee.trocencheres.bo.Categorie"></jsp:useBean>
	<jsp:useBean id="utilisateur" scope="request"
		class="fr.eni.javaee.trocencheres.bo.Utilisateur"></jsp:useBean>



<c:if test="${pseudoSession != pseudoVendeur || verifDateDebut}">	

	<div>
		<div class="text-center">
			<c:if test="${!verifDate}">
				<h1 class="formulaire">Détail vente</h1>
			</c:if>
			<c:if test="${verifDate}">
				<c:if test="${pseudoEnchereur ==  pseudoSession}">
					<h1 class="formulaire">Bravo vous avez remporté la vente</h1>
				</c:if>
				<c:if test="${pseudoEnchereur !=  pseudoSession}">
					<h1 class="formulaire">${pseudoEnchereur } a remporté la vente</h1>
				</c:if>
			</c:if>
		</div>
		<div class="row">
			<div class="col-3"></div>
			<div class="col-9">
				<h2>
					<c:out value="${nomArticle}"></c:out>
				</h2>
				<div class="row h2-affichArt">
					<h2 class="col-2">
						Description :
					</h2>
					<div>
						<h2><c:out value="${description}"></c:out></h2>
					</div>
				</div>
				<div class="row h2-affichArt">
					<h2 class="col-2">
						Catégorie :
					</h2>
					<h2><c:out value="${libelle}"></c:out></h2>
				</div>
				<div class="row h2-affichArt">
					<h2 class="col-2">
						Meilleur offre :		
					</h2>
					<h2><c:out value="${ prixVente}"></c:out> points par <c:out value="${ pseudoEnchereur}"></c:out></h2>
				</div>
				<div class="row h2-affichArt">
					<h2 class="col-2">
						Mise à prix :
			
					</h2>
					<h2><c:out value="${ miseAPrix}"></c:out> points</h2>
				</div>
				<c:if test="${!verifDate && verifDateDebut }">
					<div class="row h2-affichArt">
						<h2 class="col-2">
							Début de l'enchère :		
						</h2>
						<h2><c:out value="${ dateDebutEnchere}"></c:out></h2>
					</div>
				</c:if>
				<div class="row h2-affichArt">
					<h2 class="col-2">
						Fin de l'enchère :		
					</h2>
					<h2><c:out value="${ dateFinEnchere}"></c:out></h2>
				</div>
				<div class="row h2-affichArt">
					<h2 class="col-2">
						Retrait :
					</h2>
					<h2>
						<c:out value="${ rue}"></c:out>
						<c:out value="${ codePostal}"></c:out>
						<p>
							<c:out value="${ ville}"></c:out>
						</p>
					</h2>
				</div>
				<div class="row h2-affichArt">
					<h2 class="col-2">
						Vendeur :
					</h2>
					<h2><c:out value="${ nomVendeur}"></c:out></h2>
				</div>
				<c:if test="${verifDate}">
					<c:if test="${pseudoEnchereur ==  pseudoSession}">
						<div class="row h2-affichArt">
							<h2 class="col-2">
								Téléphone :
							</h2>
							<h2><c:out value="${ telephone}"></c:out></h2>
						</div>
						<a class="btn btn-primary" href="<%=request.getContextPath()%>/accueil">Retrait effectué</a>
					</c:if>
					<c:if test="${pseudoEnchereur !=  pseudoSession}">
						<a class="btn btn-primary" href="<%=request.getContextPath()%>/accueil">Back</a>
					</c:if>
				</c:if>
				<c:if test="${(!verifDate && statut != 2 && !verifDateDebut) }">
					<form action="<%=request.getContextPath()%>/AffichEnchere?noArticle=${articleVendu.noArticleVendu}" method="post">
						<input name="pseudoMeilleur" value="${pseudoEnchereur}" hidden>
						<input name="prixVente" value="${prixVente}" hidden>
						<div class="row h2-affichArt">
							<h2 class="col-2">
								<label for="proposition">Ma proposition : </label> 
							</h2>
							<div class="col-1">
								<input class="form-control col-8" type="number" id="proposition" name="proposition">
							</div>
					
							<div id="bouton text-center">
								<input class="btn btn-primary" type="submit" name="btnEncherir" value="Enchérir" />
							</div>
						</div>
					</form>
				</c:if>
	
			</div>
		</div>
	</div>
</c:if>



<c:if test="${pseudoSession == pseudoVendeur && !verifDateDebut}">

<div class="text-center formulaire">
		<h1>Modifiez votre article</h1>
	</div>
<div class="container">
	<div class="col-3"></div>
	<div class="col-9">
		<form class="formulaire" action="<c:url value="/ModifArticle"/>" method="post">
	
			<input hidden name="noArticle" value="${articleVendu.noArticleVendu}">
			<div class="ajout row h2-affichArt">
					<div class="col-2">
						<label for="nomArticleVendu">Article:<span
							class="requis"></span></label>
					</div>
					<div class="col-6">
						<input class="form-control" type="text" id="nomArticleVendu" required="required"
							name="nomArticleVendu" maxlength="30" placeholder="Nom de l'article"
							value="<c:out value="${nomArticle}"/>" />
					</div>
				</div>
		
				<div class="ajout row h2-affichArt">
					<div class="col-2">
						<label for="description">Description<span
							class="requis"></span></label>
					</div>
					<div class="col-6">
						<textarea class="form-control" name="description" rows=5 cols=60 required="required"
							wrap="soft" placeholder="Description de votre article">${description }</textarea>
					</div>
				</div>
		
				<div class="ajout row h2-affichArt">
					<div class="col-2">
						<label for="categorie">Catégorie<span class="requis"></span></label>
					</div>
					<div class="col-4">
						<select class="form-control" id="categorie" name="categorie" value="${noCategorie}">
							<option value="1" <c:if test="${noCategorie == 1}">selected</c:if> >Vêtement</option>
							<option value="2" <c:if test="${noCategorie == 2}">selected</c:if> >Informatique</option>
							<option value="3" <c:if test="${noCategorie == 3}">selected</c:if> >Ameublement</option>
							<option value="4" <c:if test="${noCategorie == 4}">selected</c:if> >Sport &amp; Loisirs</option>
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
							name="dateDebutEncheresDate" value="${dateDebutEnchereDate }"/>
						<input class="form-control" type="time" id="dateDebutEncheresTime"
							name="dateDebutEncheresTime" value="${dateDebutEnchereTime }">
					</div>
				</div>
		
				<div class="ajout row h2-affichArt">
					<div class="col-2">
						<label for="dateFinEncheres">Date de la fin des enchères<span
							class="requis"></span></label>
					</div>
					<div class="col-4">
						<input class="form-control" type="date" id="dateFinEncheresDate"
							name="dateFinEncheresDate" value="${dateFinEnchereDate }"/>
						<input class="form-control" type="time" id="dateFinEncheresTime"
							name="dateFinEncheresTime" value="${dateFinEnchereTime }">
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
								placeholder="Rue" value="<c:out value="${rue }"/>" />
					</div>
				</div>
				<div class="row">
					<div class="col-2">
						<label for="codePostal">Code postal</label>
					</div>
					<div class="col-4">
					  <input class="form-control" type="text"
						id="codePostal" name="codePostal" placeholder="Code postal"
						value="<c:out value="${codePostal }"/>" />
					</div>
				</div>
				<div class="row">
					<div class="col-2">
						<label for="ville">Ville</label>
					</div>
					<div class="col-4">
						 <input class="form-control" type="text" id="ville"
							name="ville" placeholder="Ville"
							value="<c:out value="${ville }"/>" />
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

</c:if>

	<c:if test="${listeCodesErreur != null }">
		<p style="color: red;">Erreur, votre participation est échouée :</p>
		<c:forEach items="listeCodesErreur" var="codeErreur">
			<c:out value="${codeErreur }"></c:out>
		</c:forEach>
	</c:if>
	<c:if test="${(!empty enchere) && (listeCodesErreur == null)}">
		<p>
			Votre enchère est enregistrée avec succès avec le prix proposé :
			<c:out value="${prixVente }"></c:out>
			points.
		</p>
	</c:if>
</body>
</html>