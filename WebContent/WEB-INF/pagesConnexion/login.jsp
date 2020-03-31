<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connexion</title>
</head>
<body>

<jsp:include page="/WEB-INF/includes/header.jsp"></jsp:include>

<div class="formulaire d-flex justify-content-center">
	<form action="<%=request.getContextPath()%>/Connexion" method="post">
		<div class="row">
			<div class="col-4">
				<label>Identifiant:</label>
			</div>
			<div class="col-8">
				<input class="form-control" type="text" name="pseudo" value="${pseudo }">
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-4">
				<label>Mot de Passe:</label>
			</div>
			<div class="col-8">
				<input class="form-control" type="password" name="motDePasse" value="${motDePasse }">
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-4">
				<input class="btn btn-secondary"  type="submit" value="Connexion">
			</div>
			<div class="col-8">
				<input type="checkbox" name="seSouvenir" checked>
				<label>Se souvenir de moi</label><br>
				<a href="<%=request.getContextPath()%>/Forgot">Mot de passe oublié</a>
			</div>
		</div>
	</form>
</div>
<br>
<br>
<div class="d-flex justify-content-center">
	<a class="btn btn-secondary btn-lg" href="<%=request.getContextPath()%>/Register">Créer un compte</a>
</div>

<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>
</body>
</html>