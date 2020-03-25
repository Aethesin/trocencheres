package fr.eni.javaee.trocencheres.bll;

import java.time.LocalDateTime;

import fr.eni.javaee.trocencheres.bo.Encheres;
import fr.eni.javaee.trocencheres.dal.DAOFactory;
import fr.eni.javaee.trocencheres.dal.EncheresDAO;
import fr.eni.javaee.trocencheres.exception.BusinessException;

public class EncheresManager {
	private EncheresDAO encheresDAO;
	
	public EncheresManager() {
		this.encheresDAO = DAOFactory.getEncheresDAO();
	}
	
	public Encheres insertEncher(LocalDateTime dateEnchere, int montantEnchere, int noArticleVendu, int noUtilisateur) throws BusinessException{
		BusinessException businessException = new BusinessException();
		Encheres enchere = new Encheres(dateEnchere, montantEnchere, noArticleVendu, noUtilisateur);
		
		this.validerDateEnchere(enchere, businessException);
		this.validerMontantEnchere(encher, businessException);
		this.
		
		
		return enchere;
		
	}
	
	public void updateEnchere(Encheres enchere) throws BusinessException{
		encheresDAO.updateEnchere(enchere);
	}
	
	public Encheres selectEnchereByNoArticleVendu(int noArticleVendu) throws BusinessException{
		Encheres enchere = encheresDAO.selectEnchereByNoArticleVendu(noArticleVendu);
		return enchere;
		
	}
	
}
