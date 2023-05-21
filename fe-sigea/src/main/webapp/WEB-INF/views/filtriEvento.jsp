<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div style="padding: 20px 10px 10px 0px; height: auto">
	<div id="gestioneAcc" hidden="true">
		<h4 class="box-title" style="font-size: 24px; line-height: 26px; padding-left: 0px">
			<span><strong><spring:message code="label.gestioneevento" /></strong></span>
		</h4>
		<div class="accordion-content">
			<spring:message code="label.gestioneevento.accordioncontent" />
		</div>
	</div>
	<a href="">NUOVA PAGINA SIGEA</a>
	<div id="promozioneAcc" hidden="true">
		<h4 class="box-title" style="font-size: 24px; line-height: 26px; padding-left: 0px">
			<span><strong><spring:message code="label.promozioneevento" /></strong></span>
		</h4>
		<div class="accordion-content">
			<spring:message code="label.promozioneevento.accordioncontent" />
		</div>
		<div class="row">
			<div class="col-md-2" style="text-align: center;">
				<img src="${contextPath}/assets/images/shopper.svg">
			</div>
			<div id="centermobile" class="col-md-10" style="padding-top: 35px;">
				<div id="nomestruttura" style="display: inline; font-weight: 600; font-size: 18px; line-height: 27px; color: #00293B;"></div>
			</div>
		</div>
	</div>
