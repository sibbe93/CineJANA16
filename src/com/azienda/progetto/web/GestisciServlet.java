package com.azienda.progetto.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azienda.progetto.businessLogic.BusinessLogic;
import com.azienda.progetto.model.Utente;
import com.azienda.progetto.utils.Costanti;

@WebServlet("/gestisciServlet")
public class GestisciServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String ris = req.getQueryString();		//prendiamo l'operazione dalla jsp		
			String id = req.getParameter("id");		//prendiamo l'id dell'utente sul quale effettuarla
			Integer iD = Integer.parseInt(id);		
			
			BusinessLogic bl = (BusinessLogic) req.getServletContext().getAttribute(Costanti.CHIAVE_SERVLET_CONTEXT);		
			Utente u = bl.getEm().find(Utente.class, iD); //prendiamo l'utente sul quale fare l'operazione
		
			if(ris.equals("elimina")) {
				String mex = bl.elimina(u);
				req.setAttribute(Costanti.CHIAVE_REQUEST, bl.getUtenteDao().retrieve());
				if(mex != null && mex.equals("ok")) {
					req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "ok");
					req.getRequestDispatcher("/jsp/private/ricercalistautenti.jsp").forward(req, resp);
				} else if(mex != null && mex.equals("errore")) {
					req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
				}
				
			}
			else if(ris.equals("accettaUt")) {
				if(u.getRuolo()==null) {
					String mex = bl.diventaUtente(u);
					req.setAttribute(Costanti.CHIAVE_REQUEST, bl.getUtenteDao().retrieve());
					if(mex != null && mex.equals("ok")) {
						req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "promozioneEffettuata");
						bl.inviaMail(u);
						req.getRequestDispatcher("/jsp/private/ricercalistautenti.jsp").forward(req, resp);
					} else if(mex != null && mex.equals("errore")) {
						req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
					}
				} else {
				String mex = bl.diventaUtente(u);
				req.setAttribute(Costanti.CHIAVE_REQUEST, bl.getUtenteDao().retrieve());
				if(mex != null && mex.equals("ok")) {
					req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "promozioneEffettuata");
					req.getRequestDispatcher("/jsp/private/ricercalistautenti.jsp").forward(req, resp);
				} else if(mex != null && mex.equals("errore")) {
					req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
				}
				
			}
			}
			else if (ris.equals("accettaSt")) {
				String mex = bl.diventaStaff(u);
				req.setAttribute(Costanti.CHIAVE_REQUEST, bl.getUtenteDao().retrieve());
				if(mex != null && mex.equals("ok")) {
					req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "promozioneEffettuata");
					req.getRequestDispatcher("/jsp/private/ricercalistautenti.jsp").forward(req, resp);
				} else if(mex != null && mex.equals("errore")) {
					req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
				}
				
				
			}		
			} catch(Exception e) {
				e.printStackTrace();
				req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
			}
		
		
		
		
		
	}
	
}
