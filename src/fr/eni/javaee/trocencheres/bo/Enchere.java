package fr.eni.javaee.trocencheres.bo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Classe en charge de définir le JavaBean enchere
 * @author D2WM2020_Team1
 * @version trocencheres - v1.0
 * @date 3 avr. 2020
 */
public class Enchere implements Serializable {

	
	private static final long serialVersionUID = -5412137110359755740L;
	
	private LocalDateTime dateEnchere;
	private int montantEnchere;
	private ArticleVendu articleVendu;
	private Utilisateur utilisateur;
	
	/**
	 * Constructeur sans paramètre
	 */
	public Enchere() {
		super();
	}

	/**
	 * Constructeur
	 * @param dateEnchere
	 * @param montantEnchere
	 * @param articleVendu
	 * @param utilisateur
	 */
	public Enchere(LocalDateTime dateEnchere, int montantEnchere, ArticleVendu articleVendu, Utilisateur utilisateur) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.articleVendu = articleVendu;
		this.utilisateur = utilisateur;
	}
	
	/**
	 * Méthode en charge de récupérer la valeur de dateEnchere
	 * @return dateEnchere
	 */
	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}



	/**
	 * Méthode en charge de définir la valeur de dateEnchere
	 * @param dateEnchere
	 */
	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}



	/**
	 * Méthode en charge de récupérer la valeur de montantEnchere
	 * @return montantEnchere
	 */
	public int getMontantEnchere() {
		return montantEnchere;
	}



	/**
	 * Méthode en charge de définir la valeur de montantEnchere
	 * @param montantEnchere
	 */
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	

	/**
	 * Méthode en charge de récupérer la valeur de articleVendu
	 * @return articleVendu
	 */
	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	/**
	 * Méthode en charge de définir la valeur de articleVendu
	 * @param articleVendu
	 */
	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
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
		result = prime * result + ((articleVendu == null) ? 0 : articleVendu.hashCode());
		result = prime * result + ((dateEnchere == null) ? 0 : dateEnchere.hashCode());
		result = prime * result + montantEnchere;
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
		Enchere other = (Enchere) obj;
		if (articleVendu == null) {
			if (other.articleVendu != null)
				return false;
		} else if (!articleVendu.equals(other.articleVendu))
			return false;
		if (dateEnchere == null) {
			if (other.dateEnchere != null)
				return false;
		} else if (!dateEnchere.equals(other.dateEnchere))
			return false;
		if (montantEnchere != other.montantEnchere)
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
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + ", articleVendu="
				+ articleVendu + ", utilisateur=" + utilisateur + "]";
	}




	
	
}
