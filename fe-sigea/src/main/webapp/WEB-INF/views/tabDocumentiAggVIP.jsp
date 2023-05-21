<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="row">
	<div class="bloccotab">
		<div class="row">
			<div class="col-md-6" style="padding: 5px 40px 5px 40px">
				<span style="color: #003a54; font-size: 16px; font-weight: 600; line-height: 40px;">DOCUMENTI AGGIUNTIVI REDAZIONE</span>
			</div>
			<div class="col-md-6" id="immagine" style="padding: 5px 40px 5px 40px; text-align: right;">
				<div id="mybutton" class="btn btn-primary invia">
					<input id="file-04" type="file" name="documentoSetAggiuntoLoad" data-feed="fileDocumentiFeed" onchange="fileClickDocV('filebut04');" />
					<button id="filebut04" type="button" class="btn btn-primary" data-bindingonload data-addbutton="documentoSetAggiunto" data-counter="-1"
						data-ph="_PH_" data-feeder="documentoSetAggiuntoLoad" style="display: none;">
						<spring:message code="label.media.carica" />
					</button>
					<i class="dms-wk-icon-upload"></i> Carica allegato
				</div>
			</div>
		</div>
		<div data-dynamic="documentoSetAggiunto" style="padding: 0px 9px;" class="contadocumentivipagg">
			<div class="row" hidden="true" data-template="documentoSetAggiunto_PH_"
				style="margin: 10px 15px 30px 15px; background: #ffffff; border-top: 1px solid #F2F2F2;">
				<input type="hidden" name="documentoSetAggiunto_PH_.documentoId" readonly> <input type="hidden" name="documentoSetAggiunto_PH_.estensione"
					readonly> <input type="hidden" name="documentoSetAggiunto_PH_.dimensione" readonly>
				<div class="tab-content" id="myVIPdcContent">
					<div class="col-md-6 Input accordionlight" style="padding: 15px; background: #ffffff; cursor: pointer;">
						<div class="boxInput" id="nomefilebox" style="margin-bottom: 0px;">
							<img src="${contextPath}/assets/svg/doc_nero.svg" style="margin-right: 20px;"> <span class="titoloinput"> <input type="hidden"
								class="inputtotext totext" name="documentoSetAggiunto_PH_.nomeOriginale" readonly /></span>
						</div>
					</div>
					<div class="col-md-6 Input blockaccordiumx" style="text-align: right; height: 60px; margin-top: 15px;">
						<div class="boxInput" style="margin: 0 15px 20px 15px;">
							<a href="${contextPath}" data-name="documentiSetAggiunto_PH_Visualizza" data-dynabindingonload="initVisualizzaDocAgg_PH_" data-event="click"
								style="margin-left: 15px; font-size: 16px; font-weight: 600;">
								<i class="dms-wk-icon-show-details" aria-hidden="true"></i>
								<%-- <img src="${contextPath}/assets/images/VIEW.svg" style="margin-right: 10px;"> --%>
							<spring:message code="label.media.visualizza" /></a> <a href="${contextPath}" data-name="documentiSetAggiunto_PH_Download"
								data-dynabindingonload="initDownloadDocAgg_PH_" data-event="click" style="margin-left: 15px; font-size: 16px; font-weight: 600;">
								
								<i class="dms-wk-icon-download" aria-hidden="true"></i>
				
					<%-- 			<img
								src="${contextPath}/assets/images/download.svg" style="margin-right: 10px;"> --%>
							<spring:message code="label.media.download" /></a>
							<button type="button" class="removeButton" data-deletebutton="documentoSetAggiunto_PH_">
								<i class="dms-wk-icon-delete" aria-hidden="true"></i>
								<spring:message code="label.media.elimina" />
							</button>
						</div>
					</div>
					<div class="tab-pane fade active in blockaccordium" data-tab="itVIPdocumenti">
						<div class="col-md-6 Input" style="margin-top: 15px">
							<spring:message code="placeholder.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<input type="text" class="form-control Input-text" name="documentoSetAggiunto_PH_.titoloMulti.IT" maxlength="300"
									placeholder="${phDidascalia}" />
								<div class="scegliere">
									<spring:message code="label.titoloevento" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="enVIPdocumenti">
						<div class="col-md-6 Input" style="margin-top: 15px">
							<spring:message code="placeholder.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<input type="text" class="form-control Input-text" name="documentoSetAggiunto_PH_.titoloMulti.EN" maxlength="300"
									placeholder="${phDidascalia}" />
								<div class="scegliere">
									<spring:message code="label.titoloevento" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="spVIPdocumenti">
						<div class="col-md-6 Input" style="margin-top: 15px">
							<spring:message code="placeholder.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<input type="text" class="form-control Input-text" name="documentoSetAggiunto_PH_.titoloMulti.SP" maxlength="300"
									placeholder="${phDidascalia}" />
								<div class="scegliere">
									<spring:message code="label.titoloevento" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="frVIPdocumenti">
						<div class="col-md-6 Input" style="margin-top: 15px">
							<spring:message code="placeholder.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<input type="text" class="form-control Input-text" name="documentoSetAggiunto_PH_.titoloMulti.FR" maxlength="300"
									placeholder="${phDidascalia}" />
								<div class="scegliere">
									<spring:message code="label.titoloevento" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="deVIPdocumenti">
						<div class="col-md-6 Input" style="margin-top: 15px">
							<spring:message code="placeholder.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<input type="text" class="form-control Input-text" name="documentoSetAggiunto_PH_.titoloMulti.DE" maxlength="300"
									placeholder="${phDidascalia}" />
								<div class="scegliere">
									<spring:message code="label.titoloevento" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="ruVIPdocumenti">
						<div class="col-md-6 Input" style="margin-top: 15px">
							<spring:message code="placeholder.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<input type="text" class="form-control Input-text" name="documentoSetAggiunto_PH_.titoloMulti.RU" maxlength="300"
									placeholder="${phDidascalia}" />
								<div class="scegliere">
									<spring:message code="label.titoloevento" />
								</div>
							</div>
						</div>
					</div>
					
					<%-- <div class="col-md-6 Input  blockaccordiumx" style="margin-top: 15px">
						<spring:message code="placeholder.documenti.ordine" var="phOrdine" />
						<div class="boxInput" style="margin: 0 15px 20px 15px;">
							<input type="number" class="form-control Input-text" name="documentoSetAggiunto_PH_.ordineNumerico"
								oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');" maxlength="10" placeholder="${phOrdine}" />
							<div class="scegliere">
								<spring:message code="label.documento.ordine" />
							</div>
						</div>
					</div> --%>
					
					
						<div class="col-lg-6 col-md-6 Input blockaccordiumx" style="margin-top: 15px">
						<spring:message code="placeholder.documenti.ordine" var="phOrdine" />
						<div class="boxInput" style="margin: 0 15px 20px 15px;">
							<input type="number" class="form-control Input-text itready" name="documentoSetAggiunto_PH_.ordineNumerico"
								 min="1" maxlength="10" placeholder="${phOrdine}" />
								 <label class="error" style="display:none;">* campo obbligatorio</label>
							<div class="scegliere">
								<spring:message code="label.documento.ordine.required" />
							</div>
						</div>
					</div>
					
					
					<input type="hidden" name="documentoSetAggiunto_PH_.daPubblicare" value="true">
				</div>
			</div>
		</div>
	</div>
</div>