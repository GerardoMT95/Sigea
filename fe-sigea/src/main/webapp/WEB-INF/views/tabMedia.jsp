<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-lg-12 col-md-12 col-sm-12 Input" id="boxmobileAllegato" style="display: none;">
	<div class="boxInput mobile-input">
		<select id="mobiletabAllegato" class="form-control Input-text">
			<option data-target="#immaginibox"><spring:message code="label.media.immagini" /></option>
			<option data-target="#documentibox"><spring:message code="label.media.documenti" /></option>
			<option data-target="#videobox"><spring:message code="label.media.video" /></option>
		</select>
		<div class="scegliere">Seleziona media</div>
	</div>
</div>

<ul class="nav nav-tabs" id="docTab" role="tablist" style="margin-right: 0px; margin-left: 0px; margin-bottom: 15px">
	<li class="nav-item active" style="cursor: pointer" id="imm"><a href="#immaginibox" role="tab" data-toggle="tab" class="nav-link active"
		id="immagini-tab" style="cursor: pointer"><spring:message code="label.media.immagini" /></a></li>
	<li class="nav-item" style="cursor: pointer" id="doc"><a href="#documentibox" role="tab" data-toggle="tab" class="nav-link" id="documenti-tab"
		style="cursor: pointer"><spring:message code="label.media.documenti" /></a></li>
	<li class="nav-item" style="cursor: pointer" id="vid"><a href="#videobox" role="tab" data-toggle="tab" class="nav-link" id="video-tab"
		style="cursor: pointer"><spring:message code="label.media.video" /></a></li>
</ul>

<div class="tab-content" id="myTabCont" style="padding-top: 10px">
	<div class="tab-pane fade active in" id="immaginibox">
		<jsp:include page="tabImmagini.jsp" flush="false" />
	</div>
	<div class="tab-pane fade" id="documentibox">
		<jsp:include page="tabDocumenti.jsp" flush="false" />
	</div>
	<div class="tab-pane fade" id="videobox">
		<jsp:include page="tabVideo.jsp" flush="false" />
	</div>
</div>