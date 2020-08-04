package com.azienda.progetto.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azienda.progetto.businessLogic.BusinessLogic;
import com.azienda.progetto.model.Spettacolo;
import com.azienda.progetto.model.Utente;
import com.azienda.progetto.utils.Costanti;

@WebServlet("/prenotazioneServlet")
public class PrenotazioneServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			BusinessLogic bl = (BusinessLogic) getServletContext().getAttribute(Costanti.CHIAVE_SERVLET_CONTEXT);
			Utente ut = (Utente) req.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_UTENTE);
			Utente st = (Utente) req.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_STAFF);
			Utente u = null;
			
			if(ut!=null) {
				u=ut;
			} else if(st!=null) {
				u=st;
			}
			
			String id = req.getParameter("id");
			Integer iD = Integer.parseInt(id);
			
			String numPosti = req.getParameter("numeroPostiPrenotati");
			Integer postiPrenotati = Integer.parseInt(numPosti);
			
			Spettacolo s = bl.getEm().find(Spettacolo.class, iD);
			
			if(postiPrenotati >0 && postiPrenotati <7) {
				String mex = bl.effettuaPrenotazione(u,s,postiPrenotati);
				
				if(mex!=null && mex.equals("ok")) {					
					
					req.setAttribute(Costanti.CHIAVE_PRENOTAZIONE, "ok");
					bl.inviaMail(u, u.getPrenotazioni().get(u.getPrenotazioni().size()-1));
					if(u.getRuolo().getDescrizione().equals("utente")) {
						req.getRequestDispatcher("/jsp/private/utente.jsp").forward(req, resp);
					} else if(u.getRuolo().getDescrizione().equals("staff")) {
						req.getRequestDispatcher("/jsp/private/staff.jsp").forward(req, resp);
					}
					
					
				} else if (mex != null && mex.equals("postiNonDisponibili")) {
					
					req.setAttribute(Costanti.CHIAVE_PRENOTAZIONE, "postiNonDisponibili");
					req.setAttribute(Costanti.CHIAVE_SPETTACOLO,s );
					req.getRequestDispatcher("/jsp/private/prenota.jsp").forward(req, resp);
				} else if (mex != null && mex.equals("erroreGenerico")) {
					req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
				}
				
				
				
				
			} else {
				
				req.setAttribute(Costanti.CHIAVE_PRENOTAZIONE, "erroreNumeroPosti");
				req.getRequestDispatcher("/jsp/private/prenota.jsp").forward(req, resp);
			}
				
			
			
		} catch (Exception e ) {
			
			e.printStackTrace();			
			req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
		}
		
		
		
	}
	
	
}
