package fr.eni.javaee.trocencheres.dal;

import java.util.List;

import fr.eni.javaee.trocencheres.bo.ArticleVendu;
import fr.eni.javaee.trocencheres.exception.BusinessException;

public interface ArticleVenduDAO {
	
	//2001 vendre un article
	public void insertArticleVendu(ArticleVendu articleVendu) throws BusinessException;
	
	//2004,2005 filtrer ma recherche par categorie
	public List<ArticleVendu> selectArticleVenduByCategorie(String categorie) throws BusinessException;
	
	//2004,2005 filtrer ma recherche par nom d'article
	public List<ArticleVendu> selectArticleVenduByMotCle(String motCle) throws BusinessException;
	
	public List<ArticleVendu> selectArticleVenduByMotCleAndCategorie(String motCle, String categorie) throws BusinessException;
	
	//2004,2005 afficher tous les articles
	public List<ArticleVendu> selectAllArticleVendu() throws BusinessException;
	
	//1002 modifier une vente
	//public void updateArtcileVendu(ArticleVendu articleVendu) throws BusinessException;
	
	//2002 annuler une vente
	//public void deleteArtcileVendu(int noArticleVendu) throws BusinessException;

}
