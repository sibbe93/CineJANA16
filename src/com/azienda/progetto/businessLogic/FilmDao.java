package com.azienda.progetto.businessLogic;

import java.util.List;

import javax.persistence.EntityManager;

import com.azienda.progetto.model.Film;

public class FilmDao implements DaoInterface<Film>{
	
	private EntityManager em;
	
	public FilmDao() {
		this(null);
	}
	
	public FilmDao(EntityManager em) {
		this.em = em;
	}
		
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public void create(Film object) {
		em.persist(object);
		
	}

	@Override
	public List<Film> retrieve() {
		List<Film> listaFilm = em.createQuery("from Film",Film.class).getResultList();
		return listaFilm;
	}

	@Override
	public void update(Film object) {
		em.persist(object);
		
	}

	@Override
	public void delete(Film object) {
		em.remove(object);
		
	}

}
