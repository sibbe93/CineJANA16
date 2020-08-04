package com.azienda.progetto.web;

import java.io.IOException;
import java.time.LocalDateTime;
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
import com.azienda.progetto.utils.Costanti;


@WebServlet("/GestisciSpettacoloServlet")
public class GestisciSpettacoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String op = req.getQueryString();
		String idS = req.getParameter("id");
		Integer id = Integer.parseInt(idS);
		
		BusinessLogic bl = (BusinessLogic) req.getServletContext().getAttribute(Costanti.CHIAVE_SERVLET_CONTEXT);
		
		Spettacolo spettacolo = bl.getEm().find(Spettacolo.class, id);
		
		if(op!= null && op.equals("eliminaspett")) {
			
			String mex = bl.eliminaSpettacolo(spettacolo);
			
				if(mex!=null && mex.equals("ok")) {
					req.setAttribute(Costanti.CHIAVE_SPETTACOLO, bl.getSpettacoloDao().retrieve());
					req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "ok");
					req.getRequestDispatcher("/jsp/private/gestiscispettacolo.jsp").forward(req, resp);
				
			} else if(mex!=null && mex.equals("errore")) {
					
					req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
			}
			
		}else if (op!= null && op.equals("modificaspett")) {
				
				String filmString = req.getParameter("film");
				Integer idFilm = Integer.parseInt(filmString);
				Film film = bl.getEm().find(Film.class, idFilm);
				
				String dataOraString = req.getParameter("dataOra");
				LocalDateTime dataOra = LocalDateTime.parse(dataOraString);			
				
				String salaString = req.getParameter("sala");
				Integer idSala = Integer.parseInt(salaString);
				Sala sala= bl.getEm().find(Sala.class,idSala);
				
				String esito = bl.modificaSpettacolo (spettacolo,film, dataOra, sala);
				
				if(esito.equals("ok")) {
					req.setAttribute(Costanti.CHIAVE_SPETTACOLO, bl.getSpettacoloDao().retrieve());
					req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "modificaOk");
					req.getRequestDispatcher("/jsp/private/gestiscispettacolo.jsp").forward(req, resp);
				} else if(esito.equals("sovrapposizione")){
					req.setAttribute(Costanti.CHIAVE_SPETTACOLO, bl.getSpettacoloDao().retrieve());					
					req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "erroreSovrapposizione");
					req.getRequestDispatcher("/jsp/private/gestiscispettacolo.jsp").forward(req, resp);
				} else if (esito.equals("deviInserireTuttiICampi")) {
					req.setAttribute(Costanti.CHIAVE_SPETTACOLO, bl.getSpettacoloDao().retrieve());					
					req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "deviInserireTuttiICampi");
					req.getRequestDispatcher("/jsp/private/gestiscispettacolo.jsp").forward(req, resp);
					
				} else if (esito.equals("errore")) {
					req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
				}
				
				
				
			} else if(op!= null && op.equals("cercaspett")) {
				req.setAttribute(Costanti.CHIAVE_SPETTACOLO, spettacolo );
				req.setAttribute(Costanti.CHIAVE_FILM, bl.getFilmDao().retrieve() );
				req.setAttribute(Costanti.CHIAVE_SALA, bl.getSalaDao().retrieve() );
				req.getRequestDispatcher("/jsp/private/modificaspettacolo.jsp").forward(req, resp);
				
			}
		
	}

}
