package com.azienda.progetto.web;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azienda.progetto.businessLogic.BusinessLogic;
import com.azienda.progetto.utils.Costanti;

@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		try {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String nome = req.getParameter("nome");
			String cognome = req.getParameter("cognome");
			String dataNascita = req.getParameter("data");
			String email = req.getParameter("email");
			
			LocalDate dataNascita1 = LocalDate.parse(dataNascita);
			
			if(dataNascita1.isBefore(LocalDate.now().minusYears(12L))) {
				if(password.length()>7) {
					BusinessLogic bl = (BusinessLogic) req.getServletContext().getAttribute(Costanti.CHIAVE_SERVLET_CONTEXT);
					
					String ris = bl.registrazione(username, password, nome, cognome, dataNascita1, email);
					
					if(ris.equals("ok")) {
						req.getRequestDispatcher("/jsp/public/attesa.jsp").forward(req, resp);
						
					} else if(ris.equals("riprova")) {
						
						req.setAttribute(Costanti.CHIAVE_MEX_ERRORE,"usernameMail");
						req.getRequestDispatcher("/jsp/public/registrazione.jsp").forward(req, resp);
						
					}	else {
						req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
					}
				} else {
					req.setAttribute(Costanti.CHIAVE_MEX_ERRORE,"passwordErrata");
					req.getRequestDispatcher("/jsp/public/registrazione.jsp").forward(req, resp);
				}
			} else {
				req.setAttribute(Costanti.CHIAVE_MEX_ERRORE,"nascitaErrata");
				req.getRequestDispatcher("/jsp/public/registrazione.jsp").forward(req, resp);
			}
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
}
