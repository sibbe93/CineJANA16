package com.azienda.progetto.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azienda.progetto.businessLogic.BusinessLogic;
import com.azienda.progetto.model.Sala;
import com.azienda.progetto.utils.Costanti;


@WebServlet("/PreRicercaSalaServlet")
public class PreRicercaSalaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			BusinessLogic bl = (BusinessLogic) req.getServletContext().getAttribute(Costanti.CHIAVE_SERVLET_CONTEXT);
		
			List<Sala> sale = bl.getSalaDao().retrieve();
		
			if(!sale.isEmpty()) {
				
				req.setAttribute(Costanti.CHIAVE_SALA, sale);
				req.getRequestDispatcher("/jsp/private/gestiscisala.jsp").forward(req, resp);
			} else if(sale.isEmpty()) {
				
				req.setAttribute(Costanti.CHIAVE_SALA, sale);
				req.getRequestDispatcher("/jsp/private/gestiscisala.jsp").forward(req, resp);
				
				}
		
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
		}
		
		
	}

}
