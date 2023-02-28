package com.iktpreobuka.backend.services;

import com.iktpreobuka.backend.entities.NastavnikEntity;

public interface NastavnikService {
	
	public NastavnikEntity findById(Long id);
	
	public NastavnikEntity nastavnikPredajePredmet( Long predmetId, Long nastavnikId);
}
