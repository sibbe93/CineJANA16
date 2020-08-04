package com.azienda.progetto.model;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Film {
	
	@Column( unique = true ,nullable = false)
	private String titolo;
	
	@Column(nullable = false)
	private String genere;
	
	@Column(nullable = false)
	private String trailer;
	
	@Column(nullable = false)
	private LocalTime durata;
	
	@Column(nullable = false ,length = 1000)
	private String trama;
	
	@OneToMany(mappedBy = "film")
	private List<Spettacolo> spettacoli;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	public Film() {
		this(null,null,null,null,null);
	}
	
	public Film(String titolo, LocalTime durata, String genere, String trama, String trailer) {
		this.titolo = titolo;
		this.durata = durata;
		this.genere = genere;
		this.trama = trama;
		this.trailer = trailer;
		spettacoli = new ArrayList<Spettacolo>();
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	

	

	public LocalTime getDurata() {
		return durata;
	}

	public void setDurata(LocalTime durata) {
		this.durata = durata;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}
	
	
	public String getTrama() {
		return trama;
	}

	public void setTrama(String trama) {
		this.trama = trama;
	}

	public List<Spettacolo> getSpettacoli() {
		return spettacoli;
	}

	public void setSpettacoli(List<Spettacolo> spettacoli) {
		this.spettacoli = spettacoli;
	}
	
	
	
	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
}
