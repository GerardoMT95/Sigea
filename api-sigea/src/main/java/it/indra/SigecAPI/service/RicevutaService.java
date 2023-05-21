package it.indra.SigecAPI.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.indra.es.utils.LogUtility;

import it.indra.SigeaCommons.model.DocumentoEventoModel;
import it.indra.SigeaCommons.model.EmailModel;
import it.indra.SigeaCommons.model.EventoModel;
import it.indra.SigeaCommons.model.ImmagineModel;
import it.indra.SigeaCommons.model.LinkModel;
import it.indra.SigeaCommons.model.LogEventoModel;
import it.indra.SigeaCommons.model.MediaDTO;
import it.indra.SigeaCommons.model.SitoModel;
import it.indra.SigeaCommons.model.TelefonoModel;
import it.indra.SigecAPI.clientstub.DMSClientStub;
import it.indra.SigecAPI.entity.Evento;
import it.indra.SigecAPI.entity.LogPubblicazioni;
import it.indra.SigecAPI.entity.Pubblicazione;
import it.indra.SigecAPI.repository.EventoRepository;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Slf4j
@Service
public class RicevutaService 
{

	@Autowired
	EventoService eventoService;


	@Value("${filesystem.repository.root}")
	private String repositoryRoot;

	@Value("${filesystem.repository.image}")
	private String imageFolderName;



	@Autowired
	CacheTokenService cacheTokenService;

	@Autowired
	DMSClientStub dmsService;


	@Autowired
	FileRicevutaService fileService;


	@Autowired
	EventoRepository eventoRepo;


	public byte[] export(Long idEvento , String token ) 
	{
		return export(idEvento, token, 0L);
	}


