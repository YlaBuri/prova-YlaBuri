package br.ucsal.lamis.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.lamis.model.Laboratorio;
import br.ucsal.lamis.util.Repositorio;

/**
 * Servlet implementation class LaboratorioForm
 */
@WebServlet("/LaboratorioForm")
public class LaboratorioFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter("id");
		Laboratorio laboratorio = null;
		Repositorio repositorio = (Repositorio) request.getSession().getServletContext().getAttribute("repositorio");
		if(sid !=null ) {
			laboratorio = repositorio.obterLaboratorio(Integer.parseInt(sid));
		}
		request.setAttribute("laboratorio", laboratorio);
		request.setAttribute("blocos", repositorio.getBlocos());
		request.getRequestDispatcher("laboratorioForm.jsp").forward(request, response);
		
	}


}
