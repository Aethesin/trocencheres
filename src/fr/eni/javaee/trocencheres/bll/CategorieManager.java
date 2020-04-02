/**
 * 
 */
package fr.eni.javaee.trocencheres.bll;

import fr.eni.javaee.trocencheres.bo.Categorie;
import fr.eni.javaee.trocencheres.dal.CategorieDAO;
import fr.eni.javaee.trocencheres.dal.DAOFactory;
import fr.eni.javaee.trocencheres.exception.BusinessException;

/**
 * Classe en charge de
 * @author Yann
 * @version trocencheres - v1.0
 * @date 31 mars 2020
 */
public class CategorieManager {

private CategorieDAO categorieDAO;
	
	public CategorieManager() {
		categorieDAO = DAOFactory.getCategorieDAO();
	}
	
	public Categorie selectCategorieById(int idCategorie) throws BusinessException{
		Categorie categorie = new Categorie();
		categorie = categorieDAO.selectCategorieById(idCategorie);
		return categorie;
	}
}
