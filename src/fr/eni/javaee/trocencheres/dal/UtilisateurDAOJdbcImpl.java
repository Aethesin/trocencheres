package fr.eni.javaee.trocencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.javaee.trocencheres.bo.Utilisateur;
import fr.eni.javaee.trocencheres.exception.BusinessException;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	

	private static final String INSERT_UTILISATEUR = "INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, "
			+ "mot_de_passe, credit, administrateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT, 0)";
	private static final String SELECT_CONNEXION = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, "
			+ "credit, administrateur FROM UTILISATEURS WHERE pseudo = ?;";
	private static final String SELECT_UTILISATEURS = "SELECT no_utilisateur, pseudo FROM UTILISATEURS WHERE no_utilisateur = ?";
	private static final String UPDATE_UTILISATEUR =	"UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?,"
			+ " telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ? WHERE no_utilisateur = ?;";
	private static final String DELETE_UTILISATEUR = "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?;";
	
	@Override
	public void insertUtilisateur(Utilisateur utilisateur) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement psmt = cnx.prepareStatement(INSERT_UTILISATEUR , PreparedStatement.RETURN_GENERATED_KEYS);){
			psmt.setString(1, utilisateur.getPseudo());
			psmt.setString(2, utilisateur.getNom());
			psmt.setString(3, utilisateur.getPrenom());
			psmt.setString(4, utilisateur.getEmail());
			psmt.setString(5, utilisateur.getTelephone());
			psmt.setString(6, utilisateur.getRue());
			psmt.setString(7, utilisateur.getCodePostal());
			psmt.setString(8, utilisateur.getVille());
			psmt.setString(9, utilisateur.getMotDePasse());
			int nbEnregistrement = psmt.executeUpdate();
			if(nbEnregistrement == 1) {
				ResultSet rs = psmt.getGeneratedKeys();
				if(rs.next()) {
					utilisateur.setNoUtilisateur(rs.getInt(1));;
				}
				rs.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	public Utilisateur afficherUtilisateurPseudo(String pseudo) throws BusinessException {
		Utilisateur utilisateur = new Utilisateur();
		
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_CONNEXION);){
			pstmt.setString(1, pseudo);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));	
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdministrateur(rs.getShort("administrateur"));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}
	
	public Utilisateur selectVendeur(int idUtilisateur) throws BusinessException{
		Utilisateur utilisateur = new Utilisateur();
		
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_UTILISATEURS);){
			pstmt.setInt(1, idUtilisateur);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){				
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));			
				utilisateur.setPseudo(rs.getString("pseudo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}


	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws BusinessException {
		Connection cnx = null;
		try {
			cnx= ConnectionProvider.getConnection();
			cnx.setAutoCommit(false);
			
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_UTILISATEUR);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2,utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5,utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8,utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getNoUtilisateur());
			pstmt.executeUpdate();
			pstmt.close();
			cnx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void supprimerUtilisateur(int noUtilisateur) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_UTILISATEUR);){
			pstmt.setInt(1, noUtilisateur);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
