package com.iktpreobuka.backend.services;

import org.springframework.http.ResponseEntity;

import com.iktpreobuka.backend.dto.OdeljenjeDTO;
import com.iktpreobuka.backend.entities.NastavnikEntity;

public interface OdeljenjeService {

	public ResponseEntity<?> novoOdeljeEntity(OdeljenjeDTO novoOdeljenjeDTO , Long id);
	
}
