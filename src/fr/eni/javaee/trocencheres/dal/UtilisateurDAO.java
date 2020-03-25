package fr.eni.javaee.trocencheres.dal;

import fr.eni.javaee.trocencheres.bo.Utilisateur;
import fr.eni.javaee.trocencheres.exception.BusinessException;

public interface UtilisateurDAO {
	
	//1003 s'inscrire
	public void insertUtilisateur(Utilisateur utilisateur) throws BusinessException;
		
	//1001 seconnecter
	public Utilisateur afficherUtilisateurPseudo(String pseudo) throws BusinessException;
		
	//1007 modifier mon profile
	public void updateUtilisateur(Utilisateur utilisateur) throws BusinessException;
	
	//1004 supprimer mon compte
	public void supprimerUtilisateur(int noUtilisateur) throws BusinessException;
	
}