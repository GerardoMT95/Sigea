<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div id="gestioneAccF" style="padding: 0px; height: auto">
	<div style="padding-left: 0px; padding-top: 15px; padding-bottom: 15px; padding-right: 0px; font-size: 16px; line-height: 28px;">
		<a href="home" style="color: #000000;" title="Gestione eventi">Gestione eventi</a> > <span id="breadcrumbs">Crea evento</span>
	</div>
	<h4 class="box-title" style="font-size: 24px; line-height: 30px; margin-bottom: 30px; padding-left: 0px; padding-right: 15px;">
		<strong><spring:message code="label.creaevento" /></strong>
	</h4>
	<div style="padding-left: 0px; padding-bottom: 30px; padding-right: 0px; font-size: 16px; line-height: 21px; color: #000;">
		<spring:message code="label.creaeventoG.accordioncontent" />
	</div>
</div>
<div id="promozioneAccF" style="padding: 0px; height: auto" hidden="true">
	<div style="padding-left: 0px; padding-top: 15px; padding-bottom: 15px; padding-right: 0px; font-size: 16px; line-height: 28px;">
		<a href="#" onclick="location.reload();"
			style="color: #000000;">Promozione eventi</a> > <span id="breadcrumbs">Crea evento</span>
	</div>
	<h4 class="box-title" style="font-size: 24px; line-height: 30px; margin-bottom: 30px; padding-left: 0px; padding-right: 15px;">
		<strong><spring:message code="label.creaevento" /></strong>
		<!-- <span class="glyphicon glyphicon-info-sign" style="padding-left:10px" aria-hidden="true"></span> -->
	</h4>
	<div style="padding-left: 0px; padding-bottom: 30px; padding-right: 0px; font-size: 16px; line-height: 21px; color: #000;">
		<spring:message code="label.creaeventoP.accordioncontent" />
	</div>
</div>

<div id="gestioneEvento" style="padding: 0px; height: auto" hidden="true">
	<div style="padding-left: 0px; padding-top: 15px; padding-bottom: 15px; padding-right: 0px; font-size: 16px; line-height: 28px;">
		<a href="home" style="color: #000000;" title="Validazione eventi">Gestione eventi</a> > <a href="#" onclick="location.reload();"
			style="color: #000000;" id="labelbreadcrumb">Validazione eventi</a> > <span id="breadcrumbs">Dettaglio evento</span>
	</div>
	<h4 class="box-title" style="font-size: 24px; line-height: 30px; margin-bottom: 30px; padding-left: 0px; padding-right: 15px;">
		<strong><spring:message code="label.dettaglioevento" /></strong>
	</h4>
	<div style="padding-left: 0px; padding-bottom: 30px; padding-right: 0px; font-size: 16px; line-height: 21px; color: #000;">
		<spring:message code="label.textevento" />
	</div>
</div>

<div id="gestioneEventoPro" style="padding: 0px; height: auto; display: none;">
	<div style="padding-left: 0px; padding-top: 15px; padding-bottom: 15px; padding-right: 0px; font-size: 16px; line-height: 28px;">
		<!-- <a href="home" style="color: #000000;" title="Validazione eventi">Gestione eventi</a> >  -->
		<a href="#" onclick="location.reload();" style="color: #000000;" title="Promozione eventi">Promozione eventi e attivit&agrave;</a> > <span id="breadcrumbs">Dettaglio
			evento</span>
	</div>
	<h4 class="box-title" style="font-size: 24px; line-height: 30px; margin-bottom: 30px; padding-left: 0px; padding-right: 15px;">
		<strong><spring:message code="label.dettaglioevento" /></strong>
	</h4>
	<div style="padding-left: 0px; padding-bottom: 30px; padding-right: 0px; font-size: 16px; line-height: 21px; color: #000;">
		<spring:message code="label.textevento" />
	</div>
</div>

