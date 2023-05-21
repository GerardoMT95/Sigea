package it.indra.SigecAPI.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.indra.SigeaCommons.model.BandoEventoDTO;
import it.indra.SigeaCommons.model.BandoLocationDTO;
import it.indra.SigeaCommons.model.BandoModelList;
import it.indra.SigeaCommons.model.BandoPeriodoDTO;
import it.indra.SigeaCommons.model.LocationModel;
import it.indra.SigeaCommons.model.PeriodoModel;
import it.indra.SigecAPI.clientstub.DMSClientStub;
import it.indra.SigecAPI.entity.Evento;
import it.indra.SigecAPI.entity.Location;
import it.indra.SigecAPI.entity.LogEvento;
import it.indra.SigecAPI.entity.LogPubblicazioni;
import it.indra.SigecAPI.entity.Periodo;
import it.indra.SigecAPI.entity.Pubblicazione;
import it.indra.SigecAPI.mapper.LocationMapper;
import it.indra.SigecAPI.mapper.PeriodoMapper;
import it.indra.SigecAPI.repository.EventoRepository;
import it.indra.SigecAPI.repository.LogPubblicazioneRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BandoService {

	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private LogPubblicazioneRepository logPubblicazioneRepository;
	
	@Autowired
	private CacheTokenService cacheTokenService;
	
	@Autowired
	private DMSClientStub dmsService;
	

	public Set<BandoModelList> getBandiProgetti(Long idAttivita) {
		Set<BandoModelList> bandiSet = eventoRepository.findBandiProgetti(idAttivita);
		return bandiSet;
	}

	public List<BandoEventoDTO> getEventiByBando(String idBando) {
		List<Evento> eventi = eventoRepository.findByBando_BandoIdAndStato_statoIdNot(idBando, "BOZZA");

		List<BandoEventoDTO> bandi = new ArrayList<BandoEventoDTO>();
		for (Evento evento : eventi) {
			String activityObj="";
			String cfTitolare="";
			String cfPiva="";
			String ragioneSociale = "";
			
		    Set<BandoLocationDTO> bandoLocationDTO = getLocationSet(evento.getLocationSet());
		    Set<BandoPeriodoDTO> bandoPeriodoDTO = getPeriodoSet(evento.getPeriodoSet());

		    
			Pubblicazione pubblicazioneVip = getPubblicazioneVip(evento.getPubblicazioneSet());
			List<LogPubblicazioni> logPubblicazioniList= logPubblicazioneRepository.findByPubblicazione(pubblicazioneVip);

			BandoEventoDTO bandoEvento = new BandoEventoDTO();
			bandoEvento.setIdEvento("" + evento.getEventoId());
			bandoEvento.setIdProgetto(evento.getProgetto() != null ? "" + evento.getProgetto().getProgettoId() : "");
			bandoEvento.setTitoloEvento(evento.getDatiGenerali().getTitolo());
			bandoEvento.setCfPromotore(evento.getOwner().getCodFiscale());
			bandoEvento.setDenominazioneAziendaPromotrice(evento.getAttivita().getDenominazione().toUpperCase());
			bandoEvento.setNomeOrganizzazione(evento.getProgetto() != null ? "" + evento.getProgetto().getNomeOrganizzazione() : ""); 
			
			bandoEvento.setLuogoEvento(bandoLocationDTO);
			bandoEvento.setDataEvento(bandoPeriodoDTO);
			bandoEvento.setDataPresentazione(getFirstValutation(evento.getLogSet()));
			bandoEvento.setDataValidazione(getLastValidation(evento.getLogSet())); 
			bandoEvento.setDataPubblicazione(getLastPubblication(logPubblicazioniList));
	
			try {
				activityObj = dmsService.getActivityDTO(cacheTokenService.getToken(), evento.getAttivita().getAttivitaId());
				JSONObject obj = new JSONObject(activityObj);
				cfPiva = obj.getString("cfPiva");
				cfTitolare = obj.getString("cfTitolare");
				ragioneSociale =obj.getString("ragioneSociale");
			} catch (Exception e) {
				log.error("IMPOSSIBILE RECUPERARE DATI SU CF E/O PI, potrebbe trattarsi di un ente con idAttivita " + evento.getAttivita().getAttivitaId());
			}
			
			bandoEvento.setCfAziendaPromotrice(cfPiva.matches("") ? cfTitolare.matches("") ? "" : cfTitolare :cfPiva ); //se non è un ente
			bandoEvento.setRagioneSocialeAziendaPromotrice(ragioneSociale); //se non è un ente
			bandi.add(bandoEvento);
		}

		return bandi;
	}

	private Set<BandoPeriodoDTO> getPeriodoSet(Set<Periodo> periodoSet) {

		Set<BandoPeriodoDTO> bandoPeriodoDTOSet = new HashSet<>();
		  
		  for (Periodo periodo : periodoSet) {
			  
			  BandoPeriodoDTO bandoPeriodoDTO = new BandoPeriodoDTO();
			  
			  bandoPeriodoDTO.setCadenza(periodo.getCadenza());
			  bandoPeriodoDTO.setCadenzaDom(periodo.getCadenzaDom());
			  bandoPeriodoDTO.setCadenzaGio(periodo.getCadenzaGio());
			  bandoPeriodoDTO.setCadenzaLun(periodo.getCadenzaLun());
			  bandoPeriodoDTO.setCadenzaMar(periodo.getCadenzaMar());
			  bandoPeriodoDTO.setCadenzaMensile(periodo.getCadenzaMensile());
			  bandoPeriodoDTO.setCadenzaMer(periodo.getCadenzaMer());
			  bandoPeriodoDTO.setCadenzaSab(periodo.getCadenzaSab());
			  bandoPeriodoDTO.setCadenzaVen(periodo.getCadenzaVen());
			  bandoPeriodoDTO.setChiusuraDom(periodo.getChiusuraDom());
			  bandoPeriodoDTO.setChiusuraGio(periodo.getChiusuraGio());
			  bandoPeriodoDTO.setChiusuraLun(periodo.getChiusuraLun());
			  bandoPeriodoDTO.setChiusuraMar(periodo.getChiusuraMar());
			  bandoPeriodoDTO.setChiusuraMer(periodo.getChiusuraMer());
			  bandoPeriodoDTO.setChiusuraSab(periodo.getChiusuraSab());
			  bandoPeriodoDTO.setChiusuraVen(periodo.getChiusuraVen());
			  bandoPeriodoDTO.setDataA(periodo.getDataA());
			  bandoPeriodoDTO.setDataDa(periodo.getDataDa());
			  bandoPeriodoDTO.setDataSecca(periodo.getDataSecca());
			  bandoPeriodoDTO.setFgContinuato(periodo.getFgContinuato());
			  bandoPeriodoDTO.setOrarioApertura(periodo.getOrarioApertura());
			  bandoPeriodoDTO.setOrarioAperturaMattina(periodo.getOrarioAperturaMattina());
			  bandoPeriodoDTO.setOrarioAperturaPomeriggio(periodo.getOrarioAperturaPomeriggio());
			  bandoPeriodoDTO.setOrarioChiusura(periodo.getOrarioChiusura());
			  bandoPeriodoDTO.setOrarioChiusuraMattina(periodo.getOrarioChiusuraMattina());
			  bandoPeriodoDTO.setOrarioChiusuraPomeriggio(periodo.getOrarioChiusuraPomeriggio());
			  bandoPeriodoDTO.setPeriodoId(periodo.getPeriodoId());			  
			  
			  bandoPeriodoDTOSet.add(bandoPeriodoDTO);
		  }
			 
		return bandoPeriodoDTOSet;
	
	}

	private Set<BandoLocationDTO> getLocationSet(Set<Location> locationSet) {
	
		Set<BandoLocationDTO> bandoLocationDTOSet = new HashSet<>();
		  
		  for (Location location : locationSet) {
			  
			  BandoLocationDTO bandoLocationDTO = new BandoLocationDTO();
			  
			  bandoLocationDTO.setArea(location.getArea());
			  bandoLocationDTO.setCap(location.getCap());
			  bandoLocationDTO.setCodArea(location.getCodArea());
			  bandoLocationDTO.setCodComune(location.getCodComune());
			  bandoLocationDTO.setCodNazione(location.getCodNazione());
			  bandoLocationDTO.setCodProvincia(location.getCodProvincia());
			  bandoLocationDTO.setCodRegione(location.getCodRegione());
			  bandoLocationDTO.setComune(location.getComune());
			  bandoLocationDTO.setComuneEstero(location.getComuneEstero());
			  bandoLocationDTO.setFgPrincipale(location.getFgPrincipale());
			  bandoLocationDTO.setIndirizzo(location.getIndirizzo());
			  bandoLocationDTO.setLatitudine(location.getLatitudine());
			  bandoLocationDTO.setNazione(location.getNazione());
			  bandoLocationDTO.setNomeLocation(location.getNomeLocation());
			  bandoLocationDTO.setProvincia(location.getProvincia());
			  bandoLocationDTO.setPuglia(location.getPuglia());
			  bandoLocationDTO.setRegione(location.getRegione());
			  bandoLocationDTO.setLink(location.getLink());
			  bandoLocationDTO.setLocationId(location.getLocationId());
			  bandoLocationDTO.setLongitudine(location.getLongitudine());
			  
			  bandoLocationDTOSet.add(bandoLocationDTO);
		  }
			 
		return bandoLocationDTOSet;
	}
	
	
	
	

	private Pubblicazione getPubblicazioneVip(Set<Pubblicazione> pubblicazioneSet) {

		for (Pubblicazione pubblicazione : pubblicazioneSet) {
			if (pubblicazione.getRedazione().getRedazioneId().equalsIgnoreCase("VIP")) {
				return pubblicazione;
			}
		}
		return null;
	}

	private String getLastPubblication(List<LogPubblicazioni> logPubblicazioniList) {
		
		/*DESC*/
		logPubblicazioniList.sort((i1, i2) -> i2.getDataModifica().compareTo(i1.getDataModifica()));

		for (LogPubblicazioni logPubblicazione : logPubblicazioniList) {
			if (logPubblicazione.getStato().getStatoId().equalsIgnoreCase("PUBBLICATO")) {
				return logPubblicazione.getDataModifica().toString();
			}
		}
		
		return null;
	
	}

	private String getLastValidation(Set<LogEvento> logSet) {

		List<LogEvento> logList = new ArrayList<LogEvento>();
		logList.addAll(logSet);
		
		/*DESC*/
		logList.sort((i1, i2) -> i2.getDataModifica().compareTo(i1.getDataModifica()));
		
		for (LogEvento logEvento : logList) {
			if (logEvento.getDescrizioneStato().equalsIgnoreCase("Validato")) {
				return logEvento.getDataModifica().toString();
			}
		}
		
		return null;
	
	}

	private String getFirstValutation(Set<LogEvento> logSet) {

		List<LogEvento> logList = new ArrayList<LogEvento>();
		logList.addAll(logSet);
		
		logList.sort((i1, i2) -> i1.getDataModifica().compareTo(i2.getDataModifica()));

		for (LogEvento logEvento : logList) {
			if (logEvento.getDescrizioneStato().equalsIgnoreCase("In attesa di valutazione")) {
				return logEvento.getDataModifica().toString();
			}
		}
		
		return null;
	}

}
