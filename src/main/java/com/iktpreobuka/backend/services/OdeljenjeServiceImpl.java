package com.iktpreobuka.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.backend.dto.OdeljenjeDTO;
import com.iktpreobuka.backend.entities.NastavnikEntity;
import com.iktpreobuka.backend.entities.OdeljenjeEntity;
import com.iktpreobuka.backend.repositories.NastavnikRepositories;
import com.iktpreobuka.backend.repositories.OdeljenjeRepositories;

@Service
public class OdeljenjeServiceImpl implements OdeljenjeService {
	@Autowired
	private OdeljenjeRepositories odeljenjeRepositories;
	@Autowired
	private NastavnikRepositories nastRepositories;

	@Override
	public OdeljenjeEntity novoOdeljeEntity(OdeljenjeDTO novoOdeljenjeDTO, Long id) {
		OdeljenjeEntity odeljenje = new OdeljenjeEntity();
		Optional<NastavnikEntity> nastavnik = nastRepositories.findById(id);
		if (nastavnik.isPresent()) {
			odeljenje.setName(novoOdeljenjeDTO.getName());
			odeljenje.setRazred(novoOdeljenjeDTO.getRazred());
			odeljenje.setNastavnik(nastavnik.get());
			return odeljenjeRepositories.save(odeljenje);
		}
		return null;
	}

}
