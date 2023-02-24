package com.iktpreobuka.backend.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iktpreobuka.backend.dto.OdeljenjeDTO;
import com.iktpreobuka.backend.entities.NastavnikEntity;
import com.iktpreobuka.backend.entities.OdeljenjeEntity;
import com.iktpreobuka.backend.repositories.NastavnikRepositories;
import com.iktpreobuka.backend.repositories.OdeljenjeRepositories;

@Service
public class OdeljenjeServiceImpl implements OdeljenjeService {
	@Autowired
	private OdeljenjeRepositories odeljenjeRepositories;
	@Autowired
	private NastavnikRepositories nastRepositories;

	@Override
	public ResponseEntity<?> novoOdeljeEntity(OdeljenjeDTO novoOdeljenjeDTO, Long id) {
		OdeljenjeEntity odeljenje = new OdeljenjeEntity();
		Optional<NastavnikEntity> nastavnik = nastRepositories.findById(id);
		if (nastavnik.isPresent()) {
			odeljenje.setName(novoOdeljenjeDTO.getName());
			odeljenje.setRazred(novoOdeljenjeDTO.getRazred());
			odeljenje.setNastavnik(nastavnik.get());
			odeljenjeRepositories.save(odeljenje);
			return new ResponseEntity<>(odeljenje, HttpStatus.OK);
		}
		return new ResponseEntity<>("Ne postoji profesor sa ovim id-jem", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
