package com.iktpreobuka.backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.backend.entities.Uloga;
import com.iktpreobuka.backend.entities.UlogaEntity;
import com.iktpreobuka.backend.repositories.UlogaRepositories;

@Service
public class UlogaServiceImpl implements UlogaService {

	@Autowired
	private UlogaRepositories ulogaRepository;

	@Override
	public Iterable<UlogaEntity> IzlistajSveUloge() {
		Iterable<UlogaEntity> uloge = ulogaRepository.findAll();
		return uloge;
	}

	@Override
	public UlogaEntity ulogePoIdju(Integer id) {
		return ulogaRepository.findById(id).get();
	}

	@Override
	public Boolean obrisiUloge(Integer id) {
		UlogaEntity deletedUloga = ulogaRepository.findById(id).get();
		if (deletedUloga != null) {
			ulogaRepository.delete(deletedUloga);
			return true;
		}
		return false;
	}

}