/**
 * 
 */
package fr.eni.javaee.trocencheres.dal;


import java.util.List;
import fr.eni.javaee.trocencheres.bo.Categorie;
import fr.eni.javaee.trocencheres.exception.BusinessException;

/**
 * Classe en charge de
 * @author Yann
 * @version trocencheres - v1.0
 * @date 31 mars 2020
 */
public interface CategorieDAO {


	public void insertCategorie(Categorie categorie) throws BusinessException;

	public List<Categorie> selectAllCategorie() throws BusinessException;

	public Categorie selectCategorieById(int idCategorie) throws BusinessException;
}
