<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="row" style="margin-bottom: 15px">
	<div class="col-md-12">
		<spring:message code="label.media.bloccodocumenti" />
		<br />
		<br />
		<div id="mybutton" class="btn btn-primary invia">
			<input id="file-03" type="file" name="documentoSetLoad" data-feed="fileDocumentiFeed" onchange="fileClickDoc('filebut03');" />
			<button id="filebut03" type="button" class="btn btn-primary" data-bindingonload data-addbutton="documentoSet" data-counter="-1" data-ph="_PH_"
				data-feeder="documentoSetLoad" style="display: none;">
				<spring:message code="label.media.carica" />
			</button>
			<i class="dms-wk-icon-upload"></i> Carica allegato
		</div>
	</div>
</div>
<div class="row" style="margin: 0 0px;">
	<div class="col-md-12 Input">
		<div class="mobile-input" id="boxmobileLinguaDoc" style="display: none; padding-bottom: 15px;">
			<select id="mobiletabLinguaDoc" class="form-control Input-text">
				<option data-target="[data-tab='itdocumenti']">IT</option>
				<option data-target="[data-tab='endocumenti']">EN</option>
				<option data-target="[data-tab='spdocumenti']">SP</option>
				<option data-target="[data-tab='frdocumenti']">FR</option>
				<option data-target="[data-tab='dedocumenti']">DE</option>
				<option data-target="[data-tab='rudocumenti']">RU</option>
			</select>
			<div class="scegliere">Cambia lingua</div>
		</div>
		<ul class="nav nav-tabs" id="myTab9" role="tablist" style="margin-bottom: 15px">
			<li class="nav-item active" style="cursor: pointer" data-tablink="itd"><a data-target="[data-tab='itdocumenti']" data-toggle="tab"
				class="nav-link active" style="cursor: pointer">IT</a></li>
			<li class="nav-item" style="cursor: pointer" data-tablink="end"><a data-target="[data-tab='endocumenti']" data-toggle="tab"
				class="nav-link active" style="cursor: pointer">EN</a></li>
			<li class="nav-item" style="cursor: pointer" data-tablink="spd"><a data-target="[data-tab='spdocumenti']" data-toggle="tab"
				class="nav-link active" style="cursor: pointer">SP</a></li>
			<li class="nav-item" style="cursor: pointer" data-tablink="frd"><a data-target="[data-tab='frdocumenti']" data-toggle="tab"
				class="nav-link active" style="cursor: pointer">FR</a></li>
			<li class="nav-item" style="cursor: pointer" data-tablink="ded"><a data-target="[data-tab='dedocumenti']" data-toggle="tab"
				class="nav-link active" style="cursor: pointer">DE</a></li>
			<li class="nav-item" style="cursor: pointer" data-tablink="rud"><a data-target="[data-tab='rudocumenti']" data-toggle="tab"
				class="nav-link active" style="cursor: pointer">RU</a></li>
		</ul>
	</div>