	public byte[] export(Long idEvento , String token , Long userId)
	{
		try {
			EventoModel evento;

			evento= eventoService.getEvento(idEvento, userId);

			if(evento==null) {
				return null;
			}

			if(evento.getBando()==null) 
			{
				return null;
			}


			Evento eventoDb = eventoRepo.findById(idEvento).get();

			Pubblicazione pubblicazioneVip =null;

			try {
				pubblicazioneVip =eventoDb.getPubblicazioneSet().stream().filter(x-> x.getRedazione().getRedazioneId().equalsIgnoreCase("VIP")).findFirst().orElse(null);
			}catch (Exception e) {
				log.error(LogUtility.exceptionToLog(e));
			}


			List<String> datePubblicazioni = new ArrayList<>();

			String ultimaPubblicazione = "";

			if(pubblicazioneVip!=null)
			{
				Set<LogPubblicazioni> logPubblicaziooni = pubblicazioneVip.getLogPubblicazioni();


				List<LogPubblicazioni> logPubblicazioniSort= new ArrayList<LogPubblicazioni>(logPubblicaziooni) ;


			
				logPubblicazioniSort.sort(Comparator.comparing(LogPubblicazioni::getDataModifica));

				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				for(LogPubblicazioni logP : logPubblicazioniSort)
				{
					if(logP.getStato().getStatoId().equals("PUBBLICATO") || logP.getStato().getStatoId().equals("RIPUBBLICATO") ) 
					{

						ultimaPubblicazione= logP.getDataModifica().toLocalDateTime().format(dateFormatter);

						datePubblicazioni.add(logP.getPubblicazione().getRedazione().getNome() + ", " +  logP.getDataModifica().toLocalDateTime().format(dateFormatter) + " alle ore " +  logP.getDataModifica().toLocalDateTime().format(dateTimeFormatter) );
					}
				}
			}





			String activityObj="";
			String cfTitolare="";
			String cfPiva="";
			String ragioneSociale = "";

			try {
			if(evento.getAttivita()!=null)
			{
				activityObj = dmsService.getActivityDTO(token, evento.getAttivita().getAttivitaId());
				
			
					
					JSONObject obj = new JSONObject(activityObj);
					cfPiva = obj.getString("cfPiva");
					cfTitolare = obj.getString("cfTitolare");
					ragioneSociale =obj.getString("ragioneSociale");
				

			}
			else if (evento.getRichiestaAttivita() != null) {
				
				activityObj = dmsService.getRegistrazioneImpresa(token,
						evento.getRichiestaAttivita().getRichiestaAttivitaId());
				
				
					
					JSONObject obj = new JSONObject(activityObj);
					JSONObject titObj = obj.getJSONObject("datititolare");
					JSONObject datiRegistrazione = obj.getJSONObject("datiAttivita");
					cfPiva = datiRegistrazione .getString("cfPiva");
					cfTitolare = titObj.getString("cfTitolare");
					ragioneSociale =datiRegistrazione .getString("ragioneSociale");

			}
			} catch (Exception e) {
				log.error("IMPOSSIBILE RECUPERARE DATI SU CF E/O PI, potrebbe trattarsi di un ente " + evento.getEventoId());
			}
		


			List<MediaDTO> listaReportMedia = new ArrayList<>();



			Set<LogEventoModel> logEventi= evento.getLogSet();

			List<LogEventoModel> logEventiSort= new ArrayList<LogEventoModel>() ;

			logEventiSort.addAll(evento.getLogSet());

			
			
	
			logEventiSort.sort(Comparator.comparing(LogEventoModel::getDataModifica));

			LogEventoModel primaValutazione = logEventiSort.stream().filter(x-> x.getDescrizioneStato().equalsIgnoreCase("In attesa di valutazione")).findFirst().orElse(null);


			LogEventoModel primaValidazione= logEventiSort.stream().filter(x-> x.getDescrizioneStato().equalsIgnoreCase("Validato")).findFirst().orElse(null);

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			String primaRichiestaValutazione = "";


			if(primaValutazione==null)
				primaValutazione=primaValidazione;


			if(primaValutazione!=null)
			{
				try {
					Date date = new Date(primaValutazione.getDataModifica().getTime());
					primaRichiestaValutazione = formatter.format(date);
				}catch (Exception e) {

				}
			}

			String ultimaValidazioneDate = "";
			if(primaValidazione!=null)
			{
				for(LogEventoModel logEv : logEventiSort)
				{
					try {
						if(logEv.getDescrizioneStato().equalsIgnoreCase("Validato")) {
							Date date = new Date(logEv.getDataModifica().getTime());
							ultimaValidazioneDate = formatter.format(date);
						}
					}catch (Exception e) {

					}
				}
			}


			String personeEsigenzeSpecifiche = getStringPersoneEsigenzeSpecifiche(evento);
			String serviziAccessibili= getServiziAccessibili (evento);
			String serviziFamiglie= getServiziFamiglia(evento);
			String animaliAmmessi =getAnimaliAmmessi(evento);



			for(ImmagineModel image :evento.getImmagineSet()) 
			{
				MediaDTO media = new MediaDTO();


				String path = repositoryRoot + File.separator + evento.getEventoId().toString() + File.separator
						+ imageFolderName +  File.separator + image.getImmagineId() + "." + image.getEstensione();
				String newPath = repositoryRoot + File.separator + evento.getEventoId().toString() + File.separator
						+ imageFolderName +  File.separator +"min-" + image.getImmagineId() + "." + image.getEstensione();

				String tmpPath = repositoryRoot + File.separator + evento.getEventoId().toString() + File.separator
						+ imageFolderName +  File.separator +"tmp-" +image.getImmagineId() + "." + image.getEstensione();
				fileService.reduceFile(path, newPath, tmpPath);
				media.setCredits(image.getCredits()!=null? image.getCredits():"");
				media.setDidascalia(image.getDidascaliaMulti().get("IT")!=null? image.getDidascaliaMulti().get("IT"):"");
				media.setDimensione((image.getDimensione()/1024) + " kb");
				media.setPath(newPath);
				media.setNome(image.getNomeOriginale());
				listaReportMedia.add(media);
			}
			String telefono="";

			for(TelefonoModel tel : evento.getTelefonoSet()) 
			{
				if(!telefono.equals(""))
				{
					telefono +=",";
				}

				telefono += tel.getContatto();
			}

			String email = "";

			for(EmailModel mail : evento.getEmailSet()) 
			{
				if(!email.equals(""))
				{
					email +=",";
				}

				email += mail.getContatto();
			}

			String sitoSocial ="";

			if(evento.getSitoSet()!=null)
			{
				for(SitoModel sito : evento.getSitoSet())
				{

					if(!sitoSocial.equals(""))
					{
						sitoSocial +=",";
					}

					sitoSocial += sito.getContatto();
				}
			}

			List<MediaDTO> altriMediaList = new ArrayList<>();

			for(DocumentoEventoModel doc : evento.getDocumentoSet())
			{
				MediaDTO docDto = new MediaDTO();
				docDto.setNome(doc.getNomeOriginale() + "," + (doc.getDimensione()/1024) + " kb");
				docDto.setDidascalia(doc.getTitoloMulti().get("IT")!=null ? doc.getTitoloMulti().get("IT"):"");
				altriMediaList.add(docDto);
			}

			List<MediaDTO> mediaLink = new ArrayList<>();

			for(LinkModel link : evento.getLinkSet())
			{
				MediaDTO linkDTO = new MediaDTO();
				linkDTO.setCredits(link.getCredits());
				linkDTO.setDidascalia(link.getDidascaliaMulti().get("IT"));
				linkDTO.setPath(link.getLink());
				linkDTO.setNome(link.getTitoloMulti().get("IT"));
				mediaLink.add(linkDTO);
			}

			
			LocalDateTime today =LocalDateTime.now();
			
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			String dataCreazione = "Generato in data " + today.format(dateFormatter) +" alle ore " + today.format(dateTimeFormatter);											

			
			JRBeanCollectionDataSource tableDataSourceMedia = new JRBeanCollectionDataSource(listaReportMedia);
			JRBeanCollectionDataSource tableDataSourceAltriMedia = new JRBeanCollectionDataSource(altriMediaList);
			JRBeanCollectionDataSource tableDataSourceLinkMedia = new JRBeanCollectionDataSource(mediaLink);
			JRBeanCollectionDataSource tableDataSourceLuoghi = new JRBeanCollectionDataSource(evento.getLocationSet());
			JRBeanCollectionDataSource tableDataSourcePeriodi= new JRBeanCollectionDataSource(evento.getPeriodoSet());
			JRBeanCollectionDataSource tableDataSourcePubblicazioi= new JRBeanCollectionDataSource(datePubblicazioni);
			Map<String, Object> parameters = new HashMap<String, Object> ();

			parameters.put("pubblicazioniList", tableDataSourcePubblicazioi);
			parameters.put("mediaList", tableDataSourceMedia);
			parameters.put("mediaLink", tableDataSourceLinkMedia);
			parameters.put("mediaAltriList", tableDataSourceAltriMedia);
			parameters.put("luoghiList", tableDataSourceLuoghi);
			parameters.put("periodiList", tableDataSourcePeriodi);
			parameters.put("evento" , evento);
			parameters.put("telefono", telefono);
			parameters.put("email", email);
			parameters.put("sito", sitoSocial);
			parameters.put("personeEsigenzeSpecifiche", personeEsigenzeSpecifiche);
			parameters.put("serviziAccessibili", serviziAccessibili);
			parameters.put("serviziFamiglie", serviziFamiglie);
			parameters.put("animaliAmmessi", animaliAmmessi);
			parameters.put("cfPiva", cfPiva);
			parameters.put("cfTitolare", cfTitolare);
			parameters.put("ragioneSociale", ragioneSociale);
			parameters.put("primaValidazione", ultimaValidazioneDate);
			parameters.put("primaValutazione", primaRichiestaValutazione);
			parameters.put("ultimaPubblicazione",ultimaPubblicazione);
			parameters.put("dataCreazione",dataCreazione);

			byte[] dataBadge =exportReportsToPdf(parameters, "ricevuta.jrxml");

			return dataBadge;

		} catch (Exception e) {
			log.error(LogUtility.exceptionToLog(e));
		};
		return null;
	}


