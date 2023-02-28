package com.iktpreobuka.backend.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.iktpreobuka.backend.entities.Razred;

public class PredmetDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Predmet_id")
	private Long id;
	@Column(name = "Ime_predmeta")
	@NotNull(message = "ne moze biti prazno polje")
	private String name;
	@Column(name = "fond_casova")
	@NotNull(message = "ne moze biti prazno polje")
	private Integer FondCasova;
	@NotNull(message = "ne moze biti prazno polje")
	@Column(name = "razred")
	private Razred razred;
	@javax.persistence.Version
	private Integer version;
	

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

	public Integer getFondCasova() {
		return FondCasova;
	}

	public void setFondCasova(Integer fondCasova) {
		FondCasova = fondCasova;
	}

	public Razred getRazred() {
		return razred;
	}

	public void setRazred(Razred razred) {
		this.razred = razred;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public PredmetDTO(Long id, @NotNull(message = "ne moze biti prazno polje") String name,
			@NotNull(message = "ne moze biti prazno polje") Integer fondCasova,
			@NotNull(message = "ne moze biti prazno polje") Razred razred, Integer version) {
		super();
		this.id = id;
		this.name = name;
		FondCasova = fondCasova;
		this.razred = razred;
		this.version = version;
	}

	public PredmetDTO() {
		super();
	}

}
