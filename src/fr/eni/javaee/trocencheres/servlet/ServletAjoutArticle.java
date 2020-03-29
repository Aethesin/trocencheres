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
import fr.eni.javaee.trocencheres.bo.ArticleVendu;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getNamedDispatcher("AjoutArticle");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String nomArticleVendu = null;
		String description = null;
		LocalDateTime dateDebutEncheres = null;
		LocalDateTime dateFinEncheres = null;
		int miseAPrix = 0;
		int prixVente = 0;

		List<Integer> listeCodesErreur = new ArrayList<>();
		

		try {
			nomArticleVendu = request.getParameter("nom");
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
			dateDebutEncheres = LocalDateTime.parse((request.getParameter("dateDebutEncheres")).toString(),
					DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_DATE_DEBUT_ENCHERES_ERREUR);
		}

		try {
			dateFinEncheres = LocalDateTime.parse((request.getParameter("dateFinEncheres")).toString(),
					DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_DATE_FIN_ENCHERES_ERREUR);
		}

		try {
			miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_MISE_A_PRIX_ERREUR);
		}

		try {
			prixVente = Integer.parseInt(request.getParameter("prixVente"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_PRIX_VENTE_ERREUR);
		}

			int noUtilisateur = Integer.parseInt(request.getParameter("noUtilisateur"));

			int noCategorie = Integer.parseInt(request.getParameter("noCategorie"));

		if (listeCodesErreur.size() > 0) {
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AjoutArticle.jsp");
			rd.forward(request, response);

		} else {
			ArticleVenduManager articleVenduManager = new ArticleVenduManager();
			try {
				
				articleVenduManager.insertArticleVendu(nomArticleVendu, description, dateDebutEncheres, dateFinEncheres,
						miseAPrix, prixVente, noUtilisateur, noCategorie);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AjoutArticleSucces.jsp");
				rd.forward(request, response);

			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AjoutArticleEchec.jsp");
				rd.forward(request, response);
			}

		}

	}

}
