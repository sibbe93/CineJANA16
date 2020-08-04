package com.azienda.progetto.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Spettacolo {
	
	@Column(nullable = false)
	private LocalDateTime dataSpettacolo;
	
	@ManyToOne
	@JoinColumn(name = "filmAssociato")
	private Film film;
	
	@ManyToOne
	@JoinColumn(name = "salaAssociata")
	private Sala sala;
	
	@OneToMany(mappedBy = "spettacolo")
	private List<Prenotazione> prenotazioni;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	public Spettacolo() {
		this(null,null,null);
	}
	public Spettacolo(LocalDateTime dataSpettacolo, Film film, Sala sala) {
		this.dataSpettacolo = dataSpettacolo;
		this.film = film;
		this.sala = sala;
		
		prenotazioni = new ArrayList<Prenotazione>();
		
	}
	
	
	public LocalDateTime getDataSpettacolo() {
		return dataSpettacolo;
	}
	public void setDataSpettacolo(LocalDateTime dataSpettacolo) {
		this.dataSpettacolo = dataSpettacolo;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public List<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}
	public void setPrenotazioni(List<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
}
