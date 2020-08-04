package com.azienda.progetto.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.azienda.progetto.model.Utente;
import com.azienda.progetto.utils.Costanti;

@WebFilter("/jsp/private/*")
public class FiltroAccesso implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		Utente utente = (Utente) req.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_ADMIN);
		Utente utente1 = (Utente) req.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_STAFF);
		Utente utente2 = (Utente) req.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_UTENTE);
		
		if (utente != null || utente1!=null || utente2 != null) {
			chain.doFilter(req, response);
		} else {
			req.getRequestDispatcher("/jsp/public/login.jsp").forward(req, response);
		}
		
	}
	
}
