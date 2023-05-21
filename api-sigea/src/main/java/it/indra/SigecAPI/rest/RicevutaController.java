package it.indra.SigecAPI.rest;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.indra.SigeaCommons.model.DettaglioUtenteModel;
import it.indra.SigecAPI.clientstub.DMSClientStub;
import it.indra.SigecAPI.service.CacheTokenService;
import it.indra.SigecAPI.service.RicevutaService;
import it.indra.SigecAPI.util.CommonUtility;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RicevutaController 
{

	@Autowired
	RicevutaService ricevutaService;
	
	
	@Autowired
	CacheTokenService tokenService;
	
	@Autowired
	DMSClientStub userService;
	
	@RequestMapping (value = "/eventi-finanziati/ricevute/{idEvento}" , method = RequestMethod.GET )
	public ResponseEntity<?> getRicevuteEsterno( @PathVariable Long idEvento)
	{
		
		String token  = tokenService.getToken();
		
		byte[] dataBadge = ricevutaService.export(idEvento, token);
		if(dataBadge !=null)
		{
			
		String base64Encoded = DatatypeConverter.printBase64Binary(dataBadge);

		   HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.parseMediaType("application/pdf"));
		    String filename = "ricevuta.pdf";
		    headers.setContentDispositionFormData(filename, filename);
		    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		  
		
			
			
			  ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(
					  dataBadge, headers, HttpStatus.OK);
	
		
		return response;
		}
		return new ResponseEntity<byte[]>(
						   HttpStatus.NOT_FOUND);
	}
			
	
	@RequestMapping (value = "/ricevute/export/{idEvento}" , method = RequestMethod.GET )
	public ResponseEntity<?> getRicevuteInterno( @PathVariable Long idEvento, @RequestParam String stato , HttpServletRequest request)
	{
		
		
		try {
			boolean first=stato!=null && stato.equalsIgnoreCase("base");
			
		DettaglioUtenteModel user = userService.getUserFromRequest(request);
		
		String token = CommonUtility.getTokenFromRequest(request);
		byte[] dataBadge ;
		if(first) {
			
			dataBadge = ricevutaService.getRicevuta(idEvento);
		}
		else {
			dataBadge= ricevutaService.export(idEvento, token, user.getUtenteId());
		}
		
		if(dataBadge !=null)
		{
		
		String base64Encoded = DatatypeConverter.printBase64Binary(dataBadge);
			
			  ResponseEntity<String> response = new ResponseEntity<String>(
					  base64Encoded, HttpStatus.OK);
	
		
		return response;
		
		}
		return new ResponseEntity<byte[]>(
						   HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(
					  HttpStatus.INTERNAL_SERVER_ERROR);
	
		}
	}
	
}
