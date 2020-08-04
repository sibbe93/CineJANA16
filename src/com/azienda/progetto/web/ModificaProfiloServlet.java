package com.azienda.progetto.web;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azienda.progetto.businessLogic.BusinessLogic;
import com.azienda.progetto.model.Utente;
import com.azienda.progetto.utils.Costanti;

@WebServlet("/ModificaProfiloServlet")
public class ModificaProfiloServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			Utente ut = (Utente) req.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_UTENTE);
			Utente staff = (Utente) req.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_STAFF);
			Utente utente = null;
			if(ut !=null){
				utente = ut;
			} else if(staff != null){
				utente = staff;
			}			
			
			String password = req.getParameter("password");
			String nome = req.getParameter("nome");
			String cognome = req.getParameter("cognome");			
			String email = req.getParameter("email");		
			
			
			
			
				if(password.length()>7) {
					
					BusinessLogic bl = (BusinessLogic) req.getServletContext().getAttribute(Costanti.CHIAVE_SERVLET_CONTEXT);
					
					String ris = bl.modifica(utente,password,nome,cognome,email);
					
					if(ris.equals("ok")) {
						
						req.setAttribute(Costanti.CHIAVE_MEX_ERRORE,"ok");
						req.getRequestDispatcher("/jsp/private/modificaprofilo.jsp").forward(req, resp);
						
					} else if(ris.equals("riprova")) {
						
						req.setAttribute(Costanti.CHIAVE_MEX_ERRORE,"usernameMail");
						req.getRequestDispatcher("/jsp/private/modificaprofilo.jsp").forward(req, resp);
						
					}	else if(ris.equals("erroreGenerico")){
						req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
					}
				} else {
					req.setAttribute(Costanti.CHIAVE_MEX_ERRORE,"passwordErrata");
					req.getRequestDispatcher("/jsp/private/modificaprofilo.jsp").forward(req, resp);
				}
			
			
		
		
		}catch(Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
		}
		
	}
	
	
}
