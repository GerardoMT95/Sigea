<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="tab-content" id="docAggVIPCont">
	<div class="tab-pane fade active in" id="immaginiAggVIPbox">
		<jsp:include page="tabImmaginiAggVIP.jsp" flush="false" />
	</div>
	<div class="tab-pane fade" id="documentiAggVIPbox">
		<jsp:include page="tabDocumentiAggVIP.jsp" flush="false" />
	</div>
	<div class="tab-pane fade" id="videoAggVIPbox">
		<jsp:include page="tabVideoAggVIP.jsp" flush="false" />
	</div>
</div>