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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class OcenaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Min(1)
	@Max(5)
	private Integer ocena;
	@Column
	private Razred razred;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getOcena() {
		return ocena;
	}
	public void setOcena(Integer ocena) {
		this.ocena = ocena;
	}
	public Razred getRazred() {
		return razred;
	}
	public void setRazred(Razred razred) {
		this.razred = razred;
	}
	public PredmetEntity getPredmet() {
		return predmet;
	}
	public void setPredmet(PredmetEntity predmet) {
		this.predmet = predmet;
	}
	public UcenikEntity getUcenik() {
		return ucenik;
	}
	public void setUcenik(UcenikEntity ucenik) {
		this.ucenik = ucenik;
	}
	public NastavnikEntity getNastavnik() {
		return nastavnik;
	}
	public void setNastavnik(NastavnikEntity nastavnik) {
		this.nastavnik = nastavnik;
	}
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_predmeta_iz_koga_je_ocena")
	private PredmetEntity predmet;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_ucenika_koj_je_dobio_ocenu")
	private UcenikEntity ucenik;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_nastavnika_koji_je_dao_ocenu")
	private NastavnikEntity nastavnik;
}