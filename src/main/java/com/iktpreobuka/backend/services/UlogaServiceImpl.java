package com.iktpreobuka.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.backend.entities.Uloga;
import com.iktpreobuka.backend.entities.UlogaEntity;
import com.iktpreobuka.backend.repositories.UlogaRepositories;

@Service
public class UlogaServiceImpl implements UlogaService {

	@Autowired
	private UlogaRepositories ulogaRepository;
	private final Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());


	@Override
	public Iterable<UlogaEntity> IzlistajSveUloge() {
		Iterable<UlogaEntity> uloge = ulogaRepository.findAll();
		logger.info("poslat je zahtev da se nadju sve uloge");
		return uloge;
	}

	@Override
	public UlogaEntity ulogePoIdju(Integer id) {
		logger.info("poslat je zahtev da se nadje uloga po id-ju");
		return ulogaRepository.findById(id).get();
	}

	@Override
	public Boolean obrisiUloge(Integer id) {
		UlogaEntity deletedUloga = ulogaRepository.findById(id).get();
		if (deletedUloga != null) {
			logger.info("uloga ce biti obrisana ako nije prazan id");
			ulogaRepository.delete(deletedUloga);
			return true;
		}
		logger.info("ne postoji uloga sa ovim id-jem");
		return false;
	}

	@Override
	public UlogaEntity findByUloga(Uloga uloga) {
		logger.info("poslat je zahtev da se nadje po ulozi");
		return ulogaRepository.findByUloga(uloga);
	}

}