</div>
<div data-dynamic="documentoSet" class="contadocumenti">
	<div class="row" hidden="true" data-template="documentoSet_PH_"
		style="border-bottom: 1px solid #c2c2c2; padding-bottom: 15px; padding-top: 15px; padding-left: 15px;">
		<input type="hidden" name="documentoSet_PH_.documentoId" readonly> <input type="hidden" name="documentoSet_PH_.estensione" readonly>
		<input type="hidden" name="documentoSet_PH_.dimensione" readonly> <input type="hidden" name="documentoSet_PH_.daPubblicare" value="true">
		<div class="tab-content" id="myTContent">
			<div class="col-md-6 Input blockaccordiumx">
				<div class="boxInput" id="nomefilebox" style="margin-bottom: 0px;">
					<img src="${contextPath}/assets/svg/doc_nero.svg" style="margin-right: 20px;"> <span class="titoloinput"><input type="hidden"
						class="inputtotext totext" name="documentoSet_PH_.nomeOriginale"
						style="width: 90%; display: inline-block; border: 0px !important; background-color: transparent !important; box-shadow: none; color: #003A54 !important;"
						maxlength="300" readonly /> </span>
				</div>
			</div>
			<div class="col-md-6 Input blockaccordiumx" style="text-align: right; height: 60px; background: #F2F2F2;">
				<div class="boxInput">
					<a href="${contextPath}" data-name="documentiSet_PH_Visualizza" data-dynabindingonload="initVisualizzaDoc_PH_" data-event="click"
						style="margin-left: 15px; font-size: 16px; font-weight: 600;">
						<i class="dms-wk-icon-show-details" aria-hidden="true"></i>
						<%-- <img src="${contextPath}/assets/images/VIEW.svg" style="margin-right: 10px;"> --%>
					<spring:message code="label.media.visualizza" /></a> <a href="${contextPath}" data-name="documentiSet_PH_Download"
						data-dynabindingonload="initDownloadDoc_PH_" data-event="click" style="margin-left: 15px; font-size: 16px; font-weight: 600;">
						<i class="dms-wk-icon-download" aria-hidden="true"></i>
				
						<%-- <img
						src="${contextPath}/assets/images/download.svg" style="margin-right: 10px;"> --%>
					<spring:message code="label.media.download" /></a>
					<button type="button" class="removeButton" data-deletebutton="documentoSet_PH_">
						<i class="dms-wk-icon-delete" aria-hidden="true"></i>
						<spring:message code="label.media.elimina" />
					</button>
				</div>
			</div>
			<div class="tab-pane fade active in blockaccordium" data-tab="itdocumenti">
				<div class="col-md-6 Input" style="margin-top: 15px">
					<spring:message code="placeholder.didascalia" var="phDidascalia" />
					<div class="boxInput">
						<input type="text" class="form-control Input-text" name="documentoSet_PH_.titoloMulti.IT" maxlength="300" placeholder="${phDidascalia}" />
						<div class="scegliere">
							<spring:message code="label.titoloevento.required" />
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane fade blockaccordium" data-tab="endocumenti">
				<div class="col-md-6 Input" style="margin-top: 15px">
					<spring:message code="placeholder.didascalia" var="phDidascalia" />
					<div class="boxInput">
						<input type="text" class="form-control Input-text" name="documentoSet_PH_.titoloMulti.EN" maxlength="300" placeholder="${phDidascalia}" />
						<div class="scegliere">
							<spring:message code="label.titoloevento" />
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane fade blockaccordium" data-tab="spdocumenti">
				<div class="col-md-6 Input" style="margin-top: 15px">
					<spring:message code="placeholder.didascalia" var="phDidascalia" />
					<div class="boxInput">
						<input type="text" class="form-control Input-text" name="documentoSet_PH_.titoloMulti.SP" maxlength="300" placeholder="${phDidascalia}" />
						<div class="scegliere">
							<spring:message code="label.titoloevento" />
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane fade blockaccordium" data-tab="frdocumenti">
				<div class="col-md-6 Input" style="margin-top: 15px">
					<spring:message code="placeholder.didascalia" var="phDidascalia" />
					<div class="boxInput">
						<input type="text" class="form-control Input-text" name="documentoSet_PH_.titoloMulti.FR" maxlength="300" placeholder="${phDidascalia}" />
						<div class="scegliere">
							<spring:message code="label.titoloevento" />
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane fade blockaccordium" data-tab="dedocumenti">
				<div class="col-md-6 Input" style="margin-top: 15px">
					<spring:message code="placeholder.titolo" var="phDidascalia" />
					<div class="boxInput">
						<input type="text" class="form-control Input-text" name="documentoSet_PH_.titoloMulti.DE" maxlength="300" placeholder="${phDidascalia}" />
						<div class="scegliere">
							<spring:message code="label.titoloevento" />
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane fade blockaccordium" data-tab="rudocumenti">
				<div class="col-md-6 Input" style="margin-top: 15px">
					<spring:message code="placeholder.titolo" var="phDidascalia" />
					<div class="boxInput">
						<input type="text" class="form-control Input-text" name="documentoSet_PH_.titoloMulti.RU" maxlength="300" placeholder="${phDidascalia}" />
						<div class="scegliere">
							<spring:message code="label.titoloevento" />
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-6 col-md-6 Input blockaccordiumx" style="margin-top: 15px">
				<spring:message code="placeholder.documenti.ordine" var="phOrdine" />
				<div class="boxInput">
					<input type="number" class="form-control Input-text itready" name="documentoSet_PH_.ordine" min="1" maxlength="10" placeholder="${phOrdine}" />
						<div class="scegliere">
							<spring:message code="label.documento.ordine.required" />
						</div>
					</div>
			</div>
			
		</div>
	</div>
</div>