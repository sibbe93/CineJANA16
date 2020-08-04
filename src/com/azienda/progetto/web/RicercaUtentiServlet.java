package com.azienda.progetto.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azienda.progetto.businessLogic.BusinessLogic;
import com.azienda.progetto.model.Utente;
import com.azienda.progetto.utils.Costanti;

@WebServlet("/ricercaUtenti")
public class RicercaUtentiServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			BusinessLogic bl = (BusinessLogic) req.getServletContext().getAttribute(Costanti.CHIAVE_SERVLET_CONTEXT);
					
			List<Utente> utenti = bl.getUtenteDao().retrieve();		
			
			req.setAttribute(Costanti.CHIAVE_REQUEST, utenti);
			
			req.getRequestDispatcher("/jsp/private/ricercalistautenti.jsp").forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
