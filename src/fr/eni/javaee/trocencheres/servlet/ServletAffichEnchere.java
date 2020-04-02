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

import fr.eni.javaee.trocencheres.bll.ArticleVenduManager;
import fr.eni.javaee.trocencheres.bll.EncheresManager;
import fr.eni.javaee.trocencheres.bll.UtilisateurManager;
import fr.eni.javaee.trocencheres.bo.ArticleVendu;
import fr.eni.javaee.trocencheres.bo.Categorie;
import fr.eni.javaee.trocencheres.bo.Enchere;
import fr.eni.javaee.trocencheres.bo.Utilisateur;
import fr.eni.javaee.trocencheres.exception.BusinessException;

/**
 * Servlet implementation class ServletAffichEnchere
 */
@WebServlet("/AffichEnchere")
public class ServletAffichEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilisateurManager;
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
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		// Permet d'afficher la page AfficherEnchere
		utilisateurManager = new UtilisateurManager();
		encheresManager = new EncheresManager();
		ArticleVendu articleVendu = new ArticleVendu();
		Enchere enchere = null;
		Utilisateur vendeur = null;
		Categorie categorie = null;
		listeCodesErreur = new ArrayList<>();
		
		int noArticleVendu = Integer.parseInt(request.getParameter("noArticle"));
		articleVendu.setNoArticleVendu(noArticleVendu);
		
		
		try {
			articleVenduManager = new ArticleVenduManager();
			articleVendu = articleVenduManager.selectArticleVenduByID(noArticleVendu);
			vendeur = articleVendu.getUtilisateur();
			vendeur = utilisateurManager.selectUtilisateurById(vendeur.getNoUtilisateur());
			categorie = articleVendu.getCategorie();
			enchere = encheresManager.selectEnchereByMeilleurOffre(articleVendu.getNoArticleVendu());
			// Aller chercher en base de données les informations de l'article
			String nomArticleVendu = articleVendu.getNomArticleVendu();
			String description = articleVendu.getDescription();
			int prixVente = 0;
			if(enchere.getMontantEnchere() > articleVendu.getMiseAPrix()){
				prixVente = enchere.getMontantEnchere();
			}else{
				prixVente = articleVendu.getMiseAPrix();
			}
			int miseAPrix = articleVendu.getMiseAPrix();
			LocalDateTime dateFinEnchere = articleVendu.getDateFinEncheres();
			String rue = vendeur.getRue();
			String codePostal = vendeur.getCodePostal();
			String ville = vendeur.getVille();

			// Afficher les données à l'écran
			request.setAttribute("articleVendu", articleVendu);
			request.setAttribute("nomArticle", nomArticleVendu);
			request.setAttribute("description", description);
			//request.setAttribute("categorie", categorie);
			request.setAttribute("prixVente", prixVente);
			request.setAttribute("miseAPrix", miseAPrix);
			request.setAttribute("dateFinEnchere", dateFinEnchere);
			request.setAttribute("rue", rue);
			request.setAttribute("codePostal", codePostal);
			request.setAttribute("ville", ville);
			request.setAttribute("nomVendeur", vendeur.getPseudo());
			rd = request.getRequestDispatcher("/WEB-INF/jsp/AffichEnchere.jsp");
			rd.forward(request, response);

		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			rd = request.getRequestDispatcher("/WEB-INF/jsp/AffichEnchere.jsp");
			rd.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Permet de gerer une proposition d'enchere
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		Enchere enchere = new Enchere();
		ArticleVendu articleVendu = new ArticleVendu();
		articleVendu.setNoArticleVendu(Integer.parseInt(request.getParameter("noArticle")));
		int valeurProposition = Integer.parseInt(request.getParameter("proposition"));
		System.out.println(valeurProposition);
		enchere.setMontantEnchere(valeurProposition);
		enchere.setArticleVendu(articleVendu);
		enchere.setDateEnchere(LocalDateTime.now());
		enchere.setUtilisateur((Utilisateur) session.getAttribute("utilisateur"));
		try {
			encheresManager.insertEnchere(enchere);
			request.setAttribute("noArticle", articleVendu.getNoArticleVendu());
			rd = request.getRequestDispatcher("/WEB-INF/jsp/AffichEnchere.jsp");
			doGet(request, response);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

}