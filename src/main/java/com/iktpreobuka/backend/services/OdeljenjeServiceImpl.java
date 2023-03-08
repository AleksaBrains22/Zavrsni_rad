package com.iktpreobuka.backend.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iktpreobuka.backend.dto.OdeljenjeDTO;
import com.iktpreobuka.backend.entities.NastavnikEntity;
import com.iktpreobuka.backend.entities.OdeljenjeEntity;
import com.iktpreobuka.backend.repositories.OdeljenjeRepositories;

@Service
public class OdeljenjeServiceImpl implements OdeljenjeService {
	@Autowired
	private OdeljenjeRepositories odeljenjeRepositories;
	@Autowired
	private NastavnikServiceImpl nastavnikServiceImpl;
	private final Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());

	@Override
	public OdeljenjeEntity novoOdeljeEntity(OdeljenjeDTO novoOdeljenjeDTO, Long id) {
		OdeljenjeEntity odeljenje = new OdeljenjeEntity();
		NastavnikEntity nastavnik = nastavnikServiceImpl.findById(id);
		if (nastavnik != null) {
			odeljenje.setName(novoOdeljenjeDTO.getName());
			odeljenje.setRazred(novoOdeljenjeDTO.getRazred());
			odeljenje.setNastavnik(nastavnik);
			logger.info("poslat je zahtev za cuvanje novog odeljenja");
			return odeljenjeRepositories.save(odeljenje);
		}
		logger.info("nije uspesno obavljen zahtev za novo odeljenje");
		return null;
	}

	@Override
	public OdeljenjeEntity nadjiOdeljenjePOIdju(Long odeljenjeId) {
		OdeljenjeEntity odeljenje = odeljenjeRepositories.findById(odeljenjeId).get();
		logger.info("poslat je zahtev za trazenje odeljenja po id-ju");
		return odeljenje;
	}

}
