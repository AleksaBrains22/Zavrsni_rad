package com.iktpreobuka.backend.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iktpreobuka.backend.dto.PredmetDTO;
import com.iktpreobuka.backend.entities.PredmetEntity;
import com.iktpreobuka.backend.repositories.PredmetRepositories;


@Service
public class PredmetServiceImpl implements PredmetService {

	@Autowired
	private PredmetRepositories predmetRepositories;

	@Override
	public ResponseEntity<?> novipredmet(PredmetDTO noviPredmetDTO) {
		PredmetEntity predmet = new PredmetEntity();
		predmet.setName(noviPredmetDTO.getName());
		predmet.setFondCasova(noviPredmetDTO.getFondCasova());
		predmet.setRazred(noviPredmetDTO.getRazred());
		predmetRepositories.save(predmet);
		return new ResponseEntity<>(predmet, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> brisanjePredmeta(Long id) {
		Optional<PredmetEntity> predmet = predmetRepositories.findById(id);
		if (predmet.isPresent()) {
			predmetRepositories.delete(predmet.get());
			return new ResponseEntity<>(predmet,HttpStatus.OK);
		}
		return new ResponseEntity<>("Ne postoji predmet sa ovim id-jem ili je vec obrisan",HttpStatus.INTERNAL_SERVER_ERROR );
	}

	@Override
	public ResponseEntity<?> izmenaPredmeta(PredmetDTO novPredmetDTO, Long id) {
		Optional<PredmetEntity> predmet = predmetRepositories.findById(id);
		if(predmet.isPresent()) {
			predmet.get().setName(novPredmetDTO.getName());
			predmet.get().setRazred(novPredmetDTO.getRazred());
			predmet.get().setFondCasova(novPredmetDTO.getFondCasova());
			predmetRepositories.save(predmet.get());
			return new ResponseEntity<>(predmet,HttpStatus.OK);
		}
		return new ResponseEntity<>("Ne postoji predmet sa ovim id-jem",HttpStatus.INTERNAL_SERVER_ERROR );
	}

	@Override
	public ResponseEntity<?> pronadjiPredmetPoIdju(Long id) {
		Optional<PredmetEntity> predmet = predmetRepositories.findById(id);
		if(predmet.isPresent()) {
			return new ResponseEntity<>(predmet,HttpStatus.OK);
		}
		return new ResponseEntity<>("Ne postoji predmet sa ovim id-jem",HttpStatus.INTERNAL_SERVER_ERROR );
	}

	@Override
	public ResponseEntity<?> pronadjiSvePredmete() {
		Iterable<PredmetEntity> predmet = predmetRepositories.findAll();
		return new ResponseEntity<>(predmet,HttpStatus.OK);
	}

}
