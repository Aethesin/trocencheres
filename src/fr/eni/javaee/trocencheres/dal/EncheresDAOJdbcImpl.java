package fr.eni.javaee.trocencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import fr.eni.javaee.trocencheres.bo.ArticleVendu;
import fr.eni.javaee.trocencheres.bo.Enchere;
import fr.eni.javaee.trocencheres.bo.Retrait;
import fr.eni.javaee.trocencheres.bo.Utilisateur;
import fr.eni.javaee.trocencheres.exception.BusinessException;

public class EncheresDAOJdbcImpl implements EncheresDAO {
	

	private static final String UPDATE_ENCHERE = "UPDATE ENCHERES SET no_utilisateur=?, no_article=?, date_enchere=?, montant_enchere=?";
	private static final String SELECT_ENCHERE_BY_NO_ARTICLE = "SELECT a.nom_article, a.description, a.prix_initial, a.prix_vente, a.date_debut_encheres, "
			+ "a.date_fin_encheres, r.rue, r.code_postal, r.ville, u.pseudo "
			+ "FROM ARTICLES_VENDUS a INNER JOIN ENCHERES e on a.no_article = e.no_article "
			+ "INNER JOIN UTILISATEURS u on u.no_utilisateur = e.no_utilisateur "
			+ "INNER JOIN RETRAITS r on r.no_article = a.nom_article "
			+ "WHERE no_article = ? " 
			+ "ORDER BY a.prix_vente DESC";
	private static final String SELECT_ENCHERE_MEILLEUR_OFFRE = "SELECT TOP 1 e.no_utilisateur as noUtilEnch, e.no_article as noArtEnch, e.date_enchere as dateEnch, e.montant_enchere as montantEnch, "
			+ "u.pseudo as pseudoUtil "
			+ "FROM ENCHERES e "
			+ "INNER JOIN UTILISATEURS u ON u.no_utilisateur = e.no_utilisateur "
			+ "WHERE no_article = ? "
			+ "ORDER BY montant_enchere DESC";
	
	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES(?,?,?,?);";


	@Override
	public void updateEnchere(Enchere enchere) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				Utilisateur utilisateur = new Utilisateur();
				ArticleVendu articleVendu = new ArticleVendu();

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

	private void setParameter(PreparedStatement pstmt, Enchere enchere) throws BusinessException {
		Utilisateur utilisateur = new Utilisateur();
		ArticleVendu articleVendu = new ArticleVendu();
		LocalDateTime now = LocalDateTime.now();
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
			pstmt.setString(3, now.format(formatter));
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ENCHERE_TERMINEE);
			throw businessException;
		}

	}

	@Override
	public Enchere selectEnchereByNoArticleVendu(int noArticleVendu) throws BusinessException{
		Utilisateur vendeur = new Utilisateur();
		ArticleVendu articleVendu = new ArticleVendu();
		Retrait retrait = new Retrait();
		Enchere enchere = new Enchere();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERE_BY_NO_ARTICLE);) {
			pstmt.setInt(1, articleVendu.getNoArticleVendu());
			ResultSet rs = pstmt.executeQuery();
			articleVendu.setNomArticleVendu(rs.getString("nomArticle"));
			articleVendu.setDescription(rs.getString("description"));
			articleVendu.setPrixVente(rs.getInt("prixVente"));
			articleVendu.setMiseAPrix(rs.getInt("miseAPrix"));		
			articleVendu.setDateDebutEncheres(
					LocalDateTime.parse(rs.getTimestamp("date_debut_encheres").toLocalDateTime().toString()));
			articleVendu.setDateFinEncheres(
					LocalDateTime.parse(rs.getTimestamp("date_fin_encheres").toLocalDateTime().toString()));	
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
	public Enchere selectEnchereByMeilleurOffre(int noArticleVendu) throws BusinessException{
		Utilisateur vendeur = new Utilisateur();
		ArticleVendu articleVendu = new ArticleVendu();
		Retrait retrait = new Retrait();
		Enchere enchere = new Enchere();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERE_MEILLEUR_OFFRE);) {
			pstmt.setInt(1, noArticleVendu);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				vendeur.setNoUtilisateur(rs.getInt("noUtilEnch"));
				vendeur.setPseudo(rs.getString("pseudoUtil"));
				articleVendu.setNoArticleVendu(rs.getInt("noArtEnch"));
				enchere.setDateEnchere(LocalDateTime.parse(rs.getTimestamp("dateEnch").toLocalDateTime().toString()));
				enchere.setMontantEnchere(rs.getInt("montantEnch"));
				enchere.setArticleVendu(articleVendu);
				enchere.setUtilisateur(vendeur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ENCHERE_MEILLEUR_OFFRE_ECHEC);
		}

		return enchere;
	}

	@Override
	public void insertEnchere(Enchere enchere) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(INSERT_ENCHERE , PreparedStatement.RETURN_GENERATED_KEYS);){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm.ss");
			psmt.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
			psmt.setInt(2, enchere.getArticleVendu().getNoArticleVendu());
			psmt.setString(3, enchere.getDateEnchere().format(formatter));
			psmt.setInt(4, enchere.getMontantEnchere());
			psmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}


}
