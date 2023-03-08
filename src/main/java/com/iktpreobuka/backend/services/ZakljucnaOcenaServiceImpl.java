package com.iktpreobuka.backend.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.backend.entities.OcenaEntity;
import com.iktpreobuka.backend.entities.Polugodiste;
import com.iktpreobuka.backend.entities.PredmetEntity;
import com.iktpreobuka.backend.entities.UcenikEntity;
import com.iktpreobuka.backend.entities.ZakljucnaOcenaEntity;
import com.iktpreobuka.backend.repositories.ZakljucnaOcenaRepositories;

@Service
public class ZakljucnaOcenaServiceImpl implements ZakljucnaOcenaService{
	@Autowired
	private ZakljucnaOcenaRepositories zakljucnaOcenaRepositories;	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OcenaServiceImpl ocenaServiceImpl;
	
	
	
	@Override
	public ZakljucnaOcenaEntity zakljuciOcenuIzPredmeta(ZakljucnaOcenaEntity zakljucnaOcenaEntity ,UcenikEntity ucenikId, PredmetEntity predmetId ,String polugodiste) {
		ZakljucnaOcenaEntity zakljucenaOcena= new ZakljucnaOcenaEntity();
		List<OcenaEntity> ocena = ocenaServiceImpl.pronadjiPoUcenikuIPredmetiIPolugodistuOcenu(ucenikId, predmetId, polugodiste);
		int sum = 0;
		int zakljucnaOcenaizPredmeta=0;
		for (OcenaEntity ocenaEntity : ocena) {
			sum += ocenaEntity.getOcena();
		}
		int brojOcena = ocena.size();
		if (brojOcena > 0) {
			zakljucnaOcenaizPredmeta=(sum/brojOcena); 
		} else {
			logger.error("doslo je do greske , ucenik nema ocene iz tog predmeta");
			return null;
		}
		if (polugodiste.toString().equalsIgnoreCase(Polugodiste.PRVO_POLUGODISTE.toString())){
			zakljucenaOcena.setPolugodiste(Polugodiste.PRVO_POLUGODISTE);
			zakljucenaOcena.setPredmet(predmetId);
			zakljucenaOcena.setUcenik(ucenikId);			
			zakljucenaOcena.setZakljucnaOcena(zakljucnaOcenaizPredmeta);
			zakljucnaOcenaRepositories.save(zakljucenaOcena);
			logger.info("Zakljucna je ocena iz prvog polugodista");
			return zakljucenaOcena;
		}else {			
			zakljucenaOcena.setPolugodiste(Polugodiste.DRUGO_POLUGODISTE);
			zakljucenaOcena.setPredmet(predmetId);
			zakljucenaOcena.setUcenik(ucenikId);			
			zakljucenaOcena.setZakljucnaOcena(zakljucnaOcenaizPredmeta);
			zakljucnaOcenaRepositories.save(zakljucenaOcena);
			logger.info("Zakljucna je ocena za kraj godine");
			return zakljucenaOcena;
		}
			

		
	}

	@Override
	public Optional<ZakljucnaOcenaEntity> findByUcenik(UcenikEntity ucenikId) {
		Optional<ZakljucnaOcenaEntity> zakljucenaOcenaUcenika = zakljucnaOcenaRepositories.findByUcenik(ucenikId);
		return zakljucenaOcenaUcenika;
	}

	
	
	
}
