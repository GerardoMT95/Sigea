<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<label id="eventoId" hidden="true">${eventoId}</label>
<label id="statoNucleo" hidden="true">${statoNucleo}</label>
<input type="hidden" name="idPubblicazione" id="idPubblicazione" readonly>
<div style="padding: 0px; height: auto">
	<div style="padding-left: 0px; padding-top: 15px; padding-bottom: 15px; padding-right: 0px; font-size: 16px; line-height: 28px;">
		<a href="home" style="color: #000000;" title="Gestione eventi">Gestione eventi</a> > <a href="#" onclick="location.reload();"
			style="color: #000000;" id="labelbreadcrumb">Redazione eventi</a> > <span id="breadcrumbs">Dettaglio evento</span>
	</div>
	<h4 class="box-title" style="font-size: 24px; line-height: 30px; margin-bottom: 30px; padding-left: 0px; padding-right: 15px;">
		<strong><spring:message code="label.schedavip" /></strong>
	</h4>
</div>
<div class="box user" style="padding: 15px 0px;">
	<form id="formSchedaVIP" name="formSchedaVIP" style="margin-bottom: 0px" accept-charset="UTF-8">
		<div id="labelEvent">
			<div class="row">
				<div class="col-md-2" style="text-align: center; padding: 20px;">
					<img src="${contextPath}/assets/images/annuncio.svg">
				</div>
				<div class="col-md-10" style="margin-top: 25px;">
					<div class="col-md-5" style="line-height: 26px;">
						<img src="${contextPath}/assets/images/Vector.svg"> <label id="idEvent" style="display: contents;"><strong>Stato
								corrente:</strong></label> <label id="statoPubblicazione"></label> <br> <br> <img src="${contextPath}/assets/images/Vector.svg"> <label
							id="utenteOwner" style="display: contents;"><strong>Pubblicato:</strong></label> <label id="statoOnline"></label> <br> <br> <img
							src="${contextPath}/assets/images/Vector.svg"> <label id="utenteOwner" style="display: contents;"><strong>Pubblicato da:</strong></label>
						<label id="autorePubblicazione"></label> <br> <br>
					</div>
					<div class="col-md-7" style="line-height: 26px;">
						<img src="${contextPath}/assets/images/Vector.svg"> <label id="utenteOwnerData" style="display: contents;"><strong>Data
								ultima pubblicazione:</strong></label> <label id="pubblicatoPubblicazione"></label> <br> <br> <img src="${contextPath}/assets/images/Vector.svg">
						<label id="utenteOwner" style="display: contents;"><strong>Data di prima pubblicazione:</strong></label> <label id="primaPubbblicazione"></label>
						<br> <br> <img src="${contextPath}/assets/images/Vector.svg"> <label id="utenteOwnerData" style="display: contents;"><strong><spring:message
									code="label.tipologia" />:</strong></label> <label id="tipologiaNucleo"></label> <br> <br> <span id="notePubblicazione" hidden="true"> <img
							src="${contextPath}/assets/images/Vector.svg"> <label id="utenteOwnerData" style="display: contents;"><strong>Note:</strong></label> <label
							id="noteRifiuto"></label> <br> <br>
						</span>
					</div>
				</div>
			</div>
			<div class="accordion-content">
				<spring:message code="label.schedavip.accordion" />
			</div>
			
			<!--  
			<div class="box user" id="boxbando" style="display: none;">
				<div class="boxbanner">
					<div class="row">
						<div class="col-md-12" style="vertical-align: middle; border: 0px; margin: 0px;" id="testobando">
							<strong style="font-weight: 600; font-size: 16px; color: #003A54">INFORMAZIONI BANDO</strong><br /> 
							<input type="hidden" name="bando.bandoId" value="" disabled="disabled">
							
							<div id="labelprogetto">
								<input type="hidden" name="progetto.progettoId"	value="" disabled="disabled">
								<strong>Progetto:</strong> <input type="hidden" name="progetto.titoloProgetto" value="" disabled="disabled"> <span id="progettoutente"></span>
							</div>
							
							<div id="labelbando">
								<strong>Bando:</strong> <input type="hidden" name="bando.titoloBando" value="" disabled="disabled"> <span id="bandoutente"></span>
							</div>
							
							<div id="labelorganizzazione">
								<strong>Organizzazione:</strong> <input type="hidden" name="progetto.nomeOrganizzazione" value="" disabled="disabled"> <span id="organizzazioneutente"></span>
							</div>
							
						</div>
					</div>
				</div>
			</div>
			-->
			
		</div>
		
		<div class="row" style="margin-bottom: 15px;">		
		<div class="col-md-12 text-right"><a style="letter-spacing: normal" 
		href="${contextPath}/getRiassunto/${eventoId}" data-container="body" title="Visualizza" data-html="true" class="databutton popup-with-form">
		<i class="dms-wk-icon-show-details" aria-hidden="true"></i> <strong>Vedi il riepilogo del nucleo</strong></a>
		
				
		
		</div>
		
		</div>
		<div id="labelValid" style="display: none;">
			<div class="row" style="margin-bottom: 0px; margin: 0px -20px;">
				<div class="col-md-12" style="text-align: center; padding: 20px;">
					<div class="box alertdms" style="padding: 15px; font-size: 14px; border-left: 7px solid #BD212F; background: #ffffff;">
						<div class="row">
							<div class="col-md-12">
								<i class="dms-wk-icon-error titolo" aria-hidden="true" style="font-size: 4rem; float: left; margin-right: 2rem;"></i> <span
									style="font-size: 16px; line-height: 40px; float: left;"><spring:message code="alert.label" /> <i
									class="dms-wk-icon-modifica-effettuata alerticonwk"></i></span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="box user" id="boxusersigea">
			<div class="row">
				<div class="col-md-12">
					<div class="Input" id="boxmobileMenu" style="display: none; padding-bottom: 15px;">
						<div class="boxInput mobile-input">
							<select id="mobiletabMenu" class="form-control Input-text">
								<option data-target="#boxDatigenerali"><spring:message code="tab.datigenerali" /></option>
								<option data-target="#boxProdotto"><spring:message code="tab.caratteristiche" /></option>
								<option data-target="#boxAttribuzioni"><spring:message code="tab.attribuzioni" /></option>
								<option data-target="#boxConsigliato"><spring:message code="label.consigliato" /></option>
								<option data-target="#boxMedia"><spring:message code="tab.media.nucleo" /></option>
								<option data-target="#boxRelazioni"><spring:message code="tab.relazioni" /></option>
							</select>
							<div class="scegliere">Cambia scheda</div>
						</div>
					</div>
					<ul class="nav nav-tabs" id="pubblTab" role="tablist">
						<li class="nav-item active" style="cursor: pointer;" id="datigeneraliV"><a href="#boxDatigenerali" role="tab" data-toggle="tab"
							class="nav-link" id="datigeneraliV-tab" style="cursor: pointer; padding: 10px 11px"><spring:message code="tab.datigenerali" /></a></li>
						<li class="nav-item" style="cursor: pointer" id="prodottoV"><a href="#boxProdotto" role="tab" data-toggle="tab" class="nav-link"
							id="prodottoV-tab" style="cursor: pointer; padding: 10px 11px"><spring:message code="tab.caratteristiche" /></a></li>
						<li class="nav-item" style="cursor: pointer" id="attribuzioniV"><a href="#boxAttribuzioni" role="tab" data-toggle="tab" class="nav-link"
							id="attribuzioniV-tab" style="cursor: pointer; padding: 10px 11px"><spring:message code="tab.attribuzioni" /></a></li>
						<li class="nav-item" style="cursor: pointer" id="consigliatoV"><a href="#boxConsigliato" role="tab" data-toggle="tab" class="nav-link"
							id="consigliatoV-tab" style="cursor: pointer; padding: 10px 11px"><spring:message code="label.consigliato" /></a></li>
						<li class="nav-item" style="cursor: pointer" id="mediaV"><a href="#boxMedia" role="tab" data-toggle="tab" class="nav-link" id="mediaV-tab"
							style="cursor: pointer; padding: 10px 11px"><spring:message code="tab.media.nucleo" /></a></li>
						<li class="nav-item" style="cursor: pointer" id="relazioniV"><a href="#boxRelazioni" role="tab" data-toggle="tab" class="nav-link"
							id="relazioniV-tab" style="cursor: pointer; padding: 10px 11px"><spring:message code="tab.relazioni" /></a></li>
					</ul>
					<div class="tab-content" id="pubblTabContent" style="padding-top: 10px">
						<div class="tab-pane fade active in" id="boxDatigenerali">
							<div class="accordion" style="padding-bottom: 10px">
								<div class="accordion-content">
									<spring:message code="label.schedavip.datigenerali.accordion" />
								</div>
							</div>
							<jsp:include page="tabDatiGeneraliVIP.jsp" flush="false" />
						</div>
						<div class="tab-pane fade" id="boxProdotto">
							<div id="carattEvento">
								<div class="accordion" style="padding-bottom: 10px">
									<div class="accordion-content">
										<spring:message code="label.schedavip.caratteristiche.accordion" />
									</div>
								</div>
							</div>
							<div id="carattAttivita">
								<div class="accordion" style="padding-bottom: 10px">
									<div class="accordion-content">
										<spring:message code="label.schedavip.caratteristicheA.accordion" />
									</div>
								</div>
							</div>
							<jsp:include page="tabProdotto.jsp" flush="false" />
						</div>
						<div class="tab-pane fade" id="boxAttribuzioni">
							<div id="attrEvento">
								<div class="accordion" style="padding-bottom: 10px">
									<div class="accordion-content">
										<spring:message code="label.schedavip.attribuzioni.accordion" />
									</div>
								</div>
							</div>
							<div id="attrAttivita">
								<div class="accordion" style="padding-bottom: 10px">
									<div class="accordion-content">
										<spring:message code="label.schedavip.attribuzioniA.accordion" />
									</div>
								</div>
							</div>
							<jsp:include page="tabAttribuzioni.jsp" flush="false" />
						</div>
						<div class="tab-pane fade" id="boxConsigliato">
							<div class="accordion" style="padding-bottom: 10px">
								<div class="accordion-content">
									<spring:message code="label.schedavip.consigliato.accordion" />
								</div>
							</div>
							<jsp:include page="tabConsigliato.jsp" flush="false" />
						</div>
						<div class="tab-pane fade" id="boxMedia">
							<div class="accordion" style="padding-bottom: 20px">
								<div class="accordion-content">
									<spring:message code="label.schedavip.media.accordion" />
								</div>
							</div>
							<div id="nucleoMedia"><jsp:include page="tabMediaVIP.jsp" flush="false" /></div>
							<div id="AggMedia"><jsp:include page="tabMediaAggVIP.jsp" flush="false" /></div>
						</div>

						<div class="tab-pane fade" id="boxRelazioni">
							<div class="accordion" style="padding-bottom: 10px">
								<div class="accordion-content">
									<spring:message code="label.schedavip.relazioni.accordion" />
								</div>
							</div>
							<div id="nucleoRel" style="margin-bottom: 30px;"><jsp:include page="tabRelazioniVIP.jsp" flush="false" /></div>
							<div id="AggRel"><jsp:include page="tabRelazioniAggVIP.jsp" flush="false" /></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
