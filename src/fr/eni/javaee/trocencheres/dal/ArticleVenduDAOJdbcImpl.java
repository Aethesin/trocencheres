package fr.eni.javaee.trocencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.trocencheres.bo.ArticleVendu;
import fr.eni.javaee.trocencheres.bo.Categorie;
import fr.eni.javaee.trocencheres.bo.Utilisateur;
import fr.eni.javaee.trocencheres.exception.BusinessException;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {
	private static BusinessException businessException;
	private static final String INSERT_ARTICLE_VENDU = "INSERT INTO ARTICLES_VENDUS"
			+ "(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, image_url, "
			+ "no_utilisateur, no_categorie) VALUES (?, ?, ?, ?, ?, ?, '', ?, ?)";

	private static final String SELECT_ARTICLES_BY_CATEGORIE = "SELECT a.no_article as noArticle, a.nom_article as nomArticle, a.description as descArticle, a.date_debut_encheres as debutEnch, a.date_fin_encheres as finEnch,"
			+ " a.prix_initial as prixInit, a.prix_vente as prixVente, a.no_utilisateur as articleNoU, a.no_categorie AS aNoCate, c.no_categorie AS cNoCate, c.libelle FROM articles_vendus a "
			+ "INNER JOIN categories c ON a.no_categorie = c.no_categorie"
			+ " WHERE c.libelle = ? ORDER BY a.date_fin_encheres ASC;";
	
	private static final String SELECT_ARTICLES_BY_CONTENU = "SELECT a.no_article as noArticle, a.nom_article as nomArticle, a.description as descArticle, a.date_debut_encheres as debutEnch,"
			+ " a.date_fin_encheres as finEnch, a.prix_initial as prixInit, a.prix_vente as prixVente, a.no_utilisateur as articleNoU, a.no_categorie AS aNoCate, c.no_categorie AS cNoCate,"
			+ " c.libelle FROM articles_vendus a INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie WHERE a.nom_article LIKE ?"
			+ " ORDER BY a.date_fin_encheres ASC;";
	
	private static final String SELECT_ARTICLES_BY_CATEGORIE_AND_CONTENU = "SELECT a.no_article as noArticle, a.nom_article as nomArticle, a.description as descArticle, a.date_debut_encheres as debutEnch,"
			+ " a.date_fin_encheres as finEnch, a.prix_initial as prixInit, a.prix_vente as prixVente, a.no_utilisateur as articleNoU, a.no_categorie AS aNoCate, c.no_categorie AS cNoCate, c.libelle "
			+ "FROM articles_vendus a INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie WHERE a.nom_article"
			+ " LIKE ? AND c.libelle = ? ORDER BY a.date_fin_encheres ASC;";
	
	private static final String SELECT_ALL_ARTICLES = "SELECT a.no_article as noArticle, a.nom_article as nomArticle, a.description as descArticle, a.date_debut_encheres as debutEnch, a.date_fin_encheres as finEnch,"
			+ " a.prix_initial as prixInit, a.prix_vente as prixVente, a.no_utilisateur as articleNoU, a.no_categorie AS aNoCate, c.no_categorie AS cNoCate, c.libelle FROM articles_vendus a "
			+ "INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie ORDER BY a.date_fin_encheres ASC;";
	
	private static final String SELECT_ARTICLE_BY_NO_ARTICLE = "SELECT a.no_article as noArticle, a.nom_article as nomArticle, a.description as descArticle, a.prix_initial as prixInit, a.prix_vente as prixVente, a.date_debut_encheres as debutEnch, "
			+ "a.date_fin_encheres as finEnch, a.no_utilisateur  as articleNoU, a.no_categorie as noCategorie, u.rue, u.code_postal, u.ville, u.pseudo "
			+ "FROM ARTICLES_VENDUS a "
			+ "INNER JOIN UTILISATEURS u on u.no_utilisateur = a.no_utilisateur " + "WHERE a.no_article = ?";

	private static final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS set nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, image_url = ?, prix_initial = ?, prix_vente = ?, no_utilisateur = ?, no_categorie = ? "
			+ "WHERE no_article = ?;";
	@Override
	public ArticleVendu insertArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm.ss");
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE_VENDU,
					PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, articleVendu.getNomArticleVendu());
			pstmt.setString(2, articleVendu.getDescription());
			pstmt.setString(3, articleVendu.getDateDebutEncheres().format(formatter));
			pstmt.setString(4, articleVendu.getDateFinEncheres().format(formatter));
			pstmt.setInt(5, articleVendu.getMiseAPrix());
			pstmt.setInt(6, articleVendu.getPrixVente());
			pstmt.setInt(7, articleVendu.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(8, articleVendu.getCategorie().getNoCategorie());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				articleVendu.setNoArticleVendu(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articleVendu;
	}

	@Override
	public List<ArticleVendu> selectArticleVenduByCategorie(String categorie) throws BusinessException {
		List<ArticleVendu> listeArticlesVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(SELECT_ARTICLES_BY_CATEGORIE)) {
			psmt.setString(1, categorie);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				listeArticlesVendu.add(mappingArticleVendu(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeArticlesVendu;
	}

	@Override
	public List<ArticleVendu> selectArticleVenduByMotCle(String motCle) throws BusinessException {
		List<ArticleVendu> listeArticlesVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(SELECT_ARTICLES_BY_CONTENU)) {
			psmt.setString(1, "%" + motCle + "%");
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				listeArticlesVendu.add(mappingArticleVendu(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeArticlesVendu;
	}

	public List<ArticleVendu> selectArticleVenduByMotCleAndCategorie(String motCle, String categorie)
			throws BusinessException {
		List<ArticleVendu> listeArticlesVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(SELECT_ARTICLES_BY_CATEGORIE_AND_CONTENU)) {
			psmt.setString(1, "%" + motCle + "%");
			psmt.setString(2, categorie);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				listeArticlesVendu.add(mappingArticleVendu(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeArticlesVendu;
	}

	@Override
	public List<ArticleVendu> selectAllArticleVendu() throws BusinessException {
		List<ArticleVendu> listeArticlesVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection(); Statement st = cnx.createStatement();) {
			ResultSet rs = st.executeQuery(SELECT_ALL_ARTICLES);
			ArticleVendu articleVendu = new ArticleVendu();
			Categorie categorie = new Categorie();
			while (rs.next()) {
				articleVendu = mappingArticleVendu(rs);
				listeArticlesVendu.add(articleVendu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeArticlesVendu;
	}

	private ArticleVendu mappingArticleVendu(ResultSet rs) throws SQLException {
		ArticleVendu articleVendu = new ArticleVendu();
		Utilisateur utilisateur = new Utilisateur();
		
		utilisateur.setNoUtilisateur(rs.getInt("articleNoU"));
		articleVendu.setNoArticleVendu(rs.getInt("noArticle"));
		articleVendu.setNomArticleVendu(rs.getString("nomArticle"));
		articleVendu.setDescription(rs.getString("descArticle"));
		articleVendu.setDateDebutEncheres(
				LocalDateTime.parse(rs.getTimestamp("debutEnch").toLocalDateTime().toString()));
		articleVendu.setDateFinEncheres(
				LocalDateTime.parse(rs.getTimestamp("finEnch").toLocalDateTime().toString()));
		articleVendu.setMiseAPrix(rs.getInt("prixInit"));
		articleVendu.setPrixVente(rs.getInt("prixVente"));
		articleVendu.setUtilisateur(utilisateur);
		return articleVendu;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see fr.eni.javaee.trocencheres.dal.ArticleVenduDAO#selectArticleVenduByID(int)
	 */
	@Override
	public ArticleVendu selectArticleVenduByID(int noArticleVendu) throws BusinessException {
		ArticleVendu articleVendu = new ArticleVendu();
		Categorie categorie = new Categorie();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLE_BY_NO_ARTICLE);) {
			pstmt.setInt(1, noArticleVendu);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				categorie.setNoCategorie(rs.getInt("noCategorie"));
				articleVendu = mappingArticleVendu(rs);
				articleVendu.setCategorie(categorie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_BY_NO_ARTICLE_ECHEC);
		}
		return articleVendu;
	}
	
	public void updateArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		Connection cnx = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm.ss");
		try {
			cnx= ConnectionProvider.getConnection();			
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ARTICLE);
			pstmt.setString(1, articleVendu.getNomArticleVendu());
			pstmt.setString(2, articleVendu.getDescription());
			pstmt.setString(3, articleVendu.getDateDebutEncheres().format(formatter));
			pstmt.setString(4, articleVendu.getDateFinEncheres().format(formatter));
			pstmt.setString(5, "");
			pstmt.setInt(6, articleVendu.getMiseAPrix());
			pstmt.setInt(7, articleVendu.getPrixVente());
			pstmt.setInt(8, articleVendu.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(9, articleVendu.getCategorie().getNoCategorie());
			pstmt.setInt(10, articleVendu.getNoArticleVendu());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
