package fr.eni.javaee.trocencheres.bo;

import java.io.Serializable;

/**
 * Classe en charge de définir le JavaBean retrait
 * @author D2WM2020_Team1
 * @version trocencheres - v1.0
 * @date 3 avr. 2020
 */
public class Retrait implements Serializable {
	
	
	private static final long serialVersionUID = -5943590610977819557L;
	
	private String rue;
	private String codePostal;
	private String ville;
	private ArticleVendu article;
	
	/**
	 * Constructeur sans paramètre
	 */
	public Retrait() {
		super();
	}

	/**
	 * Constructeur
	 * @param rue
	 * @param codePostal
	 * @param ville
	 */
	public Retrait(String rue, String codePostal, String ville) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	/**
	 * Constructeur
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param article
	 */
	public Retrait(String rue, String codePostal, String ville, ArticleVendu article) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.article = article;
	}

	/**
	 * Méthode en charge de récupérer la valeur de article
	 * @return article
	 */
	public ArticleVendu getArticle() {
		return article;
	}

	/**
	 * Méthode en charge de définir la valeur de article
	 * @param article
	 */
	public void setArticle(ArticleVendu article) {
		this.article = article;
	}

	/**
	 * Méthode en charge de récupérer la valeur de rue
	 * @return rue
	 */
	public String getRue() {
		return rue;
	}

	/**
	 * Méthode en charge de définir la valeur de rue
	 * @param rue
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}

	/**
	 * Méthode en charge de récupérer la valeur de codePostal
	 * @return codePostal
	 */
	public String getCodePostal() {
		return codePostal;
	}

	/**
	 * Méthode en charge de définir la valeur de codePostal
	 * @param codePostal
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * Méthode en charge de récupérer la valeur de ville
	 * @return ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * Méthode en charge de définir la valeur de ville
	 * @param ville
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * Méthode en charge de récupérer la valeur de serialVersionUID
	 * @return
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
		result = prime * result + ((article == null) ? 0 : article.hashCode());
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
		Retrait other = (Retrait) obj;
		if (article == null) {
			if (other.article != null)
				return false;
		} else if (!article.equals(other.article))
			return false;
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + ", article=" + article + "]\n";
	}
	
	
	
}
