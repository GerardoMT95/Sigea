<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-lg-12 col-md-12 col-sm-12 Input" id="boxmobileAllegatoVip" style="display: none;">
	<div class="boxInput mobile-input">
		<select id="mobiletabAllegatoVip" class="form-control Input-text">
			<option data-target="#immaginiVIP-tab" value="<spring:message code="label.media.immagini"/>"><spring:message code="label.media.immagini" /></option>
			<option data-target="#documentiVIP-tab" value="<spring:message code="label.media.documenti"/>"><spring:message
					code="label.media.documenti" /></option>
			<option data-target="#videoVIP-tab" value="<spring:message code="label.media.video"/>"><spring:message code="label.media.video" /></option>
		</select>
		<div class="scegliere">Seleziona media</div>
	</div>
</div>
<ul class="nav nav-tabs" id="docVIPTab" role="tablist" style="margin-right: 0px; margin-left: 0px; margin-bottom: 15px">
	<li class="nav-item active" style="cursor: pointer" id="immVIP">
		<div class="nav-link active" id="immaginiVIP-tab" style="cursor: pointer">
			<spring:message code="label.media.immagini" />
		</div>
	</li>
	<li class="nav-item" style="cursor: pointer" id="docVIP">
		<div class="nav-link" id="documentiVIP-tab" style="cursor: pointer">
			<spring:message code="label.media.documenti" />
		</div>
	</li>
	<li class="nav-item" style="cursor: pointer" id="vidVIP">
		<div class="nav-link" id="videoVIP-tab" style="cursor: pointer">
			<spring:message code="label.media.video" />
		</div>
	</li>
</ul>
<div class="tab-content" id="docVIPCont">
	<div class="tab-pane fade active in" id="immaginiVIPbox">
		<jsp:include page="tabImmaginiVIP.jsp" flush="false" />
	</div>
	<div class="tab-pane fade" id="documentiVIPbox">
		<jsp:include page="tabDocumentiVIP.jsp" flush="false" />
	</div>
	<div class="tab-pane fade" id="videoVIPbox">
		<jsp:include page="tabVideoVIP.jsp" flush="false" />
	</div>
</div>

<script>

$('div#immaginiVIP-tab').click(function() {
	
	$(this).closest('ul').find('li').each(function( index ) { 
		$(this).removeClass('active');
	});
	
	$(this).closest('ul').find('div').each(function( index ) { 
		$(this).removeClass('active');
	});	
	
	$(this).removeClass('active').addClass('active');	
	$(this).closest('li').removeClass('active').addClass('active');	
	
	$('#docVIPCont').find('div').each(function( index ) { 
		$(this).removeClass('active in');
	});
	
	$('#docAggVIPCont').find('div').each(function( index ) { 
		$(this).removeClass('active in');
	});
	
	$('#docVIPCont').find('#immaginiVIPbox').removeClass('active in').addClass('active in');
	$('#docAggVIPCont').find('#immaginiAggVIPbox').removeClass('active in').addClass('active in');
	
	$('#docVIPCont').find('.itready').attr('readonly', false);
	$('#docAggVIPCont').find('.itready').attr('readonly', false);
	
	$('#docVIPCont').find('.itreadys').attr('disabled', false);
	$('#docAggVIPCont').find('.itreadys').attr('disabled', false);
	
	$("ul#VIPimmtab").find('li').each(function( index ) { 
		$(this).removeClass('active in');
	});	
	
	$("ul#VIPimmtab li").first().addClass('active');	
	$("div[data-tab='itVIPmedia']").addClass('active in');
	
});

$('div#documentiVIP-tab').click(function() {
	
	$(this).closest('ul').find('li').each(function( index ) { 
		$(this).removeClass('active');
	});
	
	$(this).closest('ul').find('div').each(function( index ) { 
		$(this).removeClass('active');
	});	
	
	$(this).removeClass('active').addClass('active');	
	$(this).closest('li').removeClass('active').addClass('active');
	
	$('#docVIPCont').find('div').each(function( index ) { 
		$(this).removeClass('active in');
	});
	
	$('#docAggVIPCont').find('div').each(function( index ) { 
		$(this).removeClass('active in');
	});	
	
	$('#docVIPCont').find('#documentiVIPbox').removeClass('active in').addClass('active in');
	$('#docAggVIPCont').find('#documentiAggVIPbox').removeClass('active in').addClass('active in');	
	
	$('#docVIPCont').find('.itready').attr('readonly', false);
	$('#docAggVIPCont').find('.itready').attr('readonly', false);
	
	$('#docVIPCont').find('.itreadys').attr('disabled', false);
	$('#docAggVIPCont').find('.itreadys').attr('disabled', false);	
	
	$("ul#myVIPdc").find('li').each(function( index ) { 
		$(this).removeClass('active in');
	});	
	
	$("ul#myVIPdc li").first().addClass('active');
	$("div[data-tab='itVIPdocumenti']").addClass('active in');
	
});