<div class="box user" style="padding: 15px 0px;">
	<form id="formEventCreating" name="formEventCreating" style="margin-bottom: 0px" accept-charset="UTF-8">

		<div class="row" id="inseriscievento" style="display: none; margin-bottom: 15px;">
			<div class="col-md-2" style="text-align: center; padding: 20px;">
				<img src="${contextPath}/assets/images/annuncio.svg">
			</div>
			<div class="col-md-10" style="padding-top: 20px;">
				<div class="boxInput">
					<input type="text" placeholder="Aggiungi un testo con massimo 300 caratteri" class="form-control Input-text" name="datiGenerali.titoloMulti.IT"
						id="it_titolo" maxlength="300" />
					<div class="scegliere">
						<spring:message code="label.datigenerali.titolo" />
					</div>
				</div>
			</div>
		 	<div class="col-md-10" style="float: right; padding-top: 40px;">
				<div class="boxInput">

				<div class="col-lg-1 col-md-1 col-sm-1 Input validvalue" style="padding: 0px 0px 20px 0px;">
						<input type="radio" class="radio-ruolo option-input inlist radio radiofinanziato" name="radiofinanziato" value="Si"> Si
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 Input validvalue" style="padding: 0px 0px 20px 0px;">
						<input type="radio" class="radio-ruolo option-input inlist radio radiofinanziato" name="radiofinanziato" value="No" checked="checked">
						No
					</div>

					<div class="scegliere">Si tratta di un evento finanziato?</div>
				</div>
			</div> 
		</div>

		<div id="labelEvent">
			<div class="row">
				<div class="col-md-2" style="text-align: center; padding: 20px;">
					<div class="immagineventoblocco" style="background: url(${contextPath}/assets/images/annuncio.svg);"></div>
				</div>
				<div class="col-md-10 ">
					<div class="col-md-12 labelblock" style="margin-bottom: 15px;">
						<label style="margin-bottom: 18px; font-weight: 600 !important; font-size: 21px;" id="titoloEvent"></label>
					</div>
					<div class="col-md-7" style="line-height: 26px;">
						<img src="${contextPath}/assets/images/Vector.svg"> <label id="idEvent" style="display: contents;"></label> <br> <br> <img
							src="${contextPath}/assets/images/Vector.svg"> <label id="utenteOwner" style="display: contents;"></label> <br> <br>
					</div>
					<div class="col-md-5" style="line-height: 26px;">
						<img src="${contextPath}/assets/images/Vector.svg"> <label id="utenteOwnerData" style="display: contents;"></label> <br> <br> <img
							src="${contextPath}/assets/images/Vector.svg"> <label id="statoCorrente" style="display: contents;"></label><label id="utenteLavorazione"></label>
						<br> <br>
					</div>
					<div class="col-md-5" style="line-height: 26px; display: none" id="divOldOwner">
						<img src="${contextPath}/assets/images/Vector.svg"> <label id="utenteOldOwner" style="display: contents;"></label> <br> <br>
					</div>
					<div class="col-md-12">
						<img src="${contextPath}/assets/images/Vector.svg" id="noteModV" style="display: none;"> <label id="noteMod" style="display: none;"></label>
					</div>
				</div>
			</div>

			<div class="box user" id="boxbando" style="display: none;">
				<div class="boxbanner">
					<div class="row">
						<div class="col-md-12" style="vertical-align: middle; border: 0px; margin: 0px;" id="testobando">
							<strong style="font-weight: 600; font-size: 16px; color: #003A54">INFORMAZIONI BANDO</strong><br /> 
							<input type="hidden" name="bando.bandoId" value="" disabled="disabled">
							<div id="labelprogetto">
								<input type="hidden" name="progetto.progettoId" value="" disabled="disabled"> <strong>Progetto:</strong> <input type="hidden"
									name="progetto.titoloProgetto" value="" disabled="disabled"> <span id="progettoutente"></span>
							</div>
							<div id="labelbando">
								<strong>Bando:</strong> <input type="hidden" name="bando.titoloBando" value="" disabled="disabled"> <span id="bandoutente"></span>
							</div>
							<div id="labelorganizzazione">
								<strong>Organizzazione:</strong> <input type="hidden" name="progetto.nomeOrganizzazione" value="" disabled="disabled"> <span
									id="organizzazioneutente"></span>
							</div>
						</div>
						<div class="col-md-12" style="vertical-align: middle; margin-top: 15px;" id="azionibandi">
							<div id="eliminabando">
								<i class="dms-wk-icon-delete" aria-hidden="true"></i> Elimina
							</div>
							<div id="modificabando">
								<i class="dms-wk-icon-edit" aria-hidden="true"></i> Modifica
							</div>
						</div>
					</div>
				</div>
			</div>
 			<div class="box user" id="boxbandoaggiungi">
				<div class="boxbanner">
					<div class="row">
						<div class="col-md-12" style="vertical-align: middle; border: 0px; margin: 0px;">
							<div style="vertical-align: middle;" id="testobando2">
								<strong style="font-weight: 600; font-size: 16px; color: #003A54; float: left; margin-right: 15px;">SE SI TRATTA DI EVENTO FINANZIATO</strong>
								<div id="espandibando">
									<i class="fa fa-Ellipsis-v"></i> Espandi
								</div>
								<div id="chiudibando" style="display: none;">
									<i class="fas fa-ellipsis-h"></i> Chiudi
								</div>
								<div id="aggiungibando">
									<i class="dms-wk-icon-add" aria-hidden="true"></i> Associa
								</div>
							</div>
							<div style="width: 100%; display: table; vertical-align: middle; display: none;" id="boxbandoaggiungilabel">Clicca sul pulsante "Associa" 
							per accedere alla sezione dedicata all'associazione dell'evento a un bando o a un progetto.
							<br/>							
							<strong>Cos'&egrave; un evento finanziato</strong><br/>
