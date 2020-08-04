package com.azienda.progetto.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azienda.progetto.businessLogic.BusinessLogic;
import com.azienda.progetto.model.Sala;
import com.azienda.progetto.utils.Costanti;


@WebServlet("/InserisciSalaServlet")
public class InserisciSalaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String nome = req.getParameter("nome");
			Integer numeroP = Integer.parseInt(req.getParameter("numero"));
			
			BusinessLogic bl = (BusinessLogic) req.getServletContext().getAttribute(Costanti.CHIAVE_SERVLET_CONTEXT);
			
			String mex = bl.aggiungiSala(nome, numeroP);
			if(mex!=null && mex.equals("ok")) {
				
				req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "ok");
				req.getRequestDispatcher("/jsp/private/inseriscisala.jsp").forward(req, resp);
				
			}else if(mex!=null && mex.equals("nomeSala")) {
				req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "nomeSala");
				req.getRequestDispatcher("/jsp/private/inseriscisala.jsp").forward(req, resp);				
				
			}else if(mex!=null && mex.equals("errore")) {
				req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
		}
		
		
	}

}
