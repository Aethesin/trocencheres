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
 * Servlet implementation class ServletActiverCompte
 */
/**
 * Classe en charge d'activer le compte d'un utilisateur, lorsqu'il a été désactivé auparavant, çàd passer le statut dans la base de données de 2 à 0
 * @author D2WM2020_Team1
 * @version trocencheres - v1.0
 * @date 3 avr. 2020
 */
@WebServlet("/ActiverCompte")
public class ServletActiverCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UtilisateurManager utilisateurManager;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletActiverCompte() {
        super();
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
				utilisateur.setStatut((short) 0);
				utilisateurManager.modifUtilisateur(utilisateur);
				response.sendRedirect("GestionAdmin");
			} catch (BusinessException e) {
				e.printStackTrace();
				response.sendRedirect("GestionAdmin");
			}
		}
	}

}
