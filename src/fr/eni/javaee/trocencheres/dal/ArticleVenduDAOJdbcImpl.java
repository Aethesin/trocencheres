package fr.eni.javaee.trocencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.trocencheres.bo.ArticleVendu;
import fr.eni.javaee.trocencheres.bo.Categorie;
import fr.eni.javaee.trocencheres.bo.Utilisateur;
import fr.eni.javaee.trocencheres.exception.BusinessException;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	private static final String INSERT_ARTICLE_VENDU = "insert into ARTICLE_VENDU(nomArticle, description,dateDebutEncheres, "
			+ "dateFinEncheres, miseAPrix, prixVente, "
			+ "noUtilisateur, noCategorie) values (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_ALL_ARTICLES = "select nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie"
			+ " from ARTICLES_VENDUS;";

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
				pstmt.setString(1, articleVendu.getNomArticleVendu());
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
					articleVendu.setNoArticleVendu(rs.getInt(1));
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
		List<ArticleVendu> listeArticlesVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection(); Statement smt = cnx.createStatement();){
			ResultSet rs = smt.executeQuery(SELECT_ALL_ARTICLES);
			ArticleVendu articleVendu = new ArticleVendu();
			while (rs.next()) {
				articleVendu = mappingArticles(rs);
				listeArticlesVendu.add(articleVendu);
			}
			return listeArticlesVendu;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private ArticleVendu mappingArticles(ResultSet rs){
		ArticleVendu articleVendu = new ArticleVendu();
		try {
			articleVendu.setNomArticleVendu(rs.getString("nom_article"));
			articleVendu.setDescription(rs.getString("description"));
			articleVendu.setMiseAPrix(rs.getInt("prix_initial"));
			articleVendu.setPrixVente(rs.getInt("prix_vente"));
			articleVendu.setNoUtilisateur(rs.getInt("no_utilisateur"));
			articleVendu.setNoCategorie(rs.getInt("no_categorie"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articleVendu;
	}

}
