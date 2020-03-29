package fr.eni.javaee.trocencheres.servlet;

/**
 * Les codes disponibles sont entre 30000 et 39999
 */
public abstract class CodesResultatServlets {
	
	//Code résultat pour l'ajout d'un article
	public static final int FORMAT_NO_ARTICLE_ERREUR = 30000;

	public static final int FORMAT_NOM_ARTICLE_ERREUR = 30001;

	public static final int FORMAT_DESCRIPTION_ERREUR = 30002;

	public static final int FORMAT_DATE_DEBUT_ENCHERES_ERREUR = 30003;

	public static final int FORMAT_DATE_FIN_ENCHERES_ERREUR = 30004;

	public static final int FORMAT_MISE_A_PRIX_ERREUR = 30005;

	public static final int FORMAT_PRIX_VENTE_ERREUR = 30006;

	
	
	//Code résultat pour l'inscription
	public static final int PSEUDO_ERREUR = 30100;
	
	public static final int NOM_ERREUR = 30101;
	
	public static final int PRENOM_ERREUR = 30102;
	
	public static final int EMAIL_ERREUR = 30103;
	
	public static final int RUE_ERREUR = 30105;
	
	public static final int CODE_POSTAL_ERREUR = 30106;
	
	public static final int VILLE_ERREUR = 30107;
	
	public static final int MDP_ERREUR = 30108;
	
	
	//Code résultat pour les enchères
	public static final int ARTICLE_INTROUVE = 30200;
	
	public static final int MONTANT_ENCHERE_ERREUR = 30201;



}