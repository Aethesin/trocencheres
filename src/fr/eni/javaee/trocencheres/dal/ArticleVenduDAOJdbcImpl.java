package fr.eni.javaee.trocencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.List;

import fr.eni.javaee.trocencheres.bo.ArticleVendu;
import fr.eni.javaee.trocencheres.bo.Categorie;
import fr.eni.javaee.trocencheres.bo.Utilisateur;
import fr.eni.javaee.trocencheres.exception.BusinessException;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	private static final String INSERT_ARTICLE_VENDU = "insert into ARTICLE_VENDU(nomArticle, description,dateDebutEncheres, "
			+ "dateFinEncheres, miseAPrix, prixVente, "
			+ "noUtilisateur, noCategorie) values (?, ?, ?, ?, ?, ?, ?, ?)";

	@Override
	public void insertArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		if (articleVendu == null) {
			BusinessException businesseException = new BusinessException();
			businesseException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businesseException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				Utilisateur utilisateur = new Utilisateur();
				Categorie categorie = new Categorie();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE_VENDU,
						PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, articleVendu.getnomArticleVendu());
				pstmt.setString(2, articleVendu.getDescription());
				pstmt.setString(3, articleVendu.getDateDebutEncheres().format(formatter));
				pstmt.setString(4, articleVendu.getDateFinEncheres().format(formatter));
				pstmt.setInt(5, articleVendu.getMiseAPrix());
				pstmt.setInt(6, articleVendu.getPrixVente());
				pstmt.setInt(7, utilisateur.getNoUtilisateur());
				pstmt.setInt(8, categorie.getNoCategorie());
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					articleVendu.setnoArticleVendu(rs.getInt(1));
				}
				rs.close();
				pstmt.close();
				cnx.commit();

			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}

	@Override
	public List<ArticleVendu> selectArticleVenduByCategorie(String categorie) throws BusinessException {

		return null;
	}

	@Override
	public List<ArticleVendu> selectArticleVenduByMotCle(String motCle) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleVendu> selectAllArticleVendu() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
