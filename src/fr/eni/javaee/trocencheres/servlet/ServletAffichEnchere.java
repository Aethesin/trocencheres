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
		ArticleVendu articleVendu = null;
		Enchere enchere = null;
		Utilisateur vendeur = null;
		listeCodesErreur = new ArrayList<>();

		try {
			int noArticleVendu = Integer.parseInt(request.getParameter("noArticle"));
			articleVendu = articleVenduManager.selectArticleVenduByID(noArticleVendu);
			vendeur = articleVendu.getUtilisateur();
			LocalDateTime now = LocalDateTime.now();
			int montantEnchere = Integer.parseInt(request.getParameter("proposition"));
			request.setAttribute("proposition", montantEnchere);
			enchere = new Enchere(now, montantEnchere, articleVendu, vendeur);
			encheresManager.updateEnchere(enchere);
			request.setAttribute("enchere", enchere);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AffichEnchere.jsp");
			rd.forward(request, response);

		} catch (NumberFormatException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_PRIX_VENTE_ERREUR);

		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ParticipEnchere.jsp");
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