package it.indra.SigecAPI.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.indra.SigeaCommons.model.DettaglioUtenteModel;
import it.indra.SigeaCommons.model.RedazioneModel;
import it.indra.SigeaCommons.model.TipologiaModel;
import it.indra.SigecAPI.entity.DettaglioUtente;
import it.indra.SigecAPI.entity.Redazione;
import it.indra.SigecAPI.entity.Tipologia;
import it.indra.SigecAPI.mapper.DettaglioUtenteMapper;
import it.indra.SigecAPI.mapper.RedazioneMapper;
import it.indra.SigecAPI.mapper.TipologiaMapper;
import it.indra.SigecAPI.repository.DettaglioUtenteRepository;
import it.indra.SigecAPI.repository.RedazioneRepository;
import it.indra.SigecAPI.repository.TipologiaRepository;

@Service
public class UserService {

	@Autowired
	DettaglioUtenteRepository dettaglioUtenteRepository;
	
	@Autowired
	TipologiaRepository tipologiaRepository;
	
	@Autowired
	RedazioneRepository redazioneRepository;
	
	@Autowired
	DettaglioUtenteMapper dettaglioUtenteMapper;
	
	@Autowired
	TipologiaMapper tipologiaMapper;
	
	@Autowired
	RedazioneMapper redazioneMapper;
	
	public DettaglioUtenteModel profilaUtente(DettaglioUtenteModel dettaglioUser, String tipologia){
		TipologiaModel tipologiaModel = tipologiaMapper.entityToDto(tipologiaRepository.findById(tipologia).get());
		dettaglioUser.setTipologia(tipologiaModel);
		DettaglioUtente dettaglioUtente = dettaglioUtenteMapper.dtoToEntity(dettaglioUser);
		DettaglioUtente utenteSalvato = dettaglioUtenteRepository.save(dettaglioUtente);
		return dettaglioUtenteMapper.entityToDto(utenteSalvato);
	}
	
	public Set<TipologiaModel> getTipologie() {
		Set<Tipologia> tipologieSet = new HashSet<Tipologia>(tipologiaRepository.findAll());
		return tipologiaMapper.entityToDtoSet(tipologieSet);
	}
	
	public Set<RedazioneModel> getRedazioni() {
		Set<Redazione> redazioneSet = new HashSet<Redazione>(redazioneRepository.findAll());
		return redazioneMapper.entityToDtoSet(redazioneSet);
	}
	
	public Set<String> getElencoTipologieRedazionali(){
		Set<Tipologia> tipologieSet = new HashSet<Tipologia>(tipologiaRepository.findAll());
		Set<String> elenco = new HashSet<String>();
		for(Tipologia t: tipologieSet) {
			if(t.getIsRedattore()!=null && t.getIsRedattore().booleanValue()) {
				elenco.add(t.getTipologiaId());
			}
		}
		return elenco;
	}
}
