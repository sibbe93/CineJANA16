package com.azienda.progetto.businessLogic;

import java.util.List;

import javax.persistence.EntityManager;

import com.azienda.progetto.model.Sala;

public class SalaDao implements DaoInterface<Sala> {
	
	private EntityManager em;
	
	public SalaDao() {
		this(null);
	}
	
	public SalaDao(EntityManager em) {
		this.em= em;
	}
	
		
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public void create(Sala object) {
		em.persist(object);
		
	}

	@Override
	public List<Sala> retrieve() {
		List<Sala> sale = em.createQuery("from Sala",Sala.class).getResultList();
		return sale;
	}

	@Override
	public void update(Sala object) {
		em.persist(object);
		
	}

	@Override
	public void delete(Sala object) {
		em.remove(object);
		
	}

}
