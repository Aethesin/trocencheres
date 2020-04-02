/**
 * 
 */
package fr.eni.javaee.trocencheres.dal;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> branch 'master' of https://github.com/Aethesin/trocencheres
import fr.eni.javaee.trocencheres.bo.Categorie;
import fr.eni.javaee.trocencheres.exception.BusinessException;

/**
 * Classe en charge de
 * @author Yann
 * @version trocencheres - v1.0
 * @date 31 mars 2020
 */
public interface CategorieDAO {
<<<<<<< HEAD

	public void insertCategorie(Categorie categorie) throws BusinessException;

	public List<Categorie> selectAllCategorie() throws BusinessException;


=======
	
	public Categorie selectCategorieById(int idCategorie) throws BusinessException;
>>>>>>> branch 'master' of https://github.com/Aethesin/trocencheres
}
