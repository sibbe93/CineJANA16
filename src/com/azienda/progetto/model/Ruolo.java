package com.azienda.progetto.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Ruolo {
	
	@Column( nullable = false)
	private String descrizione;
	
	@OneToMany(mappedBy = "ruolo")
	private List<Utente> utenti;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	public Ruolo() {
		this(null);
	}
	
	public Ruolo(String descrizione) {
		this.descrizione = descrizione;
		utenti = new ArrayList<Utente>();
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Utente> getUtenti() {
		return utenti;
	}

	public void setUtenti(List<Utente> utenti) {
		this.utenti = utenti;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*@Override
	public String toString() {
		return descrizione;
	}*/
	
	
	
	
	
}