Un evento finanziato &egrave; un evento associato a un progetto destinatario di un finanziamento da parte di un bando o un avviso regionale, per cui &egrave; previsto l'obbligo di caricare l'evento attraverso il servizio dedicato sul DMS.
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="accordionA3" id="boxPubblicazione" hidden="true" style="margin: 0 0px 30px 0px;">
			<div class="accordionA3-header" style="text-align: left; color: #003A54;" data-toggle="collapse" data-target="#collapseA3" aria-expanded="true"
				aria-controls="collapse3A">
				<img src="${contextPath}/assets/svg/doc_nero.svg" style="margin-right: 20px;">Schede di pubblicazione
				<div style="float: right; padding-top: 8px;">
					<img src="${contextPath}/assets/images/FRECCIA_SU.svg" class="frecciafiltro" style="width: 18px;">
				</div>
			</div>
			<div class="accordionA3-content collapse in" id="collapseA3" aria-labelledby="headingA3" data-parent="#accordionExample"
				style="background: #F2F2F2; padding-top: 0px">
				<div class="row" style="margin: 0 0 0 0">
					<c:forEach var="redazione" varStatus="status" items="${redazioniSet}" step="1" begin="0">
						<c:if test="${not empty redazione.jspName}">
							<div class="schedapubb" style="cursor: pointer; margin-top: 20px;" hidden="true" onclick="apriSchedaAccessoria('${redazione.jspName}')"
								id="${redazione.redazioneId}scheda">
								<div class="labelpubb">${redazione.nome}<br>
								</div>
							</div>
						</c:if>
						<c:if test="${empty redazione.jspName}">
							<div class="schedapubburl" hidden="true" onclick="apriSchedaAccessoriaEsterna('${redazione.linkScheda}')" id="${redazione.redazioneId}scheda">
								<div class="labelpubb">${redazione.nome}<br>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
				<div class="row">
					<div class="col-md-12" id="divPubblEff" hidden="true">
						<div class="schedapubb" style="margin-top: 20px;">
							<div class="statopubdate">
								<label style="font-weight: 600; margin: 0px;"><spring:message code="label.pubbleffettuate" /></label>
							</div>
							<div class="labelpubbdate"></div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id="labelSegn" style="display: none;">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-default boxdenominazione"
						style="box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25) !important; padding: 0px !important; border: 0px;">
						<div class="panel-title" style="width: 100%;">
							<div class="boxdenominazione-title" style="padding: 12px;">
								<label id="denomiazionetitolo" style="margin: 0px;">Dettaglio suggerimento:</label> <label id="titoloSegn"
									style="display: contents; font-size: 18px; text-transform: uppercase;"></label>
							</div>
						</div>
						<div id="collapse1" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading2" aria-expanded="true" style="">
							<div class="panel-body" style="background-color: #F2F2F2;">
								<div class="row">
									<div class="col-md-4" id="boxdettaglioden">
										<img src="${contextPath}/assets/images/PRIODI_DI_APERTURA.svg" class="imgdettaglio">
										<div id="boxindirizzo">
											<strong>DATE</strong><br /> <label id="dataDaSegn" style="display: contents;"></label> <br> <label id="dataASegn"
												style="display: contents;"></label>
										</div>
									</div>
									<div class="col-md-4" id="boxdettaglioden">
										<img src="${contextPath}/assets/images/INDIRIZZO.svg" class="imgdettaglio">
										<div id="boxindirizzo">
											<strong>LUOGHI</strong><br /> <label id="comuneSegn" style="display: contents;"></label><br> <label id="indirizzoSegn"
												style="display: contents;"></label><br> <label id="locationSegn" style="display: contents;"></label>
										</div>
									</div>
									<div class="col-md-4" id="boxdettaglioden">
										<img src="${contextPath}/assets/images/icon-creazione_agenda_nero.svg" class="imgdettaglio" style="width: 35px;">
										<div id="boxindirizzo">
											<strong>CONTATTI</strong><br /> <label id="riferimentoSegn" style="display: contents;"></label>
										</div>
									</div>
									<div class="col-md-12">
										<label id="descrizioneSegn" style="display: contents;"></label>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Inserisci evento inizio -->
		<div class="row boxFinanziamento" style="display: none;" id="boxFinanziamento">
			<div class="col-md-10" style="float: right; margin-bottom: 30px;">
				<div class="col-lg-12 col-md-12 col-sm-12" style="line-height: 24px; padding: 0px;">
					<strong>Cos'&egrave; un evento finanziato</strong><br /> 
					Un evento finanziato &egrave; un evento associato a un progetto destinatario di un finanziamento da parte di un bando o un avviso regionale, per cui &egrave; previsto l'obbligo di caricare l'evento attraverso il servizio dedicato sul DMS.
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12" style="line-height: 24px; border-top: 1px solid #C4C4C4; margin-top: 15px; padding: 15px 0px 0px 0px;">
					<strong style="color: #003A54 !important; font-family: BarlowBold !important; font-weight: 600; font-size: 21px; line-height: 30px;">Scegli
						un progetto o un bando</strong><br /> Di seguito troverai l'elenco di tutti i progetti associati al Codice Fiscale della tua impresa o al Codice Fiscale del titolare. Se non trovi il progetto da associare all'evento, e si tratta di un evento finanziato, puoi selezionare il bando cliccando su "Visualizza tutti i bandi" e procedere con la creazione dell'evento. Se non trovi il bando, contatta il Servizio di Assistenza.
				</div>
				<div class="col-lg-5 col-md-5 col-sm-12" style="line-height: 24px; margin-top: 15px; padding: 15px 0px 0px 0px;">
					<strong style="color: #5E666E !important; font-weight: 600; font-size: 16px; line-height: 30px;"> PROGETTI ASSOCIATI - <span
						id="numerorisultati">0</span> RISULTATI
					</strong>
				</div>
				<div class="col-lg-7 col-md-7 col-sm-12" style="line-height: 24px; margin-top: 15px; padding: 15px 0px 0px 0px; text-align: right;">
					Non trovi il progetto di tuo interesse?
					<div id="altribandi" style="color: #0075bf; float: right; cursor: pointer;">
						<i class="dms-wk-icon-search" aria-hidden="true" style="margin: 0px 15px;"></i> Visualizza tutti i bandi
					</div>
				</div>
				<div class="listabandi">
					<div class="col-lg-12 col-md-12 col-sm-12 listaboxbandipersonali"
						style="line-height: 24px; border-top: 1px solid #C4C4C4; margin-top: 15px; padding: 15px 0px 0px 0px;"></div>
					<div class="col-lg-12 col-md-12 col-sm-12" style="display: none; line-height: 24px; margin-top: 0px; padding: 0px 0px 0px 0px;"
						id="messaggiozerorisultati">Non sono presenti progetti associati al Codice Fiscale della tua impresa o al Codice Fiscale del titolare.</div>
					<!-- <div class="col-lg-12 col-md-12 col-sm-12" style="display: none; line-height: 24px; margin-top: 0px; padding: 0px 0px 0px 0px;"
							id="ricercaincorso">Ricerca in corso...</div> -->
					<div class="col-lg-12 col-md-12 col-sm-12 listaboxbandicomuni"
						style="display: none; line-height: 24px; border-top: 1px solid #C4C4C4; margin-top: 15px; padding: 15px 0px 0px 0px;"></div>
				</div>
				<div class="col-lg-12 col-md-12 col-sm-12 divacc"
					style="display: none; line-height: 24px; border-top: 1px solid #C4C4C4; margin-top: 15px; padding: 15px 0px 0px 0px;">
					<label class="checkbox"> <input type="checkbox" style="position: inherit" name="accbandi" id="accbandi" value="true"> Confermo di essere destinatario di un finanziamento nell'ambito del bando selezionato.
					</label>
				</div>
			</div>
		</div>
		<!-- Inserisci evento fine -->

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
								<option data-target="#datigeneralibox"><spring:message code="tab.datigenerali" /></option>
								<option data-target="#locationbox"><spring:message code="tab.location" /></option>
								<option data-target="#periodobox"><spring:message code="tab.periodo" /></option>
								<option data-target="#contattibox"><spring:message code="tab.contatti" /></option>
								<option data-target="#ticketbox"><spring:message code="tab.ticket" /></option>
								<option data-target="#accessibilitabox"><spring:message code="tab.accessibilita" /></option>
								<option data-target="#mediabox"><spring:message code="tab.media" /></option>
								<option data-target="#relazionibox"><spring:message code="tab.relazioni" /></option>
								<option data-target="#revisionibox"><spring:message code="tab.revisioni" /></option>
							</select>
							<div class="scegliere">Cambia scheda</div>
						</div>
					</div>

					<ul class="nav nav-tabs" id="myTabCE" role="tablist">
						<li class="nav-item active" style="cursor: pointer;" id="datigenerali"><a href="#datigeneralibox" role="tab" data-toggle="tab"
							class="nav-link active" id="datigenerali-tab" style="cursor: pointer; padding: 10px 11px; border-top: 2px solid #0075BF;"><spring:message
									code="tab.datigenerali" /></a></li>
						<li class="nav-item" style="cursor: pointer" id="location"><a href="#locationbox" role="tab" data-toggle="tab" class="nav-link"
							id="location-tab" style="cursor: pointer; padding: 10px 11px"><spring:message code="tab.location" /></a></li>
						<li class="nav-item" style="cursor: pointer" id="periodo"><a href="#periodobox" role="tab" data-toggle="tab" class="nav-link"
							id="periodo-tab" style="cursor: pointer; padding: 10px 11px"><spring:message code="tab.periodo" /></a></li>
						<li class="nav-item" style="cursor: pointer" id="contatti"><a href="#contattibox" role="tab" data-toggle="tab" class="nav-link"
							id="contatti-tab" style="cursor: pointer; padding: 10px 11px"><spring:message code="tab.contatti" /></a></li>
						<li class="nav-item" style="cursor: pointer" id="ticket"><a href="#ticketbox" role="tab" data-toggle="tab" class="nav-link" id="ticket-tab"
							style="cursor: pointer; padding: 10px 11px"><spring:message code="tab.ticket" /></a></li>
						<li class="nav-item" style="cursor: pointer" id="accessibilita"><a href="#accessibilitabox" role="tab" data-toggle="tab" class="nav-link"
							id="accessibilita-tab" style="cursor: pointer; padding: 10px 11px"><spring:message code="tab.accessibilita" /></a></li>
						<li class="nav-item" style="cursor: pointer" id="media"><a href="#mediabox" role="tab" data-toggle="tab" class="nav-link" id="media-tab"
							style="cursor: pointer; padding: 10px 11px"><spring:message code="tab.media" /></a></li>
						<li class="nav-item" style="cursor: pointer" id="relazioni"><a href="#relazionibox" role="tab" data-toggle="tab" class="nav-link"
							id="relazioni-tab" style="cursor: pointer; padding: 10px 11px"><spring:message code="tab.relazioni" /></a></li>
						<li class="nav-item" style="cursor: pointer" id="revisioni"><a href="#revisionibox" role="tab" data-toggle="tab" class="nav-link"
							id="revisioni-tab" style="cursor: pointer; padding: 10px 11px"><spring:message code="tab.revisioni" /></a></li>
					</ul>
					<div class="tab-content" id="myTabCEContent" style="padding-top: 10px">
						<div class="tab-pane fade active in" id="datigeneralibox">
							<div class="accordion" style="padding-bottom: 10px">
								<div class="accordion-content">
									<spring:message code="label.datigenerali.accordioncontent" />
								</div>
							</div>
							<jsp:include page="tabDatiGenerali.jsp" flush="false" />
						</div>
						<div class="tab-pane fade" id="ticketbox">
							<div class="accordion" style="padding-bottom: 10px">
								<div class="accordion-content">
									<spring:message code="label.ticket.accordioncontent" />
								</div>
							</div>
							<jsp:include page="tabTicket.jsp" flush="false" />
						</div>
						<div class="tab-pane fade" id="accessibilitabox">
							<div class="accordion" style="padding-bottom: 10px">
								<div class="accordion-content">
									<spring:message code="label.accessibilita.accordioncontent" />
								</div>
							</div>
							<jsp:include page="tabAccessibilita.jsp" flush="false" />
						</div>
						<div class="tab-pane fade" id="contattibox">
							<div class="accordion" style="padding-bottom: 10px">
								<div class="accordion-content">
									<spring:message code="label.contatti.accordioncontent" />
								</div>
							</div>
							<jsp:include page="tabContatti.jsp" flush="false" />
						</div>
						<div class="tab-pane fade" id="locationbox">
							<div class="accordion" style="padding-bottom: 10px">
								<div class="accordion-content">
									<spring:message code="label.location.accordioncontent" />
									<br>
									<spring:message code="label.location.accordioncontent.geo" />
								</div>
							</div>
							<jsp:include page="tabLocation.jsp" flush="false" />
						</div>
						<div class="tab-pane fade" id="periodobox">
							<div class="accordion" style="padding-bottom: 10px">
								<div class="accordion-content">
									<spring:message code="label.periodo.accordioncontent" />
								</div>
							</div>
							<jsp:include page="tabPeriodo.jsp" flush="false" />
						</div>
						<div class="tab-pane fade" id="mediabox">
							<div class="accordion" style="padding-bottom: 10px">
								<div class="accordion-content">
									<spring:message code="label.media.accordioncontent" />
								</div>
							</div>
							<jsp:include page="tabMedia.jsp" flush="false" />
						</div>
						<div class="tab-pane fade" id="relazionibox">
							<div class="accordion" style="padding-bottom: 10px">
								<div class="accordion-content">
									<spring:message code="label.relazioni.accordioncontent" />
								</div>
							</div>
							<jsp:include page="tabRelazioni.jsp" flush="false" />
						</div>
						<div class="tab-pane fade" id="revisionibox">
							<div class="accordion" style="padding-bottom: 10px">
								<div class="accordion-content">
									<spring:message code="label.revisioni.accordioncontent" />
								</div>
							</div>
							<jsp:include page="tabRevisioni.jsp" flush="false" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<div class="row">
		<div class="col-md-12 Input" id="permettiButton" style="padding-right: 15px">
			<spring:message code="title.salva" var="phSalva" />
			<spring:message code="title.esci" var="phEsci" />
			<c:forEach var="stato" varStatus="status" items="${statiSet}" step="1" begin="0">
				<c:choose>
					<c:when test="${not empty stato.accessoCondizionato && stato.accessoCondizionato}">
						<button type="button" class="btn btn-primary invia" hidden="true" style="float: right; margin-left: 24px"
							onclick="salvaEventoCondizionato('${stato.statoId}')" id="${stato.statoId}">${stato.nome.toLowerCase()}</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-primary invia" hidden="true" style="float: right; margin-left: 24px"
							onclick="salvaEvento('${stato.statoId}')" id="${stato.statoId}">${stato.nome.toLowerCase()}</button>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<button type="button" style="float: right; margin-left: 24px" onclick="salvaEvento('AGGIORNA')" id="salva" title="${phSalva}"
				class="btn btn-primary cancella">
				<spring:message code="button.salva" />
			</button>
			<button id="chiudievento" type="button" style="float: left; float: left;" title="${phEsci}" class="btn btn-primary cancella">
				<spring:message code="button.indietro" />
			</button>
		</div>
	</div>
