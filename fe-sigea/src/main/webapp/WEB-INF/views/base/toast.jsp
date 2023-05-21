<div class="dms-toast--wrapper">

	<!-- toast success -->
	<div class="dms-toast dms-toast--success" role="alert" aria-live="assertive" style="display: none" id="tostelement">
		<div class="d-flex align-items-center justify-content-start">
			<div class="icon-wrapper">
				<i class="dms-wk-icon-success"></i>
			</div>
			<span id="title"></span>
		</div>
		<button type="button" class="close" aria-label="close" id="closetoast">
			<i class="dms-wk-icon-close"></i>
		</button>
	</div>

	<!-- toast warning -->
	<div class="dms-toast dms-toast--warning" role="alert" aria-live="assertive" style="display: none" id="tostelement">
		<div class="d-flex align-items-center justify-content-start">
			<div class="icon-wrapper">
				<i class="dms-wk-icon-warning"></i>
			</div>
			<span id="title" class="tr-p2"></span>
		</div>
		<button type="button" class="close" aria-label="close" id="closetoast">
			<i class="dms-wk-icon-close"></i>
		</button>
	</div>

	<!-- toast error -->
	<div class="dms-toast dms-toast--error" role="alert" aria-live="assertive" style="display: none" id="tostelement">
		<div class="d-flex align-items-center justify-content-start">
			<div class="icon-wrapper">
				<i class="dms-wk-icon-error"></i>
			</div>
			<span id="title"></span>
		</div>
		<button type="button" class="close" aria-label="close" id="closetoast">
			<i class="dms-wk-icon-close"></i>
		</button>
	</div>

</div>

<script>

$(document).on('click', '#closetoast' , function(){
	$(this).parent().fadeOut("slow");
});

function opendmstoastsuccess(text){	
	$(".dms-toast--success").find('#title').empty().html(text);
	
	$(".dms-toast--success").fadeIn();
	window.setTimeout("closedmstoastsuccess()", 5000);	
}

function closedmstoastsuccess(){	
	$(".dms-toast--success").fadeOut("slow");
} 

function opendmstoastwarning(text){	
	$(".dms-toast--warning").find('#title').empty().html(text);
	$(".dms-toast--warning").fadeIn();	
	window.setTimeout("closedmstoastwarning()", 5000);	
}
function closedmstoastwarning(){	
	$(".dms-toast--success").fadeOut("slow");
} 

function opendmsdmstoasterror(text){		
	$(".dms-toast--error").find('#title').empty().html(text);
	$(".dms-toast--error").fadeIn();	
	window.setTimeout("closedmsdmstoasterror()", 5000);		
}

function closedmsdmstoasterror(){	
	$(".dms-toast--success").fadeOut("slow");
} 

</script>















