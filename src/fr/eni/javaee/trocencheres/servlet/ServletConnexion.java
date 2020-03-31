package fr.eni.javaee.trocencheres.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
		Cookie[] cookies = request.getCookies();
		
		RequestDispatcher rd = null;
		if(session.getAttribute("utilisateur") != null){
			response.sendRedirect("accueil");
		}else{
			if(cookies.length > 0){
				for (Cookie cookie : cookies) {
					request.setAttribute(cookie.getName(), cookie.getValue());				
				}				
			}
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
		Cookie[] cookies = request.getCookies();
		try {
			String pseudo = request.getParameter("pseudo");
			String motDePasse = request.getParameter("motDePasse");
			String checkbox = request.getParameter("seSouvenir");
			if(checkbox == null){
				checkbox = "off";
			}
			
			umger = new UtilisateurManager();
			Utilisateur user = new Utilisateur();
			user = umger.getConnexion(pseudo);
			if(user.getPseudo().equals(pseudo) && user.getMotDePasse().equals(motDePasse)){
				System.out.println("Connexion en tant que " + pseudo);
				if(checkbox.equals("on")){					
					Cookie cookiePseudo = new Cookie("pseudo", pseudo);
					cookiePseudo.setMaxAge(-1);
					Cookie cookieMDP = new Cookie("motDePasse", motDePasse);
					cookieMDP.setMaxAge(-1);
					response.addCookie(cookiePseudo);
					response.addCookie(cookieMDP);
				}else{
					Cookie cookiePseudo = new Cookie("pseudo", "");
					cookiePseudo.setMaxAge(-1);
					Cookie cookieMDP = new Cookie("motDePasse", "");
					cookieMDP.setMaxAge(-1);
					response.addCookie(cookiePseudo);
					response.addCookie(cookieMDP);
				}
				session.setMaxInactiveInterval(300);
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
