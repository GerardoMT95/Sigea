<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="tab-content" id="myTabContent2" style="padding-top: 10px">
	<input type="hidden" name="ticket.eventoId" readonly>
	<div class="row" style="margin-bottom: 34px; margin-left: 0px">
		<div class="col-lg-2 col-md-3 col-sm-4 Input validvalue">
			<input type="radio" class="radio-ruolo option-input inlist radio" name="ticket.tipoTicket" value="indefinito"
				data-bindingonload="clickRadioPagamento" data-event="change" checked>
			<spring:message code="label.ticket.indefinito" />
		</div>
		<div class="col-lg-2 col-md-3 col-sm-4 Input validvalue">
			<input type="radio" class="radio-ruolo option-input inlist radio" name="ticket.tipoTicket" value="gratuito"
				data-bindingonload="clickRadioPagamento" data-event="change">
			<spring:message code="label.ticket.gratuito" />
		</div>
		<div class="col-lg-2 col-md-3 col-sm-4 Input validvalue">
			<input type="radio" class="radio-ruolo option-input inlist radio" name="ticket.tipoTicket" value="pagamento"
				data-bindingonload="clickRadioPagamento" data-event="change">
			<spring:message code="label.ticket.pagamento" />
		</div>
	</div>
	<div id="boxticket" style="display:none;">
		<div class="row" style="margin-top: 10px; margin-right: 0px; margin-left: 0px">
			<div class="col-md-6 Input" style="height: auto; display:none;" id="inputticket">
				<div class="boxInput ticket-euro">
					<i class="glyphicon glyphicon-euro" aria-hidden="true"></i> 
						<input type="text" class="form-control Input-text" name="ticket.ticketIntero"
						id="t_intero" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" maxlength="10" placeholder="Inserisci">
					<div class="scegliere">
						<spring:message code="label.ticket.intero" />
					</div>
				</div>
			</div>
			<div class="col-md-6 Input" style="height: auto; display:none;" id="inputlink">
				<div class="boxInput">
					<spring:message code="placeholder.video.link" var="phNlink" />
					<input type="text" class="form-control Input-text" name="ticket.linkPrenotazioni" id="t_link" maxlength="300" placeholder="${phNlink}" />
					<div class="scegliere">
						<spring:message code="label.ticket.link" />
					</div>
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 10px; margin-right: 0px; margin-left: 0px; display:none;" id="accig">
			<div class="col-md-12 Input" style="margin-bottom: 10px; height: auto">
				<div class="accordionA41">
					<div class="accordionA41-header" style="text-align: left; color: #003A54; height: 75px;" data-toggle="collapse" data-target="#collapseA41"
						aria-expanded="false" aria-controls="collapseA41">
						<span class="icon-accreditamento_fiere" style="top: 25px; display: inline-block; position: absolute; font-size: 35px;"></span>
						<div style="padding-left: 46px; float: left;">Ingresso gratuito</div>
						<div style="float: right; padding-top: 16px;">
							<img src="${contextPath}/assets/images/FRECCIA_GIU.svg" class="frecciafiltro" style="width: 18px;">
						</div>
					</div>
					<div class="accordionA41-content" id="collapseA41" aria-labelledby="headingA41" data-parent="#accordionExample" style="background: #F2F2F2;">
						<div class="row" style="margin-top: 10px; margin-right: 0px; margin-left: 0px">
							<div class="col-md-12 col-sm-12 Input" style="height: auto; background-color: transparent;">
								<div class="scegliere">
									<spring:message code="label.ticket.gratuitoPer" />
								</div>
								<label class="checkbox" style="display: inline-block; margin-right: 20px"> <input type="checkbox" style="position: inherit"
									name="ticket.flagGratisAccompagnatori" value="true"> <spring:message code="Ticket.flagGratisAccompagnatori" />
								</label> <label class="checkbox" style="display: inline-block; margin-right: 20px"> <input type="checkbox" style="position: inherit"
									name="ticket.flagGratisAnziani" value="true"> <spring:message code="Ticket.flagGratisAnziani" />
								</label> <label class="checkbox" style="display: inline-block; margin-right: 20px"> <input type="checkbox" style="position: inherit"
									name="ticket.flagGratisBambini" value="true"> <spring:message code="Ticket.flagGratisBambini" />
								</label> <label class="checkbox" style="display: inline-block; margin-right: 20px"> <input type="checkbox" style="position: inherit"
									name="ticket.flagGratisConvenzioni" value="true"> <spring:message code="Ticket.flagGratisConvenzioni" />
								</label> <label class="checkbox" style="display: inline-block; margin-right: 20px"> <input type="checkbox" style="position: inherit"
									name="ticket.flagGratisDisabili" value="true"> <spring:message code="Ticket.flagGratisDisabili" />
								</label>
							</div>
						</div>
						<div class="row" style="margin-top: 28px; margin-right: 0px; margin-left: 0px; display: none;" id="convenzioniAttiveG">
							<div class="col-md-12 Input" style="height: auto; background-color: transparent;">
								<spring:message code="placeholder.ticket.conv" var="phConv" />
								<div class="boxInput">
									<input type="text" class="form-control Input-text" name="ticket.convenzioniAttiveG" maxlength="300" placeholder="${phConv}" />
									<div class="scegliere">
										<spring:message code="label.ticket.specificaConvenzioni" />
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 10px; margin-right: 0px; margin-left: 0px; display:none;" id="accir">
			<div class="col-md-12 Input" style="margin-bottom: 10px; height: auto">
				<div class="accordionA42">
					<div class="accordionA42-header" style="text-align: left; color: #003A54; height: 75px;" data-toggle="collapse" data-target="#collapseA42"
						aria-expanded="false" aria-controls="collapseA42">
						<span class="icon-accreditamento_fiere" style="top: 25px; display: inline-block; position: absolute; font-size: 35px;"></span>
						<div style="padding-left: 46px; float: left;">Ingresso ridotto</div>
						<div style="float: right; padding-top: 16px;">
							<img src="${contextPath}/assets/images/FRECCIA_GIU.svg" class="frecciafiltro" style="width: 18px;">
						</div>
					</div>
					<div class="accordionA42-content" id="collapseA42" aria-labelledby="headingA42" data-parent="#accordionExample" style="background: #F2F2F2;">
						<div class="row" style="margin-top: 10px; margin-right: 0px; margin-left: 0px">
							<div class="col-md-12 col-sm-12 Input" style="height: auto; background-color: transparent;">
								<div class="scegliere">
									<spring:message code="label.ticket.ridottoPer" />
								</div>
								<label class="checkbox" style="display: inline-block; margin-right: 20px"> <input type="checkbox" style="position: inherit"
									name="ticket.flagRidottoAccompagnatori" value="true"> <spring:message code="Ticket.flagRidottoAccompagnatori" />
								</label> <label class="checkbox" style="display: inline-block; margin-right: 20px"> <input type="checkbox" style="position: inherit"
									name="ticket.flagRidottoAnziani" value="true"> <spring:message code="Ticket.flagRidottoAnziani" />
								</label> <label class="checkbox" style="display: inline-block; margin-right: 20px"> <input type="checkbox" style="position: inherit"
									name="ticket.flagRidottoBambini" value="true"> <spring:message code="Ticket.flagRidottoBambini" />
								</label> <label class="checkbox" style="display: inline-block; margin-right: 20px"> <input type="checkbox" style="position: inherit"
									name="ticket.flagRidottoConvenzioni" value="true"> <spring:message code="Ticket.flagRidottoConvenzioni" />
								</label> <label class="checkbox" style="display: inline-block; margin-right: 20px"> <input type="checkbox" style="position: inherit"
									name="ticket.flagRidottoDisabili" value="true"> <spring:message code="Ticket.flagRidottoDisabili" />
								</label>
							</div>
						</div>
						<div class="row" style="margin-top: 28px; margin-right: 0px; margin-left: 0px">
							<div class="col-md-6 Input" style="height: auto">
								<div class="boxInput ticket-euro">
									<i class="glyphicon glyphicon-euro" aria-hidden="true"></i> 
										<input type="text" class="form-control Input-text" name="ticket.ticketRidotto"
										id="t_ridotto" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"  maxlength="10" placeholder="${phConv}">
									<div class="scegliere">
										<spring:message code="label.ticket.ridotto" />
									</div>
								</div>
							</div>
							<div class="col-md-6 Input" style="height: auto; display: none;" id="convenzioniAttiveR">
								<spring:message code="placeholder.ticket.conv" var="phConv2" />
								<div class="boxInput">
									<input type="text" class="form-control Input-text" name="ticket.convenzioniAttiveR" maxlength="300" placeholder="${phConv2}" />
									<div class="scegliere">
										<spring:message code="label.ticket.specificaConvenzioni" />
									</div>
								</div>
							</div>
						</div>
						<div class="row" style="margin-top: 28px; margin-right: 0px; margin-left: 0px">
							<div class="col-md-6  Input" style="margin-bottom: 10px; height: auto">
								<div class="boxInput ticket-euro">
									<i class="glyphicon glyphicon-euro" aria-hidden="true"></i> <input type="text" class="form-control Input-text"
										name="ticket.ticketGruppiRidotto" id="t_gruppi" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"  maxlength="10" placeholder="${phConv}">
									<div class="scegliere">
										<spring:message code="label.ticket.ridottoPerGruppi" />
									</div>
								</div>
							</div>
							<div class="col-md-6 Input" style="margin-bottom: 10px; height: auto">
								<spring:message code="placeholder.ticket.numpersone" var="phNpersone" />
								<div class="boxInput">
									<input type="text" class="form-control Input-text" name="ticket.ticketGruppiNumero" id="t_almeno"
										oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" maxlength="10" placeholder="${phNpersone}">
									<div class="scegliere">
										<spring:message code="label.ticket.ridottoPerAlmeno" />
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-12  Input" id="boxmobileLingua" style="display: none; padding-bottom: 15px;">
		<div class="mobile-input">
			<select id="mobiletabLinguaTic" class="form-control Input-text">
				<option data-target="#itnota">IT</option>
				<option data-target="#ennota">EN</option>
				<option data-target="#spnota">SP</option>
				<option data-target="#frnota">FR</option>
				<option data-target="#denota">DE</option>
				<option data-target="#denota">RU</option>
			</select>
			<div class="scegliere">Cambia lingua</div>
		</div>
	</div>
	<ul class="nav nav-tabs" id="myTab2" role="tablist" style="margin-right: 0px; margin-left: 0px; margin-bottom: 15px">
		<li class="nav-item active" style="cursor: pointer" id="itn"><a href="#itnota" role="tab" data-toggle="tab" class="nav-link active"
			id="itn-tab" style="cursor: pointer">IT</a></li>
		<li class="nav-item" style="cursor: pointer" id="enn"><a href="#ennota" role="tab" data-toggle="tab" class="nav-link" id="enn-tab"
			style="cursor: pointer">EN</a></li>
		<li class="nav-item" style="cursor: pointer" id="spn"><a href="#spnota" role="tab" data-toggle="tab" class="nav-link" id="spn-tab"
			style="cursor: pointer">SP</a></li>
		<li class="nav-item" style="cursor: pointer" id="frn"><a href="#frnota" role="tab" data-toggle="tab" class="nav-link" id="frn-tab"
			style="cursor: pointer">FR</a></li>
		<li class="nav-item" style="cursor: pointer" id="den"><a href="#denota" role="tab" data-toggle="tab" class="nav-link" id="den-tab"
			style="cursor: pointer">DE</a></li>
		<li class="nav-item" style="cursor: pointer" id="run"><a href="#runota" role="tab" data-toggle="tab" class="nav-link" id="run-tab"
			style="cursor: pointer">RU</a></li>
	</ul>
	<spring:message code="js.nota.inserisci" var="phNota" />
	<div class="tab-pane fade active in" id="itnota">
		<div class="col-md-12 Input" style="margin-bottom: 10px; margin-top: 20px">
			<div class="boxInput">
				<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 200px" name="ticket.notaMulti.IT" id="it_nota"
					maxlength="4000" placeholder="${phNota}"></textarea>
				<div class="scegliere" style="bottom: 110% !important;">
					<spring:message code="label.ticket.nota" />
				</div>
			</div>
		</div>
	</div>
	<div class="tab-pane fade" id="ennota">
		<div class="col-md-12 Input" style="margin-bottom: 10px; margin-top: 20px">
			<spring:message code="placeholder.ticket.conv" var="phConv" />
			<div class="boxInput">
				<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 200px" name="ticket.notaMulti.EN" id="en_nota"
					maxlength="4000" placeholder="${phNota}"></textarea>
				<div class="scegliere" style="bottom: 110% !important;">
					<spring:message code="label.ticket.nota" />
				</div>
			</div>
		</div>
	</div>
	<div class="tab-pane fade" id="spnota">
		<div class="col-md-12 Input" style="margin-bottom: 10px; margin-top: 20px">
			<spring:message code="placeholder.ticket.conv" var="phConv" />
			<div class="boxInput">
				<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 200px" name="ticket.notaMulti.SP" id="sp_nota"
					maxlength="4000" placeholder="${phNota}"></textarea>
				<div class="scegliere" style="bottom: 110% !important;">
					<spring:message code="label.ticket.nota" />
				</div>
			</div>
		</div>
	</div>
	<div class="tab-pane fade" id="frnota">
		<div class="col-md-12 Input" style="margin-bottom: 10px; margin-top: 20px">
			<div class="boxInput">
				<textarea class="form-control Input-textre size" style="min-height: 75px; max-height: 200px" name="ticket.notaMulti.FR" id="fr_nota"
					maxlength="4000" placeholder="${phNota}"></textarea>
				<div class="scegliere" style="bottom: 110% !important;">
					<spring:message code="label.ticket.nota" />
				</div>
			</div>
		</div>
	</div>
	<div class="tab-pane fade" id="denota">
		<div class="col-md-12 Input" style="margin-bottom: 10px; margin-top: 20px">
			<div class="boxInput">
				<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 200px" name="ticket.notaMulti.DE" id="de_nota"
					maxlength="4000" placeholder="${phNota}"></textarea>
				<div class="scegliere" style="bottom: 110% !important;">
					<spring:message code="label.ticket.nota" />
				</div>
			</div>
		</div>
	</div>
	<div class="tab-pane fade" id="runota">
		<div class="col-md-12 Input" style="margin-bottom: 10px; margin-top: 20px">
			<div class="boxInput">
				<textarea class="form-control Input-text resize" style="min-height: 75px; max-height: 200px" name="ticket.notaMulti.RU" id="ru_nota"
					maxlength="4000" placeholder="${phNota}"></textarea>
				<div class="scegliere" style="bottom: 110% !important;">
					<spring:message code="label.ticket.nota" />
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	$(document)
			.ready(
					function() {
						$(
								'.accordionA41-header, .accordionA42-header, .accordionA43-header')
								.on(
										'click',
										function(event) {
											var link = $(this).find(
													".frecciafiltro");
											var classblocco = $(this).attr(
													"aria-expanded");
											var faq = $(this);

											if (classblocco == 'true') {
												link
														.attr("src",
																"${contextPath}/assets/images/FRECCIA_GIU.svg");

											} else {
												link
														.attr("src",
																"${contextPath}/assets/images/FRECCIA_SU.svg");
											}
										});
					});
</script>