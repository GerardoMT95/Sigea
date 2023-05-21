<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div style="padding: 20px 10px 10px 0px; height: auto">
	<div class="dms-breadcrumb">
		<a href="home" title="Gestione eventi" style="color: #000000;">Gestione eventi</a> > <span id="breadcrumbs">Redazione eventi</span>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<h4 class="box-title" style="font-size: 24px; line-height: 26px; padding-left: 0px">
			<span><strong>Redazione eventi</strong></span>
		</h4>
		<div class="row" style="margin-bottom: 30px;">
			<div class="col-md-2" style="text-align: center; padding: 20px;">
				<img src="${contextPath}/assets/svg/redazione.svg" style="width: 80px;">
			</div>
			<div class="col-md-10" style="padding: 20px; margin: 2% 0px;">
				<spring:message code="label.redazionehome" />
			</div>
		</div>
		<h4 class="box-title" style="font-size: 21px; line-height: 26px; padding-left: 0px">
			<span><strong>Le redazioni</strong></span>
		</h4>
	</div>
	<sec:authorize access="hasAnyRole('SIGEA_PROMOZIONE_VIP','SIGEA_REDAZIONE_VIP')">
		<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 button-card-redazione">
			<button class="col-xs-12 col-sm-12 col-md-12 col-lg-12 box-card-homepage d-flex" onclick="window.location.href='redazionevip'">
				<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
					<div class="quadratoblu">
						<img src="${contextPath}/assets/svg/redigi_evento_bianco.svg">
					</div>
				</div>
				<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10 spaceleft">
					<span class="titlecard-redazione">Redazione Vip</span>
				</div>
			</button>
		</div>
	</sec:authorize>
</div>