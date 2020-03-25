<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>S'inscrire</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/Register" method="post">
		<label>Pseudo : </label>
		<input type="text" name="pseudo"><br>
		<label>Nom : </label>
		<input type="text" name="nom"><br>
		<label>Prenom : </label>
		<input type="text" name="prenom"><br>
		<label>Email : </label>
		<input type="text" name="email"><br>
		<label>Telephone : </label>
		<input type="text" name="telephone"><br>
		<label>Rue : </label>
		<input type="text" name="rue"><br>
		<label>Code postal : </label>
		<input type="text" name="code_postal"><br>
		<label>Ville : </label>
		<input type="text" name="ville"><br>
		<label>Mot de passe : </label>
		<input type="password" name="motDePasse"><br>
		<label>Vérifier mot de passe : </label>
		<input type="password" name="motDePasseVerif"><br>
		<input type="submit" value="Valider">
	</form>
</body>
</html>