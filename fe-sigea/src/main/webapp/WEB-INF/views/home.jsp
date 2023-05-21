<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="row" style="margin-top: 30px; margin-bottom: 30px;">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<h4 class="box-title" style="font-size: 24px; line-height: 26px; padding-left: 0px">
			<span><strong><spring:message code="label.titolo.homepage" /></strong></span>
		</h4>
		<div class="row" style="margin-bottom: 30px;">
			<div class="col-md-2" style="text-align: center; padding: 20px;">
				<img src="${contextPath}/assets/svg/new_svg/evento.svg" style="width: 120px;">
			</div>
			<div class="col-md-10" style="padding: 20px; margin: 3% 0px;">
				<spring:message code="label.eventohome.accordioncontent" />
			</div>
		</div>
		<h4 class="box-title" style="font-size: 21px; line-height: 26px; padding-left: 0px">
			<span><strong>Cosa puoi fare</strong></span>
		</h4>
	</div>
	<sec:authorize access="hasRole('SIGEA_PROMOZIONE')">
		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 button-card-homepage">
			<button class="col-xs-12 col-sm-12 col-md-12 col-lg-12 box-card-homepage d-flex" onclick="window.location.href='promozione'">
				<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
					<div class="cerchiogrigio">
						<img src="${contextPath}/assets/svg/promuovi_evento.svg">
					</div>
				</div>
				<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8 spaceleft">
					<span class="titlecard">Promozione eventi</span>
				</div>
			</button>
		</div>
	</sec:authorize>
	<sec:authorize access="hasAnyRole('SIGEA_PROMOZIONE_VIP','SIGEA_REDAZIONE_VIP')">
		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 button-card-homepage">
			<button class="col-xs-12 col-sm-12 col-md-12 col-lg-12 box-card-homepage d-flex" onclick="window.location.href='redazione'">
				<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
					<div class="cerchiogrigio">
						<img src="${contextPath}/assets/svg/redigi_evento.svg">
					</div>
				</div>
				<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8 spaceleft">
					<span class="titlecard">Redazione eventi</span>
				</div>
			</button>
		</div>
	</sec:authorize>
	<sec:authorize access="hasRole('SIGEA_VALIDAZIONE')">
		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 button-card-homepage">
			<button class="col-xs-12 col-sm-12 col-md-12 col-lg-12 box-card-homepage d-flex" onclick="window.location.href='validazione'">
				<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
					<div class="cerchiogrigio">
						<img src="${contextPath}/assets/svg/valida_evento.svg">
					</div>
				</div>
				<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8 spaceleft">
					<span class="titlecard">Validazione eventi</span>
				</div>
			</button>
		</div>
	</sec:authorize>	
		<sec:authorize access="hasRole('SIGEA_RICEVUTE')"> 
		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 button-card-homepage">
			<button class="col-xs-12 col-sm-12 col-md-12 col-lg-12 box-card-homepage d-flex" onclick="window.location.href='ricevute'">
				<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
					<div class="cerchiogrigio">
						<img src="${contextPath}/assets/svg/logo_ricevute.svg">
					</div>
				</div>
				<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8 spaceleft">
					<span class="titlecard">Ricevute eventi finanziati</span>
				</div>
			</button>
		</div>
	 </sec:authorize>  
</div>