package com.iktpreobuka.backend.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.backend.dto.OdeljenjeDTO;
import com.iktpreobuka.backend.entities.OdeljenjeEntity;
import com.iktpreobuka.backend.services.OdeljenjeServiceImpl;

@RestController
@RequestMapping(path = "api/v1/odeljenje")
public class OdeljenjeControler {
	@Autowired
	private OdeljenjeServiceImpl odeljenjeServiceImpl;
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@RequestMapping(method = RequestMethod.POST, value = "/novoOdeljnje/{id}")
	public ResponseEntity<?> novoOdeljenje(@Valid @RequestBody OdeljenjeDTO odeljenjeDTO, @PathVariable Long id) {
		try {
			OdeljenjeEntity odeljenje = odeljenjeServiceImpl.novoOdeljeEntity(odeljenjeDTO, id);
			if (odeljenje.getNastavnik() != null)
				logger.info("novo odeljenje je napravljeno");
				return new ResponseEntity<>(odeljenje, HttpStatus.CREATED);		
		} catch (Exception e) {
			logger.error("nema nastavnika sa tim id-jem");
			return new ResponseEntity<>("Pogresan ID nastavnika", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> nadjiOdeljenje(@PathVariable Long id) {
		try {		
			OdeljenjeEntity odeljenje = odeljenjeServiceImpl.nadjiOdeljenjePOIdju(id);
			if (odeljenje != null)
				logger.info("odeljenje je nadjeno");
				return new ResponseEntity<>(odeljenje, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("nema odeljenja sa tim id-jem");
			return new ResponseEntity<>("Nema odeljenja sa tim Id-jem", HttpStatus.INTERNAL_SERVER_ERROR);
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
