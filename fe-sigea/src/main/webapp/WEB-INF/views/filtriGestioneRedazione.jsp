<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div style="padding: 20px 10px 10px 0px; height: auto">
	<div class="dms-breadcrumb">
		<a href="home" title="Gestione eventi" style="color: #000000;">Gestione eventi</a> > <span id="breadcrumbs">Redazione eventi</span>
	</div>
	<div>
		<h4 class="box-title" style="font-size: 24px; line-height: 26px; padding-left: 0px">
			<span><strong>Redazione eventi</strong></span>
		</h4>
		<div class="row" style="margin-bottom: 0px;">
			<div class="col-md-2" style="text-align: center; padding: 20px;">
				<img src="${contextPath}/assets/svg/redazione.svg" style="height: 120px;">
			</div>
			<div class="col-md-10" style="padding: 20px; margin: 3% 0px;">
				<div class="accordion-content">
					<spring:message code="label.redazioneevento.accordioncontent" />
				</div>
			</div>
		</div>
	</div>
</div>
<div class="box user" id="filtroboxuser" style="margin-bottom: 30px;">
	<h4 class="box-title" style="font-size: 24px; line-height: 26px; padding-left: 0px">
		<span><strong>${denominazione}</strong></span>
	</h4>
	<div class="accordionF">
		<div class="accordionF-header" style="text-align: left; color: #003A54;">
			<img src="${contextPath}/assets/images/equalizer.svg" style="margin-right: 20px;">
			<spring:message code="label.filtroavanzato" />
			<div style="float: right; padding-top: 8px;">
				<img src="${contextPath}/assets/images/FRECCIA_GIU.svg" class="frecciafiltro" style="width: 18px;">
			</div>
		</div>
		<div class="accordionF-content" style="background-color: #F2F2F2;">
			<div class="col-lg-12 col-md-12 col-sm-12 Input" id="boxmobileFilter" style="display: none; border-bottom: 1px solid #ABABAB;">
				<div class="boxInput mobile-input">
					<select id="mobiletabFilter" class="form-control Input-text">
						<option data-target="#campiFiltroEvento">Evento</option>
						<option data-target="#campiFiltroArea">Luogo</option>
						<option data-target="#campiFiltroPubblicazione">Pubblicazione</option>
						<option data-target="#campiFiltroPromotore">Promotore</option>
					
					</select>
					<div class="scegliere">Categoria di filtro</div>
				</div>
			</div>
			<ul class="nav nav-tabs" id="tabFilter" role="tablist" style="margin-bottom: 15px;">
				<li class="nav-item active" style="cursor: pointer;"><a href="#campiFiltroEvento" role="tab" data-toggle="tab" class="nav-link"
					style="cursor: pointer">Evento</a></li>
				<li class="nav-item" style="cursor: pointer"><a href="#campiFiltroArea" role="tab" data-toggle="tab" class="nav-link" id="segnalazioni-tab"
					style="cursor: pointer">Luogo</a></li>
				<li class="nav-item" style="cursor: pointer;"><a href="#campiFiltroPubblicazione" role="tab" data-toggle="tab" class="nav-link"
					style="cursor: pointer">Pubblicazione</a></li>
				<li class="nav-item" style="cursor: pointer;"><a href="#campiFiltroPromotore" role="tab" data-toggle="tab" class="nav-link"
					style="cursor: pointer">Promotore</a></li>		
			</ul>
			<form id="formEventFilter" name="formEventFilter" accept-charset="UTF-8" style="margin-bottom: 0px;">
				<div class="tab-content" id="myTabFEContent" style="padding-top: 10px; background: transparent; min-height: 220px; margin-bottom: 0px;">
					<div class="tab-pane fade active in" id="campiFiltroEvento">
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
							<div class="boxInput">
								<select class="form-control Input-text" name="tipologia2" id="tipologia2"></select>
								<div class="scegliere">
									<spring:message code="label.tipologia2" />
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
									<input type="search" autocomplete="off" class="form-control-date Input-text startS-d data" name="dataInsDa" id="dataInsDa"
										placeholder="${phData}">
									<div class="scegliere">
										<spring:message code="label.datainsDa" />
									</div>
								</div>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-6 Input  inputicondata">
								<spring:message code="placeholder.seldata" var="phData" />
								<div class="boxInput">
									<input type="search" autocomplete="off" class="form-control-date Input-text endS-d data" name="dataInsA" id="dataInsA"
										placeholder="${phData}">
									<div class="scegliere">
										<spring:message code="label.datainsA" />
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 Input  inputicondata">
							<spring:message code="placeholder.seldata" var="phData" />
							<div class="boxInput">
								<input type="search" autocomplete="off" class="form-control-date Input-text start-d data" name="dataDa" id="dataDal" placeholder="${phData}">
								<div class="scegliere">
									<spring:message code="label.datadal" />
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 Input  inputicondata">
							<spring:message code="placeholder.seldata" var="phData" />
							<div class="boxInput">
								<input type="search" autocomplete="off" class="form-control-date Input-text end-d data" name="dataA" id="dataAl" placeholder="${phData}">
								<div class="scegliere">
									<spring:message code="label.dataal" />
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 Input">
							<div class="boxInput">
								<select class="form-control Input-text" name="statoPubblicazione" id="statoPubb"></select>
								<div class="scegliere">
									<spring:message code="label.statoevento" />
								</div>
							</div>
						</div>
						
						<div class="col-lg-4 col-md-4 col-sm-6 Input  inputicondata">
							<spring:message code="placeholder.seldata" var="phData" />
							<div class="boxInput">
								<input type="search" autocomplete="off" class="form-control-date Input-text start-d data" name="dataAttivoDa" id="dataAttivoDa" placeholder="${phData}">
								<div class="scegliere">
									<spring:message code="label.dataAttivoDa" />
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 Input  inputicondata">
							<spring:message code="placeholder.seldata" var="phData" />
							<div class="boxInput">
								<input type="search" autocomplete="off" class="form-control-date Input-text end-d data" name="dataAttivoA" id="dataAttivoA" placeholder="${phData}">
								<div class="scegliere">
									<spring:message code="label.dataAttivoA" />
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 Input">
							<div class="boxInput">
								<select class="form-control Input-text multi" name="filtroMIBACT[]" id="filtroMIBACT" multiple="multiple"></select>
								<div class="scegliere">
									<spring:message code="label.mibact" />
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 Input">					
							<div class="boxInput">						
								<select class="form-control Input-text multi" name="prodotti[]" id="prodotti" multiple="multiple"></select>
								<div class="scegliere">
									<spring:message code="label.prodotti" />
								</div>
							</div>
						</div>							
					</div>
					<div class="tab-pane fade" id="campiFiltroPubblicazione">
						<div class="col-lg-6 col-md-6 col-sm-6 Input">
							<div class="boxInput">
							<!--  	<select class="form-control Input-text" name="pubblicato" id="pubblicato"></select> -->
										
							<div class="scegliere">
									<spring:message code="label.pubblicato" />
							</div>
							<div class="col-md-4 col-sm-4 Input validvalue">
								<input type="radio" name="pubblicato" id="pubblicato" class="radio-ruolo option-input inlist radio" value="" checked>
								<spring:message code="label.pubblicato.nessunascelta" />
							</div>
							<div class="col-md-4 col-sm-4 Input validvalue">
								<input type="radio" name="pubblicato" id="pubblicato" class="radio-ruolo option-input inlist radio" value="Online">
								<spring:message code="label.pubblicato.online" />
							</div>
								<div class="col-md-4 col-sm-4 Input validvalue">
								<input type="radio" name="pubblicato" id="pubblicato" class="radio-ruolo option-input inlist radio" value="Offline">
								<spring:message code="label.pubblicato.offline" />
							</div>
																
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 Input multipl">
							<div class="boxInput">
								<select class="form-control Input-text multi" name="redazioniGenerali[]" id="redazioniGenerali" multiple="multiple"></select>
								<div class="scegliere">
									<spring:message code="label.pubblicatoDa" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade" id="campiFiltroPromotore">
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
					</div>
				
					<div class="tab-pane fade" id="campiFiltroArea">
						<div class="col-lg-4 col-md-5 col-sm-8 Input" style="height: auto">
							<div class="boxInput">
								<select class="form-control Input-text" name="smart" id="smart"></select>
								<div class="scegliere">
									<spring:message code="label.indicatore" />
								</div>
							</div>
						</div>
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
					</div>
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
	
	<div class="row">
		<div class="col-md-12">
			<table id="t_eventi" class="table table-striped table-bordered tabredazione" style="width: 100%; max-width: 1050px !important; overflow: hidden"></table>
		</div>
	</div>
</div>
<script type="text/javascript" src="${contextPath}/assets/js/sigeajs/filtriGestioneRedazione.js"></script>