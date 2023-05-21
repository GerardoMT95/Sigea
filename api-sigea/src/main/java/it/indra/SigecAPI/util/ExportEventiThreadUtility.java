package it.indra.SigecAPI.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.commons.codec.DecoderException;

import com.indra.es.utils.LogUtility;

import it.indra.SigeaCommons.model.EventoFilter;
import it.indra.SigecAPI.service.EventoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExportEventiThreadUtility  implements Runnable {
	
	EventoService eventiService;
	private String token;
	private String filter;
	private Long idUtente;
	private String uuid;
	private String tmpDirectory;
	
	public ExportEventiThreadUtility(EventoService eventoSer , String filter , Long idUtente, String uuid , String tmpDirectory ) {
		super();
		this.eventiService = eventoSer;
		this.filter= filter;
		this.uuid=uuid;
		this.tmpDirectory=tmpDirectory;
		this.idUtente=idUtente;
	}
	
	@Override
	public void run() {
		
		String prefix=idUtente+"_"+"ricercaexporteventi_";
		String pathFile=tmpDirectory+prefix+uuid;
		WrapperFilterRequest<EventoFilter> wrappedFilter;
		try {
			wrappedFilter = WrapperFilterUtility
					.extractWrapperFromCodedString(filter, EventoFilter.class);
		WrapperFilterResponse<?> response = eventiService.getListaPaginata(wrappedFilter,idUtente);	
		FileOutputStream f = null;
		ObjectOutputStream o = null;
		
		try {	
			f = new FileOutputStream(new File(pathFile));
			o = new ObjectOutputStream(f);
			// Write objects to file
			o.writeObject(response.getData());
		} catch (Exception e) {
			log.error(LogUtility.exceptionToLog(e));
		} finally {
			if (o != null) {
				o.close();
			}
			if (f != null) {
				f.close();
			}
		}
		
		
		} catch (Exception e) {
			FileOutputStream f = null;
			ObjectOutputStream o = null;
			try {
				
				
				
					f = new FileOutputStream(new File(pathFile));
					o = new ObjectOutputStream(f);
					// Write objects to file
					o.writeObject("ECCEZIONE");
				
			
			}catch (Exception e1) {
				log.error(LogUtility.exceptionToLog(e));
			}
		 finally {
			 try {
			if (o != null) {
					o.close();
			}
			if (f != null) {
				f.close();
			}
			 }catch (Exception e2) {

			}
		}
			 
			log.error(LogUtility.exceptionToLog(e));
			} 
		
	}
	
	
}
