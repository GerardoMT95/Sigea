<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="row">
	<div class="bloccotab">
		<div class="row">
			<div class="col-md-6" style="padding: 5px 40px 5px 40px">
				<span style="color: #003a54; font-size: 16px; font-weight: 600; line-height: 40px;">IMMAGINI AGGIUNTIVE REDAZIONE</span>
			</div>
			<div class="col-md-6" id="immagine" style="padding: 5px 40px 5px 40px; text-align: right;">
				<div id="mybutton" class="btn btn-primary invia">
					<input id="file-01" type="file" name="immagineSetAggiuntoLoad" data-feed="fileImmaginiFeed" onchange="fileClickImgV('filebut01');" />
					<button id="filebut01" type="button" class="btn btn-primary" data-bindingonload data-addbutton="immagineSetAggiunto" data-counter="-1"
						data-ph="_PH_" data-feeder="immagineSetAggiuntoLoad" style="display: none;">
						<spring:message code="label.media.carica" />
					</button>
					<i class="dms-wk-icon-upload"></i> Carica immagine
				</div>
			</div>
		</div>
		<div data-dynamic="immagineSetAggiunto" style="padding: 0px 9px;" class="contaimmaginivipagg">
			<div class="row" hidden="true" data-template="immagineSetAggiunto_PH_"
				style="margin: 10px 15px 30px 15px; background: #ffffff; border-top: 1px solid #F2F2F2;">
				<input type="hidden" name="immagineSetAggiunto_PH_.immagineId" readonly> <input type="hidden" name="immagineSetAggiunto_PH_.estensione"
					readonly> <input type="hidden" name="immagineSetAggiunto_PH_.dimensione" readonly>
				<div class="tab-content">
					<div class="col-md-6 Input accordionlight" style="padding: 15px; background: #ffffff; cursor: pointer;">
						<div class="boxInput" id="nomefilebox" style="margin-bottom: 0px;">
							<img src="${contextPath}/assets/svg/doc_nero.svg" style="margin-right: 20px;"> <span class="titoloinput"> <input type="hidden"
								class="inputtotext totext" name="immagineSetAggiunto_PH_.nomeOriginale" readonly />
							</span>
						</div>
					</div>
					<div class="col-md-6 Input blockaccordiumx" style="text-align: right; height: 60px; margin-top: 15px;" id="rimuovi">
						<div class="boxInput" style="margin: 0 15px 20px 15px;">
							<a href="${contextPath}" data-name="immagineSetAggiunto_PH_Visualizza" data-dynabindingonload="initVisualizzaAgg_PH_" data-event="click"
								style="margin-left: 15px; font-size: 16px; font-weight: 600;">
								<i class="dms-wk-icon-show-details" aria-hidden="true"></i>
								<%-- <img src="${contextPath}/assets/images/VIEW.svg" style="margin-right: 10px;"> --%>
							<spring:message code="label.media.visualizza" /></a> <a href="${contextPath}" data-name="immagineSetAggiunto_PH_Download"
								data-dynabindingonload="initDownloadAgg_PH_" data-event="click" style="margin-left: 15px; font-size: 16px; font-weight: 600;">
								<i class="dms-wk-icon-download" aria-hidden="true"></i>				
						<%-- 		<img
								src="${contextPath}/assets/images/download.svg" style="margin-right: 10px;"> --%>
							<spring:message code="label.media.download" /></a>							
							<button type="button" class="removeButton" data-deletebutton="immagineSetAggiunto_PH_" style="margin: 0 0 0 15px; padding: 0 !important;">
								<i class="dms-wk-icon-delete" aria-hidden="true"></i>
								<spring:message code="label.media.elimina" />
							</button>
						</div>
					</div>
					<div class="tab-pane fade active in blockaccordium" data-tab="itVIPmedia">
						<div class="col-lg-6 col-md-6 Input" style="margin-top: 15px">
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="immagineSetAggiunto_PH_.didascaliaMulti.IT"
									maxlength="300" placeholder="${phDidascalia}"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="enVIPmedia">
						<div class="col-lg-6 col-md-6 Input" style="margin-top: 15px">
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="immagineSetAggiunto_PH_.didascaliaMulti.EN"
									maxlength="300" placeholder="${phDidascalia}"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="spVIPmedia">
						<div class="col-lg-6 col-md-6 Input" style="margin-top: 15px">
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="immagineSetAggiunto_PH_.didascaliaMulti.SP"
									maxlength="300" placeholder="${phDidascalia}"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="frVIPmedia">
						<div class="col-lg-6 col-md-6 Input" style="margin-top: 15px">
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="immagineSetAggiunto_PH_.didascaliaMulti.FR"
									maxlength="300" placeholder="${phDidascalia}"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="deVIPmedia">
						<div class="col-lg-6 col-md-6 Input" style="margin-top: 15px">
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="immagineSetAggiunto_PH_.didascaliaMulti.DE"
									maxlength="300" placeholder="${phDidascalia}"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="ruVIPmedia">
						<div class="col-lg-6 col-md-6 Input" style="margin-top: 15px">
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="immagineSetAggiunto_PH_.didascaliaMulti.RU"
									maxlength="300" placeholder="${phDidascalia}"></textarea>
								<div class="scegliere">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-6 col-md-6 Input  blockaccordiumx" style="margin-top: 15px">
						<spring:message code="placeholder.media.credits" var="phCredits" />
						<div class="boxInput" style="margin: 0 15px 20px 15px;">
							<textarea class="form-control Input-text itready resize" style="min-height: 75px; max-height: 300px" name="immagineSetAggiunto_PH_.credits"
								maxlength="300" placeholder="${phCredits}"></textarea>
							<div class="scegliere">
								<spring:message code="label.media.credits" />
							</div>
						</div>
					</div>
					<div class="col-lg-6 col-md-6 Input blockaccordiumx" style="margin-top: 15px">
						<spring:message code="placeholder.documenti.ordine" var="phOrdine" />
						<div class="boxInput" style="margin: 0 15px 20px 15px;">
							<input type="number" class="form-control Input-text itready" name="immagineSetAggiunto_PH_.ordine"
								 min="1" maxlength="10" placeholder="${phOrdine}" />
								 <label class="error" style="display:none;">* campo obbligatorio</label>
							<div class="scegliere">
								<spring:message code="label.documento.ordine.required" />
							</div>
						</div>
					</div>
					<div class="col-lg-6 col-md-6 Input blockaccordiumx" style="margin-top: 15px">
						<div class="boxInput" style="margin: 0 15px 20px 15px;">
							<label class="switch" style="float: left;"> <input type="checkbox" class="custom-control-input itreadys switch"
								style="position: inherit" name="immagineSetAggiunto_PH_.banner" value="true"> <span class="slider round"></span>
							</label> <label class="labelswith" style="left: 15px; top: 5px; font-weight: 600; font-size: 16px;">Banner</label>
						</div>
					</div>
					<input type="hidden" name="immagineSetAggiunto_PH_.daPubblicare" value="true">
				</div>
			</div>
		</div>
	</div>
</div>