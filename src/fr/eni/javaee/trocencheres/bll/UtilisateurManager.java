package fr.eni.javaee.trocencheres.bll;

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
	
	public Utilisateur selectVendeurs(int idUtilisateur) throws BusinessException{
		Utilisateur user = null;
		user = utilisateurDAO.selectVendeur(idUtilisateur);
		return user;
	}
	
	public void modifUtilisateur(Utilisateur utilisateur) throws BusinessException{
		utilisateurDAO.updateUtilisateur(utilisateur);
	}
	
	public void supprUtilisateur(int noUtilisateur) throws BusinessException {
		utilisateurDAO.supprimerUtilisateur(noUtilisateur);
	}
	
}
