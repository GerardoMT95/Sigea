package it.indra.SigeaWeb.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.tika.Tika;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indra.es.builder.RestTemplateBuilder;
import com.indra.es.utils.LogUtility;
import com.indra.es.utils.auth.service.RefreshOauth2TokenService;
import com.indra.es.utils.exceptions.RestException;
import com.indra.es.utils.model.WSO2Token;

import it.indra.SigeaCommons.model.AttivitaModel;
import it.indra.SigeaCommons.model.DocumentoEventoModel;
import it.indra.SigeaCommons.model.EventoFilter;
import it.indra.SigeaCommons.model.EventoModel;
import it.indra.SigeaCommons.model.EventoModelList;
import it.indra.SigeaCommons.model.EventoTitoloModel;
import it.indra.SigeaCommons.model.ImmagineModel;
import it.indra.SigeaCommons.model.LinkModel;
import it.indra.SigeaCommons.model.LocationModel;
import it.indra.SigeaCommons.model.LogEventoModel;
import it.indra.SigeaCommons.model.PubblicazioneModel;
import it.indra.SigeaCommons.model.RelazioneModel;
import it.indra.SigeaCommons.model.SmartModelList;
import it.indra.SigeaCommons.model.StatoModel;
import it.indra.SigeaCommons.model.redazioni.B2BDMSSchedaModel;
import it.indra.SigeaCommons.model.redazioni.B2BSchedaModel;
import it.indra.SigeaCommons.model.redazioni.VIPSchedaDocumentoModel;
import it.indra.SigeaCommons.model.redazioni.VIPSchedaImmagineModel;
import it.indra.SigeaCommons.model.redazioni.VIPSchedaLinkModel;
import it.indra.SigeaCommons.model.redazioni.VIPSchedaModel;
import it.indra.SigeaCommons.model.redazioni.VIPSchedaRelazioneModel;
import it.indra.SigeaWeb.utilities.SigeaPropertiesSettings;
import it.indra.SigeaWeb.utilities.WrapperFilterRequest;
import it.indra.SigeaWeb.utilities.WrapperFilterResponse;
import it.indra.SigeaWeb.utilities.WrapperFilterUtility;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EventoService {

//	@Autowired
//	GeneralProperties properties;

	@Autowired
	private SigeaPropertiesSettings sigeaPropertiesSettings;

	@Autowired
	private OntologiaService ontologiaService;


	@Autowired
	ServletContext servletContext;
//	@Autowired
//	@Qualifier("genericRestTemplate")
//	RestTemplate restTemplate;

//	@Autowired
//	@Qualifier("multipartRestTemplate")
//	private RestTemplate restTemplateMulti;

	@Autowired
	private TerritorialService territorialService;

	public WrapperFilterResponse<EventoModelList> getListaPaginataEventi(WSO2Token token,
			WrapperFilterRequest<EventoFilter> dataTableRequest) throws Exception {
		if (dataTableRequest.getFilter() == null) {
			EventoFilter filter = new EventoFilter();
			dataTableRequest.setFilter(filter);
		}
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/listapaginata")
				.queryParam("wrappedFilter", WrapperFilterUtility.encodeWrapper(dataTableRequest));
//		return restTemplate.exchange(url.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()),new ParameterizedTypeReference<WrapperFilterResponse<EventoModelList>>() {}).getBody();
		// TODO GESTIONE WrapperFilterResponse<DATA>
		@SuppressWarnings({ "unchecked" })
		ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder().url(url.build().toUriString())
				.returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.GET).build().executeRequest(false);

		ObjectMapper objectMapper = new ObjectMapper();

		WrapperFilterResponse<EventoModelList> result = objectMapper.readValue(res.getBody(),
				new TypeReference<WrapperFilterResponse<EventoModelList>>() {
				});

//		WrapperFilterResponse<EventoModelList> result = res.getBody();

		return result;
	}

	public String startPollingListaPaginataEventi(WSO2Token token, WrapperFilterRequest<EventoFilter> dataTableRequest)
			throws Exception {
		if (dataTableRequest.getFilter() == null) {
			EventoFilter filter = new EventoFilter();
			dataTableRequest.setFilter(filter);
		}
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/export/start")
				.queryParam("wrappedFilter", WrapperFilterUtility.encodeWrapper(dataTableRequest));
//		return restTemplate.exchange(url.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()),new ParameterizedTypeReference<WrapperFilterResponse<EventoModelList>>() {}).getBody();
		// TODO GESTIONE WrapperFilterResponse<DATA>
		@SuppressWarnings({ "unchecked" })
		ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder().url(url.build().toUriString())
				.returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.GET).build().executeRequest(false);

		if (res.getStatusCodeValue() != 200) {

			return null;
		}
		String result = res.getBody();
//		WrapperFilterResponse<EventoModelList> result = res.getBody();

		return result;
	}

	public Set<StatoModel> getStatiEvento(WSO2Token token) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/stati");
//		Set<StatoModel> listastati = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET,new HttpEntity<String>(new HttpHeaders()), new ParameterizedTypeReference<Set<StatoModel>>() {}).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<StatoModel[]> res = (ResponseEntity<StatoModel[]>) new RestTemplateBuilder()
				.url(url.build().toUriString()).returnClass(StatoModel[].class).oauth2Token(token)
				.httpMethod(HttpMethod.GET).build().executeRequest(false);

		Set<StatoModel> result = new HashSet<StatoModel>(Arrays.asList(res.getBody()));

		return result;
	}

	// TODO gestione per oggetto e non per responceentity
	public ResponseEntity<EventoModel> getEvento(WSO2Token token, Long idEvento) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/" + idEvento);
//		try {
//			ResponseEntity<EventoModel> evento = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET,new HttpEntity<String>(new HttpHeaders()), new ParameterizedTypeReference<EventoModel>() {});

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<EventoModel> res = (ResponseEntity<EventoModel>) new RestTemplateBuilder()
				.url(url.build().toUriString()).returnClass(EventoModel.class).oauth2Token(token)
				.httpMethod(HttpMethod.GET).build().executeRequest(false);

		return res;
//		} catch (HttpClientErrorException e) {
//			if (e.getStatusCode().equals(HttpStatus.CONFLICT)) {
//				return new ResponseEntity<>(HttpStatus.CONFLICT);
//			} else {
//				throw e;
//			}
//		}
	}
	

	public Long saveEvento(WSO2Token token, EventoModel model, String stato) throws Exception {
		Set<LocationModel> set = model.getLocationSet();
		Map<String, JSONObject> areaPerComune = territorialService.getAreaPerComune();
		for (LocationModel location : set) {
			if (location.getCodComune() != null && !location.getCodComune().equals("") && location.getPuglia() != null
					&& location.getPuglia().booleanValue()) {
				JSONObject objA = areaPerComune.get(location.getCodComune());
				location.setCodArea(objA.getString("codArea"));
				location.setArea(objA.getString("areaTerritoriale"));
			}
		}
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/" + stato);
//		return restTemplate.exchange(url.build().toUriString(), HttpMethod.PUT, new HttpEntity<EventoModel>(model, new HttpHeaders()), Long.class).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<Long> res = (ResponseEntity<Long>) new RestTemplateBuilder().body(model)
				.url(url.build().toUriString()).returnClass(Long.class).oauth2Token(token).httpMethod(HttpMethod.PUT)
				.build().executeRequest(false);

		return res.getBody();
	}

	public String duplicaEvento(WSO2Token token, Long idEvento) throws Exception {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/duplica/" + idEvento);
//		return restTemplate.exchange(url.build().toUriString(), HttpMethod.POST,new HttpEntity<String>(new HttpHeaders()), String.class).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder().url(url.build().toUriString())
				.returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.POST).build().executeRequest(false);

		return res.getBody();
	}

	public String duplicaEvento(WSO2Token token, Long idEvento, AttivitaModel attivita) throws Exception {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/duplica/" + idEvento);
//		return restTemplate.exchange(url.build().toUriString(), HttpMethod.POST,new HttpEntity<String>(new HttpHeaders()), String.class).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder().url(url.build().toUriString())
				.returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.POST).body(attivita).build()
				.executeRequest(false);

		return res.getBody();
	}

	public String lockEvento(WSO2Token token, Long idEvento) throws Exception {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/lock/" + idEvento);
//		return restTemplate.exchange(url.build().toUriString(), HttpMethod.POST,new HttpEntity<String>(new HttpHeaders()), String.class).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder().url(url.build().toUriString())
				.returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.POST).build().executeRequest(false);

		return res.getBody();
	}

	public HSSFWorkbook exportExcel(WSO2Token token, String ruolo, List<EventoModelList> list) throws Exception {

		LocalTime now = LocalTime.now();
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		HSSFWorkbook workbook = new HSSFWorkbook();

		HSSFPalette palette = workbook.getCustomPalette();
		HSSFColor myColor = palette.findSimilarColor(0, 117, 191);
		short palIndex = myColor.getIndex();

		HSSFPalette palette2 = workbook.getCustomPalette();
		HSSFColor myColor2 = palette2.findSimilarColor(80, 80, 90);
		short palIndex2 = myColor2.getIndex();

		HSSFCellStyle styleTitle = workbook.createCellStyle();
		HSSFFont fontTitle = workbook.createFont();
		fontTitle.setBold(true);
		fontTitle.setFontHeightInPoints((short) 12);
		styleTitle.setAlignment(CellStyle.ALIGN_LEFT);
		styleTitle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleTitle.setFont(fontTitle);
		HSSFCellStyle styleInfo = workbook.createCellStyle();
		styleInfo.setAlignment(CellStyle.ALIGN_LEFT);
		styleInfo.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		HSSFFont fontInfo = workbook.createFont();
		fontInfo.setFontHeightInPoints((short) 12);
		styleInfo.setFont(fontInfo);

		HSSFSheet sheet = workbook.createSheet("Foglio1");
		HSSFRow row1 = sheet.createRow((short) 0);
		HSSFRow row2 = sheet.createRow((short) 1);
		HSSFRow rowTitle = sheet.createRow((short) 3);
		HSSFRow rowInfo = sheet.createRow((short) 4);
		HSSFRow rowOTitle = sheet.createRow((short) 6);
		HSSFRow rowHead = sheet.createRow((short) 7);

		rowTitle.setHeight((short) 400);
		rowInfo.setHeight((short) 400);

		HSSFCellStyle style1 = workbook.createCellStyle();
		style1.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style1.setFillForegroundColor(palIndex);

		HSSFCellStyle style = workbook.createCellStyle();
		HSSFCellStyle styleHead = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		HSSFFont fontHead = workbook.createFont();
		fontHead.setColor(HSSFColor.WHITE.index);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFont(font);
		styleHead.setAlignment(CellStyle.ALIGN_CENTER);
		styleHead.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleHead.setFont(fontHead);
		styleHead.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleHead.setFillForegroundColor(palIndex2);
		styleHead.setBorderBottom(CellStyle.BORDER_THIN);
		styleHead.setBorderTop(CellStyle.BORDER_THIN);
		styleHead.setBorderLeft(CellStyle.BORDER_THIN);
		styleHead.setBorderRight(CellStyle.BORDER_THIN);
		styleHead.setBottomBorderColor(HSSFColor.WHITE.index);
		styleHead.setLeftBorderColor(HSSFColor.WHITE.index);
		styleHead.setRightBorderColor(HSSFColor.WHITE.index);
		styleHead.setTopBorderColor(HSSFColor.WHITE.index);

		HSSFCellStyle styleHead1 = workbook.createCellStyle();
		styleHead1.setAlignment(CellStyle.ALIGN_CENTER);
		styleHead1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleHead1.setFont(fontHead);
		styleHead1.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleHead1.setFillForegroundColor(palIndex2);

		Cell cell1 = row1.createCell(0);
		Cell cell2 = row2.createCell(0);
		cell1.setCellStyle(style1);
		cell2.setCellStyle(style1);

		Cell cellTitle = rowTitle.createCell(0);
		cellTitle.setCellValue("Eventi");
		cellTitle.setCellStyle(styleTitle);

		Cell cellHead = rowInfo.createCell(0);
		String ora = now.toString();
		ora = ora.substring(0, 5);
		String data = date.format(formatters);
		cellHead.setCellValue("Generato dal Digital Management System (DMS) della Regione Puglia in data " + data
				+ " alle ore " + ora);
		cellHead.setCellStyle(styleInfo);

		Cell cellOTitle = rowOTitle.createCell(0);
		cellOTitle.setCellValue("DATI EVENTI");
		cellOTitle.setCellStyle(styleHead1);

		short nextRow = 10;
		switch (ruolo) {
		case "PROMOTORE":
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 9));
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 9));
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 9));
			sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 9));
			rowHead.createCell(0).setCellValue("Titolo");
			rowHead.createCell(1).setCellValue("ID");
			rowHead.createCell(2).setCellValue("Tipo evento");
			rowHead.createCell(3).setCellValue("Data da");
			rowHead.createCell(4).setCellValue("Data a");
			rowHead.createCell(5).setCellValue("Comune");
			rowHead.createCell(6).setCellValue("Stato");
			rowHead.createCell(7).setCellValue("Creato il");
			rowHead.createCell(8).setCellValue("Data modifica");
			rowHead.createCell(9).setCellValue("Finanziamento");
