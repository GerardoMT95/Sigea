<!-- blocco menu pagine interne -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="header" style="border-bottom: 2px solid #0075bf; padding: 0px;" id="barratop">
	<div class="symbol-header" style="height: 31px;">
		<div class="container">
			<div class="row" style="margin: 0px;">
				<div class="col-md-12 toplink" style="padding: 0px; color: #fff; font-size: 14px; height: 31px; line-height: 31px; font-weight: 400;">
					<a href="https://www.regione.puglia.it/" title="Vai al sito della Regione Puglia" target="_blank" class="toplinkafter">REGIONE PUGLIA</a> <a
						href="https://www.regione.puglia.it/web/turismo-e-cultura" title="Vai al sito di Turismo e Cultura" target="_blank" class="toplinkafter">TURISMO
						E CULTURA</a> <span style="font-family: 'Titillium Web SemiBold';">DMS PUGLIA</span>
				</div>
			</div>
		</div>
	</div>
	<div class="head">
		<div class="container">
			<div class="row" style="margin: 0px;">
				<div class="col-md-6" style="padding: 15px 0px 0px 0px;">
					<div class="logo" style="width: 100%">
						<img src="${contextPath}/assets/images/DMS.png" alt="Dms Logo" style="width: 100%; max-width: 320px;">
						<h4 id="sottotitoloogodms" style="font-size: 15px; font-weight: 400; color: #000; line-height: 45px;">
							DIGITAL MANAGEMENT SYSTEM <span class="nomoble">| Turismo e cultura in Puglia</span>
						</h4>
					</div>
				</div>
				<div class="col-md-6" style="padding: 15px 0px; text-align: right;" id="loghiis">
					<a href="https://por.regione.puglia.it/" target="_blank" title="vai a POR Regione Puglia"> <img
						src="${contextPath}/assets/images/logo_pon.svg" alt="puglia promozione logo" style="height: 30px; margin: 0px 0px 0px 15px; float: right;"></a>
					<a href="http://www.governo.it/" target="_blank" title="vai a Repubblica Italiana"> <img src="${contextPath}/assets/images/logo_italia.svg"
						alt="italia logo" style="height: 30px; margin: 0px 17px; float: right;"></a> <a href="https://europa.eu/european-union/index_it"
						target="_blank" title="vai a Unione Europea"> <img src="${contextPath}/assets/images/logo-ue.svg" alt="europa logo"
						style="height: 30px; margin: 0px 17px; float: right;"></a>
				</div>
			</div>
		</div>
		<div style="position: absolute; right: 0px; top: 45px;" class="nomoble">
			<a href="http://www.regione.puglia.it/" target="_blank" title="Vai al sito della Regione Puglia"><img src="${contextPath}/assets/images/linguetta.png" alt="regione logo" style="height: 42px;"></a>
		</div>
	</div>
</div>

<!-- Navigation -->
<nav class="navbar dms interno">
	<div class="container">
		<div class="row" style="padding: 0px 0px 0px 15px;">
			<div class="col-md-12 welcome">
				<c:if test="${!fn:contains(page, 'profilo')}">
					<div class="dmsw" style="float: left;">
						<spring:message code="label.areapersonale" var="areapersonale" />
						<div style="float: left; padding-top: 5px;">
							<a href="${areaRiservata}" id="goHome" title="Area personale"><img src="${contextPath}/assets/images/icon-indietro.svg" style="height: 27px; margin-right: 5px;" /></a>
						</div>
						<a href="${areaRiservata}" class="goHomeDms" id="goHome" title="Area personale">${denominazione}</a>
					</div>
				</c:if>
				<div style="position: absolute; right: 0px;">
					<div class="dmsw" style="float: right; margin-right: 15px;">
						<a href="oidc/logout" id="goLogout" title="Logout" class="btn btn-primary invia" style="text-decoration: none;">
							<i class="icon-log_out" style="position: relative; top: -1px; font-size: 21px;"></i> <span style="display: inline; position: relative; top: -5px; margin-left: 3px;">Esci</span>
						</a>
					</div>
					<div class="dmsw utente" id="blocconomeutente" style="float: right; padding-right: 25px;">
						<div style="float: left;">
							<img src="${contextPath}/assets/images/icon-user.svg" style="width: 34px; margin: 2px 5px;;" />
						</div>
						<div style="font-size: 16px; float: left; line-height: 40px; text-align: left;">
							<c:choose>
								<c:when test="${not empty nome || not empty cognome}">${nome} ${cognome}</c:when>
								<c:otherwise>${username}</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</nav>