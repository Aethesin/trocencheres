package fr.eni.javaee.trocencheres.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.trocencheres.bll.ArticleVenduManager;
import fr.eni.javaee.trocencheres.bll.UtilisateurManager;
import fr.eni.javaee.trocencheres.bo.ArticleVendu;
import fr.eni.javaee.trocencheres.bo.Utilisateur;
import fr.eni.javaee.trocencheres.exception.BusinessException;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/accueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static ArticleVenduManager amger;
    private static UtilisateurManager umger;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAccueil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		amger = new ArticleVenduManager();
		RequestDispatcher rd = null;
		//Ici on affiche la page d'accueil du site trocencheres, l'affichage de base lorsqu'il n'y a pas eu de recherche par filtre
		try {
			//On créer alors deux listes, une pour tous les articles en ventes et une pour tous les utilisateurs qui ont mis un article en vente
			List<ArticleVendu> listeArticlesVendu = new ArrayList<ArticleVendu>();
			List<Utilisateur> listeVendeurs = new ArrayList<>();
			listeArticlesVendu  = amger.selectAllArticleVendu();
			listeVendeurs = selectUtilisateur(listeArticlesVendu);
			request.setAttribute("listeArticlesVendu", listeArticlesVendu);
			request.setAttribute("listeVendeurs", listeVendeurs);
			rd = this.getServletContext().getNamedDispatcher("accueil");
			rd.forward(request, response);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Ici on affiche la page d'accueil après la recherche par filtre, différent cas s'impose, avec soit un filtre, soit deux.
		
		amger = new ArticleVenduManager();
		RequestDispatcher rd = null;
		request.setCharacterEncoding("UTF-8");
		
		//Ici on récupère nos champs de recherche motCle et la catégorie, si catégorie = "toutes" on recherche dans toutes les catégories
		String motCle = request.getParameter("motCle");
		String categorie = request.getParameter("categorie");
		List<ArticleVendu> listeArticlesVendu = new ArrayList<ArticleVendu>();
		List<Utilisateur> listeVendeurs = new ArrayList<>();
		
		if(motCle.trim().length() != 0 && !categorie.equals("toutes")){
			//Cette condition est validée lorsque les deux champs ont été renseigné, pour la catégorie, c'est à dire autre chose que "toutes"
			try {
				listeArticlesVendu  = amger.selectArticleVenduByMotCleAndCategorie(motCle, categorie);
				listeVendeurs = selectUtilisateur(listeArticlesVendu);
				request.setAttribute("listeArticlesVendu", listeArticlesVendu);
				request.setAttribute("listeVendeurs", listeVendeurs);
				rd = this.getServletContext().getNamedDispatcher("accueil");
				rd.forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();
			}			
		}else if(motCle.trim().length() != 0 && categorie.equals("toutes")){
			//Cette condition est validée lorsque le seulement le champ "motCle" a été renseigné.
			try {
				listeArticlesVendu  = amger.selectArticleVenduByMotCle(motCle);
				listeVendeurs = selectUtilisateur(listeArticlesVendu);
				request.setAttribute("listeArticlesVendu", listeArticlesVendu);
				request.setAttribute("listeVendeurs", listeVendeurs);
				rd = this.getServletContext().getNamedDispatcher("accueil");
				rd.forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();
			}	
		}else if(motCle.trim().length() == 0 && !categorie.equals("toutes")){
			//Cette condition est validée lorsque le seulement le champ catégorie a reçu autre chose que le choix "toutes"
			try {
				listeArticlesVendu  = amger.selectArticleVenduByCategorie(categorie);
				listeVendeurs = selectUtilisateur(listeArticlesVendu);
				request.setAttribute("listeArticlesVendu", listeArticlesVendu);
				request.setAttribute("listeVendeurs", listeVendeurs);
				rd = this.getServletContext().getNamedDispatcher("accueil");
				rd.forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();
			}	
		}else{
			doGet(request, response);			
		}	

		
	}
	
	//Cette méthode sert à allez chercher tous les vendeurs qui ont au moins un articles en ventes dans la bdd
	private List<Utilisateur> selectUtilisateur(List<ArticleVendu> listeArticlesVendu){
		umger = new UtilisateurManager();
		List<Utilisateur> listeVendeurs = new ArrayList<>();
		Utilisateur utilisateur = null;
		for (ArticleVendu articleVendu : listeArticlesVendu) {
			try {
				utilisateur = umger.selectUtilisateurById(articleVendu.getUtilisateur().getNoUtilisateur());
				listeVendeurs.add(utilisateur);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
		}
		return listeVendeurs;
	}

}