<div class="row">
	<div class="col-md-12 Input" style="padding-right: 15px">
		<button type="button" style="float: right; margin-left: 24px" onclick='popupSalva("PUBBLICATO")' id="pubblicaVIP" class="btn btn-primary invia">
			<spring:message code="button.pubblica" />
		</button>
		<button type="button" style="float: right; margin-left: 24px" onclick='salvaScheda("BOZZA")' id="salvaVIP" class="btn btn-primary cancella">
			<spring:message code="button.aggBozza" />
		</button>
		<button type="button" style="float: right; margin-left: 24px" onclick='salvaScheda("BOZZA")' id="creaBozzaVIP" class="btn btn-primary invia">
			<spring:message code="button.incompilazione" />
		</button>
		<button type="button" style="margin-right: 24px" id="chiudievento" class="btn btn-primary cancella">
			<spring:message code="button.indietro" />
		</button>
		<button type="button" style="float: right; margin-left: 24px" onclick='popupSalva("REDATTO")' id="compilaVIP" class="btn btn-primary invia">
			<spring:message code="button.compila" />
		</button>
		<button type="button" style="float: right; margin-left: 24px" onclick='popupSalva("BOZZA")' id="bozzaVIP" class="btn btn-primary invia">
			<spring:message code="button.riporta" />
		</button>
		<sec:authorize access="hasRole('SIGEA_PUBBLICAZIONE_VIP')">
			<button type="button" style="float: right; margin-left: 24px" onclick='riportaUltimaPubblicazione()' id="riportaVIP"
				class="btn btn-primary cancella">
				<spring:message code="button.riportaPubb" />
			</button>
			<button type="button" style="float: right; margin-left: 24px" onclick='revocaUltimaPubblicazione()' id="revocaVIP" class="btn btn-primary cancella">
				<spring:message code="button.revocaPubb" />
			</button>
			<button type="button" style="float: right; margin-left: 24px" id="rifiutaVIP" class="btn btn-primary invia">
				<spring:message code="button.respinto" />
			</button>
		</sec:authorize>
	</div>
