package fr.eni.javaee.trocencheres.servlet;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.trocencheres.bll.ArticleVenduManager;
import fr.eni.javaee.trocencheres.exception.BusinessException;

/**
 * Servlet implementation class ServletAjoutArticle
 */
@WebServlet("/AjoutArticle")
public class ServletAjoutArticle extends HttpServlet {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -1959967161983342606L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAjoutArticle() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getNamedDispatcher("AjoutArticle");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String nomArticleVendu = null;
		String description = null;
		LocalDateTime dateDebutEncheres = null;
		LocalDateTime dateFinEncheres = null;
		int miseAPrix = 0;
		int prixVente = 0;
		int noUtilisateur = 0;
		int noCategorie =0;
		
		List<Integer> listeCodesErreur = new ArrayList<>();


		try {
			nomArticleVendu = request.getParameter("nom_article");
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_NOM_ARTICLE_ERREUR);
		}

		try {
			description = request.getParameter("description");
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_DESCRIPTION_ERREUR);
		}

		try {
			dateDebutEncheres = LocalDateTime.parse(
					(request.getParameter("date_debut_encheres")).toString(),
					DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_DATE_DEBUT_ENCHERES_ERREUR);
		}

		try {
			dateFinEncheres = LocalDateTime.parse((request.getParameter("date_fin_encheres")).toString(),
					DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_DATE_FIN_ENCHERES_ERREUR);
		}

		try {
			miseAPrix = Integer.parseInt(request.getParameter("prix_initial"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_MISE_A_PRIX_ERREUR);
		}

		try {
			prixVente = Integer.parseInt(request.getParameter("prix_vente"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_PRIX_VENTE_ERREUR);
		}
		
		try {
			noUtilisateur = Integer.parseInt(request.getParameter("no_utilisateur"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_NO_UTILISATEUR_ERREUR);
		}
		
		try {
			noCategorie = Integer.parseInt(request.getParameter("no_categorie"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_NO_CATEGORIE_ERREUR);
		}
		
		String article = null;
		article = request.getParameter("article");
		if(article==null || article.trim().isEmpty()) {
			listeCodesErreur.add(CodesResultatServlets.FORMAT_ARTICLE_ERREUR);
		}
		
		if(listeCodesErreur.size()>0) {
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AjoutArticle.jsp");
			rd.forward(request, response);
			
		}else {
			ArticleVenduManager articleVenduManager = new ArticleVenduManager();
			try {
				articleVenduManager.insertArticleVendu(nomArticleVendu, description, dateDebutEncheres, dateFinEncheres, miseAPrix, prixVente, noUtilisateur, noCategorie);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AjoutArticle.jsp");
				rd.forward(request, response);
				
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AjoutArticle.jsp");
				rd.forward(request, response);
			}
			
		}

	}
	

}
