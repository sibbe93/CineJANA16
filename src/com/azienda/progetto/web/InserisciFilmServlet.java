package com.azienda.progetto.web;

import java.io.IOException;
import java.time.LocalTime;

import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.azienda.progetto.businessLogic.BusinessLogic;
import com.azienda.progetto.model.Film;
import com.azienda.progetto.utils.Costanti;


@WebServlet("/InserisciFilmServlet")
public class InserisciFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;     
    
    

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			
		try {
			
			
			String titolo = req.getParameter("titolo");
			String genere = req.getParameter("genere");
			
			String durataString = req.getParameter("durata");
			LocalTime durata = LocalTime.parse(durataString);
			
			String trama = req.getParameter("trama");
			String trailer = req.getParameter("trailer");
			
			
			BusinessLogic bl = (BusinessLogic) getServletContext().getAttribute(Costanti.CHIAVE_SERVLET_CONTEXT);
			
			String mex = bl.inserisciFilm(titolo, durata, genere, trama, trailer);
			
			if(mex.equals("ok")) {
				
				req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "ok");
				req.getRequestDispatcher("/jsp/private/inseriscifilm.jsp").forward(req, resp);
				
			} else if (mex.equals("tooLong")) {
				req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "tooLong");
				req.getRequestDispatcher("/jsp/private/inseriscifilm.jsp").forward(req, resp);
			} else if (mex.equals("erroreGenerico")){
				
				req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
			}
			
			
		}
		 catch (Exception e) {
			
			e.printStackTrace();
			req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
			
		} 
		
		
		
		
	}

}
