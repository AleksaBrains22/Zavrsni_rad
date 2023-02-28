package com.iktpreobuka.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@RequestMapping(method = RequestMethod.POST, path= "/{natavnikId}/predaje/{predmetId}")
	public ResponseEntity<?> nastavnikPredajePredmet(@PathVariable Long natavnikId,@PathVariable  Long predmetId) {
		try {
			 NastavnikEntity natavnik =nastavnikServiceImpl.nastavnikPredajePredmet(predmetId, natavnikId);
			return new ResponseEntity<>(natavnik, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>("Nema nastavnika ili Predmeta sa tim Id-jem", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
