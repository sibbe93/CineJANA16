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
import com.azienda.progetto.model.Spettacolo;
import com.azienda.progetto.utils.Costanti;

@WebServlet("/ricercaSpettServlet")
public class RicercaSpettServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
		BusinessLogic bl = (BusinessLogic) getServletContext().getAttribute(Costanti.CHIAVE_SERVLET_CONTEXT);
		String film = req.getParameter("film");
		String datas = req.getParameter("data");
		LocalDate data = null;		
		
		if(!datas.isEmpty()) {
			data = LocalDate.parse(datas);
			if(data.isBefore(LocalDate.now())) {
				req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "erroreData");
				req.setAttribute(Costanti.CHIAVE_FILM, bl.getFilmDao().retrieve());
				req.getRequestDispatcher("/jsp/public/ricercaspettacolo.jsp").forward(req, resp);
			}
		}
		
		
		
		
		if(film.isEmpty() && data!= null) {
			List<Spettacolo> lista = bl.getSpettacoloDao().findByData(data);
			req.setAttribute(Costanti.CHIAVE_FILM, bl.getFilmDao().retrieve());
			req.setAttribute(Costanti.CHIAVE_RICERCA_SPETT, lista);
			req.getRequestDispatcher("/preRicercaServlet").forward(req, resp);
			
			
		}else if(data==null && !film.isEmpty()) {
			
			List<Spettacolo> listaTitolo = bl.getSpettacoloDao().findByTitolo(film);
			List<Spettacolo> lista = new ArrayList<Spettacolo>();
			for(int i=0; i<listaTitolo.size();i++) {
				if(listaTitolo.get(i).getDataSpettacolo().isAfter(LocalDateTime.now())) {
					lista.add(listaTitolo.get(i));
				}
			}			
			req.setAttribute(Costanti.CHIAVE_FILM, bl.getFilmDao().retrieve());
			req.setAttribute(Costanti.CHIAVE_RICERCA_SPETT, lista);
			req.getRequestDispatcher("/jsp/public/ricercaspettacolo.jsp").forward(req, resp);
			
		} else if(data!=null && !film.isEmpty()){
			
			List<Spettacolo> lista = bl.getSpettacoloDao().findByTitoloAndData(film, data);
			req.setAttribute(Costanti.CHIAVE_FILM, bl.getFilmDao().retrieve());
			req.setAttribute(Costanti.CHIAVE_RICERCA_SPETT, lista);
			req.getRequestDispatcher("/jsp/public/ricercaspettacolo.jsp").forward(req, resp);
			
			
		} else {
			List<Spettacolo> lista = bl.getSpettacoloDao().findByData(LocalDate.now());
			req.setAttribute(Costanti.CHIAVE_FILM, bl.getFilmDao().retrieve());
			req.setAttribute(Costanti.CHIAVE_RICERCA_SPETT, lista);
			req.getRequestDispatcher("/jsp/public/ricercaspettacolo.jsp").forward(req, resp);
		}
		
		} catch(Exception e) {
			
			req.getRequestDispatcher("/jsp/public/ricercaspettacolo.jsp").forward(req, resp);
		}
		
		
	}
}
