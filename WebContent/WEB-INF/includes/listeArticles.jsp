<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${empty listeArticlesVendu}">
		<p>Aucun article ne correspond à la demande actuelle</p>
</c:if>

<div class="container-liste">
	<div class="row">
		<c:if test="${sessionScope.utilisateur == null}">
			<c:forEach var="lArt" items="${listeArticlesVendu}">
				<c:set var="idUser" value="${lArt.noUtilisateur}"/>
				<c:set var="i" value="0"/>
					<div class="col-6 container-liste">
						<ul class="list-group-item" style="list-style: none;">
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
							  <c:if test="${(lArt.noUtilisateur == u.noUtilisateur) && (i == 0)}">
								  <li>
								  	Vendeur : ${u.pseudo}
								  </li>
							  <c:set var="i" value="1"/>
							  </c:if>
						  
						  </c:forEach> 
						</ul>
					</div>
			</c:forEach>
		</c:if>
	</div>
</div>

<c:if test="${sessionScope.utilisateur != null}">
<h3 class="Achats container-liste">Achats</h3>
	<div>
		<div class="row">
			<c:forEach var="lArt" items="${listeArticlesVendu}">
			<c:if test="${sessionScope.utilisateur.noUtilisateur !=  lArt.noUtilisateur}">
				<c:set var="idUser" value="${lArt.noUtilisateur}"/>
				<c:set var="i" value="0"/>
				<div class="col-6 container-liste">
					<ul class="list-group-item" style="list-style: none;">
					  <li> <a href="<%= request.getContextPath()%>/AffichEnchere?noArticle=${lArt.noArticleVendu}">${lArt.nomArticleVendu }</a></li>
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
					  	<h2>Liste de vendeur vide</h2>
					  </c:if>
					  <c:forEach var="u" items="${listeVendeurs}">
						  <c:set var="idArtUser" value="${u.noUtilisateur }"></c:set>
						  <c:if test="${(lArt.noUtilisateur == u.noUtilisateur) && (i == 0)}">
							  <li>
							  	Vendeur : <a href="<%=request.getContextPath()%>/AffichProfil?pseudo=${u.pseudo}">${u.pseudo}</a>
							  </li>
							  <c:set var="i" value="1"/>
						  </c:if>
					  
					  </c:forEach> 
					</ul>
				</div>
			</c:if>
			</c:forEach>
		</div>
	</div>
	<h3 class="MesVentes container-liste">Mes Ventes</h3>
	<div>
		<div class="row">
			<c:forEach var="lArt" items="${listeArticlesVendu}">
			<c:if test="${sessionScope.utilisateur.noUtilisateur ==  lArt.noUtilisateur}">
				<c:set var="idUser" value="${lArt.noUtilisateur}"/>
				<c:set var="i" value="0"/>
				<div class="col-6 container-liste">
					<ul class="list-group-item" style="list-style: none;">
					  <li> <a href="<%= request.getContextPath()%>/AffichEnchere?noArticle=${lArt.noArticleVendu}">${lArt.nomArticleVendu }</a></li>
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
					  	<h2>Liste de vendeur vide</h2>
					  </c:if>
					  <c:forEach var="u" items="${listeVendeurs}">
						  <c:set var="idArtUser" value="${u.noUtilisateur }"></c:set>
						  <c:if test="${(lArt.noUtilisateur == u.noUtilisateur) && (i == 0)}">
							  <li>
							  	Vendeur : <a href="<%=request.getContextPath()%>/AffichProfil?pseudo=${u.pseudo}">${u.pseudo}</a>
							  </li>
							  <c:set var="i" value="1"/>
						  </c:if>
					  
					  </c:forEach> 
					</ul>
				</div>
			</c:if>
			</c:forEach>
		</div>
	</div>
</c:if>