</div>
<div style="clear: both"></div>

<div class="modal esitoArrivo" tabindex="-1" role="dialog" id="modalAggiungibando" data-keyboard="false">
	<div class="modal-dialog" role="document" style="width: 1140px; top: 5%; margin: auto; width: -moz-fit-content">
		<div class="modal-content modal-lg-mes" id="datiBando" style="font-size: 14px !important">
			<div class="modal-header">
				<h4 class="modal-title">Modifica i dati del bando</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close" style="opacity: 1;">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" id="scrolling-x" style="font-size: 16px; text-align: left; line-height: 28px; float: left; overflow: auto; height: 600px;">
				In questa sezione puoi modificare l'associazione a un bando o a un progetto per un evento gi&agrave; creato. <br /> <br />
				<div class="boxFinanziamento">
					<div class="col-lg-12 col-md-12 col-sm-12" style="line-height: 24px; border-top: 1px solid #C4C4C4; margin-top: 15px; padding: 15px 0px 0px 0px;">
						<strong style="color: #003A54 !important; font-family: BarlowBold !important; font-weight: 600; font-size: 21px; line-height: 30px;">Scegli
							un progetto o un bando</strong><br /> 
							Di seguito troverai l'elenco di tutti i progetti associati al Codice Fiscale della tua impresa o al Codice Fiscale del titolare. Se non trovi il progetto da associare all'evento, e si tratta di un evento finanziato, puoi selezionare il bando cliccando su "Visualizza tutti i bandi" e procedere con la creazione dell'evento. Se non trovi il bando, contatta il Servizio di Assistenza.
					</div>
					<div class="col-lg-5 col-md-5 col-sm-12" style="line-height: 24px; margin-top: 15px; padding: 15px 0px 0px 0px;">
						<strong style="color: #5E666E !important; font-weight: 600; font-size: 16px; line-height: 30px;"> PROGETTI ASSOCIATI - <span
							id="numerorisultati"></span> RISULTATI
						</strong>
					</div>
					<div class="col-lg-7 col-md-7 col-sm-12" style="line-height: 24px; margin-top: 15px; padding: 15px 0px 0px 0px; text-align: right;">
						Non trovi il progetto di tuo interesse?
						<div id="altribandi" style="color: #0075bf; float: right; cursor: pointer;">
							<i class="dms-wk-icon-search" aria-hidden="true" style="margin: 0px 15px;"></i> Visualizza tutti i bandi
						</div>
					</div>
					<div class="listabandi">
						<div class="col-lg-12 col-md-12 col-sm-12 listaboxbandipersonali"
							style="line-height: 24px; border-top: 1px solid #C4C4C4; margin-top: 15px; padding: 15px 0px 0px 0px;"></div>
						<div class="col-lg-12 col-md-12 col-sm-12" style="display: none; line-height: 24px; margin-top: 0px; padding: 0px 0px 0px 0px;"
							id="messaggiozerorisultati">Non sono presenti progetti associati al Codice Fiscale della tua impresa o al Codice Fiscale del titolare.</div>
						<div class="col-lg-12 col-md-12 col-sm-12 listaboxbandicomuni"
							style="display: none; line-height: 24px; border-top: 1px solid #C4C4C4; margin-top: 15px; padding: 15px 0px 0px 0px;"></div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 divacc"
						style="display: none; line-height: 24px; border-top: 1px solid #C4C4C4; margin-top: 15px; padding: 15px 0px 0px 0px;">
						<label class="checkbox"> <input type="checkbox" style="position: inherit" name="accbandi" id="accbandi" value="true"> Confermo di essere destinatario di un finanziamento nell'ambito del bando selezionato. 
						</label>
					</div>
				</div>
			</div>
			<div class="modal-footer" style="text-align: center">
				<button type="button" class="btn btn-primary cancella" style="float: none" data-dismiss="modal">Annulla</button>
				<button type="button" class="btn btn-primary invia" id="inseriscibando" style="float: none" onclick="nuovobando()" disabled="disabled">Associa</button>
			</div>
		</div>
	</div>
