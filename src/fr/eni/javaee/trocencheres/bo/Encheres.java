package fr.eni.javaee.trocencheres.bo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Encheres implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private LocalDateTime dateEnchere;
	private int montantEnchere;
	private int noArticleVendu;
	private int noUtilisateur;
	
	public Encheres() {
		super();
	}

	

	public Encheres(LocalDateTime dateEnchere, int montantEnchere, int noArticleVendu, int noUtilisateur) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noArticleVendu = noArticleVendu;
		this.noUtilisateur = noUtilisateur;
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



	public int getNoArticleVendu() {
		return noArticleVendu;
	}



	public void setNoArticleVendu(int noArticleVendu) {
		this.noArticleVendu = noArticleVendu;
	}



	public int getNoUtilisateur() {
		return noUtilisateur;
	}



	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + noArticleVendu;
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
		Encheres other = (Encheres) obj;
		if (noArticleVendu != other.noArticleVendu)
			return false;
		if (noUtilisateur != other.noUtilisateur)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Encheres [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + ", noArticleVendu="
				+ noArticleVendu + ", noUtilisateur=" + noUtilisateur + "]\n";
	}



	
	
}
