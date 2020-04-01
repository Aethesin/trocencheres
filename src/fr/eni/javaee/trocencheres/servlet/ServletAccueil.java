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
		try {
			List<ArticleVendu> listeArticlesVendu = new ArrayList<ArticleVendu>();
			List<Utilisateur> listeVendeurs = new ArrayList<>();
			listeArticlesVendu  = amger.selectAllArticleVendu();
			listeVendeurs = selectVendeurs(listeArticlesVendu);
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
		amger = new ArticleVenduManager();
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		String motCle = request.getParameter("motCle");
		String categorie = request.getParameter("categorie");
		List<ArticleVendu> listeArticlesVendu = new ArrayList<ArticleVendu>();
		List<Utilisateur> listeVendeurs = new ArrayList<>();
		
		if(motCle.trim().length() != 0 && !categorie.equals("toutes")){
			try {
				listeArticlesVendu  = amger.selectArticleVenduByMotCleAndCategorie(motCle, categorie);
				listeVendeurs = selectVendeurs(listeArticlesVendu);
				request.setAttribute("listeArticlesVendu", listeArticlesVendu);
				request.setAttribute("listeVendeurs", listeVendeurs);
				rd = this.getServletContext().getNamedDispatcher("accueil");
				rd.forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();
			}			
		}else if(motCle.trim().length() != 0 && categorie.equals("toutes")){
			try {
				listeArticlesVendu  = amger.selectArticleVenduByMotCle(motCle);
				listeVendeurs = selectVendeurs(listeArticlesVendu);
				request.setAttribute("listeArticlesVendu", listeArticlesVendu);
				request.setAttribute("listeVendeurs", listeVendeurs);
				rd = this.getServletContext().getNamedDispatcher("accueil");
				rd.forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();
			}	
		}else if(motCle.trim().length() == 0 && !categorie.equals("toutes")){
			try {
				listeArticlesVendu  = amger.selectArticleVenduByCategorie(categorie);
				listeVendeurs = selectVendeurs(listeArticlesVendu);
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
	
	private List<Utilisateur> selectVendeurs(List<ArticleVendu> listeArticlesVendu){
		umger = new UtilisateurManager();
		List<Utilisateur> listeVendeurs = new ArrayList<>();
		Utilisateur utilisateur = null;
		for (ArticleVendu articleVendu : listeArticlesVendu) {
			try {
				utilisateur = umger.selectVendeurs(articleVendu.getUtilisateur().getNoUtilisateur());
				listeVendeurs.add(utilisateur);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
		}
		return listeVendeurs;
	}

}
