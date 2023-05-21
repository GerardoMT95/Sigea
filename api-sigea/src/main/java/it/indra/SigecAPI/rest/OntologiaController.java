package it.indra.SigecAPI.rest;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import it.indra.SigeaCommons.model.MezzoModel;
import it.indra.SigeaCommons.model.ProdottoModel;
import it.indra.SigeaCommons.model.TipologiaAttivitaModel;
import it.indra.SigeaCommons.model.TipologiaMIBACTModel;
import it.indra.SigeaCommons.model.ValoreAttrattivitaTuristicaModel;
import it.indra.SigecAPI.service.OntologiaService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class OntologiaController {
	
	@Autowired
	OntologiaService ontologiaService;

	@RequestMapping(value = "/mezzi", method = RequestMethod.GET)
	public ResponseEntity<?> getMezzi(){
		log.info("Invocato metodo getMezzi");
		return new ResponseEntity<Set<MezzoModel>>(ontologiaService.getMezzi(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/prodotti", method = RequestMethod.GET)
	public ResponseEntity<?> getProdotti(){
		log.info("Invocato metodo getProdotti");
		return new ResponseEntity<Set<ProdottoModel>>(ontologiaService.getProdotti(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tipologiemibact", method = RequestMethod.GET)
	public ResponseEntity<?> getTipologieMIBACT(){
		log.info("Invocato metodo getTipologieMIBACT");
		return new ResponseEntity<Set<TipologiaMIBACTModel>>(ontologiaService.getTipologieMIBACT(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tipologieattivita", method = RequestMethod.GET)
	public ResponseEntity<?> getTipologieAttivita(){
		log.info("Invocato metodo getTipologieAttivita");
		return new ResponseEntity<Set<TipologiaAttivitaModel>>(ontologiaService.getTipologieAttivita(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/valoriattrattivita", method = RequestMethod.GET)
	public ResponseEntity<?> getValoriAttrattivita(){
		log.info("Invocato metodo getValoriAttrattivita");
		return new ResponseEntity<Set<ValoreAttrattivitaTuristicaModel>>(ontologiaService.getValoriAttrattivita(), HttpStatus.OK);
	}
	

}
