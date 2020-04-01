package fr.eni.javaee.trocencheres.bo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Enchere implements Serializable {

	
	private static final long serialVersionUID = -5412137110359755740L;
	
	private LocalDateTime dateEnchere;
	private int montantEnchere;
	private ArticleVendu articleVendu;
	private Utilisateur utilisateur;
	
	public Enchere() {
		super();
	}

	public Enchere(LocalDateTime dateEnchere, int montantEnchere, ArticleVendu articleVendu, Utilisateur utilisateur) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.articleVendu = articleVendu;
		this.utilisateur = utilisateur;
	}
	
	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}



	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}



	public int getMontantEnchere() {
		return montantEnchere;
	}



	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	

	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	
	
}
