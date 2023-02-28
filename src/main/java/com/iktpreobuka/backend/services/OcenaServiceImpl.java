package com.iktpreobuka.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.backend.entities.NastavnikEntity;
import com.iktpreobuka.backend.entities.OcenaEntity;
import com.iktpreobuka.backend.entities.PredmetEntity;
import com.iktpreobuka.backend.entities.UcenikEntity;
import com.iktpreobuka.backend.repositories.OcenaRepositories;
@Service
public class OcenaServiceImpl implements OcenaService{
	@Autowired
	private OcenaRepositories ocenaRepositories;
	@Autowired 
	private UcenikServiceImpl ucenikServiceImpl;
	@Autowired
	private PredmetServiceImpl predmetServiceImpl;
	@Autowired
	private NastavnikServiceImpl nastavnikServiceImpl;
	
	
	@Override
	public OcenaEntity dodajNovuOcenu(Long ucenikId, Long predmetId, Long nastavnikId, OcenaEntity ocena) {
		UcenikEntity ucenik = ucenikServiceImpl.nadjiUceikiaPoIdju(ucenikId);
		NastavnikEntity nastavnik = nastavnikServiceImpl.findById(nastavnikId);
		PredmetEntity predmet = predmetServiceImpl.pronadjiPredmetPoIdju(predmetId);
		if( ucenik != null && nastavnik != null && predmet != null ) {
			
			if (ucenik.getOdeljenjeEntity().getRazred() == predmet.getRazred() ) {
				OcenaEntity novaOcena = new OcenaEntity();
				novaOcena.setUcenik(ucenik);
				novaOcena.setNastavnik(nastavnik);
				novaOcena.setPredmet(predmet);
				novaOcena.setOcena(ocena.getOcena());
				novaOcena.setRazred(ucenik.getOdeljenjeEntity().getRazred());
				ocenaRepositories.save(novaOcena);
				return novaOcena;
			}
			
		}
		return null;
	}

}
