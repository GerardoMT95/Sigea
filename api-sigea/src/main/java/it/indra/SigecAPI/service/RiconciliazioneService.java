package it.indra.SigecAPI.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.indra.SigeaCommons.model.DettaglioUtenteModel;
import it.indra.SigeaCommons.model.ElencoEventiEmail;
import it.indra.SigeaCommons.model.ElencoRiconciliazioni;
import it.indra.SigecAPI.entity.Attivita;
import it.indra.SigecAPI.entity.DettaglioUtente;
import it.indra.SigecAPI.entity.Evento;
import it.indra.SigecAPI.mapper.DettaglioUtenteMapper;
import it.indra.SigecAPI.repository.AttivitaRepository;
import it.indra.SigecAPI.repository.DettaglioUtenteRepository;
import it.indra.SigecAPI.repository.EventoRepository;

@Service
public class RiconciliazioneService {
	
	@Autowired
	private AttivitaRepository attivitaRepository;

	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private DettaglioUtenteRepository dettaglioUtenteRepository;
	
	@Autowired
	private DettaglioUtenteMapper dettaglioUtenteMapper;
	
	@Autowired
	private UserService userService;
	
	private static final String RICONCILIATA = "RICONCILIATA";
	private static final String INUTILIZZATA = "INUTILIZZATA";
	private static final String PROMUOVI_EVENTO = "PROMUOVI_EVENTO";

	public List<ElencoEventiEmail> elaboraEmail(List<String> elencoEmail) {
		List<ElencoEventiEmail> elenco = new ArrayList<ElencoEventiEmail>();
		elencoEmail.forEach(email -> {
			ElencoEventiEmail eventoEmail = new ElencoEventiEmail();
			eventoEmail.setEmail(email);
			List<Evento> eventi = eventoRepository.findAllByEmailRiconciliazioneIgnoreCase(email);
			if (!eventi.isEmpty()) {
				Map<Long, String> eventiAssociati = new HashMap<Long, String>();
				eventi.stream().filter(evento -> (evento.getDaRiconciliare() != null && evento.getDaRiconciliare().booleanValue()))
						.forEach(evento -> {
							//Da riconciliare
							String titoloEvento = evento.getDatiGenerali() == null ? ""	: evento.getDatiGenerali().getTitolo();
							eventiAssociati.put(evento.getEventoId(), titoloEvento);
						});
				if (!eventiAssociati.isEmpty()) {
					eventoEmail.setEventi(eventiAssociati);
				} else
					eventoEmail.setMotivazioneErrore(RICONCILIATA);
			} else
				eventoEmail.setMotivazioneErrore(INUTILIZZATA);		
			elenco.add(eventoEmail);
		});
		return elenco;
	}
	
	public Map<Long, String> verificaRiconciliazioni(List<ElencoRiconciliazioni> elencoRiconciliazioni,
			DettaglioUtenteModel dettaglioUtenteModel) throws AuthenticationException{
		// Utenza
		DettaglioUtenteModel dettaglioUser = userService.profilaUtente(dettaglioUtenteModel, PROMUOVI_EVENTO);
		DettaglioUtente dettaglioUtente = dettaglioUtenteMapper.dtoToEntity(dettaglioUser);
		DettaglioUtente utenteSalvato = dettaglioUtenteRepository.save(dettaglioUtente);
		// Check 409
		Map<Long, String> eventiAssociati = new HashMap<Long, String>();
		elencoRiconciliazioni.forEach(riconciliazione -> {
			Map<Long, String> mappaEventi = riconciliazione.getEventi();
			mappaEventi.forEach((key, value) -> {
				Evento evento = eventoRepository.getOne(key);
				if (evento.getDaRiconciliare() == null || !evento.getDaRiconciliare()) {
					// Esiste un'attivita' associata
					eventiAssociati.put(key, value);
				}
			});
		});
		if (eventiAssociati.isEmpty()) {
			elencoRiconciliazioni.forEach(riconciliazione -> {
				// Creo una nuova attivita' e la associo a tutti gli eventi nella mappa
				Attivita attivita = new Attivita();
				attivita.setAttivitaId(riconciliazione.getIdAttivita());
				attivita.setDenominazione(riconciliazione.getDenominazioneAttivita());
				Attivita attivitaSalvata = attivitaRepository.save(attivita);			
				eventoRepository.updateOwner(riconciliazione.getEventi().keySet(), utenteSalvato, attivitaSalvata);	
			});
		}
		return eventiAssociati;
	}
}