//			rowHead.createCell(9).setCellValue("Progetto");
			// rowHead.createCell(8).setCellValue("Pubblicato da");

			for (int i = 9; i >= 0; i--) {
				sheet.setDefaultColumnStyle(i, style);
				rowHead.getCell(i).setCellStyle(styleHead);
			}

			nextRow = 8;
			if (!list.isEmpty()) {
				for (EventoModelList evento : list) {
					HSSFRow row = sheet.createRow(nextRow);
					row.createCell(0).setCellValue(evento.getTitolo());
					row.createCell(1).setCellValue(evento.getEventoId());
					row.createCell(2)
							.setCellValue("EVENTO".equalsIgnoreCase(evento.getTipo()) ? "SINGOLO" : evento.getTipo());
					row.createCell(3).setCellValue(evento.getDataDa());
					row.createCell(4).setCellValue(evento.getDataA());
					row.createCell(5).setCellValue(evento.getComune());
					row.createCell(6).setCellValue(evento.getStato());
					row.createCell(7).setCellValue(evento.getDataIns());
					row.createCell(8).setCellValue(evento.getDataMod());
					row.createCell(9).setCellValue(evento.getFinanziato());
//					row.createCell(9).setCellValue(evento.getTitoloProgetto());
					// row.createCell(8).setCellValue(evento.getRedazioni());
					nextRow = (short) (nextRow + 1);
				}
			}

			for (int i = 9; i >= 0; i--) {
				sheet.autoSizeColumn(i);
			}
			break;
		case "REDATTORE":

			rowTitle.setHeight((short) 400);
			rowInfo.setHeight((short) 400);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 15));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 15));
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 15));
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 15));
			sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 15));
			rowHead.createCell(9).setCellValue("ID");
			rowHead.createCell(7).setCellValue("Tipo evento");
			rowHead.createCell(0).setCellValue("Titolo");
			rowHead.createCell(1).setCellValue("Stato");
			rowHead.createCell(10).setCellValue("Ripubblicato");
			rowHead.createCell(11).setCellValue("Pubblicato");
			rowHead.createCell(2).setCellValue("Data da");
			rowHead.createCell(3).setCellValue("Data a");
			rowHead.createCell(5).setCellValue("Comune");
			rowHead.createCell(8).setCellValue("Tipologia scheda");
			rowHead.createCell(6).setCellValue("Creato da");
			rowHead.createCell(4).setCellValue("Creato il");
			
			rowHead.createCell(12).setCellValue("Categorie");
			rowHead.createCell(13).setCellValue("Prodotti");
			rowHead.createCell(14).setCellValue("Tipologia Attivita");

			rowHead.createCell(15).setCellValue("Finanziamento");
			
//			rowHead.createCell(9).setCellValue("Finanziato");
//			rowHead.createCell(10).setCellValue("Progetto");
//			rowHead.createCell(12).setCellValue("Pubblicato da");

			for (int i = 15; i >= 0; i--) {
				sheet.setDefaultColumnStyle(i, style);
				rowHead.getCell(i).setCellStyle(styleHead);
			}

			CreationHelper createHelper = workbook.getCreationHelper();
			CellStyle hlink_style = workbook.createCellStyle();
			Font hlink_font = workbook.createFont();
			hlink_style.setAlignment(CellStyle.ALIGN_CENTER);
			hlink_style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			hlink_font.setUnderline(Font.U_SINGLE);
			hlink_font.setColor(IndexedColors.BLUE.getIndex());
			hlink_style.setFont(hlink_font);
			if (!list.isEmpty()) {
				for (EventoModelList evento : list) {
					Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_EMAIL);
					link.setAddress("mailto:" + evento.getEmailOwner());
					HSSFRow row = sheet.createRow(nextRow);
					row.createCell(9).setCellValue(evento.getEventoId());
					row.createCell(7)
							.setCellValue("EVENTO".equalsIgnoreCase(evento.getTipo()) ? "SINGOLO" : evento.getTipo());
					row.createCell(0).setCellValue(evento.getTitolo());
					Cell cell = row.createCell(3);
					cell.setCellValue(evento.getOwner());
					cell.setHyperlink(link);
					cell.setCellStyle(hlink_style);
					row.createCell(1).setCellValue(evento.getStatoPubblicazione());
					row.createCell(10).setCellValue(evento.getRipubblicatoRedazione());
					row.createCell(11).setCellValue(evento.getPubblicatoRedazione());
					row.createCell(2).setCellValue(evento.getDataDa());
					row.createCell(3).setCellValue(evento.getDataA());
					row.createCell(5).setCellValue(evento.getComune());
					row.createCell(6).setCellValue(evento.getOwner());			
					row.createCell(15).setCellValue(evento.getFinanziato());

					String tipologiaScheda = "";
					if (StringUtils.isNotBlank(evento.getTipologiaScheda())) {
						if (StringUtils.equalsIgnoreCase(evento.getTipologiaScheda(), "ATTIVITA")) {
							tipologiaScheda = "Attività";
						} else {
							tipologiaScheda = evento.getTipologiaScheda();
						}
					}

					row.createCell(8).setCellValue(tipologiaScheda.toUpperCase());
					row.createCell(4).setCellValue(evento.getDataIns());

					
					if (evento.getTipologieMibact()!=null && !evento.getTipologieMibact().matches("")) {
						JSONObject jObj; 

						try {	
							try {
								jObj = new JSONObject(evento.getTipologieMibact());
							} catch (JSONException e) {
								log.debug("MIBACT recuperato dalla colonna evento, crea un json");
								String mibact="{\""+evento.getTipologieMibact().replace(":", "\":\"")+"\"}";
								jObj = new JSONObject(mibact);
							}


							Iterator<String> keyItr = jObj.keys();
							String categorie="";

							while(keyItr.hasNext()) {
								String key = keyItr.next();
								categorie= categorie + jObj.getString(key)+",";
							}
							categorie=categorie.substring(0,categorie.length()-1);
							row.createCell(12).setCellValue(categorie);

						}catch (Exception e) {
							log.error("JSONObject errato, non verrà inserito il mibact");
						}

					}

					if (evento.getProdotti()!=null && !evento.getProdotti().matches("")) {
						try {
							JSONArray prodArray = new JSONArray(evento.getProdotti());
							String prodotti="";

							for (int i = 0; i < prodArray.length(); i++) {
								prodotti=prodotti + prodArray.getString(i) +",";;
							}

							prodotti=prodotti.substring(0,prodotti.length()-1);;
							row.createCell(13).setCellValue(prodotti);
						} catch (Exception e) {
							log.error("JSONArray errato, non verrà inserito il prodotto");
						}
					}

					if (evento.getTipologieAttivita()!=null && !evento.getTipologieAttivita().matches("") && evento.getTipologiaScheda().equalsIgnoreCase("ATTIVITA")) {
						try {
							JSONArray tipologieAttivitaArray = new JSONArray(evento.getTipologieAttivita());
							String tipologieAttivita="";

							for (int i = 0; i < tipologieAttivitaArray.length(); i++) {
								tipologieAttivita=tipologieAttivita + tipologieAttivitaArray.getString(i) +",";;
							}

							tipologieAttivita=tipologieAttivita.substring(0,tipologieAttivita.length()-1);
							row.createCell(14).setCellValue(tipologieAttivita);
						} catch (Exception e) {
							log.error("JSONArray errato, non verrà inserita la tipologia attivita");
						}
					}

					nextRow = (short) (nextRow + 1);
				}
			}

			for (int i = 15; i >= 0; i--) {
				sheet.autoSizeColumn(i);
			}
			break;
		case "VALIDATORE":
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 11));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 11));
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 11));
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 11));
			sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 11));
			rowHead.createCell(0).setCellValue("Titolo");
			rowHead.createCell(1).setCellValue("Stato");
			rowHead.createCell(2).setCellValue("Data da");
			rowHead.createCell(3).setCellValue("Data a");
			rowHead.createCell(5).setCellValue("Comune");
			rowHead.createCell(6).setCellValue("Creato da");
			rowHead.createCell(7).setCellValue("Pubblicato");
			rowHead.createCell(4).setCellValue("Creato il");
			rowHead.createCell(8).setCellValue("Tipo evento");
			rowHead.createCell(9).setCellValue("ID");
			rowHead.createCell(10).setCellValue("Categorie");

			rowHead.createCell(11).setCellValue("Finanziamento");
			
			
