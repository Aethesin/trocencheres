package fr.eni.javaee.trocencheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.trocencheres.bo.Utilisateur;
import fr.eni.javaee.trocencheres.dal.DAOFactory;
import fr.eni.javaee.trocencheres.dal.UtilisateurDAO;
import fr.eni.javaee.trocencheres.exception.BusinessException;

public class UtilisateurManager {
	private UtilisateurDAO utilisateurDAO;
	
	public UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public Utilisateur getConnexion(String pseudo) throws BusinessException{
		Utilisateur user = null;
		user = utilisateurDAO.afficherUtilisateurPseudo(pseudo);
		return user;
	}
	
	public void getInscription(Utilisateur utilisateur) throws BusinessException{
		utilisateurDAO.insertUtilisateur(utilisateur);	
	}
	
	public Utilisateur selectUtilisateurById(int idUtilisateur) throws BusinessException{
		Utilisateur user = null;
		user = utilisateurDAO.selectUtilisateurById(idUtilisateur);
		return user;
	}
	
	public void modifUtilisateur(Utilisateur utilisateur) throws BusinessException{
		utilisateurDAO.updateUtilisateur(utilisateur);
	}
	
	public void supprUtilisateur(int noUtilisateur) throws BusinessException {
		utilisateurDAO.supprimerUtilisateur(noUtilisateur);
	}
	
	public List<Utilisateur> selectToutLeMonde() throws BusinessException{
		List<Utilisateur> listesUtilisateurs = new ArrayList<Utilisateur>();
		listesUtilisateurs = utilisateurDAO.selectToutLeMonde();
		return listesUtilisateurs;
	}
	
}
