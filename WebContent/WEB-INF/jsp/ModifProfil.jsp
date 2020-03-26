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

<h1>Modifier / supprimer mon Profil</h1>

<%-- <%Utilisateur user = new Utilisateur()%>

<h2><label for="pseudo">Pseudo</label>
<input type="text" name="pseudo" id="pseudo" placeholder="saisir votre nouveau pseudo" value="<%=user.getPseudo()%>"/></h2>

<!-- verif pseudo pas ds bdd -->

<h2><label for="nom">Nom</label>
<input type="text" name="nom" id="nom" placeholder="saisir votre nouveau nom" value="<%=user.getNom()%>"/></h2>

<h2><label for="prénom">Prénom</label>
<input type="text" name="prénom" id="prénom" placeholder="saisir votre nouveau prénom" value="<%=user.getPrenom()%>"/></h2>

<h2><label for="email">E-mail</label>
<input type="email" name="email" id="email" placeholder="saisir votre nouvel email" value="<%=user.getEmail()%>"/></h2>

<!-- verif email pas ds bdd -->

<h2><label for="téléphone">Téléphone</label>
<input type="tel" name="téléphone" id="téléphone" placeholder="saisir votre nouveau n° de téléphone" value="<%=user.getTelephone()%>"/></h2>

<h2><label for="adresse">Adresse</label>
<input type="text" name="adresse" id="adresse" placeholder="saisir votre nouvelle adresse" value="<%=user.getRue()%>"/></h2>

<h2><label for="codepost">Code Postal</label>
<input type="number" name="codepost" id="codepost" placeholder="saisir votre nouveau code postal" value="<%=user.getCodePostal()%>"/></h2>

<h2><label for="ville">Ville</label>
<input type="text" name="ville" id="ville" placeholder="saisir votre nouvelle ville" value="<%=user.getVille()%>"/></h2>

<h2><label for="mdp">Mot de passe actuel</label>
<input type="text" name="mdp" id="mdp" value="<%=user.getMotDePasse()%>" disabled="disabled"/></h2>

<h2><label for="mdp">Nouveau mot de passe</label>
<input type="password" name="mdp" id="mdp" placeholder="saisir votre nouveau mot de passe"/></h2>

<h2><label for="mdp">Confirmation du mot de passe</label>
<input type="password" name="mdp" id="mdp" placeholder="confirmer votre nouveau mot de passe"/></h2>
 --%>

<div>bouton enregistrer modifs</div>

<div>bouton supprimer compte</div>

</body>

</html>