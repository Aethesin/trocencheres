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
import javax.servlet.http.HttpSession;

import fr.eni.javaee.trocencheres.bll.EncheresManager;
import fr.eni.javaee.trocencheres.bo.Encheres;
import fr.eni.javaee.trocencheres.bo.Utilisateur;
import fr.eni.javaee.trocencheres.exception.BusinessException;

/**
 * Servlet implementation class ServletAffichEnchere
 */
@WebServlet("/AffichEnchere")
public class ServletAffichEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EncheresManager encheresManager;
	private static List<Integer> listeCodesErreur = new ArrayList<>();

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

		Encheres enchere = new Encheres();
		encheresManager = new EncheresManager();
		int noArticleVendu = Integer.parseInt(request.getParameter("noArticleVendu"));

		try {
			enchere = encheresManager.selectEnchereByNoArticleVendu(noArticleVendu);
		} catch (BusinessException e) {
			listeCodesErreur.add(CodesResultatServlets.ARTICLE_INTROUVE);
			e.printStackTrace();
		}

		if (listeCodesErreur.size() > 0) {
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ParticipEnchere.jsp");
			rd.forward(request, response);
		} else {
			LocalDateTime dateEnchere = LocalDateTime.now();
			int montantEnchere = Integer.parseInt(request.getParameter("proposition"));
			HttpSession session = request.getSession();
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
			int noUtilisateur = utilisateur.getNoUtilisateur();
			try {
				encheresManager.insertEnchere(dateEnchere, montantEnchere, noArticleVendu, noUtilisateur);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ParticipEnchereAvecSucces.jsp");
				rd.forward(request, response);

			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ParticipEnchereEnEchec.jsp");
				rd.forward(request, response);
			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int montantEnchere = Integer.parseInt(request.getParameter("proposition"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.MONTANT_ENCHERE_ERREUR);
		}
		doGet(request, response);
	}

}
