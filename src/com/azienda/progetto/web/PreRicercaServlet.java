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
import com.azienda.progetto.model.Spettacolo;
import com.azienda.progetto.model.Utente;
import com.azienda.progetto.utils.Costanti;

@WebServlet("/preRicercaServlet")
public class PreRicercaServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		
				
		BusinessLogic bl = (BusinessLogic) getServletContext().getAttribute(Costanti.CHIAVE_SERVLET_CONTEXT);
		
		List<Film> listaFilm = bl.getFilmDao().retrieve();
		
		
		req.setAttribute(Costanti.CHIAVE_FILM, listaFilm);
		
		String elencofilm = req.getQueryString();
		
		
		String mex = req.getParameter("operazione");
		
		
		Utente ut = (Utente) req.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_UTENTE);
		Utente st = (Utente) req.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_STAFF);
		Utente ad = (Utente) req.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_ADMIN);
		Utente u = null;
		
		if(ut != null) {
			u = ut;
		} else if (st != null) {
			u=st;
		} else if (ad!= null) {
			u=ad;
		}		
		
		if(u!= null && u.getRuolo().getDescrizione().equals("admin") && mex !=null && mex.equals("modificaFilm")) {
			req.getRequestDispatcher("/jsp/public/elencofilm.jsp").forward(req, resp);
			
		} else if(u!= null &&(( u.getRuolo().getDescrizione().equals("admin"))||(u.getRuolo().getDescrizione().equals("staff"))) && mex !=null && mex.equals("inserisciSpettacolo")) {
			
			List<Sala> listaSale = bl.getSalaDao().retrieve();
			req.setAttribute(Costanti.CHIAVE_SALA, listaSale);
			req.getRequestDispatcher("/jsp/private/inseriscispettacolo.jsp").forward(req, resp);
			
		}else if ( u!=null && !u.getRuolo().getDescrizione().equals("admin")&&  elencofilm!=null && elencofilm.equals("elencofilm") ){
			
			//req.setAttribute(Costanti.CHIAVE_FILM, bl.getFilmDao().retrieve());
			
			req.getRequestDispatcher("/jsp/public/elencofilm.jsp").forward(req, resp);
			
		} else if  (u==null&& elencofilm!=null &&elencofilm.equals("elencofilm"))  {
			
			//req.setAttribute(Costanti.CHIAVE_FILM, bl.getFilmDao().retrieve());
			
			req.getRequestDispatcher("/jsp/public/elencofilm.jsp").forward(req, resp);
			
		}
		/*else if(u==null&&elencofilm!=null &&elencofilm.equals("cerca")) {
			req.getRequestDispatcher("/jsp/public/ricercaspettacolo.jsp").forward(req, resp);
			
		}*/
		else if(u==null) {
			req.getRequestDispatcher("/jsp/public/elencofilm.jsp").forward(req, resp);
			
		}
		
		else {
			
			req.getRequestDispatcher("/jsp/public/ricercaspettacolo.jsp").forward(req, resp);
		}
		
		
		
		
		}catch(Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/jsp/public/manutenzione.jsp");
		}
	}
	
}