//			rowHead.createCell(8).setCellValue("Finanziato");
//			rowHead.createCell(9).setCellValue("Progetto");

			for (int i = 11; i >= 0; i--) {
				sheet.setDefaultColumnStyle(i, style);
				rowHead.getCell(i).setCellStyle(styleHead);
			}

			nextRow = 8;
			if (!list.isEmpty()) {
				for (EventoModelList evento : list) {
					HSSFRow row = sheet.createRow(nextRow);

					row.createCell(0).setCellValue(evento.getTitolo());
					row.createCell(1).setCellValue(evento.getStato());
					row.createCell(2).setCellValue(evento.getDataDa());
					row.createCell(3).setCellValue(evento.getDataA());
					row.createCell(5).setCellValue(evento.getComune());
					row.createCell(6).setCellValue(evento.getOwner());
					row.createCell(7).setCellValue(evento.getRedazioni() != null && !evento.getRedazioni().trim().isEmpty() ? "SI" : "NO");
					row.createCell(4).setCellValue(evento.getDataIns());
					row.createCell(8).setCellValue("EVENTO".equalsIgnoreCase(evento.getTipo()) ? "SINGOLO" : evento.getTipo());
					row.createCell(9).setCellValue(evento.getEventoId());
					row.createCell(10).setCellValue(evento.getTipologieMibact());
		
					row.createCell(11).setCellValue(evento.getFinanziato());

//					row.createCell(8).setCellValue(evento.getFinanziato());
					nextRow = (short) (nextRow + 1);
				}
			}

			for (int i = 11; i >= 0; i--) {
				sheet.autoSizeColumn(i);
			}
			break;
		case "FINANZIATO_ENTE":
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 11));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 11));
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 11));
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 11));
			sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 11));
			rowHead.createCell(0).setCellValue("Creato da");
			rowHead.createCell(1).setCellValue("Titolo");
			rowHead.createCell(2).setCellValue("Bando");
			rowHead.createCell(3).setCellValue("Progetto");		
			rowHead.createCell(4).setCellValue("Data da");
			rowHead.createCell(5).setCellValue("Data a");
			rowHead.createCell(6).setCellValue("Comune");
			rowHead.createCell(7).setCellValue("ID");
			rowHead.createCell(8).setCellValue("Creato il");
			rowHead.createCell(9).setCellValue("Tipologia nucleo");
			rowHead.createCell(10).setCellValue("Tipologia scheda redazionale");


			for (int i = 10; i >= 0; i--) {
				sheet.setDefaultColumnStyle(i, style);
				rowHead.getCell(i).setCellStyle(styleHead);
			}

			nextRow = 8;
			if (!list.isEmpty()) {
				for (EventoModelList evento : list) {
					HSSFRow row = sheet.createRow(nextRow);
					row.createCell(0).setCellValue(evento.getOwner());					
					row.createCell(1).setCellValue(evento.getTitolo());					
					row.createCell(2).setCellValue(evento.getTitoloBando());
					row.createCell(3).setCellValue(evento.getTitoloProgetto());
					row.createCell(4).setCellValue(evento.getDataDa());
					row.createCell(5).setCellValue(evento.getDataA());
					row.createCell(6).setCellValue(evento.getComune());
					row.createCell(7).setCellValue(evento.getEventoId());
					row.createCell(8).setCellValue(evento.getDataIns());
					row.createCell(9).setCellValue(evento.getTipo());
					row.createCell(10).setCellValue(evento.getTipologiaScheda());
					
					nextRow = (short) (nextRow + 1);
				}
			}

			for (int i = 10 ; i >= 0; i--) {
				sheet.autoSizeColumn(i);
			}
			break;	
		case "FINANZIATO":
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 11));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 11));
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 11));
			sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 11));
			sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 11));
		
			rowHead.createCell(0).setCellValue("Titolo");
			rowHead.createCell(1).setCellValue("Bando");
			rowHead.createCell(2).setCellValue("Progetto");		
			rowHead.createCell(3).setCellValue("Data da");
			rowHead.createCell(4).setCellValue("Data a");
			rowHead.createCell(5).setCellValue("Comune");
			rowHead.createCell(6).setCellValue("ID");
			rowHead.createCell(7).setCellValue("Creato il");
			rowHead.createCell(8).setCellValue("Tipologia nucleo");
			


			for (int i = 8; i >= 0; i--) {
				sheet.setDefaultColumnStyle(i, style);
				rowHead.getCell(i).setCellStyle(styleHead);
			}

			nextRow = 8;
			if (!list.isEmpty()) {
				for (EventoModelList evento : list) {
					HSSFRow row = sheet.createRow(nextRow);
								
					row.createCell(0).setCellValue(evento.getTitolo());					
					row.createCell(1).setCellValue(evento.getTitoloBando());
					row.createCell(2).setCellValue(evento.getTitoloProgetto());
					row.createCell(3).setCellValue(evento.getDataDa());
					row.createCell(4).setCellValue(evento.getDataA());
					row.createCell(5).setCellValue(evento.getComune());
					row.createCell(6).setCellValue(evento.getEventoId());
					row.createCell(7).setCellValue(evento.getDataIns());		
					row.createCell(8).setCellValue(evento.getTipo());
					
					
					nextRow = (short) (nextRow + 1);
				}
			}

			for (int i = 8 ; i >= 0; i--) {
				sheet.autoSizeColumn(i);
			}
			break;	
									
		default:
			break;
		}

		return workbook;
	}
		
	public HSSFWorkbook exportRevisioni(WSO2Token token, Set<LogEventoModel> result) throws Exception {

		LocalTime now = LocalTime.now();
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		HSSFWorkbook workbook = new HSSFWorkbook();

		HSSFPalette palette = workbook.getCustomPalette();
		HSSFColor myColor = palette.findSimilarColor(0, 117, 191);
		short palIndex = myColor.getIndex();

		HSSFPalette palette2 = workbook.getCustomPalette();
		HSSFColor myColor2 = palette2.findSimilarColor(80, 80, 90);
		short palIndex2 = myColor2.getIndex();

		HSSFCellStyle styleTitle = workbook.createCellStyle();
		HSSFFont fontTitle = workbook.createFont();
		fontTitle.setBold(true);
		fontTitle.setFontHeightInPoints((short) 12);
		styleTitle.setAlignment(CellStyle.ALIGN_LEFT);
		styleTitle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleTitle.setFont(fontTitle);
		HSSFCellStyle styleInfo = workbook.createCellStyle();
		styleInfo.setAlignment(CellStyle.ALIGN_LEFT);
		styleInfo.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		HSSFFont fontInfo = workbook.createFont();
		fontInfo.setFontHeightInPoints((short) 12);
		styleInfo.setFont(fontInfo);
		styleInfo.setWrapText(true);

		HSSFSheet sheet = workbook.createSheet("Foglio1");
		HSSFRow row1 = sheet.createRow((short) 0);
		HSSFRow row2 = sheet.createRow((short) 1);
		HSSFRow rowTitle = sheet.createRow((short) 3);
		HSSFRow rowInfo = sheet.createRow((short) 4);
		HSSFRow rowOTitle = sheet.createRow((short) 6);
		HSSFRow rowHead = sheet.createRow((short) 7);

		rowTitle.setHeight((short) 400);
		rowInfo.setHeight((short) 400);

		HSSFCellStyle style1 = workbook.createCellStyle();
		style1.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style1.setFillForegroundColor(palIndex);

		HSSFCellStyle style = workbook.createCellStyle();
		HSSFCellStyle styleHead = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		HSSFFont fontHead = workbook.createFont();
		fontHead.setColor(HSSFColor.WHITE.index);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFont(font);
		styleHead.setAlignment(CellStyle.ALIGN_CENTER);
		styleHead.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleHead.setFont(fontHead);
		styleHead.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleHead.setFillForegroundColor(palIndex2);
		styleHead.setBorderBottom(CellStyle.BORDER_THIN);
		styleHead.setBorderTop(CellStyle.BORDER_THIN);
		styleHead.setBorderLeft(CellStyle.BORDER_THIN);
		styleHead.setBorderRight(CellStyle.BORDER_THIN);
		styleHead.setBottomBorderColor(HSSFColor.WHITE.index);
		styleHead.setLeftBorderColor(HSSFColor.WHITE.index);
		styleHead.setRightBorderColor(HSSFColor.WHITE.index);
		styleHead.setTopBorderColor(HSSFColor.WHITE.index);

		HSSFCellStyle styleHead1 = workbook.createCellStyle();
		styleHead1.setAlignment(CellStyle.ALIGN_CENTER);
		styleHead1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		styleHead1.setFont(fontHead);
		styleHead1.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleHead1.setFillForegroundColor(palIndex2);

		Cell cell1 = row1.createCell(0);
		Cell cell2 = row2.createCell(0);
		cell1.setCellStyle(style1);
		cell2.setCellStyle(style1);

		Cell cellTitle = rowTitle.createCell(0);
		cellTitle.setCellValue("Revisioni");
		cellTitle.setCellStyle(styleTitle);

		Cell cellHead = rowInfo.createCell(0);
		String ora = now.toString();
		ora = ora.substring(0, 5);
		String data = date.format(formatters);
		cellHead.setCellValue("Generato dal Digital Management System (DMS) della Regione Puglia in data " + data
				+ " alle ore " + ora);
		cellHead.setCellStyle(styleInfo);

		Cell cellOTitle = rowOTitle.createCell(0);
		cellOTitle.setCellValue("ELENCO REVISIONI");
		cellOTitle.setCellStyle(styleHead1);

		short nextRow = 4;

			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 3));
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 3));
			sheet.addMergedRegion(new CellRangeAddress(4, 5, 0, 3));
			sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 3));
			rowHead.createCell(0).setCellValue("Data e ora");
			rowHead.createCell(1).setCellValue("Tipo operazione");
			rowHead.createCell(2).setCellValue("Nome utente");
			rowHead.createCell(3).setCellValue("Stato");


			for (int i = 3; i >= 0; i--) {
				sheet.setDefaultColumnStyle(i, style);
				rowHead.getCell(i).setCellStyle(styleHead);
			}

			nextRow = 8;
			if (!result.isEmpty()) {
				for (LogEventoModel revisione : result) {
					HSSFRow row = sheet.createRow(nextRow);
					String s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(revisione.getDataModifica());
					row.createCell(0).setCellValue(s);
					row.createCell(1).setCellValue(revisione.getTipoOperazione());
					row.createCell(2).setCellValue(revisione.getNomeUtente());
					row.createCell(3).setCellValue(revisione.getDescrizioneStato());

					nextRow = (short) (nextRow + 1);
				}
			}

			for (int i = 3; i >= 0; i--) {
				sheet.autoSizeColumn(i);
			}
			
		return workbook;
	}
	
	

	public List<SmartModelList> getEventoPerComune(WSO2Token token, Boolean isRedazione) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(
				sigeaPropertiesSettings.getUrlSIGEC() + "eventi/raggruppamentopercomune?redazione=" + isRedazione);
