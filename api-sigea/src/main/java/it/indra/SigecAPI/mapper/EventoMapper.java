package it.indra.SigecAPI.mapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jfree.util.Log;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;

import it.indra.SigeaCommons.model.EmailModel;
import it.indra.SigeaCommons.model.EventoModel;
import it.indra.SigeaCommons.model.LocationModel;
import it.indra.SigeaCommons.model.PeriodoModel;
import it.indra.SigeaCommons.model.SitoModel;
import it.indra.SigeaCommons.model.TelefonoModel;
import it.indra.SigecAPI.entity.Contatto;
import it.indra.SigecAPI.entity.Evento;
import it.indra.SigecAPI.entity.Periodo;
import it.indra.SigecAPI.repository.EventoRepository;
import lombok.extern.slf4j.Slf4j;


@Mapper(uses = { AccessibilitaMapper.class, AttivitaMapper.class, BandoMapper.class, DatiGeneraliMapper.class, DocumentoEventoMapper.class, ImmagineMapper.class, LinkMapper.class, LocationMapper.class, LogEventoMapper.class, PeriodoMapper.class, ProgettoMapper.class, RelazioneMapper.class, RichiestaAttivitaMapper.class, TicketMapper.class })
public interface EventoMapper {

	EventoMapper INSTANCE = Mappers.getMapper(EventoMapper.class);
	
	@Mappings({
		@Mapping(source = "entity.emailRiconciliazione", target = "emailOldOwner"),
		@Mapping(source = "entity.stato.statoId", target = "statoId"),
		@Mapping(source = "entity.stato.descrizione", target = "descrizioneStato"),
		@Mapping(source = "entity.owner.utenteId", target = "ownerId"),
		@Mapping(source = "entity.owner.nome", target = "nomeOwner"),
		@Mapping(source = "entity.owner.cognome", target = "cognomeOwner"),
		@Mapping(source = "entity.owner.username", target = "usernameOwner"),
		@Mapping(source = "entity.owner.email", target = "emailOwner"),
		@Mapping(source = "entity.owner.tel", target = "telOwner"),
		@Mapping(source = "entity.owner.cel", target = "celOwner"),
		@Mapping(source = "entity.ownerUltimaModifica.utenteId", target = "ownerIdUltimaModifica"),
		@Mapping(source = "entity.ownerUltimaModifica.nome", target = "nomeOwnerUltimaModifica"),
		@Mapping(source = "entity.ownerUltimaModifica.cognome", target = "cognomeOwnerUltimaModifica"),
		@Mapping(source = "entity.ownerUltimaModifica.username", target = "usernameOwnerUltimaModifica"),
		@Mapping(source = "entity.ownerUltimaModifica.email", target = "emailOwnerUltimaModifica"),
		@Mapping(source = "entity.ownerUltimaModifica.tel", target = "telOwnerUltimaModifica"),
		@Mapping(source = "entity.ownerUltimaModifica.cel", target = "celOwnerUltimaModifica")
	})
	EventoModel entityToDto(Evento entity, @Context EventoRepository eventoRepository);
	
	Evento dtoToEntity(EventoModel dto);
	
	Set<EventoModel> entityToDtoSet(Set<Evento> entitySet);
	Set<Evento> dtoToEntitySet(Set<EventoModel> dtoSet);
	
