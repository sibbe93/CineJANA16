package com.azienda.progetto.web;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azienda.progetto.businessLogic.BusinessLogic;
import com.azienda.progetto.model.Prenotazione;
import com.azienda.progetto.model.Spettacolo;
import com.azienda.progetto.model.Utente;
import com.azienda.progetto.utils.Costanti;


@WebServlet("/EliminaPrenotazioneServlet")
public class EliminaPrenotazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			Utente ut = (Utente) req.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_UTENTE);
			Utente st = (Utente) req.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_STAFF);
			Utente ad = (Utente) req.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_ADMIN);
			Utente utente = null;
			if(ut!=null) {
				utente = ut;
			} else if(st!=null) {
				utente = st;
			} else if(ad!=null) {
				utente = ad;
			}	
			
			
			BusinessLogic bl = (BusinessLogic) getServletContext().getAttribute(Costanti.CHIAVE_SERVLET_CONTEXT);
			
			
			if(utente.getRuolo().getDescrizione().equals("admin")){
				
				String op = req.getQueryString();
				
				if(op!=null && op.equals("eliminaPerData")) {
					
					LocalDate data = LocalDate.parse(req.getParameter("data"));
					String mex = null;
					if(data!=null) {
						mex = bl.eliminaPrenotazionePerData(data);
					} else {
						mex = bl.eliminaPrenotazionePerData(LocalDate.now());
					}
					
					
					if(mex!=null && mex.equals("ok")) {
						req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "ok");
						req.setAttribute(Costanti.CHIAVE_PRENOTAZIONI, bl.getPrenotazioneDao().findByData(data));
						req.setAttribute(Costanti.CHIAVE_PRENOTAZIONI_TOT, bl.getPrenotazioneDao().retrieveOggiInPoi());
						req.getRequestDispatcher("/jsp/private/gestisciprenotazioni.jsp").forward(req, resp);
					} else if(mex!=null && mex.equals("errore")) {
						req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
					}
					
					
				}else if(op!=null && op.equals("eliminaPerSpettacolo")) {
					Integer id = Integer.parseInt(req.getParameter("spettacolo"));
					Spettacolo spettacolo = bl.getEm().find(Spettacolo.class, id);
				
					String mex = bl.eliminaPrenotazionePerSpettacolo(spettacolo);
					
					if(mex!=null && mex.equals("ok")) {
						req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "ok");
						req.setAttribute(Costanti.CHIAVE_PRENOTAZIONI, spettacolo.getPrenotazioni());
						req.setAttribute(Costanti.CHIAVE_PRENOTAZIONI_TOT, bl.getPrenotazioneDao().retrieveOggiInPoi());
						req.getRequestDispatcher("/jsp/private/gestisciprenotazioni.jsp").forward(req, resp);
					} else if(mex!=null && mex.equals("errore")) {
						req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
					}
					
				}else {
					Integer id = Integer.parseInt(req.getParameter("id"));
					
					Prenotazione prenotazione = bl.getEm().find(Prenotazione.class, id);
					String mex = bl.eliminaPrenotazioneAdmin(prenotazione);
					
				if(mex!=null && mex.equals("ok")) {					
					req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "ok");					
					req.setAttribute(Costanti.CHIAVE_PRENOTAZIONI, bl.getPrenotazioneDao().retrieveOggiInPoi());
					req.setAttribute(Costanti.CHIAVE_PRENOTAZIONI_TOT, bl.getPrenotazioneDao().retrieveOggiInPoi());
					req.getRequestDispatcher("/jsp/private/gestisciprenotazioni.jsp").forward(req, resp);
				} else if(mex!=null && mex.equals("errore")) {
					req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
				}
				
				}
				
				
				
				
			} else if(utente.getRuolo().getDescrizione().equals("utente") || utente.getRuolo().getDescrizione().equals("staff")) {
				
				Integer id = Integer.parseInt(req.getParameter("id"));
				
				Prenotazione prenotazione = bl.getEm().find(Prenotazione.class, id);
				String mex = bl.eliminaPrenotazione(prenotazione); 
			
		
			if(mex!=null && mex.equals("ok")) {
				req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "ok");
				req.getRequestDispatcher("/jsp/private/gestiscitueprenotazioni.jsp").forward(req, resp);
			} else if(mex!=null && mex.equals("unOraSpettacolo")) {
				req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "unOraSpettacolo");
				req.getRequestDispatcher("/jsp/private/gestiscitueprenotazioni.jsp").forward(req, resp);
				
			} else if(mex!=null && mex.equals("errore")) {
				req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
			}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
		}
		
		
		
		
		
	}

}
