package com.azienda.progetto.businessLogic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.azienda.progetto.model.Prenotazione;
import com.azienda.progetto.model.Spettacolo;
import com.azienda.progetto.model.Utente;

public class PrenotazioneDao implements DaoInterface<Prenotazione> {
	
	private EntityManager em;
	
	public PrenotazioneDao() {
		this(null);
	}
	
	public PrenotazioneDao(EntityManager em) {
		this.em = em;
	}
	
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public void create(Prenotazione object) {
		em.persist(object);
		
	}

	@Override
	public List<Prenotazione> retrieve() {
		List<Prenotazione> prenotazioni = em.createQuery("from Prenotazione", Prenotazione.class).getResultList();
		return prenotazioni;
	}
	
	public List<Prenotazione> retrieveByData(){
		List<Prenotazione> prenotazioni = em.createQuery("select pr from Prenotazione pr inner join Spettacolo sp on pr.spettacolo = sp.id order by dataSpettacolo asc", Prenotazione.class).getResultList();
		return prenotazioni;
	}
	
	public List<Prenotazione> findByData(LocalDate data){
		
		
		List<Prenotazione> prenotazioni = retrieve();
		List<Prenotazione> ris =new ArrayList<Prenotazione>();
		for(int i=0; i<prenotazioni.size();i++) {			
			if (prenotazioni.get(i).getSpettacolo().getDataSpettacolo().toLocalDate().equals(data)) {
				ris.add(prenotazioni.get(i));
			}
		}
		return ris;
		
	}
	
	public List<Prenotazione> retrieveOggiInPoi(){
		List<Prenotazione> prenotazioni = retrieve();
		List<Prenotazione> ris =new ArrayList<Prenotazione>();
		for(int i=0; i<prenotazioni.size();i++) {			
			if (prenotazioni.get(i).getSpettacolo().getDataSpettacolo().isAfter(LocalDateTime.now())) {
				ris.add(prenotazioni.get(i));
			}
		}
		return ris;
		
	}
	
	public List<Prenotazione> retrieveOggiADietro(LocalDate data){
		List<Prenotazione> prenotazioni = retrieve();
		List<Prenotazione> ris =new ArrayList<Prenotazione>();
		for(int i=0; i<prenotazioni.size();i++) {			
			if (prenotazioni.get(i).getSpettacolo().getDataSpettacolo().toLocalDate().isBefore(data)||prenotazioni.get(i).getSpettacolo().getDataSpettacolo().toLocalDate().equals(data)) {
				ris.add(prenotazioni.get(i));
			}
		}
		return ris;
		
	}
	
	
	
	public List<Prenotazione> findBySpettacolo(Spettacolo spettacolo){
		List<Prenotazione> prenotazioni = retrieve();
		List<Prenotazione> ris =new ArrayList<Prenotazione>();
		for(int i=0; i<prenotazioni.size();i++) {			
			if (prenotazioni.get(i).getSpettacolo().equals(spettacolo)) {
				ris.add(prenotazioni.get(i));
			}
		}
		return ris;
	}
	
	public List<Prenotazione> findByUtente (Utente u){
		List<Prenotazione> prenotazioni = retrieve();
		List<Prenotazione> ris =new ArrayList<Prenotazione>();
		for(int i=0; i<prenotazioni.size();i++) {			
			if (prenotazioni.get(i).getUtente().equals(u)) {
				ris.add(prenotazioni.get(i));
			}
		}
		return ris;
	}
	

	@Override
	public void update(Prenotazione object) {
		em.persist(object);
		
	}

	@Override
	public void delete(Prenotazione object) {
		em.remove(object);
		
	}

}
