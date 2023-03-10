package com.iktpreobuka.backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.backend.entities.KorisnikEntity;
import com.iktpreobuka.backend.entities.OcenaEntity;
import com.iktpreobuka.backend.entities.Polugodiste;
import com.iktpreobuka.backend.entities.PredmetEntity;
import com.iktpreobuka.backend.entities.UcenikEntity;
import com.iktpreobuka.backend.models.EmailObject;
import com.iktpreobuka.backend.services.AuthenticationUserImpl;
import com.iktpreobuka.backend.services.EmailServiceImpl;
import com.iktpreobuka.backend.services.OcenaServiceImpl;

@RestController
@RequestMapping("api/v1/ocena")
public class OcenaControler {
	@Autowired
	private OcenaServiceImpl ocenaServiceImpl;
	@Autowired
	private EmailServiceImpl emailServiceImpl;
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Secured(value = { "ADMIN", "NASTAVNIK" })
	@RequestMapping(method = RequestMethod.POST, path = "/novaOcena/ucenika/{ucenikId}/iz/{predmetId}/od/nastavnika/{nastavnikId}")
	public ResponseEntity<?> novaOcena(@Valid @RequestBody OcenaEntity ocena, @PathVariable Long ucenikId,
			@PathVariable Long predmetId, @PathVariable Long nastavnikId) {
		try {
			OcenaEntity novaOcena = ocenaServiceImpl.dodajNovuOcenu(ucenikId, predmetId, nastavnikId, ocena);
			if (novaOcena != null) {
				logger.info("Ocena je upisana u e-dnevnik");
				return new ResponseEntity<>(novaOcena, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			logger.error("Nema ili predmeta ili nastavnika sa ovim idjem");
			return new ResponseEntity<>("Nije dobar zahtev", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Nije dobar zahtev", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Secured(value = { "ADMIN", "NASTAVNIK" })
	@RequestMapping(method = RequestMethod.POST, value = "/novaOcena")
	public ResponseEntity<?> sendSimpleMessage(@RequestBody EmailObject object) {
		if (object == null || object.getText() == null || object.getTo() == null) {
			logger.error("Nije sve popunjeno kod ocene");
			return new ResponseEntity<>("NijeDobroPoslato", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.error("dobro je poslat email");
		emailServiceImpl.sendSimpleMessage(object);
		return new ResponseEntity<>("Success!", HttpStatus.OK);
	}

	@Secured(value = { "ADMIN", "NASTAVNIK" })
	@RequestMapping(method = RequestMethod.GET, value = "/nadjiOcene/{ucenikId}/izPredmeta/{predmetId}")
	public ResponseEntity<?> nadjiSveOceneUcenikaizJednogPredmeta(@PathVariable UcenikEntity ucenikId,
			@PathVariable PredmetEntity predmetId) {
		try {
			List<OcenaEntity> ocene = (List<OcenaEntity>) ocenaServiceImpl
					.nadjiSveOceneUcenikaizJednogPredmeta(ucenikId, predmetId);
			logger.info("vracene su ocene iz tog predmeta");
			return new ResponseEntity<>(ocene, HttpStatus.OK);
		} catch (Exception e) {
			logger.info("nije dobar id ucenika ili predmeta");
			return new ResponseEntity<>("NijeDobroPoslato", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Secured(value = { "ADMIN", "RODITELJ", "NASTAVNIK", "UCENIK" })
	@RequestMapping(method = RequestMethod.GET, value = "/nadjiOcene/{ucenikId}")
	public ResponseEntity<?> nadjiOceneUcenikaIzSvihPredmeta(@PathVariable UcenikEntity ucenikId) {
		try {
			List<OcenaEntity> ocene = (List<OcenaEntity>) ocenaServiceImpl.nadjiOceneUcenikaIzSvihPredmeta(ucenikId);
			logger.info("vracene su ocene");
			return new ResponseEntity<>(ocene, HttpStatus.OK);
		} catch (Exception e) {
			logger.info("nije dobar id ucenika ili nema ocena");
			return new ResponseEntity<>("NijeDobroPoslato", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Secured(value = { "ADMIN", "RODITELJ", "NASTAVNIK", "UCENIK" })
	@RequestMapping(method = RequestMethod.GET, value = "/nadjiOcene/zaUcenika/{ucenikId}/zaPredmet/{predmetId}")
	public ResponseEntity<?> pronadjiPoUcenikuIPredmetiIPolugodistuOcenu(@PathVariable UcenikEntity ucenikId,
			@PathVariable PredmetEntity predmetId, @RequestParam String polugodiste) {
		try {
			List<OcenaEntity> ocene = (List<OcenaEntity>) ocenaServiceImpl
					.pronadjiPoUcenikuIPredmetiIPolugodistuOcenu(ucenikId, predmetId, polugodiste);
			logger.info("vracene su ocene za odabrano polugodje za odabrani predmet i ucenika");
			return new ResponseEntity<>(ocene, HttpStatus.OK);
		} catch (Exception e) {
			logger.info("nije dobar id ucenika ili nema ocena za to polugodiste");
			return new ResponseEntity<>("NijeDobroPoslato", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Secured(value = { "ADMIN" })
	@RequestMapping(method = RequestMethod.PUT, value = "/izmeniOcenu/{ocenaId}")
	public ResponseEntity<?> izmeniOcenuUcenikaIzPredmeta(@PathVariable Long ocenaId,
			@RequestBody OcenaEntity novaOcena) {
		try {
			OcenaEntity ocena = ocenaServiceImpl.izmeniOcenuUcenikaIzPredmeta(ocenaId, novaOcena);
			logger.info("ocena je izmenjena");
			return new ResponseEntity<>(ocena, HttpStatus.OK);
		} catch (Exception e) {
			logger.info("ocena sa tim idjem nije dobra");
			return new ResponseEntity<>("NijeDobroPoslato", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Secured(value = { "ADMIN" })
	@RequestMapping(method = RequestMethod.DELETE, value = "/izbrisiOcenu/{ocenaId}")
	public ResponseEntity<?> izbrisiOcenuUcenika(@PathVariable Long ocenaId) {
		try {
			OcenaEntity ocena = ocenaServiceImpl.izbrisiOcenuUcenika(ocenaId);
			logger.info("ocena je izbrisana");
			return new ResponseEntity<>(ocena, HttpStatus.OK);
		} catch (Exception e) {
			logger.info("ocena sa tim idjem nije postojala");
			return new ResponseEntity<>("Nije Dobro Poslat zahtev", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = "";
			String errorMessage = "";
			if (error instanceof FieldError) {
				fieldName = ((FieldError) error).getField();
				errorMessage = ((FieldError) error).getDefaultMessage();
			} else if (error instanceof ObjectError) {
				fieldName = ((ObjectError) error).getObjectName();
				errorMessage = error.getDefaultMessage();

			}
			errors.put(fieldName, errorMessage);
		});

		return errors;
	}
}
