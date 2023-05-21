<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div style="padding: 20px 10px 10px 0px; height: auto">
	<div class="dms-breadcrumb">
		<a href="home" title="Gestione eventi" style="color: #000000;">Gestione eventi</a> > <span id="breadcrumbs">Ricevute eventi finanziati</span>
	</div>
	<div>
		<h4 class="box-title" style="font-size: 24px; line-height: 26px; padding-left: 0px">
			<span><strong>Ricevute eventi finanziati</strong></span>
		</h4>
		<div class="row" style="margin-bottom: 0px;">
			<div class="col-md-2" style="text-align: center; padding: 20px;">
				<img src="${contextPath}/assets/svg/ricevute.svg" style="width: 120px;">
			</div>
			<div class="col-md-10" style="padding: 20px; margin: 3% 0px;">
				<div class="accordion-content">Il servizio "Ricevute eventi finanziati" ti consente, per ogni evento, di effettuare il download della prima ricevuta inviata al momento della richiesta di valutazione e di una ricevuta sempre aggiornata. Ogni ricevuta riporta le informazioni sul bando, sul progetto e i dettagli dell'evento.</div>
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
						<option data-target="#campiFiltroBandi">Bandi</option>
						<option data-target="#campiFiltroPromotore">Promotore</option>
					</select>
					<div class="scegliere">Categoria di filtro</div>
				</div>
			</div>
			<ul class="nav nav-tabs" id="tabFilter" role="tablist" style="margin-bottom: 15px;">
				<li class="nav-item active" style="cursor: pointer;"><a href="#campiFiltroBandi" role="tab" data-toggle="tab" class="nav-link"
					style="cursor: pointer">Bandi</a></li>
				<li class="nav-item" style="cursor: pointer;"><a href="#campiFiltroPromotore" role="tab" data-toggle="tab" class="nav-link"
					style="cursor: pointer">Promotore</a></li>
			</ul>
			<form id="formEventFilter" name="formEventFilter" accept-charset="UTF-8" style="margin-bottom: 0px;">
				<div class="tab-content" id="myTabFEContent" style="padding-top: 10px; background: transparent; min-height: 80px; margin-bottom: 0px;">
					<div class="tab-pane fade active in" id="campiFiltroBandi">

						<div class="col-lg-12 col-md-12 col-sm-12 Input">
							<div class="boxInput">
								<select class="form-control Input-text" name="bandoId" id="bandoId"></select>
								<div class="scegliere">Titolo bando</div>
							</div>
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 Input">
							<div class="boxInput">
								<select class="form-control Input-text" name="progettoId" id="progettoId" disabled="disabled"></select>
								<div class="scegliere">Tipo progetto</div>
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
				<span>Scarica eventi finanziati</span> <i style="display: none; margin-left: 10px" class="fa fa-refresh fa-spin"></i>
			</button>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12" id="bloccorevisioni">
			<table id="t_eventifinanziati" class="table table-striped table-bordered tabricevute"
				style="width: 100%; max-width: 1060px !important; overflow: hidden"></table>
		</div>
	</div>
</div>
<div class="modal esitoArrivo" tabindex="-1" role="dialog" id="modalRevisioni" data-keyboard="false">
	<div class="modal-dialog" role="document" style="width: 1140px; top: 5%; margin: auto; width: -moz-fit-content">
		<div class="modal-content modal-lg-mes" id="datiBando" style="font-size: 14px !important">
			<div class="modal-header">
				<h4 class="modal-title">
					<span id="strutturarevisione"></span>: Elenco revisioni
				</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close" style="opacity: 1;">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" style="font-size: 16px; text-align: left; line-height: 28px; float: left; width:100%">
				<div class="row" style="margin-top: 10px; margin-right: 0px; margin-left: 0px">
					<div class="col-md-12" style="padding: 0px;">
						<input type="hidden" name="idevento" id="idevento">
						<button type="button" id="revisioni" class="btn btn-primary" style="float: left; border: 0px !important; padding: 0px 0px 0px 0px !important;">
							<span>Scarica elenco revisioni</span> <i style="display: none; margin-left: 10px" class="fa fa-refresh fa-spin"></i>
						</button>
					</div>
				</div>
				<div class="row" style="margin-top: 10px; margin-right: 0px; margin-left: 0px">
					<table id="t_revisionimodal" class="table table-striped table-bordered" style="width: 100%; max-width: 1066px !important; overflow: hidden;">
						<thead>
							<tr>
								<th><spring:message code="datatable.id" /></th>
								<th><spring:message code="datatable.revisioni.tipo" /></th>
								<th><spring:message code="datatable.revisioni.data" /></th>
								<th><spring:message code="datatable.revisioni.operazioni" /></th>
								<th><spring:message code="datatable.revisioni.utente" /></th>
								<th><spring:message code="datatable.revisioni.stato" /></th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer" style="text-align: center">
				<button type="button" class="btn btn-primary invia" style="float: none" data-dismiss="modal">Chiudi</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="${contextPath}/assets/js/sigeajs/ricevute.js"></script>