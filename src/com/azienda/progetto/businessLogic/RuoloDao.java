package com.azienda.progetto.businessLogic;

import java.util.List;

import javax.persistence.EntityManager;

import com.azienda.progetto.model.Ruolo;

public class RuoloDao implements DaoInterface<Ruolo> {
	
	private EntityManager em;

	public RuoloDao() {
		this(null);
	}
	
	public RuoloDao(EntityManager em) {
		this.em = em;
	}	
	
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public void create(Ruolo object) {
		em.persist(object);		
	}

	@Override
	public List<Ruolo> retrieve() {
		List<Ruolo> ruoli = em.createQuery("from Ruolo", Ruolo.class).getResultList();
		return ruoli;
	}

	@Override
	public void update(Ruolo object) {
		em.persist(object);		
	}

	@Override
	public void delete(Ruolo object) {
		em.remove(object);
		
	}
	
}
