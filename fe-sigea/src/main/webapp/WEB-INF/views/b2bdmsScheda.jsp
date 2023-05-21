<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<label id="eventoId" hidden="true">${eventoId}</label>
<input type="hidden" name="idPubblicazione" id="idPubblicazione" readonly>

<div style="padding: 20px 0px 10px 0px; height: auto">
	<h4 class="box-title" style="font-size: 24px; line-height: 26px">
		<strong><spring:message code="label.schedab2bdms"/></strong>
		<!-- <span class="glyphicon glyphicon-info-sign" style="padding-left:10px" aria-hidden="true"></span> -->
	</h4>
	<div class="accordion-content"><spring:message code="label.schedab2bdms.accordion"/></div>
</div>

<div class="box user">
	<form id="formSchedaB2BDMS" name="formSchedaB2BDMS" style="margin-bottom: 0px" accept-charset="UTF-8">
		<div data-name="aggiungId" style="margin-bottom:20px">
		<!-- 	<div class="row">
				<div class="col-md-12">
					<label id="statoPubblicazione" hidden="true"></label>
					<label id="idLablePubblicazione" hidden="true"></label>
				</div>
			</div> -->
			
			
				<div class="row">
				<div class="col-md-2" style="text-align:center;padding:20px;"><img src="${contextPath}/assets/images/annuncio.svg"></div>					
				<div class="col-md-10" style="margin-top: 25px;">
					<div class="col-md-5" style="line-height:26px;">				
						<img src="${contextPath}/assets/images/Vector.svg"> <label id="idEvent" style="display: contents;"><strong>Stato corrente:</strong></label> <label id="statoPubblicazione"></label>
						<br><br>
						<img src="${contextPath}/assets/images/Vector.svg"> <label id="utenteOwner" style="display: contents;"><strong>Pubblicato da:</strong></label> <label id="autorePubblicazione"></label>
						<br><br>
					</div>
					<div class="col-md-7"  style="line-height:26px;">
						<img src="${contextPath}/assets/images/Vector.svg"> <label id="utenteOwnerData" style="display: contents;"><strong>Data di pubblicazione:</strong></label>	<label id="pubblicatoPubblicazione"></label>				
						<br><br>					
					</div>
				</div>		
			</div>
			
			
			
		</div>
		
		<div id="labelValid" hidden="true">
			<div style="display:table-cell; vertical-align: middle; float:left; padding:0px 10px;"><label class="alertImg"></label></div>
			<div style="display:table-cell; vertical-align: middle; padding: 10px 0px; font-size:16px; height: 83px; width: 100%;">
				<label class="alertLabels"><spring:message code="alert.label"/> <i class="dms-wk-icon-modifica-effettuata alerticonwk"></i></label>
			</div>
		</div>
		
		<div class="row">
		<div class="col-md-12" style="padding-bottom: 20px">
			<button type="button" style="float: right; margin-left: 24px" onclick='riportaUltimaPubblicazione()' id="riportaB2BDMS" class="btn btn-primary cancella"><spring:message code="button.riportaPubb"/></button>
			<button type="button" style="float: right; margin-left: 24px" onclick='revocaUltimaPubblicazione()' id="revocaB2BDMS" class="btn btn-primary cancella"><spring:message code="button.revocaPubb"/></button>
		</div>
		</div>
		
		<ul class="nav nav-tabs" id="pubblTab" role="tablist">
			<li class="nav-item active" style="cursor: pointer" id="prodottoB2BDMS">
				<a href="#boxProdotto" role="tab" data-toggle="tab" class="nav-link active" id="prodottoB2BDMS-tab" style="cursor: pointer; padding: 10px 11px; border-top: 2px solid #0075bf !important;"><spring:message code="tab.prodotti"/></a>
			</li>
			<li class="nav-item" style="cursor: pointer" id="segmentiB2BDMS">
				<a href="#boxSegmenti" role="tab" data-toggle="tab" class="nav-link" id="segmentiB2BDMS-tab" style="cursor: pointer; padding: 10px 11px"><spring:message code="tab.segmentib2bdms"/></a>
			</li>
			<li class="nav-item" style="cursor: pointer" id="attribuzioniB2BDMS">
				<a href="#boxAttribuzioni" role="tab" data-toggle="tab" class="nav-link" id="attribuzioniB2BDMS-tab" style="cursor: pointer; padding: 10px 11px"><spring:message code="tab.attribuzionib2bdms"/></a>
			</li>
			<li class="nav-item" style="cursor: pointer" id="mediaAggB2BDMS">
				<a href="#boxMediaAgg" role="tab" data-toggle="tab" class="nav-link" id="mediaAggB2BDMS-tab" style="cursor: pointer; padding: 10px 11px"><spring:message code="tab.allegati"/></a>
			</li>
		</ul>
		
		<div class="tab-content" id="pubblTabContent" style="padding-top: 10px">
			
			<div class="tab-pane fade active in" id="boxProdotto">
				<div class="accordion" style="padding-bottom: 10px">
					<div class="accordion-content"><spring:message code="label.schedab2bdms.prodotti.accordion"/></div>
				</div>
				<jsp:include page="tabProdottoB2BDMS.jsp" flush="false"/>
			</div>
			
			<div class="tab-pane fade" id="boxSegmenti">
				<div class="accordion" style="padding-bottom: 10px">
					<div class="accordion-content"><spring:message code="label.schedab2bdms.segmenti.accordion"/></div>
				</div>
				<jsp:include page="tabSegmentiB2BDMS.jsp" flush="false"/>
			</div>
			
			<div class="tab-pane fade" id="boxAttribuzioni">
				<div class="accordion" style="padding-bottom: 10px">
					<div class="accordion-content"><spring:message code="label.schedab2bdms.attribuzioni.accordion"/></div>
				</div>
				<jsp:include page="tabAttribuzioniB2BDMS.jsp" flush="false"/>
			</div>
			
			<div class="tab-pane fade" id="boxMediaAgg">
				<div class="accordion" style="padding-bottom: 10px">
					<div class="accordion-content"><spring:message code="label.schedab2bdms.mediaAgg.accordion"/></div>
				</div>
				<jsp:include page="tabMediaAggB2BDMS.jsp" flush="false"/>
			</div>
		</div>
	</form>
</div>

<span class="col-md-12 Input" style="padding-left: 170px; padding-right: 0px">
	<button type="button" style="float: right; margin-left: 24px" onclick='popupSalva("PUBBLICATO")' id="pubblicaB2BDMS" class="btn btn-primary invia"><spring:message code="button.pubblica"/></button>
	<button type="button" style="float: right; margin-left: 24px" onclick='salvaScheda("BOZZA")' id="salvaB2BDMS" class="btn btn-primary cancella"><spring:message code="button.aggBozza"/></button>
	<button type="button" style="float: right; margin-left: 24px" onclick='salvaScheda("BOZZA")' id="creaBozzaB2BDMS" class="btn btn-primary invia"><spring:message code="button.incompilazione"/></button>
	<button type="button" style="margin-right: 24px" onclick="ritornaAEvento()" id="ritorna" class="btn btn-primary cancella"><spring:message code="button.indietro"/></button>
	<button type="button" style="float: right; margin-left: 24px" onclick='popupSalva("BOZZA")' id="bozzaB2BDMS" class="btn btn-primary invia"><spring:message code="button.riporta"/></button>
</span>

<script type="text/javascript" src="${contextPath}/assets/js/sigeajs/b2bdmsScheda.js"></script>