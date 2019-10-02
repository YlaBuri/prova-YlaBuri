package br.ucsal.lamis.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.lamis.util.Repositorio;

/**
 * Servlet implementation class ExcluirReserva
 */
@WebServlet("/ReservaExcluir")
public class ReservaExcluir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ReservaExcluir() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Repositorio repositorio = (Repositorio) request.getSession().getServletContext().getAttribute("repositorio");
		String id = request.getParameter("id");	
		repositorio.removerReserva(Integer.parseInt(id));
		response.sendRedirect("./ReservaLista");
	}

}
