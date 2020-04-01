package fr.eni.javaee.trocencheres.dal;

import fr.eni.javaee.trocencheres.bo.Enchere;
import fr.eni.javaee.trocencheres.exception.BusinessException;

public interface EncheresDAO {
	

	
	//2006 faire une enchère
	public void updateEnchere(Enchere enchere) throws BusinessException;
	
	//2007 remporter une vente
	public Enchere selectEnchereByMeilleurOffre();
	
	//2009 afficher le détail d'une enchère
	public Enchere selectEnchereByNoArticleVendu(int noArticleVendu);
	

	
}
