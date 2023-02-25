package com.iktpreobuka.backend.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Korisnici")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Tip_Korisnika", discriminatorType = DiscriminatorType.STRING)

public class KorisnikEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "korisnicki_id")
	private Long id;
	@NotNull(message= "Ime korisnika mora biti popunjeno")
	@Size(min = 3, max = 30, message = "Ime mora biti izmedju {min} i {max} broja slova.")
	@Column(name = "Ime_korisnika")
	private String ime;
	@Column
	@NotNull(message= "Prezime korisnika mora biti popunjeno")
	@Size(min = 3, max = 30, message ="Prezime mora biti izmedju {min} i {max} broja slova.")
	private String prezime;
	@Column
	@JsonIgnore
	@NotNull(message= "Password ne moze biti prazan")
	private String password;
	@Column
	@NotNull(message= "Username ne moze biti prazan")
	@Size(min = 3, max = 30, message ="Username mora biti izmedju {min} i {max} broja slova.")
	private String username;
	@Column
	@Version
	private Integer version;
	@Email
	@Column(name="Email")
	private String email;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "Rola_korisnika")
	private UlogaEntity uloga;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public UlogaEntity getUloga() {
		return uloga;
	}

	public void setUloga(UlogaEntity uloga) {
		this.uloga = uloga;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public KorisnikEntity(Long id,
			@NotNull(message = "Ime korisnika mora biti popunjeno") @Size(min = 3, max = 30, message = "Ime mora biti izmedju {min} i {max} broja slova.") String ime,
			@NotNull(message = "Prezime korisnika mora biti popunjeno") @Size(min = 3, max = 30, message = "Prezime mora biti izmedju {min} i {max} broja slova.") String prezime,
			@NotNull(message = "Password ne moze biti prazan") String password,
			@NotNull(message = "Username ne moze biti prazan") @Size(min = 3, max = 30, message = "Username mora biti izmedju {min} i {max} broja slova.") String username,
			Integer version, @Email String email, UlogaEntity uloga) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.password = password;
		this.username = username;
		this.version = version;
		this.email = email;
		this.uloga = uloga;
	}

	public KorisnikEntity() {
		super();
	}


	
	
}
