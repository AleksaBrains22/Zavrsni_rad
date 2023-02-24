package com.iktpreobuka.backend.services;

import org.springframework.http.ResponseEntity;

import com.iktpreobuka.backend.dto.PredmetDTO;


public interface PredmetService {

	public ResponseEntity<?> novipredmet(PredmetDTO noviPredmetDTO);
	public ResponseEntity<?> brisanjePredmeta(Long id);
	public ResponseEntity<?> izmenaPredmeta(PredmetDTO noviPredmetDTO ,Long id);
	public ResponseEntity<?> pronadjiPredmetPoIdju(Long id);
	public ResponseEntity<?> pronadjiSvePredmete();
}
