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

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class UlogaEntity {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name= "Id_uloge")
private Integer id;
@Column(name= "broj_uloge")
private Uloga uloga;
@Column(name="ime_uloge")
private String name;

	@JsonManagedReference
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

	public List<KorisnikEntity> getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(List<KorisnikEntity> korisnik) {
		this.korisnik = korisnik;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UlogaEntity(Integer id, Uloga uloga, String name, List<KorisnikEntity> korisnik) {
		super();
		this.id = id;
		this.uloga = uloga;
		this.name = name;
		this.korisnik = korisnik;
	}

	public UlogaEntity() {
		super();
	}

	
}
