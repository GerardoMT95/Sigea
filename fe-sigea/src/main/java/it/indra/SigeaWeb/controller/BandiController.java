package it.indra.SigeaWeb.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Status;

import com.indra.es.utils.RestUtility;
import com.indra.es.utils.model.WSO2Token;

import it.indra.SigeaWeb.service.BandiService;
import it.puglia.spc.ect.commonsbandi.BandiDTO;
import it.puglia.spc.ect.commonsbandi.ProgettiDTO;
import it.puglia.spc.ect.commonsbandi.ProgettoDTO;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping
@Slf4j
public class BandiController {

	@Autowired
	@Qualifier(value = "bandiService")
	BandiService bandiService;

	@RequestMapping(value = "/bandi")
	public ResponseEntity<?> recuperaBandi(HttpServletRequest request,
			HttpServletResponse response) {
		
		WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");
		
		try {
			log.info("Invocato servizio /bandi");
			BandiDTO bandi = bandiService.findBandi(token);
			
			return new ResponseEntity<BandiDTO>(bandi, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Errore durante il recupero dei bandi", e);
			return RestUtility.errorResponse(Status.INTERNAL_SERVER_ERROR, e.getMessage());
		}

	}

	@RequestMapping(value = "/progetti")
	public ResponseEntity<?> recuperaProgetti(
			@RequestParam(required = false, name = "cfPiva") String cfPiva , @RequestParam(required = false, name = "cfTitolare") String cfTitolare ,  HttpServletRequest request,
			HttpServletResponse response) {
		
		WSO2Token token = (WSO2Token) request.getSession().getAttribute("WSO2Token");

		ProgettiDTO progettiImpresa = new ProgettiDTO();
		progettiImpresa.setItems(new ArrayList<ProgettoDTO>());
		ProgettiDTO progettiTitolare =new ProgettiDTO();
		progettiTitolare.setItems(new ArrayList<ProgettoDTO>());
		ProgettiDTO progetti= new ProgettiDTO();
		
		
		try {
			
			if(cfPiva!=null && !cfPiva.equals("")) {
				log.info("Invocato servizio /processi con cfPiva = " + cfPiva);
				progettiImpresa = bandiService.findProgetti(cfPiva, token);
			}
			
			if(cfTitolare!=null && !cfTitolare.equals("")) {
				log.info("Invocato servizio /processi con cfTitolare = " + cfTitolare);
				progettiTitolare = bandiService.findProgetti(cfTitolare, token);
			}
			
			List<ProgettoDTO> progettiTotaliList = new ArrayList<>();
			
			List<ProgettoDTO> progettiTotaliFilteredList = new ArrayList<>();
			progettiTotaliList.addAll(progettiImpresa.getItems());
			progettiTotaliList.addAll(progettiTitolare.getItems());
			
			
			for(ProgettoDTO progetto: progettiTotaliList)
			{
				if(!progettiTotaliFilteredList.stream().anyMatch(x -> x.getId_progetto().equalsIgnoreCase(progetto.getId_progetto())))
				{
					progettiTotaliFilteredList.add(progetto);
				}
			}
			
			progetti.setItems(progettiTotaliFilteredList);
			
			
			
			
			return new ResponseEntity<ProgettiDTO>(progetti, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Errore durante il recupero dei progetti", e);
			return RestUtility.errorResponse(Status.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

}
