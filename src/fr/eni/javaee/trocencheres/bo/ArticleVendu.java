package fr.eni.javaee.trocencheres.bo;

import java.io.Serializable;
import java.time.LocalDateTime;


public class ArticleVendu implements Serializable {
	
	private static final long serialVersionUID = -6998566428347980649L;
	
	private int noArticleVendu;
	private String nomArticleVendu;
	private String description;
	private LocalDateTime dateDebutEncheres;
	private LocalDateTime dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private Utilisateur utilisateur;
	private Categorie categorie;
	
	public ArticleVendu() {
		super();
	}
	
	public ArticleVendu(String nomArticleVendu, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, int prixVente, Utilisateur utilisateur, Categorie categorie) {
		super();
		this.nomArticleVendu = nomArticleVendu;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
	}

	public ArticleVendu(int noArticleVendu, String nomArticleVendu, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, int prixVente, Utilisateur utilisateur, Categorie categorie) {
		super();
		this.noArticleVendu = noArticleVendu;
		this.nomArticleVendu = nomArticleVendu;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
	}

	public int getNoArticleVendu() {
		return noArticleVendu;
	}




	public void setNoArticleVendu(int noArticleVendu) {
		this.noArticleVendu = noArticleVendu;
	}




	public String getNomArticleVendu() {
		return nomArticleVendu;
	}




	public void setNomArticleVendu(String nomArticleVendu) {
		this.nomArticleVendu = nomArticleVendu;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public LocalDateTime getDateDebutEncheres() {
		return dateDebutEncheres;
	}




	public void setDateDebutEncheres(LocalDateTime dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}




	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}




	public void setDateFinEncheres(LocalDateTime dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}




	public int getMiseAPrix() {
		return miseAPrix;
	}




	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}




	public int getPrixVente() {
		return prixVente;
	}




	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}







	@Override
	public String toString() {
		return "ArticleVendu [noArticleVendu=" + noArticleVendu + ", nomArticleVendu=" + nomArticleVendu + ", dateDebutEncheres="
				+ dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + "]\n";
	}

}
