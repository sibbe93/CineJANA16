package com.azienda.progetto.businessLogic;



import java.util.List;
import javax.persistence.EntityManager;

import com.azienda.progetto.model.Utente;

public class UtenteDao implements DaoInterface <Utente>  {
	
	private EntityManager em;
	
	public UtenteDao() {
		this(null);
	}
	
	public UtenteDao(EntityManager em) {
		this.em = em;
	}	

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public void create(Utente object) {
		em.persist(object);		
	}

	@Override
	public List<Utente> retrieve() {
		List<Utente> utenti = em.createQuery("from Utente order by ruoloUtente asc", Utente.class).getResultList();
		return utenti; 
	}

	@Override
	public void update(Utente object) {
		em.persist(object);		
	}

	@Override
	public void delete(Utente object) {
		em.remove(object);		
	}
	
	public List<Utente> findByUsernameAndPassword(String username, String password){
		List<Utente> utenti = em.createQuery("select x from Utente x where x.username = :n and x.password = :m",Utente.class).setParameter("n", username).setParameter("m", password).getResultList();
		return utenti;
		}
	
	
	
	
}
