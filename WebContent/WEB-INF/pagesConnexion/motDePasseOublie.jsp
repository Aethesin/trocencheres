<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nouveau mot de passe</title>
</head>
<body>

<jsp:include page="/WEB-INF/includes/header.jsp"></jsp:include>

<h2 class="formulaire d-flex justify-content-center">Réinitialiser mot de passe</h2>
<br>

<div class="container">
	<form action="<%=request.getContextPath()%>/Forgot" method="post">
	
		<div class="row">
			<div class="col-6 d-flex justify-content-end">
				<label>Pseudo :</label>
			</div>
			<div class="col-3">
				<input class="form-control" type="text" name="pseudo">
			</div>
		</div>
		<br>
		
		<div class="row">
			<div class="col-6 d-flex justify-content-end">
				<label>Nouveau mot de passe :</label>
			</div>
			<div class="col-3">
				<input class="form-control" type="password" name="nouveauMDP">
			</div>
		</div>
		<br>
		
		<div class="row">
			<div class="col-6 d-flex justify-content-end">
				<label>Confirmation :</label>
			</div>
			<div class="col-3">
				<input class="form-control" type="password" name="nouveauMDPConfirmation">
			</div>
		</div>
		<br>
	
		<div class="col-7 d-flex justify-content-end">
			<input class="btn btn-secondary" type="submit" value="Confirmer">
		</div>
		
	</form>
</div>

<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>

</body>
</html>