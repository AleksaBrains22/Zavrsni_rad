package com.iktpreobuka.backend.services;

import java.util.Optional;

import com.iktpreobuka.backend.entities.PredmetEntity;
import com.iktpreobuka.backend.entities.UcenikEntity;
import com.iktpreobuka.backend.entities.ZakljucnaOcenaEntity;

public interface ZakljucnaOcenaService {

	public ZakljucnaOcenaEntity zakljuciOcenuIzPredmeta(ZakljucnaOcenaEntity zakljucnaOcenaEntity 
			,UcenikEntity ucenikId, PredmetEntity predmetId ,String polugodiste);
	
	public Optional<ZakljucnaOcenaEntity> findByUcenik(UcenikEntity ucenikId);
}
