package fr.eni.javaee.trocencheres.bo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Classe en charge de définir le JavaBean articleVendu
 * @author D2WM2020_Team1
 * @version trocencheres - v1.0
 * @date 3 avr. 2020
 */
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
	
	/**
	 * Constructeur sans paramètre
	 */
	public ArticleVendu() {
		super();
	}
	
	/**
	 * Constructeur
	 * @param nomArticleVendu
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param utilisateur
	 * @param categorie
	 */
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

	/**
	 * Constructeur
	 * @param noArticleVendu
	 * @param nomArticleVendu
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param utilisateur
	 * @param categorie
	 */
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

	/**
	 * Méthode en charge de récupérer la valeur de noArticleVendu
	 * @return noArticleVendu
	 */
	public int getNoArticleVendu() {
		return noArticleVendu;
	}

	/**
	 * Méthode en charge de définir la valeur de noArticleVendu
	 * @param noArticleVendu
	 */
	public void setNoArticleVendu(int noArticleVendu) {
		this.noArticleVendu = noArticleVendu;
	}

	/**
	 * Méthode en charge de récupérer la valeur de nomArticleVendu
	 * @return nomArticleVendu
	 */
	public String getNomArticleVendu() {
		return nomArticleVendu;
	}

	/**
	 * Méthode en charge de définir la valeur de nomArticleVendu
	 * @param nomArticleVendu
	 */
	public void setNomArticleVendu(String nomArticleVendu) {
		this.nomArticleVendu = nomArticleVendu;
	}

	/**
	 * Méthode en charge de récupérer la valeur de description
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Méthode en charge de définir la valeur de description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Méthode en charge de récupérer la valeur de dateDebutEncheres
	 * @return dateDebutEncheres
	 */
	public LocalDateTime getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	/**
	 * Méthode en charge de définir la valeur de dateDebutEncheres
	 * @param dateDebutEncheres
	 */
	public void setDateDebutEncheres(LocalDateTime dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	/**
	 * Méthode en charge de récupérer la valeur de dateFinEncheres
	 * @return dateFinEncheres
	 */
	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}

	/**
	 * Méthode en charge de définir la valeur de dateFinEncheres
	 * @param dateFinEncheres
	 */
	public void setDateFinEncheres(LocalDateTime dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	/**
	 * Méthode en charge de récupérer la valeur de miseAPrix
	 * @return miseAPrix
	 */
	public int getMiseAPrix() {
		return miseAPrix;
	}

	/**
	 * Méthode en charge de définir la valeur de miseAPrix
	 * @param miseAPrix
	 */
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	/**
	 * Méthode en charge de récupérer la valeur de prixVente
	 * @return prixVente
	 */
	public int getPrixVente() {
		return prixVente;
	}

	/**
	 * Méthode en charge de définir la valeur de prixVente
	 * @param prixVente
	 */
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	/**
	 * Méthode en charge de récupérer la valeur de utilisateur
	 * @return utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	/**
	 * Méthode en charge de définir la valeur de utilisateur
	 * @param utilisateur
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	/**
	 * Méthode en charge de récupérer la valeur de categorie
	 * @return categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}

	/**
	 * Méthode en charge de définir la valeur de categorie
	 * @param categorie
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/**
	 * Méthode en charge de récupérer la valeur de serialVersionUID
	 * @return serialVersionUID
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categorie == null) ? 0 : categorie.hashCode());
		result = prime * result + ((dateDebutEncheres == null) ? 0 : dateDebutEncheres.hashCode());
		result = prime * result + ((dateFinEncheres == null) ? 0 : dateFinEncheres.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + miseAPrix;
		result = prime * result + noArticleVendu;
		result = prime * result + ((nomArticleVendu == null) ? 0 : nomArticleVendu.hashCode());
		result = prime * result + prixVente;
		result = prime * result + ((utilisateur == null) ? 0 : utilisateur.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
		if (dateDebutEncheres == null) {
			if (other.dateDebutEncheres != null)
				return false;
		} else if (!dateDebutEncheres.equals(other.dateDebutEncheres))
			return false;
		if (dateFinEncheres == null) {
			if (other.dateFinEncheres != null)
				return false;
		} else if (!dateFinEncheres.equals(other.dateFinEncheres))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (miseAPrix != other.miseAPrix)
			return false;
		if (noArticleVendu != other.noArticleVendu)
			return false;
		if (nomArticleVendu == null) {
			if (other.nomArticleVendu != null)
				return false;
		} else if (!nomArticleVendu.equals(other.nomArticleVendu))
			return false;
		if (prixVente != other.prixVente)
			return false;
		if (utilisateur == null) {
			if (other.utilisateur != null)
				return false;
		} else if (!utilisateur.equals(other.utilisateur))
			return false;
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ArticleVendu [noArticleVendu=").append(noArticleVendu).append(", nomArticleVendu=")
				.append(nomArticleVendu).append(", description=").append(description).append(", dateDebutEncheres=")
				.append(dateDebutEncheres).append(", dateFinEncheres=").append(dateFinEncheres).append(", miseAPrix=")
				.append(miseAPrix).append(", prixVente=").append(prixVente).append(", utilisateur=").append(utilisateur)
				.append(", categorie=").append(categorie).append("]");
		return builder.toString();
	}
}
