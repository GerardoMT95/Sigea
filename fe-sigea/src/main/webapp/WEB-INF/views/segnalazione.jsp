<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div style="padding: 20px 10px 10px 0px; height: auto">
	<h4 class="box-title" style="font-size: 24px; line-height: 26px; padding-left: 0px">
		<strong><spring:message code="label.segnalazioneevento" /></strong>
	</h4>
	<div class="row">
		<div class="col-md-2" style="text-align: center;">
			<img src="${contextPath}/assets/images/marketing.svg">
		</div>
		<div class="col-md-10" id="centermobile" style="padding-top: 35px;">
			<div class="accordion-content" style="display: inline;">
				<spring:message code="label.segnalazioneevento.accordioncontent" />				
				Ricorda che devi indicare anche la <strong>Citt&agrave;</strong> o la <strong>Citt&agrave; estera</strong> in cui si svolge.
			</div>
		</div>
	</div>
</div>

<style>
textarea.resize {
	resize: vertical !important;
}
</style>

<div class="box user" id="boxusersigea">
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade active in" id="segnalabox">
			<form id="formEventReporting" name="formEventReporting" accept-charset="UTF-8">
				<div class="row" style="margin-top: 20px">
					<div class="col-md-4 Input">
						<spring:message code="placeholder.default" var="phNome" />
						<div class="boxInput">
							<input type="text" class="form-control Input-text" name="nome" id="nome" maxlength="255" placeholder="${phNome}">
							<div class="scegliere">
								<spring:message code="label.segnalazione.titolo" />
							</div>
						</div>
					</div>
					<div class="col-md-4 Input inputicondata">
						<spring:message code="placeholder.data" var="phDataDa" />
						<div class="boxInput">
							<input type="search" class="form-control-date Input-text start-date data" autocomplete="off" name="dataDa" id="dataDa"
								placeholder="${phDataDa}">
							<div class="scegliere">
								<spring:message code="label.datada" />
							</div>
						</div>
					</div>
					<div class="col-md-4 Input inputicondata">
						<spring:message code="placeholder.datafin" var="phDataA" />
						<div class="boxInput">
							<input type="search" class="form-control-date Input-text end-date data" autocomplete="off" name="dataA" id="dataA" placeholder="${phDataA}">
							<div class="scegliere">
								<spring:message code="label.dataa" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<input type="hidden" name="codIstat" id="codIstat" readonly>
					<div class="col-md-4 Input">
						<div class="boxInput">
							<select class="form-control Input-text" name="comune" id="comune" /></select>							
							<label id="comuneobberror" style="display:none; font-size: 12px; margin-left: 16px; line-height: 18px; top: -2px !important; left: -8px;
    					position: relative !important; color: #c10512 !important; font-weight: normal; text-transform: none;">* campo obbligatorio</label>
							<div class="scegliere">
								Citt&agrave;
							</div>
						</div>
					</div>
					<div class="col-md-4 Input">
						<spring:message code="placeholder.default" var="phEstero" />
						<div class="boxInput">
							<input type="text" class="form-control Input-text" name="comuneEstero" id="comEstero" placeholder="${phEstero}" maxlength="100">
							<div class="scegliere">
								Citt&agrave; estera
							</div>
						</div>
					</div>
					<div class="col-md-4 Input">
						<spring:message code="placeholder.default" var="phLocation" />
						<div class="boxInput">
							<input type="text" class="form-control Input-text" name="location" maxlength="500" placeholder="${phLocation}">
							<div class="scegliere">
								<spring:message code="label.location" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 Input">
						<spring:message code="placeholder.default" var="phIndirizzo" />
						<div class="boxInput">
							<input type="text" class="form-control Input-text" name="indirizzo" maxlength="500" placeholder="${phIndirizzo}">
							<div class="scegliere">
								<spring:message code="label.indirizzo" />
							</div>
						</div>
					</div>
					<div class="col-md-12 Input" style="margin-bottom: 20px">
						<spring:message code="placeholder.default" var="phDescrizione" />
						<div class="boxInput">
							<div class="scegliere">
								<spring:message code="label.descrizione" />
							</div>
							<textarea class="form-control Input-text resize" name="descrizione" style="min-height: 150px; max-height: 200px" maxlength="4000"
								placeholder="${phDescrizione}"></textarea>

						</div>
					</div>

					<div class="col-md-12 Input">
						<spring:message code="placeholder.default" var="phRiferimento" />
						<div class="boxInput">
							<div class="scegliere">
								<spring:message code="label.sitoevento" />
							</div>
							<textarea class="form-control Input-text resize" name="riferimento" style="min-height: 50px; max-height: 200px" maxlength="4000"
								placeholder="${phRiferimento}"></textarea>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="row">
			<div class="col-md-12" style="text-align: left;">
				<button type="button" onclick="window.location.href='${areaRiservata}'" class="btn btn-primary cancella" style="margin-right: 24px; float: left;">
					<spring:message code="button.indietro" />
				</button>
				<button type="button" id="segnalaevento" onclick="salvaSegnalazione()" class="btn btn-primary invia" style="float: right;">
					<spring:message code="button.segnalaevento" />
				</button>
				<button type="button" id="resetcampi" class="btn btn-primary cancella" style="float: right; margin-right: 24px">Azzera</button>
			</div>
		</div>
	</div>
</div>
<!-- inizio modal -->
<div class="modal fade" id="Modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document" style="width: 500px">
		<div class="modal-content">
			<div class="modal-header" style="background-color: transparent !important">
				<h4 class="modal-title" id="ModalLabel">
					<spring:message code="label.modal.titolo" htmlEscape="false" />
				</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div id="titolomodalfooter" style="text-align: center;">
					<i class="icon-errore" style="font-size: 64px; color: #a66300;"></i>
				</div>
				<br /> <strong><spring:message code="label.modal.titolofooter" htmlEscape="false" /></strong><br />
				<spring:message code="label.modal.sottotitolo" htmlEscape="false" />
				<br />
				<br />
				<div id="bloccotabellamodel">
					<table id="t_ricercaEvento" class="table table-striped table-bordered"
						style="width: 100% !important; max-width: 450px !important; overflow: hidden;">
						<thead>
							<tr>
								<th style="vertical-align: middle"><spring:message code="datatable.modal.nomeevento" htmlEscape="false" /></th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer" style="text-align: center">
				<button type="button" id="annullaevento" onclick="annullaSegnalazione()" class="btn btn-primary cancella">No</button>
				<button type="button" id="salvaevento" onclick="inviaSegnalazione()" class="btn btn-primary invia">Si</button>
			</div>
		</div>
	</div>
</div>
<!-- fine modal -->
<script>
var dataserver = '${datadioggi}';
</script>
<script type="text/javascript" src="${contextPath}/assets/js/sigeajs/segnalazione.js"></script>