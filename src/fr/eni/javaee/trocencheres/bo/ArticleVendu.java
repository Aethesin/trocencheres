package fr.eni.javaee.trocencheres.bo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ArticleVendu implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer noArticleVendu;
	private String nomArticleVendu;
	private String description;
	private String imageURL;
	private LocalDateTime dateDebutEncheres;
	private LocalDateTime dateFinEncheres;
	private Integer miseAPrix;
	private Integer prixVente;
	private Utilisateur utilisateur;
	private Categorie categorie;
	
	public ArticleVendu() {
		super();
	}

	public ArticleVendu(Integer noArticleVendu, String nomArticleVendu, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, Integer miseAPrix, Integer prixVente) {
		super();
		this.noArticleVendu = noArticleVendu;
		this.nomArticleVendu = nomArticleVendu;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
	}

	public ArticleVendu(String nomArticleVendu, String description, LocalDateTime dateDebutEncheres, LocalDateTime dateFinEncheres,
			Integer miseAPrix, Integer prixVente) {
		super();
		this.nomArticleVendu = nomArticleVendu;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
	}

	
	
	public ArticleVendu(String nomArticleVendu, String description, LocalDateTime dateDebutEncheres, LocalDateTime dateFinEncheres,
			Integer miseAPrix, Integer prixVente, Utilisateur utilisateur, Categorie categorie) {
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

	public ArticleVendu(Integer noArticleVendu, String nomArticleVendu, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, Integer miseAPrix, Integer prixVente, Utilisateur utilisateur, Categorie categorie) {
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

	public int getnoArticleVendu() {
		return noArticleVendu;
	}

	public void setnoArticleVendu(Integer noArticleVendu) {
		this.noArticleVendu = noArticleVendu;
	}

	public String getnomArticleVendu() {
		return nomArticleVendu;
	}

	public void setnomArticleVendu(String nomArticleVendu) {
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

	public void setMiseAPrix(Integer miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(Integer prixVente) {
		this.prixVente = prixVente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categorie == null) ? 0 : categorie.hashCode());
		result = prime * result + noArticleVendu;
		result = prime * result + ((nomArticleVendu == null) ? 0 : nomArticleVendu.hashCode());
		result = prime * result + ((utilisateur == null) ? 0 : utilisateur.hashCode());
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
		if (categorie == null) {
			if (other.categorie != null)
				return false;
		} else if (!categorie.equals(other.categorie))
			return false;
		if (noArticleVendu != other.noArticleVendu)
			return false;
		if (nomArticleVendu == null) {
			if (other.nomArticleVendu != null)
				return false;
		} else if (!nomArticleVendu.equals(other.nomArticleVendu))
			return false;
		if (utilisateur == null) {
			if (other.utilisateur != null)
				return false;
		} else if (!utilisateur.equals(other.utilisateur))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ArticleVendu [noArticleVendu=" + noArticleVendu + ", nomArticleVendu=" + nomArticleVendu + ", dateDebutEncheres="
				+ dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + "]\n";
	}

}
