package com.iktpreobuka.backend.entities;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue(value = "Admin")
public class AdminEntity extends KorisnikEntity{



}
