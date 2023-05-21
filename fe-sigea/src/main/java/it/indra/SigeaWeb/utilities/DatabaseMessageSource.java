package it.indra.SigeaWeb.utilities;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.indra.es.utils.LogUtility;
import com.indra.es.utils.model.WSO2Token;

import it.indra.SigeaCommons.model.DecodificaModel;
import it.indra.SigeaWeb.service.AuthService;
import it.indra.SigeaWeb.service.DecodificaService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabaseMessageSource extends ReloadableResourceBundleMessageSource {

	@Autowired
	private DecodificaService decodificaService;

	@Autowired
	private AuthService authService;

	private boolean activate;

	public DatabaseMessageSource(boolean activate) {
		this.activate = activate;
	};

	public boolean isActivate() {
		return activate;
	}

	public void setActivate(boolean activate) {
		this.activate = activate;
	}

	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {

		MessageFormat format;

		if (activate) {

			log.info("Entrato metodo resolveCode");

			List<DecodificaModel> decodifiche = new ArrayList<DecodificaModel>();
			try {
				ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
						.currentRequestAttributes();
				HttpSession session = attr.getRequest().getSession();

				WSO2Token token = (WSO2Token) session.getAttribute("WSO2Token");

				decodifiche = decodificaService.getDecodifiche(token);
			} catch (Exception e) {
				log.error(LogUtility.exceptionToLog(e));
			}

			Optional<DecodificaModel> decodifica = decodifiche.stream().filter(dec -> code.equals(dec.getKey()))
					.findAny();

			if (decodifica.isPresent()) {

				format = new MessageFormat(decodifica.get().getValue(), locale);

			} else {

				format = super.resolveCode(code, locale);

			}
		} else {
			format = super.resolveCode(code, locale);
		}

		return format;

	}

	@Override
	protected String resolveCodeWithoutArguments(String code, Locale locale) {

		String format;

		if (activate) {

			log.info("Entrato metodo resolveCodeWithoutArguments");

			List<DecodificaModel> decodifiche = new ArrayList<DecodificaModel>();
			try {

				ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
						.currentRequestAttributes();

				HttpSession session = attr.getRequest().getSession();

				WSO2Token token = (WSO2Token) session.getAttribute("WSO2Token");

				decodifiche = decodificaService.getDecodifiche(token);
			} catch (Exception e) {
				log.error(LogUtility.exceptionToLog(e));
			}

			Optional<DecodificaModel> decodifica = decodifiche.stream().filter(dec -> code.equals(dec.getKey()))
					.findAny();

			if (decodifica.isPresent()) {

				format = decodifica.get().getValue();

			} else {

				format = super.resolveCodeWithoutArguments(code, locale);

			}
		} else {
			format = super.resolveCodeWithoutArguments(code, locale);
		}

		return format;
	}
}