</div>
<div class="box user" id="filtroboxuser" style="margin-bottom: 30px;">
	<div class="Input" id="boxmobilemyTabFE" style="display: none; padding-bottom: 15px;">
		<div class="boxInput mobile-input">
			<select id="mobilemyTabFE" class="form-control Input-text">
				<option value="eventi-tab"><spring:message code="tab.cercaevento" /></option>
				<option value="segnalazioni-tab"><spring:message code="tab.cercasegnalazione" /></option>
			</select>
			<div class="scegliere">Menu</div>
		</div>
	</div>
	<ul class="nav nav-tabs" id="myTabFE" role="tablist">
		<li class="nav-item active" style="cursor: pointer; border-top: 2px solid #0075bf;" id="gesteventi"><a href="#eventibox" role="tab"
			data-toggle="tab" class="nav-link active" id="eventi-tab" style="cursor: pointer"><spring:message code="tab.cercaevento" /></a></li>
		<li class="nav-item hide" style="cursor: pointer" id="gestsegnalazioni"><a href="#segnalazionibox" role="tab" data-toggle="tab"
			class="nav-link" id="segnalazioni-tab" style="cursor: pointer"><spring:message code="tab.cercasegnalazione" /></a></li>
	</ul>
	<div class="tab-content" id="myTabFEContent" style="padding-top: 10px">
		<div class="tab-pane fade active in" id="eventibox">
			<div class="row">
				<div class="col-md-9" style="font-size: 16px;">
					E&rsquo; possibile creare un nuovo evento cliccando sul bottone <strong>Crea evento</strong> oppure gestire gli eventi gi&agrave; creati tramite
					le azioni presenti all&rsquo;interno della tabella sottostante.
				</div>
				<div class="col-md-3">
					<button type="button" name="creaevento" class="btn btn-primary invia"
						style="float: right; margin-left: 0px; margin-right: 0px; margin-bottom: 10px; margin-top: 10px;">
						<i class="dms-wk-icon-add" aria-hidden="true"></i> Crea evento
					</button>
				</div>
			</div>
			<div class="accordionF">
				<div class="accordionF-header" style="text-align: left; color: #003A54;">
					<img src="${contextPath}/assets/images/equalizer.svg" style="margin-right: 20px;">
					<spring:message code="label.filtroavanzato" />
					<div style="float: right; padding-top: 8px;">
						<img src="${contextPath}/assets/images/FRECCIA_GIU.svg" class="frecciafiltro" style="width: 18px;">
					</div>
				</div>
				<div class="accordionF-content">
					<form id="formEventFilter" name="formEventFilter" accept-charset="UTF-8">
						<div class="row" id="smartDiv" hidden="true">
							<fieldset class="campiFiltro">
								<legend class="legendFiltro">
									<spring:message code="label.smart" />
								</legend>
								<div class="col-lg-4 col-md-5 col-sm-8 Input" style="height: auto">
									<div class="boxInput">
										<select class="form-control Input-text" name="smart" id="smart"></select>
										<div class="scegliere">
											<spring:message code="label.indicatore" />
										</div>
									</div>
								</div>
							</fieldset>
							<hr style="border-top: 1px solid #ddd">
						</div>
						<div class="row" style="margin-top: 20px">
							<fieldset class="campiFiltro">
								<legend class="legendFiltro">
									<spring:message code="filtri.evento" />
								</legend>
								<div class="col-lg-4 col-md-4 col-sm-6 Input">
									<spring:message code="placeholder.id" var="phId" />
									<div class="boxInput">
										<input type="text" class="form-control Input-text" name="eventoId" id="idevento" placeholder="${phId}" maxlength="30">
										<div class="scegliere">
											<spring:message code="label.idevento" />
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 Input">
									<div class="boxInput">
										<select class="form-control Input-text" name="tipologia" id="tipologia"></select>
										<div class="scegliere">
											<spring:message code="label.tipologia" />
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 Input">
									<spring:message code="placeholder.titolo" var="phTitolo" />
									<div class="boxInput">
										<input type="text" class="form-control Input-text" name="titolo" id="titolo" placeholder="${phTitolo}" maxlength="100">
										<div class="scegliere">
											<spring:message code="label.titoloevento" />
										</div>
									</div>
								</div>
								<div id="datahid">
									<div class="col-lg-4 col-md-4 col-sm-6 Input  inputicondata">
										<spring:message code="placeholder.seldata" var="phData" />
										<div class="boxInput">
											<input type="text" class="form-control-date Input-text startS-d data" name="dataInsDa" readonly id="dataInsDa" placeholder="${phData}">
											<div class="scegliere">
												<spring:message code="label.datainsDa" />
											</div>
										</div>
									</div>
									<div class="col-lg-4 col-md-4 col-sm-6 Input  inputicondata">
										<spring:message code="placeholder.seldata" var="phData" />
										<div class="boxInput">
											<input type="text" class="form-control-date Input-text endS-d data" name="dataInsA" readonly id="dataInsA" placeholder="${phData}">
											<div class="scegliere">
												<spring:message code="label.datainsA" />
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 Input">
									<div class="boxInput">
										<select class="form-control Input-text" name="stato" id="stato"></select>
										<div class="scegliere">
											<spring:message code="label.statoevento" />
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 Input  inputicondata">
									<spring:message code="placeholder.seldata" var="phData" />
									<div class="boxInput">
										<input type="text" class="form-control-date Input-text start-d data" name="dataDa" readonly id="dataDal" placeholder="${phData}">
										<div class="scegliere">
											<spring:message code="label.datadal" />
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 Input  inputicondata">
									<spring:message code="placeholder.seldata" var="phData" />
									<div class="boxInput">
										<input type="text" class="form-control-date Input-text end-d data" name="dataA" readonly id="dataAl" placeholder="${phData}">
										<div class="scegliere">
											<spring:message code="label.dataal" />
										</div>
									</div>
								</div>
							</fieldset>
							<fieldset class="campiFiltro" id="promoDiv" hidden="true">
								<legend class="legendFiltro">
									<spring:message code="filtri.promotore" />
								</legend>
								<div class="col-lg-4 col-md-4 col-sm-6 Input">
									<spring:message code="placeholder.cognome" var="phCognome" />
									<div class="boxInput">
										<input type="text" class="form-control Input-text" name="cognomeOwner" id="cognome" placeholder="${phCognome}" maxlength="100">
										<div class="scegliere">
											<spring:message code="label.cognomeorg" />
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 Input">
									<spring:message code="placeholder.email" var="phEmail" />
									<div class="boxInput">
										<input type="text" class="form-control Input-text" name="emailOwner" id="email" placeholder="${phEmail}" maxlength="100">
										<div class="scegliere">
											<spring:message code="label.emailorg" />
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 Input">
									<spring:message code="placeholder.attivita" var="phAttivita" />
									<div class="boxInput">
										<input type="text" class="form-control Input-text" name="denominazioneAttivita" id="attivita" placeholder="${phAttivita}" maxlength="100">
										<div class="scegliere">
											<spring:message code="label.denomatt" />
										</div>
									</div>
								</div>
							</fieldset>
							<fieldset class="campiFiltro">
								<legend class="legendFiltro">
									<spring:message code="filtri.pubblicazione" />
								</legend>
								<div class="col-lg-4 col-md-4 col-sm-6 Input">
									<div class="boxInput">
										<select class="form-control Input-text" name="pubblicato" id="pubblicato"></select>
										<div class="scegliere">
											<spring:message code="label.pubblicato" />
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 Input multipl">
									<div class="boxInput">
										<select class="form-control Input-text multi" name="redazioniGenerali[]" id="redazioniGenerali" multiple="multiple" disabled></select>
										<div class="scegliere">
											<spring:message code="label.pubblicatoDa" />
										</div>
									</div>
								</div>
							</fieldset>
							<fieldset id="schedaRedHid" class="campiFiltro">
								<legend class="legendFiltro">
									<spring:message code="filtri.scheda" />
								</legend>
								<div class="col-lg-4 col-md-4 col-sm-6 Input">
									<div class="boxInput">
										<select class="form-control Input-text" name="statoPubblicazione" id="statoPubb"></select>
										<div class="scegliere">
											<spring:message code="label.statoPubblicazione" />
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 Input multipl">
									<div class="boxInput">
										<select class="form-control Input-text multi" name="redazioni[]" id="redazioni" multiple="multiple" disabled></select>
										<div class="scegliere">
											<spring:message code="label.redattoDa" />
										</div>
									</div>
								</div>
							</fieldset>
							<fieldset class="campiFiltro">
								<legend class="legendFiltro">
									<spring:message code="filtri.area" />
								</legend>
								<div class="col-lg-4 col-md-4 col-sm-6 Input">
									<div class="boxInput">
										<select class="form-control Input-text" name="codNazione" id="nazione"></select>
										<div class="scegliere">
											<spring:message code="label.nazione" />
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 Input">
									<div class="boxInput">
										<select class="form-control Input-text" name="codArea" id="areaterr"></select>
										<div class="scegliere">
											<spring:message code="label.areaterr" />
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 Input">
									<spring:message code="placeholder.estero" var="phEstero" />
									<div class="boxInput">
										<input type="text" class="form-control Input-text" name="comuneEstero" id="estero" placeholder="${phEstero}" maxlength="100">
										<div class="scegliere">
											<spring:message code="label.estero" />
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 Input">
									<div class="boxInput">
										<select class="form-control Input-text" name="codRegione" id="regione"></select>
										<div class="scegliere">
											<spring:message code="label.regione" />
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 Input multipl">
									<div class="boxInput">
										<select class="form-control Input-text multi" name="codProvinciaSet[]" id="provincia" multiple="multiple"></select>
										<div class="scegliere">
											<spring:message code="label.provincia" />
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 Input multipl">
									<div class="boxInput">
										<select class="form-control Input-text multi" name="codComuneSet[]" id="comun" multiple="multiple"></select>
										<div class="scegliere">
											<spring:message code="label.comun" />
										</div>
									</div>
								</div>
							</fieldset>
						</div>
						<div class="row" style="margin-left: 0px; margin-right: 0px">
							<div class="col-md-12">
								<div style="text-align: right">
									<button type="button" id="resetevento" class="btn btn-primary">
										<spring:message code="button.resetfiltro" />
									</button>
									<button type="button" id="cercaevento" class="btn btn-primary">
										<spring:message code="button.filtraevento" />
									</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="row" style="margin-top: 20px; margin-bottom: 20px;">
				<div class="col-md-12">
						<button type="button" id="excel" class="btn btn-primary" style="float: left; border: 0px !important; padding: 0px 0px 0px 0px !important;">
						<span>Scarica eventi</span>
						
								<i style="display:none;margin-left:10px" class="fa fa-refresh fa-spin"></i>		
							</button>		
				</div>
			</div>
			<table id="t_eventi" class="table table-striped table-bordered" style="width: 2000px !important; overflow: hidden"></table>
		</div>
		<div class="tab-pane fade" id="segnalazionibox">
			<div class="accordionF">
				<div class="accordionF-header" style="text-align: left; color: #003A54;">
					<img src="${contextPath}/assets/images/equalizer.svg" style="margin-right: 20px;">
					<spring:message code="label.filtroavanzato" />
					<div style="float: right; padding-top: 8px;">
						<img src="${contextPath}/assets/images/FRECCIA_GIU.svg" class="frecciafiltro" style="width: 18px;">
					</div>
				</div>
				<div class="accordionF-content">
					<form id="formSegnFilter" name="formSegnFilter" accept-charset="UTF-8">
						<div class="row" style="margin-top: 20px">
							<div class="col-lg-4 col-md-4 col-sm-6 Input">
								<spring:message code="placeholder.titolo" var="phTitolo" />
								<div class="boxInput">
									<input type="text" class="form-control Input-text" name="titolo" id="titoloS" placeholder="${phTitolo}" maxlength="100">
									<div class="scegliere">
										<spring:message code="label.titolosegnalazione" />
									</div>
								</div>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-6 Input  inputicondata">
								<spring:message code="placeholder.seldata" var="phData" />
								<div class="boxInput">
									<input type="text" class="form-control-date Input-text start-d data" name="dataDa" readonly id="dataDalS" placeholder="${phData}">
									<div class="scegliere">
										<spring:message code="label.datadal" />
									</div>
								</div>
							</div>

							<div class="col-lg-4 col-md-4 col-sm-6 Input  inputicondata">
								<spring:message code="placeholder.seldata" var="phData" />
								<div class="boxInput">
									<input type="text" class="form-control-date Input-text end-d data" name="dataA" readonly id="dataAlS" placeholder="${phData}">
									<div class="scegliere">
										<spring:message code="label.dataal" />
									</div>
								</div>
							</div>

							<div class="col-lg-4 col-md-4 col-sm-6 Input">
								<div class="boxInput">
									<select class="form-control Input-text" name="codIstat" id="comunS"></select>
									<div class="scegliere">
										<spring:message code="label.comun" />
									</div>
								</div>
							</div>

							<div class="col-lg-4 col-md-4 col-sm-6 Input">
								<spring:message code="placeholder.estero" var="phComune" />
								<div class="boxInput">
									<input type="text" class="form-control Input-text" name="comuneEstero" id="comunEstS" placeholder="${phComune}">
									<div class="scegliere">
										<spring:message code="label.estero" />
									</div>
								</div>
							</div>

							<div class="col-lg-4 col-md-4 col-sm-6 Input">
								<div class="boxInput">
									<select class="form-control Input-text" name="status" id="statoS"></select>
									<div class="scegliere">
										<spring:message code="label.statosegnalazione" />
									</div>
								</div>
							</div>
						</div>

						<div class="row" style="margin-left: 0px; margin-right: 0px">
							<div class="col-md-12">
								<div style="text-align: right">
									<button type="button" id="cercasegnalazione" class="btn btn-primary" style="float: right">
										<spring:message code="button.filtrasegnalazione" />
									</button>
									<button type="button" id="resetsegnalazione" class="btn btn-primary" style="float: right">
										<spring:message code="button.resetfiltro" />
									</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<table id="t_segnalazioni" class="table table-striped table-bordered" style="width: 2000px !important; overflow: hidden">
				<thead>
					<tr>
						<th><spring:message code="datatable.id" /></th>
						<th><spring:message code="datatable.azioni" /></th>
						<th><spring:message code="datatable.titolo" /></th>
						<th><spring:message code="datatable.dataDal" /></th>
						<th><spring:message code="datatable.dataAl" /></th>
						<th><spring:message code="datatable.comune" /></th>
						<th><spring:message code="datatable.riferimento" /></th>
						<th><spring:message code="datatable.dataIns" /></th>
						<th><spring:message code="datatable.stato" /></th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>

<script type="text/javascript" src="${contextPath}/assets/js/sigeajs/filtroEvento.js"></script>