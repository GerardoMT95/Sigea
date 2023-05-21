package it.indra.SigecAPI.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indra.es.utils.LogUtility;

import it.indra.SigeaCommons.model.EventoModel;
import it.indra.SigeaCommons.model.redazioni.VIPSchedaRelazioneModel;
import it.indra.SigecAPI.entity.Evento;
import it.indra.SigecAPI.entity.Location;
import it.indra.SigecAPI.entity.Periodo;
import it.indra.SigecAPI.entity.Pubblicazione;
import it.indra.SigecAPI.entity.Relazione;
import it.indra.SigecAPI.repository.EventoRepository;
import it.indra.SigecAPI.repository.PubblicazioneRepository;
import it.indra.SigecAPI.repository.RelazioneRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RelazioniService {

	@Autowired
	private PubblicazioneRepository pubblicazioneRepository;
	
	@Autowired
	private RelazioneRepository relazioneRepository;
	
	@Autowired
	private EventoRepository eventoRepository;

	public void allignRelazioniScheda(Pubblicazione pubblicazioneSalvata, JsonNode oldGenericMetadata, EventoModel evento, String redazioneId)
			throws JsonMappingException, JsonProcessingException {

		JsonNode genericMetadata = pubblicazioneSalvata.getGenericMetadataWip();
		
		log.info("Allineo relazioni per pubblicazione per eventoId #" + evento.getEventoId() + " e radazioneId " + redazioneId);

		log.info("Metadati aggiornati: " + !genericMetadata.equals(oldGenericMetadata));
		


		updateRelazione(false, pubblicazioneSalvata, evento, redazioneId);
		updateRelazione(true, pubblicazioneSalvata, evento, redazioneId);
		
//		JsonNode relazioneSet = genericMetadata.get("relazioneSet");
//		JsonNode relazioneSetAggiunto = genericMetadata.get("relazioneSetAggiunto");
		
		if(oldGenericMetadata != null) {
			JsonNode relazioneSetOld = oldGenericMetadata.get("relazioneSet");
			removeRelazioniOld(false, pubblicazioneSalvata, relazioneSetOld, evento, redazioneId);
	
			JsonNode relazioneSetAggiuntoOld = oldGenericMetadata.get("relazioneSetAggiunto");
			removeRelazioniOld(true, pubblicazioneSalvata, relazioneSetAggiuntoOld, evento, redazioneId);
		}
		

	}

	private void removeRelazioniOld(boolean isAggiuntivo, Pubblicazione pubblicazioneSalvata, JsonNode relazioneSetOld, EventoModel evento, String redazioneId) throws JsonMappingException, JsonProcessingException {

		List<Long> listEventiRelazionati = new ArrayList<Long>();

		String key = "relazioneSet";
		if (isAggiuntivo) {
			key = "relazioneSetAggiunto";
		}
		
		JsonNode genericMetadataCorrente = pubblicazioneSalvata.getGenericMetadataWip();
		JsonNode relazioneSet = genericMetadataCorrente.get(key);
		
		List<VIPSchedaRelazioneModel> relazioneSetList = getVIPSchedaRelazioneModelList(relazioneSet);
		List<VIPSchedaRelazioneModel> relazioneSetListOld = getVIPSchedaRelazioneModelList(relazioneSetOld);
		
		relazioneSetListOld.removeIf(x -> relazioneSetList.stream().anyMatch(y -> y.getEventoRelazionatoId().equals(x.getEventoRelazionatoId())));
		
		for (VIPSchedaRelazioneModel tmp : relazioneSetListOld) {
			listEventiRelazionati.add(tmp.getEventoRelazionatoId());
			if(isAggiuntivo) {// Solo per le relazioni aggiuntive sarà questa la funzione che allinea il db
				pubblicazioneSalvata.getEvento().getRelazioneSet().removeIf(x -> x.getRelazioneId().longValue() == tmp.getRelazioneId());
			}
			
		}
		
		pubblicazioneRepository.save(pubblicazioneSalvata);
		
		Set<Pubblicazione> pubblicazioniRelazionateToUp = pubblicazioneRepository
				.findByEvento_EventoIdInAndRedazione_RedazioneId(listEventiRelazionati, redazioneId);
		
		
		
		for (Pubblicazione pubblicazione : pubblicazioniRelazionateToUp) {
			JsonNode genericMetadataWip = pubblicazione.getGenericMetadataWip();

			
			JsonNode pubblicazioneRelazioneSetNode = genericMetadataWip.get(key);
			List<VIPSchedaRelazioneModel> listPubblicazioneRelazioneSet = getVIPSchedaRelazioneModelList(
					pubblicazioneRelazioneSetNode);
			
			VIPSchedaRelazioneModel vipSchedaRelazioneModelToDel = listPubblicazioneRelazioneSet.stream().filter(x -> x.getEventoRelazionatoId().equals(evento.getEventoId())).findFirst().orElse(null);
			
			if(vipSchedaRelazioneModelToDel != null) {
				if(vipSchedaRelazioneModelToDel.getRelazioneId() != null && isAggiuntivo) {// Solo per le relazioni aggiuntive sarà questa la funzione che allinea il db
					pubblicazione.getEvento().getRelazioneSet().removeIf(x -> x.getRelazioneId().longValue() == vipSchedaRelazioneModelToDel.getRelazioneId());
				}
				listPubblicazioneRelazioneSet.remove(vipSchedaRelazioneModelToDel);
			}
			
			ObjectMapper mapper = new ObjectMapper();
			JSONObject json = new JSONObject(genericMetadataWip.toString());

			json.put(key, listPubblicazioneRelazioneSet);

			pubblicazione.setGenericMetadataWip(mapper.readValue(json.toString(), JsonNode.class));

		}
		
		pubblicazioneRepository.saveAll(pubblicazioniRelazionateToUp);
	}


	private void updateRelazione(boolean isAggiuntivo, Pubblicazione pubblicazioneSalvata , EventoModel eventoModel,
			String redazioneId) throws JsonMappingException, JsonProcessingException {

		List<Long> listEventiRelazionati = new ArrayList<Long>();

		String key = "relazioneSet";
		if (isAggiuntivo) {
			key = "relazioneSetAggiunto";
		}
		
		JsonNode genericMetadataCorrente = pubblicazioneSalvata.getGenericMetadataWip();
		JsonNode relazioneSetNode = genericMetadataCorrente.get(key);
		
		List<VIPSchedaRelazioneModel> relazioneSetList = getVIPSchedaRelazioneModelList(relazioneSetNode);

		for (VIPSchedaRelazioneModel tmp : relazioneSetList) {
			listEventiRelazionati.add(tmp.getEventoRelazionatoId());
		}

		Set<Pubblicazione> pubblicazioniRelazionateToUp = pubblicazioneRepository
				.findByEvento_EventoIdInAndRedazione_RedazioneId(listEventiRelazionati, redazioneId);

		if(isAggiuntivo) {
			relazioneRepository.deleteByEventoRelazionatoIdAndRedazioneId(eventoModel.getEventoId(), redazioneId);
		}
		
		for (Pubblicazione pubblicazione : pubblicazioniRelazionateToUp) {// Ciclo sulle pubblicazioni

			JsonNode genericMetadataWip = pubblicazione.getGenericMetadataWip();

			Relazione relazioneCorrente = new Relazione();
			Relazione relazioneEsterna = new Relazione();
			
			JsonNode pubblicazioneRelazioneSetNode = genericMetadataWip.get(key);
			
			List<VIPSchedaRelazioneModel> listPubblicazioneRelazioneSet = getVIPSchedaRelazioneModelList(
					pubblicazioneRelazioneSetNode);

			VIPSchedaRelazioneModel vipSchedaRelazioneModelCorrente = relazioneSetList.stream()
					.filter(x -> x.getEventoRelazionatoId().longValue() == pubblicazione.getEvento().getEventoId().longValue()).findFirst()
					.orElse(null);
			
			// Cerco nelle relazioni della pubblicazione l'eventoId corrente
			VIPSchedaRelazioneModel vipSchedaRelazioneModelEsternoToUP = listPubblicazioneRelazioneSet.stream()
					.filter(x -> x.getEventoRelazionatoId().longValue() == eventoModel.getEventoId().longValue()).findFirst()
					.orElse(null);

			// Dati aggiornati per relazione esterna eventoCorrente -> eventoRelazionato
			Evento eventoCorrente = eventoRepository.findById(eventoModel.getEventoId()).get();
			Evento eventoEsterno = eventoRepository.findById(vipSchedaRelazioneModelCorrente.getEventoRelazionatoId()).get();
			
			String comuneCorrente = getRelazioneComuneFromEventoModel(eventoCorrente.getLocationSet());
			String comuneEsterno = getRelazioneComuneFromEventoModel(eventoEsterno.getLocationSet());
			
			String periodoCorrente = getRelazionePeriodoFromEventoModel(eventoCorrente.getPeriodoSet());
			String periodoEsterno = getRelazionePeriodoFromEventoModel(eventoEsterno.getPeriodoSet());
			
			String titoloCorrente = eventoCorrente.getDatiGenerali().getTitolo();
			String titoloEsterno = eventoEsterno.getDatiGenerali().getTitolo();
			
			String tipoCorrente = eventoCorrente.getTipo();
			String tipoEsterno = eventoEsterno.getTipo();
			
			Long eventoIdCorrente = eventoCorrente.getEventoId();
//			Long eventoIdEsterno = eventoEsterno.getEventoId();

			String tipoRelazioneCorrente = vipSchedaRelazioneModelCorrente.getTipoRelazione();
			String tipoRelazioneEsterno = "CONTIENE".equalsIgnoreCase(vipSchedaRelazioneModelCorrente.getTipoRelazione()) ? "CONTIENE" : "CONTENUTO";
			
//			Long relazioneCorrenteId = vipSchedaRelazioneModelCorrente != null ? vipSchedaRelazioneModelCorrente.getRelazioneId() : null;
			Long relazioneEsternoId = vipSchedaRelazioneModelEsternoToUP != null ? vipSchedaRelazioneModelEsternoToUP.getRelazioneId() : null;
			
			Boolean mantieni = vipSchedaRelazioneModelCorrente.getMantieni();
			
			String pubblicazioni = "";
			
			for(Pubblicazione pubb : eventoEsterno.getPubblicazioneSet())
			{
				if(pubblicazioni.length()>0)
				pubblicazioni += "|";
				
				pubblicazioni +=pubb.getRedazione().getNome();
			}
			
			
			// Aggiorno tabella per relazioni esterne
			if(isAggiuntivo) {

				// Relazione Corrente
				relazioneCorrente.setEvento(eventoCorrente);
				relazioneCorrente.setEventoRelazionatoId(vipSchedaRelazioneModelCorrente.getEventoRelazionatoId());
				relazioneCorrente.setRedazioneId(redazioneId);
				relazioneCorrente.setRelazioneId(vipSchedaRelazioneModelCorrente.getRelazioneId());
				relazioneCorrente.setTipoEventoAssociato(vipSchedaRelazioneModelCorrente.getTipoEventoAssociato());
				relazioneCorrente.setTipoRelazione(vipSchedaRelazioneModelCorrente.getTipoRelazione());
				
				
				relazioneCorrente = save(relazioneCorrente);
				
				vipSchedaRelazioneModelCorrente.setComune(comuneEsterno);
				vipSchedaRelazioneModelCorrente.setPeriodo(periodoEsterno);
				vipSchedaRelazioneModelCorrente.setTitolo(titoloEsterno);
				vipSchedaRelazioneModelCorrente.setTipoEventoAssociato(tipoEsterno);
				vipSchedaRelazioneModelCorrente.setRelazioneId(relazioneCorrente.getRelazioneId());
				vipSchedaRelazioneModelCorrente.setTipoRelazione(tipoRelazioneCorrente);

				vipSchedaRelazioneModelCorrente.setStatoEventoAssociato(eventoEsterno.getStato().getStatoId());
				vipSchedaRelazioneModelCorrente.setSchedePubblicazione(pubblicazioni);
				
				
				// Relazione esterna
				relazioneEsterna.setEvento(eventoEsterno);
				relazioneEsterna.setEventoRelazionatoId(eventoIdCorrente);
				relazioneEsterna.setRedazioneId(redazioneId);
				relazioneEsterna.setRelazioneId(relazioneEsternoId);
				relazioneEsterna.setTipoEventoAssociato(tipoCorrente);
				relazioneEsterna.setTipoRelazione(tipoRelazioneEsterno);
				
				relazioneEsterna = save(relazioneEsterna);// Aggiorna o inserisce una relazione esterna sul tabella
				
				relazioneEsternoId = relazioneEsterna.getRelazioneId();
			}
			
			
			if (vipSchedaRelazioneModelEsternoToUP != null) {// Aggiornamento json dei genericMetadata

				vipSchedaRelazioneModelEsternoToUP.setComune(comuneCorrente);
				vipSchedaRelazioneModelEsternoToUP.setPeriodo(periodoCorrente);
				vipSchedaRelazioneModelEsternoToUP.setTitolo(titoloCorrente);
				vipSchedaRelazioneModelEsternoToUP.setTipoEventoAssociato(tipoCorrente);
				vipSchedaRelazioneModelEsternoToUP.setRelazioneId(relazioneEsternoId);
				vipSchedaRelazioneModelEsternoToUP.setTipoRelazione(tipoRelazioneEsterno);
				vipSchedaRelazioneModelEsternoToUP.setStatoEventoAssociato(eventoEsterno.getStato().getStatoId());
				vipSchedaRelazioneModelEsternoToUP.setSchedePubblicazione(pubblicazioni);
				
				
				if (isAggiuntivo) {
					vipSchedaRelazioneModelEsternoToUP.setRedazioneId(redazioneId);
				} else {
					vipSchedaRelazioneModelEsternoToUP.setMantieni(mantieni);
				}

			} else {// Aggiunta
				
				VIPSchedaRelazioneModel vipSchedaRelazioneModelEsternoToAdd = new VIPSchedaRelazioneModel();

				vipSchedaRelazioneModelEsternoToAdd.setEventoRelazionatoId(eventoIdCorrente);
				
				vipSchedaRelazioneModelEsternoToAdd.setComune(comuneCorrente);
				vipSchedaRelazioneModelEsternoToAdd.setPeriodo(periodoCorrente);
				vipSchedaRelazioneModelEsternoToAdd.setTitolo(titoloCorrente);
				vipSchedaRelazioneModelEsternoToAdd.setTipoEventoAssociato(tipoCorrente);
				vipSchedaRelazioneModelEsternoToAdd.setTipoRelazione(tipoRelazioneEsterno);
				vipSchedaRelazioneModelEsternoToAdd.setMantieni(true);
				vipSchedaRelazioneModelEsternoToAdd.setSchedePubblicazione(key);
				
				if (isAggiuntivo) {
					vipSchedaRelazioneModelEsternoToAdd.setRelazioneId(relazioneEsterna.getRelazioneId());
					vipSchedaRelazioneModelEsternoToAdd.setRedazioneId(redazioneId);
				} else {
					
					Relazione rel = pubblicazione.getEvento().getRelazioneSet().stream().filter(x -> x.getEventoRelazionatoId().longValue() == eventoModel.getEventoId().longValue()).findFirst().get();
					
					vipSchedaRelazioneModelEsternoToAdd.setRelazioneId(rel.getRelazioneId());
					vipSchedaRelazioneModelEsternoToAdd.setMantieni(mantieni);
				}
				
				listPubblicazioneRelazioneSet.add(vipSchedaRelazioneModelEsternoToAdd);
			}
			

			ObjectMapper mapper = new ObjectMapper();
			
			JSONObject json = new JSONObject(genericMetadataWip.toString());
			JSONObject jsonCorrente = new JSONObject(genericMetadataCorrente.toString());
			
			json.put(key, listPubblicazioneRelazioneSet);
			jsonCorrente.put(key, relazioneSetList);

			pubblicazione.setGenericMetadataWip(mapper.readValue(json.toString(), JsonNode.class));
			pubblicazioneSalvata.setGenericMetadataWip(mapper.readValue(jsonCorrente.toString(), JsonNode.class));

		}
		pubblicazioniRelazionateToUp.add(pubblicazioneSalvata);
		pubblicazioneRepository.saveAll(pubblicazioniRelazionateToUp);
	}
	
	private List<VIPSchedaRelazioneModel> getVIPSchedaRelazioneModelList(JsonNode relazioneSetNode) {

		List<VIPSchedaRelazioneModel> result = new ArrayList<VIPSchedaRelazioneModel>();
		try {
			ObjectMapper mapper = new ObjectMapper();

			if(relazioneSetNode != null) {
				result = mapper.readValue(relazioneSetNode.toString(), new TypeReference<List<VIPSchedaRelazioneModel>>() {
				});
			}
			
		} catch (Exception e) {
			log.error(LogUtility.exceptionToLog(e));
		}

		return result;
	}
	
	public String getRelazionePeriodoFromEventoModel(Set<Periodo> periodoSet){
	
	String periodo = null;
	Date   dataMin=  null;
	Date   dataMax=  null;
	
	if (periodoSet != null) {

		for (Periodo period : periodoSet ) {

			if (period.getDataDa()!=null && dataMin==null) {
				dataMin=period.getDataDa();
			}else if (period.getDataDa()!=null && period.getDataDa().compareTo(dataMin)<0) {
				dataMin=period.getDataDa();
			}


			if (period.getDataA()!=null && dataMax==null) {
				dataMax=period.getDataA();
			}else if (period.getDataA()!=null && period.getDataA().compareTo(dataMax)>0) {
				dataMax=period.getDataA();
			}
		}

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
		if (dataMin != null && dataMax!=null) {
			if (dataMin.compareTo(dataMax)==0) {
				periodo = format.format(dataMin);
			}else if (dataMin.compareTo(dataMax)!=0) {
				periodo = "dal " + format.format(dataMin) + " al " + format.format(dataMax);
			} 
		}else {
			periodo = "n/d";
		}
	}
	
	return periodo;
	
	}
	
	public String getRelazioneComuneFromEventoModel(Set<Location> locationSet){
		
		String comune = "";
		Location tmpLocation = locationSet.stream().findFirst().orElse(new Location());
		
		if(tmpLocation.getComune() != null) {
			comune = tmpLocation.getComune();
		} else if(tmpLocation.getComuneEstero() != null) {
			comune = tmpLocation.getComuneEstero();
		}
		
		return comune;
		
	}
	
	public Relazione save(Relazione relazione) {
		log.debug("Salvo relazione per evento con id #" + relazione.getEvento().getEventoId());
		
		return relazioneRepository.save(relazione);
	}
	
	public void deleteById(Long relazioneId) {
		
		log.info("Cancello relazione con id #" + relazioneId);
		
		relazioneRepository.deleteById(relazioneId);
	}
}
