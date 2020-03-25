package fr.eni.javaee.trocencheres.bll;

import java.time.LocalDateTime;

import fr.eni.javaee.trocencheres.bo.ArticleVendu;
import fr.eni.javaee.trocencheres.dal.ArticleVenduDAO;
import fr.eni.javaee.trocencheres.dal.DAOFactory;
import fr.eni.javaee.trocencheres.exception.BusinessException;

public class ArticleVenduManager {
	private ArticleVenduDAO articleVenduDAO;
	
	public ArticleVenduManager() {
		this.setArticleVenduDAO(DAOFactory.getArticleVenduDAO());
		
	}

	public ArticleVendu insertArticleVendu(String nomArticleVendu, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, int prixVente, int noUtilisateur, int noCategorie)
			throws BusinessException {

		BusinessException businessException = new BusinessException();
		ArticleVendu articleVendu = new ArticleVendu(nomArticleVendu, description, dateDebutEncheres, dateFinEncheres,
				miseAPrix, prixVente, noUtilisateur, noCategorie);
		this.validerNomArticleVendu(articleVendu, businessException);
		this.validerDescription(articleVendu, businessException);
		this.validerDateDebutEncheres(articleVendu, businessException);
		this.validerDateFinEncheres(articleVendu, businessException);
		this.validerMiseAPrix(articleVendu, businessException);
		this.validerPrixVente(articleVendu, businessException);
		this.validerVendeur(articleVendu, businessException);
		this.validerCategorie(articleVendu, businessException);
		
		if(businessException.hasErreurs()) {
			throw businessException;
		}

		return articleVendu;
	}

	private void validerCategorie(ArticleVendu articleVendu, BusinessException businessException) {
		if (articleVendu.getNoCategorie() < 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CATEGORIE_ERREUR);
		}

	}

	private void validerVendeur(ArticleVendu articleVendu, BusinessException businessException) {
		if (articleVendu.getNoUtilisateur() < 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_VENDEUR_ERREUR);
		}

	}

	private void validerPrixVente(ArticleVendu articleVendu, BusinessException businessException) {
		if (articleVendu.getPrixVente() < articleVendu.getMiseAPrix()) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_PRIX_VENTE_ERREUR);
		}

	}

	private void validerMiseAPrix(ArticleVendu articleVendu, BusinessException businessException) {
		if (articleVendu.getMiseAPrix() < 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_MISE_A_PRIX_ERREUR);
		}

	}

	private void validerDateFinEncheres(ArticleVendu articleVendu, BusinessException businessException) {
		LocalDateTime now = LocalDateTime.now();
		if (articleVendu.getDateFinEncheres().isBefore(now) || articleVendu.getDateDebutEncheres() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_DATE_FIN_ENCHERES_ERREUR);

		}

	}

	private void validerDateDebutEncheres(ArticleVendu articleVendu, BusinessException businessException) {
		LocalDateTime now = LocalDateTime.now();
		if (articleVendu.getDateDebutEncheres().isBefore(now) || articleVendu.getDateDebutEncheres() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_DATE_DEBUT_ENCHERES_ERREUR);

		}

	}

	private void validerDescription(ArticleVendu articleVendu, BusinessException businessException) {
		if (articleVendu.getDescription() == null || articleVendu.getDescription().length() > 300) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_DESCRIPTION_ERREUR);
		}

	}

	private void validerNomArticleVendu(ArticleVendu articleVendu, BusinessException businessException) {
		if (articleVendu.getNomArticleVendu() == null || articleVendu.getNomArticleVendu().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_NOM_ARTICLE_ERREUR);
		}
	}

	public ArticleVenduDAO getArticleVenduDAO() {
		return articleVenduDAO;
	}

	public void setArticleVenduDAO(ArticleVenduDAO articleVenduDAO) {
		this.articleVenduDAO = articleVenduDAO;
	}


}