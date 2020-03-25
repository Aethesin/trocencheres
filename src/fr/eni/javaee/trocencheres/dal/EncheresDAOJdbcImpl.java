package fr.eni.javaee.trocencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import fr.eni.javaee.trocencheres.bo.ArticleVendu;
import fr.eni.javaee.trocencheres.bo.Encheres;
import fr.eni.javaee.trocencheres.bo.Retrait;
import fr.eni.javaee.trocencheres.bo.Utilisateur;
import fr.eni.javaee.trocencheres.exception.BusinessException;

public class EncheresDAOJdbcImpl implements EncheresDAO {
	
	private static final String UPDATE_ENCHERE = "update ENCHERE set noUtilisateur=? noArticle=?, DateEnchere=? montantEnchere=?";
	private static final String SELECT_ENCHERE="select a.nomArticle, a.description, a.prixVente, a.miseAPrix, a.dateDebutEncheres, "
			+ "a.dateFinEncheres, r.rue, r.codePostal, r.ville, u.pseudo "
			+ "from ARTICLE_VENDU a inner join ENCHERE e on a.noArticle = e.noArticle "
			+ "inner join UTILISATEUR u on u.noUtilisateur = e.noUtilisateur "
			+ "inner join RETRAIT r on r.noArticle = a.nomArticle "
			+ "where e.noArticle = ?";

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
				
				articleVendu.setPrixVente((articleVendu.getMiseAPrix()<enchere.getMontantEnchere()?enchere.getMontantEnchere():articleVendu.getMiseAPrix()));
				articleVendu.setPrixVente((articleVendu.getPrixVente()<enchere.getMontantEnchere()?enchere.getMontantEnchere():articleVendu.getPrixVente()));

				
				
				if(utilisateur.getCredit()<articleVendu.getMiseAPrix())
				{
					BusinessException businesseException = new BusinessException();
					businesseException.ajouterErreur(CodesResultatDAL.CREDIT_INSUFFISANT);
					throw businesseException;
				}
				
				utilisateur.setCredit(utilisateur.getCredit()-enchere.getMontantEnchere());
				
				if (enchere.getMontantEnchere()<articleVendu.getPrixVente() && (!articleVendu.getDateFinEncheres().isAfter(now))) 
				{
					//recréditer l'utilisateur comme il ne remporte pas l'enchère
					utilisateur.setCredit(utilisateur.getCredit()+enchere.getMontantEnchere());
					System.out.println("Vous n'avez pas remporté l'enchère de l'article "+articleVendu.getnomArticleVendu()+", nous vous remboursons votre mise de "+enchere.getMontantEnchere()+" points.");
				}
					
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
			pstmt.setInt(2, articleVendu.getnoArticleVendu());
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
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERE);){
			pstmt.setInt(1, noArticleVendu);
			ResultSet rs = pstmt.executeQuery();
			articleVendu.setnomArticleVendu(rs.getString("nomArticle"));;
			articleVendu.setDescription(rs.getString("description"));
			articleVendu.setPrixVente(rs.getInt("prixVente"));
			articleVendu.setMiseAPrix(rs.getInt("miseAPrix"));
			articleVendu.setDateDebutEncheres(LocalDateTime.parse(rs.getDate("dateDebutEncheres").toString(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
			articleVendu.setDateFinEncheres(LocalDateTime.parse(rs.getDate("dateFinEncheres").toString(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
			retrait.setRue(rs.getString("rue"));
			retrait.setCodePostal(rs.getString("codePostal"));
			retrait.setVille(rs.getString("ville"));
			vendeur.setPseudo(rs.getString("pseudo"));	
			
		}catch(SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_BY_NOARTICLE_ECHEC);
		}
		
		return enchere;
	}

}
