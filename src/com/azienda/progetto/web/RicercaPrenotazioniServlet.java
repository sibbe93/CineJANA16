package com.azienda.progetto.web;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azienda.progetto.businessLogic.BusinessLogic;
import com.azienda.progetto.model.Prenotazione;
import com.azienda.progetto.model.Spettacolo;
import com.azienda.progetto.utils.Costanti;


@WebServlet("/RicercaPrenotazioniServlet")
public class RicercaPrenotazioniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			BusinessLogic bl = (BusinessLogic) req.getServletContext().getAttribute(Costanti.CHIAVE_SERVLET_CONTEXT);
		
		String op = req.getQueryString();
		
		if(op != null && op.equals("cercaPerData")) {
			
			String dataString = req.getParameter("data");
			LocalDate data = null;
			if(!dataString.isEmpty()) {
				data = LocalDate.parse(dataString);
			}
			List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
			if(data!=null) {
				
				if(data.isBefore(LocalDate.now())) {
					
					prenotazioni = bl.getPrenotazioneDao().retrieveOggiADietro(data);
					
				/*	List<Prenotazione> prenotazioniTutte = bl.getPrenotazioneDao().retrieveByData();
					List<Prenotazione> prenotazioni1 = new ArrayList<Prenotazione>();
					
					for(int i=0 ; i<prenotazioniTutte.size() ;i++) {
						if(prenotazioniTutte.get(i).getSpettacolo().getDataSpettacolo().isAfter(LocalDateTime.now())) {
							prenotazioni1.add(prenotazioniTutte.get(i));
						}
					}
					req.setAttribute(Costanti.CHIAVE_PRENOTAZIONI_TOT, prenotazioni1);
					req.setAttribute(Costanti.CHIAVE_PRENOTAZIONI, prenotazioni);					
					req.getRequestDispatcher("/jsp/private/gestisciprenotazioni.jsp").forward(req, resp);*/
					
					
				} else {
				
				List<Prenotazione> prenotazioniTutte = bl.getPrenotazioneDao().findByData(data);				
			
				for(int i=0 ; i<prenotazioniTutte.size() ;i++) {
					if(prenotazioniTutte.get(i).getSpettacolo().getDataSpettacolo().isAfter(LocalDateTime.now())) {
						prenotazioni.add(prenotazioniTutte.get(i));
				}
			}
				}
			} else {
				prenotazioni = bl.getPrenotazioneDao().findByData(LocalDate.now());				
												
			}
				
				
			
			
			List<Prenotazione> prenotazioniTutte = bl.getPrenotazioneDao().retrieveByData();
			List<Prenotazione> prenotazioni1 = new ArrayList<Prenotazione>();
			
			for(int i=0 ; i<prenotazioniTutte.size() ;i++) {
				if(prenotazioniTutte.get(i).getSpettacolo().getDataSpettacolo().isAfter(LocalDateTime.now())) {
					prenotazioni1.add(prenotazioniTutte.get(i));
				}
			}
			req.setAttribute(Costanti.CHIAVE_PRENOTAZIONI_TOT, prenotazioni1);
			req.setAttribute(Costanti.CHIAVE_PRENOTAZIONI, prenotazioni);
			req.setAttribute(Costanti.CHIAVE_RICERCA_PRENOTAZIONI, "perData");
			req.getRequestDispatcher("/jsp/private/gestisciprenotazioni.jsp").forward(req, resp);
		
		}else if(op!= null && op.equals("cercaPerSpettacolo")) {
		
			String idS= req.getParameter("spettacolo");
			Integer id = Integer.parseInt(idS);
			
			Spettacolo spettacolo = bl.getEm().find(Spettacolo.class, id);
			
			List<Prenotazione> prenotazioniTutte = bl.getPrenotazioneDao().retrieveByData();
			List<Prenotazione> prenotazioni1 = new ArrayList<Prenotazione>();
			
			for(int i=0 ; i<prenotazioniTutte.size() ;i++) {
				if(prenotazioniTutte.get(i).getSpettacolo().getDataSpettacolo().isAfter(LocalDateTime.now())) {
					prenotazioni1.add(prenotazioniTutte.get(i));
				}
			}
			req.setAttribute(Costanti.CHIAVE_PRENOTAZIONI_TOT, prenotazioni1);
			
			req.setAttribute(Costanti.CHIAVE_PRENOTAZIONI, spettacolo.getPrenotazioni());
			req.setAttribute(Costanti.CHIAVE_RICERCA_PRENOTAZIONI, "perSpettacolo");
			req.getRequestDispatcher("/jsp/private/gestisciprenotazioni.jsp").forward(req, resp);
			
			
		}else {
		
		
		List<Prenotazione> prenotazioniTutte = bl.getPrenotazioneDao().retrieveByData();
		List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		
		for(int i=0 ; i<prenotazioniTutte.size() ;i++) {
			if(prenotazioniTutte.get(i).getSpettacolo().getDataSpettacolo().isAfter(LocalDateTime.now())) {
				prenotazioni.add(prenotazioniTutte.get(i));
			}
		}
			
		req.setAttribute(Costanti.CHIAVE_PRENOTAZIONI, prenotazioni);
		req.setAttribute(Costanti.CHIAVE_PRENOTAZIONI_TOT, prenotazioni);
		req.getRequestDispatcher("/jsp/private/gestisciprenotazioni.jsp").forward(req, resp);
		}
		}catch(NumberFormatException e) { 
		
			e.printStackTrace();
			req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "inputErrato");
			BusinessLogic bl = (BusinessLogic) req.getServletContext().getAttribute(Costanti.CHIAVE_SERVLET_CONTEXT);
			req.setAttribute(Costanti.CHIAVE_PRENOTAZIONI,bl.getPrenotazioneDao().retrieveOggiInPoi() );
			req.getRequestDispatcher("/jsp/private/gestisciprenotazioni.jsp").forward(req, resp);
		
		}catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
		}
		
	}

}
