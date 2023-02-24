package com.iktpreobuka.backend.services;

import org.springframework.http.ResponseEntity;

import com.iktpreobuka.backend.dto.KorisnikDTO;

public interface KorisnikService {

	public ResponseEntity<?> createNewUser(KorisnikDTO noviKorisnik);
	public ResponseEntity<?> findUserbyId(Long id);
	public ResponseEntity<?> brisanjeKorisnika(Long id);
}
