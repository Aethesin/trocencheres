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
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		if(utilisateur != null){
			request.setAttribute("utilisateur", utilisateur);
			rd = this.getServletContext().getNamedDispatcher("ModifProfil");
			rd.forward(request, response);
		}else{
			rd = request.getRequestDispatcher("Connexion");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rq = null;
		Utilisateur user = null;
		umger = new UtilisateurManager();
		
		try {
			String pseudo = request.getParameter("pseudo");
			user = umger.getConnexion(pseudo);
			if (pseudo != user.getPseudo()) {
				user.setPseudo(pseudo);
			}
			//TODO verif pseudo unique
			
			String nom = request.getParameter("nom");
			if (nom != user.getNom()) {
				user.setNom(nom);
			}
			
			String prenom = request.getParameter("prenom");
			if (prenom != user.getPrenom()) {
				user.setPrenom(prenom);
			}
			
			String email = request.getParameter("email");
			if (email != user.getEmail()) {
				user.setEmail(email);
			}
			//TODO verif email unique
			
			String telephone = request.getParameter("telephone");
			if (telephone != user.getTelephone()) {
				user.setTelephone(telephone);
			}
			
			String rue = request.getParameter("rue");
			if (rue != user.getRue()) {
				user.setRue(rue);
			}
			
			String codePostal = request.getParameter("codePostal");
			if (codePostal != user.getCodePostal()) {
				user.setCodePostal(codePostal);
			}
			
			String ville = request.getParameter("ville");
			if (ville != user.getVille()) {
				user.setVille(ville);
			}
			
			String motDePasse = request.getParameter("motDePasse");
			if(motDePasse.trim().length() == 0){
				motDePasse = user.getMotDePasse();
			}
			
			if (motDePasse != user.getMotDePasse()) {
				user.setMotDePasse(motDePasse);
			}
			
			int noUtilisateur = Integer.parseInt(request.getParameter("noUtilisateur"));
			user.setNoUtilisateur(noUtilisateur);
			
			try {
				umger.modifUtilisateur(user);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			rq = request.getRequestDispatcher("/AffichProfil");
			rq.forward(request, response);
		} catch (BusinessException e) {
			// TODO message erreur qui va  bien
			e.printStackTrace();
		}
	}

}