//		List<SmartModelList> listaSmart = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET,new HttpEntity<String>(new HttpHeaders()), new ParameterizedTypeReference<List<SmartModelList>>() {}).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<SmartModelList[]> res = (ResponseEntity<SmartModelList[]>) new RestTemplateBuilder()
				.url(url.build().toUriString()).returnClass(SmartModelList[].class).oauth2Token(token)
				.httpMethod(HttpMethod.GET).build().executeRequest(false);

		List<SmartModelList> result = new ArrayList<SmartModelList>(Arrays.asList(res.getBody()));

		return result;
	}

	public Set<EventoTitoloModel> getEventoTitoli(WSO2Token token, Long id, String tipoEvento, String query)
			throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/" + id + "/titoli").queryParam("q", query);
		if (tipoEvento != null) {
			url.queryParam("tipoEvento", tipoEvento);
		}
//		Set<EventoTitoloModel> eventoTitoloModelSet = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), new ParameterizedTypeReference<Set<EventoTitoloModel>>() {}).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<EventoTitoloModel[]> res = (ResponseEntity<EventoTitoloModel[]>) new RestTemplateBuilder()
				.url(url.build().toUriString()).returnClass(EventoTitoloModel[].class).oauth2Token(token)
				.httpMethod(HttpMethod.GET).build().executeRequest(false);

		Set<EventoTitoloModel> result = new HashSet<EventoTitoloModel>(Arrays.asList(res.getBody()));

		return result;
	}

	public String submitFile(WSO2Token token, MultipartFile file, String tipo, String idEvento, Long idPubblicazione,
			Integer w, Integer h, Boolean avoidTypeCheck, Long ordine, String jsonEventoAggiornato) throws IOException, RestException, HttpServerErrorException {
		String estensione = FilenameUtils.getExtension(file.getOriginalFilename());

		log.info(
				"Caricando file[ evento: " + idEvento + ", pubblicazione: " + idPubblicazione + ", dimensioni: "
						+ file.getSize() + ", tipo: " + tipo + ", estensione: " + estensione,
				" avoidTypeCheck: " + avoidTypeCheck, " w: " + w + ", h: " + h + " ]");
//
//		if (file.getSize() > 10000485760) {
//			throw new HttpServerErrorException(HttpStatus.PAYLOAD_TOO_LARGE);
//		} else
			if (avoidTypeCheck == null || !avoidTypeCheck.booleanValue()) {
			if (tipo.equals("immagine")) {
				
				
				if (estensione.equalsIgnoreCase("jpg") || estensione.equalsIgnoreCase("jpeg")
//						|| estensione.equalsIgnoreCase("tiff") || estensione.equalsIgnoreCase("tif")
						|| estensione.equalsIgnoreCase("png") || estensione.equalsIgnoreCase("gif")
//						|| estensione.equalsIgnoreCase("mp4") || estensione.equalsIgnoreCase("mp3")
						) {
					// Spento a seguito di richiesta del cliente
					/*
					 * BufferedImage bufferedImage = ImageIO.read(file.getInputStream()); int h =
					 * bufferedImage.getHeight(); int w = bufferedImage.getWidth(); if (w > 750 || h
					 * > 400) { return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE); }
					 */
					  Tika tika = new Tika();
					  String detectedType = tika.detect(file.getInputStream());
					List<String> acceptedType = new ArrayList<String>();
					acceptedType.add("image/jpeg");
					acceptedType.add("image/png");
					
					acceptedType.add("image/gif");
						  if(!acceptedType.contains(detectedType)) {
							  throw new HttpServerErrorException(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
						  }
						  if (file.getSize() >10485760) {
							  throw new HttpServerErrorException(HttpStatus.PAYLOAD_TOO_LARGE);
						  }
					  
					  
					  
					  
					  
					  
					  			
				} else {
					throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
				}
			} else {
				if (!(estensione.equalsIgnoreCase("pdf")  || 
						estensione.equalsIgnoreCase("jpg") || estensione.equalsIgnoreCase("jpeg")
//						|| estensione.equalsIgnoreCase("tiff") || estensione.equalsIgnoreCase("tif")
						|| estensione.equalsIgnoreCase("png") || estensione.equalsIgnoreCase("gif")
						|| estensione.equalsIgnoreCase("mp4") || estensione.equalsIgnoreCase("mp3"))
					) {
					throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
				}
				  Tika tika = new Tika();
				  String detectedType = tika.detect(file.getInputStream());
				  List<String> acceptedTypeImg = new ArrayList<String>();
					acceptedTypeImg.add("image/jpeg");
					acceptedTypeImg.add("image/png");
					
					acceptedTypeImg.add("image/gif");
					
					List<String> acceptedTypeVideo= new ArrayList<String>();
					acceptedTypeVideo.add("video/mp4");
					acceptedTypeVideo.add("video/quicktime");
					acceptedTypeVideo.add("audio/mp4");
					acceptedTypeVideo.add("application/mp4");
					
					List<String> acceptedTypePdf= new ArrayList<String>();
					acceptedTypePdf.add("application/pdf");
					
					List<String> acceptedTypeAudio= new ArrayList<String>();
					acceptedTypeAudio.add("audio/mpeg");

				  
				  
				  if(estensione.equalsIgnoreCase("mp4") )
				  {
					  if(!acceptedTypeVideo.contains(detectedType)) 
					  {
						  throw new HttpServerErrorException(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
					  }
					  if (file.getSize() >104857600) {
						  throw new HttpServerErrorException(HttpStatus.PAYLOAD_TOO_LARGE);
					  }
				  }
				  if(estensione.equalsIgnoreCase("mp3") )
				  {
					  if(!acceptedTypeAudio.contains(detectedType)) {
						  throw new HttpServerErrorException(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
					  }
					  if (file.getSize() >10485760) {
						  throw new HttpServerErrorException(HttpStatus.PAYLOAD_TOO_LARGE);
					  }
				  }
				  if(estensione.equalsIgnoreCase("jpg") || estensione.equalsIgnoreCase("jpeg")
						  || estensione.equalsIgnoreCase("png") || estensione.equalsIgnoreCase("gif")
						  ) {
					  
					  if(!acceptedTypeImg.contains(detectedType)) {
						  throw new HttpServerErrorException(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
					  }
					  if (file.getSize() >10485760) {
						  throw new HttpServerErrorException(HttpStatus.PAYLOAD_TOO_LARGE);
					  }
				  
					  
				  }
				  if(estensione.equalsIgnoreCase("pdf"))
				  if(!acceptedTypePdf.contains(detectedType)) {
					  throw new HttpServerErrorException(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
				  }
				  
			}
		}

		UriComponentsBuilder url = null;
		if (idPubblicazione == null) {
			url = UriComponentsBuilder
					.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/" + idEvento + "/file");
		} else {
			url = UriComponentsBuilder.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/" + idEvento
					+ "/pubblicazione/" + idPubblicazione + "/file");
			
			if (w != null && h != null) {
				url.queryParam("resizeWidth", w).queryParam("resizeHeight", h);
			}
		}
		MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
		parameters.add("file", file.getResource());
		parameters.add("ordine" , ordine);
		parameters.add("json", jsonEventoAggiornato);
		parameters.add("tipo", tipo);
		
		if (token.isValid()) {
			log.info("Token valido");
		} else {
			log.info("Token non valido: Aggiorno token per la submit del file.");

			try {
				token = RefreshOauth2TokenService.refreshToken(token);
			} catch (Exception e) {
				log.error("Errore durante l'aggiornamento del token",e);
				throw e;
			}
		}

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder().body(parameters)
				.contentType(MediaType.MULTIPART_FORM_DATA).url(url.build().toUriString()).returnClass(String.class)
				.oauth2Token(token).httpMethod(HttpMethod.POST).build().executeRequest(false);

		return res.getBody();
	}

	public byte[] getFile(WSO2Token token, String filePath, Long idPubblicazione) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + filePath)
				.queryParam("idPubblicazione", idPubblicazione);

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<byte[]> res = (ResponseEntity<byte[]>) new RestTemplateBuilder().url(url.build().toUriString())
				.returnClass(byte[].class).oauth2Token(token).httpMethod(HttpMethod.GET).build().executeRequest(false);

		return res.getBody();
	}

	public Long saveVipScheda(WSO2Token token, String tipoUtente, Long idEvento, VIPSchedaModel vipSchedaModel,
			String noteAggiuntive, String statoPubblicazione) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(
						sigeaPropertiesSettings.getUrlSIGEC() + "eventi/" + idEvento + "/redazione/VIP/" + tipoUtente)
				.queryParam("noteAggiuntive", noteAggiuntive).queryParam("statoPubblicazione", statoPubblicazione);

//		if ((vipSchedaModel.getImmagineSetAggiunto() == null || vipSchedaModel.getImmagineSetAggiunto().isEmpty())
//				&& !vipSchedaModel.getImmagineSet().stream().anyMatch(i -> i.getOrdine() != null)) {
//			vipSchedaModel.getImmagineSet().stream().findFirst().get().setOrdine(1L);
//		}
		for (VIPSchedaImmagineModel immagine : vipSchedaModel.getImmagineSet()) {
			if (immagine.getOrdine() == null) {
				immagine.setOrdine(0L);
			}
		}
		for (VIPSchedaImmagineModel immagine : vipSchedaModel.getImmagineSetAggiunto()) {
			if (immagine.getOrdine() == null) {
				immagine.setOrdine(0L);
			}
		}

		for (VIPSchedaDocumentoModel documento : vipSchedaModel.getDocumentoSet()) {
			if (documento.getOrdineNumerico() == null) {
				documento.setOrdineNumerico(0L);
			}
		}
		for (VIPSchedaDocumentoModel documento : vipSchedaModel.getDocumentoSetAggiunto()) {
			if (documento.getOrdineNumerico() == null) {
				documento.setOrdineNumerico(0L);
			}
		}

//		if (vipSchedaModel.getTipologieAttivitaSet() == null || vipSchedaModel.getTipologieAttivitaSet().isEmpty()) {
//			Set<String> tipologie = new HashSet<String>();
//			Set<TipologiaAttivitaModel> tipologiePossibili = ontologiaService.getTipologieAttivita(token);
//			tipologie.add(tipologiePossibili.stream().findAny().get().getTipologia());
//			vipSchedaModel.setTipologieAttivitaSet(tipologie);
//		}

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<Long> res = (ResponseEntity<Long>) new RestTemplateBuilder().body(vipSchedaModel)
				.url(url.build().toUriString()).returnClass(Long.class).oauth2Token(token).httpMethod(HttpMethod.PUT)
				.build().executeRequest(false);

		return res.getBody();
	}

	public Long recoverVipScheda(WSO2Token token, String tipoUtente, Long idEvento) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/"
				+ idEvento + "/redazione/VIP/" + tipoUtente + "/recupero");
