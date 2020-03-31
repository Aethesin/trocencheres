package fr.eni.javaee.trocencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import fr.eni.javaee.trocencheres.bo.ArticleVendu;
import fr.eni.javaee.trocencheres.bo.Encheres;
import fr.eni.javaee.trocencheres.bo.Retrait;
import fr.eni.javaee.trocencheres.bo.Utilisateur;
import fr.eni.javaee.trocencheres.exception.BusinessException;

public class EncheresDAOJdbcImpl implements EncheresDAO {
	

	private static final String UPDATE_ENCHERE = "update ENCHERES set no_utilisateur=?, no_article=?, date_enchere=?, montant_enchere=?";
	private static final String SELECT_ENCHERE_BY_NO_ARTICLE = "select a.nom_article, a.description, a.prix_initial, a.prix_vente, a.date_debut_encheres, "
			+ "a.date_fin_encheres, r.rue, r.code_postal, r.ville, u.pseudo "
			+ "from ARTICLES_VENDUS a inner join ENCHERES e on a.no_article = e.no_article "
			+ "inner join UTILISATEURS u on u.no_utilisateur = e.no_utilisateur "
			+ "inner join RETRAITS r on r.no_article = a.nom_article "
			+ "where no_article = ?" 
			+ "order by a.prix_vente desc";
	private static final String SELECT_ENCHERE_MEILLEUR_OFFRE = "select top 1 a.nom_article, a.description, a.prix_initial, a.prix_vente, "
			+ "a.date_fin_encheres, r.rue, r.code_postal, r.ville, u.pseudo "
			+ "from ARTICLES_VENDUS a inner join ENCHERES e on a.no_article = e.no_article "
			+ "inner join UTILISATEURS u on u.no_utilisateur = e.no_utilisateur "
			+ "inner join RETRAITS r on r.no_article = a.nom_article "
			+ "order by a.prix_vente desc";


	@Override
	public void updateEnchere(Encheres enchere) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				Utilisateur utilisateur = new Utilisateur();
				ArticleVendu articleVendu = new ArticleVendu();
				LocalDateTime now = LocalDateTime.now();

				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ENCHERE);
				setParameter(pstmt, enchere);
				pstmt.setInt(4, enchere.getMontantEnchere());

				articleVendu.setPrixVente(
						(articleVendu.getMiseAPrix() < enchere.getMontantEnchere() ? enchere.getMontantEnchere()
								: articleVendu.getMiseAPrix()));
				articleVendu.setPrixVente(
						(articleVendu.getPrixVente() < enchere.getMontantEnchere() ? enchere.getMontantEnchere()
								: articleVendu.getPrixVente()));

				if (utilisateur.getCredit() < articleVendu.getMiseAPrix()) {
					BusinessException businesseException = new BusinessException();
					businesseException.ajouterErreur(CodesResultatDAL.CREDIT_INSUFFISANT);
					throw businesseException;
				}

				utilisateur.setCredit(utilisateur.getCredit() - enchere.getMontantEnchere());

				pstmt.executeUpdate();

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

	private void setParameter(PreparedStatement pstmt, Encheres enchere) throws BusinessException {
		Utilisateur utilisateur = new Utilisateur();
		ArticleVendu articleVendu = new ArticleVendu();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try {
			pstmt.setInt(1, utilisateur.getNoUtilisateur());
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UTILISATEUR_INTROUVE);
			throw businessException;
		}
		try {
			pstmt.setInt(2, articleVendu.getNoArticleVendu());
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ARTICLE_VENDU_INTROUVE);
			throw businessException;
		}
		try {
			pstmt.setString(3, articleVendu.getDateDebutEncheres().format(formatter));
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ENCHERE_TERMINEE);
			throw businessException;
		}

	}

	@Override
	public Encheres selectEnchereByNoArticleVendu(int noArticleVendu) {
		Utilisateur vendeur = new Utilisateur();
		ArticleVendu articleVendu = new ArticleVendu();
		Retrait retrait = new Retrait();
		Encheres enchere = new Encheres();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERE_BY_NO_ARTICLE);) {
			pstmt.setInt(1, articleVendu.getNoArticleVendu());
			ResultSet rs = pstmt.executeQuery();
			articleVendu.setNomArticleVendu(rs.getString("nomArticle"));
			;
			articleVendu.setDescription(rs.getString("description"));
			articleVendu.setPrixVente(rs.getInt("prixVente"));
			articleVendu.setMiseAPrix(rs.getInt("miseAPrix"));
			articleVendu.setDateDebutEncheres(LocalDateTime.parse(rs.getDate("dateDebutEncheres").toString(),
					DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
			articleVendu.setDateFinEncheres(LocalDateTime.parse(rs.getDate("dateFinEncheres").toString(),
					DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
			retrait.setRue(rs.getString("rue"));
			retrait.setCodePostal(rs.getString("codePostal"));
			retrait.setVille(rs.getString("ville"));
			vendeur.setPseudo(rs.getString("pseudo"));

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_BY_NO_ARTICLE_ECHEC);
		}

		return enchere;
	}

	/**
	 * {@inheritDoc}
	 * @see fr.eni.javaee.trocencheres.dal.EncheresDAO#selectEnchereByMeilleurOffre()
	 */
	@Override
	public Encheres selectEnchereByMeilleurOffre() {
		Utilisateur vendeur = new Utilisateur();
		ArticleVendu articleVendu = new ArticleVendu();
		Retrait retrait = new Retrait();
		Encheres enchere = new Encheres();
		try (Connection cnx = ConnectionProvider.getConnection();
				Statement stmt = cnx.createStatement();) {
			ResultSet rs = stmt.executeQuery(SELECT_ENCHERE_MEILLEUR_OFFRE);
			articleVendu.setNomArticleVendu(rs.getString("nomArticle"));
			;
			articleVendu.setDescription(rs.getString("description"));
			articleVendu.setPrixVente(rs.getInt("prixVente"));
			articleVendu.setMiseAPrix(rs.getInt("miseAPrix"));
			articleVendu.setDateFinEncheres(LocalDateTime.parse(rs.getDate("dateFinEncheres").toString(),
					DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
			retrait.setRue(rs.getString("rue"));
			retrait.setCodePostal(rs.getString("codePostal"));
			retrait.setVille(rs.getString("ville"));
			vendeur.setPseudo(rs.getString("pseudo"));

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ENCHERE_MEILLEUR_OFFRE_ECHEC);
		}

		return enchere;
	}


}
