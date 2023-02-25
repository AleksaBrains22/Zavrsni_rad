package com.iktpreobuka.backend.services;


import com.iktpreobuka.backend.dto.PredmetDTO;
import com.iktpreobuka.backend.entities.PredmetEntity;


public interface PredmetService {

	public PredmetEntity novipredmet(PredmetDTO noviPredmetDTO);
	public PredmetEntity brisanjePredmeta(Long id);
	public PredmetEntity izmenaPredmeta(PredmetDTO noviPredmetDTO ,Long id);
	public PredmetEntity pronadjiPredmetPoIdju(Long id);
	public Iterable<PredmetEntity> pronadjiSvePredmete();
}
