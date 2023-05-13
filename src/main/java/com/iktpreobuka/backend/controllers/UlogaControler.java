package com.iktpreobuka.backend.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.iktpreobuka.backend.entities.UlogaEntity;
import com.iktpreobuka.backend.services.UlogaService;

@RestController
@RequestMapping(path = "/api/v1/uloga")
public class UlogaControler {
	
	@Autowired
	private UlogaService ulogaService;
	private final Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());

	
//	@Secured(value = { "ADMIN" })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> IzlistajSveUloge() {
		try {
			Iterable<UlogaEntity> uloge = ulogaService.IzlistajSveUloge();
			if (uloge.equals(null)) {
				 logger.info("Nema uloga");
				return new ResponseEntity<Iterable<UlogaEntity>>(HttpStatus.NOT_FOUND);
			} else {			
				 logger.info("Uloge su poslate");
				return new ResponseEntity<Iterable<UlogaEntity>>(uloge, HttpStatus.OK);
			}
		} catch (Exception e) {
			 logger.warn("Doslo je do greske");
			return new ResponseEntity<Iterable<UlogaEntity>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//	@Secured(value = { "ADMIN" })
	@RequestMapping(method = RequestMethod.GET, path= "/{id}")
	public ResponseEntity<UlogaEntity> ulogePoIdju(@PathVariable Integer id) {
		try {
			UlogaEntity uloga = ulogaService.ulogePoIdju(id);
			if (uloga != null) {
				 logger.info("uloga je prosledjena");
				return new ResponseEntity<UlogaEntity>(uloga, HttpStatus.OK);
			} else {
				 logger.warn("nema uloge");
				return new ResponseEntity<UlogaEntity>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			 logger.error("Doslo je do greske u slanju");
			return new ResponseEntity<UlogaEntity>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@Secured(value = { "ADMIN" })
	@RequestMapping(method = RequestMethod.DELETE, path= "/{id}")	
	public ResponseEntity<?> obrisiUloge(@PathVariable Integer id) {
		try {
			Boolean deleted = ulogaService.obrisiUloge(id);
			if (deleted) {
				logger.info("Uloga je obrisana");
				return new ResponseEntity<>("Uloga je obrisana",HttpStatus.NO_CONTENT);
			} else {
				logger.info("Nema uloge sa tim idjem");
				return new ResponseEntity<>("Nema uloge sa tim idjem",HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			 logger.error("Doslo je do greske u slanju");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