</div>





<!-- da alert a modal  -->
<div class="modal esitoArrivo" tabindex="1" role="dialog" id="modalModificaCreazione" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog modal-lg-max" role="document" style="margin: 1% auto; width: 100%; max-width: 500px;">
		<div class="modal-content modal-lg-max" id="datiCamere" style="font-size: 14px !important">
			<div class="modal-header">
				<h4 class="modal-title">Attenzione</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close" style="opacity: 1;">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" id="scrolling-x" style="font-size: 16px;">
				<form action="modificaevento" id="modificaevento">
					<div class="col-md-12" style="padding-bottom: 35px;">
						<div align="center">
							<i class="icon-errore" style="font-size: 64px; color: #a66300;"></i>
						</div>
						<div id="richiesta_nota">
							<br />Inserisci qui i motivi per cui stai modificando la scheda. La nota sar&agrave; utile alla redazione per rivalutarla. <br />Attenzione: procedendo con la modifica, l'evento potrebbe essere spubblicato dai canali sui quali &egrave; online al momento, fino a nuova valutazione. <br />Sei sicuro di voler procedere?
						</div>
					</div>
					<div class="col-md-12 Input" style="height: 200px; padding-bottom: 0px;">
						<label class="scegliere" style="left: 15px; top: -20px;">Note</label>
						<textarea name="notaModificaCreazione" class="form-control Input-text" id="notaModificaCreazione" placeholder="Inserisci"
							style="width: 100%; height: 165px;"></textarea>
					</div>
				</form>
			</div>
			<div class="modal-footer" style="text-align: center">
				<button type="button" class="btn btn-primary cancella" style="float: none" data-dismiss="modal">No</button>
				<button type="button" class="btn btn-primary invia salvamodifica" style="float: none" id="modificaeventocreato">Si</button>
			</div>
		</div>
	</div>
</div>
<!-- da alert a modal  -->

<script type="text/javascript" src="${contextPath}/assets/js/sigeajs/creaEvento.js?v=<%= (int) (Math.random() * 100) %>"></script>
<script type="text/javascript" src="${contextPath}/assets/js/Utilities.js?v=<%= (int) (Math.random() * 100) %>"></script>