//		Long idPubblicazione = restTemplate.exchange(url.build().toUriString(), HttpMethod.PUT,new HttpEntity<String>(new HttpHeaders()), Long.class).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<Long> res = (ResponseEntity<Long>) new RestTemplateBuilder().url(url.build().toUriString())
				.returnClass(Long.class).oauth2Token(token).httpMethod(HttpMethod.PUT).build().executeRequest(false);

		return res.getBody();
	}

	public Long deleteVipScheda(WSO2Token token, String tipoUtente, Long idEvento) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/"
				+ idEvento + "/redazione/VIP/" + tipoUtente + "/revocapubblicazione");
//		Long idPubblicazione = restTemplate.exchange(url.build().toUriString(), HttpMethod.DELETE,new HttpEntity<String>(new HttpHeaders()), Long.class).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<Long> res = (ResponseEntity<Long>) new RestTemplateBuilder().url(url.build().toUriString())
				.returnClass(Long.class).oauth2Token(token).httpMethod(HttpMethod.DELETE).build().executeRequest(false);

		return res.getBody();
	}

	public PubblicazioneModel getVipScheda(WSO2Token token, Long idEvento)
			throws JsonProcessingException, RestException {
		EventoModel eventoModel = getEvento(token, idEvento).getBody();
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/" + idEvento + "/redazione/VIP");
		PubblicazioneModel pubblicazioneModel = null;
		VIPSchedaModel vipSchedaModel = new VIPSchedaModel();
		ObjectMapper mapper = new ObjectMapper();
		try {
//			pubblicazioneModel = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET,new HttpEntity<String>(new HttpHeaders()), new ParameterizedTypeReference<PubblicazioneModel>() {}).getBody();

			@SuppressWarnings({ "unchecked" })
			ResponseEntity<PubblicazioneModel> res = (ResponseEntity<PubblicazioneModel>) new RestTemplateBuilder()
					.url(url.build().toUriString()).returnClass(PubblicazioneModel.class).oauth2Token(token)
					.httpMethod(HttpMethod.GET).build().executeRequest(false);

			pubblicazioneModel = res.getBody();

		} catch (HttpClientErrorException e) {
			e.getStatusCode().compareTo(HttpStatus.NOT_FOUND);
		}
		if (pubblicazioneModel == null) {
			pubblicazioneModel = new PubblicazioneModel();
			Set<VIPSchedaImmagineModel> immagineSet = new HashSet<>();
			
			
			for (ImmagineModel immagine : eventoModel.getImmagineSet()) {
				VIPSchedaImmagineModel vipImmagine = new VIPSchedaImmagineModel();
				vipImmagine.setImmagineId(immagine.getImmagineId());
				vipImmagine.setNomeOriginale(immagine.getNomeOriginale());
				vipImmagine.setDidascaliaMulti(immagine.getDidascaliaMulti());
				vipImmagine.setCredits(immagine.getCredits());
				vipImmagine.setEstensione(immagine.getEstensione());
				vipImmagine.setDimensione(immagine.getDimensione());
				vipImmagine.setDaPubblicare(true);
				vipImmagine.setOrdine(immagine.getOrdine());
				immagineSet.add(vipImmagine);
			}
			vipSchedaModel.setImmagineSet(immagineSet);

			Set<VIPSchedaDocumentoModel> documentoSet = new HashSet<>();
			for (DocumentoEventoModel documento : eventoModel.getDocumentoSet()) {
				VIPSchedaDocumentoModel vipDocumento = new VIPSchedaDocumentoModel();
				vipDocumento.setDocumentoId(documento.getDocumentoId());
				vipDocumento.setNomeOriginale(documento.getNomeOriginale());
				vipDocumento.setEstensione(documento.getEstensione());
				vipDocumento.setDimensione(documento.getDimensione());
				vipDocumento.setTitoloMulti(documento.getTitoloMulti());
				vipDocumento.setDaPubblicare(true);
				vipDocumento.setOrdineNumerico(documento.getOrdine());
				documentoSet.add(vipDocumento);
			}
			vipSchedaModel.setDocumentoSet(documentoSet);

			Set<VIPSchedaLinkModel> linkSet = new HashSet<>();
			for (LinkModel link : eventoModel.getLinkSet()) {
				VIPSchedaLinkModel vipLink = new VIPSchedaLinkModel();
				vipLink.setLinkId(link.getLinkId());
				vipLink.setCredits(link.getCredits());
				vipLink.setDidascaliaMulti(link.getDidascaliaMulti());
				vipLink.setLink(link.getLink());
				vipLink.setTitoloMulti(link.getTitoloMulti());
				vipLink.setDaPubblicare(true);
				vipLink.setOrdineNumerico(link.getOrdine());
				linkSet.add(vipLink);
			}
			vipSchedaModel.setLinkSet(linkSet);

			// Gestione relazioni per scheda non presente sula base dati
			Set<VIPSchedaRelazioneModel> relazioniSet = new HashSet<>();
			for (RelazioneModel relazione : eventoModel.getRelazioneSet()) {

//				if("VALIDATO".equals(relazione.getStatoEventoAssociato())) {

				VIPSchedaRelazioneModel vipAssociazione = new VIPSchedaRelazioneModel();

				vipAssociazione.setRelazioneId(relazione.getRelazioneId());
				vipAssociazione.setEventoRelazionatoId(relazione.getEventoRelazionatoId());
				vipAssociazione.setTipoEventoAssociato(relazione.getTipoEventoAssociato());
				vipAssociazione.setTipoRelazione(relazione.getTipoRelazione());
				vipAssociazione.setTitolo(relazione.getTitolo());
				vipAssociazione.setMantieni(true);

				vipAssociazione.setComune(relazione.getComune());
				vipAssociazione.setPeriodo(relazione.getPeriodo());
				vipAssociazione.setStatoEventoAssociato(relazione.getStatoEventoAssociato());
				vipAssociazione.setSchedePubblicazione(relazione.getSchedePubblicazione());

				relazioniSet.add(vipAssociazione);
//				}	
			}
			vipSchedaModel.setRelazioneSet(relazioniSet);

			if (eventoModel.getIdMIBACT() != null && eventoModel.getTipologiaMIBACT() != null) {
				vipSchedaModel.getTipologieMIBACT().put(eventoModel.getIdMIBACT(), eventoModel.getTipologiaMIBACT());
			}
		
			
			Map<String, String> abstractMulti= new HashMap<String, String>();
			Map<String, String> snippetMulti= new HashMap<String, String>();
			Map<String, String> descrizioneMulti= new HashMap<String, String>();
			
			for (Map.Entry<String, String> entry : eventoModel.getDatiGenerali().getAbstractDescrMulti().entrySet()) {
				String value= entry.getValue().replaceAll("\r\n","<br>");
				abstractMulti.put(entry.getKey(),value);
			}
			for (Map.Entry<String, String> entry : eventoModel.getDatiGenerali().getSnippetMulti().entrySet()) {
				String value= entry.getValue().replaceAll("\r\n","<br>");
				snippetMulti.put(entry.getKey(),value);
			}
			for (Map.Entry<String, String> entry : eventoModel.getDatiGenerali().getDescrizioneMulti().entrySet()) {
				String value= entry.getValue().replaceAll("\r\n","<br>");
				descrizioneMulti.put(entry.getKey(),value);
			}
			
			vipSchedaModel.setTitoloMulti(eventoModel.getDatiGenerali().getTitoloMulti());
			vipSchedaModel.setAbstractDescrMulti(abstractMulti);
			vipSchedaModel.setSnippetMulti(snippetMulti);
			vipSchedaModel.setDescrizioneMulti(descrizioneMulti);

			JsonNode node = mapper.valueToTree(vipSchedaModel);
			pubblicazioneModel.setEventoModel(eventoModel);
			pubblicazioneModel.setGenericMetadata(node);
		} else if (pubblicazioneModel!= null && pubblicazioneModel.getGenericMetadata() != null) { // AGGIORNAMENTO PUBBLICAZIONE ESISTENTE

			JsonNode node = pubblicazioneModel.getGenericMetadata();
			if (node != null) {
				vipSchedaModel = mapper.treeToValue(node, VIPSchedaModel.class);
			}
			
		
			
	
			for (ImmagineModel immagine : eventoModel.getImmagineSet()) {

				VIPSchedaImmagineModel tmpVipImmagine = vipSchedaModel.getImmagineSet().stream()
						.filter(img -> img.getImmagineId().longValue() == immagine.getImmagineId().longValue())
						.findFirst().orElse(null);

				if (tmpVipImmagine == null) {
					VIPSchedaImmagineModel vipImmagine = new VIPSchedaImmagineModel();
					vipImmagine.setImmagineId(immagine.getImmagineId());
					vipImmagine.setNomeOriginale(immagine.getNomeOriginale());
					vipImmagine.setDidascaliaMulti(immagine.getDidascaliaMulti());
					vipImmagine.setCredits(immagine.getCredits());
					vipImmagine.setEstensione(immagine.getEstensione());
					vipImmagine.setDimensione(immagine.getDimensione());
					if(!(vipImmagine.getOrdine()!=null && vipImmagine.getOrdine().longValue()>0L))
					vipImmagine.setOrdine(immagine.getOrdine());
					vipImmagine.setDaPubblicare(true);
					vipSchedaModel.getImmagineSet().add(vipImmagine);
				} else { // AGGIORNO IMMAGINI DEL NUCLEO
					tmpVipImmagine.setImmagineId(immagine.getImmagineId());
					tmpVipImmagine.setNomeOriginale(immagine.getNomeOriginale());
					tmpVipImmagine.setDidascaliaMulti(immagine.getDidascaliaMulti());
					tmpVipImmagine.setCredits(immagine.getCredits());
					tmpVipImmagine.setEstensione(immagine.getEstensione());
					tmpVipImmagine.setDimensione(immagine.getDimensione());
					if(!(tmpVipImmagine.getOrdine()!=null && tmpVipImmagine.getOrdine().longValue()>0L))
					tmpVipImmagine.setOrdine(immagine.getOrdine());
							}
			}
			Set<VIPSchedaImmagineModel> vipImmagineSetRem = new HashSet<>();
			for (VIPSchedaImmagineModel vipImmagine : vipSchedaModel.getImmagineSet()) {
				if (!eventoModel.getImmagineSet().stream()
						.anyMatch(img -> img.getImmagineId().longValue() == vipImmagine.getImmagineId().longValue())) {
					vipImmagineSetRem.add(vipImmagine);
				}
			}
			vipSchedaModel.getImmagineSet().removeAll(vipImmagineSetRem);
	
			for (DocumentoEventoModel documento : eventoModel.getDocumentoSet()) {

				VIPSchedaDocumentoModel tmpVipDocumento = vipSchedaModel.getDocumentoSet().stream()
						.filter(doc -> doc.getDocumentoId().longValue() == documento.getDocumentoId().longValue())
						.findFirst().orElse(null);

				if (tmpVipDocumento == null) {
					VIPSchedaDocumentoModel vipDocumento = new VIPSchedaDocumentoModel();
					vipDocumento.setDocumentoId(documento.getDocumentoId());
					vipDocumento.setNomeOriginale(documento.getNomeOriginale());
					vipDocumento.setEstensione(documento.getEstensione());
					vipDocumento.setDimensione(documento.getDimensione());
					vipDocumento.setTitoloMulti(documento.getTitoloMulti());
					vipDocumento.setDaPubblicare(true);
					if(!(vipDocumento.getOrdineNumerico()!=null && vipDocumento.getOrdineNumerico().longValue()!=0))
						vipDocumento.setOrdineNumerico(documento.getOrdine());
					vipSchedaModel.getDocumentoSet().add(vipDocumento);
				} else { // AGGIORNO DOCUMENTO DEL NUCLEO
					tmpVipDocumento.setDocumentoId(documento.getDocumentoId());
					tmpVipDocumento.setNomeOriginale(documento.getNomeOriginale());
					tmpVipDocumento.setEstensione(documento.getEstensione());
					tmpVipDocumento.setDimensione(documento.getDimensione());
					tmpVipDocumento.setTitoloMulti(documento.getTitoloMulti());
					if(!(tmpVipDocumento.getOrdineNumerico()!=null && tmpVipDocumento.getOrdineNumerico().longValue()!=0))
						tmpVipDocumento.setOrdineNumerico(documento.getOrdine());
				}
			}
			Set<VIPSchedaDocumentoModel> vipDocumentoSetRem = new HashSet<>();
			for (VIPSchedaDocumentoModel vipDocumento : vipSchedaModel.getDocumentoSet()) {
				if (!eventoModel.getDocumentoSet().stream().anyMatch(
						doc -> doc.getDocumentoId().longValue() == vipDocumento.getDocumentoId().longValue())) {
					vipDocumentoSetRem.add(vipDocumento);
				}
			}
			vipSchedaModel.getDocumentoSet().removeAll(vipDocumentoSetRem);

			for (LinkModel link : eventoModel.getLinkSet()) {

				VIPSchedaLinkModel tmpVipLink = vipSchedaModel.getLinkSet().stream()
						.filter(lk -> lk.getLinkId().longValue() == link.getLinkId().longValue()).findFirst()
						.orElse(null);

				if (tmpVipLink == null) {
					VIPSchedaLinkModel vipLink = new VIPSchedaLinkModel();
					vipLink.setLinkId(link.getLinkId());
					vipLink.setTitoloMulti(link.getTitoloMulti());
					vipLink.setDidascaliaMulti(link.getDidascaliaMulti());
					vipLink.setCredits(link.getCredits());
					vipLink.setLink(link.getLink());
					vipLink.setDaPubblicare(true);
					if(!(vipLink.getOrdineNumerico()!=null && vipLink.getOrdineNumerico().longValue()>0L))
					vipLink.setOrdineNumerico(link.getOrdine());
					vipSchedaModel.getLinkSet().add(vipLink);
				} else { // AGGIORNO LINK DEL NUCLEO
					tmpVipLink.setLinkId(link.getLinkId());
					tmpVipLink.setTitoloMulti(link.getTitoloMulti());
					tmpVipLink.setDidascaliaMulti(link.getDidascaliaMulti());
					tmpVipLink.setCredits(link.getCredits());
					tmpVipLink.setLink(link.getLink());
					if(!(tmpVipLink.getOrdineNumerico()!=null && tmpVipLink.getOrdineNumerico().longValue()>0L))
					tmpVipLink.setOrdineNumerico(link.getOrdine());
				}
			}
			Set<VIPSchedaLinkModel> vipLinkSetRem = new HashSet<>();
			for (VIPSchedaLinkModel vipLink : vipSchedaModel.getLinkSet()) {
				if (!eventoModel.getLinkSet().stream()
						.anyMatch(lk -> lk.getLinkId().longValue() == vipLink.getLinkId().longValue())) {
					vipLinkSetRem.add(vipLink);
				}
			}
			vipSchedaModel.getLinkSet().removeAll(vipLinkSetRem);

			// Gestione relazioni Nucleo
			List<VIPSchedaRelazioneModel> listVipAssociazioneToAdd = new ArrayList<VIPSchedaRelazioneModel>();

			Set<RelazioneModel> relazioniNucleo = eventoModel.getRelazioneSet().stream()
					.filter(x -> StringUtils.isEmpty(x.getRedazioneId())).collect(Collectors.toSet());
			Set<RelazioneModel> relazioniRedazione = eventoModel.getRelazioneSet().stream()
					.filter(x -> StringUtils.equalsAnyIgnoreCase(x.getRedazioneId(), "VIP"))
					.collect(Collectors.toSet());

			for (RelazioneModel relazione : relazioniNucleo) {

//				if("VALIDATO".equals(relazione.getStatoEventoAssociato())) {

				VIPSchedaRelazioneModel tmp = vipSchedaModel.getRelazioneSet().stream()
						.filter(x -> x.getRelazioneId().longValue() == relazione.getRelazioneId().longValue())
						.findFirst().orElse(null);

				if (tmp != null) {// Aggiorno associazione

					if (tmp.getRelazioneId().longValue() == relazione.getRelazioneId().longValue()) {
						tmp.setRelazioneId(relazione.getRelazioneId());
						tmp.setEventoRelazionatoId(relazione.getEventoRelazionatoId());
						tmp.setTipoEventoAssociato(relazione.getTipoEventoAssociato());
						tmp.setTipoRelazione(relazione.getTipoRelazione());
						tmp.setTitolo(relazione.getTitolo());
						tmp.setComune(relazione.getComune());
						tmp.setPeriodo(relazione.getPeriodo());
						tmp.setStatoEventoAssociato(relazione.getStatoEventoAssociato());
						tmp.setSchedePubblicazione(relazione.getSchedePubblicazione());
					}

				} else {// Aggiungo nuova associazione

					VIPSchedaRelazioneModel vipAssociazione = new VIPSchedaRelazioneModel();

					vipAssociazione.setRelazioneId(relazione.getRelazioneId());
					vipAssociazione.setEventoRelazionatoId(relazione.getEventoRelazionatoId());
					vipAssociazione.setTipoEventoAssociato(relazione.getTipoEventoAssociato());
					vipAssociazione.setTipoRelazione(relazione.getTipoRelazione());
					vipAssociazione.setTitolo(relazione.getTitolo());
					vipAssociazione.setComune(relazione.getComune());
					vipAssociazione.setPeriodo(relazione.getPeriodo());
					vipAssociazione.setStatoEventoAssociato(relazione.getStatoEventoAssociato());
					vipAssociazione.setSchedePubblicazione(relazione.getSchedePubblicazione());
					vipAssociazione.setMantieni(true);

					listVipAssociazioneToAdd.add(vipAssociazione);
				}

//				} else {
//					vipSchedaModel.getRelazioneSet().removeIf(x -> x.getEventoRelazionatoId().equals(relazione.getEventoRelazionatoId()));
//				}
			}

			vipSchedaModel.getRelazioneSet().addAll(listVipAssociazioneToAdd);

			// Rimuovo le relazioni del nucleo non piu' presenti dal generic metadata
			vipSchedaModel.getRelazioneSet().removeIf(x -> !relazioniNucleo.stream()
					.anyMatch(rel -> rel.getRelazioneId().longValue() == x.getRelazioneId().longValue()));

			// Gestione relazioni aggiuntive
			List<VIPSchedaRelazioneModel> listVipAssociazioneAggToAdd = new ArrayList<VIPSchedaRelazioneModel>();

			for (RelazioneModel relazione : relazioniRedazione) {

				VIPSchedaRelazioneModel tmp = vipSchedaModel.getRelazioneSetAggiunto().stream()
						.filter(x -> x.getRelazioneId().longValue() == relazione.getRelazioneId().longValue())
						.findFirst().orElse(null);

				if (tmp != null) {// Aggiorno associazione

					if (tmp.getRelazioneId().longValue() == relazione.getRelazioneId().longValue()) {
						tmp.setRelazioneId(relazione.getRelazioneId());
						tmp.setEventoRelazionatoId(relazione.getEventoRelazionatoId());
						tmp.setTipoEventoAssociato(relazione.getTipoEventoAssociato());
						tmp.setTipoRelazione(relazione.getTipoRelazione());
						tmp.setTitolo(relazione.getTitolo());
						tmp.setComune(relazione.getComune());
						tmp.setPeriodo(relazione.getPeriodo());
						tmp.setStatoEventoAssociato(relazione.getStatoEventoAssociato());
						tmp.setSchedePubblicazione(relazione.getSchedePubblicazione());
						tmp.setRedazioneId(relazione.getRedazioneId());
					}

				} else {// Aggiungo nuova associazione

					VIPSchedaRelazioneModel vipAssociazione = new VIPSchedaRelazioneModel();

					vipAssociazione.setRelazioneId(relazione.getRelazioneId());
					vipAssociazione.setEventoRelazionatoId(relazione.getEventoRelazionatoId());
					vipAssociazione.setTipoEventoAssociato(relazione.getTipoEventoAssociato());
					vipAssociazione.setTipoRelazione(relazione.getTipoRelazione());
					vipAssociazione.setTitolo(relazione.getTitolo());
					vipAssociazione.setComune(relazione.getComune());
					vipAssociazione.setPeriodo(relazione.getPeriodo());
					vipAssociazione.setStatoEventoAssociato(relazione.getStatoEventoAssociato());
					vipAssociazione.setSchedePubblicazione(relazione.getSchedePubblicazione());
					vipAssociazione.setRedazioneId(relazione.getRedazioneId());

					listVipAssociazioneAggToAdd.add(vipAssociazione);
				}

			}

			vipSchedaModel.getRelazioneSetAggiunto().addAll(listVipAssociazioneAggToAdd);

			// Rimuovo le relazioni del nucleo non piu' presenti dal generic metadata
			vipSchedaModel.getRelazioneSetAggiunto().removeIf(x -> !relazioniRedazione.stream()
					.anyMatch(rel -> rel.getRelazioneId().longValue() == x.getRelazioneId().longValue()));

			node = mapper.valueToTree(vipSchedaModel);
			pubblicazioneModel.setGenericMetadata(node);
		}else
		{
			
			Set<VIPSchedaImmagineModel> immagineSet = new HashSet<>();
			
			Set<ImmagineModel> immaginiEvento = eventoModel.getImmagineSet();
			
			List<ImmagineModel> sortedImage = new ArrayList();
			
			sortedImage.addAll(immaginiEvento);
			
			sortedImage.sort(Comparator.comparing(ImmagineModel::getOrdine));
			
			for (ImmagineModel immagine : sortedImage) {
				VIPSchedaImmagineModel vipImmagine = new VIPSchedaImmagineModel();
				vipImmagine.setImmagineId(immagine.getImmagineId());
				vipImmagine.setNomeOriginale(immagine.getNomeOriginale());
				vipImmagine.setDidascaliaMulti(immagine.getDidascaliaMulti());
				vipImmagine.setCredits(immagine.getCredits());
				vipImmagine.setEstensione(immagine.getEstensione());
				vipImmagine.setDimensione(immagine.getDimensione());
				vipImmagine.setDaPubblicare(true);
				vipImmagine.setOrdine(immagine.getOrdine());
				immagineSet.add(vipImmagine);
			}
			vipSchedaModel.setImmagineSet(immagineSet);

			Set<VIPSchedaDocumentoModel> documentoSet = new HashSet<>();
			for (DocumentoEventoModel documento : eventoModel.getDocumentoSet()) {
				VIPSchedaDocumentoModel vipDocumento = new VIPSchedaDocumentoModel();
				vipDocumento.setDocumentoId(documento.getDocumentoId());
				vipDocumento.setNomeOriginale(documento.getNomeOriginale());
				vipDocumento.setEstensione(documento.getEstensione());
				vipDocumento.setDimensione(documento.getDimensione());
				vipDocumento.setTitoloMulti(documento.getTitoloMulti());
				vipDocumento.setDaPubblicare(true);
				vipDocumento.setOrdineNumerico(documento.getOrdine());
				documentoSet.add(vipDocumento);
			}
			vipSchedaModel.setDocumentoSet(documentoSet);

			Set<VIPSchedaLinkModel> linkSet = new HashSet<>();
			for (LinkModel link : eventoModel.getLinkSet()) {
				VIPSchedaLinkModel vipLink = new VIPSchedaLinkModel();
				vipLink.setLinkId(link.getLinkId());
				vipLink.setCredits(link.getCredits());
				vipLink.setDidascaliaMulti(link.getDidascaliaMulti());
				vipLink.setLink(link.getLink());
				vipLink.setTitoloMulti(link.getTitoloMulti());
				vipLink.setDaPubblicare(true);
				vipLink.setOrdineNumerico(link.getOrdine());
				linkSet.add(vipLink);
			}
			vipSchedaModel.setLinkSet(linkSet);

			// Gestione relazioni per scheda non presente sula base dati
			Set<VIPSchedaRelazioneModel> relazioniSet = new HashSet<>();
			for (RelazioneModel relazione : eventoModel.getRelazioneSet()) {

//				if("VALIDATO".equals(relazione.getStatoEventoAssociato())) {

				VIPSchedaRelazioneModel vipAssociazione = new VIPSchedaRelazioneModel();

				vipAssociazione.setRelazioneId(relazione.getRelazioneId());
				vipAssociazione.setEventoRelazionatoId(relazione.getEventoRelazionatoId());
				vipAssociazione.setTipoEventoAssociato(relazione.getTipoEventoAssociato());
				vipAssociazione.setTipoRelazione(relazione.getTipoRelazione());
				vipAssociazione.setTitolo(relazione.getTitolo());
				vipAssociazione.setMantieni(true);

				vipAssociazione.setComune(relazione.getComune());
				vipAssociazione.setPeriodo(relazione.getPeriodo());
				vipAssociazione.setStatoEventoAssociato(relazione.getStatoEventoAssociato());
				vipAssociazione.setSchedePubblicazione(relazione.getSchedePubblicazione());

				relazioniSet.add(vipAssociazione);
//				}	
			}
			vipSchedaModel.setRelazioneSet(relazioniSet);

			if (eventoModel.getIdMIBACT() != null && eventoModel.getTipologiaMIBACT() != null) {
				vipSchedaModel.getTipologieMIBACT().put(eventoModel.getIdMIBACT(), eventoModel.getTipologiaMIBACT());
			}
		
			
			Map<String, String> abstractMulti= new HashMap<String, String>();
			Map<String, String> snippetMulti= new HashMap<String, String>();
			Map<String, String> descrizioneMulti= new HashMap<String, String>();
			
			for (Map.Entry<String, String> entry : eventoModel.getDatiGenerali().getAbstractDescrMulti().entrySet()) {
				String value= entry.getValue().replaceAll("\r\n","<br>");
				abstractMulti.put(entry.getKey(),value);
			}
			for (Map.Entry<String, String> entry : eventoModel.getDatiGenerali().getSnippetMulti().entrySet()) {
				String value= entry.getValue().replaceAll("\r\n","<br>");
				snippetMulti.put(entry.getKey(),value);
			}
			for (Map.Entry<String, String> entry : eventoModel.getDatiGenerali().getDescrizioneMulti().entrySet()) {
				String value= entry.getValue().replaceAll("\r\n","<br>");
				descrizioneMulti.put(entry.getKey(),value);
			}
			
			vipSchedaModel.setTitoloMulti(eventoModel.getDatiGenerali().getTitoloMulti());
			vipSchedaModel.setAbstractDescrMulti(abstractMulti);
			vipSchedaModel.setSnippetMulti(snippetMulti);
			vipSchedaModel.setDescrizioneMulti(descrizioneMulti);

			JsonNode node = mapper.valueToTree(vipSchedaModel);
			pubblicazioneModel.setEventoModel(eventoModel);
			pubblicazioneModel.setGenericMetadata(node);
		}
		return pubblicazioneModel;
	}

	public Set<PubblicazioneModel> getPubblicazioniList(WSO2Token token, Long eventoId) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/" + eventoId + "/pubblicazioni");
