package com.azienda.progetto.web;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.azienda.progetto.businessLogic.BusinessLogic;
import com.azienda.progetto.businessLogic.FilmDao;
import com.azienda.progetto.businessLogic.PrenotazioneDao;
import com.azienda.progetto.businessLogic.RuoloDao;
import com.azienda.progetto.businessLogic.SalaDao;
import com.azienda.progetto.businessLogic.SpettacoloDao;
import com.azienda.progetto.businessLogic.UtenteDao;
import com.azienda.progetto.utils.Costanti;

@WebServlet(value = "/iS", loadOnStartup = 1 ) 
public class InitServlet extends HttpServlet{
	
	EntityManagerFactory emf = null;
	EntityManager em = null;
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		
		
		try {
			emf = Persistence.createEntityManagerFactory("ciruzzo");
			em = emf.createEntityManager();
			UtenteDao ud = new UtenteDao(em);
			RuoloDao rd = new RuoloDao(em);
			PrenotazioneDao pd = new PrenotazioneDao(em);
			SpettacoloDao sd = new SpettacoloDao(em);
			FilmDao fd = new FilmDao(em);
			SalaDao sld = new SalaDao(em);
			BusinessLogic bl = new BusinessLogic(em, ud, rd, pd, sld, sd,fd );
			
			getServletContext().setAttribute(Costanti.CHIAVE_SERVLET_CONTEXT, bl);
					
						
		} catch (Exception e ) {
			e.printStackTrace();
		} 
	}
	
	@Override
	public void destroy() {
		em.close();
		emf.close();
	}
	
		
}
