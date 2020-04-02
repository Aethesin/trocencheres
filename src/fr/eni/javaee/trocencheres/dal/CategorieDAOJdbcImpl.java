/**
 * 
 */
package fr.eni.javaee.trocencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.javaee.trocencheres.bo.Categorie;
import fr.eni.javaee.trocencheres.exception.BusinessException;

/**
 * Classe en charge de
 * @author Yann
 * @version trocencheres - v1.0
 * @date 31 mars 2020
 */
public class CategorieDAOJdbcImpl implements CategorieDAO{
	
	private static final String SELECT_CATEGORIE_BY_ID = "SELECT no_categorie, libelle FROM CATEGORIES WHERE no_categorie = ?;";
	
	@Override
	public Categorie selectCategorieById(int idCategorie) throws BusinessException {
		Categorie categorie = new Categorie();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(SELECT_CATEGORIE_BY_ID)){
			psmt.setInt(1, idCategorie);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorie;
	}

}
