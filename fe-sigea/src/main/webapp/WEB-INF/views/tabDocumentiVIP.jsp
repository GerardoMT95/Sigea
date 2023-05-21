<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="row" style="margin-bottom: 15px">
	<div class="col-md-12">
		<spring:message code="label.schedavip.mediaDoc.accordion" />
	</div>
</div>
<div class="row">
	<div class="col-md-12 Input" id="boxmobileLinguaDocVip" style="display: none; padding-bottom: 15px; margin-left: 15px; margin-right: 15px;">
		<div class="mobile-input">
			<select id="mobiletabLinguaDocVip" class="form-control Input-text">
				<option value="IT" data-target="[data-tab='itVIPdocumenti']">IT</option>
				<option value="EN" data-target="[data-tab='enVIPdocumenti']">EN</option>
				<option value="SP" data-target="[data-tab='spVIPdocumenti']">SP</option>
				<option value="FR" data-target="[data-tab='frVIPdocumenti']">FR</option>
				<option value="DE" data-target="[data-tab='deVIPdocumenti']">DE</option>
				<option value="RU" data-target="[data-tab='ruVIPdocumenti']">RU</option>
			</select>
			<div class="scegliere">Cambia lingua</div>
		</div>
	</div>
	<ul class="nav nav-tabs" id="myVIPdc" role="tablist" style="margin-right: 15px; margin-left: 15px; margin-bottom: 15px">
		<li class="nav-item active" style="cursor: pointer" data-tablink="itVIPd"><a data-target="[data-tab='itVIPdocumenti']" data-toggle="tab"
			class="nav-link active" style="cursor: pointer">IT</a></li>
		<li class="nav-item" style="cursor: pointer" data-tablink="enVIPd"><a data-target="[data-tab='enVIPdocumenti']" data-toggle="tab"
			class="nav-link active" style="cursor: pointer">EN</a></li>
		<li class="nav-item" style="cursor: pointer" data-tablink="spVIPd"><a data-target="[data-tab='spVIPdocumenti']" data-toggle="tab"
			class="nav-link active" style="cursor: pointer">SP</a></li>
		<li class="nav-item" style="cursor: pointer" data-tablink="frVIPd"><a data-target="[data-tab='frVIPdocumenti']" data-toggle="tab"
			class="nav-link active" style="cursor: pointer">FR</a></li>
		<li class="nav-item" style="cursor: pointer" data-tablink="deVIPd"><a data-target="[data-tab='deVIPdocumenti']" data-toggle="tab"
			class="nav-link active" style="cursor: pointer">DE</a></li>
		<li class="nav-item" style="cursor: pointer" data-tablink="ruVIPd"><a data-target="[data-tab='ruVIPdocumenti']" data-toggle="tab"
			class="nav-link active" style="cursor: pointer">RU</a></li>
	</ul>
	<div class="row" style="margin-left: 15px; padding-top: 15px; margin-bottom: 15px;" hidden="true">
		<div class="col-md-12 Input">
			<button type="button" style="float: right;" class="btn btn-primary" data-bindingonload data-addbutton="documentoSet" data-counter="-1"
				data-ph="_PH_"></button>
		</div>
	</div>
	<div class="bloccotab">
		<div class="row">
			<div class="col-md-12" style="padding: 15px 40px 15px 40px">
				<span style="color: #003a54; font-size: 16px; font-weight: 600;">DOCUMENTI DEL NUCLEO</span>
			</div>
		</div>
		<div data-dynamic="documentoSet" style="padding: 0px 9px;" class="contadocumentivip">
			<div class="row" hidden="true" data-template="documentoSet_PH_"
				style="border-top: 1px solid #efefef; box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2); margin: 10px 15px 30px 15px; background: #F2F2F2;">
				<input type="hidden" name="documentoSet_PH_.documentoId" readonly> <input type="hidden" name="documentoSet_PH_.estensione" readonly>
				<input type="hidden" name="documentoSet_PH_.dimensione" readonly>
				<div class="tab-content" id="myVIPdcContent">
					<div class="col-md-12 Input accordionlight" style="padding: 15px; background: #ffffff; cursor: pointer;">
						<div class="boxInput" id="nomefilebox" style="margin-bottom: 0px;">
							<img src="${contextPath}/assets/svg/doc_nero.svg" style="margin-right: 20px;"> <span class="titoloinput"> <input type="hidden"
								class="inputtotext totext" name="documentoSet_PH_.nomeOriginale" maxlength="300" readonly /></span>
							<div style="float: right; padding-top: 16px;">
								<img src="${contextPath}/assets/images/FRECCIA_SU.svg" class="frecciafiltro" id="frecciaopen" style="width: 18px;">
							</div>
						</div>
					</div>
					<div class="col-md-12 Input blockaccordiumx" style="text-align: right; height: 60px; margin-top: 15px; background: #F2F2F2;">
						<div class="boxInput" style="margin: 0 15px 20px 15px;">
							<a href="${contextPath}" data-name="documentiSet_PH_Visualizza" data-dynabindingonload="initVisualizzaDoc_PH_" data-event="click"
								style="margin-right: 20px; font-size: 16px; font-weight: 600;">
								<i class="dms-wk-icon-show-details" aria-hidden="true"></i>
								<%-- <img src="${contextPath}/assets/images/VIEW.svg"
								style="margin-right: 10px;"> --%>
							<spring:message code="label.media.visualizza" /></a> <a href="${contextPath}" data-name="documentiSet_PH_Download"
								data-dynabindingonload="initDownloadDoc_PH_" data-event="click" style="margin-right: 20px; font-size: 16px; font-weight: 600;">
								<i class="dms-wk-icon-download" aria-hidden="true"></i>				
								<%-- <img
								src="${contextPath}/assets/images/download.svg" style="margin-right: 10px;"> --%>
							<spring:message code="label.media.download" /></a>
						</div>
					</div>
					<div class="tab-pane fade active in blockaccordium" data-tab="itVIPdocumenti">
						<div class="col-md-6 Input" style="margin-top: 15px">
							<spring:message code="placeholder.didascalia" var="phDidascalia" />
							<div class="boxInput" style="margin: 0 15px 20px 15px;">
								<input type="text" class="form-control Input-text" name="documentoSet_PH_.titoloMulti.IT" maxlength="300" placeholder="${phDidascalia}"
									readonly="readonly" />
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
								<input type="text" class="form-control Input-text" name="documentoSet_PH_.titoloMulti.EN" maxlength="300" placeholder="${phDidascalia}"
									readonly="readonly" />
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
								<input type="text" class="form-control Input-text" name="documentoSet_PH_.titoloMulti.SP" maxlength="300" placeholder="${phDidascalia}"
									readonly="readonly" />
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
								<input type="text" class="form-control Input-text" name="documentoSet_PH_.titoloMulti.FR" maxlength="300" placeholder="${phDidascalia}"
									readonly="readonly" />
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
								<input type="text" class="form-control Input-text" name="documentoSet_PH_.titoloMulti.DE" maxlength="300" placeholder="${phDidascalia}"
									readonly="readonly" />
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
								<input type="text" class="form-control Input-text" name="documentoSet_PH_.titoloMulti.RU" maxlength="300" placeholder="${phDidascalia}"
									readonly="readonly" />
								<div class="scegliere">
									<spring:message code="label.titoloevento" />
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-3 Input blockaccordiumx" style="margin-top: 15px">
						<spring:message code="placeholder.documenti.ordine" var="phOrdine" />
						<div class="boxInput" style="margin: 0 15px 20px 15px;">
							<input type="number" class="form-control Input-text itready" name="documentoSet_PH_.ordineNumerico"
								min="1" maxlength="10" placeholder="${phOrdine}" />
								<label class="error" style="display:none;">* campo obbligatorio</label>
							<div class="scegliere">
								<spring:message code="label.documento.ordine" />
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 Input blockaccordiumx" style="margin-top: 15px">
						<div class="boxInput" style="margin: 0 15px 20px 15px;">
							<label class="switch" style="float: left;"> <input type="checkbox" class="custom-control-input itreadys switch"
								style="position: inherit" name="documentoSet_PH_.daPubblicare" value="true" checked> <span class="slider round"></span>
							</label> <label class="labelswith" style="left: 15px; top: 5px; font-weight: 600; font-size: 16px;"><spring:message
									code="label.immagini.dapubblicare" /></label>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>