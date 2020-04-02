/**
 * 
 */
package fr.eni.javaee.trocencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< HEAD
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
=======
>>>>>>> branch 'master' of https://github.com/Aethesin/trocencheres

import fr.eni.javaee.trocencheres.bo.Categorie;
import fr.eni.javaee.trocencheres.exception.BusinessException;

/**
 * Classe en charge de
 * 
 * @author Yann
 * @version trocencheres - v1.0
 * @date 31 mars 2020
 */
<<<<<<< HEAD
public class CategorieDAOJdbcImpl implements CategorieDAO {

	private static final String INSERT_CATEGORIE = "INSERT INTO CATEGORIES (libelle) VALUES(?)";
	private static final String SELECT_ALL_CATEGORIE = "SELECT no_categorie, libelle FROM CATEGORIES ORDER BY no_categorie";

	/**
	 * {@inheritDoc}
	 * 
	 * @see fr.eni.javaee.trocencheres.dal.CategorieDAO#insertCategorie(fr.eni.javaee.trocencheres.bo.Categorie)
	 */
	@Override
	public void insertCategorie(Categorie categorie) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_CATEGORIE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, categorie.getLibelle());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				categorie.setNoCategorie(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see fr.eni.javaee.trocencheres.dal.CategorieDAO#selectAllCategorie()
	 */
	@Override
	public List<Categorie> selectAllCategorie() throws BusinessException {
		List<Categorie> listeCategorie = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection(); Statement st = cnx.createStatement();) {
			ResultSet rs = st.executeQuery(SELECT_ALL_CATEGORIE);
			Categorie categorie = new Categorie();
			while (rs.next()) {
				categorie = mappingCategorie(rs);
				listeCategorie.add(categorie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listeCategorie;
	}

	/**
	 * Méthode en charge de
	 * 
	 * @param rs
	 * @return
	 */
	private Categorie mappingCategorie(ResultSet rs) throws SQLException{
		Categorie categorie = new Categorie();
		categorie.setLibelle(rs.getString("libelle"));
=======
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
>>>>>>> branch 'master' of https://github.com/Aethesin/trocencheres
		return categorie;
	}

}
