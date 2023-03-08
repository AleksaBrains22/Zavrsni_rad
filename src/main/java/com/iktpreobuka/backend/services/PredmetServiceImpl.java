package com.iktpreobuka.backend.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.backend.dto.PredmetDTO;
import com.iktpreobuka.backend.entities.PredmetEntity;
import com.iktpreobuka.backend.repositories.PredmetRepositories;


@Service
public class PredmetServiceImpl implements PredmetService {

	@Autowired
	private PredmetRepositories predmetRepositories;
	private final Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());


	@Override
	public PredmetEntity novipredmet(PredmetDTO noviPredmetDTO) {
		PredmetEntity predmet = new PredmetEntity();
		predmet.setName(noviPredmetDTO.getName());
		predmet.setFondCasova(noviPredmetDTO.getFondCasova());
		predmet.setRazred(noviPredmetDTO.getRazred());
		predmetRepositories.save(predmet);
		logger.info("poslat je zahtev za cuvanje novog predmeta");
		return predmet;
	}

	@Override
	public PredmetEntity brisanjePredmeta(Long id) {
		PredmetEntity predmet = predmetRepositories.findById(id).get();
		if (predmet !=null) {
			predmetRepositories.delete(predmet);
			logger.info("poslat je zahtev za brisanje predmeta");
			return predmet;
		}
		logger.warn("nema predmeta za ovaj zahtev brisanja");
		return null;
	}

	@Override
	public PredmetEntity izmenaPredmeta(PredmetDTO novPredmetDTO, Long id) {
		PredmetEntity predmet = predmetRepositories.findById(id).get();
		if(predmet != null) {
			predmet.setName(novPredmetDTO.getName());
			predmet.setRazred(novPredmetDTO.getRazred());
			predmet.setFondCasova(novPredmetDTO.getFondCasova());
			predmetRepositories.save(predmet);
			logger.info("poslat je zahtev za izmenu predmeta");
			return predmet;
		}
		logger.warn("nema predmeta za ovaj zahtev izmene predmeta");
		return null;
	}

	@Override
	public PredmetEntity pronadjiPredmetPoIdju(Long id) {
		PredmetEntity predmet = predmetRepositories.findById(id).get();
		if(predmet != null) {
			logger.info("poslat je zahtev za trazenje predmeta po id-ju");
			return predmet;
		}
		return null;
	}

	@Override
	public Iterable<PredmetEntity> pronadjiSvePredmete() {
		Iterable<PredmetEntity> predmeti = predmetRepositories.findAll();
		logger.info("poslat je zahtev za iscitavanje svih predmeta");
		return predmeti;
	}

}