//		Set<PubblicazioneModel> pubblicazione = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET,new HttpEntity<String>(new HttpHeaders()), new ParameterizedTypeReference<Set<PubblicazioneModel>>() {}).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<PubblicazioneModel[]> res = (ResponseEntity<PubblicazioneModel[]>) new RestTemplateBuilder()
				.url(url.build().toUriString()).returnClass(PubblicazioneModel[].class).oauth2Token(token)
				.httpMethod(HttpMethod.GET).build().executeRequest(false);

		Set<PubblicazioneModel> result = new HashSet<PubblicazioneModel>(Arrays.asList(res.getBody()));

		return result;
	}
	
	public Set<LogEventoModel> getLogEventiList(WSO2Token token, Long idEvento) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/" + idEvento + "/logEventi");

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<LogEventoModel[]> res = (ResponseEntity<LogEventoModel[]>) new RestTemplateBuilder()
				.url(url.build().toUriString()).returnClass(LogEventoModel[].class).oauth2Token(token)
				.httpMethod(HttpMethod.GET).build().executeRequest(false);

		Set<LogEventoModel> result = new HashSet<LogEventoModel>(Arrays.asList(res.getBody()));

		return result;
	}

	public void deleteEvento(WSO2Token token, Long idEvento) throws RestException {
//		try {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/" + idEvento);
//			restTemplate.exchange(url.build().toUriString(), HttpMethod.DELETE,new HttpEntity<String>(new HttpHeaders()), String.class);

		@SuppressWarnings({ "unchecked", "unused" })
		ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder().url(url.build().toUriString())
				.returnClass(String.class).oauth2Token(token).httpMethod(HttpMethod.DELETE).build()
				.executeRequest(false);

//			return new ResponseEntity<>(HttpStatus.OK);
//		} catch (HttpClientErrorException e) {
//			if (e.getStatusCode().equals(HttpStatus.CONFLICT)) {
//				return new ResponseEntity<>(HttpStatus.CONFLICT);
//			} else {
//				throw e;
//			}
//		}

	}

	public Long saveB2BScheda(WSO2Token token, String tipoUtente, Long idEvento, B2BSchedaModel b2bSchedaModel,
			String noteAggiuntive, String statoPubblicazione) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(
						sigeaPropertiesSettings.getUrlSIGEC() + "eventi/" + idEvento + "/redazione/B2B/" + tipoUtente)
				.queryParam("noteAggiuntive", noteAggiuntive).queryParam("statoPubblicazione", statoPubblicazione);
