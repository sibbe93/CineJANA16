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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String username = req.getParameter("username").toLowerCase();
			String password = req.getParameter("pass");
			
			BusinessLogic bl = (BusinessLogic) getServletContext().getAttribute(Costanti.CHIAVE_SERVLET_CONTEXT);
			
			Utente utente1 = bl.login(username, password);
			
			if (utente1 != null) {
				
				
				if(utente1.getRuolo()==null) {
					
					req.getRequestDispatcher("/jsp/public/attesa.jsp").forward(req, resp);
				} else if (utente1.getRuolo().getDescrizione().equals("admin")) {
					req.setAttribute(Costanti.CHIAVE_BENVENUTO, "benvenuto");
					req.getSession().setAttribute(Costanti.CHIAVE_SESSIONE_ADMIN, utente1);
					req.getRequestDispatcher("/jsp/private/admin.jsp").forward(req, resp);
				} else if(utente1.getRuolo().getDescrizione().equals("staff")) {
					req.setAttribute(Costanti.CHIAVE_BENVENUTO, "benvenuto");
					req.getSession().setAttribute(Costanti.CHIAVE_SESSIONE_STAFF, utente1);
					req.getRequestDispatcher("/jsp/private/staff.jsp").forward(req, resp);
				} else {
					req.setAttribute(Costanti.CHIAVE_BENVENUTO, "benvenuto");
					req.getSession().setAttribute(Costanti.CHIAVE_SESSIONE_UTENTE, utente1);
					req.getRequestDispatcher("/jsp/private/utente.jsp").forward(req, resp);
				}			
				
				
			} else {
				req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "errore");
				req.getRequestDispatcher("/jsp/public/login.jsp").forward(req, resp);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
			
			req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
		}
		
		
	}
}
