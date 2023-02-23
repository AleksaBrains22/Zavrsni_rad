package com.iktpreobuka.backend.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.iktpreobuka.backend.entities.Razred;

public class OdeljenjeDTO {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull(message = "ne moze biti prazno polje")
	@Column(name = "ime_odeljenja")
	private String name;
	@NotNull(message = "ne moze biti prazno polje")
	@Column(name = "razred_odeljenja")
	private Razred razred;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Razred getRazred() {
		return razred;
	}
	public void setRazred(Razred razred) {
		this.razred = razred;
	}
	public OdeljenjeDTO(Long id, @NotNull(message = "ne moze biti prazno polje") String name,
			@NotNull(message = "ne moze biti prazno polje") Razred razred) {
		super();
		this.id = id;
		this.name = name;
		this.razred = razred;
	}
	public OdeljenjeDTO() {
		super();
	}
	
	
}
