package com.iktpreobuka.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.backend.entities.NastavnikEntity;
import com.iktpreobuka.backend.entities.OcenaEntity;
import com.iktpreobuka.backend.entities.PredmetEntity;
import com.iktpreobuka.backend.entities.UcenikEntity;
import com.iktpreobuka.backend.models.EmailObject;
import com.iktpreobuka.backend.repositories.OcenaRepositories;

@Service
public class OcenaServiceImpl implements OcenaService {
	@Autowired
	private OcenaRepositories ocenaRepositories;
	@Autowired
	private UcenikServiceImpl ucenikServiceImpl;
	@Autowired
	private PredmetServiceImpl predmetServiceImpl;
	@Autowired
	private NastavnikServiceImpl nastavnikServiceImpl;
	@Autowired
	private EmailServiceImpl emailServiceImpl;
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Override
	public OcenaEntity dodajNovuOcenu(Long ucenikId, Long predmetId, Long nastavnikId, OcenaEntity ocena) {
		UcenikEntity ucenik = ucenikServiceImpl.nadjiUceikiaPoIdju(ucenikId);
		NastavnikEntity nastavnik = nastavnikServiceImpl.findById(nastavnikId);
		PredmetEntity predmet = predmetServiceImpl.pronadjiPredmetPoIdju(predmetId);
		if (ucenik != null && nastavnik != null && predmet != null) {
			logger.info("id ucenika id korisnika i id nastavnika su tacni");
			if (ucenik.getOdeljenjeEntity().getRazred() == predmet.getRazred()
					&& dalinastavnikPredajepredmet(nastavnikId, predmetId)) {
				OcenaEntity novaOcena = new OcenaEntity();
				novaOcena.setUcenik(ucenik);
				novaOcena.setNastavnik(nastavnik);
				novaOcena.setPredmet(predmet);
				novaOcena.setOcena(ocena.getOcena());
				novaOcena.setRazred(ucenik.getOdeljenjeEntity().getRazred());
				ocenaRepositories.save(novaOcena);
				ocenasesaljenaemail(ocena, ucenik, predmet);
				logger.info("ocena je upisana u e-dnevnik i poslata roditelju");
				return novaOcena;
			}

		}
		return null;
	}

	private void ocenasesaljenaemail(OcenaEntity ocena, UcenikEntity ucenik, PredmetEntity predmet) {
		EmailObject emailObject = new EmailObject();
		emailObject.setTo(ucenik.getRoditelj().getEmail());
		emailObject.setSubject("Nova ocena");
		emailObject.setText("Ucenik " + ucenik.getIme() + "je dobio ocenu " + ocena.getOcena() + "iz predmeta "
				+ predmet.getName());
		emailServiceImpl.sendSimpleMessage(emailObject);
		logger.info("ocena je poslata na email roditelja");
	}

	private boolean dalinastavnikPredajepredmet(Long nastavnikId, Long predmetId) {
		NastavnikEntity nastavnik = nastavnikServiceImpl.findById(nastavnikId);
		PredmetEntity predmet = predmetServiceImpl.pronadjiPredmetPoIdju(predmetId);
		for (PredmetEntity predmetEntity : nastavnik.getPredmetKojNastavnikpredaje()) {
			if (predmetEntity.equals(predmet)) {
				logger.info("nastavnik predaje ovaj predmet");
				return true;

			}
		}
		logger.error("nastavnik ne predaje ovaj predmet");
		return false;
	}

	@Override
	public List<OcenaEntity> nadjiSveOceneUcenikaizJednogPredmeta(UcenikEntity ucenikId, PredmetEntity predmetId) {
		return ocenaRepositories.findByUcenikAndPredmet(ucenikId, predmetId);
	}

	@Override
	public List<OcenaEntity> nadjiOceneUcenikaIzSvihPredmeta(UcenikEntity ucenikId) {		
		return ocenaRepositories.findByUcenik(ucenikId);
	
	}


	@Override
	public OcenaEntity izmeniOcenuUcenikaIzPredmeta(Long ocenaId,OcenaEntity novaOcena) {
		OcenaEntity ocena =ocenaRepositories.findById(ocenaId).get();
		if(ocena != null) {
			ocena.setOcena(novaOcena.getOcena());
			logger.info("Ocena je promenjena");
			ocenaRepositories.save(ocena);
			return ocena;
		}
		logger.error("Nema ocene sa tim idjem");
		return null;
	}

	@Override
	public OcenaEntity izbrisiOcenuUcenika(Long ocenaId) {
		OcenaEntity ocena =ocenaRepositories.findById(ocenaId).get();
		if(ocena != null) {
			logger.info("Ocena je izbrisana");
			ocenaRepositories.delete(ocena);
		}
		logger.error("Nema ocene sa tim idjem");
		return null;
	}

}
