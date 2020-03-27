package fr.eni.javaee.trocencheres.bll;

import java.time.LocalDateTime;

import fr.eni.javaee.trocencheres.bo.ArticleVendu;
import fr.eni.javaee.trocencheres.bo.Encheres;
import fr.eni.javaee.trocencheres.dal.DAOFactory;
import fr.eni.javaee.trocencheres.dal.EncheresDAO;
import fr.eni.javaee.trocencheres.exception.BusinessException;

public class EncheresManager {
	private EncheresDAO encheresDAO;
	
	public EncheresManager() {
		this.encheresDAO = DAOFactory.getEncheresDAO();
	}
	
	public Encheres insertEnchere(LocalDateTime dateEnchere, int montantEnchere, int noArticleVendu, int noUtilisateur) throws BusinessException{
		BusinessException businessException = new BusinessException();
		Encheres enchere = new Encheres(dateEnchere, montantEnchere, noArticleVendu, noUtilisateur);
		
		this.validerDateEnchere(enchere, businessException);
		this.validerMontantEnchere(enchere, businessException);
		this.validerNoArticleVendu(enchere, businessException);
		this.validerNoUtilisateur(enchere, businessException);
		
		if(businessException.hasErreurs()) {
			throw businessException;
		}
		
		return enchere;
		
	}
	
	private void validerNoUtilisateur(Encheres enchere, BusinessException businessException) {
		if (enchere.getNoUtilisateur() <=0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_NO_UTILISATEUR_ERREUR);
		}		
	}

	private void validerNoArticleVendu(Encheres enchere, BusinessException businessException) {
		if (enchere.getNoArticleVendu() <=0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_NO_ARTICLE_ERREUR);
		}		
	}

	private void validerMontantEnchere(Encheres enchere, BusinessException businessException) {
		ArticleVendu articleVendu = new ArticleVendu();
		if (enchere.getMontantEnchere() < articleVendu.getPrixVente()) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_MONTANT_ENCHERE_ERREUR);
		}
		
	}

	private void validerDateEnchere(Encheres enchere, BusinessException businessException) {
		ArticleVendu articleVendu = new ArticleVendu();
		if (enchere.getDateEnchere().isBefore(articleVendu.getDateDebutEncheres()) || enchere.getDateEnchere().isAfter(articleVendu.getDateFinEncheres()) || enchere.getDateEnchere() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_DATE_ENCHERE_ERREUR);

		}
		
	}

	public void updateEnchere(Encheres enchere) throws BusinessException{
		encheresDAO.updateEnchere(enchere);
	}
	
	public Encheres selectEnchereByNoArticleVendu(int noArticleVendu) throws BusinessException{
		Encheres enchere = encheresDAO.selectEnchereByNoArticleVendu(noArticleVendu);
		return enchere;
		
	}

}
