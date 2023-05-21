<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row" style="padding-left: 15px; padding-right: 15px">
	<div id="attribuzioniEvento">
		<div class="col-lg-6 col-md-6 col-sm-6 Input" style="height: auto">
			<fieldset style="border: none; padding: 0px; padding-bottom: 5px">
				<legend style="border-bottom: none; font-size: 20px; margin-bottom: 0px">
					<spring:message code="label.attribuzioni.opzioni" />
				</legend>
				<label class="checkbox" style="margin-top: 5px !important"> <input type="checkbox" style="position: inherit" name="big" value="true">
					<spring:message code="label.attribuzioni.big" /> <input type="checkbox" style="position: inherit; margin-left: 20px !important" name="slideshow"
					value="true"> <spring:message code="label.attribuzioni.slideshow" />
				</label>
			</fieldset>
		</div>
		<div class="col-md-6 col-sm-6 Input" style="margin-top: 26px">
			<div class="boxInput">
				<select class="form-control Input-text" name="ranking" id="ranking"></select>
				<div class="scegliere">
					<spring:message code="label.attribuzioni.ranking" />
				</div>
			</div>
		</div>
	</div>
	<div id="attribuzioniAttivita" style="display: none">
		<div class="col-lg-6 col-md-6 col-sm-6 Input" style="margin-top: 20px">
			<div class="boxInput">
				<input type="hidden" name="valoreId" readonly> <select class="form-control Input-text" name="valore" id="valore"></select>
				<div class="scegliere">
					<spring:message code="label.prodotto.valore" />
				</div>
			</div>
		</div>
	</div>
</div>
<div data-dynamic="attrattoriSet">
	<div class="row" style="margin-top: 15px; padding-left: 15px; padding-right: 15px">
		<div class="col-md-12 col-sm-12 Input">
			<div class="boxInput" id="attrattoriSelect">			
				<input type="hidden" name="attrattoriSet.attrattoreId" data-ignorefield readonly> <select class="form-control Input-text"
					name="attrattoriSet" data-ignorefield data-bindingonload="selectizeAttrattori" data-feed="selectizeAttrattoriFeed"></select>	
				<div class="scegliere">
					<spring:message code="label.location.attrattori" />
				</div>
			</div>
		</div>
		<div class="col-md-12 col-sm-12 Input" style="padding-right: 10px">
			<button type="button" class="btn btn-primary invia" data-bindingonload data-addbutton="attrattoriSet" data-counter="-1" data-ph="_PH_"
				data-feeder="attrattoriSet" style="float: right; margin-left: 0px; margin-right: 0px; margin-bottom: 10px; margin-top: 10px;">
				<i class="dms-wk-icon-add" aria-hidden="true"></i>
				<spring:message code="label.location.aggiungiattrattore" />
			</button>
		</div>
	</div>
	<div hidden="true" data-template="attrattoriSet_PH_">
		<div class="row" style="margin-top: 10px; padding-left: 20px; padding-right: 15px">
			<div class="col-md-12 col-sm-12 Input">
				<div class="boxInput">
					<input type="hidden" name="attrattoriSet_PH_.attrattoreId" readonly> <input type="text" class="form-control Input-text"
						name="attrattoriSet_PH_.etichetta" readonly>
					<div class="scegliere">
						<spring:message code="label.location.attrattore" />
					</div>
				</div>
			</div>
			<div class="col-md-12 col-sm-12 Input">
				<button type="button" style="float: right; padding: 0 15px !important;" data-deletebutton="attrattoriSet_PH_" class="removeButton">
					<i class="dms-wk-icon-delete" aria-hidden="true"></i>
					<spring:message code="label.location.rimuoviattrattore" />
				</button>
			</div>
		</div>
	</div>
</div>