package fr.eni.javaee.trocencheres.servlet;

import java.io.IOException;
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
 * Servlet implementation class ServletDesactiverCompte
 */
@WebServlet("/DesactiverCompte")
public class ServletDesactiverCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UtilisateurManager utilisateurManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDesactiverCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Ici cette méthode permet de désactiver un compte, passer le statut de 0 à 2 en base de données
		utilisateurManager = new UtilisateurManager();
		HttpSession session = request.getSession();
		Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("utilisateur");
		Utilisateur utilisateur = null;
		if(utilisateurSession.getStatut() != 1){
			response.sendRedirect("accueil");
		}else{
			int noUtilisateur = Integer.parseInt(request.getParameter("idUtilisateur"));
			try {
				utilisateur = utilisateurManager.selectUtilisateurById(noUtilisateur);
				utilisateur.setStatut((short) 2);
				utilisateurManager.modifUtilisateur(utilisateur);
				response.sendRedirect("GestionAdmin");
			} catch (BusinessException e) {
				e.printStackTrace();
				response.sendRedirect("GestionAdmin");
			}
		}
	}

}
