package com.iktpreobuka.backend.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class OdeljenjeEntity {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "ime_odeljenja")
	private String name;
	@Column(name = "razred_odeljenja")
	private Razred razred;
	
	
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "Nastavnik_id")
	private NastavnikEntity nastavnik;
}
