<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row" style="margin-top: 20px; margin-left: 0px; margin-right:0px">
	<div class="Input">
		<div class="col-md-6 col-sm-6 Input multipl">
			<div class="boxInput">
				<select class="form-control Input-text multi" name="prodottiSet[]" id="prodotto" multiple="multiple"></select>
				<div class="scegliere"><spring:message code="label.prodotto.en"/></div>
			</div>
		</div>
	</div>
</div>