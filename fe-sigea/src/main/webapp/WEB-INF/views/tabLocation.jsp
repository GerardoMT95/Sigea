<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="row" style="margin-left: 0px">
	<div class="col-md-12 Input">
		<button type="button" class="btn btn-primary invia" style="float: LEFT; margin-left: 0px; margin-right: 0px; margin-bottom: 10px; margin-top: 10px;"
			data-bindingonload data-addbutton="locationSet" data-counter="-1" data-ph="_PH_">
			<i class="dms-wk-icon-add" aria-hidden="true"></i>
			<spring:message code="label.location.aggiungi" />
		</button>
	</div>
</div>
<div class="tab-content" id="myTabContent4">
	<div data-dynamic="locationSet" class="contalocation">
		<div hidden="true" data-template="locationSet_PH_" style="border-bottom: 1px solid #cecece; padding-top: 40px; padding-bottom: 40px;">
			<div class="row" style="margin-left: 0px">
				<div class="col-md-12 Input validvalue" style="padding-bottom: 12px">
					<div class="scegliere" style="margin-bottom: -5px; left: -5px;">Dove *</div>
					<div class="col-md-2 col-sm-3 Input">
						<input type="radio" name="locationSet_PH_.puglia" value="true" class="radio-ruolo option-input inlist radio" checked
							data-dynabindingonload="clickRadioPuglia_PH_" data-event="change">
						<spring:message code="label.location.puglia" />
					</div>
					<div class="col-md-3 col-sm-4 Input validvalue">
						<input type="radio" name="locationSet_PH_.puglia" value="false" class="radio-ruolo option-input inlist radio"
							data-dynabindingonload="clickRadioPuglia_PH_" data-event="change">
						<spring:message code="label.location.nopuglia" />
					</div>
				</div>
			</div>
			<input type="hidden" name="locationSet_PH_.locationId" readonly> <input type="hidden" name="locationSet_PH_.fgPrincipale" readonly>
			<div class="row" style="margin-top: 10px; margin-right: 0px; margin-left: 0px">
				<div class="col-md-6 Input" style="height: 80px !important; margin-bottom: 10px;">
					<div class="boxInput" style="margin-bottom: 0px !important;">
						<input type="hidden" name="locationSet_PH_.codNazione" readonly> <select class="form-control Input-text" name="locationSet_PH_.nazione"
							data-initialize="_PH_selectizeNazione"></select>
						<div class="scegliere">
							<spring:message code="label.location.nazione" />
						</div>
					</div>
				</div>
				<div class="col-md-6 Input" style="height: 80px !important; margin-bottom: 10px;">
					<div class="boxInput" style="margin-bottom: 0px !important;">
						<input type="hidden" name="locationSet_PH_.codRegione" readonly> <select class="form-control Input-text" name="locationSet_PH_.regione"
							data-initialize="_PH_selectizeRegione"></select>
						<div class="scegliere">
							<spring:message code="label.location.regione" />
						</div>
					</div>
				</div>
				<div class="col-md-6 Input" style="height: 80px !important; margin-bottom: 10px;">
					<div class="boxInput" style="margin-bottom: 0px !important;">
						<input type="hidden" name="locationSet_PH_.codProvincia" readonly> <select class="form-control Input-text"
							name="locationSet_PH_.provincia" data-initialize="_PH_selectizeProvincia"></select>
						<div class="scegliere">
							<spring:message code="label.location.provincia" />
						</div>
					</div>
				</div>
				<div class="col-md-6 Input" style="height: 80px !important; margin-bottom: 10px;">
					<div class="boxInput" style="margin-bottom: 0px !important;">
						<input type="hidden" name="locationSet_PH_.codComune" readonly> <input type="hidden" name="locationSet_PH_.codArea" readonly> <input
							type="hidden" name="locationSet_PH_.area" readonly> <select class="form-control Input-text" name="locationSet_PH_.comune"
							data-initialize="_PH_selectizeComune"></select>
						<div class="scegliere">
							<spring:message code="label.location.comune" />
						</div>
					</div>
				</div>
				<div class="col-md-6 Input" style="height: 80px !important; margin-bottom: 10px;">
					<spring:message code="placeholder.estero" var="phEstero" />
					<div class="boxInput" style="margin-bottom: 0px !important;">
						<input type="text" class="form-control Input-text" name="locationSet_PH_.comuneEstero" maxlength="100" placeholder="Inserisci">
						<div class="scegliere">
							<spring:message code="label.location.estero" />
						</div>
					</div>
				</div>
				<div class="col-md-6 Input" style="height: 80px !important; margin-bottom: 10px; height: auto">
					<spring:message code="placeholder.location.nomelocation" var="phLocation" />
					<div class="boxInput" style="margin-bottom: 0px !important;">
						<input type="text" class="form-control Input-text" name="locationSet_PH_.nomeLocation" maxlength="300" placeholder="Inserisci">
						<div class="scegliere">
							<spring:message code="label.location.nome" />
						</div>
					</div>
				</div>
			
				<div class="col-md-6 Input" style="height: 80px !important; margin-bottom: 10px; height: auto">
					<spring:message code="placeholder.location.cap" var="phCap" />
					<div class="boxInput" style="margin-bottom: 0px !important;">
						<input type="text" class="form-control Input-text" name="locationSet_PH_.cap"
							oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" maxlength="5" placeholder="Inserisci" />
						<div class="scegliere">
							<spring:message code="label.location.cap" />
						</div>
					</div>
				</div>
				<div class="col-md-6 Input" style="height: 80px !important; margin-bottom: 10px; height: auto">
					<spring:message code="placeholder.location.link" var="phLink" />
					<div class="boxInput" style="margin-bottom: 0px !important;">
						<input type="text" class="form-control Input-text" name="locationSet_PH_.link" maxlength="100"
							placeholder="Inserisci il link per seguire l'evento online">
						<div class="scegliere">
							<spring:message code="label.location.link" />
						</div>
					</div>
				</div>
				<div class="col-md-12 Input" style="height: 40px;">
				<spring:message code="label.location.text" />
				</div>
				
				
				<div class="col-md-6 Input" style="height: 80px !important; margin-bottom: 10px; height: auto">
					<spring:message code="placeholder.location.indirizzo" var="phIndirizzo" />
					<div class="boxInput" style="margin-bottom: 0px !important;">
						<input type="text" class="form-control Input-text" name="locationSet_PH_.indirizzo" maxlength="300" placeholder="Inserisci">
						<div class="scegliere">
							<spring:message code="label.location.indirizzo" />
						</div>
					</div>
				</div>
				<div class="col-md-6 Input" style="height: 80px !important; margin-bottom: 10px; height: auto">
					<spring:message code="placeholder.location.lat" var="phLat" />
					<div class="boxInput" style="margin-bottom: 0px !important;">
						<input type="text" class="form-control Input-text" name="locationSet_PH_.latitudine"
							oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" maxlength="100"
							placeholder="Inserisci il valore in gradi decimali">
						<div class="scegliere">
							<spring:message code="label.location.latitudine" />
						</div>
					</div>
				</div>
				<div class="col-md-6 Input" style="height: 80px !important; margin-bottom: 10px; height: auto">
					<spring:message code="placeholder.location.lon" var="phLon" />
					<div class="boxInput" style="margin-bottom: 0px !important;">
						<input type="text" class="form-control Input-text" name="locationSet_PH_.longitudine"
							oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" maxlength="100"
							placeholder="Inserisci il valore in gradi decimali">
						<div class="scegliere">
							<spring:message code="label.location.longitudine" />
						</div>						
					</div>
				</div>
				<div class="col-md-6 Input" style="float: right; height: 80px !important; margin-bottom: 10px; height: auto">
					<button type="button" class="removeButton setlatlong" data-name="locationSet_PH_.setlatlong"
						><i class="dms-wk-icon-pin" aria-hidden="true"></i> <spring:message code="label.location.button" /></button>
				</div>
			</div>
			<div class="row" style="padding: 0 15px;">
				<div class="col-md-12 Input">
					<button type="button" style="float: right; padding-right: 15px !important;" data-deletebutton="locationSet_PH_" class="removeButton">
						<i class="dms-wk-icon-delete" aria-hidden="true"></i>
						<spring:message code="label.location.rimuovi" />
					</button>
				</div>
			</div>
		</div>
	</div>
