package fr.eni.javaee.trocencheres.servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.trocencheres.bll.ArticleVenduManager;
import fr.eni.javaee.trocencheres.bo.ArticleVendu;
import fr.eni.javaee.trocencheres.bo.Categorie;
import fr.eni.javaee.trocencheres.bo.Utilisateur;
import fr.eni.javaee.trocencheres.exception.BusinessException;

/**
 * Servlet implementation class ServletModifArticle
 */
@WebServlet("/ModifArticle")
public class ServletModifArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static ArticleVenduManager articleVenduManager;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModifArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AffichEnchere.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		articleVenduManager = new ArticleVenduManager();
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		
		Categorie categorie = new Categorie();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		ArticleVendu articleVendu = null;
		LocalDateTime dateDebutEncheres = null;
		LocalDateTime dateFinEncheres = null;
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		String nomArticle = request.getParameter("nomArticleVendu");
		String description = request.getParameter("description");
		int noCategorie = Integer.parseInt(request.getParameter("categorie"));
		categorie.setNoCategorie(noCategorie);
		
		String dateDebutEnchereString = request.getParameter("dateDebutEncheresDate")+"T"+request.getParameter("dateDebutEncheresTime");
		dateDebutEncheres = LocalDateTime.parse(dateDebutEnchereString);
		
		String dateFinEnchereString = request.getParameter("dateFinEncheresDate")+"T"+request.getParameter("dateFinEncheresTime");
		dateFinEncheres = LocalDateTime.parse(dateFinEnchereString);
		
		int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
		
		String retrait = request.getParameter("rue") + " " + request.getParameter("ville") + " " +request.getParameter("codePostal");
		
		articleVendu = new ArticleVendu(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, miseAPrix, utilisateur, categorie);
		
		try {
			articleVenduManager.updateArticleVendu(articleVendu);
			session.setAttribute("noArticle", articleVendu.getNoArticleVendu());
			response.sendRedirect("AffichEnchere");
		} catch (BusinessException e) {
			e.printStackTrace();
			session.setAttribute("noArticle", articleVendu.getNoArticleVendu());
			response.sendRedirect("AffichEnchere");
		}
		
		
	}

}