	public byte[] exportReportsToPdf(Map<String, Object> parameters,String reportname) {
		ByteArrayInputStream bais = null;
		ByteArrayOutputStream bjasperBaos=null;
		OutputStreamExporterOutput out = null;

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] dataBadge = null;

		try {

			bjasperBaos = new ByteArrayOutputStream();
			ClassPathResource res = new ClassPathResource("report/"+reportname);
			JasperReport jasperReport = JasperCompileManager.compileReport(res.getInputStream());
			JRSaver.saveObject(jasperReport, bjasperBaos);
			bais = new ByteArrayInputStream(bjasperBaos.toByteArray());			

			JasperPrint print = JasperFillManager.fillReport(bais, parameters,new JREmptyDataSource());
			JRPdfExporter exporter = new JRPdfExporter();
			List<JasperPrint> list = new ArrayList<>();
			list.add(print);
			exporter.setExporterInput(SimpleExporterInput.getInstance(list));
			out = new SimpleOutputStreamExporterOutput(output);
			exporter.setExporterOutput(out);
			exporter.exportReport();
			JasperExportManager.exportReportToPdfStream(print, output);
			dataBadge = output.toByteArray();

			if(bjasperBaos != null)
				bjasperBaos.close();
			if(bais != null)
				bais.close();
			if(output!=null)
				output.close();
			if (out != null)
				out.close();


			return dataBadge;
		} 

