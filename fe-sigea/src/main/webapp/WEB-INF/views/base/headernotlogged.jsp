<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!-- blocco menu pagine interne -->

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
						<a href="accedi"><img src="${context}/assets/images/DMS.png" alt="assets/dms logo" style="width: 100%; max-width: 320px;"></a>
						<h4 id="sottotitoloogodms" style="font-size: 15px; font-weight: 400; color: #000; line-height: 45px;">DIGITAL MANAGEMENT SYSTEM | Turismo e
							cultura in Puglia</h4>
					</div>
				</div>
				<div class="col-md-6" style="padding: 15px 0px; text-align: right;" id="loghiis">
					<a href="https://www.dms.puglia.it/portal" target="_blank"><img src="${context}/assets/svg/logo_pon.svg" alt="puglia promozione logo"
						style="height: 30px; margin: 0px 0px 0px 15px; float: right;"></a> <img src="${context}/assets/svg/logo_italia.svg" alt="italia logo"
						style="height: 30px; margin: 0px 17px; float: right;"> <a href="http://ec.europa.eu/" target="_blank"><img
						src="${context}/assets/svg/logo-ue.svg" alt="europa logo" style="height: 30px; margin: 0px 17px; float: right;"></a>
				</div>
			</div>
		</div>
		<div style="position: absolute; right: 0px; top: 45px;">
			<a href="http://www.regione.puglia.it/" target="_blank"><img src="${context}/assets/images/linguetta.png" alt="regione logo"
				style="height: 42px;"></a>
		</div>
	</div>
</div>