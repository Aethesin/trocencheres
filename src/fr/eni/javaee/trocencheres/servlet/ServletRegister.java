package fr.eni.javaee.trocencheres.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.trocencheres.bll.UtilisateurManager;
import fr.eni.javaee.trocencheres.bo.Utilisateur;
import fr.eni.javaee.trocencheres.exception.BusinessException;
import fr.eni.javaee.trocencheres.messages.LecteurMessage;

/**
 * Servlet implementation class ServletRegister
 */
@WebServlet("/Register")
public class ServletRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager umger; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getNamedDispatcher("register");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		umger = new UtilisateurManager();
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		//Création du compte utilisateur
		List<Integer> listeCodesErreur = new ArrayList<>();
		List<String> listeCodesErreurString = new ArrayList<>();
		String pseudo = null;
		String nom = null;
		String prenom = null;
		String email = null;
		String rue = null;
		String codePostal = null;
		String ville = null;
		String telephone = null;
		String motDePasse = null;
		String motDePasseVerif = null;
		
		
		//Récupération de tous les champs
		if(request.getParameter("pseudo").trim().length() == 0){
			listeCodesErreur.add(CodesResultatServlets.PSEUDO_ERREUR);
		}else{
			pseudo = request.getParameter("pseudo");			
		}
		if(request.getParameter("nom").trim().length() == 0){
			listeCodesErreur.add(CodesResultatServlets.NOM_ERREUR);
		}else{
			nom = request.getParameter("nom");
		}
		if(request.getParameter("prenom").trim().length() == 0){
			listeCodesErreur.add(CodesResultatServlets.PRENOM_ERREUR);
		}else{
			prenom = request.getParameter("prenom");
		}
		if(request.getParameter("email").trim().length() == 0){
			listeCodesErreur.add(CodesResultatServlets.EMAIL_ERREUR);
		}else{
			email = request.getParameter("email");
		}
		if(request.getParameter("rue").trim().length() == 0){
			listeCodesErreur.add(CodesResultatServlets.RUE_ERREUR);
		}else{
			rue = request.getParameter("rue");
		}
		if(request.getParameter("code_postal").trim().length() == 0){
			listeCodesErreur.add(CodesResultatServlets.CODE_POSTAL_ERREUR);
		}else{
			codePostal = request.getParameter("code_postal");
		}
		if(request.getParameter("ville").trim().length() == 0){
			listeCodesErreur.add(CodesResultatServlets.VILLE_ERREUR);
		}else{
			ville = request.getParameter("ville");
		}
		if(request.getParameter("telephone").trim().length() == 0){
			telephone = null;
		}else{
			telephone = request.getParameter("telephone");
		}
		if(request.getParameter("motDePasse").trim().length() == 0){
			listeCodesErreur.add(CodesResultatServlets.MDP_ERREUR);
		}
		if(!request.getParameter("motDePasse").equals(request.getParameter("motDePasseVerif"))){
			listeCodesErreur.add(CodesResultatServlets.MDP_ERREUR);
		}else{
			motDePasse = request.getParameter("motDePasse");
			motDePasseVerif = request.getParameter("motDePasseVerif");
		}
		for (Integer integer : listeCodesErreur) {
			listeCodesErreurString.add(LecteurMessage.getMessageErreur(integer));
		}
		
		//Si la servlet a récupérée des erreurs, l'utilisateur se voit redirigé sur la même page, avec les champs préremplis par rapport à ceux qu'il a déjà remplis
		if(listeCodesErreur.size() > 0){
			Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
			request.setAttribute("utilisateur", utilisateur);
			request.setAttribute("listeCodesErreurString", listeCodesErreurString);
			doGet(request, response);
		}else{
			try {
				Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
				umger.getInscription(utilisateur);
				session.setAttribute("utilisateur", utilisateur);
				response.sendRedirect("accueil");			
			} catch (BusinessException e) {
				e.printStackTrace();
				Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
				request.setAttribute("utilisateur", utilisateur);
				request.setAttribute("listeCodesErreurString", listeCodesErreurString);
				doGet(request, response);
			}			
		}
	}

}
