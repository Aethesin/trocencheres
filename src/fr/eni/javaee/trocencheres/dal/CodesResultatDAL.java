package fr.eni.javaee.trocencheres.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_OBJET_NULL=10000;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int INSERT_OBJET_ECHEC=10001;
	
	/**
	 * Echec de faire une enchère en raison de manque de crédits
	 */
	public static final int CREDIT_INSUFFISANT=10002;
	
	/**
	 * Echec de recherche d'un utilisateur
	 */
	public static final int UTILISATEUR_INTROUVE=10003;
	
	/**
	 * Echec de recherche d'un article
	 */
	public static final int ARTICLE_VENDU_INTROUVE=10004;
	
	/**
	 * Echec de faire une enchère en raison de la date dépassée
	 */
	public static final int ENCHERE_TERMINEE=10005;
	
	public static final int SELECT_BY_NO_ARTICLE_ECHEC = 10006;
	
}