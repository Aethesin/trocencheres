<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:if test="${empty listeArticlesVendu}">
		<p>Aucun article ne correspond à la demande actuelle</p>
	</c:if>

<c:forEach var="lArt" items="${listeArticlesVendu}">
<c:set var="idUser" value="${lArt.noUtilisateur}"/>
	<ul>
	  <li> ${lArt.nomArticleVendu } </li>
	  <c:if test="${lArt.prixVente == 0}">
		  <li>
		  	Prix : ${lArt.miseAPrix }
		  </li>
	  </c:if>
	  <c:if test="${lArt.prixVente != 0}">
		  <li>
		  	Prix : ${lArt.prixVente }
		  </li>
	  </c:if>
	  <li>
	  	Fin de l'enchère : ${lArt.dateFinEncheres.toString() }
	  </li>
	  
	  <c:if test="${empty listeVendeurs}">
	  	<h2>PROBLEME</h2>
	  </c:if>
	  <c:forEach var="u" items="${listeVendeurs}">
		  <c:set var="idArtUser" value="${u.noUtilisateur }"></c:set>
		  <c:if test="${lArt.noUtilisateur == u.noUtilisateur}">
			  <li>
			  	Vendeur : ${u.pseudo}
			  </li>
		  </c:if>
	  
	  </c:forEach>
	  
	  
	</ul>

	
</c:forEach>