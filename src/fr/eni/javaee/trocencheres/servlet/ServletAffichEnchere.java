package fr.eni.javaee.trocencheres.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.trocencheres.bll.ArticleVenduManager;
import fr.eni.javaee.trocencheres.bll.EncheresManager;
import fr.eni.javaee.trocencheres.bo.ArticleVendu;
import fr.eni.javaee.trocencheres.bo.Enchere;
import fr.eni.javaee.trocencheres.bo.Utilisateur;
import fr.eni.javaee.trocencheres.exception.BusinessException;

/**
 * Servlet implementation class ServletAffichEnchere
 */
@WebServlet("/AffichEnchere")
public class ServletAffichEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleVenduManager articleVenduManager;
	private EncheresManager encheresManager;
	private static List<Integer> listeCodesErreur;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAffichEnchere() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Permet d'afficher la page AfficherEnchere
		articleVenduManager = new ArticleVenduManager();
		ArticleVendu articleVendu;
		Enchere enchere;
		Utilisateur vendeur;
		listeCodesErreur = new ArrayList<>();

		try {

			// Permet de gerer une proposition d'enchere
			if (request.getParameter("btnEncherir") != null) {
				int valeurProposition = Integer.parseInt(request.getParameter("proposition"));

				int noArticleVendu = Integer.parseInt(request.getParameter("noArticle"));
				articleVendu = articleVenduManager.selectArticleVenduByID(noArticleVendu);
				vendeur = articleVendu.getUtilisateur();

				// Aller chercher en base de données les informations de l'article
				String nomArticleVendu = articleVendu.getNomArticleVendu();
				String description = articleVendu.getDescription();
				String categorie = articleVendu.getCategorie().getLibelle();
				int prixVente = articleVendu.getPrixVente();
				int miseAPrix = articleVendu.getMiseAPrix();
				LocalDateTime dateFinEnchere = articleVendu.getDateFinEncheres();
				String rue = vendeur.getRue();
				String codePostal = vendeur.getCodePostal();
				String ville = vendeur.getVille();

				// Afficher les données à l'écran
				request.setAttribute("nomArticle", nomArticleVendu);
				request.setAttribute("description", description);
				request.setAttribute("categorie", categorie);
				request.setAttribute("prixVente", prixVente);
				request.setAttribute("miseAPrix", miseAPrix);
				request.setAttribute("dateFinEnchere", dateFinEnchere);
				request.setAttribute("rue", rue);
				request.setAttribute("codePostal", codePostal);
				request.setAttribute("ville", ville);

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AffichEnchere.jsp");
				rd.forward(request, response);
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_PRIX_VENTE_ERREUR);

		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AffichEnchere.jsp");
			rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}