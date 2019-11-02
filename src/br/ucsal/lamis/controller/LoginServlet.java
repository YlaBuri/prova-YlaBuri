package br.ucsal.lamis.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.lamis.DAO.UsuarioDAO;
import br.ucsal.lamis.model.Usuario;
import br.ucsal.lamis.util.BancoUtil;
import br.ucsal.lamis.util.Repositorio;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		Repositorio repositorio = (Repositorio) request.getSession().getServletContext().getAttribute("repositorio");
		
		if(UsuarioDAO.autenticar(usuario)) {
			request.getSession().setAttribute("usuario", usuario);
			response.sendRedirect("./Painel");
		}else {
			request.setAttribute("erro", "login ou senha invalido!");
			request.getRequestDispatcher("./index.jsp").forward(request, response);
		}

	}

}
