package com.iktpreobuka.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private final Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());

	
	@Override
	public NastavnikEntity findById(Long id) {
		NastavnikEntity nastavnik = nastavnikRepositories.findById(id).get();	
		logger.info("Poslat je zahtev za nastavnika po id-ju");
		return nastavnik;
	}

	@Override
	public NastavnikEntity nastavnikPredajePredmet(Long nastavnikId,Long predmetId) {
		NastavnikEntity nastavnik = nastavnikRepositories.findById(nastavnikId).get();
		PredmetEntity predmet = predmetServiceImpl.pronadjiPredmetPoIdju(predmetId);
		if (nastavnikId != null && predmetId != null) {
			nastavnik.getPredmetKojNastavnikpredaje().add(predmet);
			predmet.getNastavniciKojiPredajuOvajPredmet().add(nastavnik);
			logger.info("Poslat je zahtev za spajenje nastavnika i predmeta po id-ju");
			nastavnikRepositories.save(nastavnik);
			return nastavnik;
		}				
		logger.info("zahtev za spavanje predmeta i nastavnika nije prosao, proverite nastavnik id ili predmet id");
		return null;
	}
	
	
	
	
	
	
}
