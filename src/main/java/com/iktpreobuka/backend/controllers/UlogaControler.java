package com.iktpreobuka.backend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> IzlistajSveUloge() {
		try {
			Iterable<UlogaEntity> uloge = ulogaService.IzlistajSveUloge();
			if (uloge.equals(null)) {
				return new ResponseEntity<Iterable<UlogaEntity>>(HttpStatus.NOT_FOUND);
			} else {			
				return new ResponseEntity<Iterable<UlogaEntity>>(uloge, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<Iterable<UlogaEntity>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, path= "/{id}")
	public ResponseEntity<UlogaEntity> ulogePoIdju(@PathVariable Integer id) {
		try {
			UlogaEntity uloga = ulogaService.ulogePoIdju(id);
			if (uloga != null) {
				return new ResponseEntity<UlogaEntity>(uloga, HttpStatus.OK);
			} else {
				return new ResponseEntity<UlogaEntity>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<UlogaEntity>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE, path= "/{id}")	
	public ResponseEntity<?> obrisiUloge(@PathVariable Integer id) {
		try {
			Boolean deleted = ulogaService.obrisiUloge(id);
			if (deleted) {
				return new ResponseEntity<>("Uloga je obrisana",HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>("Nema uloge sa tim idjem",HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
