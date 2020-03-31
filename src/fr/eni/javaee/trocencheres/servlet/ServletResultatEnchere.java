package fr.eni.javaee.trocencheres.servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.trocencheres.bll.EncheresManager;
import fr.eni.javaee.trocencheres.bo.Encheres;
import fr.eni.javaee.trocencheres.bo.Utilisateur;
import fr.eni.javaee.trocencheres.exception.BusinessException;

/**
 * Servlet implementation class ServletResultatEnchere
 */
@WebServlet("/ServletResultatEnchere")
public class ServletResultatEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EncheresManager encheresManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletResultatEnchere() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Encheres enchere = null;
		LocalDateTime now = LocalDateTime.now();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

		try {
			enchere = encheresManager.selectEnchereByMeilleurOffre();
			if (enchere.getDateEnchere().isBefore(now)||utilisateur.getNoUtilisateur() == enchere.getNoUtilisateur()) {
				RequestDispatcher rd = this.getServletContext().getNamedDispatcher("RemportEnchere");
				rd.forward(request, response);
			} else {
				RequestDispatcher rder = this.getServletContext().getNamedDispatcher("PerdEnchere");
				utilisateur.setCredit(enchere.getMontantEnchere());
				rder.forward(request, response);
			}

		} catch (BusinessException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