</div>

<link rel="stylesheet" href="${contextPath}/assets/css/leaflet.css" />
<link rel="stylesheet" href="${contextPath}/assets/css/leaflet-locationpicker.css" />

<div class="modal" tabindex="-1" role="dialog" id="modallocation">
	<div class="modal-dialog latlon" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Scegli luogo sulla mappa</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form id="fixedContAlwaysOpen">
					Posiziona il punto d'interesse sulla mappa. In base alla posizione scelta, verranno completati in automatico i campi latitudine, longitudine e indirizzo.<br />
					<div style="min-height: 450; min-width: 200;">
					
						<input id="geoloc5" type="text" value="" size="20" class="form-control Input-text" readonly style="display:none;" />
						<input id="geolocaddress" type="text" value="" size="20" class="form-control Input-text" readonly style="display:none;" />
						
						<div id="fixedMapCont" style="border: 1px solid black; min-height: 450; min-width: 200; margin-top: 20px;"></div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-primary cancella">Annulla</button>
				<button type="button" id="salvaevento" onclick="invialatlong()" class="btn btn-primary invia">Salva luogo</button>
			</div>
		</div>
	</div>
</div>

<script src="${contextPath}/assets/js/leaflet.js"></script>
<script src="${contextPath}/assets/js/esri-leaflet.js"></script>
<script src="${contextPath}/assets/js/esri-leaflet-geocoder.js"></script>
<script src="${contextPath}/assets/js/leaflet-locationpicker.js"></script>
<script>

$(document).on("click", ".setlatlong", function(){
	
	var id = $(this).attr("data-name");
	id = id.replace("locationSet[", "");
	id = id.replace("].setlatlong", "");
	
	$("#salvaevento").attr("onclick","invialatlong("+id+")");
	$("#geoloc5").val('');
	$("#fixedMapCont").empty();
	$("#modallocation").modal('show');
	$('#geoloc5').leafletLocationPicker({
			alwaysOpen: true,
			mapContainer: "#fixedMapCont"
	});
});

function invialatlong(id){
	$("#fixedMapCont").empty();
	$("#modallocation").modal('hide');
	var latlong = $("#geoloc5").val();	
	var address = $("#geolocaddress").val();	
	var splitlalong = latlong.split(",");	
	$("input[name='locationSet["+id+"].latitudine']").val(splitlalong[0]);
	$("input[name='locationSet["+id+"].longitudine']").val(splitlalong[1]);
	$("input[name='locationSet["+id+"].indirizzo']").val(address);
}

</script>


