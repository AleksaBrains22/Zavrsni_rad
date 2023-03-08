package com.iktpreobuka.backend.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class ZakljucnaOcenaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="zakljucna_ocena")
	private Integer zakljucnaOcena;
	private Polugodiste polugodiste;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getZakljucnaOcena() {
		return zakljucnaOcena;
	}
	public void setZakljucnaOcena(Integer zakljucnaOcena) {
		this.zakljucnaOcena = zakljucnaOcena;
	}
	public Polugodiste getPolugodiste() {
		return polugodiste;
	}
	public void setPolugodiste(Polugodiste polugodiste) {
		this.polugodiste = polugodiste;
	}
	@JsonIgnore
	public UcenikEntity getUcenik() {
		return ucenik;
	}
	@JsonIgnore
	public void setUcenik(UcenikEntity ucenik) {
		this.ucenik = ucenik;
	}
	public PredmetEntity getPredmet() {
		return predmet;
	}
	public void setPredmet(PredmetEntity predmet) {
		this.predmet = predmet;
	}
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "ucenik_id")
	private UcenikEntity ucenik;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "predmet_id")
	private PredmetEntity predmet;

	public ZakljucnaOcenaEntity(Long id, Integer zakljucnaOcena, Polugodiste polugodiste, UcenikEntity ucenik,
			PredmetEntity predmet) {
		super();
		this.id = id;
		this.zakljucnaOcena = zakljucnaOcena;
		this.polugodiste = polugodiste;
		this.ucenik = ucenik;
		this.predmet = predmet;
	}
	public ZakljucnaOcenaEntity() {
		super();
	}
	
	
	
	
}