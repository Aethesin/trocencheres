package fr.eni.javaee.trocencheres.dal;

public abstract class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO()
	{
		return new UtilisateurDAOJdbcImpl();
	}
	
	public static ArticleVenduDAO getArticleVenduDAO()
	{
		return new ArticleVenduDAOJdbcImpl();
	}

	public static EncheresDAO getEncheresDAO() 
	{
		return new EncheresDAOJdbcImpl();
	}
	
	public static CategorieDAO getCategorieDAO()
	{
		return new CategorieDAOJdbcImpl();
	}
}
	