	@BeforeMapping
	default void handleJPARelationshipPre(@MappingTarget Evento evento, EventoModel model) {
		if(model.getLocationSet() != null) {
			Set<LocationModel> locationSetToRemove = new HashSet<LocationModel>();
			for(LocationModel location : model.getLocationSet()) {
				if ((location.getNazione() != null && location.getNazione() != "")
						|| (location.getRegione() != null && location.getRegione() != "")
						|| (location.getProvincia() != null && location.getProvincia() != "")
						|| (location.getComune() != null && location.getComune() != "")
						|| (location.getIndirizzo() != null && location.getIndirizzo() != "")) {
					continue;
				}
				locationSetToRemove.add(location);
			}
			if (!locationSetToRemove.isEmpty()) {
				model.getLocationSet().removeAll(locationSetToRemove);
			}
		}
		
		
		
		if(model.getPeriodoSet() != null) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			DateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
			
			Set<PeriodoModel> periodoSetToRemove = new HashSet<PeriodoModel>();
			for(PeriodoModel periodo : model.getPeriodoSet()) {
				if (periodo.getDataDa() == null) {
					periodoSetToRemove.add(periodo);
				}
				
				
				else { // if(periodo.getDataSecca().booleanValue()){

				
				String dataDa = formatterDate.format(periodo.getDataDa());
				String dataAPlusOne   = LocalDate.parse(formatterDate.format(periodo.getDataA())).plusDays(1).toString();
				String dataA =  formatterDate.format(periodo.getDataA());
				
				if(periodo.getOrarioApertura() !=null && periodo.getOrarioChiusura() != null) {
				
					String apertura ="";
					String chiusura = "";
					
				
					
				
						LocalTime orarioApertura= LocalTime.parse(periodo.getOrarioApertura(),formatter);
						if(periodo.getOrarioChiusura() != null) 
						{
							LocalTime orarioChiusura= LocalTime.parse(periodo.getOrarioChiusura(),formatter);
							
							
							if(!orarioApertura.isBefore(orarioChiusura)  && orarioChiusura.isAfter(LocalTime.MIDNIGHT))
							{
								apertura =dataDa  + " " + periodo.getOrarioApertura();
								chiusura = dataAPlusOne  + " " + periodo.getOrarioChiusura();
							}
							else 
							{
								apertura =dataDa  + " " + periodo.getOrarioApertura();
								chiusura = dataA+ " " + periodo.getOrarioChiusura();
							}
						}
						
						periodo.setOrarioApertura(apertura);
						periodo.setOrarioChiusura(chiusura);
						
					}
				else if(periodo.getOrarioApertura() != null ) {

					periodo.setOrarioApertura(dataDa  + " " + periodo.getOrarioApertura());
					
				
				} else 
				if(periodo.getOrarioChiusura()!= null)
				{
					periodo.setOrarioChiusura(dataA+ " " + periodo.getOrarioChiusura());
				}
				
				if(periodo.getOrarioAperturaPomeriggio() != null && periodo.getOrarioChiusuraPomeriggio()!= null) 
				{
					
					String apertura ="";
					String chiusura = "";
					
							
					
					
				
						LocalTime orarioApertura= LocalTime.parse(periodo.getOrarioAperturaPomeriggio(),formatter);
						if(periodo.getOrarioChiusuraPomeriggio() != null) 
						{
							LocalTime orarioChiusura= LocalTime.parse(periodo.getOrarioChiusuraPomeriggio(),formatter);
							if(!orarioApertura.isBefore(orarioChiusura)  && orarioChiusura.isAfter(LocalTime.MIDNIGHT))
							{
								apertura =dataDa  + " " + periodo.getOrarioAperturaPomeriggio();
								chiusura = dataAPlusOne  + " " + periodo.getOrarioChiusuraPomeriggio();
							}
							else 
							{
								apertura =dataDa  + " " + periodo.getOrarioAperturaPomeriggio();
								chiusura = dataA+ " " + periodo.getOrarioChiusuraPomeriggio();
							}
						}
						
						periodo.setOrarioAperturaPomeriggio(apertura);
						periodo.setOrarioChiusuraPomeriggio(chiusura);
					
				}
				else if(periodo.getOrarioAperturaPomeriggio() != null ) {

					periodo.setOrarioAperturaPomeriggio(dataDa  + " " + periodo.getOrarioAperturaPomeriggio());
					
				
				}
				else if(periodo.getOrarioChiusuraPomeriggio()!= null)
				{
					periodo.setOrarioChiusuraPomeriggio(dataA+ " " + periodo.getOrarioChiusuraPomeriggio());
				}
				
				
				if(periodo.getOrarioAperturaMattina() != null ) {

					periodo.setOrarioAperturaMattina(dataDa  + " " + periodo.getOrarioAperturaMattina());
					
				
				}
				if(periodo.getOrarioChiusuraMattina()!= null)
				{
					periodo.setOrarioChiusuraMattina(dataA+ " " + periodo.getOrarioChiusuraMattina());
				}
				
			}
				
			}
			if (!periodoSetToRemove.isEmpty()) {
				model.getPeriodoSet().removeAll(periodoSetToRemove);
			}
			
			
			
			
			
		}
	}
	
	@AfterMapping
	default void handleJPARelationship(@MappingTarget Evento evento, EventoModel model) {
		
		Set<Contatto> contattoSet = new HashSet<Contatto>();
		if (model.getTelefonoSet()!=null) {
			for(TelefonoModel telefono : model.getTelefonoSet()) {
				if(telefono.getContatto() != null && telefono.getContatto() != "") {
					Contatto contatto = new Contatto();
					contatto.setContattoId(telefono.getContattoId());
					contatto.setContatto(telefono.getContatto());
					contatto.setTipo(TelefonoModel.tipo);
					contattoSet.add(contatto);
				}
			}
		}
		if (model.getEmailSet()!=null) {
			for(EmailModel email : model.getEmailSet()) {
				if(email.getContatto() != null && email.getContatto() != "") {
					Contatto contatto = new Contatto();
					contatto.setContattoId(email.getContattoId());
					contatto.setContatto(email.getContatto());
					contatto.setTipo(EmailModel.tipo);
					contattoSet.add(contatto);
				}
			}
		}
		if (model.getSitoSet()!=null) {
			for(SitoModel sito : model.getSitoSet()) {
				if(sito.getContatto() != null && sito.getContatto() != "") {
					Contatto contatto = new Contatto();
					contatto.setContattoId(sito.getContattoId());
					contatto.setContatto(sito.getContatto());
					contatto.setTipo(SitoModel.tipo);
					contattoSet.add(contatto);
				}
			}
		}
		evento.setContattoSet(contattoSet);
		
		evento.getLocationSet().forEach(location -> location.getAttrattoriSet().stream().forEach(attrattore -> attrattore.getLocationSet().add(location)));
		evento.getTicket().setEvento(evento);
		evento.getDatiGenerali().setEvento(evento);
		evento.getAccessibilita().setEvento(evento);
		evento.getLocationSet().stream().forEach(location -> location.setEvento(evento));
		evento.getContattoSet().stream().forEach(contatto -> contatto.setEvento(evento));
		evento.getRelazioneSet().stream().forEach(relazione -> relazione.setEvento(evento));
		evento.getPeriodoSet().stream().forEach(periodo -> {periodo.setEvento(evento);});
		evento.getImmagineSet().stream().forEach(immagine -> immagine.setEvento(evento));
		evento.getDocumentoSet().stream().forEach(documento -> documento.setEvento(evento));
		evento.getLinkSet().stream().forEach(link -> link.setEvento(evento));
		if (evento.getProgetto()!=null) {
				evento.getProgetto().setBando(evento.getBando());
		}
		
		List<java.util.Date> dataDaList = new ArrayList<java.util.Date>(); 
		List<Date> dataAList = new ArrayList<Date>();
		for(Periodo periodo : evento.getPeriodoSet()) {
			if (periodo.getDataDa()!=null) {
				dataDaList.add(new java.util.Date(periodo.getDataDa().getTime()));
			}
			if (periodo.getDataA()!=null) {
				dataAList.add(new java.util.Date(periodo.getDataA().getTime()));
			}
		}
		dataDaList.sort(Comparator.naturalOrder());
		dataAList.sort(Comparator.naturalOrder());
		java.sql.Date dataDaMin = null;
		if (dataDaList != null && dataDaList.size() > 0 && dataDaList.get(0)!=null) {
			dataDaMin = new java.sql.Date(dataDaList.get(0).getTime());
		}
		java.sql.Date dataAMax = null;
		if (dataAList != null && dataAList.size() > 0 && dataAList.get(dataAList.size() - 1) !=null) {
			dataAMax = new java.sql.Date(dataAList.get(dataAList.size() - 1).getTime());
		}
		evento.setDataDaMin(dataDaMin);
		evento.setDataAMax(dataAMax);
		
		if(evento.getBando()!=null) {
			evento.setFgFinanziato(true);
		}else {
			evento.setFgFinanziato(false);
		}
	}
	
	@AfterMapping
	default void setTitoliEventiRelazionati (@MappingTarget EventoModel dto, Evento evento, @Context EventoRepository eventoRepository ) {
		//dto.getRelazioneSet().forEach(relazione -> relazione.setTitolo(eventoRepository.findByEventoId(relazione.getEventoRelazionatoId()).getTitolo()));
		Set<TelefonoModel> telefonoSet = new HashSet<TelefonoModel>();
		Set<EmailModel> emailSet = new HashSet<EmailModel>();
		Set<SitoModel> sitoSet = new HashSet<SitoModel>();
		for (Contatto contatto : evento.getContattoSet()) {
			if(contatto.getTipo().equals(TelefonoModel.tipo)) {
				TelefonoModel telefonomodel = new TelefonoModel();
				telefonomodel.setContattoId(contatto.getContattoId());
				telefonomodel.setContatto(contatto.getContatto());
				telefonoSet.add(telefonomodel);
			}else if (contatto.getTipo().equals(EmailModel.tipo)) {
				EmailModel emailModel = new EmailModel();
				emailModel.setContattoId(contatto.getContattoId());
				emailModel.setContatto(contatto.getContatto());
				emailSet.add(emailModel);
			}else if(contatto.getTipo().equals(SitoModel.tipo)) {
				SitoModel sitoModel = new SitoModel();
				sitoModel.setContattoId(contatto.getContattoId());
				sitoModel.setContatto(contatto.getContatto());
				sitoSet.add(sitoModel);
			}
		}
		if(!telefonoSet.isEmpty()) {
			dto.setTelefonoSet(telefonoSet);
		}
		if(!emailSet.isEmpty()) {
			dto.setEmailSet(emailSet);
		}
		if(!sitoSet.isEmpty()) {
			dto.setSitoSet(sitoSet);
		}
		
		Set<PeriodoModel> periodoSet = new HashSet<PeriodoModel>();
		for(Periodo periodo : evento.getPeriodoSet())
		{
			PeriodoModel periodoModel = new PeriodoModel();
			
			BeanUtils.copyProperties(periodo, periodoModel);
			
				if(periodo.getOrarioApertura() != null && !"".equals(periodo.getOrarioApertura()) && periodo.getOrarioApertura().length()>5)
				{
					try 
					{
					
					
					periodoModel.setOrarioApertura(periodo.getOrarioApertura().substring(11));
					}catch (Exception e) {
						
					}
				}
				
				if(periodo.getOrarioChiusura() != null && !"".equals(periodo.getOrarioChiusura()) && periodo.getOrarioChiusura().length()>5)
				{
					try 
					{
					
					
					periodoModel.setOrarioChiusura(periodo.getOrarioChiusura().substring(11));
					}catch (Exception e) {
						
					}
				}
				
				if(periodo.getOrarioAperturaMattina() != null && !"".equals(periodo.getOrarioAperturaMattina()) && periodo.getOrarioAperturaMattina().length()>5)
				{
					try 
					{
					
					
					periodoModel.setOrarioAperturaMattina(periodo.getOrarioAperturaMattina().substring(11));
					}catch (Exception e) {
						
					}
				}
				if(periodo.getOrarioChiusuraMattina() != null && !"".equals(periodo.getOrarioChiusuraMattina()) && periodo.getOrarioChiusuraMattina().length()>5)
				{
					try 
					{
					
					
					periodoModel.setOrarioChiusuraMattina(periodo.getOrarioChiusuraMattina().substring(11));
					}catch (Exception e) {
						
					}
				}
				
				if(periodo.getOrarioAperturaPomeriggio() != null && !"".equals(periodo.getOrarioAperturaPomeriggio()) && periodo.getOrarioAperturaPomeriggio().length()>5)
				{
					try 
					{
					
					
					periodoModel.setOrarioAperturaPomeriggio(periodo.getOrarioAperturaPomeriggio().substring(11));
					}catch (Exception e) {
						
					}
				}
				
				if(periodo.getOrarioChiusuraPomeriggio() != null && !"".equals(periodo.getOrarioChiusuraPomeriggio()) && periodo.getOrarioChiusuraPomeriggio().length()>5)
				{
					try 
					{
					
					
					periodoModel.setOrarioChiusuraPomeriggio(periodo.getOrarioChiusuraPomeriggio().substring(11));
					}catch (Exception e) {
						
					}
				}
				
				periodoSet.add(periodoModel);
		}
		
		dto.setPeriodoSet(periodoSet);
	}
}
