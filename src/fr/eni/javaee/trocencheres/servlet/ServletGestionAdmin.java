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

/**
 * Servlet implementation class ServletGestionAdmin
 */
/**
 * Classe en charge d'afficher tous les utilisateurs de la base de données dans l'onglet Gestion Admins, lorsque l'utilisateur est administrateur
 * @author D2WM2020_Team1
 * @version trocencheres - v1.0
 * @date 3 avr. 2020
 */
@WebServlet("/GestionAdmin")
public class ServletGestionAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UtilisateurManager utilisateurManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestionAdmin() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
		utilisateurManager = new UtilisateurManager();
		if(utilisateur.getStatut() != 1){
			response.sendRedirect("accueil");
		}else{
			try {
				listeUtilisateurs = utilisateurManager.selectToutLeMonde();
			} catch (BusinessException e) {
				e.printStackTrace();
				listeUtilisateurs = null;
			}
			request.setAttribute("listeUtilisateurs", listeUtilisateurs);
			rd = this.getServletContext().getNamedDispatcher("GestionAdmin");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
