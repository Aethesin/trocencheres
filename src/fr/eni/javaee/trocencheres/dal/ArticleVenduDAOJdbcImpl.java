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

	private static final String INSERT_ARTICLE_VENDU = "insert into ARTICLES_VENDUS"
			+ "(no_article, nom_article, description,date_debut_encheres, "
			+ "date_fin_encheres, prix_initial, prix_vente, "
			+ "no_utilisateur, no_categorie) values (?, ?, ?, ?, ?, ?, ?, ?,?)";
	
	private static final String SELECT_ARTICLES_BY_CATEGORIE = "select no_article, nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ " prix_initial, prix_vente, no_utilisateur, a.no_categorie as aNoCate, c.no_categorie as cNoCate, c.libelle from articles_vendus a "
			+ "inner join categories c ON a.no_categorie = c.no_categorie"
			+ " WHERE c.libelle = ? ORDER BY date_fin_encheres ASC;";
	private static final String SELECT_ARTICLES_BY_CONTENU = "select no_article, nom_article, description, date_debut_encheres,"
			+ " date_fin_encheres, prix_initial, prix_vente, no_utilisateur, a.no_categorie as aNoCate, c.no_categorie as cNoCate,"
			+ " c.libelle from articles_vendus a inner join CATEGORIES c ON a.no_categorie = c.no_categorie WHERE a.nom_article LIKE ?"
			+ " ORDER BY date_fin_encheres ASC;";
	private static final String SELECT_ARTICLES_BY_CATEGORIE_AND_CONTENU = "select no_article, nom_article, description, date_debut_encheres,"
			+ " date_fin_encheres, prix_initial, prix_vente, no_utilisateur, a.no_categorie as aNoCate, c.no_categorie as cNoCate, c.libelle "
			+ "from articles_vendus a inner join CATEGORIES c ON a.no_categorie = c.no_categorie WHERE a.nom_article"
			+ " LIKE ? AND c.libelle = ? ORDER BY date_fin_encheres ASC;";
	private static final String SELECT_ALL_ARTICLES = "select no_article, nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ " prix_initial, prix_vente, no_utilisateur, a.no_categorie as aNoCate, c.no_categorie as cNoCate, c.libelle from articles_vendus a "
			+ "inner join CATEGORIES c ON a.no_categorie = c.no_categorie ORDER BY date_fin_encheres ASC;";

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
				pstmt.setInt(1, articleVendu.getNoArticleVendu());
				pstmt.setString(2, articleVendu.getNomArticleVendu());
				pstmt.setString(3, articleVendu.getDescription());
				pstmt.setString(4, articleVendu.getDateDebutEncheres().format(formatter));
				pstmt.setString(5, articleVendu.getDateFinEncheres().format(formatter));
				pstmt.setInt(6, articleVendu.getMiseAPrix());
				pstmt.setInt(7, articleVendu.getPrixVente());
				pstmt.setInt(8, utilisateur.getNoUtilisateur());
				pstmt.setInt(9, categorie.getNoCategorie());
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
		List<ArticleVendu> listeArticlesVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(SELECT_ARTICLES_BY_CATEGORIE)){
			psmt.setString(1, categorie);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				listeArticlesVendu.add(mappingArticleVendu(rs));
			}
			return listeArticlesVendu;
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return null;
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
			return listeArticlesVendu;
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return null;
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
			return listeArticlesVendu;
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return null;
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
			return listeArticlesVendu;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeArticlesVendu;
	}
	
	private ArticleVendu mappingArticleVendu(ResultSet rs) throws SQLException{
		ArticleVendu articleVendu = new ArticleVendu();
		articleVendu.setNoArticleVendu(rs.getInt("no_article"));
		articleVendu.setNomArticleVendu(rs.getString("nom_article"));
		articleVendu.setDescription(rs.getString("description"));
		articleVendu.setDateDebutEncheres(LocalDateTime.parse(rs.getTimestamp("date_debut_encheres").toLocalDateTime().toString()));
		articleVendu.setDateFinEncheres(LocalDateTime.parse(rs.getTimestamp("date_fin_encheres").toLocalDateTime().toString()));
		articleVendu.setMiseAPrix(rs.getInt("prix_initial"));
		articleVendu.setPrixVente(rs.getInt("prix_vente"));
		articleVendu.setNoUtilisateur(rs.getInt("no_utilisateur"));
		articleVendu.setNoCategorie(rs.getInt("aNoCate"));
		return articleVendu;
	}
	
	private Categorie mappingCategorie(ResultSet rs) throws SQLException{
		Categorie categorie = new Categorie();
		categorie.setNoCategorie(rs.getInt("cNoCate"));
		categorie.setLibelle(rs.getString("libelle"));
		return categorie;
	}

}
