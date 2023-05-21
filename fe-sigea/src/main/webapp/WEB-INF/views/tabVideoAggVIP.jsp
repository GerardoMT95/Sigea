<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="row">
	<div class="bloccotab">
		<div class="row">
			<div class="col-md-6" style="padding: 5px 40px 5px 40px">
				<span style="color: #003a54; font-size: 16px; font-weight: 600; line-height: 40px;">LINK AGGIUNTIVI REDAZIONE</span>
			</div>
			<div class="col-md-6" style="padding: 5px 40px 5px 40px; text-align: right;">
				<button class="btn btn-primary invia" type="button" data-bindingonload data-addbutton="linkSetAggiunto" data-counter="-1"
					data-ph="_PH_">
					<i class="dms-wk-icon-add"></i>
					<spring:message code="label.media.aggiungivideo" />
				</button>
			</div>
		</div>
		<div data-dynamic="linkSetAggiunto" style="padding: 0px 9px;" class="contalinkvipagg">
			<div class="row" hidden="true" data-template="linkSetAggiunto_PH_" style="margin: 0 0px 30px 0px; border-bottom: 1px solid #F2F2F2;">
				<div class="tab-content" id="myVidVIPContent">
					<div class="tab-pane fade active in blockaccordium" data-tab="itVIPvideo">
						<div class="col-md-6 Input" style="margin-top: 34px">
							<spring:message code="placeholder.titolo" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 40px 15px;">
								<input type="text" class="form-control Input-text" name="linkSetAggiunto_PH_.titoloMulti.IT" maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri">
								<label class="error" style="display:none">* campo obbligatorio</label>
								<div class="scegliere">
									<spring:message code="label.titoloevento" /> *
								</div>
							</div>
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="linkSetAggiunto_PH_.didascaliaMulti.IT"
									maxlength="300" placeholder="${phDidascalia}"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="enVIPvideo">
						<div class="col-md-6 Input" style="margin-top: 34px">
							<spring:message code="placeholder.titolo" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 40px 15px;">
								<input type="text" class="form-control Input-text" name="linkSetAggiunto_PH_.titoloMulti.EN" maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri">
								<div class="scegliere">
									<spring:message code="label.titoloevento" />
								</div>
							</div>
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="linkSetAggiunto_PH_.didascaliaMulti.EN"
									maxlength="300" placeholder="${phDidascalia}"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="spVIPvideo">
						<div class="col-md-6 Input" style="margin-top: 34px">
							<spring:message code="placeholder.titolo" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 40px 15px;">
								<input type="text" class="form-control Input-text" name="linkSetAggiunto_PH_.titoloMulti.SP" maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri">
								<div class="scegliere">
									<spring:message code="label.titoloevento" />
								</div>
							</div>
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="linkSetAggiunto_PH_.didascaliaMulti.SP"
									maxlength="300" placeholder="${phDidascalia}"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="frVIPvideo">
						<div class="col-md-6 Input" style="margin-top: 34px">
							<spring:message code="placeholder.titolo" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 40px 15px;">
								<input type="text" class="form-control Input-text" name="linkSetAggiunto_PH_.titoloMulti.FR" maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri">
								<div class="scegliere">
									<spring:message code="label.titoloevento" />
								</div>
							</div>
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="linkSetAggiunto_PH_.didascaliaMulti.FR"
									maxlength="300" placeholder="${phDidascalia}"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="deVIPvideo">
						<div class="col-md-6 Input" style="margin-top: 34px">
							<spring:message code="placeholder.titolo" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 40px 15px;">
								<input type="text" class="form-control Input-text" name="linkSetAggiunto_PH_.titoloMulti.DE" maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri">
								<div class="scegliere">
									<spring:message code="label.titoloevento" />
								</div>
							</div>
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="linkSetAggiunto_PH_.didascaliaMulti.DE"
									maxlength="300" placeholder="${phDidascalia}"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade blockaccordium" data-tab="ruVIPvideo">
						<div class="col-md-6 Input" style="margin-top: 34px">
							<spring:message code="placeholder.titolo" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 40px 15px;">
								<input type="text" class="form-control Input-text" name="linkSetAggiunto_PH_.titoloMulti.RU" maxlength="300" placeholder="Aggiungi un testo con massimo 300 caratteri">
								<div class="scegliere">
									<spring:message code="label.titoloevento" />
								</div>
							</div>
							<spring:message code="placeholder.media.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="linkSetAggiunto_PH_.didascaliaMulti.RU"
									maxlength="300" placeholder="${phDidascalia}"></textarea>
								<div class="scegliere" style="bottom: 110% !important;">
									<spring:message code="label.media.didascalia" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6 Input blockaccordiumx" style="margin-top: 34px">
					<spring:message code="placeholder.video.link" var="phLink" />
					<div class="boxInput" style="margin: 0 15px 40px 15px;">
						<input hidden="true" type="text" name="linkSetAggiunto_PH_.linkId"> <input type="text" class="form-control Input-text"
							name="linkSetAggiunto_PH_.link" maxlength="300" placeholder="${phLink}">
							<label class="error" style="display:none">* campo obbligatorio</label>
						<div class="scegliere">
							<spring:message code="label.video.linkvip" /> *
						</div>
					</div>
					<spring:message code="placeholder.media.credits" var="phCredits" />
					<div class="boxInput" style="margin: 0 15px 20px 15px;">
						<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 300px" name="linkSetAggiunto_PH_.credits" maxlength="300"
							placeholder="${phCredits}"></textarea>

						<div class="scegliere" style="bottom: 110% !important;">
							<spring:message code="label.media.credits" />
						</div>
					</div>
				</div>
			<%-- 	<div class="col-md-6 Input blockaccordiumx" style="margin-top: 15px">
					<spring:message code="placeholder.documenti.ordine" var="phOrdine" />
					<div class="boxInput" style="margin: 0 15px 20px 15px;">
						<input type="number" class="form-control Input-text itready" name="linkSetAggiunto_PH_.ordineNumerico"
							oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');" maxlength="10" placeholder="${phOrdine}">
						<div class="scegliere">
							<spring:message code="label.documento.ordine" />
						</div>
					</div>
				</div> --%>
				
				
					<div class="col-lg-6 col-md-6 Input blockaccordiumx" style="margin-top: 15px">
						<spring:message code="placeholder.documenti.ordine" var="phOrdine" />
						<div class="boxInput" style="margin: 0 15px 20px 15px;">
							<input type="number" class="form-control Input-text itready order" name="linkSetAggiunto_PH_.ordineNumerico"
								 min="1" maxlength="10" placeholder="${phOrdine}" />
								 <label class="error" style="display:none;">* campo obbligatorio</label>
							<div class="scegliere">
								<spring:message code="label.documento.ordine.required" />
							</div>
						</div>
					</div>
				
				
				
				
				
				<input type="hidden" name="linkSetAggiunto_PH_.daPubblicare" value="true">
				<div class="col-md-12 Input blockaccordiumx" style="text-align: right; height: 60px; margin-top: 0px;">
					<div class="boxInput" style="margin: 0 15px 20px 15px;">
						<button type="button" data-deletebutton="linkSetAggiunto_PH_" class="removeButton">
							<i class="dms-wk-icon-delete" aria-hidden="true"></i>
							<spring:message code="label.media.elimina" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>