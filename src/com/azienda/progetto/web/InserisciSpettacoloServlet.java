package com.azienda.progetto.web;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azienda.progetto.businessLogic.BusinessLogic;
import com.azienda.progetto.model.Film;
import com.azienda.progetto.model.Sala;
import com.azienda.progetto.utils.Costanti;


@WebServlet("/InserisciSpettacoloServlet")
public class InserisciSpettacoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String filmStr= req.getParameter("film");		
			String salaStr= req.getParameter("sala");
			Integer filmId = Integer.parseInt(filmStr);
			Integer salaId = Integer.parseInt(salaStr);
			
			String dataOraStr= req.getParameter("dataOra");
			LocalDateTime dataOra = LocalDateTime.parse(dataOraStr);
			
			BusinessLogic bl = (BusinessLogic) getServletContext().getAttribute(Costanti.CHIAVE_SERVLET_CONTEXT);
			
			if(dataOra.isBefore(LocalDateTime.now())) {
				req.setAttribute(Costanti.CHIAVE_FILM, bl.getFilmDao().retrieve());
				req.setAttribute(Costanti.CHIAVE_SALA, bl.getSalaDao().retrieve());
				
				req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "ahNoNonPosso?");
				req.getRequestDispatcher("/jsp/private/inseriscispettacolo.jsp").forward(req, resp);
			}
			
			Film film = bl.getEm().find(Film.class, filmId);
			Sala sala = bl.getEm().find(Sala.class, salaId);
			
			
			String mex = bl.aggiungiSpettacolo(dataOra,film,sala);
			
			if(mex != null && mex.equals("ok")) {
				req.setAttribute(Costanti.CHIAVE_FILM, bl.getFilmDao().retrieve());
				req.setAttribute(Costanti.CHIAVE_SALA, bl.getSalaDao().retrieve());
				req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "ok");
				req.getRequestDispatcher("/jsp/private/inseriscispettacolo.jsp").forward(req, resp);
			} else if (mex != null && mex.equals("sovrapposizione")) {
				req.setAttribute(Costanti.CHIAVE_FILM, bl.getFilmDao().retrieve());
				req.setAttribute(Costanti.CHIAVE_SALA, bl.getSalaDao().retrieve());
				
				req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "sovrapposizione");
				req.getRequestDispatcher("/jsp/private/inseriscispettacolo.jsp").forward(req, resp);
			} else if (mex != null && mex.equals("deviInserireTuttiICampi")) {
				
				req.setAttribute(Costanti.CHIAVE_FILM, bl.getFilmDao().retrieve());
				req.setAttribute(Costanti.CHIAVE_SALA, bl.getSalaDao().retrieve());
				req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "deviInserireTuttiICampi");
				req.getRequestDispatcher("/jsp/private/inseriscispettacolo.jsp").forward(req, resp);
			} else if (mex != null && mex.equals("errore")) {
				
				req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
		}
		
		
		
	}

}
