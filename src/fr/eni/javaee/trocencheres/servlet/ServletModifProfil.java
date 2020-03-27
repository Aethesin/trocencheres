package fr.eni.javaee.trocencheres.servlet;

import java.io.IOException;

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

/**
 * Servlet implementation class ServletModifProfil
 */
@WebServlet("/ModifProfil")
public class ServletModifProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UtilisateurManager umger;
	
	/**
	 * Constructeur
	 */
	public ServletModifProfil() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		if(utilisateur != null){
			request.setAttribute("utilisateur", utilisateur);
			RequestDispatcher rd = this.getServletContext().getNamedDispatcher("ModifProfil");
			rd.forward(request, response);
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("Connexion");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Utilisateur user = null;
		umger = new UtilisateurManager();
		
		String pseudo = request.getParameter("pseudo");
		try {
			user = umger.getConnexion(pseudo);
		} catch (BusinessException e) {
			// TODO message erreur qui va  bien
			e.printStackTrace();
		}
		if (pseudo != user.getPseudo()) {
			user.setPseudo(pseudo);
		}
		//TODO verif pseudo unique
		
		String nom = request.getParameter("nom");
		try {
			user = umger.getConnexion(nom);
		} catch (BusinessException e) {
			// TODO message erreur qui va  bien
			e.printStackTrace();
		}
		if (nom != user.getNom()) {
			user.setNom(nom);
		}
		
		String prenom = request.getParameter("prenom");
		try {
			user = umger.getConnexion(prenom);
		} catch (BusinessException e) {
			// TODO message erreur qui va  bien
			e.printStackTrace();
		}
		if (prenom != user.getPrenom()) {
			user.setPrenom(prenom);
		}
		
		String email = request.getParameter("email");
		try {
			user = umger.getConnexion(email);
		} catch (BusinessException e) {
			// TODO message erreur qui va  bien
			e.printStackTrace();
		}
		if (email != user.getEmail()) {
			user.setEmail(email);
		}
		//TODO verif email unique
		
		String telephone = request.getParameter("telephone");
		try {
			user = umger.getConnexion(telephone);
		} catch (BusinessException e) {
			// TODO message erreur qui va  bien
			e.printStackTrace();
		}
		if (telephone != user.getTelephone()) {
			user.setTelephone(telephone);
		}
		
		String rue = request.getParameter("rue");
		try {
			user = umger.getConnexion(rue);
		} catch (BusinessException e) {
			// TODO message erreur qui va  bien
			e.printStackTrace();
		}
		if (rue != user.getRue()) {
			user.setRue(rue);
		}
		
		String codePostal = request.getParameter("codePostal");
		try {
			user = umger.getConnexion(codePostal);
		} catch (BusinessException e) {
			// TODO message erreur qui va  bien
			e.printStackTrace();
		}
		if (codePostal != user.getCodePostal()) {
			user.setCodePostal(codePostal);
		}
		
		String ville = request.getParameter("ville");
		try {
			user = umger.getConnexion(ville);
		} catch (BusinessException e) {
			// TODO message erreur qui va  bien
			e.printStackTrace();
		}
		if (ville != user.getVille()) {
			user.setVille(ville);
		}
		
		String motDePasse = request.getParameter("motDePasse");
		try {
			user = umger.getConnexion(motDePasse);
		} catch (BusinessException e) {
			// TODO message erreur qui va  bien
			e.printStackTrace();
		}
		if (motDePasse != user.getMotDePasse()) {
			user.setMotDePasse(motDePasse);
		}
		
	}

}
