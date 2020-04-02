package fr.eni.javaee.trocencheres.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	private CategorieManager categorieManager;
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
		categorieManager = new CategorieManager();
		ArticleVendu articleVendu = new ArticleVendu();
		Enchere enchere = null;
		Utilisateur vendeur = null;
		Utilisateur sessionUser = null;
		Categorie categorie = null;
	
		
		int noArticleVendu = 0;
		
		
		if(request.getParameter("noArticle") == null){
			noArticleVendu = (int) session.getAttribute("noArticle");
		}else{
			noArticleVendu = Integer.parseInt(request.getParameter("noArticle"));
		}
		articleVendu.setNoArticleVendu(noArticleVendu);
		try {
			articleVenduManager = new ArticleVenduManager();
			articleVendu = articleVenduManager.selectArticleVenduByID(noArticleVendu);
			vendeur = articleVendu.getUtilisateur();
			vendeur = utilisateurManager.selectUtilisateurById(vendeur.getNoUtilisateur());
			categorie = articleVendu.getCategorie();
			categorie = categorieManager.selectCategorieById(categorie.getNoCategorie());
			String libelle = categorie.getLibelle();
			enchere = encheresManager.selectEnchereByMeilleurOffre(articleVendu.getNoArticleVendu());
			
			// Aller chercher en base de données les informations de l'article
			String nomArticleVendu = articleVendu.getNomArticleVendu();
			String description = articleVendu.getDescription();
			String telephone = null;
			
			int prixVente = 0;
			String pseudoEnchereur = null;
			String pseudoSession = null;
			sessionUser = (Utilisateur) session.getAttribute("utilisateur");
			if(enchere.getMontantEnchere() > articleVendu.getMiseAPrix()){
				prixVente = enchere.getMontantEnchere();
				pseudoEnchereur = enchere.getUtilisateur().getPseudo();
				pseudoSession = sessionUser.getPseudo();
				telephone = vendeur.getTelephone();
			}else{
				prixVente = articleVendu.getMiseAPrix();
				pseudoEnchereur = vendeur.getPseudo();
			}
			
			int miseAPrix = articleVendu.getMiseAPrix();
			LocalDateTime dateFinEnchere = articleVendu.getDateFinEncheres();
			String rue = vendeur.getRue();
			String codePostal = vendeur.getCodePostal();
			String ville = vendeur.getVille();
			
			//Vérification de la date de fin enchère pour affichage du gagnant
			Boolean verifDate = false;
			LocalDateTime dateAujourdhui = LocalDateTime.now();
			if(dateAujourdhui.isBefore(dateFinEnchere)){
				verifDate = false;
			}else{
				verifDate = true;
			}
			DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			String dateFinEnchereString = formatter.format(dateFinEnchere);
			// Afficher les données à l'écran
			request.setAttribute("telephone", telephone);
			request.setAttribute("pseudoSession", pseudoSession);
			request.setAttribute("verifDate", verifDate);
			request.setAttribute("articleVendu", articleVendu);
			request.setAttribute("nomArticle", nomArticleVendu);
			request.setAttribute("description", description);
			request.setAttribute("libelle", libelle);
			request.setAttribute("prixVente", prixVente);
			request.setAttribute("pseudoEnchereur", pseudoEnchereur);			
			request.setAttribute("miseAPrix", miseAPrix);
			request.setAttribute("dateFinEnchere", dateFinEnchereString);
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
		int valeurProposition = Integer.parseInt(request.getParameter("proposition"));
		int prixVente = Integer.parseInt(request.getParameter("prixVente"));
		utilisateurManager = new UtilisateurManager();
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		Utilisateur utilisateurMeilleur = null;
		RequestDispatcher rd = null;
		Enchere enchere = new Enchere();
		ArticleVendu articleVendu = new ArticleVendu();
		if(valeurProposition <= utilisateur.getCredit()){
			if(valeurProposition > prixVente){
				articleVendu.setNoArticleVendu(Integer.parseInt(request.getParameter("noArticle")));
				String pseudo = request.getParameter("pseudoMeilleur");
				System.out.println(pseudo);
				try {
					
					utilisateurMeilleur = utilisateurManager.getConnexion(pseudo);
					if(utilisateur.getNoUtilisateur() == utilisateurMeilleur.getNoUtilisateur()){
						utilisateur.setCredit(utilisateur.getCredit() - valeurProposition + prixVente);
						utilisateurManager.modifUtilisateur(utilisateur);
					}else{
						utilisateurMeilleur.setCredit(utilisateurMeilleur.getCredit() + prixVente);
						utilisateurManager.modifUtilisateur(utilisateurMeilleur);						
						utilisateur.setCredit(utilisateur.getCredit() - valeurProposition);
						utilisateurManager.modifUtilisateur(utilisateur);	
					}
					enchere.setMontantEnchere(valeurProposition);
					enchere.setArticleVendu(articleVendu);
					enchere.setDateEnchere(LocalDateTime.now());
					enchere.setUtilisateur((Utilisateur) session.getAttribute("utilisateur"));
					encheresManager.insertEnchere(enchere);
					request.setAttribute("noArticle", articleVendu.getNoArticleVendu());
					rd = request.getRequestDispatcher("/WEB-INF/jsp/AffichEnchere.jsp");
					doGet(request, response);
				} catch (BusinessException e1) {
					e1.printStackTrace();
				}
			}else{
				System.out.println("Proposition inférieur");
				doGet(request, response);
			}
		}else{
			System.out.println("Pas assez de crédit");
			doGet(request, response);			
		}
		
	}

}