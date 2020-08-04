package com.azienda.progetto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Prenotazione {
	
	@ManyToOne
	@JoinColumn(name = "spettacoloPrenotato")
	private Spettacolo spettacolo;
	
	@Column(nullable = false)
	private Integer postiPrenotati;
	
	@ManyToOne
	@JoinColumn(name = "utentePrenotato")
	private Utente utente;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	public Prenotazione() {
		this(null,null,null);
	}
	public Prenotazione(Spettacolo spettacolo, Integer postiPrenotati, Utente utente) {
		this.spettacolo = spettacolo;
		this.postiPrenotati = postiPrenotati;
		this.utente = utente;
		
	}
	public Spettacolo getSpettacolo() {
		return spettacolo;
	}
	public void setSpettacolo(Spettacolo spettacolo) {
		this.spettacolo = spettacolo;
	}
	public Integer getPostiPrenotati() {
		return postiPrenotati;
	}
	public void setPostiPrenotati(Integer postiPrenotati) {
		this.postiPrenotati = postiPrenotati;
	}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
	
}
