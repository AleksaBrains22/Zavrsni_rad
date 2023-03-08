package com.iktpreobuka.backend.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.backend.entities.NastavnikEntity;
import com.iktpreobuka.backend.services.NastavnikServiceImpl;

@RestController
@RequestMapping(path = "/api/v1/nastavnik")
public class NastavnikControler {
	@Autowired
	private NastavnikServiceImpl nastavnikServiceImpl;
	private final Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());
	
	
	@Secured(value = { "ADMIN" })
	@RequestMapping(method = RequestMethod.POST, path= "/{natavnikId}/predaje/{predmetId}")
	public ResponseEntity<?> nastavnikPredajePredmet(@PathVariable Long natavnikId,@PathVariable  Long predmetId) {
		try {
			 NastavnikEntity natavnik =nastavnikServiceImpl.nastavnikPredajePredmet(natavnikId,predmetId);
			 logger.info("Povezan je nastavnik i predmet");
			return new ResponseEntity<>(natavnik, HttpStatus.OK);
		}catch (Exception e) {
			logger.error("Nema nastavnika ili predmeta sa tim id-jem");
			return new ResponseEntity<>("Nema nastavnika ili Predmeta sa tim Id-jem", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
