<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div style="padding: 20px 0px 10px 0px; height: auto">
	<div class="dms-breadcrumb">
		<a href="home" title="Gestione eventi" style="color: #000000;">Gestione eventi</a> > <span id="breadcrumbs">Promozione eventi</span>
	</div>
	<div>
		<h4 class="box-title" style="font-size: 24px; line-height: 26px; padding-left: 0px">
			<span><strong>Promozione eventi</strong></span>
		</h4>
		<div class="row" style="margin-bottom: 0px;margin: 0px -20px;">
			<div class="col-md-2" style="text-align: center; padding: 20px;">
				<img src="${contextPath}/assets/svg/promozione.svg" style="width: 120px;">
			</div>
			<div class="col-md-10" style="padding: 40px 20px;">
				<div class="accordion-content">
					<spring:message code="label.promozioneevento.accordioncontent" />
				</div>				
			</div>
		</div>
		
		<div class="row" style="display:none; margin: 0px -20px;" id="strutturainattesa">
			<div class="col-md-12" style="text-align: center; padding: 20px;">					
				<div class="box alertdms">
				<div class="boxalert">
					<div class="row">
						<div class="col-md-12">
							<i class="dms-wk-icon-warning titolo" aria-hidden="true" style="font-size: 4rem; float:left;margin-right: 2rem;"></i> 
							<span style="font-size:16px; line-height: 40px; float: left;"><spring:message code="label.strutturainattesa" /></span>
						</div>
					</div>					
				</div>
				</div>				
			</div>
		</div>		
		
	</div>
