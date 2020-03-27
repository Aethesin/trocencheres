<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connexion</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/Connexion" method="post">
		<label>Pseudo : </label>
		<input type="text" name="pseudo"><br>
		<label>Mot de Passe : </label>
		<input type="password" name="motDePasse"><br>
		<input type="submit" value="Valider">
		<div>
			<input type="checkbox" name="seSouvenir">
			<label>Se souvenir de moi</label>
			<a href="<%=request.getContextPath()%>/ResetPassword"></a>
		</div>
	</form>
	<button><a href="<%=request.getContextPath()%>/Register">S'inscrire</a></button>
</body>
</html>