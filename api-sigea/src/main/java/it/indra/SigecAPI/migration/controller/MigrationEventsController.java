package it.indra.SigecAPI.migration.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.indra.SigecAPI.exception.LockedResourceException;
import it.indra.SigecAPI.migration.service.AsyncMigrationService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MigrationEventsController {

	@Autowired
	private AsyncMigrationService asyncService;


	//BATCH - SERVIZIO MOMENTANEO PER ATTIVAZIONE IN LOCALE
	@RequestMapping(value = "/migrationEvents/create/{start}/{end}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> createEvents(@PathVariable Long start, @PathVariable Long end, HttpServletRequest request)  throws LockedResourceException, IOException, Exception {
		log.info("Invocato metodo migrationEvents per eventi");
		//se non sei owner del batch, non tentare di lanciarlo!!!
		asyncService.migrationEvents(start,end,request);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//BATCH - SERVIZIO MOMENTANEO PER RELAZIONARE GLI EVENTI ALLE RASSEGNE
	@RequestMapping(value = "/migrationEvents/relate/{start}/{end}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> relateEvents(@PathVariable Long start, @PathVariable Long end, HttpServletRequest request)  throws LockedResourceException, IOException, Exception {
		log.info("Invocato metodo per relazione gli eventi");
		//se non sei owner del batch, non tentare di lanciarlo!!!
		asyncService.relateEvents(start,end,request);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//BATCH - SERVIZIO MOMENTANEO PER L'ELIMINAZIONE DI UN EVENTO
	@RequestMapping(value = "/migrationEvents/delete/{start}/{end}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> deleteEvents(@PathVariable Long start, @PathVariable Long end, HttpServletRequest request)  throws LockedResourceException, IOException, Exception {
		log.info("Invocato metodo migrationEvents per eventi");
		//se non sei owner del batch, non tentare di lanciarlo!!!
		asyncService.deleteEvents(start,end,request);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//SERVIZIO MOMENTANEO PER L'INSERIMENYO IN CODA DI EVENTI E ATTIVITA' DEL NUOVO VIP.
	//http://localhost:9292/SigecAPI/pushNewVip/50/100

	@RequestMapping(value = "/pushNewVip/{risorsaDa}/{risorsaA}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> pushEvent2NewVip(@PathVariable Long risorsaDa, @PathVariable Long risorsaA, HttpServletRequest request)  throws LockedResourceException, IOException, Exception {
		log.info("Invocato metodo pushEvent2NewVip per la migrazione di eventi");
		//se non sei owner del batch, non tentare di lanciarlo!!!
		asyncService.pushEvent2NewVip(risorsaDa,risorsaA,request);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//SERVIZIO MOMENTANEO PER L'INSERIMENYO IN CODA DI EVENTI E ATTIVITA' DEL NUOVO VIP
	//http://localhost:9292/SigecAPI/pushNewVip/activities/2018-10-01/2020-10-01
/*	
	@RequestMapping(value = "/pushNewVip/activities/{dataDa}/{dataA}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> pushAttivita2NewVip(@PathVariable String dataDa, @PathVariable String dataA, HttpServletRequest request)  throws LockedResourceException, IOException, Exception {
		log.info("Invocato metodo pushAttivita2NewVip per la migrazione di eventi");
		//se non sei owner del batch, non tentare di lanciarlo!!!
		asyncService.pushActivities2NewVip(dataDa,dataA,request);
		return new ResponseEntity<>(HttpStatus.OK);
	}
*/
}
