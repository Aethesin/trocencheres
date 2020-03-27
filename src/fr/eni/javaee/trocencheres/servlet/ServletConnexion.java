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
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/Connexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager umger;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConnexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		if(session.getAttribute("utilisateur") != null){
			rd = request.getRequestDispatcher("/Accueil");
			rd.forward(request, response);
		}else{
			rd = this.getServletContext().getNamedDispatcher("login");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		try {
			String pseudo = request.getParameter("pseudo");
			String motDePasse = request.getParameter("motDePasse");
			String checkbox = request.getParameter("seSouvenir");
			System.out.println(checkbox);
			umger = new UtilisateurManager();
			Utilisateur user = new Utilisateur();
			user = umger.getConnexion(pseudo);
			if(user.getPseudo().equals(pseudo) && user.getMotDePasse().equals(motDePasse)){
				System.out.println("Connexion en tant que " + pseudo);
				session.setAttribute("utilisateur", user);
				doGet(request, response);
			}else {
				doGet(request, response);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			doGet(request, response);
		}
		
	}

}
