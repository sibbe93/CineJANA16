package com.azienda.progetto.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azienda.progetto.businessLogic.BusinessLogic;
import com.azienda.progetto.model.Film;
import com.azienda.progetto.model.Sala;
import com.azienda.progetto.utils.Costanti;


@WebServlet("/PreInserimentoSpettacoloServlet")
public class PreInserimentoSpettacoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
   
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String op = req.getQueryString();
		
		BusinessLogic bl = (BusinessLogic) getServletContext().getAttribute(Costanti.CHIAVE_SERVLET_CONTEXT);
		
		List<Film> listaFilm = bl.getFilmDao().retrieve();
		List<Sala> listaSale = bl.getSalaDao().retrieve();
		req.setAttribute(Costanti.CHIAVE_SALA, listaSale);
		req.setAttribute(Costanti.CHIAVE_FILM, listaFilm);
		if(op != null && op.equals("film")) {
			req.getRequestDispatcher("/jsp/public/elencofilm.jsp").forward(req, resp);
		} else {
		
		req.getRequestDispatcher("/jsp/private/inseriscispettacolo.jsp").forward(req, resp);
		}
	}

}
