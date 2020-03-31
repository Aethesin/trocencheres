<%@page import="fr.eni.javaee.trocencheres.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Modification du Profil</title>
</head>
<body>

<jsp:include page="/WEB-INF/includes/header.jsp"></jsp:include>

<h1 class="formulaire d-flex justify-content-center">Modifier / supprimer mon Profil</h1>

<jsp:useBean id="utilisateur" scope="request" class="fr.eni.javaee.trocencheres.bo.Utilisateur"></jsp:useBean>

<div class="row">
	<div class="col">
		<form action="<%=request.getContextPath()%>/ModifProfil" method="post">
			<input type="hidden" name="noUtilisateur" value="<jsp:getProperty property="noUtilisateur" name="utilisateur"/>">
			<div class="row">
				<div class="row col-6">
					<div class="col-9 d-flex justify-content-end">
						<label for="pseudo">Pseudo</label>
					</div>
					<div class="col-3">
						<input class="form-control" type="text" name="pseudo" id="pseudo" placeholder="saisir votre nouveau pseudo" value="<jsp:getProperty property="pseudo" name="utilisateur"/>"/>
					</div>
				</div>
				<!-- verif pseudo pas ds bdd -->
				<div class="row col-3">
					<div class="col-3 d-flex justify-content-end">
						<label for="nom">Nom</label>
					</div>
					<div class="col-6">
						<input class="form-control" type="text" name="nom" id="nom" placeholder="saisir votre nouveau nom" value="<jsp:getProperty property="nom" name="utilisateur"/>"/>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="row col-6">
					<div class="col-9 d-flex justify-content-end">
						<label for="prénom">Prénom</label>						
					</div>
					<div class="col-3">
						<input class="form-control" type="text" name="prenom" id="prénom" placeholder="saisir votre nouveau prénom" value="<jsp:getProperty property="prenom" name="utilisateur"/>"/>
					</div>
				</div>
				<div class="row col-3">
					<div class="col-3 d-flex justify-content-end">
						<label for="email">E-mail</label>
					</div>
					<div class="col-6">
						<input class="form-control" type="email" name="email" id="email" placeholder="saisir votre nouvel email" value="<jsp:getProperty property="email" name="utilisateur"/>"/>
					</div>
				</div>
			</div>
			<!-- verif email pas ds bdd -->
			
			<div class="row">
				<div class="row col-6">
					<div class="col-9 d-flex justify-content-end">
						<label for="téléphone">Téléphone</label>
					</div>
					<div class="col-3">
						<input class="form-control" type="tel" name="telephone" id="téléphone" placeholder="saisir votre nouveau n° de téléphone" value="<jsp:getProperty property="telephone" name="utilisateur"/>"/>
					</div>
				</div>
				<div class="row col-3">
					<div class="col-3 d-flex justify-content-end">
						<label for="adresse">Adresse</label>
					</div>
					<div class="col-6">
						<input class="form-control" type="text" name="rue" id="adresse" placeholder="saisir vos nouveaux n° et nom de rue" value="<jsp:getProperty property="rue" name="utilisateur"/>"/>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="row col-6">
					<div class="col-9 d-flex justify-content-end">
						<label for="codepost">Code Postal</label>
					</div>
					<div class="col-3">
						<input class="form-control" type="text" name="codePostal" id="codepost" placeholder="saisir votre nouveau code postal" value="<jsp:getProperty property="codePostal" name="utilisateur"/>"/>
					</div>
				</div>
				<div class="row col-3">
					<div class="col-3 d-flex justify-content-end">
						<label for="ville">Ville</label>
					</div>
					<div class="col-6">
						<input class="form-control" type="text" name="ville" id="ville" placeholder="saisir votre nouvelle ville" value="<jsp:getProperty property="ville" name="utilisateur"/>"/>
					</div>
				</div>
			</div>						
			
			<div class="row">
				<div class="row col-6">
					<div class="col-9 d-flex justify-content-end">
						<label for="mdp">Mot de passe actuel</label>
					</div>
					<div class="col-3">
						<input class="form-control" type="password" name="mdpActuel" id="mdpActuel" placeholder="saisir votre ancien mot de passe"/>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="row col-6">
					<div class="col-9 d-flex justify-content-end">
						<label for="mdp">Nouveau mot de passe</label>
					</div>
					<div class="col-3">
						<input class="form-control" type="password" name="motDePasse" id="mdp" placeholder="saisir votre nouveau mot de passe"/>
					</div>
				</div>
				<div class="row col-3">
					<div class="col-3 d-flex justify-content-end">
						<label for="mdp">Confirmation</label>
					</div>
					<div class="col-6">
						<input class="form-control" type="password" name="mdpConfirm" id="mdpConfirm" placeholder="confirmer votre nouveau mot de passe"/>
					</div>
				</div>
			</div>							

			<div class="row">
				<div class="row col-6">
					<div class="col-9 d-flex justify-content-end">
					</div>
					<div class="col-3">						
						<input class="btn btn-primary" type="submit" name="Valider" value="Valider les modifications" title="Validation des saisies" >
					</div>
				</div>
				</form>
				<div class="row col-3">
					<div class="col-3 d-flex justify-content-end">
					</div>
					<div class="col-6">						
						<form action="<%=request.getContextPath()%>/SupprimProfil" method="get">
							<input type="hidden" name="noUtilisateur" value="<jsp:getProperty property="noUtilisateur" name="utilisateur"/>">
							<input class="btn btn-dark" type="submit" name="Supprimer" value="Supprimer le compte" title="Suppression du compte utilisateur">
						</form>
					</div>
				</div>
			</div>
			
			
		
	</div>
</div>
	


<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>

			<div class="row">
				<div class="row col-6">
					<div class="col-9 d-flex justify-content-end">
						
					</div>
					<div class="col-3">
						
					</div>
				</div>
				<div class="row col-3">
					<div class="col-3 d-flex justify-content-end">
						
					</div>
					<div class="col-6">
						
					</div>
				</div>
			</div>

</body>

</html>