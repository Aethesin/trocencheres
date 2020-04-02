package fr.eni.javaee.trocencheres.bll;

import fr.eni.javaee.trocencheres.bo.ArticleVendu;
import fr.eni.javaee.trocencheres.bo.Enchere;
import fr.eni.javaee.trocencheres.dal.DAOFactory;
import fr.eni.javaee.trocencheres.dal.EncheresDAO;
import fr.eni.javaee.trocencheres.exception.BusinessException;

public class EncheresManager {
	private EncheresDAO encheresDAO;
	
	public EncheresManager() {
		this.encheresDAO = DAOFactory.getEncheresDAO();
	}
	
	public void insertEnchere(Enchere enchere) throws BusinessException{
		BusinessException businessException = new BusinessException();
		
		//this.validerDateEnchere(enchere, businessException);
		this.validerMontantEnchere(enchere, businessException);
		this.validerNoArticleVendu(enchere, businessException);
		this.validerNoUtilisateur(enchere, businessException);
		
		if(businessException.hasErreurs()) {
			throw businessException;
		}
		
		encheresDAO.insertEnchere(enchere);
		
	}
	
	private void validerNoUtilisateur(Enchere enchere, BusinessException businessException) {
		if (enchere.getUtilisateur().getNoUtilisateur() <=0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_NO_UTILISATEUR_ERREUR);
		}		
	}

	private void validerNoArticleVendu(Enchere enchere, BusinessException businessException) {
		if (enchere.getArticleVendu().getNoArticleVendu() <=0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_NO_ARTICLE_ERREUR);
		}		
	}

	private void validerMontantEnchere(Enchere enchere, BusinessException businessException) {
		ArticleVendu articleVendu = new ArticleVendu();
		if (enchere.getMontantEnchere() < articleVendu.getPrixVente()) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_MONTANT_ENCHERE_ERREUR);
		}
		
	}

//	private void validerDateEnchere(Enchere enchere, BusinessException businessException) {
//		ArticleVendu articleVendu = new ArticleVendu();
//		if (enchere.getDateEnchere().isBefore(articleVendu.getDateDebutEncheres()) || enchere.getDateEnchere().isAfter(articleVendu.getDateFinEncheres()) || enchere.getDateEnchere() == null) {
//			businessException.ajouterErreur(CodesResultatBLL.REGLE_DATE_ENCHERE_ERREUR);
//
//		}
//		
//	}

	public void updateEnchere(Enchere enchere) throws BusinessException{
		encheresDAO.updateEnchere(enchere);
	}
	
	public Enchere selectEnchereByNoArticleVendu(int noArticleVendu) throws BusinessException{
		Enchere enchere = encheresDAO.selectEnchereByNoArticleVendu(noArticleVendu);
		return enchere;
		
	}

	public Enchere selectEnchereByMeilleurOffre(int noArticleVendu) throws BusinessException{
		Enchere enchere = encheresDAO.selectEnchereByMeilleurOffre(noArticleVendu);
		return enchere;
		
	}
}
