/**
 * 
 */
package fr.eni.javaee.trocencheres.dal;

import fr.eni.javaee.trocencheres.bo.Categorie;
import fr.eni.javaee.trocencheres.exception.BusinessException;

/**
 * Classe en charge de
 * @author Yann
 * @version trocencheres - v1.0
 * @date 31 mars 2020
 */
public interface CategorieDAO {
	
	public Categorie selectCategorieById(int idCategorie) throws BusinessException;
}
