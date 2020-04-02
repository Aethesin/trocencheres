package fr.eni.javaee.trocencheres.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
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
import javax.servlet.http.HttpSession;

import fr.eni.javaee.trocencheres.bll.ArticleVenduManager;
import fr.eni.javaee.trocencheres.bll.CategorieManager;
import fr.eni.javaee.trocencheres.bo.ArticleVendu;
import fr.eni.javaee.trocencheres.bo.Categorie;
import fr.eni.javaee.trocencheres.bo.Utilisateur;
import fr.eni.javaee.trocencheres.exception.BusinessException;
import fr.eni.javaee.trocencheres.messages.LecteurMessage;;

/**
 * Servlet implementation class ServletAjoutArticle
 */
@WebServlet("/AjoutArticle")
public class ServletAjoutArticle extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1959967161983342606L;
	private static CategorieManager categorieManager;
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
		RequestDispatcher rd = null;
		categorieManager = new CategorieManager();
		String nomArticleVendu = null;
		String description = null;
		LocalDateTime dateDebutEncheres = null;
		LocalDateTime dateFinEncheres = null;
		int miseAPrix = 0;
		int prixVente = 0;
		int noUtilisateur;

		List<Integer> listeCodesErreur = new ArrayList<>();
		List<String> listeCodesErreurString = new ArrayList<>();
		
		HttpSession session = request.getSession();
		Utilisateur utilisateur = new Utilisateur();
		utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		try {
			nomArticleVendu = request.getParameter("nomArticleVendu");
		} catch (StringIndexOutOfBoundsException e) {
			listeCodesErreur.add(CodesResultatServlets.FORMAT_NOM_ARTICLE_ERREUR);
			
		}

		try {
			description = request.getParameter("description");
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_DESCRIPTION_ERREUR);
		}

		String dateDebutEnchereString = request.getParameter("dateDebutEncheresDate")+"T"+request.getParameter("dateDebutEncheresTime");
		dateDebutEncheres = LocalDateTime.parse(dateDebutEnchereString);
		
		String dateFinEnchereString = request.getParameter("dateFinEncheresDate")+"T"+request.getParameter("dateFinEncheresTime");
		dateFinEncheres = LocalDateTime.parse(dateFinEnchereString);

		try {
			miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_MISE_A_PRIX_ERREUR);
		}
		
		
	
//			Utilisateur utilisateur =  (Utilisateur) session.getAttribute("utilisateur");
//			int noUtilisateur = utilisateur.getNoUtilisateur();
			
		
			
			noUtilisateur = utilisateur.getNoUtilisateur();
			Categorie categorie = null;
			try {
				categorie = categorieManager.selectCategorieById(Integer.parseInt(request.getParameter("categorie")));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
			
			
			for(Integer integer : listeCodesErreur) {
				listeCodesErreurString.add(LecteurMessage.getMessageErreur(integer));
			}
		ArticleVendu articleVendu = new ArticleVendu(nomArticleVendu, description, dateDebutEncheres, dateFinEncheres,
				miseAPrix, prixVente, utilisateur, categorie);
		if (listeCodesErreur.size() > 0) {			
			request.setAttribute("article", articleVendu);
			request.setAttribute("listeCodesErreurString", listeCodesErreurString);
			doGet(request, response);

		} else {
			ArticleVenduManager articleVenduManager = new ArticleVenduManager();
			try {
				articleVendu = articleVenduManager.insertArticleVendu(articleVendu);
				session.setAttribute("noArticle", articleVendu.getNoArticleVendu());
				rd = request.getRequestDispatcher("/WEB-INF/jsp/AffichEnchere.jsp");
				response.sendRedirect("AffichEnchere");
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				rd = request.getRequestDispatcher("/WEB-INF/jsp/AjoutArticle.jsp");
				rd.forward(request, response);
			}

		}

	}

}