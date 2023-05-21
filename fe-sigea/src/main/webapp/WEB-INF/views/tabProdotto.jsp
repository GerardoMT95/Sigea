<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row" style="margin-top: 20px; margin-left: 0px; margin-right:0px">
	<div class="col-md-12 col-sm-12 Input multipl">
		<div class="boxInput">
			<select class="form-control Input-text multi" name="prodottiSet[]" id="prodotto" multiple="multiple"></select>
			<div class="scegliere"><spring:message code="label.prodotto"/></div>
			<label id="prodotto-error" class="error" for="prodotto" style="display:none;">* campo obbligatorio</label>
		</div>
	</div>
</div>
<div class="row" style="margin-top: 20px; margin-left: 0px; margin-right:0px">
	<div class="col-md-12 col-sm-12 Input" id="mezzoAttivita" style="height: auto; display: none">
		<div class="boxInput">
			<select class="form-control Input-text multi" name="mezziSet[]" id="mezzo" multiple="multiple"></select>
			<div class="scegliere"><spring:message code="label.mezzo"/></div>
			
		</div>
	</div>
</div>