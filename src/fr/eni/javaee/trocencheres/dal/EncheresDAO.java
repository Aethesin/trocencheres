package fr.eni.javaee.trocencheres.dal;

import fr.eni.javaee.trocencheres.bo.Encheres;
import fr.eni.javaee.trocencheres.exception.BusinessException;

public interface EncheresDAO {
	
	//2006 faire une enchère
	//2007 remporter une vente
	public void updateEnchere(Encheres enchere) throws BusinessException;
	
	//2009 afficher le détail d'une enchère
	public Encheres selectEnchereByNoArticleVendu(int noArticleVendu);
	
}
