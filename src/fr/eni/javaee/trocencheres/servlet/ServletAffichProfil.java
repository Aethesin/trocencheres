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
 * Servlet implementation class ServletUseBean
 */
@WebServlet("/AffichProfil")
public class ServletAffichProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static UtilisateurManager umger;
    
	public ServletAffichProfil() {

	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String nomUtilisateur = null;
		Utilisateur utilisateur = null;
		if(session.getAttribute("utilisateur") != null){
			utilisateur =  (Utilisateur) session.getAttribute("utilisateur");
			nomUtilisateur = utilisateur.getPseudo();
			System.out.println(nomUtilisateur);
		}
		try {
			umger = new UtilisateurManager();
			nomUtilisateur = request.getParameter("pseudo");
			utilisateur = umger.getConnexion(nomUtilisateur);
			request.setAttribute("utilisateur", utilisateur);
			RequestDispatcher rd = this.getServletContext().getNamedDispatcher("profil");
			rd.forward(request, response);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
