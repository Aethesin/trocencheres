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

	private static final String INSERT_ARTICLE_VENDU = "INSERT INTO ARTICLES_VENDUS"
			+ "(nom_article, description,date_debut_encheres, "
			+ "date_fin_encheres, prix_initial, prix_vente, "
			+ "no_utilisateur, no_categorie) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
	
	private static final String SELECT_ARTICLES_BY_CATEGORIE = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ " prix_initial, prix_vente, no_utilisateur, a.no_categorie AS aNoCate, c.no_categorie AS cNoCate, c.libelle FROM articles_vendus a "
			+ "INNER JOIN categories c ON a.no_categorie = c.no_categorie"
			+ " WHERE c.libelle = ? ORDER BY date_fin_encheres ASC;";
	private static final String SELECT_ARTICLES_BY_CONTENU = "SELECT no_article, nom_article, description, date_debut_encheres,"
			+ " date_fin_encheres, prix_initial, prix_vente, no_utilisateur, a.no_categorie AS aNoCate, c.no_categorie AS cNoCate,"
			+ " c.libelle FROM articles_vendus a INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie WHERE a.nom_article LIKE ?"
			+ " ORDER BY date_fin_encheres ASC;";
	private static final String SELECT_ARTICLES_BY_CATEGORIE_AND_CONTENU = "SELECT no_article, nom_article, description, date_debut_encheres,"
			+ " date_fin_encheres, prix_initial, prix_vente, no_utilisateur, a.no_categorie AS aNoCate, c.no_categorie AS cNoCate, c.libelle "
			+ "FROM articles_vendus a INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie WHERE a.nom_article"
			+ " LIKE ? AND c.libelle = ? ORDER BY date_fin_encheres ASC;";
	private static final String SELECT_ALL_ARTICLES = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ " prix_initial, prix_vente, no_utilisateur, a.no_categorie AS aNoCate, c.no_categorie AS cNoCate, c.libelle FROM articles_vendus a "
			+ "INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie ORDER BY date_fin_encheres ASC;";

	@Override
	public void insertArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ArticleVendu> selectArticleVenduByCategorie(String categorie) throws BusinessException {
		List<ArticleVendu> listeArticlesVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(SELECT_ARTICLES_BY_CATEGORIE)){
			psmt.setString(1, categorie);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
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
				PreparedStatement psmt = cnx.prepareStatement(SELECT_ARTICLES_BY_CONTENU)){
			psmt.setString(1, "%"+motCle+"%");
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				listeArticlesVendu.add(mappingArticleVendu(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeArticlesVendu;
	}
	
	public List<ArticleVendu> selectArticleVenduByMotCleAndCategorie(String motCle, String categorie) throws BusinessException {
		List<ArticleVendu> listeArticlesVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(SELECT_ARTICLES_BY_CATEGORIE_AND_CONTENU)){
			psmt.setString(1, "%"+motCle+"%");
			psmt.setString(2, categorie);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
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
		try (Connection cnx = ConnectionProvider.getConnection(); 
				Statement st = cnx.createStatement();){
			ResultSet rs = st.executeQuery(SELECT_ALL_ARTICLES);
			ArticleVendu articleVendu = new ArticleVendu();
			Categorie categorie = new Categorie();
			while(rs.next()){
				articleVendu = mappingArticleVendu(rs);
				listeArticlesVendu.add(articleVendu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeArticlesVendu;
	}
	
	private ArticleVendu mappingArticleVendu(ResultSet rs) throws SQLException{
		ArticleVendu articleVendu = new ArticleVendu();
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
		articleVendu.setNoArticleVendu(rs.getInt("no_article"));
		articleVendu.setNomArticleVendu(rs.getString("nom_article"));
		articleVendu.setDescription(rs.getString("description"));
		articleVendu.setDateDebutEncheres(LocalDateTime.parse(rs.getTimestamp("date_debut_encheres").toLocalDateTime().toString()));
		articleVendu.setDateFinEncheres(LocalDateTime.parse(rs.getTimestamp("date_fin_encheres").toLocalDateTime().toString()));
		articleVendu.setMiseAPrix(rs.getInt("prix_initial"));
		articleVendu.setPrixVente(rs.getInt("prix_vente"));
		articleVendu.setUtilisateur(utilisateur);
		return articleVendu;
	}
	
	private Categorie mappingCategorie(ResultSet rs) throws SQLException{
		Categorie categorie = new Categorie();
		categorie.setNoCategorie(rs.getInt("cNoCate"));
		categorie.setLibelle(rs.getString("libelle"));
		return categorie;
	}

}
