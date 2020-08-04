package com.azienda.progetto.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azienda.progetto.businessLogic.BusinessLogic;
import com.azienda.progetto.model.Spettacolo;
import com.azienda.progetto.utils.Costanti;


@WebServlet("/PrePrenotazioneServlet")
public class PrePrenotazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String idSpettString = req.getParameter("id");
			Integer idSpett = Integer.parseInt(idSpettString);
			
			BusinessLogic bl = (BusinessLogic) getServletContext().getAttribute(Costanti.CHIAVE_SERVLET_CONTEXT);
			
			Spettacolo spettacolo = bl.getEm().find(Spettacolo.class, idSpett);
			
			req.setAttribute(Costanti.CHIAVE_SPETTACOLO, spettacolo);
			req.getRequestDispatcher("/jsp/private/prenota.jsp").forward(req, resp);
		
		} catch (Exception e) {

			e.printStackTrace();
			req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
		}
		
		
		
	}

}