//		Long idPubblicazione = restTemplate.exchange(url.build().toUriString(), HttpMethod.PUT,new HttpEntity<B2BSchedaModel>(b2bSchedaModel, new HttpHeaders()), Long.class).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<Long> res = (ResponseEntity<Long>) new RestTemplateBuilder().body(b2bSchedaModel)
				.url(url.build().toUriString()).returnClass(Long.class).oauth2Token(token).httpMethod(HttpMethod.PUT)
				.build().executeRequest(false);

		return res.getBody();
	}

	public Long recoverB2BScheda(WSO2Token token, String tipoUtente, Long idEvento) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/"
				+ idEvento + "/redazione/B2B/" + tipoUtente + "/recupero");
//		Long idPubblicazione = restTemplate.exchange(url.build().toUriString(), HttpMethod.PUT,new HttpEntity<String>(new HttpHeaders()), Long.class).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<Long> res = (ResponseEntity<Long>) new RestTemplateBuilder().url(url.build().toUriString())
				.returnClass(Long.class).oauth2Token(token).httpMethod(HttpMethod.PUT).build().executeRequest(false);

		return res.getBody();
	}

	public Long deleteB2BScheda(WSO2Token token, String tipoUtente, Long idEvento) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/"
				+ idEvento + "/redazione/B2B/" + tipoUtente + "/revocapubblicazione");
