package fr.eni.javaee.trocencheres.bll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.trocencheres.bo.ArticleVendu;
import fr.eni.javaee.trocencheres.dal.ArticleVenduDAO;
import fr.eni.javaee.trocencheres.dal.DAOFactory;
import fr.eni.javaee.trocencheres.exception.BusinessException;

/**
 * Classe en charge de
 * @author Yann
 * @version trocencheres - v1.0
 * @date 1 avr. 2020
 */
/**
 * Classe en charge de
 * 
 * @author Yann
 * @version trocencheres - v1.0
 * @date 1 avr. 2020
 */
public class ArticleVenduManager {
	private ArticleVenduDAO articleVenduDAO;

	public ArticleVenduManager() {
		articleVenduDAO = DAOFactory.getArticleVenduDAO();
	}

	/**
	 * Méthode en charge de
	 * 
	 * @param nomArticleVendu
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param noUtilisateur
	 * @param noCategorie
	 */
	public ArticleVendu insertArticleVendu(ArticleVendu articleVendu) throws BusinessException {
//		BusinessException businessException = new BusinessException();
//		this.validerNomArticleVendu(articleVendu, businessException);
//		this.validerDescription(articleVendu, businessException);
//		this.validerDateDebutEncheres(articleVendu, businessException);
//		this.validerDateFinEncheres(articleVendu, businessException);
//		this.validerMiseAPrix(articleVendu, businessException);
//		this.validerPrixVente(articleVendu, businessException);
//		this.validerVendeur(articleVendu, businessException);
//		this.validerCategorie(articleVendu, businessException);
		articleVendu = articleVenduDAO.insertArticleVendu(articleVendu);
//		if (businessException.hasErreurs()) {
//			throw businessException;
//		}
		return articleVendu;
	}

	private void validerCategorie(ArticleVendu articleVendu, BusinessException businessException) {
		if (articleVendu.getCategorie().getNoCategorie() < 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CATEGORIE_ERREUR);
		}

	}

	private void validerVendeur(ArticleVendu articleVendu, BusinessException businessException) {
		if (articleVendu.getUtilisateur().getNoUtilisateur() < 0) {
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
		if (articleVendu.getDescription() == null || articleVendu.getDescription().length() > 300
				|| articleVendu.getDescription().trim().length() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_DESCRIPTION_ERREUR);
		}

	}

	private void validerNomArticleVendu(ArticleVendu articleVendu, BusinessException businessException) {
		if (articleVendu.getNomArticleVendu() == null || articleVendu.getNomArticleVendu().length() > 30
				|| articleVendu.getNomArticleVendu().trim().length() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_NOM_ARTICLE_ERREUR);
		}
	}

	public ArticleVenduDAO getArticleVenduDAO() {
		return articleVenduDAO;
	}

	public void setArticleVenduDAO(ArticleVenduDAO articleVenduDAO) {
		this.articleVenduDAO = articleVenduDAO;
	}

	public List<ArticleVendu> selectAllArticleVendu() throws BusinessException {
		List<ArticleVendu> listeArticlesVendu = new ArrayList<ArticleVendu>();
		listeArticlesVendu = articleVenduDAO.selectAllArticleVendu();
		return listeArticlesVendu;
	}

	public List<ArticleVendu> selectArticleVenduByCategorie(String categorie) throws BusinessException {
		List<ArticleVendu> listeArticlesVendu = new ArrayList<ArticleVendu>();
		listeArticlesVendu = articleVenduDAO.selectArticleVenduByCategorie(categorie);
		return listeArticlesVendu;
	}

	public List<ArticleVendu> selectArticleVenduByMotCle(String motCle) throws BusinessException {
		List<ArticleVendu> listeArticlesVendu = new ArrayList<ArticleVendu>();
		listeArticlesVendu = articleVenduDAO.selectArticleVenduByMotCle(motCle);
		return listeArticlesVendu;
	}

	public List<ArticleVendu> selectArticleVenduByMotCleAndCategorie(String motCle, String categorie)
			throws BusinessException {
		List<ArticleVendu> listeArticlesVendu = new ArrayList<ArticleVendu>();
		listeArticlesVendu = articleVenduDAO.selectArticleVenduByMotCleAndCategorie(motCle, categorie);
		return listeArticlesVendu;
	}

	public ArticleVendu selectArticleVenduByID(int noArticleVendu) throws BusinessException {
		ArticleVendu articleVendu = new ArticleVendu();
		articleVendu = articleVenduDAO.selectArticleVenduByID(noArticleVendu);
		return articleVendu;

	}
	
	public void updateArticleVendu(ArticleVendu articleVendu) throws BusinessException{
		articleVenduDAO.updateArticleVendu(articleVendu);
	}
}