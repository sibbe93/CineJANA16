package com.azienda.progetto.web;

import java.io.IOException;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azienda.progetto.businessLogic.BusinessLogic;
import com.azienda.progetto.model.Film;
import com.azienda.progetto.utils.Costanti;


@WebServlet("/GestisciFilmServlet")
public class GestisciFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
		}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String op = req.getQueryString();
			String idS= req.getParameter("id");
			Integer id = Integer.parseInt(idS);
			
			BusinessLogic bl = (BusinessLogic) req.getServletContext().getAttribute(Costanti.CHIAVE_SERVLET_CONTEXT);		
			Film film = bl.getEm().find(Film.class, id); 
			
			if(op!= null && op.equals("elimina")) {
				
				String mex = bl.eliminaFilm(film);
				if(mex!=null && mex.equals("ok")) {
					req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "eliminaOk");
					req.setAttribute(Costanti.CHIAVE_FILM, bl.getFilmDao().retrieve());					
					req.getRequestDispatcher("/jsp/public/elencofilm.jsp").forward(req, resp);
				} else if (mex!=null && mex.equals("errore")) {
					req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
				}
			} 
			
			else if(op!= null && op.equals("modifica")) {
				String titolo = req.getParameter("titolo");
				String genere = req.getParameter("genere");
				
				String durataString = req.getParameter("durata");
				LocalTime durata = LocalTime.parse(durataString);
				
				String trama = req.getParameter("trama");
				String trailer = req.getParameter("trailer");				
								
				
				String esito = bl.modificaFilm(film,titolo,genere,durata,trama,trailer);
				if(esito.equals("ok")) {
					req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "ok");
					req.setAttribute(Costanti.CHIAVE_FILM, bl.getFilmDao().retrieve());
					req.getRequestDispatcher("/jsp/public/elencofilm.jsp").forward(req, resp);
				} else if(esito.equals("errore")){
					
					req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
				}
				
			} else if(op!= null && op.equals("cercafilm")) {
				req.setAttribute(Costanti.CHIAVE_FILM, film);
				req.getRequestDispatcher("/jsp/private/modificafilm.jsp").forward(req, resp);
				
			}
			
		} catch (Exception e) {
			
			req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
		}
		
	}

}
