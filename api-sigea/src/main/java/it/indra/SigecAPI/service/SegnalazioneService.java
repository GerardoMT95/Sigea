package it.indra.SigecAPI.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.indra.SigeaCommons.model.SegnalazioneFilter;
import it.indra.SigeaCommons.model.SegnalazioneModel;
import it.indra.SigeaCommons.model.SegnalazioneModelList;
import it.indra.SigecAPI.datafiltermanager.SegnalazioneFilterManager;
import it.indra.SigecAPI.entity.DettaglioUtente;
import it.indra.SigecAPI.entity.Segnalazione;
import it.indra.SigecAPI.mapper.SegnalazioneMapper;
import it.indra.SigecAPI.repository.DettaglioUtenteRepository;
import it.indra.SigecAPI.repository.SegnalazioneRepository;
import it.indra.SigecAPI.util.WrapperFilterRequest;
import it.indra.SigecAPI.util.WrapperFilterResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SegnalazioneService {
	
	@Autowired
	private AsyncService asyncService;

	@Autowired
	private SegnalazioneRepository segnalazioniRepository;
	
	@Autowired
	private DettaglioUtenteRepository dettaglioUtenteRepository;
	
	@Autowired
	private SegnalazioneMapper segnalazioneMapper;
	
	//@Autowired
	//private MailService mailService;
	
	@Value("${jms.mail.configurazioneEmail}")
	private String configurazioneEmailString;
	
	//private JSONArray configurazioneEmail;
	
//	@PostConstruct
//	public void inizializzaConfigurazioneEmail() {
//		configurazioneEmail = new JSONArray(configurazioneEmailString);
//	}
	
	public WrapperFilterResponse<?> getListaPaginata(WrapperFilterRequest<SegnalazioneFilter> request, Long idUtente) {
		DettaglioUtente chiamante = dettaglioUtenteRepository.findById(idUtente).get();
//		if (chiamante.getTipologia().getPermessiSommati().getOrDefault("filtroSegnalazioneSeeAll",false) != true) {
//			request.getFilter().setIdUtenteIns(idUtente);
//		}
		return segnalazioniRepository.findByPageFilter(request, SegnalazioneModelList.class, new SegnalazioneFilterManager());
	}

	public Segnalazione insertSegnalazione(SegnalazioneModel segnModel, Long idUtente) throws IllegalAccessException, InvocationTargetException {
		Segnalazione segnEntity = segnalazioneMapper.dtoToEntity(segnModel);
		segnEntity.setUsernameIns(dettaglioUtenteRepository.findById(idUtente).get());
		Segnalazione segnSalvata = segnalazioniRepository.save(segnEntity);
		try {
			asyncService.invioMailSegnalazione(segnSalvata.getNome(), segnSalvata.getUsernameIns().getEmail());
		}catch(Exception e) {
			log.error("IMPOSSIBILE INVIARE EMAIL: " + e.getMessage());
		}
		return segnSalvata;
	}
	
	public Set<SegnalazioneModel> getListaSegnalazioniPerDataLuogo(Date dataDa, String codIstat) {
		Set<Segnalazione> segnalazioneList = segnalazioniRepository.findAllByDataDaAndCodIstat(dataDa, codIstat);
		return segnalazioneMapper.entityToDtoSet(segnalazioneList);
	}
	
	public boolean cambiaStato(Long segnalazioneId, String status) {
		Segnalazione segnalazione = segnalazioniRepository.getOne(segnalazioneId);
		segnalazione.setStatus(status);
		segnalazioniRepository.save(segnalazione);
		return true;
	}
	
	public SegnalazioneModel getSegnalazione(Long segnalazioneId) {
		Segnalazione segnalazione = segnalazioniRepository.getOne(segnalazioneId);
		return segnalazioneMapper.entityToDto(segnalazione);
	}
}