$('div#videoVIP-tab').click(function() {
	
	$(this).closest('ul').find('li').each(function( index ) { 
		$(this).removeClass('active');
	});
	
	$(this).closest('ul').find('div').each(function( index ) { 
		$(this).removeClass('active');
	});	
	
	$(this).removeClass('active').addClass('active');	
	$(this).closest('li').removeClass('active').addClass('active');	
	
	$('#docVIPCont').find('div').each(function( index ) { 
		$(this).removeClass('active in');
	});
	
	$('#docAggVIPCont').find('div').each(function( index ) { 
		$(this).removeClass('active in');
	});
	
	$('#docVIPCont').find('#videoVIPbox').removeClass('active in').addClass('active in');	
	$('#docAggVIPCont').find('#videoAggVIPbox').removeClass('active in').addClass('active in');
	
	$('#docVIPCont').find('.itready').attr('readonly', false);
	$('#docAggVIPCont').find('.itready').attr('readonly', false);
	
	$('#docVIPCont').find('.itreadys').attr('disabled', false);
	$('#docAggVIPCont').find('.itreadys').attr('disabled', false);
	
	
	
	$("ul#vidVIPTab").find('li').each(function( index ) { 
		$(this).removeClass('active in');
	});	
	
	$("ul#vidVIPTab li").first().addClass('active');
	$("div[data-tab='itVIPvideo']").addClass('active in');
	
});

//mobile 
$(document).on( "change", "select#mobiletabAllegatoVip", function() {    
	
	var mediavalue = $(this).val();
	
	if(mediavalue == 'Media' || mediavalue == 'Immagini'){
		
		$('#docVIPCont').find('div').each(function( index ) { 
			$(this).removeClass('active in');
		});
		
		$('#docAggVIPCont').find('div').each(function( index ) { 
			$(this).removeClass('active in');
		});
		
		$('#docVIPCont').find('#immaginiVIPbox').removeClass('active in').addClass('active in');
		$('#docAggVIPCont').find('#immaginiAggVIPbox').removeClass('active in').addClass('active in');
		
		$("ul#VIPimmtab").find('li').each(function( index ) { 
			$(this).removeClass('active in');
		});	
		
		$("ul#VIPimmtab li").first().addClass('active');	
		$("div[data-tab='itVIPmedia']").addClass('active in');
		
	}else if(mediavalue == 'Altri allegati'){
		
		$('#docVIPCont').find('div').each(function( index ) { 
			$(this).removeClass('active in');
		});
		
		$('#docAggVIPCont').find('div').each(function( index ) { 
			$(this).removeClass('active in');
		});	
		
		$('#docVIPCont').find('#documentiVIPbox').removeClass('active in').addClass('active in');
		$('#docAggVIPCont').find('#documentiAggVIPbox').removeClass('active in').addClass('active in');
		
		$("ul#myVIPdc").find('li').each(function( index ) { 
			$(this).removeClass('active in');
		});	
		
		$("ul#myVIPdc li").first().addClass('active');
		$("div[data-tab='itVIPdocumenti']").addClass('active in');
		
	}else if(mediavalue == 'Link esterni'){
		
		$('#docVIPCont').find('div').each(function( index ) { 
			$(this).removeClass('active in');
		});
		
		$('#docAggVIPCont').find('div').each(function( index ) { 
			$(this).removeClass('active in');
		});
		
		$('#docVIPCont').find('#videoVIPbox').removeClass('active in').addClass('active in');	
		$('#docAggVIPCont').find('#videoAggVIPbox').removeClass('active in').addClass('active in');
		
		$("ul#vidVIPTab").find('li').each(function( index ) { 
			$(this).removeClass('active in');
		});	
		
		$("ul#vidVIPTab li").first().addClass('active');
		$("div[data-tab='itVIPvideo']").addClass('active in');
		
	}	
	
});

</script>