//		Long idPubblicazione = restTemplate.exchange(url.build().toUriString(), HttpMethod.DELETE,new HttpEntity<String>(new HttpHeaders()), Long.class).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<Long> res = (ResponseEntity<Long>) new RestTemplateBuilder().url(url.build().toUriString())
				.returnClass(Long.class).oauth2Token(token).httpMethod(HttpMethod.PUT).build().executeRequest(false);

		return res.getBody();
	}

	public PubblicazioneModel getB2BScheda(WSO2Token token, Long idEvento)
			throws JsonProcessingException, RestException {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/" + idEvento + "/redazione/B2B");
		PubblicazioneModel pubblicazioneModel = null;
		try {
//			pubblicazioneModel = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET,new HttpEntity<String>(new HttpHeaders()), new ParameterizedTypeReference<PubblicazioneModel>() {}).getBody();

			@SuppressWarnings({ "unchecked" })
			ResponseEntity<PubblicazioneModel> res = (ResponseEntity<PubblicazioneModel>) new RestTemplateBuilder()
					.url(url.build().toUriString()).returnClass(PubblicazioneModel.class).oauth2Token(token)
					.httpMethod(HttpMethod.GET).build().executeRequest(false);

			pubblicazioneModel = res.getBody();

		} catch (HttpClientErrorException e) {
			e.getStatusCode().compareTo(HttpStatus.NOT_FOUND);
		}
		if (pubblicazioneModel == null) {
			pubblicazioneModel = new PubblicazioneModel();
			B2BSchedaModel b2bSchedaModel = new B2BSchedaModel();
			saveB2BScheda(token, "REDVALCAP-B2B", idEvento, b2bSchedaModel, null, "BOZZA");
			pubblicazioneModel = getB2BScheda(token, idEvento);
		} else {
			JsonNode node = pubblicazioneModel.getGenericMetadata();
			pubblicazioneModel.setGenericMetadata(node);
		}
		return pubblicazioneModel;
	}

	public Long saveB2BDMSScheda(WSO2Token token, String tipoUtente, Long idEvento, B2BDMSSchedaModel b2bdmsSchedaModel,
			String noteAggiuntive, String statoPubblicazione) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/" + idEvento + "/redazione/B2BDMS/"
						+ tipoUtente)
				.queryParam("noteAggiuntive", noteAggiuntive).queryParam("statoPubblicazione", statoPubblicazione);
//		Long idPubblicazione = restTemplate.exchange(url.build().toUriString(), HttpMethod.PUT,new HttpEntity<B2BDMSSchedaModel>(b2bdmsSchedaModel, new HttpHeaders()), Long.class).getBody();
		@SuppressWarnings({ "unchecked" })
		ResponseEntity<Long> res = (ResponseEntity<Long>) new RestTemplateBuilder().body(b2bdmsSchedaModel)
				.url(url.build().toUriString()).returnClass(Long.class).oauth2Token(token).httpMethod(HttpMethod.PUT)
				.build().executeRequest(false);

		return res.getBody();
	}

	public Long recoverB2BDMSScheda(WSO2Token token, String tipoUtente, Long idEvento) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/"
				+ idEvento + "/redazione/B2BDMS/" + tipoUtente + "/recupero");
//		Long idPubblicazione = restTemplate.exchange(url.build().toUriString(), HttpMethod.PUT,new HttpEntity<String>(new HttpHeaders()), Long.class).getBody();
		@SuppressWarnings({ "unchecked" })
		ResponseEntity<Long> res = (ResponseEntity<Long>) new RestTemplateBuilder().url(url.build().toUriString())
				.returnClass(Long.class).oauth2Token(token).httpMethod(HttpMethod.PUT).build().executeRequest(false);

		return res.getBody();
	}

	public Long deleteB2BDMSScheda(WSO2Token token, String tipoUtente, Long idEvento) throws RestException {
		UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/"
				+ idEvento + "/redazione/B2BDMS/" + tipoUtente + "/revocapubblicazione");
//		Long idPubblicazione = restTemplate.exchange(url.build().toUriString(), HttpMethod.DELETE,new HttpEntity<String>(new HttpHeaders()), Long.class).getBody();
		@SuppressWarnings({ "unchecked" })
		ResponseEntity<Long> res = (ResponseEntity<Long>) new RestTemplateBuilder().url(url.build().toUriString())
				.returnClass(Long.class).oauth2Token(token).httpMethod(HttpMethod.DELETE).build().executeRequest(false);

		return res.getBody();
	}

	public PubblicazioneModel getB2BDMSScheda(WSO2Token token, Long idEvento)
			throws JsonProcessingException, RestException {
		UriComponentsBuilder url = UriComponentsBuilder
				.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/" + idEvento + "/redazione/B2BDMS");
		PubblicazioneModel pubblicazioneModel = null;
		try {
//			pubblicazioneModel = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET,new HttpEntity<String>(new HttpHeaders()), new ParameterizedTypeReference<PubblicazioneModel>() {}).getBody();

			@SuppressWarnings({ "unchecked" })
			ResponseEntity<PubblicazioneModel> res = (ResponseEntity<PubblicazioneModel>) new RestTemplateBuilder()
					.url(url.build().toUriString()).returnClass(PubblicazioneModel.class).oauth2Token(token)
					.httpMethod(HttpMethod.GET).build().executeRequest(false);

			pubblicazioneModel = res.getBody();
		} catch (HttpClientErrorException e) {
			e.getStatusCode().compareTo(HttpStatus.NOT_FOUND);
		}
		if (pubblicazioneModel == null) {
			pubblicazioneModel = new PubblicazioneModel();
			B2BDMSSchedaModel b2bdmsSchedaModel = new B2BDMSSchedaModel();
			saveB2BDMSScheda(token, "REDVALCAP-B2B", idEvento, b2bdmsSchedaModel, null, "BOZZA");
			pubblicazioneModel = getB2BScheda(token, idEvento);
		} else {
			JsonNode node = pubblicazioneModel.getGenericMetadata();
			pubblicazioneModel.setGenericMetadata(node);
		}
		return pubblicazioneModel;
	}

	public List<EventoModelList> fetchListaPaginataEventi(WSO2Token token, String uuid) {
		try {

			UriComponentsBuilder url = UriComponentsBuilder
					.fromHttpUrl(sigeaPropertiesSettings.getUrlSIGEC() + "eventi/export/fetch")
					.queryParam("uuid", uuid);
//		return restTemplate.exchange(url.build().toUriString(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()),new ParameterizedTypeReference<WrapperFilterResponse<EventoModelList>>() {}).getBody();
			// TODO GESTIONE WrapperFilterResponse<DATA>
			@SuppressWarnings({ "unchecked" })
			ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder()
					.url(url.build().toUriString()).returnClass(String.class).oauth2Token(token)
					.httpMethod(HttpMethod.GET).build().executeRequest(false);

			if (res.getStatusCodeValue() == 302) {
				String result = res.getBody();

//		WrapperFilterResponse<EventoModelList> result = res.getBody();
				ObjectMapper objMapper = new ObjectMapper();
				List<EventoModelList> resultList = objMapper.readValue(result,
						new TypeReference<List<EventoModelList>>() {
						});
				;
				return resultList;
			} else {
				return null;
			}
		} catch (RestException e) {

			log.error(LogUtility.exceptionToLog(e));
			return null;
		} catch (JsonParseException e) {
			log.error(LogUtility.exceptionToLog(e));
			return null;
		} catch (JsonMappingException e) {
			log.error(LogUtility.exceptionToLog(e));
			return null;
		} catch (IOException e) {
			log.error(LogUtility.exceptionToLog(e));
			return null;
		}

	}

	
	
	public String exportRicevute(WSO2Token token , Long idEvento, String stato) 
	{
		try {
		UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(
				sigeaPropertiesSettings.getUrlSIGEC() + "ricevute/export/"+ idEvento +  "?stato=" + stato);
//		List<SmartModelList> listaSmart = restTemplate.exchange(url.build().toUriString(), HttpMethod.GET,new HttpEntity<String>(new HttpHeaders()), new ParameterizedTypeReference<List<SmartModelList>>() {}).getBody();

		@SuppressWarnings({ "unchecked" })
		ResponseEntity<String> res = (ResponseEntity<String>) new RestTemplateBuilder()
				.url(url.build().toUriString()).oauth2Token(token)
				.httpMethod(HttpMethod.GET).build().executeRequest(false);

		

		return res.getBody();
		}catch (Exception e) {
			log.error(LogUtility.exceptionToLog(e));
			return null;
		}
	}


}
