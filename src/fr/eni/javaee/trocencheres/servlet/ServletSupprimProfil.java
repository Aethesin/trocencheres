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
 * Servlet implementation class ServletSupprimProfil
 */
@WebServlet("/SupprimProfil")
public class ServletSupprimProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static UtilisateurManager umger;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSupprimProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		umger = new UtilisateurManager();
		HttpSession session = request.getSession();
		RequestDispatcher rq = null;
		//Ici on supprime le compte lorsque l'utilisateur du compte en question clique sur le bouton supprimer
		try {
			int noUtilisateur = Integer.parseInt(request.getParameter("noUtilisateur"));
			umger.supprUtilisateur(noUtilisateur);
			session.invalidate();
			rq= request.getRequestDispatcher("/Accueil");
			rq.forward(request, response);
		} catch (BusinessException e) {
			rq = request.getRequestDispatcher("/ModifProfil");
			rq.forward(request, response);
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		umger = new UtilisateurManager();
		//Ici on supprime le compte lorsque l'administrateur le décide
		HttpSession session = request.getSession();
		Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("utilisateur");
		if(utilisateurSession.getStatut() != 1){
			response.sendRedirect("accueil");
		}else{
			int noUtilisateur = Integer.parseInt(request.getParameter("idUtilisateur"));
			try {
				umger.supprUtilisateur(noUtilisateur);
				response.sendRedirect("GestionAdmin");
			} catch (BusinessException e) {
				e.printStackTrace();
				response.sendRedirect("GestionAdmin");
			}
		}
	}

}
