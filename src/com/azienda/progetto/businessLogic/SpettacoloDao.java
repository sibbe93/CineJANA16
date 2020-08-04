package com.azienda.progetto.businessLogic;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.azienda.progetto.model.Film;
import com.azienda.progetto.model.Sala;
import com.azienda.progetto.model.Spettacolo;

public class SpettacoloDao implements DaoInterface<Spettacolo>{
	
	private EntityManager em;
	
	public SpettacoloDao() {
		this(null);
	}
	
	public SpettacoloDao(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public void create(Spettacolo object) {
		em.persist(object);
		
	}

	@Override
	public List<Spettacolo> retrieve() {
		List<Spettacolo> spettacoli = em.createQuery("from Spettacolo", Spettacolo.class).getResultList();
		return spettacoli;
	}
	
	public List<Spettacolo> retrieveSortedByDataAndSala(Sala sala){
		List<Spettacolo> spettacoli = em.createQuery("select x from Spettacolo x where x.sala=:n order by x.dataSpettacolo asc", Spettacolo.class).setParameter("n", sala).getResultList();
		return spettacoli;
	}
	
	public List<Spettacolo> retrieveSortedByData(){
		List<Spettacolo> spettacoli = em.createQuery("select x from Spettacolo x order by x.dataSpettacolo asc", Spettacolo.class).getResultList();
		return spettacoli;
	}

	@Override
	public void update(Spettacolo object) {
		em.persist(object);
		
	}

	@Override
	public void delete(Spettacolo object) {
		em.remove(object);		
	}
	
	public List<Spettacolo> findByData(LocalDate data){
		
		
		List<Spettacolo> spettacoli = retrieveSortedByData();
		List<Spettacolo> ris =new ArrayList<Spettacolo>();
		for(int i=0; i<spettacoli.size();i++) {			
			if (spettacoli.get(i).getDataSpettacolo().toLocalDate().equals(data)) {
				ris.add(spettacoli.get(i));
			}
		}
		return ris;
		
	}
	
	public List<Spettacolo> findByTitolo(String s){
		
		List<Spettacolo> ris = new ArrayList<Spettacolo>();
		List<Spettacolo> spettacoli = retrieveSortedByData();
		
		
		for(int i=0; i<spettacoli.size();i++) {
				
			if (spettacoli.get(i).getFilm()!=null && spettacoli.get(i).getFilm().getTitolo().toLowerCase().contains(s.toLowerCase())) {
				ris.add(spettacoli.get(i));
				}
			
		}
		return ris;
		
		
		
	}
	
	public List<Spettacolo> findByTitoloAndData(String s, LocalDate data){
		
		List<Spettacolo> spettacoli = retrieveSortedByData();
		List<Spettacolo> ris =new ArrayList<Spettacolo>();
		
		for(int i=0; i<spettacoli.size();i++) {	
			if (spettacoli.get(i).getFilm()!=null && spettacoli.get(i).getDataSpettacolo() !=null && spettacoli.get(i).getFilm().getTitolo().toLowerCase().contains(s.toLowerCase()) && spettacoli.get(i).getDataSpettacolo().toLocalDate().equals(data)) {
							
				ris.add(spettacoli.get(i));	
		}
		}
		return ris;
	}
	
public List<Spettacolo> findBySala(Sala sala){
		
		List<Spettacolo> ris = new ArrayList<Spettacolo>();
		List<Spettacolo> spettacoli = retrieve();
		
		
		for(int i=0; i<spettacoli.size();i++) {
				
			if (spettacoli.get(i).getSala()!=null && spettacoli.get(i).getSala().getId()== sala.getId()) {
				ris.add(spettacoli.get(i));
				}
			
		}
		return ris;
		
		
		
	}
	

}