</div>
<sec:authorize access="hasRole('SIGEA_PUBBLICAZIONE_VIP')">
	<div class="modal esitoArrivo" tabindex="1" role="dialog" id="modalValidazione" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-lg-max" role="document" style="margin: 1% auto; width: 100%; max-width: 500px;">
			<div class="modal-content modal-lg-max" id="datiCamere" style="font-size: 14px !important">
				<div class="modal-header">
					<h4 class="modal-title">Attenzione</h4>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close" style="opacity: 1;">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" id="scrolling-x" style="font-size: 16px;">
					<form action="rifiutaevento" id="formvalidazione">
						<div class="col-md-12" style="padding-bottom: 35px;">
							<div align="center">
								<i class="icon-errore" style="font-size: 64px; color: #a66300;"></i>
							</div>
							<br /> <strong>Stai respingendo l'evento!</strong><br /> Inserisci i motivi per cui l'evento &egrave; da respingere.<br>La nota inserita
							sar&agrave; visualizzata nel dettaglio della scheda redazionale.
						</div>
						<input type="text" hidden="true" value="" name="ideventomodal" id="ideventomodal">
						<div class="col-md-12 Input" style="height: 200px; padding-bottom: 0px;">
							<label class="scegliere" style="left: 15px; top: -20px;">Note</label>
							<textarea name="notaValidazione" class="form-control Input-text" id="notaValidazione" placeholder="Inserisci"
								style="width: 100%; height: 165px;">${note}</textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer" style="text-align: center">
					<button type="button" class="btn btn-primary cancella" style="float: none" data-dismiss="modal">Annulla</button>
					<button type="button" class="btn btn-primary invia salvamodifica" style="float: none" onclick='salvaScheda("RIFIUTATO")' id="salvavalutazione">Salva</button>
				</div>
			</div>
		</div>
	</div>
</sec:authorize>

<script type="text/javascript" src="${contextPath}/assets/js/sigeajs/vipScheda.js?v=<%= (int) (Math.random() * 100) %>"></script>
<script type="text/javascript" src="${contextPath}/assets/js/Utilities.js?v=<%= (int) (Math.random() * 100) %>"></script>