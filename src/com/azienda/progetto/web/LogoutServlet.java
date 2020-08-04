package com.azienda.progetto.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azienda.progetto.model.Utente;
import com.azienda.progetto.utils.Costanti;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			Utente ut = (Utente) req.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_UTENTE);
			Utente st = (Utente) req.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_STAFF);
			Utente ad = (Utente) req.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_ADMIN);
			
			
			if(ut != null) {
				
				req.getSession().removeAttribute(Costanti.CHIAVE_SESSIONE_UTENTE);
			} else if (st != null) {
				
				req.getSession().removeAttribute(Costanti.CHIAVE_SESSIONE_STAFF);
			} else if (ad!= null) {
				
				req.getSession().removeAttribute(Costanti.CHIAVE_SESSIONE_ADMIN);
			}		
			
			req.getRequestDispatcher("/jsp/public/homepage.jsp").forward(req, resp);	
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
}