</div>
<div class="box user" id="boxusersigea" style="margin-bottom: 30px;">
	<h4 class="box-title" style="font-size: 24px; line-height: 26px; padding-left: 0px">
		<span><strong>${denominazione}</strong></span>
	</h4>
	<div class="Input" id="boxmobilemyTabFE" style="display: none; padding-bottom: 15px; border-bottom: 1px solid #ABABAB;">
		<div class="boxInput mobile-input">
			<select id="mobilemyTabFE" class="form-control Input-text">
				<option value="eventi-tab"><spring:message code="tab.cercaevento" /></option>
				<option value="segnalazioni-tab"><spring:message code="tab.cercasegnalazione" /></option>
			</select>
			<div class="scegliere">Menu</div>
		</div>
	</div>
	<ul class="nav nav-tabs" id="myTabFE" role="tablist" style="height: auto !important;">
		<li class="nav-item active" style="cursor: pointer;" id="gesteventi"><a href="#eventibox" role="tab" data-toggle="tab" class="nav-link"
			id="eventi-tab" style="cursor: pointer"><spring:message code="tab.cercaevento" /></a></li>
		<li class="nav-item" style="cursor: pointer" id="gestsegnalazioni"><a href="#eventibox" role="tab" data-toggle="tab" class="nav-link"
			id="segnalazioni-tab" style="cursor: pointer"><spring:message code="tab.cercasegnalazione" /></a></li>
	</ul>
	<div class="tab-content" id="myTabFEContent" style="padding-top: 10px">
		<div class="tab-pane fade active in" id="eventibox">
			<div class="row">
				<div class="col-md-9" style="font-size: 16px;">
					<spring:message code="label.promozioneevento.boxfiltro" />
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
				<div class="accordionF-content" style="background-color: #F2F2F2;">

					<div class="col-lg-12 col-md-12 col-sm-12 Input" id="boxmobileFilter" style="display: none;">
						<div class="boxInput mobile-input">
							<select id="mobiletabFilter" class="form-control Input-text">
								<option data-target="#campiFiltroEvento">Evento</option>
								<option data-target="#campiFiltroArea">Luogo</option>
							</select>
							<div class="scegliere">Categoria di filtro</div>
						</div>
					</div>
					<ul class="nav nav-tabs" id="tabFilter" role="tablist" style="margin-bottom: 15px;">
						<li class="nav-item active" style="cursor: pointer;"><a href="#campiFiltroEvento" role="tab" data-toggle="tab" class="nav-link"
							style="cursor: pointer">Evento</a></li>
						<li class="nav-item" style="cursor: pointer"><a href="#campiFiltroArea" role="tab" data-toggle="tab" class="nav-link"
							style="cursor: pointer">Luogo</a></li>
					</ul>
					<form id="formEventFilter" name="formEventFilter" accept-charset="UTF-8" style="margin-bottom: 0px;">
						<div class="tab-content" id="myTabFEContent" style="padding-top: 10px; background: transparent; min-height: 200px; margin-bottom: 0px;">
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
									<spring:message code="placeholder.titolo" var="phTitolo" />
									<div class="boxInput">
										<input type="text" class="form-control Input-text" name="titolo" id="titolo" placeholder="${phTitolo}" maxlength="100">
										<div class="scegliere">
											<spring:message code="label.titoloevento" />
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
										<select class="form-control Input-text" name="stato" id="stato"></select>
										<div class="scegliere">
											<spring:message code="label.statoevento" />
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-6 Input">
									<div class="boxInput">
										<select class="form-control Input-text multi" name="filtroMIBACT[]" id="filtroMIBACT" multiple="multiple"></select>
										<!-- <input type="hidden" name="idMIBACT" readonly> -->
										<div class="scegliere">
											<spring:message code="label.mibact" />
										</div>
									</div>
								</div>
							</div>
							<div class="tab-pane fade" id="campiFiltroArea">
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
					<table id="t_eventi" class="table table-striped table-bordered tabpromozione" style="width: 100%; max-width: 1050px !important; overflow: hidden"></table>
				</div>
			</div>
		</div>
		<div class="tab-pane fade" id="segnalazionibox">
			<div class="row">
				<div class="col-md-12" style="font-size: 16px;">
					<spring:message code="label.validazioneevento.boxfiltro" />
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
				<div class="accordionF-content" style="background-color: #F2F2F2;">
					<div class="col-lg-12 col-md-12 col-sm-12 Input" id="boxmobileFilter2" style="display: none; border-bottom: 1px solid #ABABAB;">
						<div class="boxInput mobile-input">
							<select id="mobiletabFilter2" class="form-control Input-text">
								<option data-target="#campiFiltroSuggerimento">Suggerimento</option>
								<option data-target="#campiFiltroLuogo">Luogo</option>
							</select>
							<div class="scegliere">Categoria di filtro</div>
						</div>
					</div>
					<ul class="nav nav-tabs" id="tabFilterSug" role="tablist" style="margin-bottom: 15px;">
						<li class="nav-item active" style="cursor: pointer;"><a href="#campiFiltroSuggerimento" role="tab" data-toggle="tab" class="nav-link"
							style="cursor: pointer">Suggerimento</a></li>
						<li class="nav-item" style="cursor: pointer;"><a href="#campiFiltroLuogo" role="tab" data-toggle="tab" class="nav-link"
							style="cursor: pointer">Luogo</a></li>
					</ul>
					<form id="formSegnFilter" name="formSegnFilter" accept-charset="UTF-8">
						<div class="tab-content" id="myTabFEContentSug" style="padding-top: 10px; background: transparent; min-height: 100px; margin-bottom: 0px;">
							<div class="tab-pane fade active in" id="campiFiltroSuggerimento">
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
										<input type="search" autocomplete="off" class="form-control-date Input-text start-d data" name="dataDa" id="dataDalS"
											placeholder="${phData}">
										<div class="scegliere">
											<spring:message code="label.datadal" />
										</div>
									</div>
								</div>

								<div class="col-lg-4 col-md-4 col-sm-6 Input inputicondata">
									<spring:message code="placeholder.seldata" var="phData" />
									<div class="boxInput">
										<input type="search" autocomplete="off" class="form-control-date Input-text end-d data" name="dataA" id="dataAlS" placeholder="${phData}">
										<div class="scegliere">
											<spring:message code="label.dataal" />
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
							<div class="tab-pane fade" id="campiFiltroLuogo">
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
			<div class="row">
				<div class="col-md-12">
					<table id="t_segnalazioni" class="table table-striped table-bordered tabsegnalazione" style="width: 100%; max-width: 1050px !important; overflow: hidden">
						<thead>
							<tr>						
								<th><spring:message code="datatable.azioni" /></th>
								<th><spring:message code="datatable.titolo" /></th>
								<th><spring:message code="datatable.dataDal" /></th>
								<th><spring:message code="datatable.dataAl" /></th>
								<th><spring:message code="datatable.comune" /></th>
								<th><spring:message code="datatable.creatoda" /></th>
								<th><spring:message code="datatable.riferimento" /></th>
								<th><spring:message code="datatable.dataIns" /></th>
								<th><spring:message code="datatable.stato" /></th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
var statoStruttura = "${statoStruttura}";
</script>
<script type="text/javascript" src="${contextPath}/assets/js/sigeajs/filtriGestionePromozione.js"></script>