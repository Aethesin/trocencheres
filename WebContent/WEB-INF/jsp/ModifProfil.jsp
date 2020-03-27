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

<h1>Modifier / supprimer mon Profil</h1>

<jsp:useBean id="utilisateur" scope="request" class="fr.eni.javaee.trocencheres.bo.Utilisateur"></jsp:useBean>

<h2><label for="pseudo">Pseudo</label>
<input type="text" name="pseudo" id="pseudo" placeholder="saisir votre nouveau pseudo" value="<jsp:getProperty property="pseudo" name="utilisateur"/>"/></h2>

<!-- verif pseudo pas ds bdd -->

<h2><label for="nom">Nom</label>
<input type="text" name="nom" id="nom" placeholder="saisir votre nouveau nom" value="<jsp:getProperty property="nom" name="utilisateur"/>"/></h2>

<h2><label for="prénom">Prénom</label>
<input type="text" name="prénom" id="prénom" placeholder="saisir votre nouveau prénom" value="<jsp:getProperty property="prenom" name="utilisateur"/>"/></h2>

<h2><label for="email">E-mail</label>
<input type="email" name="email" id="email" placeholder="saisir votre nouvel email" value="<jsp:getProperty property="email" name="utilisateur"/>"/></h2>

<!-- verif email pas ds bdd -->

<h2><label for="téléphone">Téléphone</label>
<input type="tel" name="téléphone" id="téléphone" placeholder="saisir votre nouveau n° de téléphone" value="<jsp:getProperty property="telephone" name="utilisateur"/>"/></h2>

<h2><label for="adresse">Adresse</label>
<input type="text" name="adresse" id="adresse" placeholder="saisir vos nouveaux n° et nom de rue" value="<jsp:getProperty property="rue" name="utilisateur"/>"/></h2>

<h2><label for="codepost">Code Postal</label>
<input type="text" name="codepost" id="codepost" placeholder="saisir votre nouveau code postal" value="<jsp:getProperty property="codePostal" name="utilisateur"/>"/></h2>

<h2><label for="ville">Ville</label>
<input type="text" name="ville" id="ville" placeholder="saisir votre nouvelle ville" value="<jsp:getProperty property="ville" name="utilisateur"/>"/></h2>

<h2><label for="mdp">Mot de passe actuel</label>
<input type="password" name="mdpActuel" id="mdpActuel" placeholder="saisir votre ancien mot de passe"/></h2>

<h2><label for="mdp">Nouveau mot de passe</label>
<input type="password" name="mdp" id="mdp" placeholder="saisir votre nouveau mot de passe"/></h2>

<h2><label for="mdp">Confirmation du mot de passe</label>
<input type="password" name="mdpConfirm" id="mdpConfirm" placeholder="confirmer votre nouveau mot de passe"/></h2>

<input type="submit" name="Valider" value="Valider les modifications" title="Validation des saisies" >

<input type="submit" name="Supprimer" value="Supprimer le compte" title="Suppression du compte utilisateur">

<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>

</body>

</html>