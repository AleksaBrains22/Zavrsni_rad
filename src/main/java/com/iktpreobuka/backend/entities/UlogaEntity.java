package com.iktpreobuka.backend.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class UlogaEntity {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name= "Id_uloge")
private Integer id;
@Column(name= "broj_uloge")
@Enumerated(EnumType.ORDINAL)
private Uloga uloga;


	
	@JsonIgnore
	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<KorisnikEntity> korisnik = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Uloga getUloga() {
		return uloga;
	}
	
	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}
	@JsonIgnore
	public List<KorisnikEntity> getKorisnik() {
		return korisnik;
	}
	
	public void setKorisnik(List<KorisnikEntity> korisnik) {
		this.korisnik = korisnik;
	}


	public UlogaEntity(Integer id, Uloga uloga, List<KorisnikEntity> korisnik) {
		super();
		this.id = id;
		this.uloga = uloga;
		this.korisnik = korisnik;
	}

	public UlogaEntity() {
		super();
	}

	
}
