package it.indra.SigeaWeb.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.indra.es.utils.model.WSO2Token;

import it.indra.SigeaWeb.utilities.SigeaPropertiesSettings;
import lombok.extern.slf4j.Slf4j;
@Controller
@Slf4j
public class LogoutController {
	
	@Autowired
	private SigeaPropertiesSettings sigeaPropertiesSettings;
	
	
	@RequestMapping(value = "oidc/logout", method = RequestMethod.GET)
	public RedirectView localLogout(HttpSession session, RedirectAttributes attributes, Authentication authentication)
			throws Exception {		
	
		WSO2Token token = (WSO2Token) session.getAttribute("OIDCToken");
		String idToken = token.getIdToken();
		attributes.addAttribute("idToken", idToken);
		
		return new RedirectView(sigeaPropertiesSettings.getLogout());
	}
	
	@RequestMapping(value = "local/logout", method = RequestMethod.GET)
	@ResponseBody
	public String globalLogout(HttpServletRequest httpServletRequest, HttpServletResponse response, Model model)
			throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		HttpSession session = httpServletRequest.getSession(false);
		SecurityContextHolder.clearContext();
		if (session != null) {
			session.invalidate();
			log.info("Sessione SIGEA invalidata");
		}
		return "loggedOut";
	}
}
