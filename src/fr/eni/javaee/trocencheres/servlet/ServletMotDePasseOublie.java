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
 * Servlet implementation class ServletMotDePasseOublie
 */
@WebServlet("/Forgot")
public class ServletMotDePasseOublie extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static UtilisateurManager umger;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMotDePasseOublie() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rq = this.getServletContext().getNamedDispatcher("forgot");
		rq.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		umger = new UtilisateurManager();
		/**
		 * Récupération du pseudo (unique en base de données) pour la modification du mot de passe
		 */
		String pseudo = request.getParameter("pseudo");
		try {
			Utilisateur utilisateur = umger.getConnexion(pseudo);
			String mdp = request.getParameter("nouveauMDP");
			String mdpConfirm = request.getParameter("nouveauMDPConfirmation");
			if(mdp.equals(mdpConfirm)){
				utilisateur.setMotDePasse(mdp);
				umger.modifUtilisateur(utilisateur);
				session.setAttribute("utilisateur", utilisateur);
				response.sendRedirect("accueil");
			}else{
				doGet(request, response);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			doGet(request, response);
		}
	}

}
