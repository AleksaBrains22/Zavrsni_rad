package com.iktpreobuka.backend.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.backend.entities.PredmetEntity;
import com.iktpreobuka.backend.entities.UcenikEntity;
import com.iktpreobuka.backend.entities.ZakljucnaOcenaEntity;
import com.iktpreobuka.backend.services.ZakljucnaOcenaServiceImpl;

@RestController
@RequestMapping("api/v1/zakljucnaOcena")
public class ZakljucnaOcenaControler {
	@Autowired
	private ZakljucnaOcenaServiceImpl zakljucnaOcenaServiceImpl;
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Secured(value = { "ADMIN","NASTAVNIK" })
	@RequestMapping(method = RequestMethod.POST, path = "/zakljuciOcenu/uceniku/{ucenikId}/iz/Predmeta/{predmetId}")
	public ResponseEntity<?> zakljuciOcenuIzPredmeta(@RequestBody ZakljucnaOcenaEntity zakljucnaOcenaEntity,
			@PathVariable UcenikEntity ucenikId, @PathVariable PredmetEntity predmetId,@RequestParam String polugodiste) {
		try {
			zakljucnaOcenaServiceImpl.zakljuciOcenuIzPredmeta(zakljucnaOcenaEntity,ucenikId, predmetId, polugodiste);
			logger.info("Metoda za zakljucivanje ocene se izvrsila");
			return new ResponseEntity<>("Zakljucena je ocena", HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Doslo je do greske pri zakljucivanju Ocene");
			return new ResponseEntity<>("Doslo je do greske pri zakljucivanju Ocene", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@Secured(value = { "ADMIN","RODITELJ","NASTAVNIK","UCENIK" })
	@RequestMapping(method = RequestMethod.GET, path= "/{ucenikId}")
	public ResponseEntity<?>pronadjiZakljuceOceneUcenika(@PathVariable UcenikEntity ucenikId){
		try {
			Optional<ZakljucnaOcenaEntity> zakljucna = zakljucnaOcenaServiceImpl.findByUcenik(ucenikId);			
			return new ResponseEntity<>(zakljucna, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>("Nema zakljucnih ocena ili je doslo do greske", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
	}

	
	

}
