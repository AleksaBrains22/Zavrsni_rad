package com.iktpreobuka.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.backend.dto.PredmetDTO;
import com.iktpreobuka.backend.entities.NastavnikEntity;
import com.iktpreobuka.backend.entities.PredmetEntity;
import com.iktpreobuka.backend.repositories.NastavnikRepositories;

@Service
public class NastavnikServiceImpl implements NastavnikService{

	@Autowired
	private NastavnikRepositories nastavnikRepositories;
	@Autowired
	private PredmetServiceImpl predmetServiceImpl;
	

	@Override
	public NastavnikEntity findById(Long id) {
		NastavnikEntity nastavnik = nastavnikRepositories.findById(id).get();	
		return nastavnik;
	}

	@Override
	public NastavnikEntity nastavnikPredajePredmet(Long predmetId, Long nastavnikId) {
		NastavnikEntity nastavnik = nastavnikRepositories.findById(nastavnikId).get();
		PredmetEntity predmet = predmetServiceImpl.pronadjiPredmetPoIdju(predmetId);
		if (nastavnikId != null && predmetId != null) {
			nastavnik.getPredmetKojNastavnikpredaje().add(predmet);
			predmet.getNastavniciKojiPredajuOvajPredmet().add(nastavnik);
			nastavnikRepositories.save(nastavnik);
			return nastavnik;
		}				
		return null;
	}
	
	
	
	
	
	
}
