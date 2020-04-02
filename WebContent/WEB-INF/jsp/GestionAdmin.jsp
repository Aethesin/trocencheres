<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestion des Utilisateurs</title>
</head>
<body>

<jsp:include page="/WEB-INF/includes/header.jsp"></jsp:include>

<div class="container formulaire">
<table class="table table-striped">
	<thead>
	    <tr>
	    	<th scope="col">N°</th>
	      	<th scope="col">Pseudo</th>
	      	<th scope="col">Nom</th>
	      	<th scope="col">Prénom</th>
	      	<th scope="col">Email</th>
	      	<th scope="col">Téléphone</th>
	      	<th scope="col">Rue</th>
	      	<th scope="col">Code Postal</th>
	      	<th scope="col">Crédit</th>
	      	<th scope="col">Statut</th>
	      	<th scope="col">Actions</th>
	    </tr>
	</thead>
	<tbody>
		<c:set var="compteur" value="0"></c:set>
		<c:forEach var="user" items="${listeUtilisateurs}" varStatus="loop">
			<tr <c:if test="${user.statut == 1 }">class="table-primary"</c:if> <c:if test="${user.statut == 2 }">class="table-danger"</c:if>>
				<td>${loop.index}</td>
				<td scope="col">${user.pseudo }</td>
		      	<td scope="col">${user.nom }</td>
		      	<td scope="col">${user.prenom }</td>
		      	<td scope="col">${user.email }</td>
		      	<td scope="col">${user.telephone }</td>
		      	<td scope="col">${user.rue }</td>
		      	<td scope="col">${user.codePostal }</td>
		      	<td scope="col">${user.credit }</td>
		      	<td scope="col"><c:if test="${user.statut == 0 }">Utilisateur</c:if><c:if test="${user.statut == 1 }">Administrateur</c:if><c:if test="${user.statut == 2 }">Désactivé</c:if></td>
		      	<td>
		      		<c:if test="${user.statut != 1 }">
		      			<c:if test="${user.statut == 0 }">
				      		<form action="<%=request.getContextPath()%>/DesactiverCompte?idUtilisateur=${user.noUtilisateur}" method="post">
				      			<input type="submit" value ="Désactiver compte" class="btn btn-primary">
				      		</form>
			      		</c:if>
		      			<c:if test="${user.statut == 2 }">
				      		<form action="<%=request.getContextPath()%>/ActiverCompte?idUtilisateur=${user.noUtilisateur}" method="post">
				      			<input type="submit" value ="Réactiver compte" class="btn btn-primary">
				      		</form>
			      		</c:if>
				      		<form action="<%=request.getContextPath()%>/SupprimProfil?idUtilisateur=${user.noUtilisateur}" method="post">
				      			<input type="submit" value ="Supprimer compte" class="btn btn-danger">
				      		</form>
		      		</c:if>
		      	</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>

<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>

</body>
</html>