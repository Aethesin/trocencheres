package fr.eni.javaee.trocencheres.bo;

import java.io.Serializable;
import java.time.LocalDateTime;


public class ArticleVendu implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer noArticleVendu;
	private String nomArticleVendu;
	private String description;
	private LocalDateTime dateDebutEncheres;
	private LocalDateTime dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private int noUtilisateur;
	private int noCategorie;
	
	public ArticleVendu() {
		super();
	}

	
	

	public ArticleVendu(String nomArticleVendu, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, int prixVente, int noUtilisateur, int noCategorie) {
		super();
		this.nomArticleVendu = nomArticleVendu;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
	}




	public ArticleVendu(Integer noArticleVendu, String nomArticleVendu, String description,
			LocalDateTime dateDebutEncheres, LocalDateTime dateFinEncheres, int miseAPrix, int prixVente,
			int noUtilisateur, int noCategorie) {
		super();
		this.noArticleVendu = noArticleVendu;
		this.nomArticleVendu = nomArticleVendu;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
	}




	public Integer getNoArticleVendu() {
		return noArticleVendu;
	}




	public void setNoArticleVendu(Integer noArticleVendu) {
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




	public int getNoUtilisateur() {
		return noUtilisateur;
	}




	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}




	public int getNoCategorie() {
		return noCategorie;
	}




	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((noArticleVendu == null) ? 0 : noArticleVendu.hashCode());
		result = prime * result + noUtilisateur;
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticleVendu other = (ArticleVendu) obj;
		if (noArticleVendu == null) {
			if (other.noArticleVendu != null)
				return false;
		} else if (!noArticleVendu.equals(other.noArticleVendu))
			return false;
		if (noUtilisateur != other.noUtilisateur)
			return false;
		return true;
	}




	@Override
	public String toString() {
		return "ArticleVendu [noArticleVendu=" + noArticleVendu + ", nomArticleVendu=" + nomArticleVendu + ", dateDebutEncheres="
				+ dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + "]\n";
	}

}
