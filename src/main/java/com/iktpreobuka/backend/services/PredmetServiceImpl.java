package com.iktpreobuka.backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.backend.dto.PredmetDTO;
import com.iktpreobuka.backend.entities.PredmetEntity;
import com.iktpreobuka.backend.repositories.PredmetRepositories;


@Service
public class PredmetServiceImpl implements PredmetService {

	@Autowired
	private PredmetRepositories predmetRepositories;

	@Override
	public PredmetEntity novipredmet(PredmetDTO noviPredmetDTO) {
		PredmetEntity predmet = new PredmetEntity();
		predmet.setName(noviPredmetDTO.getName());
		predmet.setFondCasova(noviPredmetDTO.getFondCasova());
		predmet.setRazred(noviPredmetDTO.getRazred());
		predmetRepositories.save(predmet);
		return predmet;
	}

	@Override
	public PredmetEntity brisanjePredmeta(Long id) {
		PredmetEntity predmet = predmetRepositories.findById(id).get();
		if (predmet !=null) {
			predmetRepositories.delete(predmet);
			return predmet;
		}
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
			return predmet;
		}
		return null;
	}

	@Override
	public PredmetEntity pronadjiPredmetPoIdju(Long id) {
		PredmetEntity predmet = predmetRepositories.findById(id).get();
		if(predmet != null) {
			return predmet;
		}
		return null;
	}

	@Override
	public Iterable<PredmetEntity> pronadjiSvePredmete() {
		Iterable<PredmetEntity> predmeti = predmetRepositories.findAll();
		return predmeti;
	}

}
