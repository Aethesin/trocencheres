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
import fr.eni.javaee.trocencheres.bo.ArticleVendu;
import fr.eni.javaee.trocencheres.exception.BusinessException;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/Accueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static ArticleVenduManager amger;
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
		HttpSession session = request.getSession();
		if(session.getAttribute("utilisateur") == null){
			System.out.println("Pas d'utilisateur connecté");
		}else{
			System.out.println("Un utilsiateur connecté");
		}
		
		try {
			List<ArticleVendu> listeArticlesVendu = new ArrayList<ArticleVendu>();
			listeArticlesVendu  = amger.selectAllArticleVendu();
			request.setAttribute("listeArticlesVendu", listeArticlesVendu);
			RequestDispatcher rd = this.getServletContext().getNamedDispatcher("accueil");
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
		HttpSession session = request.getSession();
		String motCle = request.getParameter("motCle");
		System.out.println(motCle);
		String categorie = request.getParameter("categorie");
		if(session.getAttribute("utilisateur") == null){
			System.out.println("Pas d'utilisateur connecté");
		}else{
			System.out.println("Un utilsiateur connecté");
		}
		if(categorie.equals("VÃªtement")){
			categorie = "Vêtement";
		}
		System.out.println(categorie);
		if(motCle.trim().length() != 0 && !categorie.equals("toutes")){
			try {
				List<ArticleVendu> listeArticlesVendu = new ArrayList<ArticleVendu>();
				listeArticlesVendu  = amger.selectArticleVenduByMotCleAndCategorie(motCle, categorie);
				request.setAttribute("listeArticlesVendu", listeArticlesVendu);
				RequestDispatcher rd = this.getServletContext().getNamedDispatcher("accueil");
				rd.forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();
			}			
		}else if(motCle.trim().length() != 0 && categorie.equals("toutes")){
			try {
				List<ArticleVendu> listeArticlesVendu = new ArrayList<ArticleVendu>();
				listeArticlesVendu  = amger.selectArticleVenduByMotCle(motCle);
				request.setAttribute("listeArticlesVendu", listeArticlesVendu);
				RequestDispatcher rd = this.getServletContext().getNamedDispatcher("accueil");
				rd.forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();
			}	
		}else if(motCle.trim().length() == 0 && !categorie.equals("toutes")){
			try {
				List<ArticleVendu> listeArticlesVendu = new ArrayList<ArticleVendu>();
				listeArticlesVendu  = amger.selectArticleVenduByCategorie(categorie);
				request.setAttribute("listeArticlesVendu", listeArticlesVendu);
				RequestDispatcher rd = this.getServletContext().getNamedDispatcher("accueil");
				rd.forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();
			}	
		}else{
			doGet(request, response);			
		}
	}

}
