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


@WebServlet("/GestisciSalaServlet")
public class GestisciSalaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		try {
			
		
		String op = req.getQueryString();
		Integer id = Integer.parseInt(req.getParameter("id"));
		
		BusinessLogic bl = (BusinessLogic) req.getServletContext().getAttribute(Costanti.CHIAVE_SERVLET_CONTEXT);
		Sala sala = bl.getEm().find(Sala.class, id);
		
		if(op!= null && op.equals("eliminasala")) {
			
			String mex = bl.eliminaSala(sala);
			
			if(mex!=null && mex.equals("ok")) {
				
				req.setAttribute(Costanti.CHIAVE_SALA, bl.getSalaDao().retrieve());
				req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "ok");
				
				req.getRequestDispatcher("/jsp/private/gestiscisala.jsp").forward(req, resp);
				
			} else if(mex!=null && mex.equals("errore")) {
				
				req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
			}
			
		} else if(op!= null && op.equals("modificasala")) {
			
			String nome = req.getParameter("nome");
			Integer numeroP = Integer.parseInt(req.getParameter("numero"));
			
			String esito = bl.modificaSala(sala,nome,numeroP);
			
			if(esito.equals("ok")) {
				
				req.setAttribute(Costanti.CHIAVE_SALA, bl.getSalaDao().retrieve());
				req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "modificaOk");
				req.getRequestDispatcher("/jsp/private/gestiscisala.jsp").forward(req, resp);
			} else if (esito.equals("nomeSala")) {
				
				req.setAttribute(Costanti.CHIAVE_SALA, bl.getSalaDao().retrieve());
				req.setAttribute(Costanti.CHIAVE_MEX_ERRORE, "nomeSala");
				req.getRequestDispatcher("/jsp/private/gestiscisala.jsp").forward(req, resp);
				
			} else if (esito.equals("errore")) {
				req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
			}
			
		} else if (op!= null && op.equals("cercasale")) {
			
			req.setAttribute(Costanti.CHIAVE_SALA, sala);
			req.getRequestDispatcher("/jsp/private/modificasala.jsp").forward(req, resp);
			
		}
		
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/jsp/public/manutenzione.jsp").forward(req, resp);
		}
		
	}

}
