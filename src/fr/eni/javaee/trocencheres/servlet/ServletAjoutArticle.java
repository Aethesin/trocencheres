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
import javax.servlet.http.HttpSession;

import fr.eni.javaee.trocencheres.bll.ArticleVenduManager;
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
		int noUtilisateur;
		int noCategorie;

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
		
		
	
//			Utilisateur utilisateur =  (Utilisateur) session.getAttribute("utilisateur");
//			int noUtilisateur = utilisateur.getNoUtilisateur();
			
		
			
			noUtilisateur = utilisateur.getNoUtilisateur();
			Categorie categorie = null;
			
			switch (request.getParameter("categorie")) {
				case "Vêtement":
					noCategorie = 1;
					break;
				case "Informatique":
					noCategorie = 2;
					break;
				case "Ameublement":
					noCategorie = 3;
					break;
				case "Sport &amp; Loisirs":
					noCategorie = 4;
					break;
			}
			
			for(Integer integer : listeCodesErreur) {
				listeCodesErreurString.add(LecteurMessage.getMessageErreur(integer));
			}
			
		if (listeCodesErreur.size() > 0) {
			ArticleVendu articleVendu = new ArticleVendu(nomArticleVendu, description, dateDebutEncheres, dateFinEncheres,
					miseAPrix, prixVente, utilisateur, categorie);
			request.setAttribute("article", articleVendu);
			request.setAttribute("listeCodesErreurString", listeCodesErreurString);
			doGet(request, response);

		} else {
			ArticleVenduManager articleVenduManager = new ArticleVenduManager();
			try {			
				articleVenduManager.insertArticleVendu(nomArticleVendu, description, dateDebutEncheres, dateFinEncheres,
						miseAPrix, prixVente, utilisateur, categorie);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AjoutArticleAvecSucces.jsp");
				rd.forward(request, response);

			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AjoutArticleEnEchec.jsp");
				rd.forward(request, response);
			}

		}

	}

}