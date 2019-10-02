package br.ucsal.lamis.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucsal.lamis.model.Laboratorio;
import br.ucsal.lamis.model.Reserva;
import br.ucsal.lamis.model.Usuario;
import br.ucsal.lamis.util.Repositorio;

/**
 * Servlet implementation class ReservaSalvar
 */
@WebServlet("/ReservaSalvar")
public class ReservaSalvar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("H:mm");
	
    public ReservaSalvar() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Repositorio repositorio = (Repositorio) request.getSession().getServletContext().getAttribute("repositorio");
		
		String idLami= request.getParameter("laboratorio");
		String idUser= request.getParameter("idUsuario");
		String objetivo = request.getParameter("objetivo");
		String descricao = request.getParameter("descricao");
		String data= request.getParameter("data");
		String horaInicio= request.getParameter("horaInicio");
		String horaFinal= request.getParameter("horaFinal");
		
		
		Reserva reserva = new Reserva();
		Laboratorio l = repositorio.obterLaboratorio(Integer.parseInt(idLami));
		Usuario u = repositorio.obterUsuario(Integer.parseInt(idUser));
			
		reserva.setLaboratorio(l);
		reserva.setUsuario(u);
		reserva.setDescricao(descricao);
		reserva.setObjetivo(objetivo);
		reserva.setData(LocalDate.parse(data, dateFormat));
		reserva.setHoraInicio(LocalTime.parse(horaInicio, timeFormat));
		reserva.setHoraFinal(LocalTime.parse(horaFinal, timeFormat));
		if(validar(reserva, repositorio)) {
			repositorio.inserirReserva(reserva);
			response.sendRedirect("./ReservaLista");
		}else {
			request.setAttribute("erro", "Laboratorio Indisponivel");
			request.getRequestDispatcher("./reservaForm.jsp").forward(request, response);
		}
		
	}
	
	public boolean validar(Reserva reserva,Repositorio repositorio ) {
		boolean validacao=true;
		LocalTime inicio=reserva.getHoraInicio();
		LocalTime fim=reserva.getHoraFinal();
		for (Reserva r : repositorio.getReservas()) {
			if(r.getLaboratorio().getNome().equals(reserva.getLaboratorio().getNome())
					&& r.getData().equals(reserva.getData()) ) {
				
				if(inicio.isAfter(r.getHoraInicio()) && inicio.isBefore(r.getHoraFinal()) ||
						fim.isAfter(r.getHoraInicio()) && fim.isAfter(r.getHoraFinal())) {
					validacao=false;
				}
			}
		}		
		return validacao;
		
	}
	
	

}