		catch (Exception e) {
			log.error(LogUtility.exceptionToLog(e));
			return dataBadge;
		} 
	}


	public byte[] createRicevuta(EventoModel evento , String stato) {

		byte[] data = exportFirst(evento, cacheTokenService.getToken(), 0L, stato);

		String path = repositoryRoot + File.separator + evento.getEventoId().toString() + File.separator
				+ "ricevuta";
		fileService.storePdf(data , path , evento.getEventoId());

		return data;
	}


	public byte[] getRicevuta(Long idEvento) {
		try {
			String path = repositoryRoot + File.separator + idEvento.toString() + File.separator
					+ "ricevuta"+  File.separator + "ricevuta.pdf";


			File file = new File(path);

			byte[] data = Files.readAllBytes(file.toPath());

			return data;
		}
		catch (Exception e) {
			log.error(LogUtility.exceptionToLog(e));
			return null;
		}
	}


	public boolean checkFile (Long idEvento)
	{
		String path = repositoryRoot + File.separator +idEvento.toString() + File.separator
				+ "ricevuta";
		return fileService.checkFile(idEvento, path);
	}

	public byte[] exportFirst(EventoModel evento, String token , Long userId , String stato)
	{
		try {

			if(evento==null) {
				return null;
			}

			if(evento.getBando()==null) 
			{
				return null;
			}

			EventoModel  eventoDaService = eventoService.getEvento(evento.getEventoId(), userId);
			log.info("Evento da service " + eventoDaService.getNomeOwner());

			List<String> datePubblicazioni = new ArrayList<>();
			evento.setEmailOwner(eventoDaService.getEmailOwner());
			evento.setNomeOwner(eventoDaService.getNomeOwner());
			evento.setCognomeOwner(eventoDaService.getCognomeOwner());
			evento.setAttivita(eventoDaService.getAttivita());
			evento.setRichiestaAttivita(eventoDaService.getRichiestaAttivita());

			String activityObj="";
			String cfTitolare="";
			String cfPiva="";
			String ragioneSociale = "";
			try {
				
			if(evento.getAttivita()!=null)
			{
				activityObj = dmsService.getActivityDTO(token, evento.getAttivita().getAttivitaId());
				
				
					JSONObject obj = new JSONObject(activityObj);
					cfPiva = obj.getString("cfPiva");
					cfTitolare = obj.getString("cfTitolare");
					ragioneSociale =obj.getString("ragioneSociale");
			

			}
			else if (evento.getRichiestaAttivita() != null) {
				
				activityObj = dmsService.getRegistrazioneImpresa(token,
						evento.getRichiestaAttivita().getRichiestaAttivitaId());
				
				
			
					
					JSONObject obj = new JSONObject(activityObj);
					JSONObject titObj = obj.getJSONObject("datititolare");
					JSONObject datiRegistrazione = obj.getJSONObject("datiAttivita");
					cfPiva = datiRegistrazione .getString("cfPiva");
					cfTitolare = titObj.getString("cfTitolare");
					ragioneSociale =datiRegistrazione .getString("ragioneSociale");

			}
			} catch (Exception e) {
				log.error("IMPOSSIBILE RECUPERARE DATI SU CF E/O PI, potrebbe trattarsi di un ente " + evento.getEventoId());
			}			
		



			List<MediaDTO> listaReportMedia = new ArrayList<>();




			LocalDate today= LocalDate.now();


			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			String primaRichiestaValutazione = today.format(dateFormatter);



			String primaValidazioneDate = "";

			if(stato!= null && (stato.equalsIgnoreCase("VALIDATO") ) )
			{
				primaValidazioneDate=primaRichiestaValutazione;
			}
			
			
			if(stato!= null && (stato.equalsIgnoreCase("VALIDATO") ) )
			{
				evento.setDescrizioneStato("Validato");
			}
			else 
			{
				evento.setDescrizioneStato("In attesa di valutazione");
			}



			String personeEsigenzeSpecifiche = getStringPersoneEsigenzeSpecifiche(evento);
			String serviziAccessibili= getServiziAccessibili (evento);
			String serviziFamiglie= getServiziFamiglia(evento);
			String animaliAmmessi =getAnimaliAmmessi(evento);

			
			Set<ImmagineModel> setImmagini= new HashSet<>();
			Set<ImmagineModel> setAltriMedia= new HashSet<>();
			for(ImmagineModel image :evento.getImmagineSet()) 
			{

				String estensione = image.getEstensione();
				if(	estensione.equalsIgnoreCase("jpg") || estensione.equalsIgnoreCase("jpeg")
						//				|| estensione.equalsIgnoreCase("tiff") || estensione.equalsIgnoreCase("tif")
						|| estensione.equalsIgnoreCase("png") || estensione.equalsIgnoreCase("gif")
						) {
					setImmagini.add(image);
				}else
				{
					setAltriMedia.add(image);
				}
			}
			for(ImmagineModel image :setImmagini) 
			{
				MediaDTO media = new MediaDTO();

				String path = repositoryRoot + File.separator + evento.getEventoId().toString() + File.separator
						+ imageFolderName +  File.separator + image.getImmagineId() + "." + image.getEstensione();
				String newPath = repositoryRoot + File.separator + evento.getEventoId().toString() + File.separator
						+ imageFolderName +  File.separator +"min-" + image.getImmagineId() + "." + image.getEstensione();

				String tmpPath = repositoryRoot + File.separator + evento.getEventoId().toString() + File.separator
						+ imageFolderName +  File.separator +"tmp-" +image.getImmagineId() + "." + image.getEstensione();
				fileService.reduceFile(path, newPath, tmpPath);
				media.setCredits(image.getCredits()!=null? image.getCredits():"");
				media.setDidascalia(image.getDidascaliaMulti().get("IT")!=null? image.getDidascaliaMulti().get("IT"):"");
				media.setDimensione((image.getDimensione()/1024) + " kb");
				media.setPath(newPath);
				media.setNome(image.getNomeOriginale());
				listaReportMedia.add(media);

			}
			String telefono="";

			for(TelefonoModel tel : evento.getTelefonoSet()) 
			{
				if(!telefono.equals(""))
				{
					telefono +=",";
				}

				telefono += tel.getContatto();
			}

			String email = "";

			for(EmailModel mail : evento.getEmailSet()) 
			{
				if(!email.equals(""))
				{
					email +=",";
				}

				email += mail.getContatto();
			}

			String sitoSocial ="";

			if(evento.getSitoSet()!=null)
			{
				for(SitoModel sito : evento.getSitoSet())
				{

					if(!sitoSocial.equals(""))
					{
						sitoSocial +=",";
					}

					sitoSocial += sito.getContatto();
				}
			}

			List<MediaDTO> altriMediaList = new ArrayList<>();

			for(DocumentoEventoModel doc : evento.getDocumentoSet())
			{
				MediaDTO docDto = new MediaDTO();
				docDto.setNome(doc.getNomeOriginale() + "," + (doc.getDimensione()/1024) + " kb");
				docDto.setDidascalia(doc.getTitoloMulti().get("IT")!=null ? doc.getTitoloMulti().get("IT"):"");
				altriMediaList.add(docDto);
			}

			List<MediaDTO> mediaLink = new ArrayList<>();

			for(LinkModel link : evento.getLinkSet())
			{
				MediaDTO linkDTO = new MediaDTO();
				linkDTO.setCredits(link.getCredits());
				linkDTO.setDidascalia(link.getDidascaliaMulti().get("IT"));
				linkDTO.setPath(link.getLink());
				linkDTO.setNome(link.getTitoloMulti().get("IT"));
				mediaLink.add(linkDTO);
			}

			
			
		
			
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

		
			
			LocalDateTime creationDate = LocalDateTime.now();
			
			
			String dataCreazione = "Generato in data " + creationDate.format(dateFormatter) +" alle ore " + creationDate.format(dateTimeFormatter);											

			
			
			
			JRBeanCollectionDataSource tableDataSourceMedia = new JRBeanCollectionDataSource(listaReportMedia);
			JRBeanCollectionDataSource tableDataSourceAltriMedia = new JRBeanCollectionDataSource(altriMediaList);
			JRBeanCollectionDataSource tableDataSourceLinkMedia = new JRBeanCollectionDataSource(mediaLink);
			JRBeanCollectionDataSource tableDataSourceLuoghi = new JRBeanCollectionDataSource(evento.getLocationSet());
			JRBeanCollectionDataSource tableDataSourcePeriodi= new JRBeanCollectionDataSource(evento.getPeriodoSet());
			JRBeanCollectionDataSource tableDataSourcePubblicazioi= new JRBeanCollectionDataSource(datePubblicazioni);
			Map<String, Object> parameters = new HashMap<String, Object> ();

			parameters.put("pubblicazioniList", tableDataSourcePubblicazioi);
			parameters.put("mediaList", tableDataSourceMedia);
			parameters.put("mediaLink", tableDataSourceLinkMedia);
			parameters.put("mediaAltriList", tableDataSourceAltriMedia);
			parameters.put("luoghiList", tableDataSourceLuoghi);
			parameters.put("periodiList", tableDataSourcePeriodi);
			parameters.put("evento" , evento);
			parameters.put("telefono", telefono);
			parameters.put("email", email);
			parameters.put("sito", sitoSocial);
			parameters.put("personeEsigenzeSpecifiche", personeEsigenzeSpecifiche);
			parameters.put("serviziAccessibili", serviziAccessibili);
			parameters.put("serviziFamiglie", serviziFamiglie);
			parameters.put("animaliAmmessi", animaliAmmessi);
			parameters.put("cfPiva", cfPiva);
			parameters.put("cfTitolare", cfTitolare);
			parameters.put("ragioneSociale", ragioneSociale);
			parameters.put("primaValidazione", primaValidazioneDate);
			parameters.put("primaValutazione", primaRichiestaValutazione);
			parameters.put("ultimaPubblicazione", datePubblicazioni.size()>0? datePubblicazioni.get(datePubblicazioni.size()-1):"");
			parameters.put("dataCreazione",dataCreazione);

			byte[] dataBadge =exportReportsToPdf(parameters, "ricevuta.jrxml");
			

			return dataBadge;

		} catch (Exception e) {
			log.error(LogUtility.exceptionToLog(e));
		};
		return null;
	}


	private String getAnimaliAmmessi(EventoModel evento) {
		String animaliAmmessi = "";



		if(evento.getAccessibilita().getFlagCani()) 
		{
			if(animaliAmmessi.length()>0) 
			{
				animaliAmmessi +=", ";
			}
			animaliAmmessi +="tutti i cani";
		}

		if(evento.getAccessibilita().getFlagCaniPiccoli()) 
		{
			if(animaliAmmessi.length()>0) 
			{
				animaliAmmessi +=", ";
			}
			animaliAmmessi +="cani taglia piccola";
		}

		if(evento.getAccessibilita().getFlagCaniMedi()) 
		{
			if(animaliAmmessi.length()>0) 
			{
				animaliAmmessi +=", ";
			}
			animaliAmmessi +="cani taglia media";
		}
		return animaliAmmessi;
	}


	private String getServiziFamiglia(EventoModel evento) {
		String serviziFamiglie= "";


		if(evento.getAccessibilita().getFlagBiblioteca()) 
		{
			if(serviziFamiglie.length()>0) 
			{
				serviziFamiglie +=", ";
			}
			serviziFamiglie +="biblioteca per bambini";
		}
		if(evento.getAccessibilita().getFlagLudoteca()) 
		{
			if(serviziFamiglie.length()>0) 
			{
				serviziFamiglie +=", ";
			}
			serviziFamiglie +="ludoteca";
		}
		if(evento.getAccessibilita().getFlagParcogiochi()) 
		{
			if(serviziFamiglie.length()>0) 
			{
				serviziFamiglie +=", ";
			}
			serviziFamiglie +="parco giochi";
		}
		if(evento.getAccessibilita().getFlagGiardini()) 
		{
			if(serviziFamiglie.length()>0) 
			{
				serviziFamiglie +=", ";
			}
			serviziFamiglie +="spazi esterni attrezzati(giardini)";
		}
		return serviziFamiglie;
	}


	private String getServiziAccessibili(EventoModel evento) {

		String serviziAccessibili= "";

		if(evento.getAccessibilita().getFlagInfopoint()) 
		{
			if(serviziAccessibili.length()>0) 
			{
				serviziAccessibili +=", ";
			}
			serviziAccessibili +="punto informazioni/accoglienza accessibile";
		}
		if(evento.getAccessibilita().getFlagServiziIgienici()) 
		{
			if(serviziAccessibili.length()>0) 
			{
				serviziAccessibili +=", ";
			}
			serviziAccessibili +="servizi igienici accessibili";
		}
		if(evento.getAccessibilita().getFlagParcheggioDisabili()) 
		{
			if(serviziAccessibili.length()>0) 
			{
				serviziAccessibili +=", ";
			}
			serviziAccessibili +="parcheggio riservato alle persone con disabilità";
		}
		if(evento.getAccessibilita().getFlagIngressi()) 
		{
			if(serviziAccessibili.length()>0) 
			{
				serviziAccessibili +=", ";
			}
			serviziAccessibili +="ingresso principale o secodario accessibile";
		}
		if(evento.getAccessibilita().getFlagPercorsiTattili()) 
		{
			if(serviziAccessibili.length()>0) 
			{
				serviziAccessibili +=", ";
			}
			serviziAccessibili +="percorsi tattili";
		}
		if(evento.getAccessibilita().getFlagGuideBraille()) 
		{
			if(serviziAccessibili.length()>0) 
			{
				serviziAccessibili +=", ";
			}
			serviziAccessibili +="guide cartacee in braille";
		}
		if(evento.getAccessibilita().getFlagSegnaleticaBraille()) 
		{
			if(serviziAccessibili.length()>0) 
			{
				serviziAccessibili +=", ";
			}
			serviziAccessibili +="segnaletica in braille";
		}
		if(evento.getAccessibilita().getFlagSegnaleticaLeggibile()) 
		{
			if(serviziAccessibili.length()>0) 
			{
				serviziAccessibili +=", ";
			}
			serviziAccessibili +="segnaletica ad alto contrasto/alta leggibilità";
		}
		if(evento.getAccessibilita().getFlagMaterialeLeggibile()) 
		{
			if(serviziAccessibili.length()>0) 
			{
				serviziAccessibili +=", ";
			}
			serviziAccessibili +="materiale informativo ad alto contrasto/alta leggibilità";
		}
		if(evento.getAccessibilita().getFlagPostazioniAudio()) 
		{
			if(serviziAccessibili.length()>0) 
			{
				serviziAccessibili +=", ";
			}
			serviziAccessibili +="postazioni audio-descrittive della struttura e del percorso";
		}
		if(evento.getAccessibilita().getFlagAudioguide()) 
		{
			if(serviziAccessibili.length()>0) 
			{
				serviziAccessibili +=", ";
			}
			serviziAccessibili +="audioguide per persone ipovedenti o non vedenti";
		}
		if(evento.getAccessibilita().getFlagAudioguidePercorsi()) 
		{
			if(serviziAccessibili.length()>0) 
			{
				serviziAccessibili +=", ";
			}
			serviziAccessibili +="audioguide con percorsi tattili per persone ipovedenti o non vedenti";
		}
		if(evento.getAccessibilita().getFlagMaterialeSottotitolato()) 
		{
			if(serviziAccessibili.length()>0) 
			{
				serviziAccessibili +=", ";
			}
			serviziAccessibili +="materiale audiovisivo sottotitolato";
		}
		if(evento.getAccessibilita().getFlagRiproduzioneTattili()) 
		{
			if(serviziAccessibili.length()>0) 
			{
				serviziAccessibili +=", ";
			}
			serviziAccessibili +="riproduzione tattili";
		}
		if(evento.getAccessibilita().getFlagComputer()) 
		{
			if(serviziAccessibili.length()>0) 
			{
				serviziAccessibili +=", ";
			}
			serviziAccessibili +="computer con descrizioni vocali";
		}
		if(evento.getAccessibilita().getFlagLis()) 
		{
			if(serviziAccessibili.length()>0) 
			{
				serviziAccessibili +=", ";
			}
			serviziAccessibili +="personale che conosce la Lingua dei Segni Italiana (LIS)";
		}
		return serviziAccessibili;
	}


	private String getStringPersoneEsigenzeSpecifiche(EventoModel evento) {
		String personeEsigenzeSpecifiche="";

		if(evento.getAccessibilita().getFlagAnziani())
		{
			personeEsigenzeSpecifiche +="anziani";
		}
		if(evento.getAccessibilita().getFlagDisabilitaFisica()) 
		{
			if(personeEsigenzeSpecifiche.length()>0) 
			{
				personeEsigenzeSpecifiche +=", ";
			}
			personeEsigenzeSpecifiche +="disabilità fisica";
		}
		if(evento.getAccessibilita().getFlagDisabilitaUditiva()) 
		{
			if(personeEsigenzeSpecifiche.length()>0) 
			{
				personeEsigenzeSpecifiche +=", ";
			}
			personeEsigenzeSpecifiche +="disabilità uditiva";
		}
		if(evento.getAccessibilita().getFlagDisabilitaVisiva()) 
		{
			if(personeEsigenzeSpecifiche.length()>0) 
			{
				personeEsigenzeSpecifiche +=", ";
			}
			personeEsigenzeSpecifiche +="disabilità visiva";
		}
		if(evento.getAccessibilita().getFlagGravidanza()) 
		{
			if(personeEsigenzeSpecifiche.length()>0) 
			{
				personeEsigenzeSpecifiche +=", ";
			}
			personeEsigenzeSpecifiche +="donne in stato di gravidanza";
		}
		if(evento.getAccessibilita().getFlagFamiglieBambini()) 
		{
			if(personeEsigenzeSpecifiche.length()>0) 
			{
				personeEsigenzeSpecifiche +=", ";
			}
			personeEsigenzeSpecifiche +="famiglie con bambini";
		}
		if(evento.getAccessibilita().getFlagPercorsi()) 
		{
			if(personeEsigenzeSpecifiche.length()>0) 
			{
				personeEsigenzeSpecifiche +=", ";
			}
			personeEsigenzeSpecifiche +="percorsi accessibili";
		}
		if(evento.getAccessibilita().getFlagPersoneAnimali()) 
		{
			if(personeEsigenzeSpecifiche.length()>0) 
			{
				personeEsigenzeSpecifiche +=", ";
			}
			personeEsigenzeSpecifiche +="persone con animali";
		}

		return personeEsigenzeSpecifiche;
	}





}
