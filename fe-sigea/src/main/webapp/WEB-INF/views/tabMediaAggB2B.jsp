<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

	<div class="row" style="margin-left: 0px; padding-top:15px; margin-bottom: 15px;">
		<div class="col-md-5 Input" id="documento" style="margin-top:2px">
			<input type='file' name="documentoSetAggiuntoLoad" data-feed="fileDocumentiFeed"/>
		</div>
		<div class="col-md-3 Input" id="aggiungiDocumento">
			<button type="button" class="btn btn-primary" data-bindingonload data-addbutton="documentoSetAggiunto" data-counter="-1" data-ph="_PH_" data-feeder="documentoSetAggiuntoLoad" ><spring:message code="label.media.carica.en"/></button>
		</div>
	</div>


	<div data-dynamic="documentoSetAggiunto">
		<div class="row" hidden="true" data-template="documentoSetAggiunto_PH_" style="margin: 0px 0px 30px 0px; padding: 30px 15px 15px 15px; background: #ffffff; cursor:pointer;     box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);">
			<input type="hidden" name="documentoSetAggiunto_PH_.documentoId" readonly>
			<input type="hidden" name="documentoSetAggiunto_PH_.estensione" readonly>
			<input type="hidden" name="documentoSetAggiunto_PH_.dimensione" readonly>
			<div class="tab-content" id="myVIPdcContent">		
			
				<div class="col-md-6 Input">
				<div class="boxInput" style="margin-bottom: 0px;">
					<input type="text" class="form-control Input-text" name="documentoSetAggiunto_PH_.nomeOriginale" style="cursor: default" maxlength="300" readonly/>
					<div class="scegliere"><spring:message code="label.media.nomefile.en"/></div>
					</div>
				</div>
			
				
			<div class="col-md-6 Input">
			<div class="boxInput" style="text-align:right; margin-bottom: 0px;">
				<a href="${contextPath}" data-name = "documentiSetAggiunto_PH_Download" data-dynabindingonload="initDownloadDocAgg_PH_" data-event="click" style="margin-right: 20px; font-size:16px; font-weight:600;">
				<%-- <img src="${contextPath}/assets/images/VIEW.svg" style="margin-right:10px;"> --%>
				<i class="dms-wk-icon-show-details" aria-hidden="true"></i>
				<spring:message code="label.media.download.en"/></a>
				<button type="button" class="removeButton" style="margin-left:20px" data-deletebutton="documentoSetAggiunto_PH_"><i class="dms-wk-icon-delete" aria-hidden="true"></i><spring:message code="label.media.elimina"/></button>
			</div>
			</div>
			</div>
		</div>
	</div>
